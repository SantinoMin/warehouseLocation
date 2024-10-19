package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorReqDto {

    @NotNull(message = "floorNumber is required.")
    private Long floorNumber;


};