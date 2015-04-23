package com.scytl.stackoverflow.model;

/**
 * 
 * @author cesardiaz
 *
 */
public class Player {

	private String name;
	private boolean checked;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Player [ name: ");
		sb.append(name);
		sb.append(" ]");

		return sb.toString();
	}

}
