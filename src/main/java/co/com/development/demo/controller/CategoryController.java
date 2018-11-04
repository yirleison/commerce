package co.com.development.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.hibernate.annotations.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.json.*;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import co.com.development.demo.model.CategoryModel;
import co.com.development.demo.service.impl.CategoryServiceImpl;
import co.com.development.demo.util.RestResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private static final Long Long = null;
	Gson gson;
	@Autowired
	private CategoryServiceImpl categoryService;

	private ObjectMapper objectMapper;

	@PostMapping(value = "/save", produces = "application/json; charset=UTF-8")
	public RestResponse SaveOrUpdate(@RequestBody String category)
			throws JsonParseException, JsonMappingException, IOException {
		this.objectMapper = new ObjectMapper();
		try {
			CategoryModel categoryModel = this.objectMapper.readValue(category, CategoryModel.class);
			if (!this.validate(categoryModel)) {
				return new RestResponse(HttpStatus.BAD_REQUEST.value(),
						"Los campos enviados en el body no son validos");
			}

			this.categoryService.save(categoryModel);

		} catch (Exception e) {
			e.printStackTrace();
			return new RestResponse(HttpStatus.NOT_FOUND.value(), "Ha ocurrido un error al parsear el objeto Json");
		}

		return new RestResponse(HttpStatus.OK.value(), "Registro realizado exitosamente");
	}

	@RequestMapping(value = "/categoria/{id}", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String getCategory(@PathVariable("id") Long id) throws JsonProcessingException {
		Optional<CategoryModel> c = this.categoryService.findById(id);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(c);
		System.out.println(jsonString);
		return jsonString.toString();
	}

	@RequestMapping(value = "/categorias", method = RequestMethod.GET)
	public String getCategorias(@PathVariable("id") Long id) {
		// System.out.println(id);
		return "hola mundo" + id;
	}

	private boolean validate(CategoryModel categoryModel) {
		boolean isvalid = true;

		if (categoryModel.getCategoryName().equals("") || categoryModel.getCategoryName() == null) {
			isvalid = false;
		}

		if (categoryModel.getDescription().equals("") || categoryModel.getDescription() == null) {
			isvalid = false;
		}

		return isvalid;
	}
}
