package com.qianliu.springboot_test.dao;

import com.qianliu.springboot_test.model.User3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User3, Long> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return
     */
    User3 findByUsernameEquals(String username);
}
