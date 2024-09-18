package uz.urinov.fastfood.kitchen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uz.urinov.fastfood.kitchen.entity.KitchenAttachEntity;


import java.util.List;

public interface KitchenAttachRepository extends CrudRepository<KitchenAttachEntity,String> {

    @Query(value = " SELECT sa.attachId FROM KitchenAttachEntity AS sa WHERE sa.kitchenId=?1")
    List<String> findAttachIds(Integer id);
}
