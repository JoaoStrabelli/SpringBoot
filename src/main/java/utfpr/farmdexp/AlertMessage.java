package utfpr.farmdexp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AlertMessage {
  private String greenhouseId;
  private Double temperature;
  private Double humidity;
  private Long ts;
  private String alertType;
}