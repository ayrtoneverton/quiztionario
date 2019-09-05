package com.quiztionario.service;

import com.quiztionario.dao.CategoryDAO;
import com.quiztionario.model.Category;
import com.quiztionario.model.ValidationException;

import java.util.List;

public class CategoryService {
	private static CategoryService service = new CategoryService();

	private CategoryService() {}

	public Category create(Category category) throws ValidationException {
		if (category.getName() == null || category.getName().trim().isEmpty())
			throw new ValidationException("Category Name is required");

		return CategoryDAO.getInstance().create(category);
	}

	public List<Category> find(String find){
		if (find == null || find.trim().isEmpty())
			return null;

		return CategoryDAO.getInstance().find(find);
	}

	public static CategoryService getInstance() {
		return service;
	}
}
