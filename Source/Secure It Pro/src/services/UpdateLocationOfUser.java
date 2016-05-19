package services;

import gps.GetMyLocation;

import java.util.Timer;
import java.util.TimerTask;

import web.StoreDeviceLocation;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class UpdateLocationOfUser extends Service {
	private Context mContext;
	public static final long NOTIFY_INTERVAL = 1 * 1000; // 10 seconds
	// run on another Thread to avoid crash
	private Handler mHandler = new Handler();
	// timer handling
	private Timer mTimer = null;
	SharedPreferences pref;
	TelephonyManager tManager;
	GetMyLocation gml;
	StoreDeviceLocation sdl;

	@Override
	public void onCreate() {
		Toast.makeText(this, "Congrats! DETECTSIMCHANGE Created",
				Toast.LENGTH_LONG).show();
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		gml = new GetMyLocation(getApplicationContext());
		sdl = new StoreDeviceLocation(getApplicationContext());
		tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		if (mTimer != null) {
			mTimer.cancel();
		} else {
			// recreate new
			mTimer = new Timer();
		}
		// schedule task
		mTimer.scheduleAtFixedRate(new SIMTimerClass(), 0, NOTIFY_INTERVAL);

	}

	private class SIMTimerClass extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mHandler.post(new Runnable() {

				@Override
				public void run() {
					sdl.storeLocation(gml.fetchLocation(),
							tManager.getDeviceId());
				}

			});

		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
