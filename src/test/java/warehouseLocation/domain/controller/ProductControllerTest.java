//package warehouseLocation.domain.controller;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import warehouseLocation.domain.dto.ProductResDto;
//import warehouseLocation.domain.service.ProductService;
//import warehouseLocation.models.ProductEntity;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ProductController.class)
//public class ProductControllerTest {
//
////    @MockBean
////    private ProductService productService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @DisplayName("/product, 상품 검색")
//    void getSearchTest() throws Exception {
//
//    /*
//    given
//     */
//        ProductEntity product = ProductEntity.builder()
//                .productName("펩시 콜라")
//                .productId(9L) // ID는 Long 타입으로 가정
//                .categoryId(4L)
//                .expiredDate(LocalDate.from(LocalDateTime.parse("2024-04-28T16:30:56")))
//                .imageUrl("https://www.google.com/pepsi1, https://www.google.com/pepsi2, https://www.google.com/pepsi3")
//                .price("1,300원")
//                .createdAt(LocalDateTime.parse("2024-04-28T16:30:56"))
//                .updatedAt(LocalDateTime.parse("2024-04-28T16:30:56"))
//                .status("for sale")
////                .location(ProductEntity.Location.builder()
////                        .areaName("D")
////                        .rackNumber(12)
////                        .floorNumber(1)
////                        .build())
//                .build();
//
//    /*
//    when, then
//     */
//        // Assertions
//        Assertions.assertThat(product.getProductName()).isEqualTo("펩시 콜라"); // 기대하는 값: "펩시 콜라"
//        Assertions.assertThat(product.getProductId()).isEqualTo(9L); // 기대하는 값: 9L
//        Assertions.assertThat(product.getCategoryId()).isEqualTo(4L); // 기대하는 값: 4L
//        Assertions.assertThat(product.getExpiredDate()).isEqualTo(LocalDate.of(2024, 4, 26)); // 기대하는 값: LocalDate.of(2024, 4, 26)
//        Assertions.assertThat(product.getImageUrl()).isEqualTo("https://www.google.com/pepsi1, https://www.google.com/pepsi2, https://www.google.com/pepsi3");
//        Assertions.assertThat(product.getPrice()).isEqualTo("1,300원");
//        Assertions.assertThat(product.getCreatedAt()).isEqualTo(LocalDateTime.parse("2024-04-28T16:30:56"));
//        Assertions.assertThat(product.getUpdatedAt()).isEqualTo(LocalDateTime.parse("2024-04-28T16:30:56"));
//        Assertions.assertThat(product.getStatus()).isEqualTo("for sale");
//    }
//
//
//    }
//
//
//
//
////    @Test
////    @DisplayName("/product, 상품 검색")
////    void getSearchTest() throws Exception {
////        // 테스트할 데이터 생성
////        List<ProductResDto.ProductSearch> mockProducts = new ArrayList<>();
////        mockProducts.add(new ProductResDto.ProductSearch("코카 콜라", 11, "식품", "2024-04-29", "1,650원", "https://www.google.com/pepsi11"));
////
////        // Mocking the service method
////        when(productService.search("콜라")).thenReturn(mockProducts);
////
////        // MockMvc를 사용하여 GET 요청을 수행하고 결과를 검증
////        mockMvc.perform(get("/product")
////                        .param("productName", "콜라")
////                        .accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$[0].productName").value("코카 콜라"))
////                .andExpect(jsonPath("$[0].productId").value(11))
////                .andExpect(jsonPath("$[0].categoryName").value("식품"));
////    }
////}