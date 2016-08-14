package de.effectivetrainings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@Import(MetricsConfig.class)
public class BillyTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillyTimeApplication.class, args);
	}

	@Bean
	public TimeTrackingResource timeTrackingResource() {
		return new TimeTrackingResource();
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
		return propertySourcesPlaceholderConfigurer;
	}
}
