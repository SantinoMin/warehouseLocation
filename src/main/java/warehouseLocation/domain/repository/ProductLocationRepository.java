package warehouseLocation.domain.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.ProductEntity;
import warehouseLocation.models.ProductLocationEntity;

@Repository
public interface ProductLocationRepository extends JpaRepository<ProductLocationEntity, Integer> {


  /**
   *
   **/
  @Query("SELECT pl FROM ProductLocationEntity pl WHERE pl.productId = :productId")
  Optional<ProductLocationEntity> productLocation(@Param("productId") Long productId);

}
