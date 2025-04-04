package furniture.management.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import furniture.management.com.model.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name); 
    Category findCategoryById(Long id);
}
