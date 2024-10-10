package com.shoponline.chinaorder.service.users;


import com.shoponline.chinaorder.dao.UserRepository;
import com.shoponline.chinaorder.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    @Override
//    public Users findByPeopleId(int id){
//        return userRepository.findByPeopleid(id);
//    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> getAllUsersEnable() {
        return userRepository.findAll().stream().filter(user -> user.getEnabled()==1).collect(Collectors.toList());
    }

    @Override
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUser(Users user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
    @Override
    public void save(Users user){
        userRepository.save(user);
    };
}
