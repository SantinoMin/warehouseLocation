package warehouseLocation.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
public class Product {
    private Long productId;
    private String productName;
    private Long productPrice;

    public Product() {
    }



    public Product(Long productId, String productName, Long productPrice){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

//    Product productInfo1 = new Product();
//    productInfo1.setProductId(productId);
//    productInfo1.setProductName(productName);

    void a() {}
    void a(String aa) {}
}
