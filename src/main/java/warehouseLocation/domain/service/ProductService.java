package warehouseLocation.domain.service;

import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import warehouseLocation.domain.dto.LocationResDto;
import warehouseLocation.domain.dto.ProductReqDto;
import warehouseLocation.domain.dto.ProductResDto;
import warehouseLocation.domain.repository.*;
import warehouseLocation.global.utills.response.error.CustomException;
import warehouseLocation.global.utills.response.error.ErrorMessage;
import warehouseLocation.models.CategoryEntity;
import warehouseLocation.models.Location;
import warehouseLocation.models.ProductEntity;
import warehouseLocation.models.ProductLocationEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AreaRepository areaRepository;
    private final RackRepository rackRepository;
    private final FloorRepository floorRepository;
    private final CategoryRepository categoryRepository;
    private ProductLocationRepository productLocationRepository;


    @Autowired
    ProductService(ProductRepository productRepository, AreaRepository areaRepository,
                   RackRepository rackRepository, FloorRepository floorRepository,
                   CategoryRepository categoryRepository, UserRepository userRepository,
                   ProductLocationRepository productLocationRepository) {
        this.productRepository = productRepository;
        this.areaRepository = areaRepository;
        this.rackRepository = rackRepository;
        this.floorRepository = floorRepository;
        this.categoryRepository = categoryRepository;
        this.productLocationRepository = productLocationRepository;
    }

    //2.1(GET) /manage/product : 상품 검색
    public List<ProductResDto.ProductSearch> search(String productName) throws Exception {

        /**
         * 1) (완료)상품명이 일부라도 포함되는 경우, 전부 검색 가능 (검색: 콜라 -> 펩시 콜라, 제로 콜라, 코카 콜라 검색 가능)
         * 2) (완료)이미지는 여러개 등록 가능
         */

        //1-1.productName을 ProductEntity에서 존재하는 상품명인지 확인 후, 있다면 필요한 정본들 넘겨주기.
        //상품명 리스트 가져오기
        List<ProductEntity> productNameList = this.productRepository.byProductName(productName);

        //1-2 productEntity의 정보들 가져오기

        System.out.println("productNameList " + productNameList);


        // 만약 product 값이 없을 시, 에러 띄우기.
        if (productNameList.isEmpty()) {
            throw new CustomException(ErrorMessage.NOT_FOUND_PRODUCTLIST);
        }

        List<ProductResDto.ProductSearch> productAdd = new ArrayList<>();

        for (ProductEntity prod : productNameList) {

            //객체 생성
            ProductResDto.ProductSearch product = new ProductResDto.ProductSearch();

            product.setProductName(productName);
            product.setProductId(prod.getProductId());
            product.setPrice(prod.getPrice());
            product.setStatus(prod.getStatus());
            product.setImageUrl(prod.getImageUrl());
            product.setUpdatedAt(prod.getUpdatedAt());
            product.setCreatedAt(prod.getCreatedAt());
            product.setExpiredDate(prod.getExpiredDate());

            // 2-1)카테고리 이름 저장
            Optional<CategoryEntity> categoryNameOpt = this.categoryRepository.categoryNameByCategoryId(prod.getCategoryId());
            String categoryName = categoryNameOpt.map(CategoryEntity::getCategoryName).orElseGet(() -> "카테고리가 비어있습니다.");
            System.out.println(categoryName);

            product.setCategoryName(categoryName);

            //3-1) Location 저장
            Optional<ProductLocationEntity> productLocationOpt = this.productLocationRepository.productLocationIdByProductId(prod.getProductId());


            ProductLocationEntity ple = new ProductLocationEntity();

            productLocationOpt.ifPresent(p -> {
                ple.setAreaId(p.getAreaId());
                ple.setRackId(p.getRackId());
                ple.setFloorId(p.getFloorId());
            });

            String areaName = this.areaRepository.findAreaNameByAreaId(ple.getAreaId());
            Long rackNumber = this.rackRepository.findRackNumByRackId(ple.getRackId());
            Long floorNumber = this.floorRepository.findFloorNumByFloorId(ple.getFloorId());

            LocationResDto locationRD = new LocationResDto();
            locationRD.setAreaName(areaName);
            locationRD.setRackNumber(rackNumber);
            locationRD.setFloorNumber(floorNumber);

            product.setLocation(locationRD);

            productAdd.add(product);
        }

        return productAdd;
    }

    ;

    // 2.2 상품 정보
    public ProductResDto.ProductInfo productInfo(Long productId) throws Exception {
        ProductEntity productEntity = this.productRepository.productInfoByProductId(productId);

        ProductResDto.ProductInfo productInfo = new ProductResDto.ProductInfo();

        //카테고리 찾기 by productId
        Optional<Long> categoryOpt = this.productRepository.categoryIdByProductId(productId);
        Long categoryId = categoryOpt.orElseThrow(() -> new Exception("dd"));

        // location 찾기


        productInfo.setProductId(productEntity.getProductId());
        productInfo.setProductName(productEntity.getProductName());

        productInfo.setCategory(categoryId);

        productInfo.setImageUrl(productEntity.getImageUrl());
        productInfo.setPrice(productEntity.getPrice());
        productInfo.setCreatedDate(productEntity.getCreatedAt());
        productInfo.setUpdatedDate(productEntity.getUpdatedAt());
        productInfo.setStatus(productEntity.getStatus());


        //product_id 구하고(parameter에 있음), product_location 테이블에서 location정보 얻기

        Optional<ProductLocationEntity> productLocationOpt = this.productLocationRepository.productLocationIdByProductId(productId);

        ProductLocationEntity ple = new ProductLocationEntity();

        productLocationOpt.ifPresent(p -> {
            ple.setAreaId(p.getAreaId());
            ple.setRackId(p.getRackId());
            ple.setFloorId(p.getFloorId());
        });

        String areaName = this.areaRepository.findAreaNameByAreaId(ple.getAreaId());
        Long rackNumber = this.rackRepository.findRackNumByRackId(ple.getRackId());
        Long floorNumber = this.floorRepository.findFloorNumByFloorId(ple.getFloorId());

        ProductResDto.Location locationRD = new ProductResDto.Location();
        locationRD.setAreaName(areaName);
        locationRD.setRackNumber(rackNumber);
        locationRD.setFloorNumber(floorNumber);


        productInfo.setLocation(locationRD);


        return productInfo;
    }


    /**
     * 하나 또는 여러개로 나눌 필요 없이, 값 있으면 값들 보여주는 걸로 진행해보기
     * 상품 정보 !!해결 필요 1)(완료)dto의 Location클래스를 타입으로 가져오는 법? 2)imageUrl이 Postman response에서 리스트 형태로 보여지는
     * 법(배열 형태로) -> 이거 , 콤마로 나누는 거 맞는지?
     */

    public ProductResDto.Edit productEdit(@PathVariable Long productId, @RequestBody ProductReqDto.Edit body) {


        // 1. parameter의 productId를 가지고, product Table에서 값 조회
        // 2. 사용자가 body에 입력한 값을 다시 product에 저장해주기

        //   LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        LocalDate expiredDate = LocalDate.now();

        // db에서 productId로 변경할 상품 검색
        ProductEntity productInfo = this.productRepository.ProductInfoByProductId(productId);

        //categoryName 구하기
        Optional<CategoryEntity> categoryNameOpt = this.categoryRepository.categoryNameByCategoryId(productInfo.getCategoryId());
//        CategoryEntity category = categoryNameOpt.orElseThrow();

        CategoryEntity categoryInfo = new CategoryEntity();
        categoryNameOpt.ifPresent(c -> {
            categoryInfo.setCategoryName(c.getCategoryName());
        });

        // Location 정보 구하기
        Optional<ProductLocationEntity> productLocationOpt = this.productLocationRepository.productLocationIdByProductId(productId);

        ProductLocationEntity ple = new ProductLocationEntity();

        productLocationOpt.ifPresent(p -> {
            ple.setAreaId(p.getAreaId());
            ple.setRackId(p.getRackId());
            ple.setFloorId(p.getFloorId());
        });

        String areaName = this.areaRepository.findAreaNameByAreaId(ple.getAreaId());
        Long rackNumber = this.rackRepository.findRackNumByRackId(ple.getRackId());
        Long floorNumber = this.floorRepository.findFloorNumByFloorId(ple.getFloorId());

        ProductResDto.Location locationRD = new ProductResDto.Location();
        locationRD.setAreaName(areaName);
        locationRD.setRackNumber(rackNumber);
        locationRD.setFloorNumber(floorNumber);


        // 변경 값 저장할 entity 생성
        ProductResDto.Edit updateProduct = new ProductResDto.Edit();
        updateProduct.setProductName(body.getProductName());

        updateProduct.setCategoryName(categoryInfo.getCategoryName());

        updateProduct.setProductId(productId);
        updateProduct.setExpiredDate(body.getExpiredDate());
        updateProduct.setImageUrl(body.getImageUrl());
        updateProduct.setPrice(body.getPrice());
        updateProduct.setStatus(body.getStatus());

        updateProduct.setLocation(locationRD);

        updateProduct.setUpdatedAt(updatedAt);


            return updateProduct;
    }

