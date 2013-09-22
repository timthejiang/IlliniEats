package com.cs196.illinieats;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView.OnEditorActionListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMenusActivity extends Activity implements OnItemSelectedListener {
	
	private Spinner spinner;
	private HashSet<String> favorites_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_menus);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		spinner = (Spinner) findViewById(R.id.hall_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.hall_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
				
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
				
		favorites_list = new HashSet<String>();
		try {
			DataInputStream in = new DataInputStream(this.openFileInput("favorites_list.txt"));
			String line = "";
			while((line = in.readUTF()) != null)
			{
				favorites_list.add(line);
				Log.d("Favorites List Contains", line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("DisplayMenuActivity", "Reading UTF strings in Display Menu not working");
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH) + 1; //starts at 0
		int day = c.get(Calendar.DAY_OF_MONTH);
		int year = c.get(Calendar.YEAR); //####
		String yearModded = Integer.toString(year).substring(2,4); //2013 -> 13
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		EditText dateEditText = (EditText)findViewById(R.id.date_text);	
		dateEditText.setText(month+ "/" +day+ "/" +yearModded);
		dateEditText.setCursorVisible(false);
		
		dateEditText.setOnEditorActionListener(new OnEditorActionListener(){
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				
				Toast invalidDateFormatToast = Toast.makeText(getApplicationContext(), "Invalid date format", Toast.LENGTH_LONG);
				
				String dateTextValue = ((TextView) v).getText().toString();			
				String[] dateSplit = dateTextValue.split("/");
				
				if(dateSplit.length != 3)
				{
					invalidDateFormatToast.show();
					return false;
				}
				
				if(dateSplit[0].length() > 2 || dateSplit[0].length() < 1 || dateSplit[1].length() > 2 || dateSplit[1].length() < 1 || dateSplit[2].length() > 4 || dateSplit[2].length() < 2 || dateSplit[2].length() == 3)
				{
					invalidDateFormatToast.show();
					return false;
				}
				boolean passed = false;
				int slashCount = 0;
				
				for(int i = 0; i < dateTextValue.length(); i++)
				{	 
					if(dateTextValue.charAt(i) == '/')
					{
						slashCount++;
					}
					
					if(dateTextValue.charAt(i) != '/' && dateTextValue.charAt(i) < '0' || dateTextValue.charAt(i) > '9')
					{
						i = dateTextValue.length();
						slashCount = 0; //doesn't matter anymore
					}					 
				}

				if(slashCount == 2)
					passed = true;
			
				if(passed)
				{
					if(dateSplit[2].length() == 4)
						dateTextValue = dateSplit[0]+ "-" +dateSplit[1]+ "-" + dateSplit[2];
					else
						dateTextValue = dateSplit[0]+ "-" +dateSplit[1]+ "-20" + dateSplit[2];				

					Toast retrievingToast = Toast.makeText(getApplicationContext(), "Retrieving . . .", Toast.LENGTH_SHORT);
					retrievingToast.show();
					
					onItemSelectedDateWrapper(spinner.getSelectedItemPosition(), dateTextValue);
 
					retrievingToast.cancel();
					Toast doneToast = Toast.makeText(getApplicationContext(), "Loaded", Toast.LENGTH_SHORT);
			    	doneToast.show();
					
					InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			        v.setCursorVisible(false);		          
			         
					return true; 
				}
				else
				{
					invalidDateFormatToast.show();
					return false;
				}
				
			}
		});	
		
		dateEditText.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	           ((TextView) v).setCursorVisible(true);
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_menus, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
    	String url = "http://www.housing.illinois.edu/Current/Dining/Menus.aspx?RIndex="+pos;
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
    	
    	page = cleanHtml(Html.fromHtml(page).toString());
    
    	String[] lines = page.split("\n");
    	LinearLayout menu_layout = (LinearLayout) findViewById(R.id.menu_list);
    	menu_layout.removeAllViews();
    	
    	for(String line : lines)
    	{
    		if((line.contains("Breakfast •") || line.contains("Lunch •") || line.contains("Dinner •")) && !line.contains("Dinner Rolls"))
    		{
    			if(!line.contains("Breakfast"))
				{
					TextView section = new TextView(this);
					menu_layout.addView(section);
				}
    			
    			TextView title = new TextView(this);
    				
    			SpannableString content = new SpannableString(line.substring(0, line.indexOf("•")));
        		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        			
        		title.setText(content);
        		title.setTextColor(Color.parseColor("#F47F24"));
        		title.setTextSize(1,18);
        		menu_layout.addView(title);
    		}
    		else if(line.contains(":") && !line.contains("Eat"))
    		{
    			String[] foods = line.split(",");
    			for(String food : foods)
    			{
    				if(food.contains(":"))
    					food = food.substring(food.indexOf(":")+2);
    				food = food.trim();
    				FavoritesLayout favorite = new FavoritesLayout(this, food, true);
    				if(favorites_list.contains(food))
    				{
    					favorite.setSelected(true);
    				}
    				menu_layout.addView(favorite);
    			}
    		}
    		
    		try {
    			DataOutputStream out = 
    		            new DataOutputStream(this.openFileOutput("favorites_list.txt", Context.MODE_PRIVATE));
    			//out.writeUTF("");
    		    out = new DataOutputStream(this.openFileOutput("favorites_list.txt", Context.MODE_APPEND));
    			for(String item : favorites_list)
    		    {	
    		    	out.writeUTF(item);
    		    }
    			out.flush();
    			out.close();
    		} catch (IOException e) {
    			Log.d("FavoritesLayout", "Adding to favorites didn't work");
    		}

    		menu_layout.setVerticalScrollBarEnabled(true);
    	}
    }
    
    public void onItemSelectedDateWrapper(int pos, String date) {
    	String url = "http://www.housing.illinois.edu/Current/Dining/Menus.aspx?RIndex="+pos+ "&Date=" +date;
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
    	
    	page = cleanHtml(Html.fromHtml(page).toString());
    
    	String[] lines = page.split("\n");
    	LinearLayout menu_layout = (LinearLayout) findViewById(R.id.menu_list);
    	menu_layout.removeAllViews();
    	
    	for(String line : lines)
    	{
    		if((line.contains("Breakfast •") || line.contains("Lunch •") || line.contains("Dinner •")) && !line.contains("Dinner Rolls"))
    		{
    				if(!line.contains("Breakfast"))
    				{
    					TextView section = new TextView(this);
    					menu_layout.addView(section);
    				}
        			
        			TextView title = new TextView(this);
        				
        			SpannableString content = new SpannableString(line.substring(0, line.indexOf("•")));
            		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            			
            		title.setText(content);
            		title.setTextColor(Color.parseColor("#F47F24"));
            		title.setTextSize(1,18);
            		menu_layout.addView(title);
    		}
    		else if(line.contains(":") && !line.contains("Eat"))
    		{
    			String[] foods = line.split(",");
    			for(String food : foods)
    			{
    				if(food.contains(":"))
    					food = food.substring(food.indexOf(":")+2);
    				food = food.trim();
    				FavoritesLayout favorite = new FavoritesLayout(this, food, true);
    				if(favorites_list.contains(food))
    				{
    					favorite.setSelected(true);
    				}
    				menu_layout.addView(favorite);
    			}
    		}
    		
    		try {
    			DataOutputStream out = 
    		            new DataOutputStream(this.openFileOutput("favorites_list.txt", Context.MODE_PRIVATE));
    			//out.writeUTF("");
    		    out = new DataOutputStream(this.openFileOutput("favorites_list.txt", Context.MODE_APPEND));
    			for(String item : favorites_list)
    		    {	
    		    	out.writeUTF(item);
    		    }
    			out.flush();
    			out.close();
    		} catch (IOException e) {
    			Log.d("FavoritesLayout", "Adding to favorites didn't work");
    		}

    		menu_layout.setVerticalScrollBarEnabled(true);
    		
    	}
    }
    
    public void onNothingSelected(AdapterView<?> parent) { 
    }
    
    private static String cleanHtml(String src)
    {
    	String result = src.substring(src.indexOf("Serving"), src.indexOf("function"));
    	return result;
    }
    
    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first 
    }
}