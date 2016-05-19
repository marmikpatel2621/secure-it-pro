package com.example.mobiletheft;

import gps.GetMyLocation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.UserProfile;
import web.CheckExistence;
import web.RegisterUser;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationForm extends Activity implements OnClickListener {

	EditText name, email, mobile, pass, confirmpass, myseqans;
	Button createAccount;
	boolean valueFlag;
	Spinner spin;
	UserProfile up;
	TelephonyManager tManager;
	CheckExistence reg;
	RegisterUser rm;
	GetMyLocation gml;
	SharedPreferences pref;
	Editor edit;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(), MainModule.class));
		finish();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Initialize the class
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		edit = pref.edit();
		up = new UserProfile();
		tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		reg = new CheckExistence(getApplicationContext());
		rm = new RegisterUser(getApplicationContext());
		gml = new GetMyLocation(getApplicationContext());
		// Get Element
		name = (EditText) findViewById(R.id.etName);
		email = (EditText) findViewById(R.id.etEmail);
		mobile = (EditText) findViewById(R.id.etMobile);
		pass = (EditText) findViewById(R.id.etPass);
		myseqans = (EditText) findViewById(R.id.etSecurityque);
		confirmpass = (EditText) findViewById(R.id.etCPass);
		createAccount = (Button) findViewById(R.id.bCreateAccount);
		spin = (Spinner) findViewById(R.id.spinner1);

		// On click listner with create account
		createAccount.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// Form Varification Code
		String eName = name.getText().toString();
		boolean goAhead = true;
		if (!correctName(eName) && goAhead) {
			String nameMsg = "Enter valid Name";
			goAhead = showWarningMessage(nameMsg);
		}
		String eEmail = email.getText().toString();
		if (!correctEmail(eEmail) && goAhead) {
			String emailMsg = "Enter Valid Email";
			goAhead = showWarningMessage(emailMsg);
		}
		String eMobile = mobile.getText().toString();
		if (!correctMobile(eMobile) && goAhead) {
			String mobileMsg = "Enter Valid Mobile Number";
			goAhead = showWarningMessage(mobileMsg);
		}

		int id = spin.getSelectedItemPosition();
		String ans = myseqans.getText().toString();
		if (!correctSecurityQue(ans, id) && goAhead) {
			String secMsg = "Enter Valid answer or select valid Question.";
			goAhead = showWarningMessage(secMsg);
		}

		String ePassword = pass.getText().toString();
		if (!correctPassword(ePassword) && goAhead) {
			String passMsg = "The password's first character must be a letter, it must contain at least 4 characters and no more than 15 characters and no characters other than letters, numbers and the underscore may be used";
			goAhead = showWarningMessage(passMsg);
		}

		String ecoPassword = confirmpass.getText().toString();
		if (!correctPassword(ePassword, ecoPassword) && goAhead) {
			String passMsg = "Password doesn't Match.";
			goAhead = showWarningMessage(passMsg);
		}

		String IMEI = null, SIMSerialNumber = null;
		if (SIMAvilable()) {
			IMEI = getIMEI();
			SIMSerialNumber = getSimSerialNumber();
		} else {
			goAhead = showWarningMessage("SIM not Available");
		}

		// Set the value to UserProfile class
		if (goAhead) {
			// set 8 Field
			up.setAns(ans);
			up.setEmail(eEmail);
			up.setId(id);
			up.setMobile(eMobile);
			up.setName(eName);
			up.setPassword(ePassword);
			up.setIMEI(IMEI);
			up.setSimNumber(SIMSerialNumber);
			up.setGml(gml.fetchLocation());
			// Regiser User If he register 1st time
			if (!reg.userAvailable(up)) {
				Toast.makeText(getApplicationContext(), "Start",
						Toast.LENGTH_LONG).show();
				boolean s = rm.regiserNewUser(up);
				if (s) {
					edit.putString("Antitheft_login_successfully_done", "true");
					edit.commit();
					startActivity(new Intent(getApplicationContext(),
							AntitheftFrontPage.class));
					finish();
				} else {
					edit.putString("Antitheft_login_successfully_done", null);
					edit.commit();
					startActivity(new Intent(getApplicationContext(),
							MainModule.class));
					finish();
				}
			}

			// Open MainModule
		}

	}

	private boolean correctPassword(String ePassword) {
		// TODO Auto-generated method stub
		boolean isValid = false;

		String expression = "^[a-zA-Z]\\w{3,14}$";
		CharSequence inputStr = ePassword;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);

		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;

	}

	private String getSimSerialNumber() {
		// TODO Auto-generated method stub
		return tManager.getSimSerialNumber();
	}

	private boolean SIMAvilable() {
		// TODO Auto-generated method stub
		int SIMSTATUS = tManager.getSimState();
		if (SIMSTATUS != TelephonyManager.SIM_STATE_ABSENT) {
			return true;
		}
		return false;
	}

	private String getIMEI() {
		// TODO Auto-generated method stub
		return tManager.getDeviceId();
	}

	private boolean correctSecurityQue(String ans, int id) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		if (id < 1 && ans.equals("") || ans.equals(" ") || ans == null)
			isValid = false;
		return isValid;
	}

	private boolean correctName(String eName) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		if (eName == null || eName.trim() == " " || eName.equals(" ")
				|| eName.equals(""))
			isValid = false;
		return isValid;
	}

	private boolean correctPassword(String ePassword, String ecoPassword) {
		// TODO Auto-generated method stub
		boolean isValid = false;
		if (ePassword == null || ePassword.equals(" ") || ecoPassword == null
				|| ePassword.equals("") || ecoPassword.equals("")
				|| ecoPassword.equals(" "))
			isValid = false;
		else if (ePassword.equals(ecoPassword))
			isValid = true;
		return isValid;
	}

	private boolean correctMobile(String eMobile) {
		// TODO Auto-generated method stub
		boolean isValid = false;
		if (eMobile.length() >= 10 && eMobile.length() <= 12)
			isValid = true;
		return isValid;
	}

	private boolean correctEmail(String eEmail) {
		// TODO Auto-generated method stub
		boolean isValid = false;

		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = eEmail;

		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

	private boolean showWarningMessage(String Msg) {
		// TODO Auto-generated method stub
		valueFlag = false;
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Warning")
				.setMessage(Msg)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						valueFlag = false;
					}

				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								valueFlag = false;
							}
						}).show();
		return valueFlag;
	}

}