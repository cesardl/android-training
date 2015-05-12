package net.londatiga.android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.Context;

import java.util.ArrayList;

/**
 * Adapter for crop option list.
 * 
 * @author Lorensius W. L. T <lorenz@londatiga.net>
 *
 */
public class CropOptionAdapter extends ArrayAdapter<CropOption> {

	private ArrayList<CropOption> mOptions;
	private LayoutInflater mInflater;

	public CropOptionAdapter(Context context, ArrayList<CropOption> options) {
		super(context, R.layout.crop_selector, options);
		mOptions = options;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CropOptionHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.crop_selector, parent,
					false);

			holder = new CropOptionHolder();
			holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
			holder.name = (TextView) convertView.findViewById(R.id.tv_name);

			convertView.setTag(holder);
		} else {
			holder = (CropOptionHolder) convertView.getTag();
		}

		CropOption item = mOptions.get(position);

		holder.icon.setImageDrawable(item.icon);
		holder.name.setText(item.title);

		return convertView;
	}

	static class CropOptionHolder {
		ImageView icon;
		TextView name;
	}
}