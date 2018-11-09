package co.com.development.demo.controller;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import co.com.development.demo.model.CategoryModel;
import co.com.development.demo.service.impl.CategoryServiceImpl;
import co.com.development.demo.util.RestResponse;

@RestController
@RequestMapping("/categoria")
public class CategoryController {

	Gson gson;
	@Autowired
	private CategoryServiceImpl categoryService;

	private ObjectMapper objectMapper;

	@PostMapping(value = "/crear", produces = "application/json; charset=UTF-8")
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
		
		objectMapper = new ObjectMapper();
		ObjectNode json = objectMapper.createObjectNode();
		
		json.put("id", c.get().getId());
		json.put("categoria", c.get().getCategoryName());
		json.put("descripcion", c.get().getDescription());
		
		String p = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		System.out.println(p);
		return p;
	}

	@RequestMapping(value = "/categorias", method = RequestMethod.GET, produces= {"application/json"})
	public String getCategorias() throws JsonProcessingException {
		objectMapper = new ObjectMapper();
		
		String data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.categoryService.list());
		
		System.out.println(data);
		
		return data;
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
