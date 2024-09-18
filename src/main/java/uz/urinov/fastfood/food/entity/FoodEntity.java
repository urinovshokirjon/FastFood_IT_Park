package uz.urinov.fastfood.food.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.fastfood.category.entity.CategoryEntity;
import uz.urinov.fastfood.food.enums.StatusFood;
import uz.urinov.fastfood.kitchen.entity.KitchenEntity;
import uz.urinov.fastfood.profile.entity.ProfileEntity;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "average_rating")
    private Double averageRating = 0D;

    @Column(name = "rating_count")
    private Integer ratingCount = 0;

    @Column(name = "owner_id")
    private String ownerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",insertable = false, updatable = false)
    private ProfileEntity owner;

    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "kitchen_id")
    private Integer kitchenId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id",insertable = false, updatable = false)
    private KitchenEntity kitchen;

    @Enumerated(EnumType.STRING)
    private StatusFood status = StatusFood.READY;

    @Column(name = "visible")
    private Boolean visible=Boolean.TRUE;

    @Column(name = "create_date")
    private LocalDate createDate=LocalDate.now();
}
