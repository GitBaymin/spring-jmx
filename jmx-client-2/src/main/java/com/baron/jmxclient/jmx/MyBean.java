/*
 * Copyright (c) 2017, China Mobile IOT All Rights Reserved.
 */

package com.baron.jmxclient.jmx;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.IntrospectionUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import rx.annotations.Beta;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * @author xuebai
 * @data 2017年11月22日17:31
 * @modify 2017年11月22日
 * @since v
 */
@ManagedResource(objectName = "bean:name=MyBean", description = "My Managed Bean", log = true, logFile = "jmx-client-2.log",
    currencyTimeLimit = 15, persistPolicy = "OnUpdate", persistPeriod = 200, persistLocation = "foo",
    persistName = "bar")
public class MyBean {
    private ApplicationContext applicationContext;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> valueOperations;

    public MyBean(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    private void init(){
        this.valueOperations = stringRedisTemplate.opsForValue();
    }

    private Connector getConnector(){
        EmbeddedServletContainer embeddedServletContainer =
            ((EmbeddedWebApplicationContext) applicationContext).getEmbeddedServletContainer();
        Tomcat tomcat = ((TomcatEmbeddedServletContainer) embeddedServletContainer).getTomcat();
        return tomcat.getConnector();
    }

    @ManagedAttribute(description = "获得最小备用线程数，tomcat启动时的初始化的线程数")
    public int getMaxPostSize() {
        int maxPostSize = (int) IntrospectionUtils.getProperty(getConnector(), "maxPostSize");
        valueOperations.set("maxPostSize2", String.valueOf(maxPostSize));
        return maxPostSize;
    }

    @ManagedAttribute(description = "设置最小备用线程数，tomcat启动时的初始化的线程数")
    public void setMaxPostSize(int value){
        valueOperations.set("maxPostSize2", String.valueOf(value));
        IntrospectionUtils.setProperty(getConnector(), "maxPostSize", String.valueOf(value));
    }

    @ManagedAttribute(description = "获得tomcat起动的最大线程数，即同时处理的任务个数")
    public int getMaxThreads(){
        int maxThreads = (int) IntrospectionUtils.getProperty(getConnector(), "maxThreads");
        valueOperations.set("maxThreads2", String.valueOf(maxThreads));
        return maxThreads;
    }

    @ManagedAttribute(description = "设置tomcat起动的最大线程数，即同时处理的任务个数")
    public void setMaxThreads(int value){
        valueOperations.set("maxThreads2", String.valueOf(value));
        IntrospectionUtils.setProperty(getConnector(), "maxThreads", String.valueOf(value));
    }

}
