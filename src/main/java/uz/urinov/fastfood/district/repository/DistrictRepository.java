package uz.urinov.fastfood.district.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uz.urinov.fastfood.district.entity.DistrictEntity;
import uz.urinov.fastfood.district.mapper.DistrictMapper;

import java.util.List;
import java.util.Optional;


public interface DistrictRepository extends JpaRepository<DistrictEntity, Integer> {

    // 3. Region list
    @Query("SELECT d FROM DistrictEntity d where d.visible = true ORDER BY d.regionId desc")
    List<DistrictEntity> findAllVisible();

    // 5. Region By Lang
    @Query(value = "SELECT id,region_id, " +
            " CASE :lang " +
            " WHEN 'UZ' THEN name_uz " +
            " WHEN 'EN' THEN name_en " +
            " WHEN 'RU' THEN name_ru " +
            " END AS name " +
            " FROM district ORDER BY order_number DESC; ", nativeQuery = true)
    List<DistrictMapper> findAll(@Param("lang") String lang);

    //   // 6. District Region  By Lang
    @Query(value = "SELECT id,region_id, " +
            " CASE :lang " +
            " WHEN 'UZ' THEN name_uz " +
            " WHEN 'EN' THEN name_en " +
            " WHEN 'RU' THEN name_ru " +
            " END AS name " +
            " FROM district " +
            " WHERE region_id =:regionId; ", nativeQuery = true)
    List<DistrictMapper> getDistrictRegionId(@Param("lang") String lang, @Param("regionId") int regionId);


    List<DistrictEntity> findAllByRegionId(Integer regionId);

    Optional<DistrictEntity> findByRegionId(Integer regionId);





}
