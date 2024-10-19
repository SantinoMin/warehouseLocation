package warehouseLocation.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "rack")
public class RackEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rack_id", nullable = false, unique = true)
  private Long rackId;

  @Column(name = "rack_number", nullable = false)
  private Long rackNumber;

  @Column(name = "created_at", nullable = true)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = true)
  private LocalDateTime updatedAt;

  @Column(name = "deleted_at", nullable = true)
  private LocalDateTime deletedAt;

  @Column(name = "status", nullable = false)
  private int status;
  };
