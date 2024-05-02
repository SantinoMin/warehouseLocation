package warehouseLocation.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import warehouseLocation.domain.dto.LocationReqDto;
import warehouseLocation.domain.dto.LocationResDto;
import warehouseLocation.domain.dto.ProductReqDto;
import warehouseLocation.domain.dto.ProductResDto;
import warehouseLocation.domain.dto.ProductResDto.CategoryList;
import warehouseLocation.domain.repository.AreaRepository;
import warehouseLocation.domain.repository.CategoryRepository;
import warehouseLocation.domain.repository.FloorRepository;
import warehouseLocation.domain.repository.ProductRepository;
import warehouseLocation.domain.repository.RackRepository;
import warehouseLocation.domain.repository.UserRepository;
import warehouseLocation.global.utills.response.error.CustomException;
import warehouseLocation.global.utills.response.error.ErrorMessage;
import warehouseLocation.models.AreaEntity;
import warehouseLocation.models.CategoryEntity;
import warehouseLocation.models.FloorEntity;
import warehouseLocation.models.ProductEntity;
import warehouseLocation.models.RackEntity;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final AreaRepository areaRepository;
  private final RackRepository rackRepository;
  private final FloorRepository floorRepository;
  private final CategoryRepository categoryRepository;
  private final UserRepository userRepository;


  @Autowired
  ProductService(ProductRepository productRepository, AreaRepository areaRepository,
      RackRepository rackRepository, FloorRepository floorRepository,
      CategoryRepository categoryRepository, UserRepository userRepository) {
    this.productRepository = productRepository;
    this.areaRepository = areaRepository;
    this.rackRepository = rackRepository;
    this.floorRepository = floorRepository;
    this.categoryRepository = categoryRepository;
    this.userRepository = userRepository;
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
    //   +이미 등록되어 있는 경우 에러 메시지 / 등록 가능한 경우에도 메시지..

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


  public ProductResDto.Edit productEdit(@PathVariable Long productId,
      @RequestBody ProductReqDto.Edit body) {
    //1. 일단 db에서 해당 productId에 대한 정보를 가져온 후
    //   productId를 검색해서, 해당 product의 정보들을 가져온 후
    //2. Repository에서 값을 update 하기.
    //3. 업데이트 한 값을 변수에 담아서, 다시 save하기.

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt = LocalDateTime.now();
    LocalDate expiredDate = LocalDate.now();

    //1번
    ProductEntity product = this.productRepository.findById(productId);

    product.setProductName(body.getProductName());
    product.setExpiredDate(expiredDate);
    product.setImageUrl(body.getImageUrl());
    product.setPrice(body.getPrice());
    product.setLocation(body.getLocation());
//    product.setSort(body.getSort());
    product.setCreatedAt(createdAt);
    product.setCreatedAt(updatedAt);

    productRepository.save(product);

    ProductResDto.Edit updatedProduct = ProductResDto.Edit.builder()
        .productId(productId)
        .productName(product.getProductName())
        .expiredDate(product.getExpiredDate())
        .imageUrl(product.getImageUrl())
        .price(product.getPrice())
        .categoryId(product.getCategoryId())
        .location(product.getLocation())
//        .createdAt(createdAt)
        .updatedAt(product.getUpdatedAt())
        .build();

    return updatedProduct;
  }

  public ResponseEntity<ProductResDto.Message> productDelete(Long productId, ProductReqDto body) {

    //1. productId로 해당 product 검색
    //2. 해당 productId를 repository에서 update로 해당 정보 비활성화(is_valid=false로 변경하기) 진행
    //3. productId repo에 저장
    //4. ResponseEntity로 ok값 반환하기.

    this.productRepository.deleteProductById(productId);

    ProductResDto.Message success = new ProductResDto.Message();
    success.setMessage("상품명 : " + body.getProductName() + " -> 삭제 완료");

    return ResponseEntity.ok(success);
  }

  public List<ProductResDto.Area> areaList() {

    //area는 구역이 몇개 안돼서, findAll로 해도 되는데,
    // 만약 userId 또는 productId였다면?
    // FindAll로 찾는건 전체 데이터를 가져오는 거라 비효율적일듯

    List<AreaEntity> areaEntities = this.areaRepository.findAll();

    List<ProductResDto.Area> areaList = new ArrayList<>();

    for (AreaEntity areaEntity : areaEntities) {

      ProductResDto.Area areaDto = new ProductResDto.Area();
      areaDto.setAreaId(areaEntity.getAreaId());

      areaList.add(areaDto);
    }

    return areaList;

  }

  public List<ProductResDto.Rack> rackList() {

    List<RackEntity> rackEntities = this.rackRepository.findAll();

    List<ProductResDto.Rack> rackList = new ArrayList<>();

    for (RackEntity rackEntity : rackEntities) {

      ProductResDto.Rack rackDto = new ProductResDto.Rack();
      rackDto.setRackId(rackEntity.getRackId());

      rackList.add(rackDto);
    }

    return rackList;
  }

  public List<ProductResDto.Floor> floorList() {

    List<FloorEntity> floorEntities = this.floorRepository.findAll();

    List<ProductResDto.Floor> rackDto = new ArrayList<>();

    for (FloorEntity floorEntity : floorEntities) {

      ProductResDto.Floor floor = new ProductResDto.Floor();
      floor.setFloorId(floorEntity.getFloor_id());

      rackDto.add(floor);
    }

    return rackDto;
  }


  //jwt인증된 user만 접근이 가능해서, 현재 customUserDetails는 실행이 안되는 듯?
  //일단 jwt설정 놔두고, 다른 api부터 작성하자.
  public CategoryList categoryList( ) {

    List<CategoryEntity> categoryList = this.categoryRepository.findAll();

    List<String> categoryIdList = categoryList.stream().map(CategoryEntity::getCategoryName).toList();


    ProductResDto.CategoryList categoryListDto = new ProductResDto.CategoryList();
    categoryListDto.setCategoryList(categoryIdList);

    return categoryListDto;
  }


  public ResponseEntity<LocationResDto.Message> setLocation(LocationReqDto body) {

    //area, rack, floor repo에 user가 입력한 값을 저장하기.
    //중복되는 값이 이미 있는지 확인 필요할듯, 방법 2가지 일 거 같은데.
    //1) DB에 area,rack,floor값들을 미리 다 저장해두고, user는 리스트에서 선택만 해서, 위치 저장하도록.
    //2) 그냥 user가 임의로 값을 입력해서, repository에 저장 하도록.

    //1)번으로 실행하면 -> repo에 있는 각(area,rack,floor) 리스트 정보들을 보여주기만 하면 됨
    //근데 4번에서는 보여주고 선택하고 등록하는 게 아니라, 값 넣고 저장하는 걸로 등록하는 거 끝 // 2번에서 선택하고 저장하는 걸로.

    //2)번으로 실행하면 -> user가 입력한 값 그대로 db에 저장해보기 + 중복값만 없도록.
    // *중복값 찾기 + repo에 저장
    /**
     * Area 중복 확인 후, 중복 아니라면 repo에 저장하기.
     */
    AreaEntity area = new AreaEntity();
    area.setAreaId(body.getAreaId());

    Optional<AreaEntity> duplicatedArea = this.areaRepository.findByAreaId(body.getAreaId());
    duplicatedArea.ifPresent(a -> {
      throw new CustomException(ErrorMessage.DUPLICATE_AREA);
    });
    this.areaRepository.save(area);

    /**
     * Rack 중복 확인 후, 중복 아니라면 repo에 저장하기
     */
    RackEntity rack = new RackEntity();
    rack.setRackId(body.getRackId());

    Optional<RackEntity> duplicatedRack = this.rackRepository.findByRackId(body.getRackId());
    duplicatedRack.ifPresent(r -> {
      throw new CustomException(ErrorMessage.DUPLICATE_RACK);
    });
    this.rackRepository.save(rack);

    /**
     * Floor중복 확인 후, 중복 아니라면 repo에 저장하기
     */
    FloorEntity floor = new FloorEntity();
    floor.setFloor_id(body.getFloorId());
    Optional<FloorEntity> duplicatedFloor = this.floorRepository.findByFloorId(body.getFloorId());
    duplicatedFloor.ifPresent(f -> {
      throw new CustomException(ErrorMessage.DUPLICATE_FLOOR);
    });
    this.floorRepository.save(floor);

    // LocationResDto 객체 생성 및 값 설정
    LocationResDto.Message locationResDto = new LocationResDto.Message();
    locationResDto.setMessage("로케이션 : " + body.getLocationId() + " -> 등록 완료");

    return ResponseEntity.ok(locationResDto);
  }



}
