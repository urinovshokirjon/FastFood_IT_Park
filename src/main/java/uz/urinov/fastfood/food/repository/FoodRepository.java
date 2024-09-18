package uz.urinov.fastfood.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import uz.urinov.fastfood.food.entity.FoodEntity;
import uz.urinov.fastfood.food.enums.StatusFood;

import java.util.List;
import java.util.Optional;


public interface FoodRepository extends JpaRepository<FoodEntity, Integer>, JpaSpecificationExecutor<FoodEntity> {

    Optional<FoodEntity> findByIdAndOwnerId(Integer id, String ownerId);

    List<FoodEntity> findAllByKitchenIdAndStatusAndVisibleTrue(Integer stadiumId, StatusFood status);

    Optional<FoodEntity> findByIdAndVisibleTrueAndStatus(Integer id,StatusFood status);

    @Query("UPDATE FoodEntity f SET f.visible=false WHERE f.kitchenId=?1 ")
    Integer updateFirstByVisible(Integer kitchenId);

    List<FoodEntity> findAllByIdInAndVisibleTrueAndStatus(List<Integer> ids, StatusFood status);
}
