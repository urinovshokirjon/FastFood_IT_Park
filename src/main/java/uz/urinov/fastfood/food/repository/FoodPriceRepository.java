package uz.urinov.fastfood.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import uz.urinov.fastfood.food.entity.FoodPriceEntity;


public interface FoodPriceRepository extends JpaRepository<FoodPriceEntity, Long> {


//    @Query("SELECT CASE " +
//            " WHEN AVG(f.averageRating) IS NULL THEN 0 " +
//            " WHEN AVG(f.averageRating) >= 4.75 THEN 5 " +
//            " WHEN AVG(f.averageRating) >= 4.25 THEN 4.5 " +
//            " WHEN AVG(f.averageRating) >= 3.75 THEN 4 " +
//            " WHEN AVG(f.averageRating) >= 3.25 THEN 3.5 " +
//            " WHEN AVG(f.averageRating) >= 2.75 THEN 3 " +
//            " ELSE 0.5 END " +
//            " FROM FoodPriceEntity f WHERE f.foodId = :foodId")
//    Double ratingByFieldId(@Param("fieldId") Integer foodId);
}
