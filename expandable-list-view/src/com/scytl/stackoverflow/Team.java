package com.scytl.stackoverflow;

import android.widget.TextView;

public class Team {

	public static final int TYPE_Team = 0;
	public static final int TYPE_Player = 1;

	private String strName;
	private boolean isSelected;
	private Team[] players;
	private Team parent;
	private int type;
	private boolean isExpanded = false;
	private TextView tViewNoOfSelectionFrmPatent;

	public Team(String strName, int type) {
		super();
		this.strName = strName;
		this.type = type;
	}

	public Team(String strName, int type, Team parent) {
		super();
		this.strName = strName;
		this.type = type;
		this.parent = parent;
	}

	public void toggleExpansion(TeamAdapter teamAdapter, int pos) {
		isExpanded = !isExpanded;
		if (isExpanded) {
			for (Team player : players) {
				pos++;
				teamAdapter.insert(player, pos);
			}
		} else {
			for (Team player : players) {
				teamAdapter.remove(player);
			}
		}
	}

	public void toggleSelection() {
		isSelected = !isSelected;
		/*
		 * for (Team player : parent.getPlayers()) { if (player.isSelected) {
		 * parent.isSelected = true; } }
		 */
	}

	public boolean hasChildren() {
		return players != null;
	}

	public void addPlayers(Team[] players) {
		this.players = players;
	}

	private Team[] getPlayers() {
		return players;
	}

	public String getStrName() {
		return strName;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public Team getParent() {
		return parent;
	}

	public void setParent(Team parent) {
		this.parent = parent;
	}

	public int getType() {
		return type;
	}

	public TextView gettViewNoOfSelectionFrmPatent() {
		return tViewNoOfSelectionFrmPatent;
	}

	public void settViewNoOfSelectionFrmPatent(
			TextView tViewNoOfSelectionFrmPatent) {
		System.out.println("TextView Id: "
				+ tViewNoOfSelectionFrmPatent.getId());
		this.tViewNoOfSelectionFrmPatent = tViewNoOfSelectionFrmPatent;
	}

}
