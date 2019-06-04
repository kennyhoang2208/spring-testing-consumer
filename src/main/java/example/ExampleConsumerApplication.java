package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ExampleConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleConsumerApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World from Spring Testing Consumer!!";
    }
}
