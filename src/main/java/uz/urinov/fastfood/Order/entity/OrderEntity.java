package uz.urinov.fastfood.Order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.fastfood.Order.enums.BookingStatus;
import uz.urinov.fastfood.kitchen.entity.KitchenEntity;
import uz.urinov.fastfood.profile.entity.ProfileEntity;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "order_table")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kitchen_id")
    private Integer kitchenId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id", insertable = false, updatable = false)
    private KitchenEntity kitchen;

    @Column(name = "customer_id")
    private String customerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private ProfileEntity customer;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY)
    private List<SingleOrderEntity> orderList;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "foods_count")
    private Integer foodsCount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.CHECKING;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "estimated_delivery_time")
    private Integer estimatedDeliveryTime;

    @Column(name = "estimated_cooked_time")
    private Integer estimatedCookedTime;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();
}
