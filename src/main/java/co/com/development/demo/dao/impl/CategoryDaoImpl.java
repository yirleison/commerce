package co.com.development.demo.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.development.demo.dao.CategoryDaoI;
import co.com.development.demo.dao.CategoryRepository;
import co.com.development.demo.model.CategoryModel;

@Component
public class CategoryDaoImpl implements CategoryDaoI {

	private static final long serialVersionUID = -1623788188064988003L;
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public CategoryModel save(CategoryModel category) {
		return categoryRepository.save(category);
	}

	@Override
	public Optional<CategoryModel> findById(Long id) {
		return this.categoryRepository.findById(id);
	}

	@Override
	public boolean exitstsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
