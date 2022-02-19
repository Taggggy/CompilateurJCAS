package fr.esisar.compilation.gencode;

import java.util.HashMap;
import java.util.Map;

import fr.esisar.compilation.global.src.Arbre;
import fr.esisar.compilation.global.src.NatureType;
import fr.esisar.compilation.global.src.Type;
import fr.esisar.compilation.global.src3.Inst;
import fr.esisar.compilation.global.src3.Operande;
import fr.esisar.compilation.global.src3.Operation;
import fr.esisar.compilation.global.src3.Prog;

public class Pile {
	
	private static Map<String, Integer> pile;
	private static int SP; //pointeur de pile
	private static int reservationPermanente;
	
	public static void reset() {
		pile = new HashMap<String, Integer>(); //Variables permanentes du programme
		SP = 1;
		reservationPermanente = 0;
	}
	
	public static boolean containsChaine(String idf) {
		return pile.containsKey(idf);
	}
	
	public static int getAddress(String idf) {
		return pile.get(idf);
	}
	
	public static void reservationPermanente(Arbre a) {
		Type type = a.getDecor().getType();
		if(type.getNature().equals(NatureType.Array)) {
			pile.put(a.getChaine().toLowerCase(), SP);
			int decalage = 1;
			while(type.getNature().equals(NatureType.Array)) {
				decalage = decalage * ( type.getIndice().getBorneSup()- type.getIndice().getBorneInf() + 1);
				type = type.getElement();
			}
			reservationPermanente += decalage;
			SP += decalage;
		} 
		else //Boolean, Interval, Real 
		{
			pile.put(a.getChaine().toLowerCase(), SP);
			reservationPermanente += 1;
			SP++;
		}
	}
	
	public static void allocationPermanente() {
		Prog.ajouterComment("Début de l'allocation d'une variable permanente dans la pile");
		Erreur.verifDebordementPile(reservationPermanente);
		Prog.ajouter(Inst.creation1(Operation.ADDSP, Operande.creationOpEntier(reservationPermanente)));
		Prog.ajouterComment("Fin de l'allocation d'une variable permanente dans la pile");
	}
	
	public static int allocationTemporaire() {
		Prog.ajouterComment("Début de l'allocation d'une variable temporaire dans la pile");
		Erreur.verifDebordementPile(1);
		SP++;
		Prog.ajouterComment("Fin de l'allocation d'une variable temporaire dans la pile");
		return SP-1;
	}
	
	public static void liberationTemporaire() { //On libère uniquement des variables temporaires
		Prog.ajouterComment("Libération d'une variable temporaire dans la pile");
		SP--;
		Prog.ajouter(Inst.creation1(Operation.SUBSP, Operande.creationOpEntier(1)));
	}
	
}
