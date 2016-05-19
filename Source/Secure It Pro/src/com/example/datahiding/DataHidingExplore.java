package com.example.datahiding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bean.Item;

import com.example.applock.ApplockFrontPage;
import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

public class DataHidingExplore extends ListActivity {

	private File currentDir;
	private FileArrayAdapter adapter;
	final String NOMEDIA_FILE = ".nomedia";
	ListView listview;
	List<Item> dir;
	List<Item> fls;
	String hidePath = "personal/.hide/";
	ArrayAdapter<String> fileList;
	Listener listener;
	File file;
	private TextView myPath;
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.explorer);
		myPath = (TextView) findViewById(R.id.path);
		currentDir = new File("mnt/");
		fill(currentDir);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (currentDir.getPath().equals("mnt")) {
			super.onBackPressed();
		} else if (!currentDir.getPath().equals("/mnt")) {
			currentDir = new File(currentDir.getParent());
			fill(currentDir);
		} else {
			startActivity(new Intent(getApplicationContext(), MainModule.class));
			// finish();
		}
	}

	private void fill(File f) {
		File[] dirs = f.listFiles();
		myPath.setText("Current Dir: " + f.getPath());
		dir = new ArrayList<Item>();
		fls = new ArrayList<Item>();
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
		listview = (ListView) findViewById(android.R.id.list);
		if (!f.getName().equalsIgnoreCase("mnt")) {
			dir.add(0, new Item("..", "Parent Directory", "", f.getParent(),
					"directory_up"));
			fls.add(0, new Item("..", "Parent Directory", "", f.getParent(),
					"directory_up"));
		}
		adapter = new FileArrayAdapter(this, R.layout.file_view, dir);
		this.setListAdapter(adapter);
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listview.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.activity_main, menu);
				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				// TODO Auto-generated method stub
				switch (item.getItemId()) {
				case R.id.delete: {
					Toast.makeText(
							getBaseContext(),
							"Delete selected"
									+ getListView().getCheckedItemCount(),
							Toast.LENGTH_LONG).show();
					return true;
				}
				case R.id.edit: {
					try {
						getCheckedItems();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return false;
				}
				default:
					return false;
				}
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Item o = adapter.getItem(position);
		if (o.getImage().equalsIgnoreCase("directory_icon")
				|| o.getImage().equalsIgnoreCase("directory_up")) {
			currentDir = new File(o.getPath());
			fill(currentDir);
		} else {
			Intent i = new Intent(Intent.ACTION_VIEW);
			Uri uri = Uri.fromFile(new File(o.getPath()));
			String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
					.toString());
			String mimeType = MimeTypeMap.getSingleton()
					.getMimeTypeFromExtension(fileExtension);
			i.setDataAndType(uri, mimeType);
			startActivity(i);
		}
	}

	private void getCheckedItems() throws IOException {
		// TODO Auto-generated method stub
		File f;
		File p;
		String check = "";
		SparseBooleanArray checkedItems = getListView()
				.getCheckedItemPositions();
		for (int i = 0; i < dir.size(); i++) {
			if (checkedItems.get(i)) {
				Log.d("a", Integer.toString(dir.size()));
				// check=check+" "+dir.get(i).getName()
				// +" "+dir.get(i).getPath() +" " + fls.get(i).getName();
				p = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + File.separator + hidePath);
				p.mkdirs();
				Log.d("hiii", check);
				f = new File(p, NOMEDIA_FILE);
				if (!f.exists()) {
					try {
						f.createNewFile();
						Toast.makeText(getBaseContext(),
								"no media may created" + f.isHidden(),
								Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					Toast.makeText(getBaseContext(),
							"no media already " + f.isHidden(),
							Toast.LENGTH_LONG).show();
				}
				File fp = new File(dir.get(i).getPath());
				Toast.makeText(getBaseContext(), dir.get(i).getName(),
						Toast.LENGTH_LONG).show();
				if (fp.isDirectory()) {
					copyDirectoryOneLocationToAnotherLocation(fp, p);
				} else {
					fp = new File(dir.get(i).getPath());
					p = new File(p, dir.get(i).getName());
					copyDirectoryOneLocationToAnotherLocation(fp, p);
					dir.remove(i);
					fls.remove(i);
				}
			}
		}
	}

	public void copyDirectoryOneLocationToAnotherLocation(File sourceLocation,
			File targetLocation) throws IOException {
		Toast.makeText(getBaseContext(), "inside", Toast.LENGTH_SHORT).show();
		Toast.makeText(getBaseContext(),
				" " + sourceLocation + "\n " + targetLocation,
				Toast.LENGTH_LONG).show();
		if (sourceLocation.isDirectory()) {
			if (!targetLocation.exists()) {
				targetLocation.mkdir();
			}

			Toast.makeText(getBaseContext(), "inside 3333", Toast.LENGTH_SHORT)
					.show();

			String[] children = sourceLocation.list();
			for (int i = 0; i < sourceLocation.listFiles().length; i++) {

				copyDirectoryOneLocationToAnotherLocation(new File(
						sourceLocation, children[i]), new File(targetLocation,
						children[i]));

			}
		} else {
			File[] url = new File[2];
			url[0] = sourceLocation;
			url[1] = targetLocation;
			Log.d("abd1", " msg start button" + sourceLocation.getName());
			new DownloadFileAsync().execute(url);

		}

	}

	class DownloadFileAsync extends AsyncTask<File, String, String> {
		private ProgressDialog dialog = new ProgressDialog(
				DataHidingExplore.this);
		long total;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d("abd2", " msg pre button");
			dialog.setMessage("Copying file..");
			dialog.show();
			/* onCreateDialog(DIALOG_DOWNLOAD_PROGRESS); */
		}

		@Override
		protected void onProgressUpdate(String... progress) {
			Log.d("ANDRO_ASYNC1", progress[0]);
			// dialog.setMessage("copying...");
			super.onProgressUpdate(progress[0]);
			setProgress(Integer.parseInt(progress[0]));
			dialog.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String unused) {
			Log.d("p", "in post");
			if (dialog.isShowing())
				dialog.dismiss();
		}

		@Override
		protected String doInBackground(File... params) {
			// TODO Auto-generated method stub
			int count;

			try {

				int lenghtOfFile = (int) params[0].length();
				Log.d("ANDRO_ASYNC",
						"Lenght of file: " + " " + params[0].getName() + " "
								+ lenghtOfFile);

				InputStream input = new FileInputStream(params[0]);
				OutputStream output = new FileOutputStream(params[1]);

				byte data[] = new byte[1024];

				total = 0;

				while ((count = input.read(data)) != -1) {
					total += count;
					Log.d("Lentgh", Long.toString(total));
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}
				params[0].delete();
				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
			}
			return null;

		}
	}

}
