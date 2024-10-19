package warehouseLocation.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import warehouseLocation.models.AreaEntity;
import warehouseLocation.models.FloorEntity;
import warehouseLocation.models.RackEntity;

@Repository
public interface FloorRepository extends JpaRepository<FloorEntity, Integer> {

  @Query("SELECT f FROM FloorEntity f WHERE f.floor_number = :floorNumber")
  Optional<FloorEntity> findFloorByFloorNumber(@Param("floorNumber") Long floorNumber);


  //(floorName 찾기) findFloorNameByFloorId
  @Query("SELECT f.floor_number FROM FloorEntity f WHERE f.floor_id =:floorId")
  Long findFloorNumByFloorId(@Param("floorId") Long floorId);

}
