package launcher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import com.example.mobiletheft.R;
import com.example.mobiletheft.TheftScreenActive;

public class Launcher extends Activity {
	SharedPreferences pref;
	String firstPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.launcher);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		firstPass = pref.getString("SecureIt_PIN_Created", null);

		Thread timer = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();

				try {
					sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (firstPass != null) {
						Intent openPasscodeScreen = new Intent(
								getApplicationContext(), GetPIN.class);
						startActivity(openPasscodeScreen);
						finish();
					} else {
						Intent openMainActivity = new Intent(
								getApplicationContext(), SetPIN.class);

						startActivity(openMainActivity);
						finish();
					}

				}
			}
		};
		timer.start();
	}
}
