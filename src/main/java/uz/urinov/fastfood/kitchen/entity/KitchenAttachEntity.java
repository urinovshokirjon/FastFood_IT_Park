package uz.urinov.fastfood.kitchen.entity;

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
@Table(name = "kitchen_attach")
public class KitchenAttachEntity {
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

    @Column(name = "kitchen_id")
    private Integer kitchenId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id",insertable = false, updatable = false)
    private KitchenEntity kitchen;

    @Column(name = "create_date")
    private LocalDate createDate=LocalDate.now();
}
