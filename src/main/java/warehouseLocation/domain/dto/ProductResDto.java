package warehouseLocation.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    private Long productId;
    private Category category;
    private LocalDate expiredDate;
    private String imageUrl;
    private String price;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String status;
    private Location location;
  }

  @Data
  public static class Category {

    private Long categoryId;
    private String categoryName;

  }

  @Data
  public static class ProductInfo {

    private Long productId;
    private String productName;
    private Category category;
    private String imageUrl;
    private String price;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
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
    private String categoryName;
    private LocalDate expiredDate;
    private String status;
    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private boolean isValid;

  }

  @Data
  public static class Edit {

    private Long productId;
    private String productName;
    private Category category;
    private LocalDate expiredDate;
    private String imageUrl;
    private String price;
    private String status;
    private Location location;
    private LocalDateTime updatedAt;

//    @Builder
//    public void edit(Long productId, String productName, String imageUrl, String price,
//        LocalDate expiredDate,
//
//        Category category, String status, String location
//        , LocalDateTime updatedAt, LocalDateTime createdAt) {
//      this.productId = productId;
//      this.productName = productName;
//      this.category = category;
//      this.imageUrl = imageUrl;
//      this.expiredDate = expiredDate;
//      this.price = price;
//      this.status = status;
//      this.location = location;
//      this.updatedAt = updatedAt;
//      this.createdAt = createdAt;
//
//    }
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


