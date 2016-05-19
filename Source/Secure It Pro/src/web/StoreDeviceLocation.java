package web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import bean.GPSLocation;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

public class StoreDeviceLocation {
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	Context c;

	public StoreDeviceLocation(Context c) {
		this.c = c;
	}

	public void storeLocation(GPSLocation loc, String IMEI) {
		// TODO Auto-generated method stub

		// Strict Mode For Async Task
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("longitude", loc
				.getLongitude()));
		nameValuePairs
				.add(new BasicNameValuePair("latitude", loc.getLatitude()));
		nameValuePairs.add(new BasicNameValuePair("IMEI", IMEI));

		try {
			HttpClient httpclient = new DefaultHttpClient();

			HttpPost httppost = new HttpPost(
					"http://172.20.10.4/my/antitheft_update_device_location.php");

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(c, "Invalid IP Address: Reg Process" + e.toString(),
					Toast.LENGTH_LONG).show();
		}
		Toast.makeText(c, "Conenction Store 1", Toast.LENGTH_LONG).show();
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Log.e("pass 2", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}
		Toast.makeText(c, "Conenction Store 2", Toast.LENGTH_LONG).show();
		try {

			JSONObject json_data = new JSONObject(result);
			code = json_data.getInt("codereturn");
			Log.e("pass 3 CODE", "connection success :" + code);

			if (code == 1) {
				Toast.makeText(c, "Inserted Successfully", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(c, "Sorry, Try Again", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Fail 3 STORE", e.toString() + ":::" + result);
		}

	}
}