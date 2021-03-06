package de.effectivetrainings;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TimeTrackingResource {

    public static final String PROJECTS_URI = "projects";

    @RequestMapping(PROJECTS_URI)
    public List<String> projects() throws Exception {
        return Arrays.asList("project1", "project", "project3");
    }
}
