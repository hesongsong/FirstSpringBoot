package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @JDK Version:1.8.0_40
 * Version: V1.0
 * Author: hs
 * Date: 2018/4/16 14:00
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @RequestMapping(value = "say", method = RequestMethod.GET)
    public String say() {
        return "hi, you !!";
    }
}
