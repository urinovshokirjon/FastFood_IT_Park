package uz.urinov.fastfood.food.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import uz.urinov.fastfood.attach.entity.AttachEntity;
import uz.urinov.fastfood.profile.entity.ProfileEntity;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "food_attach")
public class FoodAttachEntity {
    @Id
    @UuidGenerator
    private String id;

    @Column(name = "owner_id")
    private String ownerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id",insertable = false, updatable = false)
    private ProfileEntity owner;

    @Column(name = "attach_id")
    private String attachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "food_id")
    private Integer foodId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id",insertable = false, updatable = false)
    private FoodEntity food;

    @Column(name = "create_date")
    private LocalDate createDate=LocalDate.now();
}
