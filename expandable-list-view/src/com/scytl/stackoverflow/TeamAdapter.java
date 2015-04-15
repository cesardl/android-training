package com.scytl.stackoverflow;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TeamAdapter extends ArrayAdapter<Team> {

	private List<Team> listTeam;
	private TextView tViewNoOfSelectionOnTop;
	private int countSelectionOnTop = 0;
	// private int pos = 0;
	// private Team rowData = null;
	private TextView tViewNoOfSelectionFrmPatent = null;
	// private int rowCount = 0;

	static final String TAG = TeamAdapter.class.getSimpleName();

	public TeamAdapter(Context context, int resourceId, List<Team> teams,
			TextView tViewNoOfSelection) {
		super(context, resourceId, teams);
		this.listTeam = teams;
		this.tViewNoOfSelectionOnTop = tViewNoOfSelection;
		Log.d(TAG, String.format("Total of values %d", teams.size()));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// rowCount++;

		final int pos = position;
		final Team rowData = listTeam.get(pos);
		// pos = position;
		// rowData = listTeam.get(pos);
		// Log.d(TAG,"Row Count(Position): " + position + ", pos: " +
		// pos
		// + ", Row Count2: " + rowCount);

		Log.d(TAG, String.format(
				"position %d - %s - %d - %s - %s",
				position,
				convertView == null ? "Is null " : convertView
						.getContentDescription(), rowData.getType(), rowData
						.getStrName(), parent));

		LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
		if (rowData.getType() == Team.TYPE_Team) {
			convertView = inflater.inflate(R.layout.parent, parent, false);
		} else if (rowData.getType() == Team.TYPE_Player) {
			convertView = inflater.inflate(R.layout.child, parent, false);
		}

		if (rowData.getType() == Team.TYPE_Team) {
			Log.d(TAG, "Type Team");
			TextView tViewTeamName = (TextView) convertView
					.findViewById(R.id.tViewTeamName);
			TextView tViewNoOfSelectionFrmPatent = (TextView) convertView
					.findViewById(R.id.tViewNoOfSelectionFrmPatent);

			tViewTeamName.setText(rowData.getStrName());
			rowData.settViewNoOfSelectionFrmPatent(tViewNoOfSelectionFrmPatent);
			convertView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					rowData.toggleExpansion(TeamAdapter.this, pos);
				}
			});

		} else if (rowData.getType() == Team.TYPE_Player) {
			Log.d(TAG, "Type Player");
			final ImageView iViewCheckUnCheck = (ImageView) convertView
					.findViewById(R.id.iViewCheckUnCheck);
			TextView tViewPlayerName = (TextView) convertView
					.findViewById(R.id.tViewPlayerName);
			tViewNoOfSelectionFrmPatent = rowData.getParent()
					.gettViewNoOfSelectionFrmPatent();
			Log.d(TAG, "TextView2 Id: " + tViewNoOfSelectionFrmPatent.getId());
			tViewPlayerName.setText(rowData.getStrName());

			if (rowData.isSelected())
				iViewCheckUnCheck
						.setImageResource(android.R.drawable.checkbox_on_background);
			else
				iViewCheckUnCheck
						.setImageResource(android.R.drawable.checkbox_off_background);

			convertView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.d(TAG, "Player Clicked");
					rowData.toggleSelection();
					int a = Integer.valueOf(tViewNoOfSelectionFrmPatent
							.getText().toString());
					if (rowData.isSelected()) {
						Log.d(TAG, "Player Selected");
						a++;
						countSelectionOnTop++;
						iViewCheckUnCheck
								.setImageResource(android.R.drawable.checkbox_on_background);
					} else {
						Log.d(TAG, "Player Unselected");
						a--;
						countSelectionOnTop--;
						iViewCheckUnCheck
								.setImageResource(android.R.drawable.checkbox_off_background);
					}
					tViewNoOfSelectionOnTop.setText(String
							.valueOf(countSelectionOnTop));
					tViewNoOfSelectionFrmPatent.setText(String.valueOf(a));
				}
			});
		}

		return convertView;
	}
}