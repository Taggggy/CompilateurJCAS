package fr.esisar.compilation.gencode;

import fr.esisar.compilation.global.src.*;
import fr.esisar.compilation.global.src3.*;

/**
 * Génération de code pour un programme JCas à partir d'un arbre décoré.
 */

class Generation {
   
   /**
    * Méthode principale de génération de code.
    * Génère du code pour l'arbre décoré a.
    */
   static Prog coder(Arbre a) {
      Prog.ajouterGrosComment("Programme généré par JCasc");

      // -----------
      // A COMPLETER
      // -----------
      Generation generator = new Generation();
      Pile.reset();
      Registres.reset();
      Erreur.reset();
      generator.coder_LISTE_DECL(a.getFils1());
      Pile.allocationPermanente();
      generator.coder_LISTE_INST(a.getFils2());
      
      // Fin du programme
      // L'instruction "HALT"
      Inst inst = Inst.creation0(Operation.HALT);
      // On ajoute l'instruction à la fin du programme
      Prog.ajouter(inst);
      Erreur erreur = new Erreur(); erreur.ecrireErreurs();
      // On retourne le programme assembleur généré
      return Prog.instance(); 
   }
   
   private void coder_LISTE_DECL(Arbre a) {
	   switch(a.getNoeud()) {
	   case ListeDecl:
		   coder_LISTE_DECL(a.getFils1());
		   coder_DECL(a.getFils2());
		   break;
	   case Vide:
		   break;
	   default:
		   break;
	   }
   }
   
   private void coder_DECL(Arbre a) {
	  coder_LISTE_IDENT(a.getFils1());
   }
   
   private void coder_LISTE_IDENT(Arbre a) {
	   switch(a.getNoeud()) {
	   case ListeIdent:
		  coder_LISTE_IDENT(a.getFils1());
		  Pile.reservationPermanente(a.getFils2());
		  break;
	   case Vide:
		  break;
	   default:
		  break;
	   }
   }
   

   private void coder_LISTE_INST(Arbre a) {
	   switch(a.getNoeud()) {
	   case ListeInst:
		   coder_LISTE_INST(a.getFils1());
		   coder_INST(a.getFils2());
		   break;
	   case Vide:
		   break;
	   default:
		   break;
	   }
   }
   
