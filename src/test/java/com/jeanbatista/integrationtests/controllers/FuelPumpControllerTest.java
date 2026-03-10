package com.jeanbatista.integrationtests.controllers;

import com.jeanbatista.configs.TestConfigs;
import com.jeanbatista.data.dto.request.FuelPumpRequestDto;
import com.jeanbatista.data.dto.request.FuelTypeRequestDto;
import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import com.jeanbatista.data.dto.response.FuelTypeResponseDto;
import com.jeanbatista.integrationtests.AbstractIntegrationTest;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.repositories.FuelTypeRepository;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class FuelPumpControllerTest extends AbstractIntegrationTest {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    private static RequestSpecification specification;
    private static FuelType savedFuelType;
    private static FuelPumpResponseDto fuelPumpResponse;

    @BeforeEach
    void setup() {
        specification = new RequestSpecBuilder()
                .setBasePath(TestConfigs.FUEL_PUMP_BASE_PATH)
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Should save a FuelPump")
    void shouldSaveAFuelPump() {
        savedFuelType = fuelTypeRepository.save(FuelTypeMocks.mockEntity("Gasolina", 4.53, true));

        FuelPumpRequestDto request = FuelPumpMocks.mockRequestDto("Bomba 01", savedFuelType.getId(), true);

        FuelPumpResponseDto response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(FuelPumpResponseDto.class);

        fuelPumpResponse = response;

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(request.getName(), response.getName());
        assertNotNull(response.getFuelType());
        assertEquals(savedFuelType.getId(), response.getFuelType().getId());
    }

    @Test
    @Order(2)
    @DisplayName("Should find a FuelPump by your id")
    void shouldFindAFuelPumpById() {
        FuelPumpResponseDto response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", fuelPumpResponse.getId())
                .when()
                .get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(FuelPumpResponseDto.class);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getName());
        assertNotNull(response.getFuelType());
        assertNotNull(response.getFuelType().getId());
        assertEquals(savedFuelType.getId() ,response.getFuelType().getId());
        assertEquals(savedFuelType.getName() ,response.getFuelType().getName());
        assertEquals(savedFuelType.getPricePerLiter() ,response.getFuelType().getPricePerLiter());
    }

    @Test
    @Order(3)
    @DisplayName("Should find all FuelPumps")
    void shouldFindAllFuelPumps() {
        FuelPumpResponseDto[] response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(FuelPumpResponseDto[].class);

        List<FuelPumpResponseDto> list = Arrays.stream(response).toList();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertNotNull(list.getFirst());
        assertNotNull(list.getFirst().getId());
        assertNotNull(list.getFirst().getName());
        assertNotNull(list.getFirst().getFuelType());
        assertNotNull(list.getFirst().getFuelType().getId());
        assertNotNull(list.getLast());
        assertNotNull(list.getLast().getId());
        assertNotNull(list.getLast().getName());
        assertNotNull(list.getLast().getFuelType());
        assertNotNull(list.getLast().getFuelType().getId());
    }


    @Test
    @Order(4)
    @DisplayName("Should update a FuelPump")
    void shouldUpdateAFuelPump() {
        savedFuelType = fuelTypeRepository.save(FuelTypeMocks.mockEntity("Etanol", 5.32, true));

        FuelPumpRequestDto body = FuelPumpMocks.mockRequestDto("Bomba 02", savedFuelType.getId(), true);
        body.setId(fuelPumpResponse.getId());

        FuelPumpResponseDto response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(body)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(FuelPumpResponseDto.class);

        fuelPumpResponse = response;

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getName());
        assertNotNull(response.getFuelType());
        assertNotNull(response.getFuelType().getId());
        assertEquals(savedFuelType.getId() ,response.getFuelType().getId());
        assertEquals(savedFuelType.getName() ,response.getFuelType().getName());
        assertEquals(savedFuelType.getPricePerLiter() ,response.getFuelType().getPricePerLiter());
    }

    @Test
    @Order(5)
    @DisplayName("Should delete a FuelPump")
    void shouldDeleteAFuelPump() {
        given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", fuelPumpResponse.getId())
                .when()
                .delete("/{id}")
                .then()
                .statusCode(204);
    }
}
