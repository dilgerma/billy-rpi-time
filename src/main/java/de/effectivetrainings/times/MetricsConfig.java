package de.effectivetrainings.times;

import com.codahale.metrics.JvmAttributeGaugeSet;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import de.effectivetrainings.metrics.InfoProvider;
import de.effectivetrainings.metrics.ServiceInfo;
import de.effectivetrainings.metrics.influx.InfluxConfiguration;
import de.effectivetrainings.metrics.influx.InfluxReporter;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Configuration
@Import(MetricsConfig.InfluxConfig.class)
@Profile("metrics")
public class MetricsConfig {

    @Autowired
    private InfluxDB influxDB;
    @Autowired
    private InfluxConfiguration influxConfig;

    @Bean
    public MetricRegistry metricRegistry() {
        MetricRegistry metricRegistry = new MetricRegistry();
        metricRegistry.registerAll(new JvmAttributeGaugeSet());
        metricRegistry.registerAll(new MemoryUsageGaugeSet());
        metricRegistry.registerAll(new GarbageCollectorMetricSet());
        return metricRegistry;
    }

    @Bean
    public InfluxReporter influxReporter() {
        MetricsProperties properties = metricsProperties();

        return new InfluxReporter(metricRegistry(),
                properties.getServiceName(),
                MetricFilter.ALL,
                TimeUnit.MILLISECONDS,
                TimeUnit.MILLISECONDS,
                influxDB,
                influxConfig.getDatabase(),
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


    @PostConstruct
    public void schedule() {
        MetricsProperties properties = metricsProperties();
        final InfluxReporter reporter = influxReporter();
        reporter.start(properties.getScheduleSeconds(), TimeUnit.SECONDS);
    }

    @Configuration
    @Profile("metrics")
    public static class InfluxConfig {
        @Bean
        public InfluxDB influxDb() {
            InfluxConfiguration influxConfig = influxConfig();
            return InfluxDBFactory.
                    connect(influxConfig.getUrl(),
                            influxConfig.getUsername(),
                            influxConfig.getPassword());
        }

        @Bean
        @ConfigurationProperties(prefix = "influxdb", ignoreUnknownFields = false)
        public InfluxConfiguration influxConfig() {

            final InfluxConfiguration influxConfiguration = new InfluxConfiguration();
                return influxConfiguration;
        }
    }
}
