package com.rpo.rp_pmdm_mini_proyecto;

import java.util.ArrayList;

/**
 * Con esta clase podemos manejar las cuentas del cliente
 *
 */

public class MovementManager {
	ArrayList<Movement> movements;

	public MovementManager() {
		super();
		this.movements = new ArrayList<Movement>();
	}

	public Movement getMovement(int index) {
		return movements.get(index);
	}

	public void addMovement(Movement movement) {
		this.movements.add(movement);
	}
	
	public int numberOfMovements() {
		return movements.size();
	}
}
