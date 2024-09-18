package uz.urinov.fastfood.Order.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.fastfood.Order.entity.OrderEntity;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByIdAndCustomerId(Integer id, String customerId);



}
