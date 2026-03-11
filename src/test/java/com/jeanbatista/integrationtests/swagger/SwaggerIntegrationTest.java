package com.jeanbatista.integrationtests.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jeanbatista.configs.TestConfigs;
import com.jeanbatista.integrationtests.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
    @DisplayName("Should display Swagger UI page")
	public void shouldDisplaySwaggerUiPage() {
		var content = given()
                    .basePath("/swagger-ui/index.html")
                    .port(TestConfigs.SERVER_PORT)
                    .when()
					.get()
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .asString();

		assertTrue(content.contains("Swagger UI"));
	}

    @Test
    @DisplayName("Should return OpenAPI JSON documentation")
    public void shouldReturnOpenApiJson() {
        given()
                .basePath("/v3/api-docs")
                .port(TestConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(TestConfigs.CONTENT_TYPE_JSON);
    }
}
