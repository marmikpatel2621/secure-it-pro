package com.example.datahiding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import bean.Item;

import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

public class HiddenActivity extends ListActivity {

	private File currentDir;
	private FileArrayAdapter adapter;
	String pathUnhide;
	final String NOMEDIA_FILE = ".nomedia";
	private static final int REQUEST_PATH = 1;
	ListView listview;
	List<Item> dir;
	List<Item> fls;
	String hidePath = "personal/.hide/";
	ArrayAdapter<String> fileList;
	Listener listener;
	final Context context = this;
	File file;
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	private EditText result;
	private EditText userInput;
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hidden);
		currentDir = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator + hidePath);
		fill(currentDir);

		Intent i = getIntent();
		if (i.getStringExtra("GetPath") != null) {
			userInput.setText(i.getStringExtra("GetPath"));

		}
	}
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
			startActivity(new Intent(getApplicationContext(),
					DataHidingFrontPage.class));
			finish();
		
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_unhide, menu);
		return true;
	}

	private void fill(File f) {
		File[] dirs = f.listFiles();
		this.setTitle("Current Dir: " + f.getName());
		dir = new ArrayList<Item>();
		fls = new ArrayList<Item>();
		try {
			for (File ff : dirs) {
				Date lastModDate = new Date(ff.lastModified());
				DateFormat formater = DateFormat.getDateTimeInstance();
				String date_modify = formater.format(lastModDate);
				fls.add(new Item(ff.getName(), ff.length() + " Byte",
						date_modify, ff.getAbsolutePath(), "file_icon"));
			}
		} catch (Exception e) {

		}
		Collections.sort(fls);
		listview = (ListView) findViewById(android.R.id.list);
		adapter = new FileArrayAdapter(this, R.layout.file_view, fls);
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
				inflater.inflate(R.menu.activity_unhide, menu);
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
					LayoutInflater li = LayoutInflater.from(context);
					View promptsView = li.inflate(R.layout.prompts, null);

					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							context);

					// set prompts.xml to alertdialog builder
					alertDialogBuilder.setView(promptsView);

					userInput = (EditText) promptsView
							.findViewById(R.id.editTextDialogUserInput);
					Button b = (Button) promptsView
							.findViewById(R.id.skipButton);
					b.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View promptsView) {
							// TODO Auto-generated method stub
							getfile(promptsView);

						}
					});
					// set dialog message
					alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// get user input and set it to
											// result
											// edit text
											pathUnhide = userInput.getText()
													.toString();
											Toast.makeText(getBaseContext(),
													pathUnhide,
													Toast.LENGTH_SHORT).show();
											try {
												getCheckedItems();
											} catch (IOException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();

					return true;
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

	private void getCheckedItems() throws IOException {
		// TODO Auto-generated method stub
		File f;
		File p;
		Log.d("aa", "hello " + pathUnhide + "hello");
		String check = "";
		SparseBooleanArray checkedItems = getListView()
				.getCheckedItemPositions();
		Log.d("aa",
				"hello " + pathUnhide + "hello" + checkedItems.get(1)
						+ fls.size());
		for (int i = 0; i < fls.size(); i++) {
			if (checkedItems.get(i)) {
				Log.d("a", Integer.toString(fls.size()));
				// check=check+" "+dir.get(i).getName()
				// +" "+dir.get(i).getPath() +" " + fls.get(i).getName();
				Log.d("aa", "hello " + pathUnhide + i);
				p = new File(pathUnhide);
				p.mkdirs();
				Log.d("hiii", check);

				if (!p.exists()) {
					try {
						p.createNewFile();
						Toast.makeText(getBaseContext(),
								"no media may created" + p.isHidden(),
								Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					Toast.makeText(getBaseContext(),
							"no media already " + p.isHidden(),
							Toast.LENGTH_LONG).show();
				}
				File fp = new File(fls.get(i).getPath());
				Toast.makeText(getBaseContext(), fls.get(i).getName(),
						Toast.LENGTH_LONG).show();
				if (fp.isDirectory()) {
					copyDirectoryOneLocationToAnotherLocation(fp, p);
				} else {
					fp = new File(fls.get(i).getPath());
					p = new File(p, fls.get(i).getName());
					copyDirectoryOneLocationToAnotherLocation(fp, p);
					// dir.remove(i);
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
		private ProgressDialog dialog = new ProgressDialog(HiddenActivity.this);
		long total;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.d("abd2", " msg pre button");
			dialog.setMessage("Copying file..");
			dialog.show();
			onCreateDialog(DIALOG_DOWNLOAD_PROGRESS);
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
				// params[0].delete();
				output.flush();
				output.close();
				input.close();
			} catch (Exception e) {
			}
			return null;

		}
	}

	public void getfile(View view) {
		Intent intent1 = new Intent(this, UnhidePathSelectionActivity.class);
		startActivityForResult(intent1, REQUEST_PATH);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Toast.makeText(getApplicationContext(), "ONActivityResukt",
				Toast.LENGTH_LONG).show();
		if (requestCode == REQUEST_PATH) {
			if (resultCode == RESULT_OK) {
				pathUnhide = data.getStringExtra("GetPath");
				userInput.setText(pathUnhide);
				Toast.makeText(getApplicationContext(), "hiii" + pathUnhide,
						Toast.LENGTH_LONG).show();
			}

		}
	}
	// Listen for results.
}
