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

import bean.UserProfile;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

public class CheckExistence {
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	Context c;

	public CheckExistence(Context c) {
		// TODO Auto-generated constructor stub
		this.c = c;
	}

	public boolean userAvailable(UserProfile up) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		// Strict Mode For Async Task
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		String IMEI = up.getIMEI();
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("IMEI", IMEI));
		try {
			HttpClient httpclient = new DefaultHttpClient();

			HttpPost httppost = new HttpPost(
					"http://172.20.10.4/my/antitheft_check_user_already_available.php");

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(c, "Invalid IP Address:" + e.toString(),
					Toast.LENGTH_LONG).show();
			isValid = false;
		}
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
			isValid = false;
		}

		try {
			JSONObject json_data = new JSONObject(result);
			code = (json_data.getInt("code"));
			Toast.makeText(c, "code1:" + code, Toast.LENGTH_SHORT).show();
			if (code == 1) {
				Toast.makeText(c, "Code run", Toast.LENGTH_SHORT).show();
				isValid = true;
			} else {
				Toast.makeText(c, "Sorry, Try Again", Toast.LENGTH_LONG).show();
				isValid = false;
			}
		} catch (Exception e) {
			Log.e("Fail 3", e.toString() + ":::" + result);
			isValid = false;
		}
		// return code;
		return isValid;
	}

}
