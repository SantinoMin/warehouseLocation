package warehouseLocation.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.AreaEntity;
import warehouseLocation.models.RackEntity;

@Repository
public interface RackRepository extends JpaRepository<RackEntity, Integer> {

  //area(구역) 검색
//  @Query("SELECT a FROM AreaEntity a WHERE a.areaId = :areaId")
//  List<AreaEntity> (@Param("areaId") Long areaId);




}
