package receiver;

import services.DetectSIMChange;
import services.UpdateLocationOfUser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartUpdateLocationServiceIfNot extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "The Update Location Broadcast",
				Toast.LENGTH_LONG).show();

		Intent i = new Intent(context, UpdateLocationOfUser.class);
		context.startService(i);

	}

}