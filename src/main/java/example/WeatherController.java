package example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WeatherController {

    private WeatherServiceClient weatherServiceClient;

    public WeatherController(WeatherServiceClient weatherServiceClient) {
        this.weatherServiceClient = weatherServiceClient;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World from Spring Testing Consumer!!";
    }

    @GetMapping("/weather")
    public Map<String, String> fetchWeather() {
        return weatherServiceClient.getWeather();
    }
}
