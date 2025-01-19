//package warehouseLocation.domain.repository;
//
//import java.util.Optional;
//
//import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import warehouseLocation.models.AreaEntity;
//import warehouseLocation.models.RackEntity;
//
//@Repository
//public interface RackRepository extends JpaRepository<RackEntity, Integer> {
//
//  @Query("SELECT r.rackNumber FROM RackEntity r WHERE r.rackId =:rackId")
//  Long findRackNumByRackId(@Param("rackId") Long rackId);
//
//  @Query("SELECT r FROM RackEntity r WHERE r.rackNumber = :rackNumber")
//  Optional<RackEntity> findRackByRackNumber(@Param("rackNumber") Long rackNumber);
//
//  // rack(구역) 삭제(업데이트로 진행)
//  @Transactional
//  @Modifying
//  @Query("UPDATE RackEntity r SET r.isValid = false WHERE r.rackId = :rackId")
//  int softDeleteRackByRackId(@Param("rackId") Long rackId);
//
//
//  //(areaName찾기) findAreaNameByAreaId
//  @Query("SELECT r.rackNumber FROM RackEntity r WHERE r.rackId =:rackId")
//  String findRackNumberByRackId(@Param("rackId") Long rackId);
//
//}
