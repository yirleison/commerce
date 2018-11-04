package co.com.development.demo.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

	@Entity
	@Table(name = "category")
	@Access(AccessType.FIELD)
	public class CategoryModel extends parentEntity implements Serializable {

		private static final long serialVersionUID = -4540432055443408574L;

		@Column(name = "category_name", nullable = false, length = 255)
		private String categoryName;

		@Column(name = "description", nullable = false, length = 255)
		private String description;

		
		public CategoryModel() {

		}

		public CategoryModel(String categoryName, String description) {
			super();
			this.categoryName = categoryName;
			this.description = description;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "CategoryModel [categoryName=" + categoryName + ", description=" + description + "]";
		}
		
		
}