//    !! productDelete에서 body 부분이 필요한지? productId만 있으면 되는거 아닌지?
  public ResponseEntity<ProductResDto.Message> productDelete(Long productId, ProductReqDto body) {

//    //1. productId로 해당 product 검색
//    //2. 해당 productId를 repository에서 update로 해당 정보 비활성화(is_valid=false로 변경하기) 진행
//    //3. productId repo에 저장
//    //4. ResponseEntity로 ok값 반환하기.

      //메시지 정보 넣을 객체
      ProductResDto.Message success = new ProductResDto.Message();

      //찾기만 하고, repository에서 update 하는 작업 안되어있음 --> 여기서부터 10/17(목) 이어서

      Optional<ProductEntity> productInfo = this.productRepository.productNameByProductId(productId);
      productInfo.ifPresent( p -> {
              success.setProductId(productId);
              success.setProductName(p.getProductName());
              success.setStatus("(삭제 완료) ->  " + "상품명: " + p.getProductName());
              });

    return ResponseEntity.ok(success);
  }
}


//
//  public ProductResDto.Register productRegister(ProductReqDto body) {
////
////    // 1. 상품 중복 확인
//    Optional<ProductEntity> optRegister = this.productRepository.registerByProductName(
//        body.getProductName());
//    System.out.println("optRegister = " + optRegister);
//
//    optRegister.ifPresent(name -> {
//      if (name.getProductName().equals(body.getProductName())) {
//        throw new CustomException("해당 상품명은 이미 존재합니다.");
//      }
//    });
//
//    // 2. 상품 DB에 저장
//    LocalDateTime createdAt = LocalDateTime.now();
//    LocalDateTime updatedAt = LocalDateTime.now();
//
//    ProductEntity product = new ProductEntity();
//    product.setProductName(body.getProductName());
////      product.setProductId();
//    product.setExpiredDate(body.getExpiredDate());
//    product.setImageUrl(body.getImageUrl());
//    product.setPrice(body.getPrice());
//    product.setCreatedAt(createdAt);
//    product.setUpdatedAt(updatedAt);
////      product.setValid(body.isValid());
//    this.productRepository.save(product);
//    System.out.println("product = " + product);
//
//    // 3. 사용자가 카테고리 리스트에서 카테고리 선택하기 -> 이건 좀 복잡해지네. api를 따로 둬서 카테고리를 선택하는 걸로 가야될듯.
//
//    // 4. 등록 완료 시, 등록 요청 상품 내역 반환.
//
//    ProductResDto.Register toDto = new ProductResDto.Register();
//    toDto.setProductName(product.getProductName());
//    toDto.setProductId(product.getProductId());
//    toDto.setStatus("등록 완료 되었습니다");
//    toDto.setCreatedAt(createdAt);
//    return toDto;
//  }

