/*
 * Copyright (c) 2017, China Mobile IOT All Rights Reserved.
 */

package com.baron.adminserver.config;

import com.baron.adminserver.sum.SumBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuebai
 * @data 2017年12月08日16:46
 * @modify 2017年12月08日
 * @since v
 */
@Configuration
public class BeanConfig {

    @Bean
    public SumBean sumBean(){
        return new SumBean();
    }
}
