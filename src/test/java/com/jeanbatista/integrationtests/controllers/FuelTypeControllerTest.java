package com.jeanbatista.integrationtests.controllers;

import com.jeanbatista.configs.TestConfigs;
import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.integrationtests.AbstractIntegrationTest;
import com.jeanbatista.mocks.FuelTypeMocks;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class FuelTypeControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static FuelTypeResponseDto fuelTypeResponse;

    @BeforeEach
    void setup() {
        specification = new RequestSpecBuilder()
                .setBasePath(TestConfigs.FUEL_TYPE_BASE_PATH)
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Should save a FuelType")
    void shouldSaveAFuelType() {

        FuelTypeRequestDto request = FuelTypeMocks.mockRequestDto("Gasolina", 4.53, true);

        FuelTypeResponseDto response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(FuelTypeResponseDto.class);

        fuelTypeResponse = response;

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getPricePerLiter(), response.getPricePerLiter());

    }

    @Test
    @Order(2)
    @DisplayName("Should find a FuelType by your id")
    void shouldFindAFuelTypeById() {
        FuelTypeResponseDto response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", fuelTypeResponse.getId())
                .when()
                .get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(FuelTypeResponseDto.class);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(fuelTypeResponse.getName(), response.getName());
        assertEquals(fuelTypeResponse.getPricePerLiter(), response.getPricePerLiter());

    }

    @Test
    @Order(3)
    @DisplayName("Should find all FuelType")
    void shouldFindAllFuelTypes() {
        FuelTypeResponseDto[] response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(FuelTypeResponseDto[].class);

        List<FuelTypeResponseDto> list = Arrays.stream(response).toList();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertNotNull(list.getFirst());
        assertNotNull(list.getFirst().getId());
        assertNotNull(list.getFirst().getName());
        assertNotNull(list.getFirst().getPricePerLiter());
        assertNotNull(list.getLast());
        assertNotNull(list.getLast().getId());
        assertNotNull(list.getLast().getName());
        assertNotNull(list.getLast().getPricePerLiter());
    }


    @Test
    @Order(4)
    @DisplayName("Should update a FuelType")
    void shouldUpdateAFuelType() {

        FuelTypeRequestDto body = FuelTypeMocks.mockRequestDto("Etanol", 6.45, false);
        body.setId(fuelTypeResponse.getId());

        FuelTypeResponseDto response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(body)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(FuelTypeResponseDto.class);

        fuelTypeResponse = response;

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(body.getId(), response.getId());
        assertEquals(body.getName(), response.getName());
        assertEquals(body.getPricePerLiter(), response.getPricePerLiter());
    }

    @Test
    @Order(5)
    @DisplayName("Should delete a FuelType")
    void shouldDeleteAFuelType() {
        given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", fuelTypeResponse.getId())
                .when()
                .delete("/{id}")
                .then()
                .statusCode(204);
    }
}
