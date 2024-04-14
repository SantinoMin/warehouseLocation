package warehouseLocation.domain.controller;

import io.swagger.v3.oas.annotations.Parameter;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import warehouseLocation.domain.dto.ProductReqDto;

@RestController
@Slf4j // 응답을 기록하는 데 도움이 되며 주로 디버깅 목적
//@ResponseBody // 요거 왜 쓰고있지? // return되는 확인용으로 html이 아닌 문자열을 받기 위해.
@RequestMapping("/product")
//@Tag(name = "Product Controller", description = "상품 관리 controller")
public class ProductController {

  //1.1 (GET) /product/manage/search : 상품 관리 페이지로 이동.
  @GetMapping("/manage/search")
//  @Operation(summary = "상품 관리", description = "상품 관리 페이지로 이동")
//  @Hidden
  public String manage(ProductReqDto body) {
    System.out.println("productManage");
    return "productManage";
  }

  //2.1(Get) /product/manage/search/productsList : 상품 검색
  @GetMapping("/manage/search/productsList")
//  @Operation(summary = "상품 관리 - 검색", description = "상품 리스트 검색")
  public String productsList(ProductReqDto body) {
    System.out.println("body" + body);
    return "productsList";
  }

  //2.2 (GET) product/manage/search/productsList/productInfo 상품 정보 보여주기
  @GetMapping("/manage/search/productsList/productInfo")
//  @Operation(summary = "상품 정보", description = "상품 리스트 - 상품 정보 검색")
  public String productInfo(ProductReqDto body) {
    System.out.println("body" + body);
    return "productsList";
  }

  //2.2(PUT) /product/manage/search/productsList/productInfo/edit : 상품 정보 변경
  @PutMapping("/manage/search/productsList/productInfo/edit")
//  @Operation(summary = "상품 수정", description = "상품 정보 수정")
  public String productEdit(ProductReqDto body,
      @Parameter(description = "key value값 필요") @RequestBody Map<String, Object> putData) {
    StringBuilder sb = new StringBuilder();

    putData.entrySet().forEach(map -> {
      sb.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
    });

    System.out.println("nice");
    return sb.toString();
  }

//2.2(PUT) /product/manage/search/productsList/productInfo/delete : 상품 삭제
  @PutMapping("/manage/search/productsList/productInfo/delete")
//  @Operation(summary = "상품 삭제", description = "상품 내용 업데이트")
  public String productDelete(ProductReqDto body,
      @Parameter(description = "key value값 필요") @RequestBody Map<String, Object> putData) {
    StringBuilder sb = new StringBuilder();

    putData.entrySet().forEach(map -> {
      sb.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
    });

    System.out.println("nice");
    return sb.toString();
  }

//  3.1 (POST) /product/manage/register/post : 상품 등록
@PutMapping("/manage/manage/register/post")
//@Operation(summary = "상품 등록", description = "새로운 상품 등록 작업")
public String ProductPost(ProductReqDto body,
    @Parameter(description = "key value값 필요") @RequestBody Map<String, Object> putData) {
  StringBuilder sb = new StringBuilder();

  putData.entrySet().forEach(map -> {
    sb.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
  });

  System.out.println("nice");
  return sb.toString();
}

//4.1 (POST) /product/location/add : 위치 등록
@PostMapping("/manage/location/add")
//@Operation(summary = "위치 등록", description = "새로운 위치 등록")
public String locationAdd(@RequestBody ProductReqDto body,
    @Parameter(description = "위치 등록") @RequestBody Map<String, Object> putData) {
  StringBuilder sb = new StringBuilder();

  putData.entrySet().forEach(map -> {
    sb.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
  });

  System.out.println("nice");
  return sb.toString();
}


};

// 로그인, 회원가입시 jwt 적용 시켜서 진행?
// 주소 찾는
// 스크린키 생성해줘야 되는듯?

// 회원가입
//    @PostMapping("/user/join")
//    public ResponseEntity<UserResDto.message> join(@Valid @RequestBody UserReqDto body) {
//      return this.userService.join(body);
//    }
//
//    // 로그인
//    @PostMapping("/user/login")
//    public ResponseEntity<UserResDto.message> login(@Valid @RequestBody LoginUserReqDto body) {
//      return this.userService.login(body);
//    }
