// PeopleServiceImpl.java
package com.shoponline.chinaorder.service.people;

import com.shoponline.chinaorder.dao.PeopleRepository;
import com.shoponline.chinaorder.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public List<People> getAllPeople() {
        return peopleRepository.findAll();
    }

    @Override
    public People createPerson(People person) {
        return peopleRepository.save(person);
    }

    @Override
    public People findPersonById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }
}
