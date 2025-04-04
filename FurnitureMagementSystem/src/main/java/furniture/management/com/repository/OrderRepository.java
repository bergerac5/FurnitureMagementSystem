package furniture.management.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import furniture.management.com.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
