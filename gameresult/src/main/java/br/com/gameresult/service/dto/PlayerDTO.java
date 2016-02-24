package br.com.gameresult.service.dto;

import java.util.Date;

public class PlayerDTO {
	
	private String name;
	
	private int killed;
	
	private int died;
	
	private Date date ;
	
	public PlayerDTO(String name, Date date, boolean killed) {
		this.name = name;
		this.date = date;
		if (killed) {
			addKilled();
		}else{
			addDied();
		}
	}

	public int getKilled() {
		return killed;
	}

	public void addKilled() {
		this.killed ++;
	}

	public int getDied() {
		return died;
	}

	public void addDied() {
		this.died ++;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}
	
	

}
