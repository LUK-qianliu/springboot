package com.qianliu.springboot_test.dao;


import com.qianliu.springboot_test.entity.User2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User2, Long> {

    User2 findById(long id);

    void deleteById(Long id);
}