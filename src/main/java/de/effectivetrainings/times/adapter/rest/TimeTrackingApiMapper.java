package de.effectivetrainings.times.adapter.rest;

import de.effectivetrainings.times.api.TimeTracking;
import de.effectivetrainings.times.repository.TimeTrackingData;

public class TimeTrackingApiMapper {

    public TimeTracking to(TimeTrackingData timeTrackingData) {
        return TimeTracking.builder().billableHours(timeTrackingData.getBillableHours())
                .date(timeTrackingData.getDate())
                .hours(timeTrackingData.getHours())
                .project(timeTrackingData.getProject())
                .build();
    }

    public TimeTrackingData from(TimeTracking timeTracking) {
        return new TimeTrackingData(timeTracking.getProject(),
                timeTracking.getHours(),timeTracking.getBillableHours());
    }
}
