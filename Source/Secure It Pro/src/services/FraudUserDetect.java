package services;

import bean.GPSLocation;
import sms.SendSMS;
import gps.GetMyLocation;
import gps.NetworkCondition;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class FraudUserDetect extends Service {

	NetworkCondition nc;
	GetMyLocation gl;
	SharedPreferences pref;
	SendSMS ss;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		nc = new NetworkCondition(getApplicationContext());
		gl = new GetMyLocation(getApplicationContext());
		ss = new SendSMS(getApplicationContext());
		pref = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "FraudUser_Service",
				Toast.LENGTH_LONG).show();
		// Wait for network to connect to internet
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					// check if connected!
					while (!nc.isNetworkAccessible()) {
						// Wait to connect
						Thread.sleep(1000);
					}

				} catch (Exception e) {

				}
			}

		};
		t.start();
		GPSLocation loc = gl.fetchLocation();
		Toast.makeText(getApplicationContext(),
				"loc:" + loc.getLatitude() + ":" + loc.getLongitude(),
				Toast.LENGTH_LONG).show();
		ss.sendSMS(loc, pref.getString("Antitheft_stored_mobile", null));
		return startId;
	}
}
