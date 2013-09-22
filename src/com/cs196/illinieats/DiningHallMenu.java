package com.cs196.illinieats;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.widget.TextView;

public class DiningHallMenu extends Activity {

	private boolean isOpen(int hall){
		boolean open = true;
		Calendar c = Calendar.getInstance();
		int day  = c.get(Calendar.DAY_OF_WEEK);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		if(hour >= 0 && hour <= 7)
			return false;
		if(hall == 0){
			if(day == Calendar.MONDAY || day == Calendar.TUESDAY || day == Calendar.WEDNESDAY || day == Calendar.THURSDAY || day == Calendar.FRIDAY){
				if(hour >=10 && hour <= 11)
					open = false;
				if(hour >= 13 && minute >= 30 && hour <= 14)
					open = false;
				if(hour >= 14 && hour <= 17)
					open = false;
			}
			if(day == Calendar.SATURDAY)
				open = false;
			if(day == Calendar.SUNDAY)
				if(hour <= 12 || hour >= 18)
					open = false;
		}
		else if(hall == 1){
			if(day == Calendar.MONDAY || day == Calendar.TUESDAY || day == Calendar.WEDNESDAY || day == Calendar.THURSDAY){
				if(hour <= 11)
					open = false;
				if(hour >= 13 && minute >= 30 && hour <= 14)
					open = false;
				if(hour >= 14 && hour <= 16)
					open = false;
				if(hour == 16 && minute <= 45)
					open = false;
				if(hour >= 19 && hour < 20 && minute > 30)
					open = false;
				if(hour >= 20)
					open = false;
			}
			if(day == Calendar.SUNDAY){
				if(hour <= 16 && minute <= 45)
					open = false;
				if(hour >= 19 && minute >= 30)
					open = false;
			}
			if(day == Calendar.FRIDAY){
				if(hour < 11)
					open = false;
				if(hour >= 13 && minute >= 30 && hour < 14)
					open = false;
				if(hour >= 14)
					open = false;
			}
		}
		else if(hall == 2){
			if(day == Calendar.MONDAY || day == Calendar.TUESDAY || day == Calendar.WEDNESDAY || day == Calendar.THURSDAY || day == Calendar.FRIDAY){
				if(hour >= 10 && hour < 11)
					open = false;
				if(hour >= 15 && hour < 16)
					open = false;
				if(hour == 16 && minute <= 30)
					open = false;
				if(hour >= 20)
					open = false;
			}
			if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
				if(hour < 9)
					open = false;
				if(hour >= 10 && hour < 11)
					open = false;
				if(hour >= 14 && hour < 16)
					open = false;
				if(hour == 16 && minute <= 30)
					open = false;
				if(hour >= 20)
					open = false;
			}
			if(day == Calendar.FRIDAY || day == Calendar.SATURDAY)
				if(hour >= 19)
					open = false;
		}
		else if(hall == 3){
			if(day == Calendar.MONDAY || day == Calendar.TUESDAY || day == Calendar.WEDNESDAY || day == Calendar.THURSDAY || day == Calendar.FRIDAY){
				if(hour >= 10 && hour < 11)
					open = false;
				if(hour == 13 && minute >= 30)
					open = false;
				if(hour >= 14 && hour < 16)
					open = false;
				if(hour == 16 && minute <= 45)
					open = false;
				if(hour == 19)
					open = false;
			}
			if(day == Calendar.SATURDAY){
				if(hour < 9)
					open = false;
				if(hour >= 10 && hour < 11)
					open = false;
				if(hour == 13 && minute >= 30)
					open = false;
				if(hour >= 14 && hour < 16)
					open = false;
				if(hour == 16 && minute <= 45)
					open = false;
				if(hour == 18 && minute >= 30)
					open = false;
				if(hour >= 19 && hour < 20)
					open = false;
			}
			if(day == Calendar.SUNDAY){
				if(hour < 11)
					open = false;
				if(hour == 11 && minute <= 30)
					open = false;
				if(hour == 13 && minute >= 30)
					open = false;
				if(hour >= 14 && hour <= 17)
					open = false;
				if(hour >= 19 && hour < 20)
					open = false;
			}
		}
		else if(hall == 4){
			if(day == Calendar.MONDAY || day == Calendar.TUESDAY || day == Calendar.WEDNESDAY || day == Calendar.THURSDAY || day == Calendar.FRIDAY){
				if(hour >= 10 && hour < 11)
					open = false;
				if(hour == 13 && minute >= 30)
					open = false;
				if(hour >= 14 && hour < 16)
					open = false;
				if(hour == 16 && minute <= 30)
					open = false;
				if(hour == 18 && minute >= 30)
					open = false;
				if(hour >= 19)
					open = false;
			}
			if(day == Calendar.SATURDAY){
				if(hour < 9)
					open = false;
				if(hour >= 10 && hour < 11)
					open = false;
				if(hour == 11 && minute <= 30)
					open = false;
				if(hour >= 14 && hour < 17)
					open = false;
				if(hour == 18 && minute >= 30)
					open = false;
				if(hour >= 19)
					open = false;
			}
			if(day == Calendar.SUNDAY){
				if(hour < 9)
					open = false;
				if(hour >= 10 && hour < 11)
					open = false;
				if(hour == 11 && minute <= 30)
					open = false;
				if(hour == 13 && minute >= 30)
					open = false;
				if(hour >= 14 && hour < 17)
					open = false;
				if(hour == 18 && minute >= 30)
					open = false;
				if(hour >= 19)
					open = false;
			}
		}
		else{
				if(hour >= 10 && hour < 11)
					open = false;
				if(hour == 13 && minute >= 30)
					open = false;
				if(hour >= 14 && hour < 16)
					open = false;
				if(hour == 16 && minute <= 30)
					open = false;
				if(hour >= 19 && hour < 20)
					open = false;
			if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
				if(hour < 9)
					open = false;
			}
		}
		return open;
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_hall_menu);
        Intent intent = getIntent();
        String message = intent.getStringExtra(DiningHallInfo.EXTRA_MESSAGE);
        int diningHall = Integer.parseInt(message);
        TextView textView = new TextView(this);
        if(isOpen(diningHall))
        	textView.setText("OPEN\n" + values[diningHall]);
        else 
        	textView.setText("CLOSED\n" + values[diningHall]);
        textView.setTextColor(Color.WHITE);
        setContentView(textView);
        
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dining_hall_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String[] values = {
    		"Busey-Evans \n 1111 W. Nevada, Urbana\n\n Monday-Friday"
 		   + "\nContinental Breakfast: 7:00 - 10:00 a.m."
 		   + "\nEggs to Order: 7:30 - 9:00 a.m."
 		   + "\nFull Hot Breakfast: 7:15 - 9:30 a.m."
 		   + "\nLunch: 11:00 a.m. - 1:30 p.m."
 		   + "\nDinner: 4:45 - 6:30 p.m."
 		   + "\n\nSaturday: CLOSED"
 		   + "\n\nSunday"
 		   + "Soup, Salad & Sandwich Bar: 12:00 noon - 6:00 p.m.", 
 		   
 		   "FAR \n1011 W. College Court, Urbana"
 			+ "\n\nMonday-Friday"
 			+ "\nLunch: 11:00 a.m. - 1:30 p.m."
 			+ "\n\nSunday–Thursday"
 			+ "\nDinner 4:45 p.m. - 7:30 p.m.", 
 			
 			"Ikenberry \n301 E. Gregory Drive, Champaign"
 			+ "\n\nMonday-Friday"
 			+ "\nContinental Breakfast: 7:00 - 10:00 a.m."
 			+ "\nEggs to Order: 7:30 - 9:00 a.m."
 			+ "\nFull Hot Breakfast: 7:15 - 9:30 a.m."
 			+ "\nLunch: 11:00 a.m. - 1:30 p.m."
 			+ "\nLight Lunch: 1:30 - 3:00 p.m."
 			+ "\nDinner: Monday - Thursday: 4:30 - 8:00 p.m.; Friday: 4:30 - 7:00 p.m."
 			+ "\n\nSaturday"
 			+ "\nContinental Breakfast: 9:00 - 10:00 a.m."
 			+ "\nBrunch: 11:00 a.m. - 2:00 p.m."
 			+ "\nDinner: 4:30 - 7:00 p.m."
 			+ "\n\nSunday"
 			+ "\nContinental Breakfast:  9:00 - 10:00 a.m."
 			+ "\nBrunch: 11:00 a.m. - 2:00 p.m."
 			+ "\nDinner: 4:30 - 8:00 p.m.", 
 			
 			"ISR \n1010 W. Illinois Street, Urbana"
 			+"\n\nMonday-Friday"
 			+"\nContinental Breakfast: 7:00 - 10:00 a.m."
 			+"\nEggs to Order: 7:30 - 9:00 a.m."
 			+"\nFull Hot Breakfast: 7:15 - 9:30 a.m."
 			+"\nLunch: 11:00 a.m. - 1:30 p.m."
 			+"\nDinner: 4:45 - 7:00 p.m."
 			+"\nAfter Dark: 8:00 p.m. - 12"
 			+"\n\nSaturday"
 			+"\nContinental Breakfast: 9:00 - 10:00 a.m."
 			+"\nLunch: 11:00 a.m. - 1:30 p.m."
 			+"\nDinner: 4:45 - 6:30 p.m."
 			+"\nAfter Dark: 8:00 p.m. - 12"
 			+"\n\nSunday"
 			+"\nBrunch: 11:30 a.m. - 1:30 p.m."
 			+"\nDinner: 5:00 - 7 p.m."
 			+"\nAfter Dark: 8:00 p.m. - 12",
 			
 			"LAR \n1005 S. Lincoln, Urbana"
 			+"\n\nMonday-Friday"
 			+"\nContinental Breakfast: 7:00 - 10:00 a.m."
 			+"\nEggs to Order: 7:30 - 9:00 a.m."
 			+"\nFull Hot Breakfast: 7:15 - 9:30 a.m."
 			+"\nLunch: 11:00 a.m. - 1:30 p.m."
 			+"\nDinner: 4:45 - 6:30 p.m."			    		 			
 			+"\n\nSaturday"
 			+"\nContinental Breakfast: 9:00 - 10:00 a.m."
 			+"\nLunch: 11:30 a.m. - 1:00 p.m."
 			+"\nDinner: 5:00 - 6:30 p.m."			    		 			
 			+"\n\nSunday"
 			+"\nContinental Breakfast: 9:00 - 10:00 a.m."
 			+"\nBrunch: 11:30 a.m. - 1:30 p.m."
 			+"\nDinner: 5:00 - 6:30 p.m.",
 			
 			"PAR \n906 W. College Ct., Urbana"
 			+"\n\nMonday-Friday"
 			+"\nContinental Breakfast: 7:00 - 10:00 a.m."
 			+"\nEggs to Order: 7:30 - 9:00 a.m."
 			+"\nFull Hot Breakfast: 7:15 - 9:30 a.m."
 			+"\nLunch: 11:00 a.m. - 1:30 p.m."
 			+"\nLight lunch: 1:30 - 3:00 p.m."
 			+"\nDinner: 4:30 - 7:00 p.m."
 			+"\nAll You Care to Eat - After Dark: 8:00 p.m. - 12 Midnight" 
 			+"\n\nSaturday-Sunday"
 			+"\nContinental Breakfast: 9:00 - 10:00 a.m."
 			+"\nBrunch: 11:00 a.m. - 1:30 p.m."
 			+"\nDinner: 4:30 - 7:00  p.m." 
 			+"\nAll You Care to Eat - After Dark: 8:00 p.m. - 12 Midnight"};
}
