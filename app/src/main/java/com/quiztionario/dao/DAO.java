package com.quiztionario.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DAO extends SQLiteOpenHelper {
	private static DAO dao;
	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_NAME = "QuiztionarioDB";

	// Table User
	static final String USER_TABLE = "user";
	static final String USER_ID = "id_user";
	static final String USER_NAME = "user_name";
	static final String USER_EMAIL = "email";
	static final String USER_PASSWORD = "password";

	// Table Category
	static final String CATEGORY_TABLE = "category";
	static final String CATEGORY_ID = "id_category";
	static final String CATEGORY_NAME = "category_name";

	// Table Quiz
	static final String QUIZ_TABLE = "quiz";
	static final String QUIZ_ID = "id_quiz";
	static final String QUIZ_NAME = "quiz_name";
	static final String QUIZ_OPEN = "open";
	static final String QUIZ_CODE = "code";
	static final String QUIZ_START = "start_date";
	static final String QUIZ_END = "end_date";
	static final String QUIZ_CATEGORY = "quiz_id_category";
	static final String QUIZ_USER = "quiz_id_user";

	// Table Question
	static final String QUESTION_TABLE = "question";
	static final String QUESTION_ID = "id_question";
	static final String QUESTION_TEXT = "question_text";
	static final String QUESTION_QUIZ = "question_id_quiz";
	static final String QUESTION_OPTION = "question_id_option";

	// Table Option
	static final String OPTION_TABLE = "option";
	static final String OPTION_ID = "id_option";
	static final String OPTION_TEXT = "option_text";
	static final String OPTION_QUESTION = "option_id_question";

	// Table QuizAnswer
	static final String QUIZ_ANSWER_TABLE = "quiz_answer";
	static final String QUIZ_ANSWER_ID = "id_answer";
	static final String QUIZ_ANSWER = "id_quiz_answer";
	static final String QUIZ_ANSWER_CREATOR = "quiz_answer_id_user";
	static final String QUIZ_ANSWER_SCORE = "quiz_answer_score";

	// Table QuestionAnswer
	static final String QUESTION_ANSWER_TABLE = "question_answer";
	static final String QUESTION_ANSWER_ID = "id_question_answer";
	static final String QUESTION_ANSWER_ANSWER = "question_answer_id_answer";
	static final String QUESTION_ANSWER_QUESTION = "question_answer_id_question";
	static final String QUESTION_ANSWER_OPTION = "question_answer_id_option";
	static final String QUESTION_ANSWER_SCORE = "question_answer_score";

	private DAO(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	static DAO getInstance(Context context) {
		if (dao == null)
			dao = new DAO(context);
		return dao;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + USER_TABLE + "("
				+ USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ USER_NAME + " TEXT,"
				+ USER_EMAIL + " TEXT,"
				+ USER_PASSWORD + " TEXT"
				+ ")");

		db.execSQL("CREATE TABLE " + CATEGORY_TABLE + "("
				+ CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ CATEGORY_NAME + " TEXT"
				+ ")");

		db.execSQL("CREATE TABLE " + QUIZ_TABLE + "("
				+ QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ QUIZ_NAME + " TEXT,"
				+ QUIZ_OPEN + " INTEGER,"
				+ QUIZ_CODE + " INTEGER,"
				+ QUIZ_START + " TEXT,"
				+ QUIZ_END + " TEXT,"
				+ QUIZ_CATEGORY + " INTEGER,"
				+ QUIZ_USER + " INTEGER"
				+ ")");

		db.execSQL("CREATE TABLE " + QUESTION_TABLE + "("
				+ QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ QUESTION_TEXT + " TEXT,"
				+ QUESTION_QUIZ + " INTEGER,"
				+ QUESTION_OPTION + " INTEGER"
				+ ")");

		db.execSQL("CREATE TABLE " + OPTION_TABLE + "("
				+ OPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ OPTION_TEXT + " TEXT,"
				+ OPTION_QUESTION + " INTEGER"
				+ ")");

		db.execSQL("CREATE TABLE " + QUIZ_ANSWER_TABLE + "("
				+ QUIZ_ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ QUIZ_ANSWER + " INTEGER,"
				+ QUIZ_ANSWER_CREATOR + " INTEGER,"
				+ QUIZ_ANSWER_SCORE + " INTEGER"
				+ ")");

		db.execSQL("CREATE TABLE " + QUESTION_ANSWER_TABLE + "("
				+ QUESTION_ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ QUESTION_ANSWER_ANSWER + " INTEGER,"
				+ QUESTION_ANSWER_QUESTION + " INTEGER,"
				+ QUESTION_ANSWER_OPTION + " INTEGER,"
				+ QUESTION_ANSWER_SCORE + " INTEGER"
				+ ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + QUIZ_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + OPTION_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + QUIZ_ANSWER_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + QUESTION_ANSWER_TABLE);

		onCreate(db);
	}

	static String getDate(GregorianCalendar c) {
		@SuppressLint("SimpleDateFormat")
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		fmt.setCalendar(c);
		return fmt.format(c.getTime());
	}

	static GregorianCalendar getDate(String dateText) {
		String[] dateTime = dateText.split(" ");
		String[] date = dateTime[0].split("-");
		String[] time = dateTime[1].split(":");
		return new GregorianCalendar(
				Integer.parseInt(date[0]),
				Integer.parseInt(date[1]),
				Integer.parseInt(date[2]),
				Integer.parseInt(time[0]),
				Integer.parseInt(time[1])
		);
	}
}
