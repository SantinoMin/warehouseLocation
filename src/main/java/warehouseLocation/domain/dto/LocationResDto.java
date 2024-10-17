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

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
public class LocationResDto {

//  @NotBlank(message = "locationId is required.")
//  private Long locationId;

//  @NotBlank(message = "LocationId is required.")
//  private Long locationId;

  @NotBlank(message = "area is required.")
  private String areaName;

  @NotBlank(message = "rack is required.")
  private Long rackNumber;

  @NotBlank(message = "floor is required.")
  private Long floorNumber;

  public LocationResDto() {

  }

//  @NotBlank(message = "createdAt is required.")
//  LocalDateTime createdAt;

  @Data
  public static class Message {

    String message;

    public Message(String message) {
      this.message = message;
    }
  }

  @Data
  public static class Area {

    Long areaId;
  }

};