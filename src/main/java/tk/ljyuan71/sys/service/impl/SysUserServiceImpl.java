package tk.ljyuan71.sys.service.impl;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.ljyuan71.sys.dao.SysUserMapper;
import tk.ljyuan71.sys.model.SysUser;
import tk.ljyuan71.sys.model.SysUserExample;
import tk.ljyuan71.sys.service.SysUserService;
import tk.ljyuan71.utils.Pager;

import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Pager<SysUser> queryAllJson(Integer offset, Integer limit, String search, String sort, String order) {
        SysUserExample example = new SysUserExample();
        if (StringUtils.isNotBlank(sort)) {
            example.setOrderByClause(sort + order == null ? "" : order);
        }
        if (StringUtils.isNotBlank(search)) {
            example.createCriteria().andUserNameLike(search).andNickNameLike(search).andPhoneLike(search).andEmailLike(search);
        }
        PageHelper.startPage(offset, limit);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        Long count = sysUserMapper.countByExample(example);
        Pager<SysUser> pager = new Pager<>();
        pager.setDataList(sysUsers);
        pager.setCount(count.intValue());
        return pager;
    }
}
