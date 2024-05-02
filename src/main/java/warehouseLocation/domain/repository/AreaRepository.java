package warehouseLocation.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.AreaEntity;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Integer> {

//  area(구역) 검색
  @Query("SELECT a FROM AreaEntity a WHERE a.areaId = :areaId")
  AreaEntity findByAreaId(@Param("areaId") Long areaId);


}
