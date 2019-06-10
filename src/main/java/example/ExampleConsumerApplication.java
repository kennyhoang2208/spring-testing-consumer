package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
@ComponentScan(basePackages = "example")
public class ExampleConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleConsumerApplication.class, args);
    }
}
