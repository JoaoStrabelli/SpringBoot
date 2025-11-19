package utfpr.farmdexp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  public static final String EXCHANGE = "alerts.exchange";
  public static final String QUEUE    = "alerts.queue";

  @Bean TopicExchange alertsExchange() { return new TopicExchange(EXCHANGE, true, false); }
  @Bean Queue alertsQueue()            { return new Queue(QUEUE, true); }
  @Bean Binding alertsBinding(Queue q, TopicExchange ex) {
    return BindingBuilder.bind(q).to(ex).with("alerts.*");
  }
}