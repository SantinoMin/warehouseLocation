package warehouseLocation.demo.models;


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
@Table(name = "area")
public class AreaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "area_id", nullable = false, unique = true)
  private int area_id;

  @Column(name = "area_name", nullable = false)
  private String area_name;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime created_at;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updated_at;

  @Column(name = "deleted_at", nullable = false)
  private LocalDateTime deleted_at;

  @Column(name = "status", nullable = false)
  private String status;
  };
