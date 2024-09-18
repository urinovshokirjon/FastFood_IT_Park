package uz.urinov.fastfood.category.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uz.urinov.fastfood.category.entity.CategoryEntity;
import uz.urinov.fastfood.category.mapper.CategoryMapper;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByIdAndVisibleTrue(Integer id);

    // 3. Category list
    @Query("FROM CategoryEntity WHERE visible=true ORDER BY orderNumber DESC ")
    List<CategoryEntity> findAllByVisibleTrueOrderByOrderNumber();

    // 5.Category lang
    @Query(value ="SELECT id, " +
            " CASE :lang " +
            " WHEN 'UZ' THEN name_uz " +
            " WHEN 'EN' THEN name_en " +
            " END AS name " +
            " FROM category ORDER BY order_number DESC " ,nativeQuery = true)
    List<CategoryMapper> findAllLang(@Param("lang") String lang);



}
