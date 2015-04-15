package com.scytl.stackoverflow.model;

/**
 * 
 * @author cesardiaz
 *
 */
public class Player {

	private String name;
	private boolean selected;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Player [ name: ");
		sb.append(name);
		sb.append(" ]");

		return sb.toString();
	}

}
