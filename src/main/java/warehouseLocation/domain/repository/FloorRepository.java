package warehouseLocation.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.AreaEntity;
import warehouseLocation.models.FloorEntity;

@Repository
public interface FloorRepository extends JpaRepository<FloorEntity, Integer> {

  //floor(층) 검색
  @Query("SELECT f FROM FloorEntity f WHERE f.floor_id = :floorId")
  Optional<FloorEntity> findByFloorId(@Param("floorId") Long floorId);

}
