package gps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkCondition {
	Context c;

	public NetworkCondition(Context ct) {
		// TODO Auto-generated constructor stub
		c = ct;
	}

	public boolean isNetworkAccessible() {

		ConnectivityManager cm = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}
}
