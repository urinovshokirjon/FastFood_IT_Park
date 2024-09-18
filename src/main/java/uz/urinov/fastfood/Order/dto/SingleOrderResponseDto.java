package uz.urinov.fastfood.Order.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.fastfood.Order.entity.OrderEntity;
import uz.urinov.fastfood.food.entity.FoodEntity;

import java.time.LocalDateTime;

@Setter
@Getter
public class SingleOrderResponseDto {

    private Integer id;
    private Integer foodId;
    private Double totalPrice;
    private Integer foodCount;
    private Integer orderId;
    private LocalDateTime createDate;
}
