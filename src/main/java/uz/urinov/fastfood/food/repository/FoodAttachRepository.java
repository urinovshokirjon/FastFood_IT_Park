package uz.urinov.fastfood.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.urinov.fastfood.food.entity.FoodAttachEntity;


import java.util.List;

public interface FoodAttachRepository extends JpaRepository<FoodAttachEntity,String> {

    @Query(value = " SELECT fa.attachId FROM FoodAttachEntity AS fa WHERE fa.foodId=?1")
    List<String> findAttachIds(Integer id);
}
