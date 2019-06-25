package example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class AppConfig {
    @Bean
    RestTemplate restTemplate() {

        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
         Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("www.google.com", 80));
         clientHttpRequestFactory.setProxy(proxy);

        return new RestTemplate(clientHttpRequestFactory);
    }

    @Bean
    @LoadBalanced
    WeatherServiceClient weatherServiceClient(
            RestTemplate restTemplate,
            @Value("${backend.weather-service.url}") String endpoint) {
        return new WeatherServiceClient(restTemplate, endpoint);
    }

    @Bean
    @LoadBalanced
    HelloServiceClient helloServiceClient(
            RestTemplate restTemplate,
            @Value("${backend.hello-service.url}") String endpoint) {
        return new HelloServiceClient(restTemplate, endpoint);
    }

}
