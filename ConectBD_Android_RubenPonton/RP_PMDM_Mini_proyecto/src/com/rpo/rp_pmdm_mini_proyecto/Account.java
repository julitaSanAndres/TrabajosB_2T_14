package com.rpo.rp_pmdm_mini_proyecto;

/**
 * Clase generada para gestionar los datos de las cuentas
 *
 */
public class Account {
    private int id_cuenta;
    private String iban;
    private double balance;
    private String type;
    
	public Account(int id_cuenta, String iban, double balance, String type) {
		super();
		this.id_cuenta = id_cuenta;
		this.iban = iban;
		this.balance = balance;
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId_cuenta() {
		return id_cuenta;
	}

	public String getIban() {
		return iban;
	}

	public String getType() {
		return type;
	}

	
    
}
