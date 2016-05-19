package com.example.mobiletheft;

import launcher.SetPIN;
import bean.GPSLocation;
import gps.GetMyLocation;
import gps.NetworkCondition;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AntitheftFrontPage extends Activity implements OnClickListener {

	TextView tvAntitheftSIM, tvAntitheftIMEI, tvAntitheftMobile,
			tvAntitheftPIN, tvAntitheftLocation;
	Button bAntitheftChangePassword;
	TelephonyManager tManager;
	SharedPreferences pref;
	NetworkCondition nc;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(), MainModule.class));
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.antitheft_front_page);
		tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		nc = new NetworkCondition(getApplicationContext());

		tvAntitheftIMEI = (TextView) findViewById(R.id.tvAntitheftIMEI);
		tvAntitheftLocation = (TextView) findViewById(R.id.tvAntitheftLocation);
		tvAntitheftMobile = (TextView) findViewById(R.id.tvAntitheftMobile);
		tvAntitheftPIN = (TextView) findViewById(R.id.tvAntitheftPIN);
		tvAntitheftSIM = (TextView) findViewById(R.id.tvAntitheftSIM);
		bAntitheftChangePassword = (Button) findViewById(R.id.bAntitheftAssignMobileNumber);

		setSIM();
		setIMEI();
		setMobile();
		setPIN();
		setLocation();
		bAntitheftChangePassword.setOnClickListener(this);
	}

	private void setPIN() {
		// TODO Auto-generated method stub
		tvAntitheftPIN.setText(pref.getString("SecureIt_Create_PIN", null));
	}

	private void setLocation() {
		// TODO Auto-generated method stub
		if (nc.isNetworkAccessible()) {
			GetMyLocation getLoc = new GetMyLocation(getApplicationContext());
			GPSLocation loc = getLoc.fetchLocation();
			if (loc != null) {
				tvAntitheftLocation.setText(loc.getLatitude() + ":"
						+ loc.getLongitude());
			}
		}
	}

	private void setIMEI() {
		// TODO Auto-generated method stub
		tvAntitheftIMEI.setText(tManager.getDeviceId());
	}

	private void setMobile() {
		// TODO Auto-generated method stub
		if (pref.getString("Antitheft_stored_mobile", null) != null)
			tvAntitheftMobile.setText(pref.getString("Antitheft_stored_mobile",
					null));
		else
			tvAntitheftMobile.setText("Not Defined.");

	}

	private void setSIM() {
		// TODO Auto-generated method stub
		String SIMNUMBER = tManager.getSimSerialNumber();
		Editor edit = pref.edit();
		edit.putString("Antitheft_SIM_Number", SIMNUMBER);
		edit.commit();
		tvAntitheftSIM.setText(SIMNUMBER);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bAntitheftAssignMobileNumber:
			Intent i1 = new Intent(getApplicationContext(),
					AntitheftSetMobile.class);
			startActivity(i1);
			finish();
			break;
		}
	}
}
