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

//  @Id
//  private int id;

  @Schema(description = "상품명", example = "바나나콘")
  @NotBlank(message = "productName is required.")
  @Size(min = 3, max = 20, message = "The username must be from 3 to 20 characters.")
  private String productName;

//  @NotNull(message = "enter type of the Male or Female")
//  private Gender gender;

//  @NotBlank(message = "The password is required.")
//  @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$", message = "Password must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.")
//  private String password;
//
//  @NotNull(message = "birthday is required")
//  private LocalDate birthday;
//
//  @NotNull(message = "The address is required.")
//  private String address;

  @Schema(description = "휴대폰 번호", maxLength = 13, example = "010-6365-0381")
  @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이어야 합니다.")
  @NotNull(message = "phone is required.")
  private String phone;

  @Schema(description = "이메일", maxLength = 50, example = "test@gmail.com")
  @NotEmpty(message = "The email is required.")
  @Email(message = "The email is not a valid email. You may need to include @ ")
  private String email;

//  private boolean is_valid = false;

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