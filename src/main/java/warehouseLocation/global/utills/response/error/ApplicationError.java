package warehouseLocation.global.utills.response.error;

import lombok.Getter;

@Getter
public class ApplicationError {

  private String status;
  private String message;
  private String productName;

  public void setStatus(String status) {
    this.status = status;
  }

  public void setMessage(String message) {
    this.message = message;
  }

//  public void setProductName(String productName) {
//    this.productName = productName;
//  }
}