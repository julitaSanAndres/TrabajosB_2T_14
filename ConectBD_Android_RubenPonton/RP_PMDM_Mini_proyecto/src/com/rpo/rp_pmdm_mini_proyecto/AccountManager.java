package com.rpo.rp_pmdm_mini_proyecto;

import java.util.ArrayList;

/**
 * Con esta clase podemos manejar todas las cuentas de un cliente 
 *
 */

public class AccountManager {
	ArrayList<Account> client_Accounts;

	public AccountManager() {
		super();
		this.client_Accounts = new ArrayList<Account>();
	}

	public Account getClient_Accounts(int index) {
		return client_Accounts.get(index);
	}

	public void addAccount(Account account) {
		this.client_Accounts.add(account);
	}
	
	public int numberOfAccounts() {
		return client_Accounts.size();
	}
}