//  public CategoryList categoryList() {
//
//    List<CategoryEntity> categoryList = this.categoryRepository.findAll();
//
//    List<String> categoryNameList = categoryList.stream().map(CategoryEntity::getCategoryName)
//        .toList();
//
//    ProductResDto.CategoryList categoryListDto = new ProductResDto.CategoryList();
//    categoryListDto.setCategoryNameList(categoryNameList);
//
//    return categoryListDto;
//  }

//  public ProductResDto.AreaResponse areaList() {
//
//    List<AreaEntity> areaEntities = this.areaRepository.findAll();
//
//    List<ProductResDto.Area> areaList = new ArrayList<>();
//    for (AreaEntity areaEntity : areaEntities) {
//      ProductResDto.Area areaDto = new ProductResDto.Area();
//      areaDto.setId(areaEntity.getAreaId());
//      areaDto.setName(areaEntity.getAreaName());
//      areaList.add(areaDto);
//    }

//    ProductResDto.AreaResponse response = new ProductResDto.AreaResponse();
//    response.setArea(areaList);
//
//    return response;
//  }
//
//  public List<ProductResDto.Rack> rackList() {
//
//    List<RackEntity> rackEntities = this.rackRepository.findAll();
//
//    List<ProductResDto.Rack> rackList = new ArrayList<>();
//
//    for (RackEntity rackEntity : rackEntities) {
//
//      ProductResDto.Rack rackDto = new ProductResDto.Rack();
//      rackDto.setRackId(rackEntity.getRackId());
//      rackDto.setRackNumber(rackEntity.getRackNumber());
//
//      rackList.add(rackDto);
//    }
//
//    return rackList;
//  }
//
//  public List<ProductResDto.Floor> floorList() {
//
//    List<FloorEntity> floorEntities = this.floorRepository.findAll();
//
//    List<ProductResDto.Floor> rackDto = new ArrayList<>();
//
//    for (FloorEntity floorEntity : floorEntities) {
//
//      ProductResDto.Floor floor = new ProductResDto.Floor();
//      floor.setFloorId(floorEntity.getFloor_id());
//      floor.setFloorNumber(floorEntity.getFloor_number());
//
//      rackDto.add(floor);
//    }
//
//    return rackDto;
//  }
//
//
//  @Transactional
//  public ResponseEntity<LocationResDto.Message> addArea(LocationReqDto body) {
//
//    AreaEntity area = new AreaEntity();
//    area.setAreaId(body.getAreaId());
//
//    Optional<AreaEntity> duplicatedArea = this.areaRepository.findByAreaId(body.getAreaId());
//    duplicatedArea.ifPresent(a -> {
//      throw new CustomException(ErrorMessage.DUPLICATE_AREA);
//    });
//    this.areaRepository.save(area);
//    return null;
//  }
//
//  ;
//
//  public ResponseEntity<LocationResDto.Message> addRack(LocationReqDto body) {
//    /**
//     * Rack 중복 확인 후, 중복 아니라면 repo에 저장하기
//     */
//    RackEntity rack = new RackEntity();
//    rack.setRackId(body.getRackId());
//
//    Optional<RackEntity> duplicatedRack = this.rackRepository.findByRackId(body.getRackId());
//    duplicatedRack.ifPresent(r -> {
//      throw new CustomException(ErrorMessage.DUPLICATE_RACK);
//    });
//    this.rackRepository.save(rack);
//
//    return null;
//  }
//
//  ;
//
//  public ResponseEntity<LocationResDto.Message> addFloor(LocationReqDto body) {
////    /**
////     * Floor중복 확인 후, 중복 아니라면 repo에 저장하기
////     */
//    FloorEntity floor = new FloorEntity();
//    floor.setFloor_id(body.getFloorId());
//    Optional<FloorEntity> duplicatedFloor = this.floorRepository.findByFloorId(body.getFloorId());
//    duplicatedFloor.ifPresent(f -> {
//      throw new CustomException(ErrorMessage.DUPLICATE_FLOOR);
//    });
//    this.floorRepository.save(floor);
//
//    // LocationResDto 객체 생성 및 값 설정
//    LocationResDto.Message locationResDto = new LocationResDto.Message();
//    locationResDto.setMessage("로케이션 : " + body.getLocationId() + " -> 등록 완료");
//
//    return ResponseEntity.ok(locationResDto);
//  }
//
//  public ResponseEntity<ProductResDto.Message> areaDelete(Long areaId) {
//
//    //1. productId로 해당 product 검색
//    //2. 해당 productId를 repository에서 update로 해당 정보 비활성화(is_valid=false로 변경하기) 진행
//    //3. productId repo에 저장
//    //4. ResponseEntity로 ok값 반환하기.
//
////    this.productRepository.deleteProductById(productId);
////
////    Optional<ProductEntity> OptProductNameByProductId = this.productRepository.productNameByProductId(
////        productId);
////    ProductEntity ProductNameByProductId = OptProductNameByProductId.orElseThrow(
////        () -> new CustomException(ErrorMessage.NOT_FOUND_PRODUCT));
////    String productName = ProductNameByProductId.getProductName();
////
////    ProductResDto.Message success = new ProductResDto.Message();
////    success.setProductId(productId);
////    success.setProductName(productName);
////    success.setStatus("(삭제 완료) ->" + " 상품명: " + productName);
//
//    return null;
//  }
//
//
//  public ResponseEntity<ProductResDto.Message> rackDelete(Long rackId) {
//
//    return null;
//  }
//
//  public ResponseEntity<ProductResDto.Message> floorDelete(Long floorId) {
//
//    return null;
//  }
//
//  public ResponseEntity<ProductResDto.Message> locationUpdate(Long productId, Long rackId,
//      Long areaId, Long floorId) {
//
//    return null;
//  }

//}
//;


