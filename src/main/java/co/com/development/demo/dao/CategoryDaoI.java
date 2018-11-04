package co.com.development.demo.dao;

import java.io.Serializable;
import java.util.Optional;

import co.com.development.demo.model.CategoryModel;

public interface CategoryDaoI extends Serializable {
	public CategoryModel save(CategoryModel category);

	public Optional<CategoryModel> findById(Long id);

	public boolean exitstsById(Long id);
}
