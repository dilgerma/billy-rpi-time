package de.effectivetrainings.times.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTimeBookingRepository extends JpaRepository<TimeTrackingData, Long> {

    List<TimeTrackingData> findByProject(String project);
}
