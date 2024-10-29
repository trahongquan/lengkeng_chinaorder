// StatusService.java
package com.shoponline.chinaorder.service.status;

import com.shoponline.chinaorder.entity.Status;

import java.util.List;

public interface StatusService {
    List<Status> getAllStatuses();

    Status createStatus(Status status);

    Status findStatusById(int id);

    void deleteStatus(int id);

    List<Status> findStatusNotUsedInOrder();

}
