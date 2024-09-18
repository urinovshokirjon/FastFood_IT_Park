package uz.urinov.fastfood.region.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uz.urinov.fastfood.region.entity.RegionEntity;
import uz.urinov.fastfood.region.mapper.RegionMapper;


import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {

    // 3. Region list
    @Query("SELECT r FROM RegionEntity r where r.visible = true order by r.id")
    List<RegionEntity> findAllVisible();

    // 5. Region By Lang
    @Query(value = "SELECT id, " +
            " CASE :lang " +
            " WHEN 'UZ' THEN name_uz " +
            " WHEN 'RU' THEN name_ru " +
            " WHEN 'EN' THEN name_en " +
            " END AS name " +
            " FROM region ORDER BY order_number DESC; ", nativeQuery = true)
    List<RegionMapper> findAll(@Param("lang") String lang);





}
