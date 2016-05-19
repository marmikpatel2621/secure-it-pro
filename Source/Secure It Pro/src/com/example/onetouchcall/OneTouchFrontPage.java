package com.example.onetouchcall;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

public class OneTouchFrontPage extends Activity implements OnClickListener {
	TextView eNumber;
	Button bContact;
	SharedPreferences pref;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(), MainModule.class));
		finish();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.onetouch_front_page);
		eNumber = (TextView) findViewById(R.id.etOneTouchNumber);
		bContact = (Button) findViewById(R.id.bOneTouchSelectContact);
		bContact.setOnClickListener(this);
		pref = PreferenceManager.getDefaultSharedPreferences(this);

		try {
			Intent i = getIntent();
			if (pref.getString("OneTouched_stored_mobile", null) != null) {
				eNumber.setText(pref
						.getString("OneTouched_stored_mobile", null));
			} else
				eNumber.setText("");
		} catch (Exception e) {
			Log.d("Contact_Fetch_Error", "Error while fetching contact.");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bOneTouchSelectContact:
			Intent iContact;
			try {
				iContact = new Intent(getApplicationContext(),
						ContactList.class);
				startActivity(iContact);
				finish();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}
	}
}
