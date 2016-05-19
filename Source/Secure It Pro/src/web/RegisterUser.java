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

public class RegisterUser {
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	Context c;

	public RegisterUser(Context c) {
		// TODO Auto-generated constructor stub
		this.c = c;
	}

	public boolean regiserNewUser(UserProfile up) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		Toast.makeText(c, "Register 1", Toast.LENGTH_LONG).show();
		// Strict Mode For Async Task
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		String id = up.getId() + "";
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("name", up.getName()));
		nameValuePairs.add(new BasicNameValuePair("email", up.getEmail()));
		nameValuePairs.add(new BasicNameValuePair("mobile", up.getMobile()));
		nameValuePairs.add(new BasicNameValuePair("sim", up.getSimNumber()));
		nameValuePairs.add(new BasicNameValuePair("imei", up.getIMEI()));
		nameValuePairs
				.add(new BasicNameValuePair("password", up.getPassword()));
		nameValuePairs.add(new BasicNameValuePair("que", id));
		nameValuePairs.add(new BasicNameValuePair("ans", up.getAns()));
		nameValuePairs.add(new BasicNameValuePair("longitude", up.getGml()
				.getLongitude()));
		nameValuePairs.add(new BasicNameValuePair("latitude", up.getGml()
				.getLatitude()));

		System.out.println(up.getName() + " " + up.getEmail());
		Toast.makeText(c, "Register 2", Toast.LENGTH_LONG).show();

		try {
			HttpClient httpclient = new DefaultHttpClient();
			Toast.makeText(c, "Register 3", Toast.LENGTH_LONG).show();
			HttpPost httppost = new HttpPost(
					"http://172.20.10.4/my/antitheft_register_new_user.php");
			Toast.makeText(c, "Register 4", Toast.LENGTH_LONG).show();
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(c, "Invalid IP Address: Reg Process" + e.toString(),
					Toast.LENGTH_LONG).show();
			isValid = false;
		}
		try {
			Toast.makeText(c, "Register 5", Toast.LENGTH_LONG).show();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Toast.makeText(c, "Register 6", Toast.LENGTH_LONG).show();
			Log.e("pass 2", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
			isValid = false;
		}

		try {

			Toast.makeText(c, "Register 7", Toast.LENGTH_LONG).show();
			JSONObject json_data = new JSONObject(result);
			code = (json_data.getInt("code"));

			Toast.makeText(c, "Register 8 ::" + code, Toast.LENGTH_LONG).show();
			if (code == 1) {
				Toast.makeText(c, "Inserted Successfully", Toast.LENGTH_SHORT)
						.show();
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
