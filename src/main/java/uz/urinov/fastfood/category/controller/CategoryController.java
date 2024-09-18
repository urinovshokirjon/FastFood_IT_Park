package uz.urinov.fastfood.category.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.category.dto.CategoryCreateDto;
import uz.urinov.fastfood.category.dto.CategoryResponseDto;
import uz.urinov.fastfood.category.service.CategoryService;
import uz.urinov.fastfood.util.ApiResponse;

import java.util.List;

@SecurityRequirement(name = "Authorization")
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    // 1. Create category (ADMIN)
    @PostMapping("/adm/create")
    public ResponseEntity<ApiResponse<String>> createCategory(@RequestBody CategoryCreateDto categoryDto,
                                                      @RequestHeader(value = "Accept-Language") Language lang) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto,lang));
    }

    // 2. Update category (ADMIN)
    @PutMapping("/adm/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateCategory(@PathVariable int id, @RequestBody CategoryCreateDto categoryDto,
                                                 @RequestHeader(value = "Accept-Language") Language lang) {

        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto,lang));
    }

    // 3. Category list (ADMIN)
    @GetMapping("/adm/list")
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getCategoryList() {

        return ResponseEntity.ok(categoryService.getCategoryList());
    }

    // 4. Delete category (ADMIN)
    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable int id,
                                               @RequestHeader(value = "Accept-Language") Language lang) {

        return ResponseEntity.ok(categoryService.deleteCategory(id,lang));
    }

    // 5. Category By Lang
    @GetMapping("/lang")
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getCategoryByLang(@RequestHeader(value = "Accept-Language") Language lang) {
        return ResponseEntity.ok(categoryService.getCategoryByLang2(lang));

    }


}
