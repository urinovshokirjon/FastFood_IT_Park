package uz.urinov.fastfood.kitchen.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import uz.urinov.fastfood.food.entity.FoodEntity;
import uz.urinov.fastfood.kitchen.entity.KitchenEntity;
import uz.urinov.fastfood.kitchen.enums.Status;


import java.util.List;
import java.util.Optional;

public interface KitchenRepository extends JpaRepository<KitchenEntity, Integer> {

    Optional<KitchenEntity> findByIdAndProfileIdAndVisibleTrue(Integer id, String profileId);

    Optional<KitchenEntity> findByIdAndVisibleTrue(Integer id);



    @Query(value = "SELECT * FROM kitchen k " +
            " WHERE k.visible = true AND k.visible AND k.status='ACTIVE'" +
            " ORDER BY (6371 * acos(cos(radians(:lat)) * cos(radians(k.lat)) * " +
            " cos(radians(k.lon) - radians(:lon)) + sin(radians(:lat)) * sin(radians(k.lat)))) ASC",
            nativeQuery = true)
    List<KitchenEntity> findClosestKitchens(@Param("lat") double lat, @Param("lon") double lon, Pageable pageable);

    @Query("SELECT s FROM KitchenEntity s " +
            " WHERE s.visible AND s.status=:status " +
            " ORDER BY s.createDate ASC ")
    List<KitchenEntity> listStatusKitchen(@Param("status") Status status, Pageable pageable);

    @Query("SELECT f FROM FoodEntity f " +
            " WHERE f.visible AND f.status=:status " +
            " ORDER BY f.createDate ASC ")
    List<FoodEntity> listStatusField(@Param("status") Status status, Pageable pageable);


    @Query(value = "SELECT * FROM kitchen s " +
            " WHERE s.district_id IN (:districtIds) AND s.visible AND s.status='ACTIVE' " +
            " ORDER BY (6371 * acos(cos(radians(:lat)) * cos(radians(s.lat)) * " +
            "            cos(radians(s.lon) - radians(:lon)) + sin(radians(:lat)) * sin(radians(s.lat)))) ASC",nativeQuery = true)
    List<KitchenEntity> findClosestKitchensInDistricts(@Param("districtIds") List<Integer> districtIds,
                                                             @Param("lat") Double lat,
                                                             @Param("lon") Double lon,
                                                             Pageable pageable);




}


