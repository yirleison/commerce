package co.com.development.demo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.development.demo.dao.impl.ProductDaoImpl;
import co.com.development.demo.model.ProductModel;
import co.com.development.demo.services.ProductServiceI;

@Component
public class ProductServiceImpl implements Serializable, ProductServiceI {

	private static final long serialVersionUID = 9135864742677482897L;

	@Autowired
	ProductDaoImpl productDaoImpl;

	@Override
	public ProductModel save(ProductModel product) {

		return this.productDaoImpl.save(product);
	}

	@Override
	public ArrayList<ProductModel> list() {
		return this.productDaoImpl.list();
	}

	@Override
	public boolean status(Long id) {
		return this.productDaoImpl.status(id);
	}

}
