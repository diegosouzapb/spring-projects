package com.guedim.activemqsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ActiveMqSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMqSenderApplication.class, args);
	}

}
