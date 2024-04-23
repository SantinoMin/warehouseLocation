package warehouseLocation.global.utills.response.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * CustomException에 설정한 ErrorMessage들을 에러 발생 시, user에게 띄워주는 메서드
   */
  @ExceptionHandler(CustomException.class)
  public ResponseEntity<String> handleCustomException(CustomException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }

  /**
   * controller에서 parameters에 key값이 주어지지 않았을 경우, user에게 에러 띄워주는 메서드
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public String handleMissingParameterException(MissingServletRequestParameterException ex) {
    return "Required request parameter '" + ex.getParameterName() + "' is missing.";
  }

  // 다른 예외에 대한 핸들러도 추가할 수 있습니다.
}