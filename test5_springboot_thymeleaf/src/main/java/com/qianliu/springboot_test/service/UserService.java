package com.qianliu.springboot_test.service;



import com.qianliu.springboot_test.entity.User2;

import java.util.List;

public interface UserService {

    public List<User2> getUserList();

    public User2 findUserById(long id);

    public void save(User2 user);

    public void edit(User2 user);

    public void delete(long id);


}
