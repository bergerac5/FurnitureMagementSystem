package furniture.management.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import furniture.management.com.model.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerById(Long Id);
}
