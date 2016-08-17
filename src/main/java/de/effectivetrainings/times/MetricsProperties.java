package de.effectivetrainings.times;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetricsProperties {

    private String serviceName;
    private String host;
    private String environment;
    private int scheduleSeconds;
}
