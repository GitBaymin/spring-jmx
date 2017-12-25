/*
 * Copyright (c) 2017, China Mobile IOT All Rights Reserved.
 */

package com.baron.adminserver.sum;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
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
@ManagedResource(objectName = "bean:name=sumBean", description = "两个服务参数的和", log = true, logFile = "admin-server.log")
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
        String maxPostSizeString = valueOperations.get("maxPostSize");
        int maxPostSize = Integer.parseInt(StringUtils.isEmpty(maxPostSizeString) ? "0" : maxPostSizeString);

        // 取出jmx-client-2保存的maxPostSize2值
        String maxPostSizeString2 = valueOperations.get("maxPostSize2");
        int maxPostSize2 = Integer.parseInt(StringUtils.isEmpty(maxPostSizeString2) ? "0" : maxPostSizeString2);
        return maxPostSize + maxPostSize2;
    }

    @ManagedAttribute(description = "两个服务maxThreads的和")
    public int getSumMaxThreads() {
        // 取出jmx-client保存的maxThreads值
        String maxThreadsString = valueOperations.get("maxThreads");
        int maxThreads = Integer.parseInt(StringUtils.isEmpty(maxThreadsString) ? "0" : maxThreadsString);

        // 取出jmx-client-2保存的maxThreads2值
        String maxThreadsString2 = valueOperations.get("maxThreads2");
        int maxThreads2 = Integer.parseInt(StringUtils.isEmpty(maxThreadsString2) ? "0" : maxThreadsString2);
        return maxThreads + maxThreads2;
    }
}
