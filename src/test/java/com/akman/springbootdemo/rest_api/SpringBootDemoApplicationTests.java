package com.akman.springbootdemo.rest_api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PrepareForTest(SpringBootDemoApplication.class)
public class SpringBootDemoApplicationTests {
    @LocalServerPort
    int randomServerPort;

/*
    @Test
    public void testListFootballClubsSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        ParameterizedTypeReference<List<FootballClubResponse>> responseType =
                new ParameterizedTypeReference<List<FootballClubResponse>>() {
                };

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1/footballClub/listFootballClubs";
        URI uri = new URI(baseUrl);
        RequestEntity<Void> request = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity result = restTemplate.exchange(request, responseType);

        //Verify request succeed
        Assert.assertEquals(202, result.getStatusCodeValue());
    }

    @Test
    public void testListFootballersSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        ParameterizedTypeReference<List<FootballerResponse>> responseType =
                new ParameterizedTypeReference<List<FootballerResponse>>() {
                };

        final String baseUrl = "http://localhost:" + randomServerPort + "/api/v1/footballer/listFootballers";
        URI uri = new URI(baseUrl);
        RequestEntity<Void> request = RequestEntity.get(uri)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity result = restTemplate.exchange(request, responseType);

        //Verify request succeed
        Assert.assertEquals(202, result.getStatusCodeValue());
    }
*/

    @Test
    public void testPackageMarker() {
        PackageMarker packageMarker = new PackageMarker();
        com.akman.springbootdemo.PackageMarker packageMarker1 = new com.akman.springbootdemo.PackageMarker();
    }

    @InjectMocks
    private SpringBootDemoApplication springBootDemoApplication;

    private SpringApplicationBuilder application = new SpringApplicationBuilder();

    @Test
    public void testApplication() {
        SpringBootDemoApplication.main(new String[0]);
        springBootDemoApplication.configure(application);
    }
}