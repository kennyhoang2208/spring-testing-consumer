package example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    WeatherServiceClient weatherServiceClient(
            RestTemplate restTemplate,
            @Value("${backend.weather-service.url}") String endpoint) {
        return new WeatherServiceClient(restTemplate, endpoint);
    }

}
