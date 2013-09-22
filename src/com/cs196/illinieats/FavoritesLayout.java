package com.cs196.illinieats;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
class FavoritesLayout extends LinearLayout implements OnClickListener{

	private TextView favorite;
	private Context context;
	private boolean selected = false;
	private FavoritesDatabaseHelper db;
	public FavoritesLayout(Context context, String text, boolean addable) {
		super(context);
		favorite = new TextView(context);
		favorite.setText(text);
		favorite.setTextColor(Color.WHITE);
		this.addView(favorite);		
		this.setOnClickListener(this);
		this.context = context;
		db = new FavoritesDatabaseHelper(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	/*
	 * TODO
	 * Combine the two methods below, is CRUFTY
	 */
	public void onClick(View v) {
		if(selected)
		{
			this.setSelected(false);
			db.removeFavorite((String)favorite.getText());
		    Toast toast = Toast.makeText(context, "Removed From Favorites", Toast.LENGTH_SHORT);
		    toast.show();

		}
		else
		{
			this.setSelected(true);
			db.addFavorite((String)favorite.getText());
		    Toast toast = Toast.makeText(context, "Added To Favorites", Toast.LENGTH_SHORT);
		    toast.show();

		}
	}
	
	
	public void setSelected(boolean val)
	{
		selected = val;
		if(selected)
		{
		    favorite.setTypeface(null, Typeface.BOLD);
		    favorite.setTextColor(Color.parseColor("#F47F24"));
		}
		else
		{
			favorite.setTypeface(null, Typeface.NORMAL);
		    favorite.setTextColor(Color.WHITE);
		}

	}
}