/**
 * 해당 방법보다 yml에 작성해서 진행 중
 * => yml이 더 효율적이라 판단.
 * 1)configuration으로 작성 시,
 * controller,dto부분이 작성 완료 되어야만, 프론트와 소통이 가능함
 * 2)yml로 작성 시,
 * dto, controller부분 작성이 완료 안 되더라도, 빠르게 프론트와 소통이 가능함.
 */

//package warehouseLocation.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//    import org.springframework.context.annotation.Bean;
//    import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class OpenAPIConfiguration {
//
//  private static final String API_NAME = "Santino API";
//  private static final String API_VERSION = "1.0.0";
//  private static final String API_DESCRIPTION = "Springboot Repository!";
//  @Bean
//  public OpenAPI openAPIConfig() {
//    return new OpenAPI()
//        .info(new Info()
//            .title(API_NAME)
//            .description(API_DESCRIPTION)
//            .version(API_VERSION));
//  }
//}

//  @Bean
//  public GroupedOpenApi publicApi() {
//    String[] paths = {"/main/**"};
//    return GroupedOpenApi.builder()
//        .group("main page")
//        .pathsToMatch(paths)
//        .build();
//  }
//
//  @Bean
//  public GroupedOpenApi NaverApi() {
//    String[] paths = {"/product/**"};
//    return GroupedOpenApi.builder()
//        .group("Product Api")
//        .pathsToMatch(paths)
//        .build();
//  }
//
//  @Bean
//  public GroupedOpenApi gRpcApi() {
//    String[] paths = {"/gRPC/**"};
//    return GroupedOpenApi.builder()
//        .group("gRPC-api")
//        .pathsToMatch(paths)
//        .build();
//  }
//}