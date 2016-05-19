package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

public class Call_Running_Receiver extends BroadcastReceiver {

	static boolean flag = false;
	static long start_time, end_time;
	long total_time;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
		System.out.println("My Called Number" + phoneNumber);

		String action = intent.getAction();
		if (action.equalsIgnoreCase("android.intent.action.PHONE_STATE")) {

			if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
					TelephonyManager.EXTRA_STATE_RINGING)) {

				// tOAST FOR INCOMING CALL, NOT YET PICKED UP

			}
			if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
					TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
				// tOAST FOR INCOMING CALL, NOT YET PICKED UP
				System.out.println("On Hook ");
				intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ "9426802658"));

			}
			if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
					TelephonyManager.EXTRA_STATE_IDLE)) {
				end_time = System.currentTimeMillis();
				// Total time talked =
				total_time = end_time - start_time;
				// Store total_time somewhere or pass it to an Activity using
				// intent
				System.out.println("total" + total_time);
			}
			if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
					TelephonyManager.EXTRA_STATE_OFFHOOK)) {
				start_time = System.currentTimeMillis();
				System.out.println("start" + start_time);

			}

		}
		System.out.println("total Time" + total_time);

		if (total_time == 0) {
			System.out.println("Call not picked up.");
		}
	}
}
