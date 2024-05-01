package warehouseLocation.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {


  /**
   * 카테고리 리스트 검색하
   **/
//  @Query("SELECT c FROM CategoryEntity c WHERE c.categoryId = :categoryId")
//  CategoryEntity categoryIdByUserId(@Param("categoryId") Long categoryId);

}
