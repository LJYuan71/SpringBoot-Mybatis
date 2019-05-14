package tk.ljyuan71.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.ljyuan71.blog.controller.BaseController;
import tk.ljyuan71.sys.model.SysUser;
import tk.ljyuan71.sys.service.SysUserService;
import tk.ljyuan71.utils.Pager;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sys")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("list.html")
    public String index() {
        return "sys/list";
    }

    @ResponseBody
    @GetMapping("queryAllJson.do")
    public Map<String, Object> queryAllJson(@RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                            @RequestParam(value = "limit", defaultValue = "10") Integer limit,String search, String sort, String order) {
        Map<String, Object> map = new HashMap<>();
        Pager<SysUser> pager = sysUserService.queryAllJson(offset,limit,search,sort,order);
        map.put("rows",pager.getDataList());
        map.put("total", pager.getCount());
        return map;
    }

}
