package warehouseLocation.domain.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import warehouseLocation.domain.dto.LocationReqDto;
import warehouseLocation.domain.dto.LocationResDto;
import warehouseLocation.domain.dto.ProductReqDto;
import warehouseLocation.domain.dto.ProductResDto;
import warehouseLocation.domain.service.ProductService;

@RestController
//@Slf4j // 응답을 기록하는 데 도움이 되며 주로 디버깅 목적
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  @Autowired
  ProductController(ProductService productService) {

    this.productService = productService;
  }

  //2.1(Get) /product/manage/search : 상품 검색
  /**
   * 값을 입력 안하고, 검색을 했을 경우 메시지 띄우려면? 메시지 아예 안 띄워도 되긴하지만,,만약에 띄운다면? 만약에 띄운다면, 1)전체 값들 보여주기 또는 2) 상품명을
   * 입력하세요 메시지 출력
   */
  @GetMapping("/manage/search")
  public List<ProductResDto.ProductSearch> search(
      @RequestParam(required = true) String productName) {
    return this.productService.search(productName);
  }

  //2.2 (GET) product/manage/search/{product_id} : 해당 상품 정보
  @GetMapping("/manage/search/{productId}")
  public ProductResDto.ProductInfo productInfo(@PathVariable Long productId) {
    System.out.println("productId : " + productId);
    return this.productService.productInfo(productId);
  }

  //2.2 (GET) /product/location/areaList : 구역 리스트
  @GetMapping("/location/areaList")
  public List<ProductResDto.Area> areaList() {
    return this.productService.areaList();
  }

  //2.2 (GET) /product/location/rackList : 랙 리스트
  @GetMapping("/location/rackList")
  public List<ProductResDto.Rack> rackList() {
    return this.productService.rackList();
  }

  //2.2 (GET) /product/location/floorList : 층 리스트
  @GetMapping("/location/floorList")
  public List<ProductResDto.Floor> floorList() {
    return this.productService.floorList();
  }

  //2.2 (put) product/manage/{productId}/edit : 상품 정보 변경
  @PutMapping("/manage/{productId}/edit")
  public ProductResDto.Edit productEdit(@PathVariable Long productId,
      @RequestBody ProductReqDto.Edit body) {
    System.out.println("producEdit = " + body);
    return this.productService.productEdit(productId, body);
  }

  //2.2(PUT) /product/manage/{productId}/delete : 해당 상품 삭제(완전 삭제 대신, 업데이트로 진행)
  @PutMapping("/manage/{productId}/delete")
  public ResponseEntity<ProductResDto.Message> productDelete(@PathVariable Long productId,
      @RequestBody ProductReqDto body) {
    System.out.println("delete this productId = " + productId);
    return this.productService.productDelete(productId, body);
  }


  //3.1 (GET) /product/manage/categoryList : 카테고리 리스트
//  CustomUserDetail 사용할 지? 있어야지 참고해서 가져올 수 있을듯
//  customUserDetail사용하려면 User인증을 거친 상태여야 되는데..
  @GetMapping("/manage/categoryList")
  public ProductResDto.CategoryList categoryList() {
    return this.productService.categoryList();
  }


  //3.1 (POST) /product/manage/post : 상품 등록
  @PostMapping("/manage/post")
  public ProductResDto.Register ProductRegister(@Valid @RequestBody ProductReqDto body) {

    return this.productService.productRegister(body);
  }


  /**
   * 4 (POST) /product/location/setLocation : 구역 등록 areaList, rackList, floorList에서 값들을 가져온 후 해당 값들
   * 중 하나씩 선택 후 구역에 등록하기
   */

//4.1 (POST) /product/location/setLocation : 구역 등록
  @PostMapping("/manage/location/setLocation")
  public ResponseEntity<LocationResDto.Message> addLocation(@RequestBody LocationReqDto body) {
    return this.productService.setLocation(body);
  }


};