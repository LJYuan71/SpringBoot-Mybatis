package tk.ljyuan71.blog.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.ljyuan71.blog.Bo.RestResponseBo;
import tk.ljyuan71.blog.controller.BaseController;
import tk.ljyuan71.blog.dto.Types;
import tk.ljyuan71.blog.model.Meta;
import tk.ljyuan71.blog.service.IMetaService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 13 on 2017/2/21.
 */
@Controller
@RequestMapping("admin/links")
public class LinksController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinksController.class);

    @Resource
    private IMetaService metasService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<Meta> metas = metasService.getMetas(Types.LINK.getType());
        request.setAttribute("links", metas);
        return "admin/links";
    }

    @PostMapping(value = "save")
    @ResponseBody
    public RestResponseBo saveLink(@RequestParam String title, @RequestParam String url,
                                   @RequestParam String logo, @RequestParam Integer mid,
                                   @RequestParam(value = "sort", defaultValue = "0") int sort) {
        try {
            Meta metas = new Meta();
            metas.setName(title);
            metas.setSlug(url);
            metas.setDescription(logo);
            metas.setSort(sort);
            metas.setType(Types.LINK.getType());
            if (null != mid) {
                metas.setMid(mid);
                metasService.update(metas);
            } else {
                metasService.saveMeta(metas);
            }
        } catch (Exception e) {
            String msg = "友链保存失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public RestResponseBo delete(@RequestParam int mid) {
        try {
            metasService.delete(mid);
        } catch (Exception e) {
            String msg = "友链删除失败";
            LOGGER.error(msg, e);
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }

}
