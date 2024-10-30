package warehouseLocation.domain.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import warehouseLocation.domain.dto.*;
import warehouseLocation.domain.repository.*;
import warehouseLocation.global.utills.response.error.CustomException;
import warehouseLocation.global.utills.response.error.ErrorMessage;
import warehouseLocation.models.*;

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
         * @Test
         * 1) 검색한 값이 없을 경우
         * 2) (완료)빈값이 제출 되었을 경우 -> 필수값 메시지 띄움
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

            product.setProductName(prod.getProductName());
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


    // todo categoryName이 db에 안들어감, 그 외 정보들도 response는 받지만, db에는 안 들어감
    public ProductResDto.Edit productEdit(@PathVariable Long productId, @RequestBody ProductReqDto.Edit body) {


        // 1. parameter의 productId를 가지고, product Table에서 값 조회
        // 2. 사용자가 body에 입력한 값을 다시 product에 저장해주기

        //   LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        // db에서 productId로 변경할 상품 검색
        ProductEntity productInfo = this.productRepository.ProductInfoByProductId(productId);

        //categoryName 구하기
//        Optional<CategoryEntity> categoryNameOpt = this.categoryRepository.categoryNameByCategoryId(productInfo.getCategoryId());
//        String categoryName = categoryNameOpt.map(CategoryEntity::getCategoryName).orElse(null);

//        CategoryEntity categoryInfo = new CategoryEntity();
//        categoryNameOpt.ifPresent(c -> {
//            categoryInfo.setCategoryName(c.getCategoryName());
//        });

        String categoryName = body.getCategoryName();


        // Location 정보 구하기
        Optional<ProductLocationEntity> productLocationOpt = this.productLocationRepository.productLocationIdByProductId(productId);

        // todo ???
        ProductLocationEntity ple = productLocationOpt.orElse(new ProductLocationEntity());


        String areaName = this.areaRepository.findAreaNameByAreaId(ple.getAreaId());
        Long rackNumber = this.rackRepository.findRackNumByRackId(ple.getRackId());
        Long floorNumber = this.floorRepository.findFloorNumByFloorId(ple.getFloorId());

        ProductResDto.Location locationRD = new ProductResDto.Location();
        locationRD.setAreaName(areaName);
        locationRD.setRackNumber(rackNumber);
        locationRD.setFloorNumber(floorNumber);

        // body에서 입력한 값으로 productInfo 업데이트
        productInfo.setProductName(body.getProductName());
        productInfo.setCategoryName(categoryName);
        productInfo.setImageUrl(body.getImageUrl());
        productInfo.setExpiredDate(body.getExpiredDate());
        productInfo.setPrice(body.getPrice());
        productInfo.setStatus(body.getStatus());
        productInfo.setUpdatedAt(updatedAt);

        // db에 저장
        this.productRepository.save(productInfo);


        // 변경 값 저장할 객체 생성
        ProductResDto.Edit updateProduct = new ProductResDto.Edit();
        updateProduct.setProductName(body.getProductName());

        updateProduct.setCategoryName(categoryName);

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


    //    !! 상품 중복 등록시, 에러 안뜨고 바로 500 서버 뜨는데?
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
                .map(areaInfo -> new ProductResDto.Area(areaInfo.getAreaId(), areaInfo.getAreaName(), areaInfo.getIsValid()))
                .toList();

        return areaList;
    }

    public List<ProductResDto.Rack> rackList() {


        List<RackEntity> rackEntityList = this.rackRepository.findAll();

        List<ProductResDto.Rack> rackList = rackEntityList.stream().map(r -> new ProductResDto.Rack(r.getRackId(), r.getRackNumber(), r.getIsValid())).toList();

        return rackList;
    }


    public List<ProductResDto.Floor> floorList() {

        List<FloorEntity> floorEntity = this.floorRepository.findAll();

        List<ProductResDto.Floor> floorList = floorEntity.stream().map(f -> new ProductResDto.Floor(f.getFloor_id(), f.getFloor_number(), f.getStatus())).toList();

        return floorList;
    }

    @Transactional
    public ResponseEntity<Message> addArea(AreaReqDto body) {

        // 우선 이미 사용 중인 areaName인지 확인하고, 사용가능한 area만 Return하기
        Optional<AreaEntity> checkDuplicateArea = areaRepository.findAreaByAreaName(body.getAreaName());

        if (checkDuplicateArea.isPresent()) {

            return ResponseEntity.badRequest().body(new Message("이미 등록되어 있는 area입니다."));
        }

        AreaEntity addArea = new AreaEntity();
        addArea.setAreaName(body.getAreaName());
        addArea.setCreatedAt(LocalDateTime.now());
        addArea.setIsValid(true);
        this.areaRepository.save(addArea);

        return ResponseEntity.ok(new Message("등록 완료"));
    }

    // TODO Rack이 숫자형이 아니라, 문자형일 경우에 에러 띄우는 법
    // 현재 숫자로는 등록 가능
    public ResponseEntity<Message> addRack(RackReqDto body) {
        /**
         * Rack 중복 확인 후, 중복 아니라면 repo에 저장하기
         */

        Optional<RackEntity> checkDuplicateRack = rackRepository.findRackByRackNumber(body.getRackNumber());

        if (checkDuplicateRack.isPresent()) {

            return ResponseEntity.badRequest().body(new Message("이미 등록되어 있는 rackNumber 입니다."));
        }

        RackEntity addRack = new RackEntity();
        addRack.setRackNumber(body.getRackNumber());
        addRack.setCreatedAt(LocalDateTime.now());
        addRack.setIsValid(true);
        this.rackRepository.save(addRack);

        return ResponseEntity.ok(new Message("등록 완료"));
    }


    public ResponseEntity<Message> addFloor(FloorReqDto body) {


        Optional<FloorEntity> checkDuplicateFloor = floorRepository.findFloorByFloorNumber(body.getFloorNumber());

        if (checkDuplicateFloor.isPresent()) {

            return ResponseEntity.badRequest().body(new Message("이미 등록되어 있는 floorNumber 입니다."));
        }

        FloorEntity addFloor = new FloorEntity();
        addFloor.setFloor_number(body.getFloorNumber());
        addFloor.setCreated_at(LocalDateTime.now());
        addFloor.setStatus(1);
        this.floorRepository.save(addFloor);

        return ResponseEntity.ok(new Message("등록 완료"));
    }


    //사용자가 입력한 area인지? 아니면 리스트에서 보는건지 --> rackList에서 찾아서 해당 areaId를 삭제하는 걸로 이해.
    //만약에 이미 삭제된 area라면, "이미 삭제된 area입니다" 라는 메시지 필요
    public ResponseEntity<Message> areaDelete(Long areaId) {

        //1. productId로 해당 product 검색
        //2. 해당 productId를 repository에서 update로 해당 정보 비활성화(is_valid=false로 변경하기) 진행
        //3. productId repo에 저장
        //4. ResponseEntity로 ok값 반환하기.

        int areaInfo = this.areaRepository.softDeleteAreaByAreaId(
                areaId);

        //areaName구하기
        String areaName = this.areaRepository.findAreaNameByAreaId(areaId);


        Message success = new Message();
        success.setAreaId(areaId);
        success.setAreaName(areaName);
        success.setIsValid(0L);
        success.setMessage("삭제 완료되었습니다.");

        return ResponseEntity.ok(success);
    }

    public ResponseEntity<Message> rackDelete(Long rackId) {

        int rackInfo = this.rackRepository.softDeleteRackByRackId(
                rackId);

        //areaName구하기
        String rackNumber = this.rackRepository.findRackNumberByRackId(rackId);


        Message success = new Message();
        success.setAreaId(rackId);
        success.setRackNumber(Long.valueOf(rackNumber));
        success.setIsValid(0L);
        success.setMessage("삭제 완료되었습니다.");

        return ResponseEntity.ok(success);


    }
};

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


