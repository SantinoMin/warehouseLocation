package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//@Getter
//@Setter
@Data
@ToString
@AllArgsConstructor
public class ProductReqDto {

  @NotBlank(message = "productName is required.")
  @Size(min = 2, max = 10, message = "The productName must be from 2 to 10 characters.")
  private String productName;

//  @NotBlank(message = "expiredDate is required.")
  private LocalDate expiredDate;

  @NotBlank(message = "productName is required.")
  private String imageUrl;

  @NotBlank(message = "price is required.")
  private String price;

//  @NotBlank(message = "createdAt is required.")
  LocalDateTime createdAt;

//  @NotBlank(message = "updatedAt is required.")
  LocalDateTime updatedAt;

//  @NotBlank(message = "isValid is required.")
  boolean isValid;


  @Data
//  @Builder
  @NoArgsConstructor
  public static class Edit {
    @NotBlank(message = "productName is required.")
    @Size(min = 2, max = 10, message = "The productName must be from 2 to 10 characters.")
    private String productName;

    @NotBlank(message = "expiredDate is required.")
    private LocalDate expiredDate;

    @NotBlank(message = "productName is required.")
    private String imageUrl;

    @NotBlank(message = "price is required.")
    private String price;

    @NotBlank(message = "sort is required.")
    private String sort;

    @NotBlank(message = "location is required.")
    private String location;

    @NotBlank(message = "createdAt is required.")
    LocalDateTime createdAt;

    @NotBlank(message = "updatedAt is required.")
    LocalDateTime updatedAt;


  }



  /**
   * entity -> dto로 변환하기
   */
//  public static ProductReqDto toUserReqDto(UserEntity userEntity) {
//    ProductReqDto productReqDto = new ProductReqDto();
//    productReqDto.setId(userEntity.getId());
//    productReqDto.setUserName(userEntity.getUsername());
//    productReqDto.setPhone(userEntity.getPhone());
//    return productReqDto;
  };