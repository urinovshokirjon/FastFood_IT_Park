package uz.urinov.fastfood.kitchen.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.kitchen.dto.KitchenCreateDto;
import uz.urinov.fastfood.kitchen.dto.KitchenResponseDto;
import uz.urinov.fastfood.kitchen.service.KitchenService;
import uz.urinov.fastfood.util.ApiResponse;

import java.util.List;


@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
@RestController
@Tag(name = "Kitchen controller", description = "API list for Kitchen")
@RequestMapping("/kitchen")
public class KitchenController {
    private final KitchenService kitchenService;

    // 1. Create kitchen (ADMIN,OWNER)
    @PostMapping()
    @Operation(summary = "Create kitchen", description = "Api for create kitchen")
    public ResponseEntity<ApiResponse<String>> createKitchen(@Valid @RequestBody KitchenCreateDto dto,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(kitchenService.createKitchen(dto, lang));
    }


    // 2. Update kitchen (ADMIN,OWNER)
    @PutMapping("/{id}")
    @Operation(summary = "Update kitchen", description = "Api for update kitchen")
    public ResponseEntity<ApiResponse<String>> updateKitchen(@PathVariable int id, @Valid @RequestBody KitchenCreateDto dto,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {

        return ResponseEntity.ok(kitchenService.updateKitchen(id, dto, lang));
    }

    // 3. Delete kitchen (ADMIN,OWNER)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete kitchen", description = "Api for delete kitchen")
    public ResponseEntity<ApiResponse<String>> deleteKitchen(@PathVariable("id") int kitchenId,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {

        return ResponseEntity.ok(kitchenService.deleteKitchen(kitchenId, lang));
    }

    // 4. Region Kitchen List kitchen
    @GetMapping("/region-id-kitchen-list")
    @Operation(summary = "Region Kitchen List kitchen", description = "Api for Region Kitchen List kitchen")
    public ResponseEntity<ApiResponse<List<KitchenResponseDto>>> regionIdKitchenList(@RequestParam(defaultValue = "1") int page,
                                                                                     @RequestParam(defaultValue = "2") int size,
                                                                                     @RequestParam int regionId,
                                                                                     @RequestParam Double lat,
                                                                                     @RequestParam Double lon,
                                                                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(kitchenService.regionKitchenList(regionId, lang, page - 1, size, lat, lon));
    }


    // 5. Closest Kitchen List kitchen (eng yaqin)
    @GetMapping("/closest-kitchen-list")
    @Operation(summary = "Closest Kitchen List kitchen", description = "Api for Closest Kitchen List kitchen")
    public ResponseEntity<ApiResponse<List<KitchenResponseDto>>> closestKitchenList(@RequestParam(defaultValue = "1") int page,
                                                                                    @RequestParam(defaultValue = "2") int size,
                                                                                    @RequestParam Double lat,
                                                                                    @RequestParam Double lon,
                                                                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(kitchenService.closestKitchenList(lang, page - 1, size, lat, lon));
    }


}
