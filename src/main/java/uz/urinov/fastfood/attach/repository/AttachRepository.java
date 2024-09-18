package uz.urinov.fastfood.attach.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.fastfood.attach.entity.AttachEntity;

import java.util.List;

public interface AttachRepository extends JpaRepository<AttachEntity,String> {

    List<AttachEntity> findAllByIdIn(List<String> ids);
}
