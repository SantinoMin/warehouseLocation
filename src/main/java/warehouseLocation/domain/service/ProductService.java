package warehouseLocation.domain.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import warehouseLocation.domain.dto.ProductReqDto;
import warehouseLocation.domain.dto.ProductResDto;
import warehouseLocation.domain.dto.ProductResDto.Register;
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
   * 상품 검색 - 더 추가 필요: 1)상품명의 2글자만 일치하여도 db에서 찾아서 보이도록 하기. 2)(완료) imageUrl이 List형태로 보여지도록 해야됨.
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

  /**
   * 상품 정보 !!해결 필요 1)dto의 Location클래스를 타입으로 가져오는 법? 2)imageUrl이 Postman response에서 리스트 형태로 보여지는 법(배열
   * 형태로) // 생성자 만들어서 하는듯?
   */
  public ProductResDto.ProductInfo productInfo(@RequestParam Long productId) {

    //search에서 검색한 후, product의 세부 정보를 보는 거라, id는 무조건 참이여야 함.
    // 그 전에 일단 productId을 productRepo에서 찾아서
    // id값을 통해서, 정보들을 가져와야 되는듯?

    ProductEntity productInfo = this.productRepository.productInfoById(productId);

    ProductResDto.ProductInfo info = new ProductResDto.ProductInfo();
    info.setProductId(productInfo.getProductId());
    info.setProductName(productInfo.getProductName());
    info.setImageUrl(Collections.singletonList(productInfo.getImageUrl()));
    info.setPrice(productInfo.getPrice());
    info.setCategoryId(productInfo.getCategoryId());
    info.setStatus(productInfo.getStatus());
    return info;

    //!! 값이 존재하지 않을 경우는 없음 -> search 메서드에서 검색한 이후의 상품 정보를 보여주는 것이기 때문에.

  }

  ;

  public ProductResDto.Register productRegister(ProductReqDto body) {

    //1. 상품이 중복이 아닌지 repo에서 확인 후, 중복이 아니라면 등록 가능하도록 하기.
    //   +이미 등록되어 있는 경우 에러 메시지 / 등록 가능한 경우에도 메시지.

    Optional<ProductEntity> register = this.productRepository.registerByProductName(
        body.getProductName());

    register.ifPresent(existingProduct -> {
      throw new CustomException("이미 등록된 상품 입니다 : " + body.getProductName());
//          (ErrorMessage.PRODUCT_ALREADY_EXIST);
    });

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt = LocalDateTime.now();

    ProductEntity newProduct = new ProductEntity();
    newProduct.setProductId(newProduct.getProductId());
    newProduct.setProductName(body.getProductName());
    newProduct.setExpiredDate(body.getExpiredDate());
    newProduct.setImageUrl(body.getImageUrl());
    newProduct.setPrice(body.getPrice());
    newProduct.setCreatedAt(createdAt);
    newProduct.setUpdatedAt(updatedAt);


//    ProductEntity registerdProduct =
    this.productRepository.save(newProduct);
    System.out.println("newProduct = " + newProduct);

    ProductResDto.Register toDto = new ProductResDto.Register();
    toDto.setProductId((newProduct.getProductId()));
    toDto.setProductName(newProduct.getProductName());
    toDto.setExpiredDate(newProduct.getExpiredDate());
    toDto.setImageUrl(newProduct.getImageUrl());
    toDto.setPrice(newProduct.getPrice());
    toDto.setCreatedAt(newProduct.getCreatedAt());
//    toDto.setCreatedAt(newProduct.getCreatedAt());
    toDto.setUpdatedAt(createdAt);
//    toDto.setUpdatedAt(updatedAt);
    return toDto;

  }


  public String productEdit(@RequestBody ProductReqDto body) {

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
