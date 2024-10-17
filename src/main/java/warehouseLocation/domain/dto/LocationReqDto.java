package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class LocationReqDto {

  @NotBlank(message = "LocationId is required.")
  private Long locationId;

  @NotBlank(message = "area is required.")
  private String areaName;

  @NotBlank(message = "rack is required.")
  private Long rackId;

  @NotBlank(message = "floor is required.")
  private Long floorId;

  @NotBlank(message = "createdAt is required.")
  LocalDateTime createdAt;

};