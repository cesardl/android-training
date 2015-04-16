package com.scytl.stackoverflow;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.scytl.stackoverflow.model.Player;
import com.scytl.stackoverflow.model.Team;

/**
 * 
 * @author cesardiaz
 * @see <a href=
 *      "http://stackoverflow.com/questions/24832497/avoid-passing-null-as-the-view-root-need-to-resolve-layout-parameters-on-the-in"
 *      >Avoid passing null as the view root (need to resolve layout parameters
 *      on the inflated layout's root element)</a >
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
		// Log.i(tag, String.format("Child in (%d, %d): %s", groupPosition,
		// childPosition, child));
		return child;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.child, parent, false);

			holder = new ChildHolder();
			holder.name = (TextView) convertView.findViewById(R.id.player_name);
			holder.checkBox = (CheckBox) convertView
					.findViewById(R.id.check_box);

			convertView.setTag(holder);
		}

		holder = (ChildHolder) convertView.getTag();

		final Player player = (Player) getChild(groupPosition, childPosition);
		holder.name.setText(player.getName());
		holder.checkBox.setChecked(player.isChecked());
		Log.i(tag, String.format("Create listener for(%d, %d) >> %s",
				groupPosition, childPosition, player.isChecked()));
		holder.checkBox.setOnClickListener(new PlayerCheckedChangeListener(
				groupPosition, childPosition));
		// Log.i(tag, String.format("Selected for(%d, %d) %s", groupPosition,
		// childPosition, player.isSelected()));
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mData.get(groupPosition).getPlayers().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		Object group = mData.get(groupPosition);
		// Log.i(tag, String.format("Group in (%d): %s", groupPosition, group));
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
		GroupHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.parent, parent, false);

			holder = new GroupHolder();
			holder.name = (TextView) convertView.findViewById(R.id.team_name);
			holder.count = (TextView) convertView.findViewById(R.id.count);

			convertView.setTag(holder);
		}

		holder = (GroupHolder) convertView.getTag();

		final Team team = (Team) getGroup(groupPosition);
		holder.name.setText(team.getName());
		holder.count.setText(team.getCount());
		// Log.i(tag, String.format("Get count %s", team.getCount()));
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// Log.i(tag, String.format("Child selectable in (%d, %d)",
		// groupPosition, childPosition));
		return true;
	}

	static class GroupHolder {
		TextView name;
		TextView count;
	}

	static class ChildHolder {
		TextView name;
		CheckBox checkBox;
	}

	private final class PlayerCheckedChangeListener implements OnClickListener {

		int groupPosition;
		int childPosition;

		PlayerCheckedChangeListener(int groupPosition, int childPosition) {
			this.groupPosition = groupPosition;
			this.childPosition = childPosition;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Team team = mData.get(groupPosition);
			Player player = team.getPlayers().get(childPosition);
			CheckBox buttonView = (CheckBox) v;
			
			boolean isChecked = buttonView.isChecked();
			
			if (isChecked) {
				team.increase();
			} else {
				team.decrease();
			}
			player.setChecked(isChecked);

			Log.w(tag, String.format("Is checked %s %s %s >> %s, %d", team,
					player, isChecked, team.getCount(), buttonView.getId()));
			
			notifyDataSetChanged();
		}

	}
}
