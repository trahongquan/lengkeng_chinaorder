// LogsServiceImpl.java
package com.shoponline.chinaorder.service.logs;

import com.shoponline.chinaorder.dao.LogsRepository;
import com.shoponline.chinaorder.entity.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsServiceImpl implements LogsService {

    @Autowired
    private LogsRepository logsRepository;

    @Override
    public List<Logs> getAllLogs() {
        return logsRepository.findAll();
    }

    @Override
    public Logs createLog(Logs log) {
        return logsRepository.save(log);
    }

    @Override
    public Logs findLogById(int id) {
        return logsRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteLog(int id) {
        logsRepository.deleteById(id);
    }
}
// LogsServiceImpl.java
