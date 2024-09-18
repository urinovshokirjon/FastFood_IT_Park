package uz.urinov.fastfood.food.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uz.urinov.fastfood.category.dto.CategoryResponseDto;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodResponseMiniDto {

    private Integer kitchenId;

    private Integer id;

    private String name;

    private Double price;

    private String description;

    private Double rating;

    private List<String> photolist;

    private CategoryResponseDto categoryResponseDto;

}
