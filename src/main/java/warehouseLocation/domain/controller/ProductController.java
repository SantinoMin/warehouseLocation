package warehouseLocation.domain.controller;

import io.swagger.v3.oas.annotations.Parameter;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import warehouseLocation.domain.dto.ProductReqDto;
import warehouseLocation.domain.dto.ProductResDto;
import warehouseLocation.domain.service.ProductService;

@RestController
@Slf4j // 응답을 기록하는 데 도움이 되며 주로 디버깅 목적
//@ResponseBody // 요거 왜 쓰고있지? // return되는 확인용으로 html이 아닌 문자열을 받기 위해.
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  @Autowired
  ProductController(ProductService productService) {

    this.productService = productService;
  }

  //1.1 (GET) /product/manage/search : 상품 검색
  @GetMapping("/manage/search")
  public ProductResDto.ProductInfo search(@RequestParam String productName) {
    System.out.println("productManage");

    return this.productService.search(productName);
  }

  //2.2 (GET) product/manage/search/{productId} 상품 정보
  @GetMapping("/manage/search/{productId}")
  public String productInfo(ProductReqDto body) {
    System.out.println("body" + body);
    return this.productService.productInfo(body);
  }


  //  3.1 (POST) /product/manage/register : 상품 등록
  @PutMapping("/manage/register")
  public String ProductPost(ProductReqDto body,
      @Parameter(description = "key value값 필요") @RequestBody Map<String, Object> putData) {
    StringBuilder sb = new StringBuilder();

    putData.entrySet().forEach(map -> {
      sb.append(map.getKey()).append(" : ").append(map.getValue()).append("\n");
    });

    System.out.println("nice");
    return sb.toString();
  }

  //2.2(PUT) /product/manage/search{productId}/edit : 상품 정보 변경

  /**
   * 이거 ParamVariable인가 그거 설정하도록 해야함.
   */
  @PutMapping("/manage/search/{productId}/edit")
//  @Operation(summary = "상품 수정", description = "상품 정보 수정")
  public String productEdit(@RequestBody ProductReqDto body
  ) {

    System.out.println("nice");
    return this.productService.productEdit(body);
  }


  //2.2(PUT) /product/manage/search/{productId}/delete : 해당 상품 삭제
  @PutMapping("/manage/search/{productId}/delete")
  public String productDelete(@RequestBody ProductReqDto body,
      @Parameter(description = "key value값 필요")) {

    System.out.println("nice");
    return this.productService.productDelete(body);
  }

  //2.2 (GET) /product/locationManagement/locationList : 위치 리스트
  @GetMapping("/locationManagement/locationList")
  public String locationList(ProductReqDto body) {
    System.out.println("body" + body);
    return this.productService.locationList(body);
  }


  //4 (POST) /product/location/addArea : 구역 등록
  @PostMapping("/manage/location/addArea")
  public String addArea(@RequestBody ProductReqDto body) {
    return this.productService.addArea(body);
  }

  //4.1 (POST) /product/location/addRack : 랙 추가
  @PostMapping("/manage/location/addRack")
  public String addRack(@RequestBody ProductReqDto body) {
    return this.productService.addRack(body);
  }

  //4.1 (POST) /product/location/addFloor : 층 추가
  @PostMapping("/manage/location/addFloor")
  public String addFloor(@RequestBody ProductReqDto body) {
    return this.productService.addFloor(body);
  }


};