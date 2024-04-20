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
  private int productId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "expired_date", nullable = false)
  private LocalDateTime expiredDate;

  @Column(name = "image_url", nullable = false)
  private String imageUrl;

  @Column(name = "price", nullable = false)
  private int price;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @Column(name = "deleted_at", nullable = false)
  private LocalDateTime deletedAt;

  @Column(name = "status", nullable = false)
  private String status;


  ProductEntity toDto = new ProductEntity();


};
//여기부터 이어서 하기
//Entity값은 받아왔는데, dto로 변경 후에, repository에 .save()하고,
//return type으로 해당 dto타입에 맞게 해야함.
