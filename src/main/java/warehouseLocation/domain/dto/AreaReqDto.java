package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaReqDto {

    @NotBlank(message = "areaName is required.")
    private String areaName;


};