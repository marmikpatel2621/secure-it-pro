package com.example.applock;

import services.FraudUserDetect;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

public class ApplockLockScreen extends Activity implements OnClickListener {
	Button bOk, bDel;
	Button b[] = new Button[10];
	EditText et[] = new EditText[4];
	static int myVal = 0;
	SharedPreferences pref;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theftlockscreen);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		tv = (TextView) findViewById(R.id.tvTheftLockScreen);
		tv.setText("Enter PIN");
		initialize();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "BACK", Toast.LENGTH_LONG)
				.show();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		b[0] = (Button) findViewById(R.id.b30);
		b[1] = (Button) findViewById(R.id.b31);
		b[2] = (Button) findViewById(R.id.b32);
		b[3] = (Button) findViewById(R.id.b33);
		b[4] = (Button) findViewById(R.id.b34);
		b[5] = (Button) findViewById(R.id.b35);
		b[6] = (Button) findViewById(R.id.b36);
		b[7] = (Button) findViewById(R.id.b37);
		b[8] = (Button) findViewById(R.id.b38);
		b[9] = (Button) findViewById(R.id.b39);
		bOk = (Button) findViewById(R.id.b3Ok);
		bDel = (Button) findViewById(R.id.b3Del);

		et[0] = (EditText) findViewById(R.id.et31);
		et[1] = (EditText) findViewById(R.id.et32);
		et[2] = (EditText) findViewById(R.id.et33);
		et[3] = (EditText) findViewById(R.id.et34);
		for (int i = 0; i < 10; i++) {
			b[i].setOnClickListener(this);
		}
		bOk.setOnClickListener(this);
		bDel.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.b30:

			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(0 + "");
			}

			break;
		case R.id.b31:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(1 + "");
			}

			break;
		case R.id.b32:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(2 + "");
			}

			break;
		case R.id.b33:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(3 + "");
			}

			break;
		case R.id.b34:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(4 + "");

			}

			break;
		case R.id.b35:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(5 + "");

			}
			break;
		case R.id.b36:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(6 + "");
			}

			break;
		case R.id.b37:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(7 + "");
			}

			break;
		case R.id.b38:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(8 + "");
			}

			break;
		case R.id.b39:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(9 + "");
			}

			break;
		case R.id.b3Ok:
			String txt = et[0].getText().toString()
					+ et[1].getText().toString() + et[2].getText().toString()
					+ et[3].getText().toString();
			Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG)
					.show();

			String setValue = pref.getString("SecureIt_Create_PIN", null);

			Toast.makeText(getApplicationContext(),
					"My Stored Value:" + setValue, Toast.LENGTH_LONG).show();

			if (setValue.equals(txt)) {
				myVal = 0;
				comformIt();

			}

			else {
				Toast.makeText(getApplicationContext(), "Fraud CALLED",
						Toast.LENGTH_LONG).show();
				myVal = 0;
				et[0].setText("");
				et[1].setText("");
				et[2].setText("");
				et[3].setText("");
				// fraud();
			}
			break;
		case R.id.b3Del:

			if (myVal > 0) {
				et[myVal - 1].setText("");
				myVal--;
			}
			break;

		}
	}

	private void fraud() {
		// TODO Auto-generated method stub
		Intent i = new Intent(getApplicationContext(), ApplockLockScreen.class);
		startActivity(i);
	}

	private void comformIt() {
		// TODO Auto-generated method stub
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String value = extras.getString("Applock_Foreground_PackageName");
			// Toast.makeText(getApplicationContext(), value+" Bundle",
			// Toast.LENGTH_LONG).show();
			Intent LaunchIntent = getPackageManager()
					.getLaunchIntentForPackage(value);
			startActivity(LaunchIntent);
			finish();
		}

	}
}
