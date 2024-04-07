package warehouseLocation.demo.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
@Table(name = "location")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private int id;

  @Column(name = "productName", nullable = false)
  private String productName;

  @Column(name = "location", nullable = false)
  private String location;

  @Column(name = "expiredDate", nullable = false)
  private LocalDate expiredDate;

  @Column(name = "registerDate", nullable = false)
  private LocalDate registerDate;

  @Column(name = "updateDate", nullable = false)
  private LocalDate updateDate;

  };
