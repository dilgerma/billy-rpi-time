package de.effectivetrainings.times.api;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class TimeTracking {

    private String project;
    private Date date;
    private double hours;
    private double billableHours;
}
