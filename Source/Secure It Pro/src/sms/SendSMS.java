package sms;

import bean.GPSLocation;
import android.app.PendingIntent;
import android.content.Context;
import android.telephony.SmsManager;

public class SendSMS {
	Context c;

	public SendSMS(Context c) {
		// TODO Auto-generated constructor stub
		this.c = c;
	}

	public void sendSMS(GPSLocation loc, String destinationaddress) {
		// TODO Auto-generated method stub

		SmsManager smsMngr = SmsManager.getDefault();

		String scAddress = null;
		String text = "SIM changed at  " + "https://www.google.co.in/maps/@"
				+ loc.getLatitude() + "," + loc.getLongitude() + ",18z";

		// String text = "New Sim Inserted.";

		PendingIntent sentIntent = null;
		PendingIntent deliveryIntent = null;
		smsMngr.sendTextMessage(destinationaddress, scAddress, text,
				sentIntent, deliveryIntent);
	}

}
