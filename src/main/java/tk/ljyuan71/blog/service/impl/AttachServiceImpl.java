package tk.ljyuan71.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.ljyuan71.blog.dao.AttachMapper;
import tk.ljyuan71.blog.model.Attach;
import tk.ljyuan71.blog.model.AttachExample;
import tk.ljyuan71.blog.service.IAttachService;
import tk.ljyuan71.utils.DateKit;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by wangq on 2017/3/20.
 */
@Service
public class AttachServiceImpl implements IAttachService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttachServiceImpl.class);

    @Resource
    private AttachMapper attachDao;

    @Override
    public PageInfo<Attach> getAttachs(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        AttachExample attachExample = new AttachExample();
        attachExample.setOrderByClause("id desc");
        List<Attach> attachs = attachDao.selectByExample(attachExample);
        return new PageInfo<>(attachs);
    }

    @Override
    public Attach selectById(Integer id) {
        if(null != id){
            return attachDao.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    @Transactional
    public void save(String fname, String fkey, String ftype, Integer author) {
        Attach attach = new Attach();
        attach.setFname(fname);
        attach.setAuthorId(author);
        attach.setFkey(fkey);
        attach.setFtype(ftype);
        attach.setCreated(DateKit.getCurrentUnixTime());
        attachDao.insertSelective(attach);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (null != id) {
            attachDao.deleteByPrimaryKey( id);
        }
    }
}
