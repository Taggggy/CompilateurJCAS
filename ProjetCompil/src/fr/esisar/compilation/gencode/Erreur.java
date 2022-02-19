package fr.esisar.compilation.gencode;

import java.util.HashMap;
import java.util.Map;

import fr.esisar.compilation.global.src.Arbre;
import fr.esisar.compilation.global.src.Type;
import fr.esisar.compilation.global.src3.Etiq;
import fr.esisar.compilation.global.src3.Inst;
import fr.esisar.compilation.global.src3.Operande;
import fr.esisar.compilation.global.src3.Operation;
import fr.esisar.compilation.global.src3.Prog;
import fr.esisar.compilation.global.src3.Registre;

public class Erreur {
	
	private static Map<String, Boolean> errorUsed;
	private static Map<String, String> errorMessage;
	
	public static void reset() {
		errorUsed = new HashMap<String, Boolean>();
		errorUsed.put("debordementIntervalle", false);
		errorUsed.put("debordementArithmetique", false);
		errorUsed.put("debordementIndiceTableau", false);
		errorUsed.put("debordementPile", false);
		
		errorMessage = new HashMap<String, String>();
		errorMessage.put("debordementIntervalle", "Erreur - Debordement d'intervalle");
		errorMessage.put("debordementArithmetique", "Erreur - Debordement arithmetique");
		errorMessage.put("debordementIndiceTableau", "Erreur - Debordement d'indice de tableau");
		errorMessage.put("debordementPile", "Erreur - Debordement de pile");
	}
	
	public void ecrireErreurs() {
		for(String error : errorUsed.keySet()) {
			if(errorUsed.get(error))
				ajouterErreur(error, errorMessage.get(error));
		}
	}
	
	private void ajouterErreur(String label, String message) {
		Prog.ajouter(Etiq.lEtiq(label));
		Prog.ajouter(Inst.creation1(Operation.WSTR, Operande.creationOpChaine(message)));
		Prog.ajouter(Inst.creation0(Operation.HALT));
	}
	
	public static void verifDebordementIndiceTableau(Arbre a, Registre reg) {
        Prog.ajouter(Inst.creation2(Operation.CMP, Operande.creationOpEntier(a.getDecor().getType().getIndice().getBorneInf()), Operande.opDirect(reg)));
        Prog.ajouter(Inst.creation1(Operation.BLT, Operande.creationOpEtiq(Etiq.lEtiq("debordementIndiceTableau"))));
        Prog.ajouter(Inst.creation2(Operation.CMP, Operande.creationOpEntier(a.getDecor().getType().getIndice().getBorneSup()), Operande.opDirect(reg)));
        Prog.ajouter(Inst.creation1(Operation.BGT, Operande.creationOpEtiq(Etiq.lEtiq("debordementIndiceTableau"))));
        errorUsed.put("debordementIndiceTableau", true);
	}
	
	public static void verifDebordementIntervalle(Type type, Registre reg) {
		Prog.ajouter(Inst.creation2(Operation.CMP, Operande.creationOpEntier(type.getBorneInf()), Operande.opDirect(reg)));
        Prog.ajouter(Inst.creation1(Operation.BLT, Operande.creationOpEtiq(Etiq.lEtiq("debordementIntervalle"))));
        Prog.ajouter(Inst.creation2(Operation.CMP, Operande.creationOpEntier(type.getBorneSup()), Operande.opDirect(reg)));
        Prog.ajouter(Inst.creation1(Operation.BGT, Operande.creationOpEtiq(Etiq.lEtiq("debordementIntervalle"))));
        errorUsed.put("debordementIntervalle", true);
	}
	
	public static void verifDebordementArithmetique() {
		Prog.ajouter(Inst.creation1(Operation.BOV, Operande.creationOpEtiq(Etiq.lEtiq("debordementArithmetique"))));
		errorUsed.put("debordementArithmetique", true);
	}

	public static void verifDebordementPile(int taille) {
		Prog.ajouter(Inst.creation1(Operation.TSTO, Operande.creationOpEntier(taille)));
		Prog.ajouter(Inst.creation1(Operation.BOV, Operande.creationOpEtiq(Etiq.lEtiq("debordementPile"))));
		errorUsed.put("debordementPile", true);
	}
}
