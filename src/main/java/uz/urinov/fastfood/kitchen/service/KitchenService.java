package uz.urinov.fastfood.kitchen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.district.entity.DistrictEntity;
import uz.urinov.fastfood.district.repository.DistrictRepository;
import uz.urinov.fastfood.district.service.DistrictService;
import uz.urinov.fastfood.exp.AppBadException;
import uz.urinov.fastfood.food.repository.FoodRepository;
import uz.urinov.fastfood.kitchen.dto.KitchenCreateDto;
import uz.urinov.fastfood.kitchen.dto.KitchenResponseDto;
import uz.urinov.fastfood.kitchen.dto.KitchenResponseMiniDto;
import uz.urinov.fastfood.kitchen.entity.KitchenEntity;
import uz.urinov.fastfood.kitchen.enums.Status;
import uz.urinov.fastfood.kitchen.repository.KitchenAttachRepository;
import uz.urinov.fastfood.kitchen.repository.KitchenRepository;
import uz.urinov.fastfood.profile.enums.ProfileRole;
import uz.urinov.fastfood.region.service.RegionService;
import uz.urinov.fastfood.util.ApiResponse;
import uz.urinov.fastfood.util.SecurityUtil;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class KitchenService {
    @Autowired
    private ResourceBundleMessageSource rbms;
    private final KitchenRepository kitchenRepository;
    private final DistrictService districtService;
    private final FoodRepository foodRepository;
    private final KitchenAttachService kitchenAttachService;
    private final KitchenAttachRepository kitchenAttachRepository;
    private final RegionService regionService;
    private final DistrictRepository districtRepository;

    // 1. Create kitchen (ADMIN,OWNER)
    public ApiResponse<String> createKitchen(KitchenCreateDto dto, Language lang) {

        boolean empty = dto.getPhotoList().isEmpty();
        if (empty) {
            String message = rbms.getMessage("image.not.available", null, new Locale(lang.name()));
            throw new AppBadException(message);
        }

        districtService.getDistrictEntityById(dto.getDistrictId(), lang);
        KitchenEntity kitchen = new KitchenEntity();
        kitchen.setName(dto.getName());
        kitchen.setLat(dto.getLat());
        kitchen.setLon(dto.getLon());
        kitchen.setDescription(dto.getDescription());
        kitchen.setDistrictId(dto.getDistrictId());
        kitchen.setProfileId(SecurityUtil.getProfileId());

        KitchenEntity saveKitchen = kitchenRepository.save(kitchen);

        kitchenAttachService.kitchenAttachSave(dto.getPhotoList(), saveKitchen.getId(), lang);

        String message = rbms.getMessage("created", null, new Locale(lang.name()));
        return new ApiResponse<>("Kitchen " + message, 201,false);
    }

    // 2. Update kitchen (ADMIN,OWNER)
    public ApiResponse<String> updateKitchen(int id, KitchenCreateDto dto, Language lang) {
        KitchenEntity kitchen = getKitchenOwnerById(id, lang);
        kitchen.setName(dto.getName());
        kitchen.setLat(dto.getLat());
        kitchen.setLon(dto.getLon());
        kitchen.setDescription(dto.getDescription());
        kitchen.setDistrictId(dto.getDistrictId());
        kitchen.setProfileId(SecurityUtil.getProfileId());
        kitchen.setStatus(Status.INACTIVE);

        KitchenEntity saveKitchen = kitchenRepository.save(kitchen);
        kitchenAttachService.kitchenAttachSave(dto.getPhotoList(), saveKitchen.getId(), lang);

        String message = rbms.getMessage("changed", null, new Locale(lang.name()));
        return new ApiResponse<>("Kitchen " + message, 200,false);
    }

    // 3. Delete kitchen (ADMIN,OWNER)
    public ApiResponse<String> deleteKitchen(int kitchenId, Language lang) {

        KitchenEntity entity =null;
        if (SecurityUtil.getProfile().getRole().equals(ProfileRole.ROLE_OWNER)){
             entity = getKitchenOwnerById(kitchenId, lang);
        }
        if (SecurityUtil.getProfile().getRole().equals(ProfileRole.ROLE_ADMIN)){
             entity = getKitchenById(kitchenId, lang);
        }

        Integer effectiveRow = foodRepository.updateFirstByVisible(entity.getId());
        entity.setVisible(false);
        kitchenRepository.save(entity);
        String message = rbms.getMessage("deleted", null, new Locale(lang.name()));
        return new ApiResponse<>("Kitchen with " + effectiveRow + " field " + message, 200,false);

    }

    // 4. Region Kitchen List kitchen
    public ApiResponse<List<KitchenResponseDto>> regionKitchenList(int regionId, Language lang, int page, int size, Double lat, Double lon) {
        // 1. Region ID ga mos keladigan barcha tumanlarni topamiz
        regionService.getRegionEntityById(regionId, lang);

        Pageable pageable = PageRequest.of(page, size);

        List<DistrictEntity> districts = districtRepository.findAllByRegionId(regionId);

        List<Integer> districtIds = districts.stream().map(DistrictEntity::getId).toList();

        List<KitchenEntity> entityList = kitchenRepository.findClosestKitchensInDistricts(districtIds, lat, lon, pageable);
        return new ApiResponse<>(200,false,entityList.stream().map(this::kitchenDetails).toList());
    }

    // 5. Closest Kitchen List kitchen (eng yaqin)
    public ApiResponse<List<KitchenResponseDto> > closestKitchenList(Language lang, int page, int size, Double lat, Double lon) {

        Pageable pageable = PageRequest.of(page, size);
        List<KitchenEntity> kitchenEntityList = kitchenRepository.findClosestKitchens(lat, lon, pageable);
        return new ApiResponse<>(200,false,kitchenEntityList.stream().map(this::kitchenDetails).toList());
    }


    public KitchenResponseDto kitchenDetails(KitchenEntity entity) {
        KitchenResponseDto dto = new KitchenResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setDescription(entity.getDescription());
        dto.setLat(entity.getLat());
        dto.setLon(entity.getLon());
        dto.setPhotolist(kitchenAttachRepository.findAttachIds(entity.getId()));

        return dto;
    }

    public KitchenResponseMiniDto kitchenDetailsMini(KitchenEntity entity) {
        KitchenResponseMiniDto dto = new KitchenResponseMiniDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
//        dto.setStatus(entity.getStatus());
//        dto.setDescription(entity.getDescription());
        dto.setLat(entity.getLat());
        dto.setLon(entity.getLon());
//        dto.setPhotolist(kitchenAttachRepository.findAttachIds(entity.getId()));
        return dto;
    }

    public KitchenEntity getKitchenOwnerById(int id, Language lang) {
        return kitchenRepository.findByIdAndProfileIdAndVisibleTrue(id, SecurityUtil.getProfileId()).orElseThrow(() -> {
            String message = rbms.getMessage("item.not.found", null, new Locale(lang.name()));
            throw new AppBadException(message);
        });
    }

    public KitchenEntity getKitchenById(int id, Language lang) {
        return kitchenRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> {
            String message = rbms.getMessage("item.not.found", null, new Locale(lang.name()));
            throw new AppBadException(message);
        });
    }


}
