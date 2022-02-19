package fr.esisar.compilation.gencode;

import java.util.HashMap;
import java.util.Map;

import fr.esisar.compilation.global.src3.Registre;

public class Registres {
	private static Map<Registre, Boolean> registres; //true = libre; false = alloué
	private static Map<Registre, Boolean> registresReserves;
	
	public static void reset() {
		registres = new HashMap<Registre,Boolean>();
		for(Registre reg : Registre.values()) {
			registres.put(reg, true);
		}
		//Registres réservés
		registres.put(Registre.R0, false);
		registres.put(Registre.R1, false);
		registres.put(Registre.R2, false);
		registres.put(Registre.GB, false);
		registres.put(Registre.LB, false);
		
		registresReserves = new HashMap<Registre, Boolean>();
		registresReserves.put(Registre.R0, true);
		registresReserves.put(Registre.R1, true);
		registresReserves.put(Registre.R2, true);
	}
	
	public static boolean isFreeReg(Registre reg) {
		if(reg.equals(Registre.R0) || reg.equals(Registre.R1) || reg.equals(Registre.R2))
			return registresReserves.get(reg);
		else
			return registres.get(reg);
	}
	
	public static void reserver(Registre reg) {
		if(registresReserves.containsKey(reg))
			registresReserves.put(reg, false);
	}
	
	public static Registre allouerReg() {
		for(Registre reg : registres.keySet()) {
			if(registres.get(reg)) {
				registres.put(reg, false);
				return reg;
			}
		}
		return null;
	}
	
	public static void liberer(Registre reg) {
		if(reg.equals(Registre.R0) || reg.equals(Registre.R1) || reg.equals(Registre.R2))
			registresReserves.put(reg, true);
		else
			registres.put(reg, true);
	}
	
	
	public static boolean isFree() {
		for(Registre reg : registres.keySet()) {
			if(registres.get(reg)) {
				return true;
			}
		}
		return false;
	}
}
