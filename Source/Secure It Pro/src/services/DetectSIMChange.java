package services;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.mobiletheft.TheftScreenActive;

public class DetectSIMChange extends Service {
	private Context mContext;
	public static final long NOTIFY_INTERVAL = 1 * 1000; // 10 seconds
	// run on another Thread to avoid crash
	private Handler mHandler = new Handler();
	// timer handling
	private Timer mTimer = null;
	SharedPreferences pref;
	static public boolean goAhead = true;

	@Override
	public void onCreate() {
		Toast.makeText(this, "Congrats! DETECTSIMCHANGE Created",
				Toast.LENGTH_LONG).show();
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		if (mTimer != null) {
			mTimer.cancel();
		} else {
			// recreate new
			mTimer = new Timer();
		}
		// schedule task
		if (goAhead
				&& pref.getString("Antitheft_login_successfully_done", null) != null)
			mTimer.scheduleAtFixedRate(new SIMTimerClass(), 0, NOTIFY_INTERVAL);

	}

	private class SIMTimerClass extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mHandler.post(new Runnable() {

				@Override
				public void run() {
					if (isSIMNotChanged() && goAhead) {
						Toast.makeText(getApplicationContext(), "SIM Ok",
								Toast.LENGTH_LONG).show();
						goAhead = false;
					} else if (!isSIMNotChanged() && goAhead) {
						Toast.makeText(getApplicationContext(),
								"DetectSIMChangeService called ",
								Toast.LENGTH_LONG).show();
						goAhead = true;// true
						Intent i = new Intent(getApplicationContext(),
								TheftScreenActive.class);
						i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(i);
					}

				}

				private boolean isSIMNotChanged() {
					// TODO Auto-generated method stub
					boolean isValid = false;
					TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					String currentSIMNumber = tManager.getSimSerialNumber();
					String storedSIMNumber = null;
					if (pref.getString("Antitheft_SIM_Number", null) != null)
						storedSIMNumber = pref.getString(
								"Antitheft_SIM_Number", null);
					else
						storedSIMNumber = "nothing";
					if (storedSIMNumber.equals(currentSIMNumber)) {

						isValid = true;

					} else {
						isValid = false;

					}
					return isValid;
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
