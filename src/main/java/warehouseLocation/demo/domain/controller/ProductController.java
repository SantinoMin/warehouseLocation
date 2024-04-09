//package warehouseLocation.demo.domain.controller;
//
//import jakarta.validation.Valid;
//import java.time.LocalDateTime;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j // 응답을 기록하는 데 도움이 되며 주로 디버깅 목적
////@ResponseBody // 요거 왜 쓰고있지? // return되는 확인용으로 html이 아닌 문자열을 받기 위해.
//@RequestMapping("/app")
//
//public class ProductController {
//
//  // 회원가입 페이지 출력 요청
//  @GetMapping("/main")
//  public String homeController() {
//    return "/main";
//  }
//// 로그인, 회원가입시 jwt 적용 시켜서 진행?
//  // 주소 찾는
//  // 스크린키 생성해줘야 되는듯?
//
//  // 회원가입
////    @PostMapping("/user/join")
////    public ResponseEntity<UserResDto.message> join(@Valid @RequestBody UserReqDto body) {
////      return this.userService.join(body);
////    }
////
////    // 로그인
////    @PostMapping("/user/login")
////    public ResponseEntity<UserResDto.message> login(@Valid @RequestBody LoginUserReqDto body) {
////      return this.userService.login(body);
////    }
//}
//;