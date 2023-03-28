package com.dymc.service;

import com.dymc.pojo.Log;

import java.util.List;

public interface LogService {
    int insertLog(Log log);
    List<Log> queryLog(Log log);
    List<Log> queryLogs(Log log);
}
