package com.example.applock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

public class ApplockFrontPage extends Activity implements OnClickListener {
	Intent ourIntent;
	TextView tApplist;
	Button bSetLockOnApps;
	String appName;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(), MainModule.class));
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.applock_front_page);

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);

		appName = pref.getString("appName", null);

		Toast.makeText(getApplicationContext(), appName + " AppList",
				Toast.LENGTH_LONG).show();

		tApplist = (TextView) findViewById(R.id.tApplockFrontText);
		bSetLockOnApps = (Button) findViewById(R.id.bSetLockOnApps);
		bSetLockOnApps.setOnClickListener(this);
		setText();

	}

	private void setText() {
		// TODO Auto-generated method stub
		if (appName == null) {
			tApplist.setText("No Apps were selected.");
		} else {

			tApplist.setText("Following Apps are Selected \n" + appName);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent ourIntent = new Intent(getApplicationContext(),
				ApplockShowAppList.class);
		startActivity(ourIntent);
		finish();
	}

}
