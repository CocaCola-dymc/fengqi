package com.dymc.service;

import com.dymc.mapper.LogMapper;
import com.dymc.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogMapper logMapper;

    @Override
    public int insertLog(Log log) {
        int rows = logMapper.insertLog(log);
        return rows;
    }

    @Override
    public List<Log> queryLog(Log log) {
        List<Log> list = logMapper.queryLog(log);
        return list;
    }

    @Override
    public List<Log> queryLogs(Log log) {
        List<Log> list = logMapper.queryLogs(log);
        return list;
    }
}
