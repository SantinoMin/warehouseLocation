package warehouseLocation.domain.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.AreaEntity;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntity, Long> {

//  area(구역) 검색
  @Query("SELECT a FROM AreaEntity a WHERE a.areaId = :areaId")
  Optional<AreaEntity> findByAreaId(@Param("areaId") Long areaId);

  @Query("SELECT a FROM AreaEntity a WHERE a.areaName = :areaName")
  List<AreaEntity> findIdByAreaName(@Param("areaName") String areaName);


  // areaId 찾기
  @Query("SELECT a FROM AreaEntity a WHERE a.areaName = :areaName")
  Optional<AreaEntity> areaIdByAreaName (@Param("areaName") String areaName);


  //(areaName찾기) findAreaNameByAreaId
  @Query("SELECT a.areaName FROM AreaEntity a WHERE a.areaId =:areaId")
  String findAreaNameByAreaId(@Param("areaId") Long areaId);

}
