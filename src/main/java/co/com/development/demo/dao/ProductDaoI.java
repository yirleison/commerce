package co.com.development.demo.dao;

import java.util.ArrayList;

import co.com.development.demo.model.ProductModel;

public interface ProductDaoI {
	public ProductModel save(ProductModel product);
	public ArrayList<ProductModel> list();
	public boolean status(Long id);
}
