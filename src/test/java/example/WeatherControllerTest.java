package example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExampleConsumerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WeatherControllerTest {

    @Autowired
    private WeatherServiceClient weatherServiceClient;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fetchWeather() {

    }
}