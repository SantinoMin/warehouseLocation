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