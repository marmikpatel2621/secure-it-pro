package com.example.applock;

import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import bean.ApplockAppList;

import com.example.mobiletheft.R;

public class ApplockArrayAdapter extends ArrayAdapter<ApplicationInfo> {

	public static List<ApplockAppList> list = null;
	private final Activity context;
	private PackageManager packageManager = null;
	ApplicationInfo aInfo = null;
	List<ApplicationInfo> applist;

	public ApplockArrayAdapter(Activity context, List<ApplockAppList> list,
			List<ApplicationInfo> applist, PackageManager pm) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.applock_applist_format, applist);
		this.context = context;
		ApplockArrayAdapter.list = list;
		packageManager = pm;
		this.applist = applist;
	}

	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
		protected ImageView img;
		protected TextView stext;
	}

	// @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		System.out.println("I am Called Now  :::" + list);
		ApplicationInfo info = applist.get(position);
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.applock_applist_format, null);
			final ViewHolder viewHolder = new ViewHolder();

			viewHolder.text = (TextView) view.findViewById(R.id.tvAppName);
			viewHolder.stext = (TextView) view.findViewById(R.id.tvPackageName);
			viewHolder.img = (ImageView) view.findViewById(R.id.ivAppLogo);
			viewHolder.text.setText("Select Ringtone");
			viewHolder.checkbox = (CheckBox) view
					.findViewById(R.id.cbapplockCheckBox);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							ApplockAppList element = (ApplockAppList) viewHolder.checkbox
									.getTag();
							element.setSelected(buttonView.isChecked());
						}
					});

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (viewHolder.checkbox.isChecked() == true)
						viewHolder.checkbox.setChecked(false);
					else
						viewHolder.checkbox.setChecked(true);

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
		holder.stext.setText(info.packageName);
		holder.img.setImageDrawable(info.loadIcon(packageManager));
		holder.checkbox.setChecked(list.get(position).isSelected());
		return view;

	}
}
