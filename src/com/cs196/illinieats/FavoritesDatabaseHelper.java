package com.cs196.illinieats;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "favoritesDB";
	private static final String TABLE_FAVORITES = "favorites";
	
	private static final String KEY_FOOD = "food";

	public FavoritesDatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + 
				"("+ KEY_FOOD + " TEXT" + ")";
        db.execSQL(CREATE_FAVORITES_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
		 
        // Create tables again
        onCreate(db);

	}
	
	public void addFavorite(String favorite)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues val = new ContentValues();
		val.put(KEY_FOOD, favorite);
		
		db.insert(TABLE_FAVORITES, null, val);
		db.close();
	}
	
	public ArrayList<String> getAllFavorites()
	{
		ArrayList<String> allFavorites = new ArrayList<String>();
		String query = "SELECT * FROM " + TABLE_FAVORITES;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			while(cursor.moveToNext())
			{
				allFavorites.add(cursor.getString(0));
			}
		}
		cursor.close();
		db.close();
		
		return allFavorites;
	}
	
	public int getNumFavorites()
	{
		String query = "SELECT * FROM " + TABLE_FAVORITES;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		cursor.close();
		db.close();
		return cursor.getCount();
	}
	
	public void removeFavorite(String favorite)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FAVORITES, KEY_FOOD + "= ?", new String[] {favorite});
		db.close();
	}
	
	public boolean isInDB(String favorite)
	{
		String query = "SELECT * FROM " + TABLE_FAVORITES;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst())
		{
			while(cursor.moveToNext())
			{
				if(cursor.getString(0).equals(favorite))
				{	db.close();
					return true;
				}
			}
		}
		db.close();
		return false;
	}

}
