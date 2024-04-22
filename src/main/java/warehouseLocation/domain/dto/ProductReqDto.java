package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
public class ProductReqDto {

  @NotBlank(message = "productName is required.")
  @Size(min = 2, max = 10, message = "The productName must be from 2 to 10 characters.")
  private String productName;


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