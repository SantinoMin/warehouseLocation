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
@Table(name = "product")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false, unique = true)
  private int product_id;

  @Column(name = "product_name", nullable = false)
  private String product_name;

  @Column(name = "expired_date", nullable = false)
  private LocalDateTime expired_date;

  @Column(name = "image_url", nullable = false)
  private String image_url;

  @Column(name = "price", nullable = false)
  private int price;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime created_at;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updated_at;

  @Column(name = "deleted_at", nullable = false)
  private LocalDateTime deleted_at;

  @Column(name = "status", nullable = false)
  private String status;
  };
