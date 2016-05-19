package com.example.onetouchcall;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import bean.Contact;

import com.example.mobiletheft.MainModule;
import com.example.mobiletheft.R;

public class ContactList extends Activity implements OnClickListener {
	ListView listView;
	Cursor cursor = null;
	Context context = null;
	Button bDone;
	SharedPreferences getPref;

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(),
				OneTouchFrontPage.class));
		finish();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_list_view);
		getPref = PreferenceManager.getDefaultSharedPreferences(this);
		context = this;
		listView = (ListView) findViewById(R.id.lvOneTouchList);
		bDone = (Button) findViewById(R.id.bOneTouchContactSelected);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"Click ListItem Number " + position, Toast.LENGTH_LONG)
						.show();
			}
		});
		ArrayAdapter<Contact> adapter = new ArrayAdaperAdvance(this, getModel());
		listView.setAdapter(adapter);
		bDone.setOnClickListener(this);
	}

	private List<Contact> getModel() {
		// TODO Auto-generated method stub
		List<Contact> list = new ArrayList<Contact>();

		try {
			ContentResolver cr = getContentResolver();
			cursor = cr.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					null, null,
					ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
							+ " ASC");

			cursor.moveToFirst();
			if (cursor.moveToFirst()) {
				do {
					String name = cursor
							.getString(cursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
					String number = cursor
							.getString(cursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

					list.add(get(name, number));

				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.d("???????? Error in Contacts Read: ", "" + e.getMessage());
		}

		return list;
	}

	private Contact get(String name, String number) {
		// TODO Auto-generated method stub
		return new Contact(name, number);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bOneTouchContactSelected:
			String s = "";
			for (int i = 0; i < ArrayAdaperAdvance.list.size(); i++) {
				if (ArrayAdaperAdvance.list.get(i).isSelected()) {
					s = s + i + " ";
					System.out.println("String: " + s);
					break;
				}
			}
			for (int i = 0; i < ArrayAdaperAdvance.list.size(); i++) {
				if (ArrayAdaperAdvance.list.get(i).isSelected()) {
					cursor.moveToPosition(i);
					String contactID = cursor
							.getString(cursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

					Editor confirmEdit = getPref.edit();
					confirmEdit
							.putString("OneTouched_stored_mobile", contactID);
					confirmEdit.commit();

					Intent iContact;
					try {
						iContact = new Intent(getApplicationContext(),
								OneTouchFrontPage.class);
						startActivity(iContact);
						finish();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				}
			}

			break;
		}
	}
}
