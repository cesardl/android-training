package com.scytl.stackoverflow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author cesardiaz
 * @see <a
 *      href="https://stackoverflow.com/questions/29598154/arrayadapter-call-getview-five-time-for-each-row-and-produces-unexpected-behavio">ArrayAdapter
 *      call getView five time for each row and produces unexpected
 *      behaviour</a>
 */
public class MainActivity extends Activity {

	private Context context;
	private ListView lView;
	private ArrayAdapter<Team> aAdapterTeam;
	private List<Team> listTeam;
	private TextView tViewNoOfSelectionOnTop;

	private ArrayList<Player> aListPlayer;
	private ArrayAdapter<Player> aAdapterPlayers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = MainActivity.this;

		tViewNoOfSelectionOnTop = (TextView) findViewById(R.id.tViewNoOfSelectionOnTop);
		lView = (ListView) findViewById(R.id.lView);
		listTeam = new ArrayList<Team>();
		loadData();

		aAdapterTeam = new TeamAdapter(context, 0, listTeam,
				tViewNoOfSelectionOnTop);

		lView.setAdapter(aAdapterTeam);
	}

	private void loadData() {
		Team team = new Team("Team A", 0);
		Team[] teamPlayers = new Team[5];
		teamPlayers[0] = new Team("Player A1", 1, team);
		teamPlayers[1] = new Team("Player A2", 1, team);
		teamPlayers[2] = new Team("Player A3", 1, team);
		teamPlayers[3] = new Team("Player A4", 1, team);
		teamPlayers[4] = new Team("Player A5", 1, team);
		team.addPlayers(teamPlayers);
		listTeam.add(team);

		team = new Team("Team B", 0);
		teamPlayers = new Team[4];
		teamPlayers[0] = new Team("Player B1", 1, team);
		teamPlayers[1] = new Team("Player B2", 1, team);
		teamPlayers[2] = new Team("Player B3", 1, team);
		teamPlayers[3] = new Team("Player B4", 1, team);
		team.addPlayers(teamPlayers);
		listTeam.add(team);

		team = new Team("Team C", 0);
		teamPlayers = new Team[3];
		teamPlayers[0] = new Team("Player C1", 1, team);
		teamPlayers[1] = new Team("Player C2", 1, team);
		teamPlayers[2] = new Team("Player C3", 1, team);
		team.addPlayers(teamPlayers);
		listTeam.add(team);
	}

}
