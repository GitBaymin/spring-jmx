package com.baron.jmxclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 描述该类实现的功能
 *
 * @author xuebai
 * @date 20171205 17:03:28
 * @modify 20171205 xuebai
 * @since v
 */
@SpringBootApplication
@EnableEurekaClient
public class JmxClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmxClientApplication.class, args);
	}
}
