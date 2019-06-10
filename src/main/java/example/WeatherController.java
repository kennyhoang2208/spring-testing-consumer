package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    private WeatherServiceClient weatherServiceClient;

    private HelloServiceClient helloServiceClient;

    @Autowired
    public WeatherController(WeatherServiceClient weatherServiceClient, HelloServiceClient helloServiceClient) {
        this.helloServiceClient = helloServiceClient;
        this.weatherServiceClient = weatherServiceClient;
    }

    @GetMapping("/hello")
    public String hello() {
        logger.debug("Hello World from Spring Testing Consumer!!");
        return helloServiceClient.getHello();
    }

    @GetMapping("/weather")
    public String fetchWeather() {
        return weatherServiceClient.getWeather();
    }
}
