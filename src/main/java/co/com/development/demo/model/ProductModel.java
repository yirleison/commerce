package co.com.development.demo.model;



import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.AccessType;
import javax.persistence.Column;

@Entity
@Table(name = "products")
@Access(AccessType.FIELD)
public class ProductModel extends parentEntity implements Serializable {

	private static final long serialVersionUID = 753631769309285826L;

	@Column(name = "product_name", nullable = false, length = 150)
	private String productName;

	@Column(name = "product_description", nullable = false, length = 200)
	private String productDescription;

	@Column(name = "unit_value", nullable = false)
	private float unitValue;

	@Column(name = "quantity", nullable = false, length = 11)
	private Integer quantity;

	@Column(name = "state", nullable = true, length = 11, columnDefinition="tinyint(1) default 1")
	private Integer state = 1;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryModel category;

	public ProductModel() {

	}

	public ProductModel(String productName, String productDescription, float unitValue, Integer quantity,
			CategoryModel category) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.unitValue = unitValue;
		this.quantity = quantity;
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public float getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(float unitValue) {
		this.unitValue = unitValue;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CategoryModel getProducCaegory() {
		return this.category;
	}

	public void setProducCaegory(CategoryModel category) {
		this.category = category;
	}

}
