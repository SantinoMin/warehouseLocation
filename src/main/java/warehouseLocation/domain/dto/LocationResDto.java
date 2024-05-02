package warehouseLocation.domain.dto;


import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class LocationResDto {

  @NotBlank(message = "locationId is required.")
  private Long locationId;

  @Data
  public static class Message {

    String message;
  }

  @Data
  public static class Area {

    Long areaId;
  }

};