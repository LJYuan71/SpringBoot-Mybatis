package tk.ljyuan71.blog.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.ljyuan71.blog.dao.MetaMapper;
import tk.ljyuan71.blog.dto.MetaDto;
import tk.ljyuan71.blog.dto.Types;
import tk.ljyuan71.blog.model.Content;
import tk.ljyuan71.blog.model.Meta;
import tk.ljyuan71.blog.model.MetaExample;
import tk.ljyuan71.blog.model.Relationship;
import tk.ljyuan71.blog.service.IContentService;
import tk.ljyuan71.blog.service.IMetaService;
import tk.ljyuan71.blog.service.IRelationshipService;
import tk.ljyuan71.constant.WebConst;
import tk.ljyuan71.exception.TipException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by BlueT on 2017/3/17.
 */
@Service
public class MetaServiceImpl implements IMetaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetaServiceImpl.class);

    @Resource
    private MetaMapper metaDao;

    @Resource
    private IRelationshipService relationshipService;

    @Resource
    private IContentService contentService;

    @Override
    public MetaDto getMeta(String type, String name) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
            return metaDao.selectDtoByNameAndType(name, type);
        }
        return null;
    }

    @Override
    public Integer countMeta(Integer mid) {
        return metaDao.countWithSql(mid);
    }

    @Override
    public List<Meta> getMetas(String types) {
        if (StringUtils.isNotBlank(types)) {
            MetaExample metaExample = new MetaExample();
            metaExample.setOrderByClause("sort desc, mid desc");
            metaExample.createCriteria().andTypeEqualTo(types);
            return metaDao.selectByExample(metaExample);
        }
        return null;
    }

    @Override
    public List<MetaDto> getMetaList(String type, String orderby, int limit) {
        if (StringUtils.isNotBlank(type)) {
            if (StringUtils.isBlank(orderby)) {
                orderby = "count desc, a.mid desc";
            }
            if (limit < 1 || limit > WebConst.MAX_POSTS) {
                limit = 10;
            }
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("type", type);
            paraMap.put("order", orderby);
            paraMap.put("limit", limit);
            return metaDao.selectFromSql(paraMap);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(int mid) {
        Meta metas = metaDao.selectByPrimaryKey(mid);
        if (null != metas) {
            String type = metas.getType();
            String name = metas.getName();

            metaDao.deleteByPrimaryKey(mid);

            List<Relationship> rlist = relationshipService.getRelationshipById(null, mid);
            if (null != rlist) {
                for (Relationship r : rlist) {
                    Content contents = contentService.getContents(String.valueOf(r.getCid()));
                    if (null != contents) {
                        Content temp = new Content();
                        temp.setCid(r.getCid());
                        if (type.equals(Types.CATEGORY.getType())) {
                            temp.setCategories(reMeta(name, contents.getCategories()));
                        }
                        if (type.equals(Types.TAG.getType())) {
                            temp.setTags(reMeta(name, contents.getTags()));
                        }
                        contentService.updateContentByCid(temp);
                    }
                }
            }
            relationshipService.deleteById(null, mid);
        }
    }

    @Override
    @Transactional
    public void saveMeta(String type, String name, Integer mid) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)) {
            MetaExample metaExample = new MetaExample();
            metaExample.createCriteria().andTypeEqualTo(type).andNameEqualTo(name);
            List<Meta> metaVos = metaDao.selectByExample(metaExample);
            Meta metas;
            if (metaVos.size() != 0) {
                throw new TipException("已经存在该项");
            } else {
                metas = new Meta();
                metas.setName(name);
                if (null != mid) {
                    Meta original = metaDao.selectByPrimaryKey(mid);
                    metas.setMid(mid);
                    metaDao.updateByPrimaryKeySelective(metas);
//                    更新原有文章的categories
                    contentService.updateCategory(original.getName(), name);
                } else {
                    metas.setType(type);
                    metaDao.insertSelective(metas);
                }
            }
        }
    }

    @Override
    @Transactional
    public void saveMetas(Integer cid, String names, String type) {
        if (null == cid) {
            throw new TipException("项目关联id不能为空");
        }
        if (StringUtils.isNotBlank(names) && StringUtils.isNotBlank(type)) {
            String[] nameArr = StringUtils.split(names, ",");
            for (String name : nameArr) {
                this.saveOrUpdate(cid, name, type);
            }
        }
    }

    private void saveOrUpdate(Integer cid, String name, String type) {
        MetaExample metaExample = new MetaExample();
        metaExample.createCriteria().andTypeEqualTo(type).andNameEqualTo(name);
        List<Meta> metaVos = metaDao.selectByExample(metaExample);

        int mid;
        Meta metas;
        if (metaVos.size() == 1) {
            metas = metaVos.get(0);
            mid = metas.getMid();
        } else if (metaVos.size() > 1) {
            throw new TipException("查询到多条数据");
        } else {
            metas = new Meta();
            metas.setSlug(name);
            metas.setName(name);
            metas.setType(type);
            metaDao.insertSelective(metas);
            mid = metas.getMid();
        }
        if (mid != 0) {
            Long count = relationshipService.countById(cid, mid);
            if (count == 0) {
                Relationship relationships = new Relationship();
                relationships.setCid(cid);
                relationships.setMid(mid);
                relationshipService.insert(relationships);
            }
        }
    }


    private String reMeta(String name, String metas) {
        String[] ms = StringUtils.split(metas, ",");
        StringBuilder sbuf = new StringBuilder();
        for (String m : ms) {
            if (!name.equals(m)) {
                sbuf.append(",").append(m);
            }
        }
        if (sbuf.length() > 0) {
            return sbuf.substring(1);
        }
        return "";
    }

    @Override
    @Transactional
    public void saveMeta(Meta metas) {
        if (null != metas) {
            metaDao.insertSelective(metas);
        }
    }

    @Override
    @Transactional
    public void update(Meta metas) {
        if (null != metas && null != metas.getMid()) {
            metaDao.updateByPrimaryKeySelective(metas);
        }
    }
}
