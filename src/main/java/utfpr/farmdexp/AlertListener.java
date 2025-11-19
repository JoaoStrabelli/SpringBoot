package utfpr.farmdexp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AlertListener {
  private final ObjectMapper mapper = new ObjectMapper();

  @RabbitListener(queues = RabbitConfig.QUEUE)
  public void handle(String json) throws Exception {
    AlertMessage msg = mapper.readValue(json, AlertMessage.class);
    log.info("ALERTA recebido: {}", msg);
    // TODO: persista no Postgres, chame e-mail/SMS, etc.
  }
}