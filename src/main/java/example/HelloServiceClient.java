package example;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class HelloServiceClient {

    private RestTemplate restTemplate;
    private String endpoint;

    public HelloServiceClient(RestTemplate restTemplate, String endpoint) {
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    public String getHello() {
        try {
            return restTemplate.getForObject(endpoint, String.class);
        } catch (HttpStatusCodeException e) {
            return "Unable to connect";
        }
    }
}
