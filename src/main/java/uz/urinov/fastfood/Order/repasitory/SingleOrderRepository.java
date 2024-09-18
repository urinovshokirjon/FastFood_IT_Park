package uz.urinov.fastfood.Order.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.fastfood.Order.entity.SingleOrderEntity;

import java.util.List;

public interface SingleOrderRepository extends JpaRepository<SingleOrderEntity, Integer> {

    List<SingleOrderEntity> findByOrderId(int orderId);





}
