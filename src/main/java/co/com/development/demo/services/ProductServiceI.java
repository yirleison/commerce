package co.com.development.demo.services;

import java.util.ArrayList;

import co.com.development.demo.model.ProductModel;

public interface ProductServiceI {
	
	public ProductModel save(ProductModel product);
	public ArrayList<ProductModel> list();
	public boolean status(Long id);
}
