package com.jeanbatista.integrationtests.controllers;

import com.jeanbatista.configs.TestConfigs;
import com.jeanbatista.data.dto.request.SupplyRequestDto;
import com.jeanbatista.data.dto.response.SupplyResponseDto;
import com.jeanbatista.integrationtests.AbstractIntegrationTest;
import com.jeanbatista.mocks.FuelPumpMocks;
import com.jeanbatista.mocks.FuelTypeMocks;
import com.jeanbatista.mocks.SupplyMocks;
import com.jeanbatista.model.FuelPump;
import com.jeanbatista.model.FuelType;
import com.jeanbatista.repositories.FuelPumpRepository;
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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class SupplyControllerTest extends AbstractIntegrationTest {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private FuelPumpRepository fuelPumpRepository;

    private static RequestSpecification specification;
    private static FuelType savedFuelType;
    private static FuelPump savedFuelPump;
    private static SupplyResponseDto supplyResponse;

    @BeforeEach
    void setup() {
        specification = new RequestSpecBuilder()
                .setBasePath(TestConfigs.SUPPLY_BASE_PATH)
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Should save a Supply")
    void shouldSaveASupply() {
        savedFuelType = fuelTypeRepository.save(FuelTypeMocks.mockEntity("Gasolina", 4.53, true));
        savedFuelPump = fuelPumpRepository.save(FuelPumpMocks.mockEntity("Bomba 01", savedFuelType, true));

        SupplyRequestDto request = SupplyMocks.mockRequestDto(savedFuelPump.getId(), 25.65, true);

        SupplyResponseDto response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(SupplyResponseDto.class);

        supplyResponse = response;

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getFuelPump());
        assertNotNull(response.getFuelPump().getId());
        assertEquals(savedFuelPump.getId(), response.getFuelPump().getId());
        assertNotNull(response.getFuelPump().getFuelType());
        assertNotNull(response.getFuelPump().getFuelType().getId());
        assertEquals(savedFuelType.getId(), response.getFuelPump().getFuelType().getId());
        assertNotNull(response.getSupplyDate());
        assertNotNull(response.getTotalPrice());
        assertNotNull(response.getLiters());
    }

    @Test
    @Order(2)
    @DisplayName("Should find a Supply by your id")
    void shouldFindASupplyById() {
        SupplyResponseDto response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", supplyResponse.getId())
                .when()
                .get("/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(SupplyResponseDto.class);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getFuelPump());
        assertNotNull(response.getFuelPump().getId());
        assertEquals(savedFuelPump.getId(), response.getFuelPump().getId());
        assertNotNull(response.getFuelPump().getFuelType());
        assertNotNull(response.getFuelPump().getFuelType().getId());
        assertEquals(savedFuelType.getId(), response.getFuelPump().getFuelType().getId());
        assertNotNull(response.getSupplyDate());
        assertNotNull(response.getTotalPrice());
        assertNotNull(response.getLiters());

    }

    @Test
    @Order(3)
    @DisplayName("Should find all Supplies")
    void shouldFindAllSupplies() {
        SupplyResponseDto[] response = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(SupplyResponseDto[].class);

        List<SupplyResponseDto> list = Arrays.stream(response).toList();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertNotNull(list.getFirst().getId());
        assertNotNull(list.getFirst().getFuelPump());
        assertNotNull(list.getFirst().getFuelPump().getId());
        assertNotNull(list.getFirst().getFuelPump().getFuelType());
        assertNotNull(list.getFirst().getFuelPump().getFuelType().getId());
        assertNotNull(list.getFirst().getSupplyDate());
        assertNotNull(list.getFirst().getTotalPrice());
        assertNotNull(list.getFirst().getLiters());
        assertNotNull(list.getLast().getId());
        assertNotNull(list.getLast().getFuelPump());
        assertNotNull(list.getLast().getFuelPump().getId());
        assertNotNull(list.getLast().getFuelPump().getFuelType());
        assertNotNull(list.getLast().getFuelPump().getFuelType().getId());
        assertNotNull(list.getLast().getSupplyDate());
        assertNotNull(list.getLast().getTotalPrice());
        assertNotNull(list.getLast().getLiters());
    }

    @Test
    @Order(4)
    @DisplayName("Should delete a Supply")
    void shouldDeleteASupply() {
        given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .pathParam("id", supplyResponse.getId())
                .when()
                .delete("/{id}")
                .then()
                .statusCode(204);
    }
}
