/*
 * Copyright (c) 2017, China Mobile IOT All Rights Reserved.
 */

package com.baron.jmxclient.config;

import com.baron.jmxclient.jmx.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author xuebai
 * @data 2017年11月23日9:32
 * @modify 2017年11月23日
 * @since v
 */
@Configuration
public class BeanConfig {

    @Bean
    public MyBean myBean(ApplicationContext applicationContext){
        return new MyBean(applicationContext);
    }

}
