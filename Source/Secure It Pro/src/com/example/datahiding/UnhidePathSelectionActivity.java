package com.example.datahiding;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.Item;

import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

public class UnhidePathSelectionActivity extends ListActivity implements
		OnClickListener {

	private File currentDir;
	private FileArrayAdapter adapter;
	private TextView path;
	private Button select, cancel;
	private Item o;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unhide_path_selection);
		currentDir = new File("/sdcard/");
		path = (TextView) findViewById(R.id.path);
		fill(currentDir);
		select = (Button) findViewById(R.id.select);
		select.setOnClickListener(this);
		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(this);
	}

	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (!currentDir.getPath().equals("/sdcard")) {
			currentDir = new File(currentDir.getParent());
			fill(currentDir);
		} else {
			startActivity(new Intent(getApplicationContext(),
					HiddenActivity.class));
			// finish();
		}
	}

	private void fill(File f) {
		File[] dirs = f.listFiles();
		path.setText("Current Dir: " + f.getPath());
		List<Item> dir = new ArrayList<Item>();
		List<Item> fls = new ArrayList<Item>();
		try {
			for (File ff : dirs) {
				Date lastModDate = new Date(ff.lastModified());
				DateFormat formater = DateFormat.getDateTimeInstance();
				String date_modify = formater.format(lastModDate);
				if (ff.isDirectory()) {

					File[] fbuf = ff.listFiles();
					int buf = 0;
					if (fbuf != null) {
						buf = fbuf.length;
					} else
						buf = 0;
					String num_item = String.valueOf(buf);
					if (buf == 0)
						num_item = num_item + " item";
					else
						num_item = num_item + " items";

					// String formated = lastModDate.toString();
					dir.add(new Item(ff.getName(), num_item, date_modify, ff
							.getAbsolutePath(), "directory_icon"));
				} else {

					fls.add(new Item(ff.getName(), ff.length() + " Byte",
							date_modify, ff.getAbsolutePath(), "file_icon"));
				}
			}
		} catch (Exception e) {

		}
		Collections.sort(dir);
		Collections.sort(fls);
		dir.addAll(fls);
		if (!f.getName().equalsIgnoreCase("sdcard"))
			dir.add(0, new Item("..", "Parent Directory", "", f.getParent(),
					"directory_up"));
		adapter = new FileArrayAdapter(UnhidePathSelectionActivity.this,
				R.layout.file_view, dir);
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		o = adapter.getItem(position);
		if (o.getImage().equalsIgnoreCase("directory_icon")
				|| o.getImage().equalsIgnoreCase("directory_up")) {
			currentDir = new File(o.getPath());
			fill(currentDir);
		} else {
			Toast.makeText(getApplicationContext(), "Please Select Folder",
					1000);
		}
	}

	private void onFileClick(Item o) {
		Toast.makeText(this, "Folder Clicked: " + currentDir,
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		intent.putExtra("GetPath", currentDir.toString());
		// intent.putExtra("GetFileName", o.getName());
		setResult(RESULT_OK, intent);
		// setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.select:
			Toast.makeText(getApplicationContext(),
					"Select:" + currentDir.toString(), Toast.LENGTH_LONG)
					.show();
			onFileClick(o);
			break;

		case R.id.cancel:
			Intent intent = new Intent();
			intent.putExtra("GetPath", "/sdcard/new folder/");
			// intent.putExtra("GetFileName",o.getName());
			setResult(RESULT_OK, intent);
			finish();
			break;

		default:
			break;
		}

	}

}
