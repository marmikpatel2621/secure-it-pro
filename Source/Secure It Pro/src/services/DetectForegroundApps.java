package services;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.applock.ApplockLockScreen;

public class DetectForegroundApps extends Service {

	private Context mContext;
	private List apps = null;
	String foregroundTaskAppName = null;
	public static final long NOTIFY_INTERVAL = 3 * 1000; // 10 seconds
	public static String s = null;
	public static String t = null;
	public static String u = null, v = null, w = null, x = null, y = null,
			z = null;

	public static int counter = 0;
	SharedPreferences pref;
	// run on another Thread to avoid crash
	private Handler mHandler = new Handler();
	// timer handling
	private Timer mTimer = null;

	@Override
	public void onCreate() {

		pref = PreferenceManager.getDefaultSharedPreferences(this);
		if (mTimer != null) {
			mTimer.cancel();
		} else {
			// recreate new
			mTimer = new Timer();
		}
		// schedule task
		if (pref.getString("appName", null) != null) {

			mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0,
					NOTIFY_INTERVAL);
		}
	}

	class TimeDisplayTimerTask extends TimerTask {

		@Override
		public void run() {
			// run on another thread
			mHandler.post(new Runnable() {

				@Override
				public void run() {
					// display toast
					// Toast.makeText(getApplicationContext(),
					// "Run At Regular Interval",
					// Toast.LENGTH_SHORT).show();
					new MyAsycClass().execute();
				}

			});
		}

		private class MyAsycClass extends AsyncTask<Void, Void, Void> {

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				callAppLock();
				return null;
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				// callAppLock();
			}

			private void callAppLock() {
				// TODO Auto-generated method stub

				String appName = pref.getString("appName", null);
				// int countApp=pref.getInt("countApp", 0);
				// Toast.makeText(getApplicationContext(), appName+" MyService",
				// Toast.LENGTH_LONG).show();

				ActivityManager am = (ActivityManager) DetectForegroundApps.this
						.getSystemService(ACTIVITY_SERVICE);
				RunningTaskInfo foregroundTaskInfo = am.getRunningTasks(1).get(
						0);
				// Toast.makeText(getApplicationContext(), "My Service Running",
				// Toast.LENGTH_LONG).show();

				String foregroundTaskPackageName = foregroundTaskInfo.topActivity
						.getPackageName();
				PackageManager pm = DetectForegroundApps.this
						.getPackageManager();
				PackageInfo foregroundAppPackageInfo = null;
				try {
					foregroundAppPackageInfo = pm.getPackageInfo(
							foregroundTaskPackageName, 0);
					foregroundTaskAppName = foregroundAppPackageInfo.applicationInfo
							.loadLabel(pm).toString();
					// Toast.makeText(getApplicationContext(),foregroundTaskPackageName
					// , Toast.LENGTH_LONG).show();

					// loop

					// Toast.makeText(getApplicationContext(),
					// "Number of Apps "+countApp, Toast.LENGTH_LONG).show();
					if (!(appName.contains(foregroundTaskAppName))) {
						// Toast.makeText(getApplicationContext(),"Application Not Locked"
						// , Toast.LENGTH_LONG).show();
					}

					if (counter == 0) {
						if (appName.contains(foregroundTaskAppName)) {
							s = foregroundTaskAppName;
							counter = 1;

							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);

						}

					} else if (counter == 1) {
						if (s.equals(foregroundTaskAppName)) {
							// Toast.makeText(getApplicationContext(),
							// "app was unlocked", Toast.LENGTH_LONG).show();
						} else if (appName.contains(foregroundTaskAppName)) {
							t = foregroundTaskAppName;
							counter = 2;
							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);
						}
					} else if (counter == 2) {
						if (t.equals(foregroundTaskAppName)) {
							// Toast.makeText(getApplicationContext(),
							// "app was unlocked", Toast.LENGTH_LONG).show();
						} else if (appName.contains(foregroundTaskAppName)) {
							u = foregroundTaskAppName;
							counter = 3;
							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);
						}
					} else if (counter == 3) {
						if (u.equals(foregroundTaskAppName)) {
							// Toast.makeText(getApplicationContext(),
							// "app was unlocked", Toast.LENGTH_LONG).show();
						} else if (appName.contains(foregroundTaskAppName)) {
							v = foregroundTaskAppName;
							counter = 4;
							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);
						}
					} else if (counter == 4) {
						if (v.equals(foregroundTaskAppName)) {
							// Toast.makeText(getApplicationContext(),
							// "app was unlocked", Toast.LENGTH_LONG).show();
						} else if (appName.contains(foregroundTaskAppName)) {
							w = foregroundTaskAppName;
							counter = 5;
							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);
						}
					} else if (counter == 5) {
						if (w.equals(foregroundTaskAppName)) {
							// Toast.makeText(getApplicationContext(),
							// "app was unlocked", Toast.LENGTH_LONG).show();
						} else if (appName.contains(foregroundTaskAppName)) {
							x = foregroundTaskAppName;
							counter = 6;
							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);
						}
					} else if (counter == 6) {
						if (x.equals(foregroundTaskAppName)) {
							// Toast.makeText(getApplicationContext(),
							// "app was unlocked", Toast.LENGTH_LONG).show();
						} else if (appName.contains(foregroundTaskAppName)) {
							y = foregroundTaskAppName;
							counter = 7;
							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);
						}
					} else if (counter == 7) {
						if (y.equals(foregroundTaskAppName)) {
							// Toast.makeText(getApplicationContext(),
							// "app was unlocked", Toast.LENGTH_LONG).show();
						} else if (appName.contains(foregroundTaskAppName)) {
							z = foregroundTaskAppName;
							counter = 8;
							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);
						}
					} else if (counter == 8) {
						if (z.equals(foregroundTaskAppName)) {
							// Toast.makeText(getApplicationContext(),
							// "app was unlocked", Toast.LENGTH_LONG).show();
						} else if (appName.contains(foregroundTaskAppName)) {
							s = foregroundTaskAppName;
							counter = 0;
							Intent lockIntent = new Intent(
									getApplicationContext(),
									ApplockLockScreen.class);
							lockIntent.putExtra(
									"Applock_Foreground_PackageName",
									foregroundTaskPackageName);
							lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(lockIntent);
						}
					}

				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	@Override
	public IBinder onBind(Intent intent) {

		// TODO Auto-generated method stub
		return null;
	}
}