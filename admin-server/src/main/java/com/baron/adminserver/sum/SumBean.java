/*
 * Copyright (c) 2017, China Mobile IOT All Rights Reserved.
 */

package com.baron.adminserver.sum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * @author xuebai
 * @data 2017年12月08日16:45
 * @modify 2017年12月08日
 * @since v
 */
@ManagedResource(objectName = "bean:name=sumBean",description = "两个服务参数的和")
public class SumBean {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> valueOperations;

    @PostConstruct
    private void init() {
        this.valueOperations = stringRedisTemplate.opsForValue();
    }

    @ManagedAttribute(description = "两个服务maxPostSize的和")
    public int getSumMaxPostSize() {
        // 取出jmx-client保存的maxPostSize值
        int maxPostSize = Integer.parseInt(valueOperations.get("maxPostSize"));
        // 取出jmx-client-2保存的maxPostSize2值
        int maxPostSize2 = Integer.parseInt(valueOperations.get("maxPostSize2"));
        return maxPostSize + maxPostSize2;
    }
    @ManagedAttribute(description = "两个服务maxThreads的和")
    public int getSumMaxThreads(){
        // 取出jmx-client保存的maxThreads值
        int maxThreads = Integer.parseInt(valueOperations.get("maxThreads"));
        // 取出jmx-client-2保存的maxThreads2值
        int maxThreads2 = Integer.parseInt(valueOperations.get("maxThreads2"));
        return maxThreads + maxThreads2;
    }
}
