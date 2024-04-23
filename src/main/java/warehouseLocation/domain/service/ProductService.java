package warehouseLocation.domain.service;

import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import warehouseLocation.domain.dto.ProductReqDto;
import warehouseLocation.domain.dto.ProductResDto;
import warehouseLocation.domain.repository.ProductRepository;
import warehouseLocation.global.utills.response.error.CustomException;
import warehouseLocation.global.utills.response.error.ErrorMessage;
import warehouseLocation.models.ProductEntity;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * 상품 검색 - 더 추가 필요: 1)상품명의 2글자만 일치하여도 db에서 찾아서 보이도록 하기. 2) imageUrl이 List형태로 보여지도록 해야됨.
   */
  public ProductResDto.ProductSearch search(String productName) {

    Optional<ProductEntity> product = this.productRepository.searchProduct(productName);

    ProductEntity searchProduct = product.orElseThrow(() -> new CustomException(
        ErrorMessage.NOT_FOUND_PRODUCT));

    ProductResDto.ProductSearch toDto = new ProductResDto.ProductSearch();
    toDto.setProductName(searchProduct.getProductName());
    toDto.setImageUrl(Collections.singletonList(searchProduct.getImageUrl()));
    toDto.setPrice(searchProduct.getPrice());
    //category Id말고, category를 보여줄 순 없는지?
    toDto.setCategoryId(searchProduct.getCategoryId());
    toDto.setStatus(searchProduct.getStatus());
    return toDto;
  }

  /** 상품 정보
   *
   *
   */
  public ProductResDto.ProductInfo productInfo(@RequestParam int productId){

    //search에서 검색한 후, product의 세부 정보를 보는 거라, id는 무조건 참이여야 함.

    // 그 전에 일단 productName을 productRepo에서 찾아서, 찾은 productName의 id값을 가져와서
    // id값을 통해서, 정보들을 가져와야 되는듯?
    // 여기부터 이어서 하기.
    ProductEntity productInfo = this.productRepository.productInfoById(productId);


    return null;
  }

  public String productEdit(ProductReqDto body) {
    return null;
  }

  public String productDelete(ProductReqDto body) {
    return null;
  }

  public String locationList(ProductReqDto body) {
    return null;
  }

  public String addArea(ProductReqDto body) {
    return null;
  }

  public String addRack(ProductReqDto body) {
    return null;
  }

  public String addFloor(ProductReqDto body) {
    return null;
  }

}
