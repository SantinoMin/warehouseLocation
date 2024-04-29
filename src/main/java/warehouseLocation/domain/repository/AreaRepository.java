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
public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {

  //area(구역) 검색
//  @Query("SELECT a FROM AreaEntity a WHERE a.areaId = :areaId")
//  List<AreaEntity> (@Param("areaId") Long areaId);




}
