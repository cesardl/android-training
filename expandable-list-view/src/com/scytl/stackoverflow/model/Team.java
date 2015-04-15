package com.scytl.stackoverflow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author cesardiaz
 *
 */
public class Team {

	private String name;
	private List<Player> players;

	public Team(String name, Player... players) {
		this.name = name;
		this.players = new ArrayList<>();
		for (int i = 0; i < players.length; i++) {
			this.players.add(players[i]);
		}
	}

	public String getName() {
		return name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Team [ name: ");
		sb.append(name);
		sb.append(", total players: ");
		sb.append(players.size());
		sb.append(" ] ");

		return sb.toString();
	}
}
