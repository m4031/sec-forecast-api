package com.marsinfotech.secforecast;

import com.marsinfotech.secforecast.services.core.ServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Map;

@SpringBootApplication
//@EnableWebSecurity
@EnableJpaRepositories("com.marsinfotech.secforecast.persistence.db.repository")
@EntityScan("com.marsinfotech.secforecast.persistence.db.entity")
@ComponentScan(basePackages = {"com.marsinfotech.secforecast","com.marsinfotech.secforecast.services",
		"com.marsinfotech.secforecast.config", "com.marsinfotech.secforecast.persistence.db"})
public class SecForecastServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecForecastServer.class);

	public static void main(String[] args) {
		LOGGER.info("Starting Securities Forecast Application");
		ApplicationContext context = SpringApplication.run(SecForecastServer.class, args);
		Map<String, ServiceI> serviceBeans = context.getBeansOfType(ServiceI.class);
		for (ServiceI service : serviceBeans.values()) {
			service.initialize();
			service.start();
			service.preload();
			service.poststart();
		}
		LOGGER.info("Application is READY");
	}

}
