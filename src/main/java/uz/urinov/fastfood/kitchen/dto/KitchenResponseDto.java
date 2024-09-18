package uz.urinov.fastfood.kitchen.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uz.urinov.fastfood.district.dto.DistrictResponseDTO;
import uz.urinov.fastfood.kitchen.enums.Status;


import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KitchenResponseDto {

    private Integer id;

    private String name;

    private Status status;

    private DistrictResponseDTO district;

    private String description;

    private Double lat;

    private Double lon;

    private List<String> photolist;

//    private List<FoodResponseDto> fieldResponseDtoList;

    private Boolean visible;

    private LocalDate createDate;
}
