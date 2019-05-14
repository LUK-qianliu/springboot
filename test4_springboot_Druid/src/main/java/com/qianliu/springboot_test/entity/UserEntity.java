package com.qianliu.springboot_test.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity  //标记实体类，交给spring data管理
@Table(name = "user") //映射数据库的user表
@Data  //lombok框架自动生成get和set方法
@NoArgsConstructor //lombok生产无参构造方法
public class UserEntity implements Serializable {

    @Id  //主键用@Id标识
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") //列名和数据库中的列名一致
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;

    public UserEntity(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
