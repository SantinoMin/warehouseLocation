package warehouseLocation.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@Data
public class ProductResDto {


    public static class ProductInfo {
      String productName;
      String imageUrl;
      Long price;
      String category;
      String status;

    }
};
