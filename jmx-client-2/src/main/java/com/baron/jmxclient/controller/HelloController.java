/*
 * Copyright (c) 2017, China Mobile IOT All Rights Reserved.
 */

package com.baron.jmxclient.controller;

import java.util.Date;

import com.baron.jmxclient.vo.MemStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author xuebai
 * @data 2017年11月13日15:13
 * @modify 2017年11月13日
 * @since v
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String greet() {
        MemStatus memStatus = new MemStatus();
        memStatus.setDate(new Date());
        for (int i = 0; i < 1000000; i++){
            for (int j = 0; j < 100000; j++){

            }
        }
        return "test";
    }

}
