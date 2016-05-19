package receiver;

import services.DetectForegroundApps;
import services.UpdateLocationOfUser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartDetectForegroundAppsIfNot extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "The Update Location Broadcast",
				Toast.LENGTH_LONG).show();

		Intent i = new Intent(context, DetectForegroundApps.class);
		context.startService(i);

	}

}