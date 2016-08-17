package de.effectivetrainings.times.repository;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
//JPA
@Table(name = "TIME_BOOKING")
@Entity
public class TimeTrackingData {

    @Id
    private Long id;

    @NonNull
    @Column(name = "project_name")
    private String project;
    @Column(name = "booking_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @NonNull
    @Column(name = "hours")
    private double hours;
    @NonNull
    @Column(name = "billable_hours")
    private double billableHours;


    @PrePersist
    public void setTime() {
        date = new Date();
    }

}
