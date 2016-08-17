package de.effectivetrainings.times;

import de.effectivetrainings.times.adapter.rest.TimeTrackingResource;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BillyTimeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit-test-env")
@Category(de.effectivetrainings.test.support.IntegrationTest.class)
public class BillyTimeApplicationTests {

    @Value("${local.server.port}")
    private String serverPort;
    @Value("${server.context-path}")
    private String contextPath;

    @Test
    public void startApplicationAndVerify() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        final ResponseEntity<List> result = restTemplate.getForEntity(serviceUri(), List.class);
        assertFalse(result
                .getBody()
                .isEmpty());
    }

    private URI serviceUri() {
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(serverPort)
                .path(contextPath)
                .path(TimeTrackingResource.TIMES_URI)
                .build()
                .toUri();
    }
}
