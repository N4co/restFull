package br.com.felix.projeto;

import br.com.felix.projeto.unitests.config.TestsConfigs;
import br.com.felix.projeto.unitests.integration.testcontainers.AbstractIntegrationTests;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerApplicationTests extends AbstractIntegrationTests {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content =
		given()
		.basePath("/swagger-ui/index.html")
				.port(TestsConfigs.SERVER_PORT)
				.when()
			    	.get()
				.then()
			     	.statusCode(200)
				.extract()
				   .body()
				         .asString();

		assertTrue(content.contains("Swagger UI"));
	}

}
