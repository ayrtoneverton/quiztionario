package com.quiztionario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quizwork.Category;

import java.util.ArrayList;
import java.util.List;

import static com.quiztionario.dao.DAO.*;

public class CategoryDAO extends WithDAO {
	private static CategoryDAO categoryDAO;

	private CategoryDAO(Context context) {
		super(context);
	}

	public Category create(Category category) {
		SQLiteDatabase db = dao.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CATEGORY_NAME, category.getName());

		category.setId(db.insert(CATEGORY_TABLE, null, values));
		return category;
	}

	public List<Category> find(String find) {
		SQLiteDatabase db = dao.getReadableDatabase();
		Cursor c = db.query(CATEGORY_TABLE, null,
				CATEGORY_NAME + " LIKE ?",
				new String[]{"%" + find + "%"},
				null, null, null);

		List<Category> result = new ArrayList<>();
		while (c.moveToNext()) {
			result.add(new Category(
					c.getLong(c.getColumnIndex(CATEGORY_ID)),
					c.getString(c.getColumnIndex(CATEGORY_NAME))
			));
		}
		c.close();
		return result;
	}

	public static CategoryDAO getInstance(Context context) {
		if (categoryDAO == null)
			categoryDAO = new CategoryDAO(context);
		return categoryDAO;
	}
}
