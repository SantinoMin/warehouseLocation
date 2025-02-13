package warehouseLocation.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import warehouseLocation.models.Location;


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
    private String categoryName;
    private LocalDate expiredDate;
    private String imageUrl;
    private String price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private LocationResDto location;

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
    private Long category;
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

    private String areaName;
    private Long rackNumber;
    private Long floorNumber;
  }

  @Data
  public static class Register {

    private Long productId;
    private String productName;
//    private String price;
//    private Category category;
//    private LocalDate expiredDate;
    private String status;
//    private String imageUrl;
//    private Location location;
    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private boolean isValid;

  }


  @Data
  public static class Edit {

    private Long productId;
    private String productName;
    private String categoryName;
    private LocalDate expiredDate;
    private String imageUrl;
    private String price;
    private String status;
//    private Location location;
    private String areaName;
    private Long rackNumber;
    private Long floorNumber;

    private LocalDateTime updatedAt;

    @Builder
    public void edit(Long productId, String productName, String imageUrl, String price,
        LocalDate expiredDate,

        Category category, String status, String location
        , LocalDateTime updatedAt, LocalDateTime createdAt) {
      this.productId = productId;
      this.productName = productName;
//      this.category = category;
      this.imageUrl = imageUrl;
      this.expiredDate = expiredDate;
      this.price = price;
      this.status = status;
//      this.location = location;
      this.updatedAt = updatedAt;
//      this.createdAt = createdAt;

    }
  }

  @Data
  public static class Delete {

    private Long productId;
    private String productName;

  }

  @Data
  @Builder
  public static class CategoryList {

    private List<String> categoryNameList;

  }


  @Data
  public static class Message {

    Long productId;
    String productName;
    String status;
  }

  @Data
  public static class Area {

    Long id;
    String name;
    boolean isValid;

    public Area(Long id, String name, boolean isValid) {
      this.id = id;
      this.name = name;
      this.isValid = isValid;
    }
  }

//  @Data
//  public static class AreaResponse {
//    List<Area> area;
//  }

  @Data
  public static class Rack {

    Long rackId;
    Long rackNumber;
    Boolean isValid;

    public Rack(Long rackId, Long rackNumber, Boolean isValid) {
      this.rackId = rackId;
      this.rackNumber = rackNumber;
      this.isValid = isValid;
    }
  }

  @Data
  public static class Floor {

    Long floorId;
    Long floorNumber;
    Boolean isValid;

    public Floor(Long floorId, Long floorNumber, Boolean isValid) {
      this.floorId = floorId;
      this.floorNumber = floorNumber;
      this.isValid = isValid;

    }
  }

};


