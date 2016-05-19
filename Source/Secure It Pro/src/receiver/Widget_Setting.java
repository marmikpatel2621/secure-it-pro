package receiver;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.mobiletheft.R;

public class Widget_Setting extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.emergency_call_widget);

			SharedPreferences pref = PreferenceManager
					.getDefaultSharedPreferences(context);
			String num = pref.getString("OneTouched_stored_mobile", null);
			Toast.makeText(context, num, Toast.LENGTH_LONG).show();

			// Intent callBackProcess = new Intent(context,
			// Call_Running_Receiver.class);
			// PendingIntent pIntent2 = PendingIntent.getActivity(context, 0,
			// callBackProcess, 0);
			// views.setOnClickPendingIntent(R.id.iOneTouchWidget, pIntent2);

			Intent makeAcall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ num));
			PendingIntent pIntent = PendingIntent.getActivity(context, 0,
					makeAcall, 0);
			views.setOnClickPendingIntent(R.id.iOneTouchWidget, pIntent);

			appWidgetManager.updateAppWidget(appWidgetIds[i], views);
		}

	}
}