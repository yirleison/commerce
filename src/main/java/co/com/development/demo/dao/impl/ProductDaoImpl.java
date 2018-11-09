package co.com.development.demo.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.development.demo.dao.ProductDaoI;
import co.com.development.demo.dao.ProductRepository;
import co.com.development.demo.model.ProductModel;

@Component
public class ProductDaoImpl implements Serializable, ProductDaoI {

	private static final long serialVersionUID = 8995178579692250103L;
	@Autowired
	ProductRepository productRepository;

	@Override
	public ProductModel save(ProductModel product) {
		return this.productRepository.save(product);
	}

	@Override
	public ArrayList<ProductModel> list() {
		return (ArrayList<ProductModel>) this.productRepository.findAll();
	}

	@Override
	public boolean status(Long id) {
	HashMap< Object, Object> map = new HashMap<>();
		return this.productRepository.status(id);
	}



}
