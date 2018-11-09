package co.com.development.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.development.demo.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
	
	
	@Query(value="select state from products p where p.id = ?1", nativeQuery=true)
	boolean status(Long id);
}
