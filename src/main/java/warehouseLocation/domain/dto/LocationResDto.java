package warehouseLocation.domain.dto;


import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationResDto {

    @NotBlank(message = "area is required.")
    private String areaName;

    @NotBlank(message = "rack is required.")
    private Long rackNumber;

    @NotBlank(message = "floor is required.")
    private Long floorNumber;

};