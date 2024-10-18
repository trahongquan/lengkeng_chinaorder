// CommuneService.java
package com.shoponline.chinaorder.service.commune;

import com.shoponline.chinaorder.entity.Commune;

import java.util.List;

public interface CommuneService {
    List<Commune> getAllCommunes();

    Commune createCommune(Commune commune);

    Commune findCommuneById(int id);

    void deleteCommune(int id);
}
