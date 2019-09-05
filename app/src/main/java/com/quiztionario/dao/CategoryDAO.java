package com.quiztionario.dao;

import com.quiztionario.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
	private static CategoryDAO dao = new CategoryDAO();
	private int currentId = 0;
	private List<Category> categories = new ArrayList<>();

	private CategoryDAO() {}

	public Category create(Category category) {
		category.setId(++currentId);
		categories.add(category);
		return category;
	}

	public List<Category> find(String find) {
		String findLower = find.toLowerCase();
		List<Category> result = new ArrayList<>();
		for (Category c: categories) {
			if (c.getName().toLowerCase().contains(findLower))
				result.add(c);
		}
		return result;
	}

	public static CategoryDAO getInstance() {
		return dao;
	}
}
