package uz.urinov.fastfood.region.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.region.dto.RegionCreateDTO;
import uz.urinov.fastfood.region.dto.RegionResponseDTO;
import uz.urinov.fastfood.region.service.RegionService;
import uz.urinov.fastfood.util.ApiResponse;


import java.util.List;

@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    // 1. Region create (ADMIN)
    @PostMapping("/adm/create")
    public ResponseEntity<ApiResponse<String>> createRegion(@Valid @RequestBody RegionCreateDTO regionDto,
                                                            @RequestHeader(value = "Accept-Language") Language lang) {
        return ResponseEntity.ok(regionService.createRegion(regionDto, lang));
    }

    // 2. Region update (ADMIN)
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateRegion(@Valid @RequestBody RegionCreateDTO regionDto,
                                                            @RequestHeader(value = "Accept-Language") Language lang,
                                                            @PathVariable("id") int id) {
        return ResponseEntity.ok(regionService.updateRegion(regionDto, id, lang));
    }

    // 3. Region list (ADMIN)
    @GetMapping("/adm/list")
    public ResponseEntity<ApiResponse<List<RegionResponseDTO>>> getRegionList() {
        return ResponseEntity.ok(regionService.getRegionList());
    }

    // 4. Region delete (ADMIN)
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRegion(@PathVariable int id,
                                                            @RequestHeader(value = "Accept-Language") Language lang) {
        return ResponseEntity.ok(regionService.deleteRegion(id, lang));
    }

    // 5. Region By Lang
    @GetMapping("/lang")
    public ResponseEntity<ApiResponse<List<RegionResponseDTO>>> getRegionByLang2(@RequestHeader(value = "Accept-Language") Language lang) {
        return ResponseEntity.ok(regionService.getRegionByLang(lang));
    }


}
