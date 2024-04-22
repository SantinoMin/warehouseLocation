package warehouseLocation.domain.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  /**
   * 상품 검색 - 더 추가 필요: 상품명의 2글자만 일치하여도 db에서 찾아서 보이도록 하기.
   */
  public ProductResDto.ProductInfo search(String productName) {

    Optional<ProductEntity> product = this.productRepository.searchProduct(productName);

    ProductEntity searchProduct = product.orElseThrow(() -> new CustomException(
        ErrorMessage.NOT_FOUND_PRODUCT));

    ProductResDto.ProductInfo toDto = new ProductResDto.ProductInfo();
    toDto.setProductName(searchProduct.getProductName());
    toDto.setImageUrl(searchProduct.getImageUrl());
    toDto.setPrice(searchProduct.getPrice());
    //category Id말고, category를 보여줄 순 없는지?
    toDto.setCategoryId(searchProduct.getCategoryId());
    toDto.setStatus(searchProduct.getStatus());
    return toDto;
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
