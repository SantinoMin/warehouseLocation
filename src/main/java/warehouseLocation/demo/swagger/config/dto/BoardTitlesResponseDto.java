//package warehouseLocation.demo.swagger.config.dto;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import java.util.List;
//import lombok.Builder;
//
//// tag 추가
//@Tag(name = "board title response dto 객체", description = "board title API를 요청 처리가 완료되었을 때 응답되는 객체")
//@Builder
//public record BoardTitlesResponseDto(
//    // schema 추가
//    @Schema(title = "게시판 제목 목록", description = "필터 처리가 수행되며 조회된 게시판 제목 목록")
//    List<String> titles
//) {
//}