package tk.ljyuan71.sys.service;

import tk.ljyuan71.sys.model.SysUser;
import tk.ljyuan71.utils.Pager;

public interface SysUserService {

    Pager<SysUser> queryAllJson(Integer offset, Integer limit, String search, String sort, String order);
}
