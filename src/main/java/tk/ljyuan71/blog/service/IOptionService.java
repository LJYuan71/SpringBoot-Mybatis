package tk.ljyuan71.blog.service;

import tk.ljyuan71.blog.model.Option;

import java.util.List;
import java.util.Map;

/**
 * options的接口
 * Created by BlueT on 2017/3/7.
 */
public interface IOptionService {

    void insertOption(Option optionVo);

    void insertOption(String name, String value);

    List<Option> getOptions();


    /**
     * 保存一组配置
     *
     * @param options
     */
    void saveOptions(Map<String, String> options);

    Option getOptionByName(String name);
}
