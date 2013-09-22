package com.cs196.illinieats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;

public class DiningHallInfo extends Activity {
	
    public static final String BUSEY_EVANS = "0";
    public static final String F_A_R = "1";
    public static final String IKENBERRY = "2";
    public static final String I_S_R = "3";
    public static final String L_A_R = "4";
    public static final String P_A_R = "5";
    public static String EXTRA_MESSAGE = "0";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_hall_info);   
		getActionBar().setDisplayHomeAsUpEnabled(true);

    }

	public void goBuseyEvans(View view){
		Intent intent= new Intent(this, DiningHallMenu.class);
		intent.putExtra(EXTRA_MESSAGE, BUSEY_EVANS);
		startActivity(intent);
	}
	
	public void goFAR(View view){
		Intent intent= new Intent(this, DiningHallMenu.class);
		intent.putExtra(EXTRA_MESSAGE, F_A_R);
		startActivity(intent);
	}
	
	public void goIkenberry(View view){
		Intent intent= new Intent(this, DiningHallMenu.class);
		intent.putExtra(EXTRA_MESSAGE, IKENBERRY);
		startActivity(intent);
	}
	
	public void goISR(View view){
		Intent intent= new Intent(this, DiningHallMenu.class);
		intent.putExtra(EXTRA_MESSAGE, I_S_R);
		startActivity(intent);
	}
	
	public void goLAR(View view){
		Intent intent= new Intent(this, DiningHallMenu.class);
		intent.putExtra(EXTRA_MESSAGE, L_A_R);
		startActivity(intent);
	}
	
	public void goPAR(View view){
		Intent intent= new Intent(this, DiningHallMenu.class);
		intent.putExtra(EXTRA_MESSAGE, P_A_R);
		startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}