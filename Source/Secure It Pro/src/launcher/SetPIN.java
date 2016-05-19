package launcher;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiletheft.R;

public class SetPIN extends Activity implements OnClickListener {

	Button bOk, bDel;
	Button b[] = new Button[10];
	EditText et[] = new EditText[4];
	static int myVal = 0;
	SharedPreferences pref;
	Editor edit;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lockscreen);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		edit = pref.edit();
		tv = (TextView) findViewById(R.id.tvLockscreenText);
		tv.setText("Enter New PIN");
		initialize();
		for (int i = 0; i < 10; i++) {
			b[i].setOnClickListener(this);
		}
		bOk.setOnClickListener(this);
		bDel.setOnClickListener(this);

	}

	private void initialize() {
		// TODO Auto-generated method stub
		b[0] = (Button) findViewById(R.id.b0);
		b[1] = (Button) findViewById(R.id.b1);
		b[2] = (Button) findViewById(R.id.b2);
		b[3] = (Button) findViewById(R.id.b3);
		b[4] = (Button) findViewById(R.id.b4);
		b[5] = (Button) findViewById(R.id.b5);
		b[6] = (Button) findViewById(R.id.b6);
		b[7] = (Button) findViewById(R.id.b7);
		b[8] = (Button) findViewById(R.id.b8);
		b[9] = (Button) findViewById(R.id.b9);
		bOk = (Button) findViewById(R.id.bOk);
		bDel = (Button) findViewById(R.id.bDel);

		et[0] = (EditText) findViewById(R.id.et1);
		et[1] = (EditText) findViewById(R.id.et2);
		et[2] = (EditText) findViewById(R.id.et3);
		et[3] = (EditText) findViewById(R.id.et4);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.b0:

			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(0 + "");
			}

			break;
		case R.id.b1:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(1 + "");
			}

			break;
		case R.id.b2:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(2 + "");
			}

			break;
		case R.id.b3:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(3 + "");
			}

			break;
		case R.id.b4:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(4 + "");

			}

			break;
		case R.id.b5:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(5 + "");

			}
			break;
		case R.id.b6:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(6 + "");
			}

			break;
		case R.id.b7:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(7 + "");
			}

			break;
		case R.id.b8:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(8 + "");
			}

			break;
		case R.id.b9:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(9 + "");
			}

			break;
		case R.id.bOk:
			String txt = et[0].getText().toString()
					+ et[1].getText().toString() + et[2].getText().toString()
					+ et[3].getText().toString();
			Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG)
					.show();
			if (myVal == 4) {
				edit.putString("SecureIt_Create_PIN", txt);
				edit.commit();
				myVal = 0;
				comformIt();
			}

			break;
		case R.id.bDel:

			if (myVal > 0) {
				et[myVal - 1].setText("");
				myVal--;
			}
			break;

		}
	}

	private void comformIt() {
		// TODO Auto-generated method stub
		Intent ourIntent;
		try {
			Toast.makeText(getApplicationContext(), "call", Toast.LENGTH_LONG)
					.show();
			ourIntent = new Intent(getApplicationContext(), ConfirmPIN.class);
			startActivity(ourIntent);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
