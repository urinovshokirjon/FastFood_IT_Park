package uz.urinov.fastfood.Order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.fastfood.food.entity.FoodEntity;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@Table(name = "single_order_table")
public class SingleOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "food_id")
    private Integer foodId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", insertable = false, updatable = false)
    private FoodEntity food;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "food_count")
    private Integer foodCount;

    @Column(name = "order_id")
    private Integer orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderEntity order;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();
}
