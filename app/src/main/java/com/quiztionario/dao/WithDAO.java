package com.quiztionario.dao;

import android.content.Context;

abstract class WithDAO {
	DAO dao;

	WithDAO(Context context) {
		dao = DAO.getInstance(context);
	}

	public void beginTransaction() {
		dao.getWritableDatabase().beginTransaction();
	}

	public void setTransactionSuccessful() {
		dao.getWritableDatabase().setTransactionSuccessful();
	}

	public void endTransaction() {
		dao.getWritableDatabase().endTransaction();
	}
}
