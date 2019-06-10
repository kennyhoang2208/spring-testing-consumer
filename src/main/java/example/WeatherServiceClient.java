package example;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class WeatherServiceClient {

    private RestTemplate restTemplate;
    private String endpoint;

    public WeatherServiceClient(RestTemplate restTemplate, String endpoint) {

        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    public String getWeather() {
        try {
            return restTemplate.getForObject(endpoint, String.class);
        } catch (HttpStatusCodeException e) {
            return "Unable to connect";
        }
    }
}
