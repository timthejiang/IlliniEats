package com.cs196.illinieats;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;

public class FirstPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_first_page, menu);
		return true;
	}
	
	public void goToMenu(View view)
	{
		Intent intent = new Intent(this, DisplayMenusActivity.class);
		startActivity(intent);
	}
	
	public void checkBalance(View view)
	{
		Intent intent = new Intent(this, CheckBalanceActivity.class);
		startActivity(intent);
	}
	
	public void favoritesList(View view)
	{
		Intent intent = new Intent(this, DisplayFavoritesActivity.class);
		startActivity(intent);

	}
	
	public void showInfo(View view)
	{
		Intent intent = new Intent(this, DiningHallInfo.class);
		startActivity(intent);
	}

}