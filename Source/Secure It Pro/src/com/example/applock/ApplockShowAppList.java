package com.example.applock;

import java.util.ArrayList;
import java.util.List;

import services.DetectForegroundApps;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import bean.ApplockAppList;

import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

public class ApplockShowAppList extends Activity implements OnClickListener {

	ListView listView;
	Context context = null;
	Button bDone;
	private List<ApplicationInfo> applist = null;
	private PackageManager packageManager = null;
	List<ApplockAppList> aList = null;
	ArrayAdapter<ApplicationInfo> adapter = null;
	Bundle extras;
	Intent intent;
	private int backpress = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.applock_display_app_list);
		context = this;
		listView = (ListView) findViewById(R.id.lvApplockList);
		bDone = (Button) findViewById(R.id.bNewAppSelected);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"Click ListItem Number " + position, Toast.LENGTH_LONG)
						.show();
			}
		});
		new LoadApplications().execute();

		bDone.setOnClickListener(this);

	}

	@Override
	public void onBackPressed() {
		// code here to show dialog
		// optional depending on your needs
		// backpress = backpress + 1;
		// if (backpress % 2 != 0) {
		// Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG)
		// .show();
		// } else {
		// Intent intent = new Intent();
		// intent.setAction(Intent.ACTION_MAIN);
		// intent.addCategory(Intent.CATEGORY_HOME);
		// startActivity(intent);
		// finish();
		// }
		startActivity(new Intent(getApplicationContext(),
				ApplockFrontPage.class));
		finish();
	}

	private List<ApplicationInfo> getModel(List<ApplicationInfo> list) {
		// TODO Auto-generated method stub
		aList = new ArrayList<ApplockAppList>();
		List<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
		for (ApplicationInfo info : list) {
			try {
				if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
					applist.add(info);
					String n = info.loadLabel(packageManager).toString();
					System.out.println("packagename:" + n);
					int i = info.icon;

					aList.add(get(n));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return applist;
	}

	private ApplockAppList get(String s) {
		// TODO Auto-generated method stub
		return new ApplockAppList(s);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bNewAppSelected:
			String s = "";

			for (int i = 0; i < ApplockArrayAdapter.list.size(); i++) {
				if (ApplockArrayAdapter.list.get(i).isSelected()) {
					s = s + " • " + ApplockArrayAdapter.list.get(i).getName()
							+ "\n";

					;
				}

			}
			System.out.println("String: " + s);

			Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT)
					.show();

			// int countApp = s.length() - s.replace(" ", "").length();
			// Toast.makeText(getApplicationContext(),
			// "Number of Apps Main "+countApp, Toast.LENGTH_LONG).show();

			SharedPreferences pref = PreferenceManager
					.getDefaultSharedPreferences(this);// 0 - for private mode
			Editor editor = pref.edit();
			editor.putString("appName", s);
			// editor.putInt("countApp", countApp);
			editor.commit();
			startService(new Intent(ApplockShowAppList.this,
					DetectForegroundApps.class));
			startActivity(new Intent(getApplicationContext(),
					ApplockFrontPage.class));
			finish();

		}
	}

	private class LoadApplications extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progress = null;

		@Override
		protected Void doInBackground(Void... params) {
			packageManager = getPackageManager();
			applist = getModel((packageManager
					.getInstalledApplications(PackageManager.GET_META_DATA)));
			adapter = new ApplockArrayAdapter(ApplockShowAppList.this, aList,
					applist, packageManager);
			return null;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(Void result) {
			// setListAdapter(adapter);
			listView.setAdapter(adapter);
			progress.dismiss();

			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(ApplockShowAppList.this, null,
					"Loading application info...");
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

	}

}
