package uz.urinov.fastfood.Order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FoodCount {

    @NotNull(message = " FoodCount id bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "FoodCount id  ning qiymati minimal 1 bo'lsin")
    private Integer foodId;

    @NotNull(message = " foods count bo'sh bo'lishi mumkin emas")
    @Min(value = 1, message = "foods count  ning qiymati minimal 1 bo'lsin")
    private Integer foodsCount;
}
