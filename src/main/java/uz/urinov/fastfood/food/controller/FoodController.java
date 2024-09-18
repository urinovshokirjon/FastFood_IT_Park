package uz.urinov.fastfood.food.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.food.dto.FilterDto;
import uz.urinov.fastfood.food.dto.FoodCreateDto;
import uz.urinov.fastfood.food.dto.FoodResponseDto;
import uz.urinov.fastfood.food.service.FoodService;
import uz.urinov.fastfood.util.ApiResponse;

import java.util.List;


@SecurityRequirement(name = "Authorization")
@RestController
@Tag(name = "Food controller", description = "API list for Food")
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // Food created
    @PostMapping("/owr/created")
    @Operation(summary = "Create food", description = "Api for create food")
    public ResponseEntity<ApiResponse<String>> createFood(@Valid @RequestBody FoodCreateDto dto,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(foodService.createFood(dto, lang));
    }

    // Food update
    @PutMapping("/owr/update/{foodId}")
    @Operation(summary = "Update food", description = "Api for update food")
    public ResponseEntity<ApiResponse<String>> updateFood(@PathVariable(value = "foodId") int foodId, @Valid @RequestBody FoodCreateDto dto,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(foodService.updateFood(foodId, dto, lang));
    }

    // Food delete
    @PutMapping("/owr/delete/{foodId}")
    @Operation(summary = "Delete food", description = "Api for delete food")
    public ResponseEntity<ApiResponse<String>> deleteFood(@PathVariable(value = "foodId") int foodId,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(foodService.deleteFood(foodId, lang));
    }

    // get id Food
    @GetMapping("/getIdFood/{foodId}")
    @Operation(summary = "Get by id food", description = "Api for get by id food")
    public ResponseEntity<ApiResponse<FoodResponseDto>> getIdFood(@PathVariable(value = "foodId") int foodId,
                                                                  @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(foodService.getIdFood(foodId, lang));
    }

    // Get by food List
    @PostMapping("/list")
    @Operation(summary = "List food", description = "Api for List food")
    public ResponseEntity<List<FoodResponseDto>> getFoodList(@RequestBody FilterDto dto,
                                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        List<FoodResponseDto> result = foodService.getFoodList(dto, lang);
        return ResponseEntity.ok(result);
    }

    // Food rating
    @PostMapping("/rating")
    @Operation(summary = "Rating food", description = "Api for rating food")
    public ResponseEntity<ApiResponse<String>> ratingFood(@RequestParam int foodId,
                                                          @RequestParam int rating,
                                                          @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(foodService.ratingFood(foodId, rating, lang));
    }

}
