package tk.ljyuan71.blog.service.impl;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.ljyuan71.blog.dao.LogMapper;
import tk.ljyuan71.blog.model.Log;
import tk.ljyuan71.blog.model.LogExample;
import tk.ljyuan71.blog.service.ILogService;
import tk.ljyuan71.constant.WebConst;
import tk.ljyuan71.utils.DateKit;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BlueT on 2017/3/4.
 */
@Service
public class LogServiceImpl implements ILogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    @Resource
    private LogMapper logDao;

    @Override
    public void insertLog(Log log) {
        logDao.insert(log);
    }

    @Override
    public void insertLog(String action, String data, String ip, Integer authorId) {
        Log logs = new Log();
        logs.setAction(action);
        logs.setData(data);
        logs.setIp(ip);
        logs.setAuthorId(authorId);
        logs.setCreated(DateKit.getCurrentUnixTime());
        logDao.insert(logs);
    }

    @Override
    public List<Log> getLogs(int page, int limit) {
        LOGGER.debug("Enter getLogs method:page={},linit={}",page,limit);
        if (page <= 0) {
            page = 1;
        }
        if (limit < 1 || limit > WebConst.MAX_POSTS) {
            limit = 10;
        }
        LogExample logExample = new LogExample();
        logExample.setOrderByClause("id desc");
        PageHelper.startPage((page - 1) * limit, limit);
        List<Log> logs = logDao.selectByExample(logExample);
        LOGGER.debug("Exit getLogs method");
        return logs;
    }
}
