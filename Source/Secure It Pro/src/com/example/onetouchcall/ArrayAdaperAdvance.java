package com.example.onetouchcall;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import bean.Contact;

import com.example.mobiletheft.R;

public class ArrayAdaperAdvance extends ArrayAdapter<Contact> {

	public static List<Contact> list = null;
	private final Activity context;

	public ArrayAdaperAdvance(Activity context, List<Contact> list) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.contact_list, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView text, number;

		protected RadioButton checkbox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;

		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.contact_list, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) view
					.findViewById(R.id.tvOneTouchContactName);
			viewHolder.number = (TextView) view
					.findViewById(R.id.tvOneTouchContactNumber);
			viewHolder.text.setText("Select Ringtone");

			viewHolder.checkbox = (RadioButton) view
					.findViewById(R.id.rbOneTouchRadioButton);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							Contact element = (Contact) viewHolder.checkbox
									.getTag();
							element.setSelected(buttonView.isChecked());
						}
					});

			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(list.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getName());
		holder.number.setText(list.get(position).getNumber());
		holder.checkbox.setChecked(list.get(position).isSelected());

		return view;
	}

}