   private void coder_INST(Arbre a) {
	   if(a.getNoeud().equals(Noeud.Nop))
		   Prog.ajouterComment("Nop : " + a.getNumLigne());
	   else if(a.getNoeud().equals(Noeud.Ligne)) {
		   Prog.ajouterComment("new_line : " + a.getNumLigne());
		   Prog.ajouter(Inst.creation0(Operation.WNL));
	   } else {
		   
		 //Sauvegarde registres réservés
		   int sauvegarderR0 = 0;
		   int sauvegarderR1 = 0;
		   if(!Registres.isFreeReg(Registre.R0)) {
			   sauvegarderR0 = Pile.allocationTemporaire();
			   Prog.ajouter(Inst.creation2(Operation.STORE,Operande.opDirect(Registre.R0), Operande.creationOpIndirect(sauvegarderR0, Registre.GB)));
		   }
		   if(!Registres.isFreeReg(Registre.R1)) {
			   sauvegarderR1 = Pile.allocationTemporaire();
			   Prog.ajouter(Inst.creation2(Operation.STORE,Operande.opDirect(Registre.R1), Operande.creationOpIndirect(sauvegarderR1, Registre.GB)));
		   }

		   switch(a.getNoeud()) {
		   case Affect:
			   Prog.ajouterComment("Affect : " + a.getNumLigne());
			   coder_Affect(a);
			   break;
		   case Pour:
			   Prog.ajouterComment("Pour : " + a.getNumLigne());
			   coder_Pour(a);
			   break;
		   case TantQue:
			   Prog.ajouterComment("TantQue : " + a.getNumLigne());
			   coder_TantQue(a);
			   break;
		   case Si:
			   Prog.ajouterComment("Si : " + a.getNumLigne());
			   coder_Si(a);
			   break;
		   case Lecture:
			   Prog.ajouterComment("Lecture : " + a.getNumLigne());
			   coder_Lecture(a.getFils1());
			   break;
		   case Ecriture:
			   Prog.ajouterComment("Ecriture : " + a.getNumLigne());
			   boolean r1 = Registres.isFreeReg(Registre.R1);
			   Registres.reserver(Registre.R1);
			   coder_Ecriture(a.getFils1());
			   if(r1)	Registres.liberer(Registre.R1);
			   break;
		   default:
			   break;
		   }
		   
		   if(sauvegarderR0 != 0) {
			   Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpIndirect(sauvegarderR0, Registre.GB),Operande.opDirect(Registre.R0)));
			   Pile.liberationTemporaire();
		   }
		   if(sauvegarderR1 != 0) {
			   Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpIndirect(sauvegarderR1, Registre.GB),Operande.opDirect(Registre.R1)));
			   Pile.liberationTemporaire();
		   }
	   }	   
   }
   
   private void coder_Pour(Arbre a) {
	   boolean r0 = Registres.isFreeReg(Registre.R0);
	   Registres.reserver(Registre.R0);
	   boolean r1 = Registres.isFreeReg(Registre.R0);
	   Registres.reserver(Registre.R1);
	   Etiq debutFor = Etiq.nouvelle("debutFor");

	   coder_EXP(a.getFils1().getFils2(), Registre.R0);
	   coder_EXP(a.getFils1().getFils3(), Registre.R1);
	   
	   Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(Registre.R0), Operande.creationOpIndirect(Pile.getAddress(a.getFils1().getFils1().getChaine().toLowerCase()), Registre.GB)));
	   Prog.ajouter(debutFor);	   
	   coder_LISTE_INST(a.getFils2());
	   
	   switch(a.getFils1().getNoeud()) {
	   	case Increment:
			Prog.ajouter(Inst.creation2(Operation.ADD, Operande.creationOpEntier(1), Operande.opDirect(Registre.R0)));
			Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(Registre.R0), Operande.creationOpIndirect(Pile.getAddress(a.getFils1().getFils1().getChaine().toLowerCase()), Registre.GB)));	
			Prog.ajouter(Inst.creation2(Operation.CMP, Operande.R0, Operande.R1));
			Prog.ajouter(Inst.creation1(Operation.BGE,Operande.creationOpEtiq(debutFor)));
			break;
	   	case Decrement:
			Prog.ajouter(Inst.creation2(Operation.SUB, Operande.creationOpEntier(1), Operande.opDirect(Registre.R0)));
			Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(Registre.R0), Operande.creationOpIndirect(Pile.getAddress(a.getFils1().getFils1().getChaine().toLowerCase()), Registre.GB)));	
			Prog.ajouter(Inst.creation2(Operation.CMP, Operande.R0, Operande.R1));
			Prog.ajouter(Inst.creation1(Operation.BLE,Operande.creationOpEtiq(debutFor)));
			break;
	   	default:
	   		break;
	   }
	   if(r0)	Registres.liberer(Registre.R0);
	   if(r1)	Registres.liberer(Registre.R1);
   }
   
   private void coder_Affect(Arbre a) {
	   boolean r0 = Registres.isFreeReg(Registre.R0);
	   Registres.reserver(Registre.R0);
	   boolean r1 = Registres.isFreeReg(Registre.R1);
	   Registres.reserver(Registre.R1);
	   
	   Type type = coder_Place(a.getFils1(), Registre.R0);
	   if(type.getNature().equals(NatureType.Array)) {
		   int pileAddr;
		   if(a.getFils2().getNoeud().equals(Noeud.Conversion))
			   pileAddr = Pile.getAddress(a.getFils2().getFils1().getChaine().toLowerCase());
		   else
			   pileAddr = Pile.getAddress(a.getFils2().getChaine().toLowerCase());
		   Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpEntier(pileAddr), Operande.opDirect(Registre.R1)));
		   
		   //Affectation composée : fils1 = tableau, fils2 = tableau OU conversion /!\
		   Etiq debutAffect = Etiq.nouvelle("debutAffectationComposee");
		   Etiq finAffect = Etiq.nouvelle("finAffectationComposee");
		   Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpEntier(sizeArray(type)-1), Operande.opDirect(Registre.R2)));
		   Prog.ajouter(Inst.creation2(Operation.ADD, Operande.opDirect(Registre.R1), Operande.opDirect(Registre.R2)));
		   
		   //R2 contient la dernière adresse du tableau, on va la sauvegarder en pile, pour ré-utiliser R2
		   int sauvegardeR2 = Pile.allocationTemporaire();
		   Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(Registre.R2), Operande.creationOpIndirect(sauvegardeR2, Registre.GB)));
		   
		   Prog.ajouter(debutAffect);
		   Prog.ajouter(Inst.creation2(Operation.CMP, Operande.creationOpIndirect(sauvegardeR2, Registre.GB), Operande.opDirect(Registre.R1)));
		   Prog.ajouter(Inst.creation1(Operation.BGT, Operande.creationOpEtiq(finAffect)));
		   
		   //Si le dernier type est Interval, vérifier les bornes
		   Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpIndexe(0, Registre.GB, Registre.R1), Operande.opDirect(Registre.R2)));
		   if(getLastType(type).getNature().equals(NatureType.Interval))
			   Erreur.verifDebordementIntervalle(getLastType(type), Registre.R2);
		   
		   if(a.getFils2().getNoeud().equals(Noeud.Conversion)) //S'il y a un noeud conversion => conversion en Real
			   Prog.ajouter(Inst.creation2(Operation.FLOAT, Operande.opDirect(Registre.R2), Operande.opDirect(Registre.R2)));
		   Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(Registre.R2), Operande.creationOpIndexe(0, Registre.GB, Registre.R0)));
		   
		   //On incrémente les adresses des identificateurs à affecter
		   Prog.ajouter(Inst.creation2(Operation.ADD, Operande.creationOpEntier(1), Operande.opDirect(Registre.R0)));
		   Prog.ajouter(Inst.creation2(Operation.ADD, Operande.creationOpEntier(1), Operande.opDirect(Registre.R1)));

		   Prog.ajouter(Inst.creation1(Operation.BRA, Operande.creationOpEtiq(debutAffect)));
		   
		   Prog.ajouter(finAffect);
		   Pile.liberationTemporaire();
		   
	   } else {
		   coder_EXP(a.getFils2(), Registre.R1);
		   if(type.getNature().equals(NatureType.Interval)) {
			   Erreur.verifDebordementIntervalle(type, Registre.R1);
		   } 
		   Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(Registre.R1), Operande.creationOpIndexe(0,Registre.GB,Registre.R0)));
	   }
	   if(r0)	Registres.liberer(Registre.R0);
	   if(r1)	Registres.liberer(Registre.R1);
   }
   
   private void coder_TantQue(Arbre a) {
	   boolean r0 = Registres.isFreeReg(Registre.R0);
	   Registres.reserver(Registre.R0);
	   Etiq conditionWhile = Etiq.nouvelle("conditionWhile");
	   Etiq debutWhile = Etiq.nouvelle("debutWhile");
	   
	   Prog.ajouter(Inst.creation1(Operation.BRA, Operande.creationOpEtiq(conditionWhile)));
	   Prog.ajouter(debutWhile);

	   coder_LISTE_INST(a.getFils2());
	   Prog.ajouter(conditionWhile);
	   coder_EXP(a.getFils1(), Registre.R0);
	   Prog.ajouter(Inst.creation2(Operation.CMP, Operande.creationOpEntier(1), Operande.opDirect(Registre.R0)));
  	   Prog.ajouter(Inst.creation1(Operation.BEQ, Operande.creationOpEtiq(debutWhile)));
  	   if(r0)	Registres.liberer(Registre.R0);
   }

   private void coder_Si(Arbre a) {
	   boolean r0 = Registres.isFreeReg(Registre.R0);
	   Registres.reserver(Registre.R0);
	   Etiq finSi = Etiq.nouvelle("FinSi");
	   coder_EXP(a.getFils1(), Registre.R0);
	   Prog.ajouter(Inst.creation2(Operation.CMP, Operande.creationOpEntier(1), Operande.opDirect(Registre.R0)));
	   	if(a.getFils3().getNoeud().equals(Noeud.Vide)) { //If sans else
	   		Prog.ajouter(Inst.creation1(Operation.BNE, Operande.creationOpEtiq(finSi)));
	   		coder_LISTE_INST(a.getFils2());
	   	} else {
	   		Etiq sinon = Etiq.nouvelle("Sinon");
	   		Prog.ajouter(Inst.creation1(Operation.BNE, Operande.creationOpEtiq(sinon)));
	   		coder_LISTE_INST(a.getFils2());
	   		Prog.ajouter(Inst.creation1(Operation.BRA, Operande.creationOpEtiq(finSi)));
	   		Prog.ajouter(sinon);
	   		coder_LISTE_INST(a.getFils3());
	   	}
	   	Prog.ajouter(finSi);
	   	if(r0)	Registres.liberer(Registre.R0);
   }


   private void coder_Lecture(Arbre a) {
	   boolean r0 = Registres.isFreeReg(Registre.R0);
	   Registres.reserver(Registre.R0);
	   boolean r1 = Registres.isFreeReg(Registre.R1);
	   Registres.reserver(Registre.R1);
	   coder_Place(a, Registre.R0);
	   switch(a.getDecor().getType().getNature()) {
	   	case Real:
	   	   Prog.ajouterComment("Read real");
		   Prog.ajouter(Inst.creation0(Operation.RFLOAT));
		   Erreur.verifDebordementArithmetique();
		   Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(Registre.R1), Operande.creationOpIndexe(0, Registre.GB, Registre.R0)));
		   break;
	   	case Interval:
		   Prog.ajouterComment("Read int");
		   Prog.ajouter(Inst.creation0(Operation.RINT));
		   Erreur.verifDebordementArithmetique();
		   Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(Registre.R1), Operande.creationOpIndexe(0, Registre.GB, Registre.R0))); 
		   break;
	   	default:
		   break;
	   }
	   if(r0)	Registres.liberer(Registre.R0);
	   if(r1)	Registres.liberer(Registre.R1);
   }
   
   private void coder_Ecriture(Arbre a) {
	   if(!a.getNoeud().equals(Noeud.Vide)) {
		   coder_Ecriture(a.getFils1());
		   switch(a.getFils2().getDecor().getType().getNature()) {
			case String:
				String str = a.getFils2().getChaine();
				str = str.substring(1, str.length()-1); //On enlève les guillemets aux extrémités
				Prog.ajouter(Inst.creation1(Operation.WSTR, Operande.creationOpChaine(str)));
				break;
			case Interval:
				coder_EXP(a.getFils2(), Registre.R1);
				Prog.ajouter(Inst.creation0(Operation.WINT));
				break;
			case Real:
				coder_EXP(a.getFils2(), Registre.R1);
				Prog.ajouter(Inst.creation0(Operation.WFLOAT));
				break;
			default:
				break;
		   }
	   }  
   }
   
   private void genererUna(Arbre a, Registre reg) {
	   switch(a.getNoeud()) {
	   case MoinsUnaire:
		   Prog.ajouter(Inst.creation2(Operation.OPP, Operande.opDirect(reg), Operande.opDirect(reg)));
		   Erreur.verifDebordementArithmetique();
		   break;
	   case PlusUnaire:
		   break;
	   case Non:
		   Prog.ajouter(Inst.creation2(Operation.CMP, Operande.creationOpEntier(0), Operande.opDirect(reg)));
		   Prog.ajouter(Inst.creation1(Operation.SEQ, Operande.opDirect(reg)));
		   break;
	   case Conversion:
		   if(a.getFils1().getDecor().getType().getNature() != NatureType.Array)
			   Prog.ajouter(Inst.creation2(Operation.FLOAT, Operande.opDirect(reg), Operande.opDirect(reg)));
		default:
			break;
	   }
   }
   
   private void genererBin(Arbre a, Operande operand, Registre regDest) {
	   Operation operation = null;
	   switch(a.getNoeud()) {
	   case DivReel: operation = Operation.DIV;
	   if (a.getFils2().getDecor().getType().getNature().equals(NatureType.Interval))
		   Prog.ajouter(Inst.creation2(Operation.FLOAT, Operande.opDirect(regDest), Operande.opDirect(regDest)));
	   if (a.getFils1().getDecor().getType().getNature().equals(NatureType.Interval)) {
		   if(operand.getNature().equals(NatureOperande.OpDirect))
			   Prog.ajouter(Inst.creation2(Operation.FLOAT, operand, operand));
		   else {
			   int tmp = Pile.allocationTemporaire();
			   Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(regDest), Operande.creationOpIndirect(tmp, Registre.GB)));
			   Prog.ajouter(Inst.creation2(Operation.FLOAT, operand, Operande.opDirect(regDest)));
			   Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(regDest), operand));
			   Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpIndirect(tmp, Registre.GB), Operande.opDirect(regDest)));
			   Pile.liberationTemporaire();
		   }
	   }
	   break;
	   case Moins: operation = Operation.SUB; break;
	   case Plus: operation = Operation.ADD; break;
	   case Quotient: operation = Operation.DIV; break;
	   case Reste: operation = Operation.MOD; break;
	   case Mult: operation = Operation.MUL; break;
	   default: break;
	   }
	   if(operation != null) {
		   Prog.ajouter(Inst.creation2(operation, operand, Operande.opDirect(regDest)));
		   Erreur.verifDebordementArithmetique();
	   } else {
		   switch(a.getNoeud()) {
		   case Et: //  A ET B <--> (A+B)==2
			   Prog.ajouterComment("Operation ET : " + a.getNumLigne());
			   Prog.ajouter(Inst.creation2(Operation.ADD, operand, Operande.opDirect(regDest)));
			   Erreur.verifDebordementArithmetique();
			   operand = Operande.creationOpEntier(2);
			   operation = Operation.SEQ;
			   break;
		   case Ou: // A OU B <---> (A+B) >= 1
			   Prog.ajouterComment("Operation OU : " + a.getNumLigne());
			   Prog.ajouter(Inst.creation2(Operation.ADD, operand, Operande.opDirect(regDest)));
			   Erreur.verifDebordementArithmetique();
			   operand = Operande.creationOpEntier(1);
			   operation = Operation.SGE;
			   break;
		   case Inf: operation = Operation.SLT; break;
		   case InfEgal: operation = Operation.SLE; break;
		   case Sup: operation = Operation.SGT; break;
		   case SupEgal: operation = Operation.SGE; break;
		   case NonEgal: operation = Operation.SNE; break;
		   case Egal: operation = Operation.SEQ; break;
		   default: break;
		   }
		   Prog.ajouter(Inst.creation2(Operation.CMP, operand, Operande.opDirect(regDest)));
		   Prog.ajouter(Inst.creation1(operation, Operande.opDirect(regDest)));
	   }
   }
   
   private void coder_EXP(Arbre a, Registre reg) {
	   switch(a.getArite()) {
	   		case 0:  //Case F
   				Prog.ajouterComment("Chargement, ligne : " + a.getNumLigne());
   				if(a.getDecor().getDefn() != null && a.getDecor().getDefn().getNature().equals(NatureDefn.Var) && Pile.containsChaine(a.getChaine().toLowerCase())) {
   	   				int pileAddr = Pile.getAddress(a.getChaine().toLowerCase());
   					Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpIndirect(pileAddr,Registre.GB), Operande.opDirect(reg)));
   				}
   				else {
   					switch(a.getNoeud()) {
   					case Ident:
   						if(a.getChaine().toLowerCase().equals("true"))
   							Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpEntier(1), Operande.opDirect(reg)));
   						else if(a.getChaine().toLowerCase().equals("false"))
   							Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpEntier(0), Operande.opDirect(reg)));
   						else if(a.getChaine().toLowerCase().equals("max_int"))
   							Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpEntier(java.lang.Integer.MAX_VALUE), Operande.opDirect(reg)));
   						break;
   					case Entier:
   						Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpEntier(a.getEntier()), Operande.opDirect(reg)));
   						break;
   					case Reel:
   						Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpReel(a.getReel()), Operande.opDirect(reg)));
   						break;
   				    default:
   				    	break;
   					}
   				}
   				break;
	   		case 1: //Case OP E1
	   			coder_EXP(a.getFils1(), reg);
	   			genererUna(a, reg);
	   			break;
	   		case 2: //Case E1 op E2
	   			if(a.getNoeud().equals(Noeud.Index)) {
	   				coder_Place(a, reg);
	   				Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpIndexe(0, Registre.GB, reg), Operande.opDirect(reg)));
	   				break;
	   			} else { //Case E1 op E2 (and E2 not leaf)
	   				if(Registres.isFree()) {
		   				coder_EXP(a.getFils1(),reg);
		   				Registre reg2 = Registres.allouerReg();
		   				coder_EXP(a.getFils2(),reg2);
		   				genererBin(a,Operande.opDirect(reg2),reg);
		   				Registres.liberer(reg2);
		   			}
		   			else {
		   				coder_EXP(a.getFils2(),reg);
		   				int temp = Pile.allocationTemporaire();
		   				Prog.ajouterComment("Stockage temp");
		   				Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(reg), Operande.creationOpIndirect(temp,Registre.GB)));
		   				coder_EXP(a.getFils1(),reg);
		   				genererBin(a,Operande.creationOpIndirect(temp, Registre.GB),reg);
		   				Pile.liberationTemporaire();
		   			}
	   			}
	   			break;
	   		default:
	   			break;//ERREUR
	   }	
   }
   
   private Type coder_Place(Arbre a, Registre reg) {
	   switch(a.getNoeud()) {
	   case Ident:
		   Prog.ajouter(Inst.creation2(Operation.LOAD, Operande.creationOpEntier(Pile.getAddress(a.getChaine().toLowerCase())), Operande.opDirect(reg)));
		   return a.getDecor().getType();
	   case Index: //T[i1][i2]...[in]
		   coder_Place(a.getFils1(), reg); //Calcule l'offset de T[i1][i2]...[in-1] dans le registre.
		   int size = sizeArray(a.getFils1().getDecor().getType().getElement());
		   if(Registres.isFree()) {
			    Registre expReg = Registres.allouerReg();
		        coder_EXP(a.getFils2(), expReg);
		        Erreur.verifDebordementIndiceTableau(a.getFils1(), expReg);
		        
		        Prog.ajouter(Inst.creation2(Operation.SUB, Operande.creationOpEntier(a.getFils1().getDecor().getType().getIndice().getBorneInf()), Operande.opDirect(expReg)));
		        Prog.ajouter(Inst.creation2(Operation.MUL, Operande.creationOpEntier(size), Operande.opDirect(expReg)));
		        Prog.ajouter(Inst.creation2(Operation.ADD, Operande.opDirect(expReg), Operande.opDirect(reg)));
		        Registres.liberer(expReg);
		   } else {
		        int adresseTemporaire = Pile.allocationTemporaire();
		        Prog.ajouter(Inst.creation2(Operation.STORE, Operande.opDirect(reg), Operande.creationOpIndirect(adresseTemporaire, Registre.GB)));
		        coder_EXP(a.getFils2(), reg);
		        Erreur.verifDebordementIndiceTableau(a.getFils1(), reg);
		        
		        Prog.ajouter(Inst.creation2(Operation.SUB, Operande.creationOpEntier(a.getFils1().getDecor().getType().getIndice().getBorneInf()), Operande.opDirect(reg)));
		        Prog.ajouter(Inst.creation2(Operation.MUL, Operande.creationOpEntier(size), Operande.opDirect(reg)));
		        Prog.ajouter(Inst.creation2(Operation.ADD, Operande.creationOpIndirect(adresseTemporaire,Registre.GB), Operande.opDirect(reg)));
		        Pile.liberationTemporaire();
		      }
		   return a.getDecor().getType();
	   default:
		   return null;
	   }
   }
   
   private int sizeArray(Type type) {
	   int size = 1;
	   if(type.getNature() == NatureType.Array) {
		   size = type.getIndice().getBorneSup() - type.getIndice().getBorneInf()+1;
		   return sizeArray(type.getElement())*size;
	   } else {
		   return size;
	   }   
   }
   
   private Type getLastType(Type type) {
	   while(type.getNature().equals(NatureType.Array))
		   type = type.getElement();
	  return type;
   }
}