package de.effectivetrainings.times;

import de.effectivetrainings.times.adapter.rest.TimeTrackingResource;
import de.effectivetrainings.times.repository.ProjectTimeBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestAdapterConfig {

    @Autowired
    private ProjectTimeBookingRepository projectTimeBookingRepository;

    @Bean
    public TimeTrackingResource timeTrackingResource() {
        return new TimeTrackingResource(projectTimeBookingRepository);
    }
}
