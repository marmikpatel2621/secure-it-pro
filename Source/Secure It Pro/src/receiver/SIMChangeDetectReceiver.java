package receiver;

import services.DetectSIMChange;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SIMChangeDetectReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "SIM_CHANGE_DETECT_RECEIVER", Toast.LENGTH_LONG)
				.show();

		DetectSIMChange.goAhead = true;
		Intent i = new Intent(context, DetectSIMChange.class);
		context.startService(i);

	}

}