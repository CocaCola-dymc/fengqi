package com.dymc.controller;

import com.dymc.pojo.Log;
import com.dymc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fengqi")
public class LogController {
    @Autowired
    private LogService logService;
//    插入用户充电记录
    @RequestMapping("doInsertLog")
    public int doInsertLog(Log log){
        return logService.insertLog(log);
    }
//    用户查询自己的充电记录
    @RequestMapping("doQueryLog")
    public List<Log> doQueryLog(Log log){
        return logService.queryLog(log);
    }
//    管理员查询所有用户的充电记录
    @RequestMapping("doQueryLogs")
    public List<Log> doQueryLogs(Log log){
        return logService.queryLogs(log);
    }
}
