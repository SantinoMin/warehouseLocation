package warehouseLocation.domain.dto;

import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import warehouseLocation.models.ProductEntity;


@Getter
@Setter
@Builder
@Data
public class ProductResDto {


  @Data
  public static class ProductSearch {

    String productName;
    List<String> imageUrl;
    Long price;
    Long categoryId;
    String status;
  }

  @Data
  public static class ProductInfo {

    Long productId;
    String productName;
    List<String> imageUrl;
    Long price;
    Long categoryId;
    String status;
    Location location;
  }

  @Data
  @Setter
  @Getter
  public static class Location {

    String area;
    Long rackNumber;
    Long floorHeight;
  }


  @Data
  public static class Message {

    String message;
  }
};

