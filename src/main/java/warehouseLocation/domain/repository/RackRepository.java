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

  @Query("SELECT r.rackNumber FROM RackEntity r WHERE r.rackId =:rackId")
  Long findRackNumByRackId(@Param("rackId") Long rackId);

  @Query("SELECT r FROM RackEntity r WHERE r.rackNumber = :rackNumber")
  Optional<RackEntity> findRackByRackNumber(@Param("rackNumber") Long rackNumber);


}
