package warehouseLocation.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Schema(description = "Product Request Dto")
@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
public class ProductReqDto {

  @NotBlank(message = "productName is required.")
  @Size(min = 3, max = 20, message = "The username must be from 3 to 20 characters.")
  private String productName;

  @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이어야 합니다.")
  @NotNull(message = "phone is required.")
  private String phone;

  @NotEmpty(message = "The email is required.")
  @Email(message = "The email is not a valid email. You may need to include @ ")
  private String email;


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