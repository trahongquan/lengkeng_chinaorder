package com.shoponline.chinaorder.service.users;


import com.shoponline.chinaorder.entity.Users;

import java.util.List;

public interface UserService {

    Users findByUsername(String username);

    Users findByPeopleId(int id);

    List<Users> getAllUsers();

    List<Users> getAllUsersEnable();

    Users createUser(Users user);

    void updateUser(Users user);

    void deleteUser(String username);

    public void save(Users user);
}
