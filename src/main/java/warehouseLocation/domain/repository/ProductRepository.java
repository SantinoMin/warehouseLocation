package warehouseLocation.domain.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.AreaEntity;
import warehouseLocation.models.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

  /**
   * 상품 검색
   **/

  @Query("SELECT p FROM ProductEntity p WHERE p.productId = :productId")
  Optional<ProductEntity> productNameByProductId(@Param("productId") Integer productId);

  @Query("SELECT p FROM ProductEntity p WHERE p.productId = :productId")
  ProductEntity productInfoByProductId(@Param("productId") Integer productId);

  @Query("SELECT p FROM ProductEntity p WHERE p.productName = :productName")
  Optional<ProductEntity> productIdByProductName(@Param("productName") String productName);

  // productId로 categoryId 찾기
  @Query("SELECT p.categoryId FROM ProductEntity p WHERE p.productId = :productId")
  Optional<Long> categoryIdByProductId(@Param("productId") Integer productId);

  //to find categoryIdList
  @Query("SELECT p.categoryId FROM ProductEntity p WHERE p.productId IN :productIdList")
   List<Long> categoryIdListByProductIdList(@Param("productIdList")List<Long> productIdList);


  @Query("SELECT p.productName FROM ProductEntity p WHERE p.productName = :productName")
  Optional<ProductEntity> duplicateProductByProductName(@Param("productName") String productName);

  //상품 업데이트
  @Query("SELECT p FROM ProductEntity p WHERE p.productId = :productId")
  ProductEntity ProductInfoByProductId(@Param("productId") Long productId);

  //메서드 category
  @Query("SELECT p FROM ProductEntity p WHERE p.productName LIKE %:productName% OR :productName LIKE CONCAT('%', p.productName, '%')")
  List<ProductEntity> byProductName(@Param("productName") String productName);


  @Query("SELECT p FROM ProductEntity p WHERE p.productName LIKE %:productName% OR :productName LIKE CONCAT('%', p.productName, '%')")
  Optional<ProductEntity> ProductIdByproductName(@Param("productName") String productName);


  // area(구역) 삭제(업데이트로 진행)
  @Transactional
  @Modifying
  @Query("UPDATE ProductEntity p SET p.isValid = false WHERE p.productId = :productId")
  Optional<ProductEntity> softDeleteProductByProductId(@Param("productId") Integer productId);


}
