package stream.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;

@EnableBinding(Source.class)
public class GreetingSource {

    @InboundChannelAdapter(value = Source.OUTPUT)
    public String greet() {
        return "hello world " + System.currentTimeMillis();
    }
}
