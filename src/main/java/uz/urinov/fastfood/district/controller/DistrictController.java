package uz.urinov.fastfood.district.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.district.dto.DistrictCreateDTO;
import uz.urinov.fastfood.district.dto.DistrictResponseDTO;
import uz.urinov.fastfood.district.service.DistrictService;
import uz.urinov.fastfood.util.ApiResponse;


import java.util.List;

@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService regionService;

    // 1. Region create (ADMIN)
    @PostMapping("/adm/create")
    public ResponseEntity<ApiResponse<String>> createDistrict(@Valid @RequestBody DistrictCreateDTO regionDto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
       return ResponseEntity.ok(regionService.createDistrict(regionDto, lang));
    }

    // 2. District update (ADMIN)
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateDistrict(@Valid @RequestBody DistrictCreateDTO regionDto,
                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang,
                                                 @PathVariable("id") int id) {
        return ResponseEntity.ok(regionService.updateDistrict(regionDto, id, lang));
    }

    // 3. District list (ADMIN)
    @GetMapping("/adm/list")
    public ResponseEntity<ApiResponse<List<DistrictResponseDTO>>> getDistrictList() {
        return ResponseEntity.ok(regionService.getDistrictList());
    }

    // 4. District delete (ADMIN)
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDistrict(@PathVariable int id,
                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
       return ResponseEntity.ok(regionService.deleteDistrict(id, lang));
    }

    // 5. District By Lang
    @GetMapping("/lang")
    public ResponseEntity<ApiResponse<List<DistrictResponseDTO>>> getDistrictByLang2(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(regionService.getDistrictByLang(lang));
    }

        // 6. District Region  By Lang
    @GetMapping("/region-lang/{id}")
    public ResponseEntity<ApiResponse<List<DistrictResponseDTO>>> getDistrictRegionId(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang,
                                                                        @PathVariable("id") int regionId) {
        return ResponseEntity.ok(regionService.getDistrictRegionId(regionId,lang));
    }

    // 7. District id  By Lang
    @GetMapping("/district-lang/{id}")
    public ResponseEntity<ApiResponse<DistrictResponseDTO>> getDistrictId(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang,
                                                             @PathVariable("id") int districtId) {
        return ResponseEntity.ok(regionService.getDistrictId(districtId,lang));
    }


}
