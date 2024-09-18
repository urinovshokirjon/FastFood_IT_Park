package uz.urinov.fastfood.Order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.fastfood.Order.dto.CreateOrderDto;
import uz.urinov.fastfood.Order.dto.OrderResponse;
import uz.urinov.fastfood.Order.service.OrderService;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.util.ApiResponse;

@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
@RestController
@Tag(name = "Order controller", description = "API list for Order")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "Create order", description = "Api for create order")
    public ResponseEntity<ApiResponse<String>> foodOrder(@RequestBody CreateOrderDto dto,
                                                 @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(orderService.foodOrder(dto, lang));
    }


    @GetMapping("/by-id/{id}")
    @Operation(summary = "Order by id", description = "Api for order by id")
    public ResponseEntity<ApiResponse<OrderResponse>> getFoodOrder(@PathVariable("id") Integer id,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(orderService.getOrder(id, lang));
    }



}
