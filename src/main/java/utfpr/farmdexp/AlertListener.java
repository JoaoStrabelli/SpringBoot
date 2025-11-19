package utfpr.farmdexp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AlertListener {

    @RabbitListener(queues = "${app.rabbitmq.alerts-queue:alerts.queue}")
    public void consume(String payload) {
        log.info("Mensagem recebida (JSON): {}", payload);
        // Se quiser, parseie depois com Jackson:
        // ObjectMapper om = new ObjectMapper();
        // JsonNode node = om.readTree(payload);
    }
}