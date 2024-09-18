package uz.urinov.fastfood.region.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.exp.AppBadException;
import uz.urinov.fastfood.region.dto.RegionCreateDTO;
import uz.urinov.fastfood.region.dto.RegionResponseDTO;
import uz.urinov.fastfood.region.entity.RegionEntity;
import uz.urinov.fastfood.region.repository.RegionRepository;
import uz.urinov.fastfood.util.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class RegionService {

    private final RegionRepository regionRepository;
    @Autowired
    private ResourceBundleMessageSource rbms;

    // 1. Region create (ADMIN)
    public ApiResponse<String> createRegion(RegionCreateDTO createDTO, Language lang) {
        RegionEntity entity = new RegionEntity();
        entity.setNameUz(createDTO.getNameUz());
        entity.setNameEn(createDTO.getNameEn());
        entity.setNameRu(createDTO.getNameRu());
        regionRepository.save(entity);
        String message = rbms.getMessage("created", null, new Locale(lang.name()));
        return new ApiResponse<>("Region " + message, 201, false);
    }

    // 2. Region update (ADMIN)
    public ApiResponse<String> updateRegion(RegionCreateDTO regionDto, int id, Language lang) {
        RegionEntity regionEntity = getRegionEntityById(id, lang);
        regionEntity.setNameUz(regionDto.getNameUz());
        regionEntity.setNameEn(regionDto.getNameEn());
        regionEntity.setNameRu(regionDto.getNameRu());
        regionRepository.save(regionEntity);
        String message = rbms.getMessage("changed", null, new Locale(lang.name()));
        return new ApiResponse<>("Region" + message, 200, false);
    }

    // 3. Region list (ADMIN)
    public ApiResponse<List<RegionResponseDTO>> getRegionList() {

        List<RegionResponseDTO> regionDtoList = new ArrayList<>();

        for (RegionEntity regionEntity : regionRepository.findAll()) {
            regionDtoList.add(toDTO(regionEntity));
        }
        return new ApiResponse<>(200, false, regionDtoList);
    }

    //4. Region delete (ADMIN)
    public ApiResponse<String> deleteRegion(int id, Language lang) {
        RegionEntity regionEntity = getRegionEntityById(id, lang);
        regionRepository.delete(regionEntity);
        String message = rbms.getMessage("deleted", null, new Locale(lang.name()));
        return new ApiResponse<>("Region " + message, 200, false);
    }

    // 5. Region By Lang
    public ApiResponse<List<RegionResponseDTO>> getRegionByLang(Language lang) {

        List<RegionResponseDTO> regionLangDtoList = new ArrayList<>();

        List<RegionEntity> allByVisibleTrue = regionRepository.findAllVisible();

        for (RegionEntity regionEntity : allByVisibleTrue) {

            RegionResponseDTO regionLangDto = new RegionResponseDTO();
            regionLangDto.setId(regionEntity.getId());
            switch (lang) {
                case UZ -> regionLangDto.setName(regionEntity.getNameUz());
                case EN -> regionLangDto.setName(regionEntity.getNameEn());
                case RU -> regionLangDto.setName(regionEntity.getNameRu());
            }
            regionLangDtoList.add(regionLangDto);
        }
        return new ApiResponse<>(200, false, regionLangDtoList);
    }


    public RegionResponseDTO toDTO(RegionEntity entity) {
        RegionResponseDTO dto = new RegionResponseDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    public RegionEntity getRegionEntityById(int regionId, Language lang) {
        return regionRepository.findById(regionId).orElseThrow(() -> {
            String message = rbms.getMessage("item.not.found", null, new Locale(lang.name()));
            throw new AppBadException(message);
        });
    }


}
