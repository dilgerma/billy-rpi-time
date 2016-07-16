package de.effectivetrainings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import(MetricsConfig.class)
public class BillyTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillyTimeApplication.class, args);
	}

	@Bean
	public TimeTrackingResource timeTrackingResource() {
		return new TimeTrackingResource();
	}
}
