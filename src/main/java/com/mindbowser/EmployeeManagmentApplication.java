package com.mindbowser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagmentApplication {
	private static Logger logger = LoggerFactory.getLogger(EmployeeManagmentApplication.class);

	public static void main(String[] args) {
		logger.info("Employee Managment Application start");
		SpringApplication.run(EmployeeManagmentApplication.class, args);
	}

	@Configuration
	public class CorsConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("*").maxAge(3600)
					.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD").allowCredentials(true);
		}

	}
}
