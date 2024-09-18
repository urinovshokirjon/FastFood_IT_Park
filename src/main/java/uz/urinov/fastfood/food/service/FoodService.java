package uz.urinov.fastfood.food.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.category.service.CategoryService;
import uz.urinov.fastfood.exp.AppBadException;
import uz.urinov.fastfood.food.dto.FilterDto;
import uz.urinov.fastfood.food.dto.FoodCreateDto;
import uz.urinov.fastfood.food.dto.FoodResponseDto;
import uz.urinov.fastfood.food.dto.FoodResponseMiniDto;
import uz.urinov.fastfood.food.entity.FoodEntity;
import uz.urinov.fastfood.food.enums.StatusFood;
import uz.urinov.fastfood.food.repository.FoodAttachRepository;
import uz.urinov.fastfood.food.repository.FoodEntitySpecifications;
import uz.urinov.fastfood.food.repository.FoodRepository;
import uz.urinov.fastfood.kitchen.entity.KitchenEntity;
import uz.urinov.fastfood.kitchen.service.KitchenService;
import uz.urinov.fastfood.util.ApiResponse;
import uz.urinov.fastfood.util.SecurityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodService {

    private final ResourceBundleMessageSource rbms;
    private final FoodRepository foodRepository;
    private final CategoryService categoryService;
    private final KitchenService kitchenService;
    private final FoodAttachService foodAttachService;
    private final FoodAttachRepository foodAttachRepository;

    // Food created
    public ApiResponse<String> createFood(FoodCreateDto dto, Language lang) {

        boolean empty = dto.getPhotoList().isEmpty();
        if (empty) {
            String message = rbms.getMessage("image.not.available", null, new Locale(lang.name()));
            throw new AppBadException(message);
        }
        categoryService.getCategoryEntityById(dto.getCategoryId(), lang);
        KitchenEntity kitchen = kitchenService.getKitchenOwnerById(dto.getKitchenId(), lang);

        FoodEntity entity = new FoodEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setCategoryId(dto.getCategoryId());
        entity.setKitchenId(dto.getKitchenId());
        entity.setOwnerId(SecurityUtil.getProfileId());

        FoodEntity saveFood = foodRepository.save(entity);
        foodAttachService.foodAttachSave(dto.getPhotoList(), saveFood, kitchen, lang);

        log.info("Create profile id = {}  phone = {} ", SecurityUtil.getProfile().getId(), SecurityUtil.getProfile().getPhone());

        String message = rbms.getMessage("created", null, new Locale(lang.name()));
        return new ApiResponse<>("Food " + message, 201, false);
    }

    // Food update
    public ApiResponse<String> updateFood(int fieldId, FoodCreateDto dto, Language lang) {

        boolean empty = dto.getPhotoList().isEmpty();
        if (empty) {
            String message = rbms.getMessage("image.not.available", null, new Locale(lang.name()));
            throw new AppBadException(message);
        }

        categoryService.getCategoryEntityById(dto.getCategoryId(), lang);
        KitchenEntity kitchen = kitchenService.getKitchenOwnerById(dto.getKitchenId(), lang);

        FoodEntity entity = getFoodOwnerById(fieldId, lang);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setCategoryId(dto.getCategoryId());
        entity.setKitchenId(dto.getKitchenId());
        entity.setOwnerId(SecurityUtil.getProfileId());
//        entity.setVisible(dto.get);
        FoodEntity saveFood = foodRepository.save(entity);

        foodAttachService.foodAttachSave(dto.getPhotoList(), saveFood, kitchen, lang);

        log.info(" Update profile id = {}  phone = {} ", SecurityUtil.getProfile().getId(), SecurityUtil.getProfile().getPhone());
        String message = rbms.getMessage("changed", null, new Locale(lang.name()));
        return new ApiResponse<>("Food " + message, 201, false);
    }

    // Food delete
    public ApiResponse<String> deleteFood(int fieldId, Language lang) {
        FoodEntity entity = getFoodOwnerById(fieldId, lang);
        entity.setVisible(false);
        foodRepository.save(entity);
        log.warn("Delete profile id = {}  phone = {} ", SecurityUtil.getProfile().getId(), SecurityUtil.getProfile().getPhone());
        String message = rbms.getMessage("changed", null, new Locale(lang.name()));
        return new ApiResponse<>("Food " + message, 200, false);
    }

    // get id Food
    public ApiResponse<FoodResponseDto> getIdFood(int fieldId, Language lang) {
        FoodEntity entity = getById(fieldId, lang);
        return new ApiResponse<>(200, false, fieldDetails(entity, lang));
    }

    // Get by field List
    public List<FoodResponseDto> getFoodListKitchenId(int kitchenId, Language lang) {
        kitchenService.getKitchenById(kitchenId, lang);
        List<FoodResponseDto> responseDtoList = new ArrayList<>();
        for (FoodEntity entity : foodRepository.findAllByKitchenIdAndStatusAndVisibleTrue(kitchenId, StatusFood.READY)) {
            responseDtoList.add(fieldDetails(entity, lang));
        }
        return responseDtoList;
    }

    // Food rating
    public ApiResponse<String> ratingFood(int fieldId, Integer rating, Language lang) {
        FoodEntity entity = getById(fieldId, lang);
        int count = entity.getRatingCount() + 1;
        Double averageRating = (entity.getAverageRating() * (count - 1) + rating) / count;
        entity.setRatingCount(count);
        entity.setAverageRating(averageRating);
        foodRepository.save(entity);
        log.warn("Rating profile id = {}  phone = {} ", SecurityUtil.getProfile().getId(), SecurityUtil.getProfile().getPhone());
        String message = rbms.getMessage("created", null, new Locale(lang.name()));
        return new ApiResponse<>("Price " + message, 200, false);
    }


    public FoodResponseDto fieldDetails(FoodEntity entity, Language lang) {
        FoodResponseDto dto = new FoodResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setStatus(entity.getStatus());
        dto.setDescription(entity.getDescription());
        dto.setRating(entity.getAverageRating());
        dto.setKitchenId(entity.getKitchenId());
        dto.setCategoryResponseDto(categoryService.toCategoryLang(entity.getCategory(), lang));
        dto.setPhotolist(foodAttachRepository.findAttachIds(entity.getId()));
        return dto;
    }

    public FoodResponseMiniDto fieldDetailsMini(FoodEntity entity, Language lang) {
        FoodResponseMiniDto dto = new FoodResponseMiniDto();
        dto.setKitchenId(entity.getKitchenId());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setRating(entity.getAverageRating());
        dto.setCategoryResponseDto(categoryService.toCategoryLang(entity.getCategory(), lang));
        dto.setPhotolist(foodAttachRepository.findAttachIds(entity.getId()));
        return dto;
    }


    public FoodEntity getFoodOwnerById(int id, Language lang) {
        return foodRepository.findByIdAndOwnerId(id, SecurityUtil.getProfileId()).orElseThrow(() -> {
            String message = rbms.getMessage("item.not.found", null, new Locale(lang.name()));
            throw new AppBadException(message);
        });
    }

    public FoodEntity getById(int id, Language lang) {
        return foodRepository.findByIdAndVisibleTrueAndStatus(id, StatusFood.READY).orElseThrow(() -> {
            String message = rbms.getMessage("item.not.found", null, new Locale(lang.name()));
            throw new AppBadException(message);
        });
    }


    public List<FoodResponseDto> getFoodList(FilterDto dto, Language lang) {

        Specification<FoodEntity> eligibleForCampaign = Specification.where(FoodEntitySpecifications.byVisible(true));

        if (dto.getCategoryId() != null) {
            eligibleForCampaign = eligibleForCampaign.and(FoodEntitySpecifications.byCategory(dto.getCategoryId()));
        }

        if (dto.getKitchenId() != null) {
            eligibleForCampaign = eligibleForCampaign.and(FoodEntitySpecifications.byKitchen(dto.getKitchenId()));
        }

        if (dto.getStatus() != null) {
            eligibleForCampaign = eligibleForCampaign.and(FoodEntitySpecifications.byStatus(dto.getStatus()));
        }

        if (dto.getName() != null) {
            eligibleForCampaign = eligibleForCampaign.and(FoodEntitySpecifications.byNameLike(dto.getName()));
        }

        if (dto.getPrice() != null) {
            eligibleForCampaign = eligibleForCampaign.and(FoodEntitySpecifications.byPrice(0, dto.getPrice()));
        }

        return foodRepository.findAll(eligibleForCampaign).stream().map((item) -> fieldDetails(item, lang)).toList();
    }
}
