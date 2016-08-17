package de.effectivetrainings.times.adapter.rest;

import de.effectivetrainings.times.api.TimeTracking;
import de.effectivetrainings.times.repository.ProjectTimeBookingRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class TimeTrackingResource {

    public static final String TIMES_URI = "/times";
    public static final String PROJECT_TIMES_URI = "/{project}/times";

    private ProjectTimeBookingRepository projectTimeBookingRepository;
    private TimeTrackingApiMapper timeTrackingApiMapper = new TimeTrackingApiMapper();

    public TimeTrackingResource(ProjectTimeBookingRepository timeBookingRepository) {
        this.projectTimeBookingRepository = Objects.requireNonNull(timeBookingRepository);
    }

    @RequestMapping(TIMES_URI)
    public List<TimeTracking> trackings() throws Exception {
        return projectTimeBookingRepository.findAll().stream().map(timeTrackingApiMapper::to).collect(Collectors.toList());
    }

    @RequestMapping(PROJECT_TIMES_URI)
    public List<TimeTracking> projectTimeTrackings(@PathVariable("project") String project) throws Exception {
        return projectTimeBookingRepository.findByProject(project).stream().map(timeTrackingApiMapper::to).collect(Collectors.toList());
    }
}
