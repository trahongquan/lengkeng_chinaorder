// CommuneServiceImpl.java
package com.shoponline.chinaorder.service.commune;

import com.shoponline.chinaorder.dao.CommuneRepository;
import com.shoponline.chinaorder.entity.Commune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommuneServiceImpl implements CommuneService {

    @Autowired
    private CommuneRepository communeRepository;

    @Override
    public List<Commune> getAllCommunes() {
        return communeRepository.findAll();
    }

    @Override
    public Commune createCommune(Commune commune) {
        return communeRepository.save(commune);
    }

    @Override
    public Commune findCommuneById(int id) {
        return communeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCommune(int id) {
        communeRepository.deleteById(id);
    }
}
