package com.example.mobiletheft;

import setting.OldPassword;

import com.example.applock.ApplockFrontPage;
import com.example.datahiding.DataHidingFrontPage;
import com.example.onetouchcall.OneTouchFrontPage;

import launcher.SetPIN;
import aboutus.Developers;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainModule extends Activity implements OnClickListener {
	ImageView iAntitheft, iOneTouchCall, iApplock, iDataHiding, iSetting,
			iAboutUs;
	SharedPreferences pref;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.mainmodule);
		iAntitheft = (ImageView) findViewById(R.id.ivAntitheft);
		iOneTouchCall = (ImageView) findViewById(R.id.ivonetouchcall);
		iApplock = (ImageView) findViewById(R.id.ivAppLock);
		iDataHiding = (ImageView) findViewById(R.id.ivDataHiding);
		iSetting = (ImageView) findViewById(R.id.ivSetting);
		iAboutUs = (ImageView) findViewById(R.id.ivAboutUs);
		iAntitheft.setOnClickListener(this);
		iOneTouchCall.setOnClickListener(this);
		iApplock.setOnClickListener(this);
		iDataHiding.setOnClickListener(this);
		iAboutUs.setOnClickListener(this);
		iSetting.setOnClickListener(this);

		pref = PreferenceManager.getDefaultSharedPreferences(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ivAntitheft:
			Intent openAntitheft;
			if (pref.getString("Antitheft_login_successfully_done", null) != null) {

				openAntitheft = new Intent(getApplicationContext(),
						AntitheftFrontPage.class);
				openAntitheft.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
				startActivity(openAntitheft);
				finish();
			} else {
				openAntitheft = new Intent(getApplicationContext(),
						RegistrationForm.class);
				startActivity(openAntitheft);
				finish();
			}
			break;
		case R.id.ivonetouchcall:
			Intent openOneTouch = new Intent(getApplicationContext(),
					OneTouchFrontPage.class);
			startActivity(openOneTouch);
			break;
		case R.id.ivAppLock:
			Intent openApplock = new Intent(getApplicationContext(),
					ApplockFrontPage.class);
			startActivity(openApplock);
			break;
		case R.id.ivDataHiding:
			Intent openDataHiding = new Intent(getApplicationContext(),
					DataHidingFrontPage.class);
			startActivity(openDataHiding);

			break;
		case R.id.ivSetting:
			Intent openSetting = new Intent(getApplicationContext(),
					OldPassword.class);
			startActivity(openSetting);

			break;
		case R.id.ivAboutUs:
			Intent openAboutUs = new Intent(getApplicationContext(),
					Developers.class);
			startActivity(openAboutUs);

			break;
		}
	}
}
