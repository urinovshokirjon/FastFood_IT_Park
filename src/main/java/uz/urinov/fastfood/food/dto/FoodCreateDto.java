package uz.urinov.fastfood.food.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodCreateDto {

    @NotBlank(message = "Name bo'sh bo'lishi mumkin emas")
    @Size(min = 3, max = 50, message = "Berilgan field (Name) ning uzunligi 3 va 50 orasida bo'lishi kerak")
    private String name;

    @NotNull(message = "price bo'sh bo'lishi mumkin emas")
    private Double price;

    @NotBlank(message = "Description bo'sh bo'lishi mumkin emas")
    @Size(min = 3, message = "Field haqida to'liqroq ma'lumot bo'lishi kerak")
    private String description;

    @NotNull(message = " category  id bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "category  id  ning qiymati minimal 1 bo'lsin")
    private Integer categoryId;

    @NotNull(message = " Kitchen id bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "Kitchen id  ning qiymati minimal 1 bo'lsin")
    private Integer kitchenId;

    @NotNull
    private List<String> photoList;

}
