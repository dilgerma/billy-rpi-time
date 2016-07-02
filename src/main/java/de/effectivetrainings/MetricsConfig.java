package de.effectivetrainings;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import de.effectivetrainings.metrics.InfoProvider;
import de.effectivetrainings.metrics.ServiceInfo;
import de.effectivetrainings.metrics.influx.InfluxConfiguration;
import de.effectivetrainings.metrics.influx.InfluxReporter;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Configuration
public class MetricsConfig {

    @Autowired
    private MetricRegistry metricRegistry;

    @Bean
    public InfluxReporter influxReporter() {
        InfluxDB influxDB = influxDb();
        InfluxConfiguration influxConfiguration = influxConfig();
        MetricsProperties properties = metricsProperties();

        return new InfluxReporter(metricRegistry,
                properties.getServiceName(),
                MetricFilter.ALL,
                TimeUnit.MILLISECONDS,
                TimeUnit.MILLISECONDS,
                influxDB,
                influxConfiguration.getDatabase(),
                Optional.of(serviceInfoProvider()));
    }

    @Bean
    public InfoProvider serviceInfoProvider() {
        MetricsProperties properties = metricsProperties();
        return () -> ServiceInfo
                .builder()
                .serviceName(properties.getServiceName())
                .host(properties.getHost())
                .environment(properties.getEnvironment())
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "metrics")
    public MetricsProperties metricsProperties() {
        return new MetricsProperties();
    }

    @Bean
    public InfluxDB influxDb() {
        InfluxConfiguration influxConfiguration = influxConfig();
        return InfluxDBFactory.
                connect(influxConfiguration.getUrl(),
                        influxConfiguration.getUsername(),
                        influxConfiguration.getPassword());
    }

    @Bean
    @ConfigurationProperties(prefix = "influxdb")
    public InfluxConfiguration influxConfig() {
        return new InfluxConfiguration();
    }

    @PostConstruct
    public void schedule() {
        MetricsProperties properties = metricsProperties();
        final InfluxReporter reporter = influxReporter();
        reporter.start(properties.getScheduleSeconds(), TimeUnit.SECONDS);
    }
}
