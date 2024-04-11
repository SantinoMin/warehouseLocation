package warehouseLocation.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "Product Response Dto")
@Getter
@Setter
@Builder
@Data
public class ProductResDto {

  public String message;

  @Getter
  @Setter
  public static class joinUser {

    @Schema(description = "Product Response Dto")
    private String ProductName;

    private LocalDate birthday;
    private String address;
//    private Gender gender;
    private String phone;
    private String email;
    private String message;
  }

  @Setter
  @Getter
  public static class message {

    private String message;

  }

//  public static ProductResDto.message toMessage(UserEntity userEntity) {
//    ProductResDto.message userDto = new ProductResDto.message();
//    userDto.setMessage(userEntity.getMessage());
//    return userDto;
//  }


//  public static ProductResDto.joinUser toJoinUserDto(UserEntity userEntity) {
//    ProductResDto.joinUser userDto = new ProductResDto.joinUser();
//    userDto.setUserName(userEntity.getUsername());
//    userDto.setBirthday(userEntity.getBirthday());
//    userDto.setAddress(userEntity.getAddress());
//    userDto.setPhone(userEntity.getPhone());
////      userDto.set_valid(userEntity.is_valid());
//    userDto.setEmail(userEntity.getEmail());
//    return userDto;
  };