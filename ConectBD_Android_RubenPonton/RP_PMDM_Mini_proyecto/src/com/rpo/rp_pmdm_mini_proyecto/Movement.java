package com.rpo.rp_pmdm_mini_proyecto;

import java.util.Date;

public class Movement {
		
	private int id_mov;
	private double amount;
	private double current_balance;
	private String description;
	private Date date;
	private String mov_type;
	
	
	public Movement(int id_mov, double amount, double current_balance,
			String description, Date date, String mov_type) {
		super();
		this.id_mov = id_mov;
		this.amount = amount;
		this.current_balance = current_balance;
		this.description = description;
		this.date = date;
		this.mov_type = mov_type;
	}


	public int getId_mov() {
		return id_mov;
	}


	public double getAmount() {
		return amount;
	}


	public double getCurrent_balance() {
		return current_balance;
	}


	public String getDescription() {
		return description;
	}


	public Date getDate() {
		return date;
	}


	public String getMov_type() {
		return mov_type;
	}

}
