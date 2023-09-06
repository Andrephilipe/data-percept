package com.data.percept;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class PerceptApplication {
	public static Logger logger = LoggerFactory.getLogger(PerceptApplication.class);

	public static void main(String[] args) throws IOException, InterruptedException {

		logger.info("Inicio PerceptApplication");
		SpringApplication.run(PerceptApplication.class, args);
		logger.info("Pronto para uso Inicio PerceptApplication");

	}

}
