package warehouseLocation.domain.dto;

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
  public static class ProductInfo {

    String productName;
    String imageUrl;
    int price;
    int categoryId;
    String status;
  }

  @Data
  public static class message {

    String message;
  }

    public static ProductResDto.ProductInfo toProductDto(ProductEntity productEntity) {
      ProductResDto.ProductInfo productInfo = new ProductResDto.ProductInfo();
      productInfo.setProductName(productInfo.getProductName());
      productInfo.setImageUrl(productInfo.getImageUrl());
      productInfo.setPrice(productInfo.getPrice());
      productInfo. setCategoryId(productInfo.getCategoryId());
      return productInfo;

  }
};

