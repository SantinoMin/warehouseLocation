//package warehouseLocation.demo.swagger.config.controller;
//
//import io.swagger.v3.oas.annotations.tags.Tag;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import warehouseLocation.demo.swagger.config.dto.BoardTitlesRequestDto;
//import warehouseLocation.demo.swagger.config.dto.BoardTitlesResponseDto;
//
//// tag 추가
//@Tag(name = "board controller api", description = "게시판에 사용되는 API")
//@RestController
//@RequestMapping("/app")
//public class BoardController {
//
//  @PostMapping("/main")
//  public ResponseEntity<BoardTitlesResponseDto> getTitles(
//      BoardTitlesRequestDto boardTitlesRequestDto) {
//    List<String> titles = Arrays.asList(
//        "첫 번째 게시글 제목",
//        "블로그에 대한 공지사항을 알려드립니다.",
//        "누구나 작성할 수 있는 블로그",
//        "블로그의 모든 것"
//    );
//
//    return ResponseEntity.ok(
//        BoardTitlesResponseDto
//            .builder()
//            .titles(
//                titles
//                    .stream()
//                    .filter(title -> title.contains(boardTitlesRequestDto.title()))
//                    .collect(Collectors.toList())
//            )
//            .build()
//    );
//  }
//}