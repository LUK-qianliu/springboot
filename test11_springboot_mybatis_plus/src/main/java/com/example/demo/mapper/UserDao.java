package com.example.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper //表示这是一个mybatis的mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 自定义sql：查询用户
     *
     * @param userId 用户ID
     */
    @Select("select * from user where user_id = #{userId}")
    List<User> queryAllPerms(@Param("userId") int userId);

}
