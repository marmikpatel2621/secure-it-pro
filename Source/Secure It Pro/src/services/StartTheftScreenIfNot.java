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
import android.widget.Toast;

import com.example.mobiletheft.TheftScreenActive;

public class StartTheftScreenIfNot extends Service {
	private Context mContext;
	public static final long NOTIFY_INTERVAL = 10 * 1000; // 10 seconds
	// run on another Thread to avoid crash
	private Handler mHandler = new Handler();
	// timer handling
	private Timer mTimer = null;
	SharedPreferences pref;
	static public boolean goAhead = true;

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Destroy", Toast.LENGTH_SHORT)
				.show();
		super.onDestroy();
	}

	@Override
	public void onCreate() {
		// Toast.makeText(this, "Congrats! MyService Created",
		// Toast.LENGTH_LONG)
		// .show();
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		if (mTimer != null) {
			mTimer.cancel();
		} else {
			// recreate new
			mTimer = new Timer();
		}
		// schedule task
		if (goAhead)
			mTimer.scheduleAtFixedRate(new SIMTimerClass(), 0, NOTIFY_INTERVAL);

	}

	private class SIMTimerClass extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mHandler.post(new Runnable() {

				@Override
				public void run() {
					Toast.makeText(getApplicationContext(),
							"StartTheftScreenIfNot", Toast.LENGTH_LONG).show();
					if (goAhead) {
						Toast.makeText(getApplicationContext(),
								"StartTheftScreenIfNot", Toast.LENGTH_LONG)
								.show();
						goAhead = false;
						Intent i = new Intent(getApplicationContext(),
								TheftScreenActive.class);
						i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(i);
					}
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
