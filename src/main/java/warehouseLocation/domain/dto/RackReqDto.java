package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RackReqDto {

    @NotNull(message = "rackNumber is required.")
    private Long rackNumber;


};