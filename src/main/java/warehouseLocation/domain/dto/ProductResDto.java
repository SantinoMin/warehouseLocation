package warehouseLocation.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "Product Response Dto")
@Getter
@Setter
@Builder
@Data
public class ProductResDto {

  @Getter
  @Setter
  public static class Search {

    private String ProductName;
    private String address;
    private String message;
  }


  };