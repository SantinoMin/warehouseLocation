package warehouseLocation.domain.dto;

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
    int price;
    int categoryId;
    String status;
  }

  @Data
  public static class ProductInfo {

    int productId;
    String productName;
    List<String> imageUrl;
    int price;
    int categoryId;
    String status;
    Location location;
  }

  @Data
  public static class Location {
    String area;
    int rackNumber;
    int floorHeight;
  }


  @Data
  public static class message {

    String message;
  }

    public static ProductResDto.ProductSearch toProductDto(ProductEntity productEntity) {
      ProductResDto.ProductSearch productInfo = new ProductResDto.ProductSearch();
      productInfo.setProductName(productInfo.getProductName());
      productInfo.setImageUrl(productInfo.getImageUrl());
      productInfo.setPrice(productInfo.getPrice());
      productInfo. setCategoryId(productInfo.getCategoryId());
      return productInfo;

  }
};

