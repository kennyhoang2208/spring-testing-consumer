package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class ExampleConsumerApplication {

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    public static void main(String[] args) {
        SpringApplication.run(ExampleConsumerApplication.class, args);
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
