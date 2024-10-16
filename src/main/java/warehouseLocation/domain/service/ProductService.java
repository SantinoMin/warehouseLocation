package warehouseLocation.domain.service;

import jakarta.transaction.Transactional;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import warehouseLocation.domain.dto.LocationReqDto;
import warehouseLocation.domain.dto.LocationResDto;
import warehouseLocation.domain.dto.ProductReqDto;
import warehouseLocation.domain.dto.ProductResDto;
import warehouseLocation.domain.repository.*;
import warehouseLocation.global.utills.response.error.CustomException;
import warehouseLocation.global.utills.response.error.ErrorMessage;
import warehouseLocation.models.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<ProductResDto.Message> softDeleteProduct(Long productId) {

//    //1. productId로 해당 product 검색
//    //2. 해당 productId를 repository에서 update로 해당 정보 비활성화(is_valid=false로 변경하기) 진행
//    //3. productId repo에 저장
//    //4. ResponseEntity로 ok값 반환하기.

        //메시지 정보 넣을 객체
        ProductResDto.Message success = new ProductResDto.Message();

        //찾기만 하고, repository에서 update 하는 작업 안되어있음 --> 여기서부터 10/17(목) 이어서

        //productName를 찾기 (message에 상품명도 보여주기 위해)
        Optional<ProductEntity> productInfo = this.productRepository.productNameByProductId(productId);

        // 업데이트로 진행
        this.productRepository.softDeleteProductByProductId(productId);

        productInfo.ifPresent(p -> {
            success.setProductId(productId);
            success.setProductName(p.getProductName());
            success.setStatus("(비활성화 완료) ->  " + "상품명: " + p.getProductName());
        });

        return ResponseEntity.ok(success);
    }


    public ProductResDto.Register productRegister(ProductReqDto body) {

        // 1. 상품 중복 확인
        Optional<ProductEntity> duplicateProductOpt = this.productRepository.duplicateProductByProductName(
                body.getProductName());

        duplicateProductOpt.ifPresent(p -> {
            throw new CustomException("해당 상품명은 이미 존재합니다.");
        });

        // 2. 상품 DB에 저장
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        ProductEntity product = new ProductEntity();
        product.setProductName(body.getProductName());
        product.setExpiredDate(body.getExpiredDate());
        product.setImageUrl(body.getImageUrl());
        product.setPrice(body.getPrice());
        product.setCreatedAt(createdAt);
        product.setUpdatedAt(updatedAt);
        product.setValid(true);
        product.setCategoryId(body.getCategoryId());

        this.productRepository.save(product);

        System.out.println("product = " + product);

        // 3. 사용자가 카테고리 리스트에서 카테고리 선택하기

        // 4. 등록 완료 시, 등록 요청 상품 내역 반환.

        ProductResDto.Register toDto = new ProductResDto.Register();
        toDto.setProductName(product.getProductName());
        toDto.setProductId(product.getProductId());
        toDto.setStatus("등록 완료 되었습니다");
        toDto.setCreatedAt(createdAt);

        return toDto;
    }


    public ProductResDto.CategoryList categoryList() {


        List<CategoryEntity> categoryList = this.categoryRepository.findAll();

        List<String> categoryNameList = categoryList.stream().map(CategoryEntity::getCategoryName)
                .toList();

        return ProductResDto.CategoryList.builder().categoryNameList(categoryNameList).build();
    }

    public List<ProductResDto.Area> areaList() {

        List<AreaEntity> areaEntityList = this.areaRepository.findAll();

        List<ProductResDto.Area> areaList = areaEntityList.stream()
                .map(areaInfo -> new ProductResDto.Area(areaInfo.getAreaId(), areaInfo.getAreaName(), areaInfo.getStatus()))
                .toList();

        return areaList;
    }

    public List<ProductResDto.Rack> rackList() {


        List<RackEntity> rackEntityList = this.rackRepository.findAll();

        List<ProductResDto.Rack> rackList = rackEntityList.stream().map(r -> new ProductResDto.Rack(r.getRackId(), r.getRackNumber(), r.getStatus())).toList();

        return rackList;
    }


    public List<ProductResDto.Floor> floorList() {

        List<FloorEntity> floorEntity = this.floorRepository.findAll();

        List<ProductResDto.Floor> floorList = floorEntity.stream().map(f -> new ProductResDto.Floor(f.getFloor_id(), f.getFloor_number(), f.getStatus())).toList();

        return floorList;
    }

    @Transactional
    public ResponseEntity<LocationResDto.Message> addArea(LocationReqDto body) throws Exception {

        // 우선 이미 사용 중인 areaName인지 확인하고, 사용가능한 area만 Return하기
        LocalDateTime createdAt = LocalDateTime.now();

        String areaName = body.getAreaName();

        if (areaRepository.findIdByAreaName(areaName) == null) {
            AreaEntity addArea = new AreaEntity();
            addArea.setAreaName(body.getAreaName());
            addArea.setCreatedAt(createdAt);
            addArea.setStatus(1);
            return ResponseEntity.badRequest().body(new LocationResDto.Message("등록 완료"));
        } else {
            return ResponseEntity.badRequest().body(new LocationResDto.Message("이미 등록되어 있는 area입니다."));
//            throw new Exception("이미 등록되어 있는 area입니다.");
        }

    }
};


// +등록하기


//        Optional<AreaEntity> duplicatedArea = this.areaRepository.findByAreaId(body.getAreaId());
//        duplicatedArea.ifPresent(a -> {
//            throw new CustomException(ErrorMessage.DUPLICATE_AREA);
//        });
//        this.areaRepository.save(area);
//        return null;
//    }
//};

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


