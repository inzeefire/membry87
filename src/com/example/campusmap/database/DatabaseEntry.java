package com.example.campusmap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DatabaseEntry extends SQLiteOpenHelper implements TableDefinition {

	private SQLiteDatabase db;
	public static final int DATABASE_VERSION = 4;
	public static final String DATABASE_NAME = CAMPUSMAP_DATABASE;

	public DatabaseEntry(Context context) {
		super(context, Environment.getExternalStorageDirectory()
				+ "/CampusMap/" + DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
	}

	public void createTables() {
		db = this.getWritableDatabase();
		createBuildingTable(db);
		createRouteTable(db);
		this.close();
	}

	private void createBuildingTable(SQLiteDatabase db) {
		// Building table

		db.execSQL("CREATE TABLE IF NOT EXISTS " + BUILDING_TABLE + " ("
				+ BUILDING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ BUILDING_NAME + " VARCHAR," + BUILDING_ADDRESS + " VARCHAR, "
				+ LOCATION_LAT + " DOUBLE," + LOCATION_LNG + " DOUBLE, "
				+ CREATE_TIME
				+ " TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))"
				+ ")");
	}

	private void createRouteTable(SQLiteDatabase db) {
		// Route table
		db.execSQL("CREATE TABLE IF NOT EXISTS " + ROUTE_TABLE + " ("
				+ ROUTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ROUTE_FILENAME + " VARCHAR, " + STARTING_LAT + " DOUBLE, "
				+ STARTING_LNG + " DOUBLE, " + ENDING_LAT + " DOUBLE, "
				+ ENDING_LNG + " DOUBLE, " + DISTANCE + " DOUBLE, " + TAKETIME
				+ " INTEGER, " + CREATE_TIME
				+ " TimeStamp NOT NULL DEFAULT (datetime('now','localtime'))"
				+ ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + BUILDING_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + ROUTE_TABLE);
		onCreate(db);
	}

}