package warehouseLocation.domain.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

  public ProductResDto.ProductInfo search(String productName) {

    //사용자가 입력한 값(=query parameters에 입력된 값)을 repository에서 찾기
    //만약 상품명과 일치하는 상품이 있다면, 상품들 전체 보여주기
    //근데, 검색 시, 2글자만 일치하는 경우? 이런 부분도 설정 해줘야 되는듯?
    Optional<ProductEntity> product = this.productRepository.searchProduct(productName)

    ProductEntity searchProduct = product.orElseThrow(() -> new CustomException(
        ErrorMessage.NOT_FOUND_PRODUCT));



    searchProduct.save
    return null;
  }

  public String productInfo(ProductReqDto body) {
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
