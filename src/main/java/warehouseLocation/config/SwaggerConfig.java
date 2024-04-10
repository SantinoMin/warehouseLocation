package warehouseLocation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("abc")
            .description("bbb")
            .version("v1.0"));
  }

  @Bean
  public GroupedOpenApi publicApi() {
    String[] paths = {"/main/**"};
    return GroupedOpenApi.builder()
        .group("main page")
        .pathsToMatch(paths)
        .build();
  }

  @Bean
  public GroupedOpenApi NaverApi() {
    String[] paths = {"/product/**"};
    return GroupedOpenApi.builder()
        .group("Product Api")
        .pathsToMatch(paths)
        .build();
  }

  @Bean
  public GroupedOpenApi gRpcApi() {
    String[] paths = {"/gRPC/**"};
    return GroupedOpenApi.builder()
        .group("gRPC-api")
        .pathsToMatch(paths)
        .build();
  }
}