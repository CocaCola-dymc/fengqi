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

    @RequestMapping("doInsertLog")
    public int doInsertLog(Log log){
        return logService.insertLog(log);
    }

    @RequestMapping("doQueryLog")
    public List<Log> doQueryLog(Log log){
        return logService.queryLog(log);
    }

    @RequestMapping("doQueryLogs")
    public List<Log> doQueryLogs(Log log){
        return logService.queryLogs(log);
    }
}
