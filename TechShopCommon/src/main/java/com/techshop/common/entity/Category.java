package com.techshop.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 128, nullable = false, unique = true)
	private String name;

	@Column(length = 64, nullable = false, unique = true)
	private String alias;

	@Column(length = 128, nullable = false)
	private String image;

	private boolean enabled;

	@OneToOne
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private Set<Category> children = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	public Category(String name) {
		this.name = name;
		this.alias = name;
		this.image = "default.jpg";
	}

	public Category(String name, Category parent) {
		this(name);
		this.parent = parent;
	}

	public Category(int id) {
		this.id = id;
	}

	public Category() {
	}

	public static Category copyIdAndName(Category category) {
		Category copy = new Category();
		copy.setId(category.getId());
		copy.setName(category.getName());

		return copy;
	}

	public static Category copyIdAndName(Integer id, String name) {
		Category copy = new Category();
		copy.setId(id);
		copy.setName(name);

		return copy;
	}

	public static Category copyAll(Category category) {
		Category copyCategory = new Category();
		copyCategory.setId(category.getId());
		copyCategory.setName(category.getName());
		copyCategory.setImage(category.getImage());
		copyCategory.setAlias(category.getAlias());
		copyCategory.setParent(category.getParent());
		copyCategory.setHasChildren(category.getChildren().size() > 0);
		
		return copyCategory;
	}
	
	@Transient
	private boolean hasChildren;
	
	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public static Category copyAll(Category category, String name) {
		Category copyName = copyAll(category);
		copyName.setName(name);
		
		return copyName;
	}

	public Category(Integer id, String name, String alias) {
		this.id = id;
		this.name = name;
		this.alias = alias;
	}

	@Transient
	public String getPhotosImagePath() {
		if (id == null || image == null)
			return "/images/image-thumbnail.png";

		return "/category-images/" + this.id + "/" + this.image;
	}
	
}
