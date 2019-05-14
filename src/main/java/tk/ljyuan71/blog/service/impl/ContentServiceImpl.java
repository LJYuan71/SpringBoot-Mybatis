package tk.ljyuan71.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.ljyuan71.blog.dao.ContentMapper;
import tk.ljyuan71.blog.dao.MetaMapper;
import tk.ljyuan71.blog.dto.Types;
import tk.ljyuan71.blog.model.Content;
import tk.ljyuan71.blog.model.ContentExample;
import tk.ljyuan71.blog.service.IContentService;
import tk.ljyuan71.blog.service.IMetaService;
import tk.ljyuan71.blog.service.IRelationshipService;
import tk.ljyuan71.constant.WebConst;
import tk.ljyuan71.exception.TipException;
import tk.ljyuan71.utils.DateKit;
import tk.ljyuan71.utils.TaleUtils;
import tk.ljyuan71.utils.Tools;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13 013.
 */
@Service
public class ContentServiceImpl implements IContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Resource
    private ContentMapper contentDao;

    @Resource
    private MetaMapper metaDao;

    @Resource
    private IRelationshipService relationshipService;

    @Resource
    private IMetaService metasService;

    @Override
    @Transactional
    public String publish(Content contents) {
        if (null == contents) {
            return "文章对象为空";
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            return "文章标题不能为空";
        }
        if (StringUtils.isBlank(contents.getContent())) {
            return "文章内容不能为空";
        }
        int titleLength = contents.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            return "文章标题过长";
        }
        int contentLength = contents.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            return "文章内容过长";
        }
        if (null == contents.getAuthorId()) {
            return "请登录后发布文章";
        }
        if (StringUtils.isNotBlank(contents.getSlug())) {
            if (contents.getSlug().length() < 5) {
                return "路径太短了";
            }
            if (!TaleUtils.isPath(contents.getSlug())) return "您输入的路径不合法";
            ContentExample contentExample = new ContentExample();
            contentExample.createCriteria().andTypeEqualTo(contents.getType()).andStatusEqualTo(contents.getSlug());
            long count = contentDao.countByExample(contentExample);
            if (count > 0) return "该路径已经存在，请重新输入";
        } else {
            contents.setSlug(null);
        }

//        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));
        contents.setContent(contents.getContent());
        int time = DateKit.getCurrentUnixTime();
        contents.setCreated(time);
        contents.setModified(time);
        contents.setHits(0);
        contents.setCommentsNum(0);

        String tags = contents.getTags();
        String categories = contents.getCategories();
        contentDao.insert(contents);
        Integer cid = contents.getCid();
        metasService.saveMetas(cid, tags, Types.TAG.getType());
        metasService.saveMetas(cid, categories, Types.CATEGORY.getType());
        return WebConst.SUCCESS_RESULT;
    }

    @Override
    public PageInfo<Content> getContents(Integer p, Integer limit) {
        LOGGER.debug("Enter getContents method");
        ContentExample example = new ContentExample();
        example.setOrderByClause("created desc");
        example.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType());
        PageHelper.startPage(p, limit);
        List<Content> data = contentDao.selectByExampleWithBLOBs(example);
        PageInfo<Content> pageInfo = new PageInfo<>(data);
        LOGGER.debug("Exit getContents method");
        return pageInfo;
    }

    @Override
    public Content getContents(String id) {
        if (StringUtils.isNotBlank(id)) {
            if (Tools.isNumber(id)) {
                Content content = contentDao.selectByPrimaryKey(Integer.valueOf(id));
                return content;
            } else {
                ContentExample contentExample = new ContentExample();
                contentExample.createCriteria().andSlugEqualTo(id);
                List<Content> contents = contentDao.selectByExampleWithBLOBs(contentExample);
                if (contents.size() != 1) {
                    throw new TipException("query content by id and return is not one");
                }
                return contents.get(0);
            }
        }
        return null;
    }

    @Override
    public void updateContentByCid(Content content) {
        if (null != content && null != content.getCid()) {
            contentDao.updateByPrimaryKeySelective(content);
        }
    }

    @Override
    public PageInfo<Content> getArticles(Integer mid, int page, int limit) {
        int total = metaDao.countWithSql(mid);
        PageHelper.startPage(page, limit);
        List<Content> list = contentDao.findByCatalog(mid);
        PageInfo<Content> paginator = new PageInfo<>(list);
        paginator.setTotal(total);
        return paginator;
    }

    @Override
    public PageInfo<Content> getArticles(String keyword, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        ContentExample contentExample = new ContentExample();
        ContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andTypeEqualTo(Types.ARTICLE.getType());
        criteria.andStatusEqualTo(Types.PUBLISH.getType());
        criteria.andTitleLike("%" + keyword + "%");
        contentExample.setOrderByClause("created desc");
        List<Content> contents = contentDao.selectByExampleWithBLOBs(contentExample);
        return new PageInfo<>(contents);
    }

    @Override
    public PageInfo<Content> getArticlesWithpage(ContentExample commentExample, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Content> contents = contentDao.selectByExampleWithBLOBs(commentExample);
        return new PageInfo<>(contents);
    }

    @Override
    @Transactional
    public String deleteByCid(Integer cid) {
        Content contents = this.getContents(cid + "");
        if (null != contents) {
            contentDao.deleteByPrimaryKey(cid);
            relationshipService.deleteById(cid, null);
            return WebConst.SUCCESS_RESULT;
        }
        return "数据为空";
    }

    @Override
    public void updateCategory(String ordinal, String newCatefory) {
        Content content = new Content();
        content.setCategories(newCatefory);
        ContentExample example = new ContentExample();
        example.createCriteria().andCategoriesEqualTo(ordinal);
        contentDao.updateByExampleSelective(content, example);
    }

    @Override
    @Transactional
    public String updateArticle(Content contents) {
        if (null == contents) {
            return "文章对象为空";
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            return "文章标题不能为空";
        }
        if (StringUtils.isBlank(contents.getContent())) {
            return "文章内容不能为空";
        }
        int titleLength = contents.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            return "文章标题过长";
        }
        int contentLength = contents.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            return "文章内容过长";
        }
        if (null == contents.getAuthorId()) {
            return "请登录后发布文章";
        }
        if (StringUtils.isBlank(contents.getSlug())) {
            contents.setSlug(null);
        }
        int time = DateKit.getCurrentUnixTime();
        contents.setModified(time);
        Integer cid = contents.getCid();
//        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));
        contents.setContent(contents.getContent());

        contentDao.updateByPrimaryKeySelective(contents);
        relationshipService.deleteById(cid, null);
        metasService.saveMetas(cid, contents.getTags(), Types.TAG.getType());
        metasService.saveMetas(cid, contents.getCategories(), Types.CATEGORY.getType());
        return WebConst.SUCCESS_RESULT;
    }
}
