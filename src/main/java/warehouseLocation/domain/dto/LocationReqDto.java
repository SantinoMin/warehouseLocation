package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class LocationReqDto {

  @NotNull(message = "LocationId is required.")
  private Long locationId;

  @NotBlank(message = "area is required.")
  private String areaName;

  @NotNull(message = "rack is required.")
  private Long rackId;

  @NotNull(message = "floor is required.")
  private Long floorId;

  @NotBlank(message = "createdAt is required.")
  LocalDateTime createdAt;

};