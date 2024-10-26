package warehouseLocation;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import warehouseLocation.domain.service.ProductServiceTest;
import warehouseLocation.models.ProductEntity;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
class WarehouseLocationApplicationTests {

//	@BeforeEach
//	@AfterEach
//	@BeforeAll
//	@AfterAll

	@Autowired
	private ProductServiceTest productService;

	@Test
	void contextLoads() {
	}


}
