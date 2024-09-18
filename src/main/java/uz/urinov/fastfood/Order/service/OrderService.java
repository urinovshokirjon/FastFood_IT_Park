package uz.urinov.fastfood.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.Order.dto.CreateOrderDto;
import uz.urinov.fastfood.Order.dto.FoodCount;
import uz.urinov.fastfood.Order.dto.OrderResponse;
import uz.urinov.fastfood.Order.dto.SingleOrderResponseDto;
import uz.urinov.fastfood.Order.entity.OrderEntity;
import uz.urinov.fastfood.Order.entity.SingleOrderEntity;
import uz.urinov.fastfood.Order.repasitory.OrderRepository;
import uz.urinov.fastfood.Order.repasitory.SingleOrderRepository;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.exp.AppBadException;
import uz.urinov.fastfood.food.entity.FoodEntity;
import uz.urinov.fastfood.food.repository.FoodRepository;
import uz.urinov.fastfood.food.service.FoodService;
import uz.urinov.fastfood.kitchen.entity.KitchenEntity;
import uz.urinov.fastfood.kitchen.service.KitchenService;
import uz.urinov.fastfood.util.ApiResponse;
import uz.urinov.fastfood.util.DistanceCalculator;
import uz.urinov.fastfood.util.SecurityUtil;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ResourceBundleMessageSource rbms;
    private final OrderRepository orderRepository;
    private final SingleOrderRepository singleOrderRepository;
    private final FoodRepository foodRepository;
    private final KitchenService kitchenService;

    public ApiResponse<String> foodOrder(CreateOrderDto dto, Language lang) {
        String profileId = SecurityUtil.getProfileId();
        KitchenEntity kitchenEntity = kitchenService.getKitchenById(dto.getKitchenId(), lang);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setKitchenId(dto.getKitchenId());
        orderEntity.setCustomerId(profileId);
        orderRepository.save(orderEntity);

        Double orderTotalPrice = 0.0;
        Double orderTotalEstimatedTime = 0.0;

        for (FoodCount foodCount : dto.getFoodCountList()) {
            FoodEntity foodEntity = foodRepository.findById(foodCount.getFoodId()).get();

            SingleOrderEntity singleOrderEntity = new SingleOrderEntity();
            singleOrderEntity.setOrderId(orderEntity.getId());
            singleOrderEntity.setFoodId(foodCount.getFoodId());
            singleOrderEntity.setFoodCount(foodCount.getFoodsCount());

            Double totalPrice = foodEntity.getPrice() * foodCount.getFoodsCount();
            orderTotalPrice = orderTotalPrice + totalPrice;
            orderTotalEstimatedTime = orderTotalEstimatedTime + (1.25 * foodCount.getFoodsCount());
            singleOrderEntity.setTotalPrice(totalPrice);
            singleOrderRepository.save(singleOrderEntity);
        }

        double distance = DistanceCalculator.calculateDistance(kitchenEntity.getLat(), kitchenEntity.getLon(), dto.getLat(), dto.getLon());
        int distanceTime = (int) (distance * 3);

        orderEntity.setEstimatedDeliveryTime(distanceTime); // taxminiy yetkazib berish vaqti
        orderEntity.setEstimatedCookedTime(orderTotalEstimatedTime.intValue()); // Taxminiy pishirish vaqtini belgilang
        orderEntity.setTotalPrice(orderTotalPrice);  // Umumiy narxni belgilang

        orderRepository.save(orderEntity);
        String message = rbms.getMessage("created", null, new Locale(lang.name()));
        return new ApiResponse<>("Food order " + message, 201, false);
    }

    public ApiResponse<OrderResponse> getOrder(Integer orderId, Language lang) {

        OrderEntity orderEntity = getById(orderId, lang);
        return new ApiResponse<>(200,false,entityToDto(orderEntity));
    }


    private OrderResponse entityToDto(OrderEntity entity) {
        OrderResponse dto = new OrderResponse();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setTotalTime(entity.getEstimatedCookedTime() + entity.getEstimatedDeliveryTime());
        dto.setCreateDate(entity.getCreateDate());
        dto.setSingleOrderResponseDtoList(singleOrderRepository.findByOrderId(entity.getId()).stream().map(this::singleEntityToDto).toList());
        return dto;
    }

    private SingleOrderResponseDto singleEntityToDto(SingleOrderEntity entity) {
        SingleOrderResponseDto dto = new SingleOrderResponseDto();
        dto.setId(entity.getId());
        dto.setFoodId(entity.getFoodId());
        dto.setFoodCount(entity.getFoodCount());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setOrderId(entity.getOrderId());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    public OrderEntity getById(int id, Language lang) {
        return orderRepository.findByIdAndCustomerId(id,SecurityUtil.getProfileId()).orElseThrow(() -> {
            String message = rbms.getMessage("item.not.found", null, new Locale(lang.name()));
            throw new AppBadException(message);
        });
    }



}
