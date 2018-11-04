package co.com.development.demo.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.com.development.demo.dao.impl.CategoryDaoImpl;
import co.com.development.demo.model.CategoryModel;
import co.com.development.demo.services.CategoryServiceI;

@Component
public class CategoryServiceImpl implements CategoryServiceI {

	private static final long serialVersionUID = 6439965922122146297L;

	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	@Override
	public CategoryModel save(CategoryModel category) {
		return this.categoryDaoImpl.save(category);
	}

	@Override
	public Optional<CategoryModel> findById(Long id) {
	return this.categoryDaoImpl.findById(id);
	}

	@Override
	public boolean exitstsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
