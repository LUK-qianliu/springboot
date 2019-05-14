package com.qianliu.springboot_test.jpa;

import com.qianliu.springboot_test.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

//1.JpaRepository接口提供的简单数据操作接口
//2.JpaSpecificationExecutor提供的复杂查询接口
//3.SpringBoot以及SpringDataJPA会为我们全部搞定，SpringDataJPA内部使用了类代理的方式让继承了它接口的子接口都以spring管理的Bean的形式存在，
// 也就是说我们可以直接使用@Autowired注解在spring管理bean使用，
public interface UserJPA extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity>, Serializable {
}
