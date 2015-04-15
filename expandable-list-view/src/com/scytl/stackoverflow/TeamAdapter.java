package com.scytl.stackoverflow;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.scytl.stackoverflow.model.Player;
import com.scytl.stackoverflow.model.Team;

/**
 * 
 * @author cesardiaz
 *
 */
public class TeamAdapter extends BaseExpandableListAdapter {

	private static final String tag = TeamAdapter.class.getSimpleName();

	private Context mContext;
	private List<Team> mData;

	public TeamAdapter(Context context, List<Team> data) {
		mContext = context;
		mData = data;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		Object child = mData.get(groupPosition).getPlayers().get(childPosition);
		Log.i(tag, String.format("Child in (%d, %d): %s", groupPosition,
				childPosition, child));
		return child;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Player player = (Player) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.child, null);
		}

		TextView name = (TextView) convertView.findViewById(R.id.player_name);
		name.setText(player.getName());

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mData.get(groupPosition).getPlayers().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		Object group = mData.get(groupPosition);
		Log.i(tag, String.format("Group in (%d): %s", groupPosition, group));
		return group;
	}

	@Override
	public int getGroupCount() {
		return mData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Team team = (Team) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.parent, null);
		}

		TextView name = (TextView) convertView.findViewById(R.id.team_name);
		name.setText(team.getName());

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		Log.i(tag, String.format("Child selectable in (%d, %d)", groupPosition,
				childPosition));
		return true;
	}
}