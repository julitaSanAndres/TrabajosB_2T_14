package com.rpo.rp_pmdm_mini_proyecto;

/**
 * Clase creada para gestionar los datos del cliente
 *
 */

public class Client {
	private int id_user;
	private String name;
	private String surname_1;
	private String surname_2;
	private String address;
	private String city;
	private String postal_code;
	private String DNI;

	public Client(int id_user, String name, String surname_1, String surname_2,
			String address, String city, String postal_code, String dNI) {
		super();
		this.id_user = id_user;
		this.name = name;
		this.surname_1 = surname_1;
		this.surname_2 = surname_2;
		this.address = address;
		this.city = city;
		this.postal_code = postal_code;
		DNI = dNI;
	}

	public int getId_user() {
		return id_user;
	}

	public String getName() {
		return name;
	}

	public String getSurname_1() {
		return surname_1;
	}

	public String getSurname_2() {
		return surname_2;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public String getDNI() {
		return DNI;
	}

	
	
}
