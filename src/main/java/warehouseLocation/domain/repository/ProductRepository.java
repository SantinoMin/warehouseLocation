package warehouseLocation.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.ProductEntity;

@Repository
public interface ProductRepository {


  /**
   * 상품 검색
   **/
  @Query("SELECT p FROM ProductEntity p WHERE p.productName = :productName")
  Optional<ProductEntity> searchProduct(@Param("productName") String productName);
}
