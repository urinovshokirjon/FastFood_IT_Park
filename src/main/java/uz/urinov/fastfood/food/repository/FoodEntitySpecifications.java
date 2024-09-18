package uz.urinov.fastfood.food.repository;

import uz.urinov.fastfood.food.entity.FoodEntity;
import org.springframework.data.jpa.domain.Specification;
import uz.urinov.fastfood.food.enums.StatusFood;

public class FoodEntitySpecifications {


    public static Specification<FoodEntity> byStatus(StatusFood status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<FoodEntity> byCategory(Integer categoryId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryId"), categoryId);
    }

    public static Specification<FoodEntity> byKitchen(Integer kitchenId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("kitchenId"), kitchenId);
    }

    public static Specification<FoodEntity> byVisible(boolean visible) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("visible"), visible);
    }

    public static Specification<FoodEntity> byNameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<FoodEntity> byPrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), min, max);
    }


}
