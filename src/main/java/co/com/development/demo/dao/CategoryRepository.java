package co.com.development.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.development.demo.model.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
	
		
}
