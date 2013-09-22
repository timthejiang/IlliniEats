package com.cs196.illinieats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayFavoritesActivity extends Activity {

	private String[] halls = {"Busey-Evans","FAR","Ikenberry","ISR","LAR","PAR"};
	private LinearLayout favorites_list;
	private Context context;
	private FavoritesDatabaseHelper db;
	private Toast loading;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_favorites);
		favorites_list = (LinearLayout) findViewById(R.id.favorites_list);
		favorites_list.setVerticalScrollBarEnabled(true);
		context = this;
		db = new FavoritesDatabaseHelper(this);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		loading = Toast.makeText(context, "Loading", Toast.LENGTH_LONG);
		loading.show();
		new FavoritesTask().execute(halls);
	}
	
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
	    if (item.getItemId() == android.R.id.home) {
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_favorites, menu);
		return true;
	}
	
	private static String format(String page, String food)
	{
		if(page.contains(food))
		{
			if(page.indexOf(food) < page.indexOf("Lunch"))
				return "Breakfast: " + food;
			else if(page.indexOf(food) < page.indexOf("Dinner"))
				return "Lunch: " + food;
			else
				return "Dinner: " + food;
		}
		return null;
	}
	

	private class FavoritesTask extends AsyncTask<String, Integer, String> {
		Map<String, ArrayList<String>> foodList = new HashMap<String, ArrayList<String>> ();
		
		@Override
		protected String doInBackground(String... hallList) {
			String finalResult = "";
			ArrayList<String> favorites = db.getAllFavorites();
			for(int i = 0; i < hallList.length; i++)
			{
				foodList.put(hallList[i], new ArrayList<String>());
				for(String food : favorites)
				{
		             if (isCancelled()) break;
					String url = "http://www.housing.illinois.edu/Current/Dining/Menus.aspx?RIndex="+i;
			    	DefaultHttpClient httpClient = new DefaultHttpClient();
			    	HttpGet httpGet = new HttpGet(url); 
			    	ResponseHandler<String> resHandler = new BasicResponseHandler();
			    	String page = null;
			    	try {
						 page = httpClient.execute(httpGet, resHandler);
					} catch (ClientProtocolException e) {
						Log.d("DisplayMenusActivity", "Client Protocol Exception");
						e.printStackTrace();
					} catch (IOException e) {
						Log.d("DisplayMenusActivity", "IOException");
						e.printStackTrace();
					}
			    	
			    	page = page.substring(page.indexOf("Serving"), page.indexOf("function"));    
			    	String result = format(page,food);
			    	if(result != null)
			    	{	
			            foodList.get(hallList[i]).add(result); 
			    		finalResult += result + "\n";
			    	}
			    	publishProgress(i/hallList.length * 100);
				}
			}
			return finalResult;
		}
		
	     protected void onProgressUpdate(Integer... progress) {
	         setProgress(progress[0]);
	     }

		
		@Override
		protected void onPostExecute(String result)
		{
			loading.cancel();
			
			for(int i = 0; i < 6; i++)
			{
				TextView title = new TextView(context);
				title.setText(halls[i]);
				title.setTextColor(Color.parseColor("#F47F24"));
				title.setTextSize(1,18);
				favorites_list.addView(title);

				if(foodList.get(halls[i]).size() == 0)
				{
					TextView food = new TextView(context);
					food.setText("No favorites being served here today");
					food.setTextColor(Color.WHITE);
					favorites_list.addView(food);
					
				}
				for(String line : foodList.get(halls[i]))
				{
						TextView food = new TextView(context);
						food.setText(line);
						food.setTextColor(Color.WHITE);
						favorites_list.addView(food);
				}
			}
		}

	}


}
