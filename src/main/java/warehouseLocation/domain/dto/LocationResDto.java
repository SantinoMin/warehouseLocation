package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class LocationResDto {

  @NotBlank(message = "locationId is required.")
  private Long locationId;

  @Setter
  @Data
  public static class Message {

    String message;
  }

  };