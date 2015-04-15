package com.scytl.stackoverflow;

import java.util.ArrayList;
import java.util.List;

import com.scytl.stackoverflow.model.Player;
import com.scytl.stackoverflow.model.Team;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

/**
 * 
 * @author cesardiaz
 * @see <a href=
 *      "https://stackoverflow.com/questions/29598154/arrayadapter-call-getview-five-time-for-each-row-and-produces-unexpected-behavio">ArrayAdapter
 *      call getView five time for each row and produces unexpected
 *      behaviour</a>
 * @see <a href=
 *      "http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/"
 *      >Android Expandable List View Tutorial</a >
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get the listview
		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.exp_list);

		// preparing list data
		TeamAdapter adapter = new TeamAdapter(this, loadData());

		// setting list adapter
		expListView.setAdapter(adapter);
	}

	private List<Team> loadData() {
		List<Team> data = new ArrayList<>();

		data.add(new Team("Team A", new Player("Player A1"), new Player(
				"Player A2"), new Player("Player A3"), new Player("Player A4"),
				new Player("Player A5")));

		data.add(new Team("Team B", new Player("Player B1"), new Player(
				"Player B2"), new Player("Player B3"), new Player("Player B4")));

		data.add(new Team("Team C", new Player("Player C1"), new Player(
				"Player C2"), new Player("Player C3")));

		return data;
	}
}
