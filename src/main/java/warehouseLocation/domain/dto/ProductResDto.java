package warehouseLocation.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
public class ProductResDto {


  @Data
  public static class ProductSearch {

    private String productName;
    private String imageUrl;
    private String price;
    private String categoryName;
    private String status;
  }

  @Data
  public static class ProductInfo {

//    private Long productId;
    private String productName;
    private String imageUrl;
    private String price;
//    private Long categoryId;
    private String categoryName;
    private String status;

    private Location location;

  }

  @Data
  @Setter
  public static class Location {

    private String area;
    private String rack;
    private String floor;
  }

  @Data
  public static class Register {

//    private Long productId;
    private String productName;
    private String price;
    private String category;
    private String status;
    private String imageUrl;

//    private LocalDate expiredDate;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private boolean isValid;

  }

  @Data
  public static class Edit {

    private Long productId;
    private String productName;
    private String imageUrl;
    private LocalDate expiredDate;
    private String price;
    private Long categoryId;
    private String status;
    private String location;
    //    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Edit(Long productId, String productName, String imageUrl, String price,
        LocalDate expiredDate,

        Long categoryId, String status, String location
        , LocalDateTime updatedAt) {
      this.productId = productId;
      this.productName = productName;
      this.imageUrl = imageUrl;
      this.expiredDate = expiredDate;
      this.price = price;
      this.categoryId = categoryId;
      this.status = status;
      this.location = location;
//      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
    }
  }

  @Data
  public static class Delete {

    private Long productId;
    private String productName;

  }

  @Data
  public static class CategoryList {

    private List<String> categoryList;

  }


  @Data
  public static class Message {

    String message;
  }

  @Data
  public static class Area {

    Long areaId;
  }

  @Data
  public static class Rack {

    Long rackId;
  }

  @Data
  public static class Floor {

    Long floorId;
  }

};


