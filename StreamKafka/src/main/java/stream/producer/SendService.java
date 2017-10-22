package stream.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(Source.class)
public class SendService {

    private final Source source;

    @Autowired
    public SendService(Source source) {
        this.source = source;
    }

    public void send(String msg) {
        source.output().send(MessageBuilder.withPayload(msg).build());
    }
}
