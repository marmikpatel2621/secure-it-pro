package com.example.mobiletheft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AntitheftSetMobile extends Activity implements OnClickListener {
	EditText eMobile;
	Button bOk;
	SharedPreferences getPref;
	boolean valueFlag;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(),
				AntitheftFrontPage.class));
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobilenumber);
		getPref = PreferenceManager.getDefaultSharedPreferences(this);
		eMobile = (EditText) findViewById(R.id.etAntitheftSetMobileNumber);
		setValue();
		bOk = (Button) findViewById(R.id.bAntitheftMobileSetButton);
		bOk.setOnClickListener(this);

	}

	private void setValue() {
		// TODO Auto-generated method stub
		if (getPref.getString("Antitheft_stored_mobile", null) != null) {
			eMobile.setText(getPref.getString("Antitheft_stored_mobile", null));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String mobilenum = eMobile.getText().toString();
		if (mobilenum.length() >= 10 && mobilenum.length() <= 12) {
			Editor confirmEdit = getPref.edit();
			confirmEdit.putString("Antitheft_stored_mobile", mobilenum);
			confirmEdit.commit();
			Intent ourIntent = new Intent(getApplicationContext(),
					AntitheftFrontPage.class);
			ourIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			ourIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(ourIntent);

		} else {
			showWarningMessage("Insert valid Mobile Number.");
		}

	}

	private boolean showWarningMessage(String Msg) {
		// TODO Auto-generated method stub
		valueFlag = false;
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Warning")
				.setMessage(Msg)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						valueFlag = false;
					}

				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								valueFlag = false;
							}
						}).show();
		return valueFlag;
	}
}
