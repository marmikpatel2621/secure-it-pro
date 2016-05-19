package com.example.datahiding;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

@SuppressWarnings("deprecation")
public class DataHidingFrontPage extends TabActivity {
	TabHost tabHost;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(), MainModule.class));
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datahiding_front_page);

		// create the TabHost that will contain the Tabs
		tabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabSpec explorer = tabHost.newTabSpec("Explorer");
		TabSpec hidden = tabHost.newTabSpec("Hidden Files");

		// Set the Tab name and Activity
		// that will be opened when particular Tab will be selected

		explorer.setIndicator("Exploere");
		explorer.setContent(new Intent(this, DataHidingExplore.class));

		hidden.setIndicator("Hidden Files");
		hidden.setContent(new Intent(this, HiddenActivity.class));

		tabHost.addTab(explorer);
		tabHost.addTab(hidden);

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String arg0) {

				setTabColor(tabHost);
			}
		});
		setTabColor(tabHost);
	}

	// Change The Backgournd Color of Tabs
	public void setTabColor(TabHost tabhost) {

		/*
		 * for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
		 * tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
		 * //unselected
		 */
		if (tabhost.getCurrentTab() == 0) {
			tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
					.setBackgroundColor(Color.argb(10, 113, 166, 210));// 1st
																		// tab
																		// selected
			tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab() + 1)
					.setBackgroundColor(Color.WHITE);
		} else {
			tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab())
					.setBackgroundColor(Color.argb(10, 113, 166, 210)); // 2nd
																		// tab
																		// selected
			tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab() - 1)
					.setBackgroundColor(Color.WHITE);
		}
	}

}
