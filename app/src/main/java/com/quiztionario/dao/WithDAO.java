package com.quiztionario.dao;

import android.content.Context;

abstract class WithDAO {
	DAO dao;

	WithDAO(Context context) {
		dao = DAO.getInstance(context);
	}
}
