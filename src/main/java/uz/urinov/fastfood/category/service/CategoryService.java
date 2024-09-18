package uz.urinov.fastfood.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.category.dto.CategoryCreateDto;
import uz.urinov.fastfood.category.dto.CategoryResponseDto;
import uz.urinov.fastfood.category.entity.CategoryEntity;
import uz.urinov.fastfood.category.mapper.CategoryMapper;
import uz.urinov.fastfood.category.repository.CategoryRepository;
import uz.urinov.fastfood.exp.AppBadException;
import uz.urinov.fastfood.util.ApiResponse;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ResourceBundleMessageSource rbms;

    // 1. Category create
    public ApiResponse<String> createCategory(CategoryCreateDto categoryDto, Language lang) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setOrderNumber(categoryDto.getOrderNumber());
        categoryEntity.setNameUz(categoryDto.getNameUz());
        categoryEntity.setNameEn(categoryDto.getNameEn());
        categoryEntity.setNameRu(categoryDto.getNameRu());
        categoryRepository.save(categoryEntity);
        String message = rbms.getMessage("created", null, new Locale(lang.name()));
        return new ApiResponse<>("Category " + message, 201, false);

    }

    // 2. Update category
    public ApiResponse<String> updateCategory(int id, CategoryCreateDto categoryDto, Language lang) {
        CategoryEntity categoryEntity = getCategoryEntityById(id, lang);
        categoryEntity.setOrderNumber(categoryDto.getOrderNumber());
        categoryEntity.setNameUz(categoryDto.getNameUz());
        categoryEntity.setNameEn(categoryDto.getNameEn());
        categoryEntity.setNameRu(categoryDto.getNameRu());
        categoryRepository.save(categoryEntity);
        String message = rbms.getMessage("changed", null, new Locale(lang.name()));
        return new ApiResponse<>("Category " + message, 200, false);
    }

    // 3. Category list
    public ApiResponse<List<CategoryResponseDto>> getCategoryList() {
        List<CategoryResponseDto> categoryDtoList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryRepository.findAllByVisibleTrueOrderByOrderNumber()) {
            categoryDtoList.add(toDTO(categoryEntity));
        }
        return new ApiResponse<>(200, false, categoryDtoList);
    }

    //4. Category delete
    public ApiResponse<String> deleteCategory(int id, Language lang) {
        CategoryEntity categoryEntity = getCategoryEntityById(id, lang);
        categoryRepository.delete(categoryEntity);
        String message = rbms.getMessage("deleted", null, new Locale(lang.name()));
        return new ApiResponse<>("Category " + message, 200, false);
    }


    // 5. Category By Lang
    public ApiResponse<List<CategoryResponseDto>>getCategoryByLang2(Language lang) {

        List<CategoryResponseDto> categoryLangDtoList = new ArrayList<>();
        for (CategoryMapper categoryMapper : categoryRepository.findAllLang(lang.name())) {
            CategoryResponseDto categoryLangDto = new CategoryResponseDto();
            categoryLangDto.setId(categoryMapper.getId());
            categoryLangDto.setName(categoryMapper.getName());
            categoryLangDtoList.add(categoryLangDto);
        }
        return new ApiResponse<>(200,false,categoryLangDtoList);
    }

    public CategoryResponseDto toDTO(CategoryEntity entity) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }


    public CategoryResponseDto toCategoryLang(CategoryEntity entity, Language lang) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        switch (lang) {
            case UZ -> dto.setName(entity.getNameUz());
            case EN -> dto.setName(entity.getNameEn());
            case RU -> dto.setName(entity.getNameRu());
        }
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }


    public CategoryEntity getCategoryEntityById(int id, Language lang) {
        return categoryRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> {
            String message = rbms.getMessage("item.not.found", null, new Locale(lang.name()));
            throw new AppBadException(message);
        });
    }


}
