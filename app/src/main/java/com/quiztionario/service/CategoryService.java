package com.quiztionario.service;

import android.annotation.SuppressLint;
import android.content.Context;

import com.quiztionario.dao.CategoryDAO;
import com.quiztionario.model.Category;
import com.quiztionario.model.ValidationException;
import com.quiztionario.model.WithContext;

import java.util.List;

public class CategoryService extends WithContext {
	@SuppressLint("StaticFieldLeak")
	private static CategoryService service;

	private CategoryService(Context context) {
		super(context);
	}

	public Category create(Category category) throws ValidationException {
		if (category.getName() == null || category.getName().trim().isEmpty())
			throw new ValidationException("Category Name is required");

		return CategoryDAO.getInstance(context).create(category);
	}

	public List<Category> find(String find) {
		if (find == null || find.trim().isEmpty())
			return null;

		return CategoryDAO.getInstance(context).find(find);
	}

	public static CategoryService getInstance(Context context) {
		if (service == null)
			service = new CategoryService(context);
		return service;
	}
}
