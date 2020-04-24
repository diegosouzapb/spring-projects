package com.guedim.activemqsender;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.guedim.activemqsender.model.Message;
import com.guedim.activemqsender.sender.Sender;

@Component
public class MyRunner implements CommandLineRunner {
	
	private static final String FILE_NAME = "src/main/resources/data.csv";

	private static final Logger LOGGER = LoggerFactory.getLogger(MyRunner.class);
	
	@Autowired
	private Sender sender;
	
	@Autowired
    private ConfigurableApplicationContext context;

	@Override
	public void run(String... args) throws Exception {

		String line = null;
		Path path = Paths.get(FILE_NAME);

		BufferedReader reader = Files.newBufferedReader(path);
		while ((line = reader.readLine()) != null) {
			LOGGER.info("reading line: {}", line);
			try {
				String[] split = line.split(",");
				Message message = new Message(Integer.valueOf(split[0]), split[1]);
				sender.send(message);
			} catch (Exception e) {
				LOGGER.error("error processing line {}", line, e);
			}
		}
		SpringApplication.exit(context);
	}
}