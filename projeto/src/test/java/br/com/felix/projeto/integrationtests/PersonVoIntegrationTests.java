package br.com.felix.projeto.integrationtests;

import br.com.felix.projeto.unitests.config.TestsConfigs;
import br.com.felix.projeto.unitests.integration.testcontainers.AbstractIntegrationTests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonVoIntegrationTests extends AbstractIntegrationTests {

	private static RequestSpecification specification;
	private static ObjectMapper objectMapper;

	private static PersonVo person;

	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

		person = new PersonVo();


	}

	@Test
	@Order(1)
	public void testCreate() throws JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_PROJETO)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();


		var content = given().spec(specification)

				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		PersonVo createdPersonVo = objectMapper.readValue(content, PersonVo.class);
		person = createdPersonVo;

		assertNotNull(createdPersonVo);
		assertNotNull(createdPersonVo.getId());
		assertNotNull(createdPersonVo.getFirstName());
		assertNotNull(createdPersonVo.getLastName());
		assertNotNull(createdPersonVo.getAddress());
		assertNotNull(createdPersonVo.getGender());

		assertTrue(createdPersonVo.getId() > 0);

		assertEquals("Tadeu", createdPersonVo.getFirstName());
		assertEquals("Pereira", createdPersonVo.getLastName());
		assertEquals("Ubatuba", createdPersonVo.getAddress());
		assertEquals("Male", createdPersonVo.getGender());

	}

	@Test
	@Order(2)
	public void testCreateWithWrongOrigin() throws JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_PROJETO)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();


		var content = given().spec(specification)

				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();

		assertNotNull(content);
		assertEquals("Male", content);

	}
	@Test
	@Order(3)
	public void testFindById() throws JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_PROJETO)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();


		var content = given().spec(specification)

				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", person.getId())
				.when()
			     	.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();

		assertNotNull(content);
		assertEquals("Invalid Cors Request", content);

	}
	@Test
	@Order(4)
	public void testFindByIdWrongOrigin() throws JsonProcessingException {
		mockPerson();

		specification = new RequestSpecBuilder()
				.addHeader(TestsConfigs.HEADER_PARAM_ORIGIN, TestsConfigs.ORIGIN_PROJETO)
				.setBasePath("/api/person/v1")
				.setPort(TestsConfigs.SERVER_PORT)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();


		var content = given().spec(specification)

				.contentType(TestsConfigs.CONTENT_TYPE_JSON)
				.pathParam("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();

		assertNotNull(content);
		assertEquals("Invalid Cors Request", content);

	}

	private void mockPerson() {
		person.setFirstName("Tadeu");
		person.setLastName("Pereira");
		person.setAddress("Ubatuba");
		person.setGender("Male");

	}

}
