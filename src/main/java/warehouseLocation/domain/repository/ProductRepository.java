package warehouseLocation.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.domain.dto.ProductReqDto;
import warehouseLocation.domain.dto.ProductResDto.Location;
import warehouseLocation.models.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {


  /**
   * 상품 검색
   **/
  @Query("SELECT p FROM ProductEntity p WHERE p.productName = :productName")
  Optional<ProductEntity> searchProduct(@Param("productName") String productName);

  @Query("SELECT p FROM ProductEntity p WHERE p.productId = :productId")
  ProductEntity productInfoById(@Param("productId") Long productId);

  @Query("SELECT p FROM ProductEntity p WHERE p.productId = :productId")
  List<Location> productId(@Param("productId") Long productId);
}
