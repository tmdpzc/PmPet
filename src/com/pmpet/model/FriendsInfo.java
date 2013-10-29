package com.pmpet.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FriendsInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5440661710221078929L;
	static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy.MM.dd");
	public String name;
	public Date date;
	public int petLevel;

	public FriendsInfo(String name, Date date, int petLevel) {
		super();
		this.name = name;
		this.date = date;
		this.petLevel = petLevel;
	}
	public FriendsInfo(String name, long time, int petLevel) {
		super();
		this.name = name;
		this.date = new Date(time);
		this.petLevel = petLevel;
	}

	public String getDateString(){
		return sFormat.format(date);
	}
	@Override
	public String toString() {
		return "FriendsInfo [name=" + name + ", date=" + sFormat.format(date)
				+ ", petLevel=" + petLevel + "]";
	}

}
