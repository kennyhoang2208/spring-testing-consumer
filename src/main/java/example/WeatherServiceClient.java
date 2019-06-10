package example;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class WeatherServiceClient {

    private RestTemplate restTemplate;
    private String endpoint;

    public WeatherServiceClient(RestTemplate restTemplate, String endpoint) {

        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    public String getWeather() {
        try {
            Object result = restTemplate.getForObject(endpoint, Map.class);
            return result == null ? "null" : result.toString();
        } catch (HttpStatusCodeException e) {
            return "Unable to connect";
        }
    }
}
