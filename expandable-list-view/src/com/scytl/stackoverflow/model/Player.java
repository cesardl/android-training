package com.scytl.stackoverflow.model;

/**
 * 
 * @author cesardiaz
 *
 */
public class Player {

	private String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Player [ name: ");
		sb.append(name);
		sb.append("] ");

		return sb.toString();
	}

}
