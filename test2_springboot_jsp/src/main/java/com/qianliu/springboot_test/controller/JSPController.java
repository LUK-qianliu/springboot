package com.qianliu.springboot_test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
@RestController
public class JSPController {
    @RequestMapping(value = "/jsp", method = RequestMethod.GET)
    public ModelAndView getJSP() {
        return new ModelAndView("index");
    }
}
