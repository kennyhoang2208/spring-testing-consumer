package example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class AppConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    WeatherServiceClient weatherServiceClient(
            RestTemplate restTemplate,
            @Value("${backend.helloworld-service.url}") String endpoint) {
        return new WeatherServiceClient(restTemplate, endpoint);
    }

}
