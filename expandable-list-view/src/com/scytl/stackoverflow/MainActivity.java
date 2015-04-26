package com.scytl.stackoverflow;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.scytl.stackoverflow.model.Player;
import com.scytl.stackoverflow.model.Team;

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

	private static final String tag = MainActivity.class.getSimpleName();

	private TextView vCount;

	private TeamAdapter mAdapter;

	private DataSetObserver mObserver = new DataSetObserver() {
		@Override
		public void onChanged() {
			String count = mAdapter.getTotalSelected();
			Log.i(tag, String.format("Updating total: %s", count));

			vCount.setText(count);
			super.onChanged();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		vCount = (TextView) findViewById(R.id.count);

		// get the listview
		ExpandableListView expListView = (ExpandableListView) findViewById(R.id.exp_list);

		// preparing list data
		mAdapter = new TeamAdapter(this, loadData());
		mAdapter.registerDataSetObserver(mObserver);

		// setting list adapter
		expListView.setAdapter(mAdapter);
	}

	@Override
	protected void onDestroy() {
		mAdapter.unregisterDataSetObserver(mObserver);
		super.onDestroy();
	}

	private List<Team> loadData() {
		List<Team> data = new ArrayList<Team>();

		data.add(new Team("Team A", new Player("Player A1"), new Player(
				"Player A2"), new Player("Player A3"), new Player("Player A4"),
				new Player("Player A5")));

		data.add(new Team("Team B", new Player("Player B1"), new Player(
				"Player B2"), new Player("Player B3"), new Player("Player B4")));

		data.add(new Team("Team C", new Player("Player C1"), new Player(
				"Player C2"), new Player("Player C3")));

		data.add(new Team("Team D", new Player("Player D1"), new Player(
				"Player D2"), new Player("Player D3"), new Player("Player D4"),
				new Player("Player D5"), new Player("Player D6")));

		return data;
	}

}
