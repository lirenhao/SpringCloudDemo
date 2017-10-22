package stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stream.producer.SendService;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final SendService sendService;

    @Autowired
    public Application(SendService sendService) {
        this.sendService = sendService;
    }

    @RequestMapping("/send/{msg}")
    public String send(@PathVariable("msg") String msg) {
        sendService.send(msg);
        return msg;
    }
}