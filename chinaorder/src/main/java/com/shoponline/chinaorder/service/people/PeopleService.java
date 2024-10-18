// PeopleService.java
package com.shoponline.chinaorder.service.people;

import com.shoponline.chinaorder.entity.People;

import java.util.List;

public interface PeopleService {
    List<People> getAllPeople();

    People createPerson(People person);

    People findPersonById(int id);

    void deletePerson(int id);
}
