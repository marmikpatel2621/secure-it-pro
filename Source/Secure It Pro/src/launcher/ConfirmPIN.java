package launcher;

import com.example.mobiletheft.AntitheftFrontPage;
import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

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

public class ConfirmPIN extends Activity implements OnClickListener {
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
		setContentView(R.layout.confirmscreen);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		tv = (TextView) findViewById(R.id.tvConfirmScreenText);
		tv.setText("Confirm PIN");
		initialize();
		for (int i = 0; i < 10; i++) {
			b[i].setOnClickListener(this);
		}
		bOk.setOnClickListener(this);
		bDel.setOnClickListener(this);

	}

	private void initialize() {
		// TODO Auto-generated method stub
		b[0] = (Button) findViewById(R.id.b20);
		b[1] = (Button) findViewById(R.id.b21);
		b[2] = (Button) findViewById(R.id.b22);
		b[3] = (Button) findViewById(R.id.b23);
		b[4] = (Button) findViewById(R.id.b24);
		b[5] = (Button) findViewById(R.id.b25);
		b[6] = (Button) findViewById(R.id.b26);
		b[7] = (Button) findViewById(R.id.b27);
		b[8] = (Button) findViewById(R.id.b28);
		b[9] = (Button) findViewById(R.id.b29);
		bOk = (Button) findViewById(R.id.b2Ok);
		bDel = (Button) findViewById(R.id.b2Del);

		et[0] = (EditText) findViewById(R.id.et21);
		et[1] = (EditText) findViewById(R.id.et22);
		et[2] = (EditText) findViewById(R.id.et23);
		et[3] = (EditText) findViewById(R.id.et24);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.b20:

			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(0 + "");
			}

			break;
		case R.id.b21:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(1 + "");
			}

			break;
		case R.id.b22:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(2 + "");
			}

			break;
		case R.id.b23:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(3 + "");
			}

			break;
		case R.id.b24:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(4 + "");

			}

			break;
		case R.id.b25:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(5 + "");

			}
			break;
		case R.id.b26:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(6 + "");
			}

			break;
		case R.id.b27:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(7 + "");
			}

			break;
		case R.id.b28:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(8 + "");
			}

			break;
		case R.id.b29:
			if (myVal >= 0 && myVal < 4) {
				myVal++;
				et[myVal - 1].setText(9 + "");
			}

			break;
		case R.id.b2Ok:
			String txt = et[0].getText().toString()
					+ et[1].getText().toString() + et[2].getText().toString()
					+ et[3].getText().toString();
			Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG)
					.show();
			if (myVal == 4) {
				String firstPass = pref.getString("SecureIt_Create_PIN", null);
				if (firstPass.equals(txt)) {
					myVal = 0;
					comformIt();
				} else {
					myVal = 0;
					falseIt();
				}
			}
			break;
		case R.id.b2Del:

			if (myVal > 0) {
				et[myVal - 1].setText("");
				myVal--;
			}
			break;

		}
	}

	private void falseIt() {
		// TODO Auto-generated method stub
		Intent ourIntent2 = new Intent(getApplicationContext(),
				ConfirmPIN.class);
		startActivity(ourIntent2);
	}

	private void comformIt() {
		// TODO Auto-generated method stub
		Editor confirmEdit = pref.edit();
		confirmEdit.putString("SecureIt_PIN_Created", "true");
		confirmEdit.commit();
		Intent ourIntent = new Intent(getApplicationContext(), MainModule.class);
		startActivity(ourIntent);
	}
}
