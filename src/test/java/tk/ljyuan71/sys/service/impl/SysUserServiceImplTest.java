package tk.ljyuan71.sys.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.ljyuan71.sys.model.SysUser;
import tk.ljyuan71.sys.service.SysUserService;
import tk.ljyuan71.utils.Pager;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {
    @Autowired
    private SysUserService sysUserService;

    @Test
    public void queryAllJson(){
        Pager<SysUser> pager = sysUserService.queryAllJson(0,10,null,null,null);
        List<SysUser> dataList = pager.getDataList();

    }

}