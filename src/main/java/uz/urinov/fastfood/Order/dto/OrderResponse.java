package uz.urinov.fastfood.Order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import uz.urinov.fastfood.Order.entity.SingleOrderEntity;
import uz.urinov.fastfood.Order.enums.BookingStatus;
import uz.urinov.fastfood.kitchen.entity.KitchenEntity;
import uz.urinov.fastfood.profile.entity.ProfileEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    private Integer id;

    private Integer kitchenId;

    private String customerId;

    private List<SingleOrderResponseDto> singleOrderResponseDtoList;

    private Double totalPrice;

    private Integer foodsCount;

    private Integer totalTime;

    private LocalDateTime createDate;
}
