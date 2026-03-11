package com.jeanbatista.controllers.docs;

import com.jeanbatista.data.dto.request.FuelPumpRequestDto;
import com.jeanbatista.data.dto.response.FuelPumpResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface FuelPumpControllerDocs {

    @Operation(summary = "Adds a new Fuel Pump",
            description = "Adds a new Fuel Pump by passing in a JSON representation of the fuel pump!",
            tags = {"Fuel Pump"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = FuelPumpResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    FuelPumpResponseDto create(FuelPumpRequestDto fuelPumpDto);

    @Operation(summary = "Finds a Fuel Pump", description = "Finds a Fuel Pump",
            tags = {"Fuel Pump"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = FuelPumpResponseDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    FuelPumpResponseDto findById(UUID id);

    @Operation(summary = "Finds all Fuel Pumps", description = "Finds all Fuel Pumps",
            tags = {"Fuel Pump"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = FuelPumpResponseDto.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    List<FuelPumpResponseDto> findAll();

    @Operation(summary = "Updates a Fuel Pump",
            description = "Updates a Fuel Pump by passing in a JSON representation of the fuel pump!",
            tags = {"Fuel Pump"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = FuelPumpResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    FuelPumpResponseDto update(FuelPumpRequestDto fuelPumpDto);

    @Operation(summary = "Deletes a Fuel Pump",
            description = "Deletes a Fuel Pump by passing in a JSON representation of the fuel pump!",
            tags = {"Fuel Pump"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    ResponseEntity<?> delete(@PathVariable(value = "id") UUID id);

}
