package co.com.development.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import co.com.development.demo.model.CategoryModel;
import co.com.development.demo.model.ProductModel;
import co.com.development.demo.service.impl.CategoryServiceImpl;
import co.com.development.demo.service.impl.ProductServiceImpl;
import co.com.development.demo.util.*;

@RestController
@RequestMapping("/productos")
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private CategoryServiceImpl categoryService;

	private ObjectMapper mapper = new ObjectMapper();

	@PostMapping(value = "/crear", produces = "application/json; charset=UTF-8")
	public RestResponse create(@RequestBody JsonNode json) {
		this.mapper = new ObjectMapper();

		// Optengo el dato para el id de la categoria que me llega de la vista
		Long idCategory = json.get("category").longValue();

		// Hago una consulta a la base de datos para buscar una categoria en especifica
		// con base a un id
		Optional<CategoryModel> c = null;
		try {
			c = this.categoryService.findById(idCategory);
		} catch (Exception e) {
			System.err.println(e);
		}

		// Creo una istancia del modelo CategoryModel
		CategoryModel catModel = new CategoryModel();
		catModel.setId(c.get().getId());

		ProductModel productModel = new ProductModel();
		productModel.setProductName(json.get("productName").textValue());
		productModel.setProductDescription(json.get("productDescription").textValue());
		productModel.setUnitValue(json.get("unitValue").floatValue());
		productModel.setQuantity(json.get("quantity").intValue());
		productModel.setProducCaegory(catModel);

		try {
			productServiceImpl.save(productModel);
			return new RestResponse(HttpStatus.OK.value(), "Producto Registrado Exitosamente");
		} catch (Exception e) {
			System.out.println(e);
		}

		return new RestResponse(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.value(), "Ha ocurrido un error inesperadol");
	}

	@GetMapping(value = "/listar", produces = "application/json")
	public String listProducts() {

		String data = "";

		try {
			ArrayList<ProductModel> listProducts = this.productServiceImpl.list();
			data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listProducts);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	@GetMapping(value = "/estado/{id}", produces="application/json")
	public String ProductState(@PathVariable Long id) {

		String data = "";
		ObjectNode objectNode1 = mapper.createObjectNode();
		ObjectNode objectNode2 = mapper.createObjectNode();
		ArrayNode a = new ArrayNode(null);
	
		try {
			boolean s = this.productServiceImpl.status(id);
			if(s) {
				objectNode1.put("status", 200);
				objectNode2.put("estado_producto", "Activo");
			}
			else {
				objectNode1.put("status", 200);
				objectNode2.put("estado_producto", "Inactivo");	
			}
			a.add(objectNode1);
			a.add(objectNode2);
			
			data = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(a);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;

	}

	private boolean validProduct(ProductModel product) {
		boolean isValid = true;
		if (product.getProductName().isEmpty() || product.getProductName().equals("")
				|| product.getProductName() == null) {
			return isValid = false;
		}
		if (product.getProductDescription().isEmpty() || product.getProductDescription().equals("")
				|| product.getProductDescription() == null) {
			return isValid = false;
		}
		if (product.getUnitValue() != 0 || product.getUnitValue() <= 0) {
			return isValid = false;
		}

		if (product.getQuantity() != 0 || product.getQuantity() <= 0) {
			return isValid = false;
		}
		return isValid;
	}
}
