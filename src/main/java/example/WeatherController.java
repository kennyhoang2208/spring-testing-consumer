package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private WeatherServiceClient weatherServiceClient;

    @Autowired
    public WeatherController(WeatherServiceClient weatherServiceClient) {
        logger.debug("Testing");
        this.weatherServiceClient = weatherServiceClient;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World from Spring Testing Consumer!!";
    }

    @GetMapping("/weather")
    public Map<String, String> fetchWeather() {
        return weatherServiceClient.getWeather();
    }
}
