package warehouseLocation.domain.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  @GetMapping("/manage/search")
  public List<ProductResDto.ProductSearch> search(
      @RequestParam String productName) {
    return this.productService.search(productName);
  }

  //2.2 (GET) product/manage/search/{product_id} : 해당 상품 정보
  @GetMapping("/manage/search/{productId}")
  public ProductResDto.ProductInfo productInfo(@PathVariable Long productId) {
    return this.productService.productInfo(productId);
  }

  //3.1 (put) product/manage/search/{productId} : 상품 정보 변경
  @PutMapping("/manage/search/{productId}")
  public ProductResDto.Edit productEdit(@PathVariable Long productId,
      @RequestBody ProductReqDto.Edit body) {
    return this.productService.productEdit(productId, body);
  }

  //3.1(DELETE) /product/manage/{productId} : 해당 상품 삭제(완전 삭제 대신, 업데이트로 진행)
  @DeleteMapping("/manage/search/{productId}/delete")
  public ResponseEntity<ProductResDto.Message> productDelete(@PathVariable Long productId,
      @RequestBody ProductReqDto body) {
    return this.productService.productDelete(productId, body);
  }

  //3.1 (POST) /product/manage/post : 상품 등록
  @PostMapping("/manage/post")
  public ProductResDto.Register ProductRegister(@Valid @RequestBody ProductReqDto body) {

    return this.productService.productRegister(body);
  }

  //3.1 (GET) /product/manage/categoryList : 카테고리 리스트
  @GetMapping("/manage/categoryList")
  public ProductResDto.CategoryList categoryList() {
    return this.productService.categoryList();
  }

  //2.2 (GET) /product/location/areaList : 구역 리스트
  @GetMapping("/location/areaList")
  public ProductResDto.AreaResponse areaList() {
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




  /**
   * 4 (POST) /product/location/setLocation : 구역 등록 areaList, rackList, floorList에서 값들을 가져온 후 해당 값들
   * 중 하나씩 선택 후 구역에 등록하기
   */
//4.1 (POST) /product/location/setArea : 구역 등록
  @PostMapping("/location/addArea")
  public ResponseEntity<LocationResDto.Message> addArea(@RequestBody LocationReqDto body) {
    return this.productService.addArea(body);
  }

  //4.1 (POST) /product/location/setRack : 랙 등록
  @PostMapping("/location/setRack")
  public ResponseEntity<LocationResDto.Message> addRack(@RequestBody LocationReqDto body) {
    return this.productService.addRack(body);
  }

  //4.1 (POST) /product/location/setFloor : 층 등록
  @PostMapping("/location/setFloor")
  public ResponseEntity<LocationResDto.Message> addFloor(@RequestBody LocationReqDto body) {
    return this.productService.addFloor(body);
  }


};