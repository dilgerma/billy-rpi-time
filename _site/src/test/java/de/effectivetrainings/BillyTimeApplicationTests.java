package de.effectivetrainings;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertFalse;

@WebIntegrationTest(randomPort = true)
@SpringApplicationConfiguration(classes = BillyTimeApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("local")
@Category(de.effectivetrainings.test.support.IntegrationTest.class)
public class BillyTimeApplicationTests {

    @Autowired
    private TimeTrackingResource timeTrackingResource;
    @Value("${local.server.port}")
    private String serverPort;

    @Test
    public void startApplicationAndVerify() {
        RestTemplate restTemplate = new TestRestTemplate();
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
                .path(TimeTrackingResource.PROJECTS_URI)
                .build()
                .toUri();
    }
}
