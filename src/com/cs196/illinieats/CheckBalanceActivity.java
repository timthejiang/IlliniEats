package com.cs196.illinieats;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
@TargetApi(9)
public class CheckBalanceActivity extends Activity {

	private final String MOBILE_LOGIN = "https://eas.admin.uillinois.edu/eas/servlet/EasLogin?redirect=https://webprod.admin.uillinois.edu/bl/servlet/BannerLogin?appName=edu.uillinois.aits.uiHousingBL_illinois";
	//private final String LOGIN_URL = "https://eas.admin.uillinois.edu/eas/servlet/EasLogin?redirect=https://webprod.admin.uillinois.edu/bl/servlet/BannerLogin?appName=edu.uillinois.aits.uiHousingBL_illinois";
	private final String BALANCES_URL = "https://apps-s.housing.illinois.edu/MyBalances/";
	private static boolean firstLogin = true;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);
       
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
              
        WebView webView = (WebView)findViewById(R.id.webView1); 
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        
        webView.setWebViewClient(new WebViewClient()
        {    	      	 	
        	 @Override
        	 public boolean shouldOverrideUrlLoading(WebView view, String url) {
        	        view.loadUrl(url);
        	        return true;
        	 	}
        	 
        	 @Override
        	 public void onPageFinished(WebView view, String url)
        	 {
        		 if(url.equals("https://apps-s.housing.illinois.edu/WebLogin/Default.aspx"))
        			 view.loadUrl(BALANCES_URL);
        	 }
        	 });
        if(firstLogin)
        {
        	webView.loadUrl(MOBILE_LOGIN);
        	firstLogin = false;
        }
        else
        	webView.loadUrl(BALANCES_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_check_balance, menu);
        return true;
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