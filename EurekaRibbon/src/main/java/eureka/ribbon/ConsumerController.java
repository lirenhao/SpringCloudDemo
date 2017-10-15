package eureka.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/")
    public String home() {
        return restTemplate.getForEntity("http://EUREKA-COMPUTE/", String.class).getBody();
    }

    @GetMapping(value = "/add")
    public String add() {
        return restTemplate.getForEntity("http://EUREKA-COMPUTE/add?a=10&b=20", String.class).getBody();
    }
}