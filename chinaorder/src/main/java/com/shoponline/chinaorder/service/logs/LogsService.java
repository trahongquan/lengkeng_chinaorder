// LogsService.java
package com.shoponline.chinaorder.service.logs;

import com.shoponline.chinaorder.entity.Logs;

import java.util.List;

public interface LogsService {
    List<Logs> getAllLogs();

    Logs createLog(Logs log);

    Logs findLogById(int id);

    void deleteLog(int id);
}
