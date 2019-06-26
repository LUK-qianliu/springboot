package com.qianliu.springboot_test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * 因为为了保持restful风格，所以返回的信息必须是规定好的格式，ResponseBean就是自定义的返回格式
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean {
    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;
}
