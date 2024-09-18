package uz.urinov.fastfood.food.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.fastfood.profile.entity.ProfileEntity;


import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "food_price", uniqueConstraints = {@UniqueConstraint(columnNames = {"food_id", "owner_id"})})
public class FoodPriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "average_rating")
    private Integer averageRating = 0;

    @Column(name = "owner_id")
    private String ownerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",insertable = false, updatable = false)
    private ProfileEntity owner;

    @Column(name = "food_id")
    private Integer foodId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id",insertable = false, updatable = false)
    private FoodEntity food;

    @Column(name = "visible")
    private Boolean visible=Boolean.TRUE;

    @Column(name = "create_date")
    private LocalDate createDate=LocalDate.now();

}
