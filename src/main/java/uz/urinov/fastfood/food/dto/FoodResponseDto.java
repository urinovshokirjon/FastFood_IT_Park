package uz.urinov.fastfood.food.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uz.urinov.fastfood.category.dto.CategoryResponseDto;
import uz.urinov.fastfood.food.enums.StatusFood;


import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodResponseDto {

    private Integer kitchenId;

    private String kitchenName;

    private Integer id;

    private StatusFood status;

    private String name;

    private Double price;

    private String description;

    private Double rating;

    private List<String> photolist;

    private CategoryResponseDto categoryResponseDto;

    private Boolean visible;

    private LocalDate createDate;
}
