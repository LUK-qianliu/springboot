package com.example.demo.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("User") //和数据库表名一一对应
public class User implements Serializable {
    @TableId //主键
    int user_id;
    String username;
    String password;
}
