package example;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class WeatherServiceClient {

    private RestTemplate restTemplate;
    private String endpoint;

    public WeatherServiceClient(RestTemplate restTemplate, String endpoint) {

        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
    }

    Map<String, String> greetingFallback() {
        Map<String, String> response = new HashMap<>();
        response.put("weather", "Unable to connect");
        return response;
    }

    public Map<String, String> getWeather() {
        try {
            return restTemplate.getForObject(endpoint, Map.class);
        } catch (HttpStatusCodeException e) {
            return greetingFallback();
        }
    }
}
