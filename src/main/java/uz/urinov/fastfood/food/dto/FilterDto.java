package uz.urinov.fastfood.food.dto;


import lombok.Data;
import uz.urinov.fastfood.food.enums.StatusFood;

@Data
public class FilterDto {

    private String name;

    private Double price;

    private Integer categoryId;

    private Integer kitchenId;

    private StatusFood status;

}
