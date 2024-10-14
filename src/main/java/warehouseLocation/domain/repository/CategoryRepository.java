package warehouseLocation.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.CategoryEntity;
import warehouseLocation.models.ProductEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

  @Query("SELECT c.categoryName FROM CategoryEntity c")
  Page<String> findAllCategoryNames(Pageable pageable);

//  Optional<CategoryEntity> findFirstByCategoryName();

  /**
   * 카테고리 리스트 검색하
   **/

  //카테고리 이름 찾기
  @Query("SELECT c.categoryName FROM CategoryEntity c WHERE c.categoryId = :categoryId")
  Optional<CategoryEntity> categoryNameByCategoryId(@Param("categoryId") Long categoryId);

}
