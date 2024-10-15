package warehouseLocation.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.AreaEntity;
import warehouseLocation.models.RackEntity;

@Repository
public interface RackRepository extends JpaRepository<RackEntity, Integer> {

  //rack(랙) 검색
  @Query("SELECT r FROM RackEntity r WHERE r.rackId = :rackId")
  Optional<RackEntity> findByRackId(@Param("rackId") Long rackId);

  //(rackNum찾기) findRackNameByRackId
  @Query("SELECT r.rackNumber FROM RackEntity r WHERE r.rackId =:rackId")
  Optional<RackEntity> findRackNumByRackId(@Param("areaId") Long areaId);


}
