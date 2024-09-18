package uz.urinov.fastfood.Order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {

    @NotNull(message = " Kitchen id bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "Kitchen id  ning qiymati minimal 1 bo'lsin")
    private Integer kitchenId;

    @NotNull
    private List<FoodCount> foodCountList;

    @NotNull(message = "Lat bo'sh bo'lishi mumkin emas")
    private Double lat;

    @NotNull(message = "Lon bo'sh bo'lishi mumkin emas")
    private Double lon;
}
