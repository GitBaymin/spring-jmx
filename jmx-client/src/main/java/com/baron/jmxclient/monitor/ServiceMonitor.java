/*
 * Copyright (c) 2017, China Mobile IOT All Rights Reserved.
 */

package com.baron.jmxclient.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

/**
 * @author xuebai
 * @data 2017年11月14日15:54
 * @modify 2017年11月14日
 * @since v
 */
@Aspect
@Component
public class ServiceMonitor {

    @Autowired
    private CounterService counterService;
    @Autowired
    private GaugeService gaugeService;

    /**
     * 统计接口访问次数
     *
     * @param joinPoint
     * @date 20171115 09:40:32
     * @since v
     */
    @Before("execution(* com.baron.jmxclient.controller.*.*(..))")
    public void countServiceInvoke(JoinPoint joinPoint) {
        counterService.increment(joinPoint.getSignature() + "");
    }

    /**
     * 统计上次调用接口时访问时间
     *
     * @param pjp
     * @return 返回 object 描述此返回参数
     * @throws Throwable 描述抛出异常原因
     * @date 20171115 09:41:05
     * @since v
     */
    @Around("execution(* com.baron.jmxclient.controller.*.*(..))")
    public Object latencyService(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        gaugeService.submit(pjp.getSignature().toString(), end - start);
        return result;
    }
}
