package com.qianliu.springboot_test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    @Null
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
}
