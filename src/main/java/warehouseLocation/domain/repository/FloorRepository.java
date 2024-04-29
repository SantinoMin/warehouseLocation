package warehouseLocation.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.AreaEntity;
import warehouseLocation.models.FloorEntity;

@Repository
public interface FloorRepository extends JpaRepository<FloorEntity, Integer> {

  //area(구역) 검색
//  @Query("SELECT a FROM AreaEntity a WHERE a.areaId = :areaId")
//  List<AreaEntity> (@Param("areaId") Long areaId);




}
