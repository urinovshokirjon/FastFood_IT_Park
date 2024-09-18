package uz.urinov.fastfood.district.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.district.dto.DistrictCreateDTO;
import uz.urinov.fastfood.district.dto.DistrictResponseDTO;
import uz.urinov.fastfood.district.entity.DistrictEntity;
import uz.urinov.fastfood.district.mapper.DistrictMapper;
import uz.urinov.fastfood.district.repository.DistrictRepository;
import uz.urinov.fastfood.exp.AppBadException;
import uz.urinov.fastfood.region.service.RegionService;
import uz.urinov.fastfood.util.ApiResponse;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final RegionService regionService;
    @Autowired
    private ResourceBundleMessageSource rbms;

    // 1. District create (ADMIN)
    public ApiResponse<String> createDistrict(DistrictCreateDTO createDTO, Language lang) {

        regionService.getRegionEntityById(createDTO.getRegionId(), lang);
        DistrictEntity entity = new DistrictEntity();
        entity.setRegionId(createDTO.getRegionId());
        entity.setNameUz(createDTO.getNameUz());
        entity.setNameEn(createDTO.getNameEn());
        entity.setNameRu(createDTO.getNameRu());
        entity.setCounty(createDTO.getCounty());

        districtRepository.save(entity);
        String message=rbms.getMessage("created",null, new Locale(lang.name()));
        return new ApiResponse<>("District "+message,201,false);
    }

    // 2. District update (ADMIN)
    public ApiResponse<String> updateDistrict(DistrictCreateDTO districtDto, int id, Language lang) {

        regionService.getRegionEntityById(districtDto.getRegionId(), lang);
        DistrictEntity districtEntity = getDistrictEntityById(id, lang);

        districtEntity.setNameUz(districtDto.getNameUz());
        districtEntity.setNameEn(districtDto.getNameEn());
        districtEntity.setNameRu(districtDto.getNameRu());
        districtEntity.setCounty(districtDto.getCounty());
        districtRepository.save(districtEntity);
        String message = rbms.getMessage("changed", null, new Locale(lang.name()));
        return new ApiResponse<>("District " + message, 200,true);
    }

    // 3. District list (ADMIN)
    public ApiResponse<List<DistrictResponseDTO>> getDistrictList() {

        List<DistrictResponseDTO> districtDtoList = new ArrayList<>();

        for (DistrictEntity districtEntity : districtRepository.findAll()) {
            districtDtoList.add(toDTO(districtEntity));
        }
        return new ApiResponse<>(200,false,districtDtoList);
    }

    //4. District delete (ADMIN)
    public ApiResponse<String> deleteDistrict(int id, Language lang) {
        DistrictEntity districtEntity = getDistrictEntityById(id, lang);
        districtRepository.delete(districtEntity);
        String message = rbms.getMessage("deleted", null, new Locale(lang.name()));
        return new ApiResponse<>("District " + message, 200,false);
    }

//     5. District By Lang
    public ApiResponse<List<DistrictResponseDTO> > getDistrictByLang(Language lang) {

        List<DistrictResponseDTO> districtLangDtoList = new ArrayList<>();

        List<DistrictEntity> allByVisibleTrue = districtRepository.findAllVisible();

        for (DistrictEntity districtEntity : allByVisibleTrue) {

            DistrictResponseDTO districtLangDto = new DistrictResponseDTO();
            districtLangDto.setId(districtEntity.getId());
            switch (lang) {
                case UZ -> districtLangDto.setName(districtEntity.getNameUz());
                case EN -> districtLangDto.setName(districtEntity.getNameEn());
                case RU -> districtLangDto.setName(districtEntity.getNameRu());
            }
            districtLangDtoList.add(districtLangDto);
        }
        return new ApiResponse<>(200,false,districtLangDtoList);
    }

    // 5. District By Lang (Native query)
    public List<DistrictResponseDTO> getDistrictByLang2(Language lang) {

        List<DistrictResponseDTO> districtLangDtoList = new ArrayList<>();

        List<DistrictMapper> allByVisibleTrue = districtRepository.findAll(lang.name());

        for (DistrictMapper districtMapper : allByVisibleTrue) {
            DistrictResponseDTO districtLangDto = new DistrictResponseDTO();
            districtLangDto.setId(districtMapper.getId());
            districtLangDto.setRegionId(districtMapper.getRegionId());
            districtLangDto.setName(districtMapper.getName());
            districtLangDtoList.add(districtLangDto);
        }
        return districtLangDtoList;
    }

    // 6. District Region  By Lang
    public ApiResponse<List<DistrictResponseDTO>> getDistrictRegionId(int regionId, Language lang) {
        regionService.getRegionEntityById(regionId, lang);
        List<DistrictResponseDTO> districtRegionDtoList = new ArrayList<>();

        List<DistrictMapper> allByVisibleTrue = districtRepository.getDistrictRegionId(lang.name(),regionId);

        for (DistrictMapper districtMapper : allByVisibleTrue) {
            DistrictResponseDTO districtLangDto = new DistrictResponseDTO();
            districtLangDto.setId(districtMapper.getId());
            districtLangDto.setRegionId(districtMapper.getRegionId());
            districtLangDto.setName(districtMapper.getName());
             districtRegionDtoList.add(districtLangDto);
        }
        return new ApiResponse<>(200,false,districtRegionDtoList);
    }

    // 7. District id  By Lang
    public ApiResponse<DistrictResponseDTO> getDistrictId(int districtId, Language lang) {
        DistrictEntity entity = getDistrictEntityById(districtId, lang);
        DistrictResponseDTO dto = new DistrictResponseDTO();
        dto.setId(entity.getId());
        dto.setRegionId(entity.getRegionId());
        dto.setCountry(entity.getCounty());
        switch (lang) {
            case UZ -> dto.setName(entity.getNameUz());
            case EN -> dto.setName(entity.getNameEn());
            case RU -> dto.setName(entity.getNameRu());
        }

        return new ApiResponse<>(200,false,dto);
    }


    public DistrictResponseDTO toDTO(DistrictEntity entity) {
        DistrictResponseDTO dto = new DistrictResponseDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    public DistrictEntity getDistrictEntityById(int id, Language lang) {
        return districtRepository.findById(id).orElseThrow(() -> {
            String message = rbms.getMessage("item.not.found", null, new Locale(lang.name()));
            throw new AppBadException(message);
        });
    }



}
