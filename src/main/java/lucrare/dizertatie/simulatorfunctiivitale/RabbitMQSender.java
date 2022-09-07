package lucrare.dizertatie.simulatorfunctiivitale;

import lombok.extern.log4j.Log4j2;
import lucrare.dizertatie.common.exception.MessagingException;
import lucrare.dizertatie.simulatorfunctiivitale.util.Notificare;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${spital.rabbitmq.exchange}")
    private String exchange;

    @Value("${spital.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Notificare notificare) throws MessagingException {
        rabbitTemplate.convertAndSend(exchange, routingkey, notificare);
        log.info("Notificare spital: " + notificare);
    }
}
