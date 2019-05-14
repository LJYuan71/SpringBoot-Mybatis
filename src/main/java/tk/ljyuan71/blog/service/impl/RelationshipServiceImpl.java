package tk.ljyuan71.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.ljyuan71.blog.dao.RelationshipMapper;
import tk.ljyuan71.blog.model.Relationship;
import tk.ljyuan71.blog.model.RelationshipExample;
import tk.ljyuan71.blog.service.IRelationshipService;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by BlueT on 2017/3/18.
 */
@Service
public class RelationshipServiceImpl implements IRelationshipService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RelationshipServiceImpl.class);

    @Resource
    private RelationshipMapper relationshipMapper;

    @Override
    public void deleteById(Integer cid, Integer mid) {
        RelationshipExample relationshipExample = new RelationshipExample();
        RelationshipExample.Criteria criteria = relationshipExample.createCriteria();
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        relationshipMapper.deleteByExample(relationshipExample);
    }

    @Override
    public List<Relationship> getRelationshipById(Integer cid, Integer mid) {
        RelationshipExample relationshipExample = new RelationshipExample();
        RelationshipExample.Criteria criteria = relationshipExample.createCriteria();
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        return relationshipMapper.selectByExample(relationshipExample);
    }

    @Override
    public void insert(Relationship relationshipKey) {
        relationshipMapper.insert(relationshipKey);
    }

    @Override
    public Long countById(Integer cid, Integer mid) {
        LOGGER.debug("Enter countById method:cid={},mid={}",cid,mid);
        RelationshipExample relationshipExample = new RelationshipExample();
        RelationshipExample.Criteria criteria = relationshipExample.createCriteria();
        if (cid != null) {
            criteria.andCidEqualTo(cid);
        }
        if (mid != null) {
            criteria.andMidEqualTo(mid);
        }
        long num = relationshipMapper.countByExample(relationshipExample);
        LOGGER.debug("Exit countById method return num={}",num);
        return num;
    }

}
