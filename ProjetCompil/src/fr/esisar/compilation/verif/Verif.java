package fr.esisar.compilation.verif;

import fr.esisar.compilation.global.src.*;
 
/**
 * Cette classe permet de réaliser la vérification et la décoration 
 * de l'arbre abstrait d'un programme.
 */
public class Verif {

   private Environ env; // L'environnement des identificateurs

   /**
    * Constructeur.
    */
   public Verif() {
      env = new Environ();
   }

   /**
    * Vérifie les contraintes contextuelles du programme correspondant à 
    * l'arbre abstrait a, qui est décoré et enrichi. 
    * Les contraintes contextuelles sont décrites 
    * dans Context.txt.
    * En cas d'erreur contextuelle, un message d'erreur est affiché et 
    * l'exception ErreurVerif est levée.
    */
   public void verifierDecorer(Arbre a) throws ErreurVerif {
      verifier_PROGRAMME(a);
   }

   /**
    * Initialisation de l'environnement avec les identificateurs prédéfinis.
    */
   private void initialiserEnv() {
      Defn def;
      // integer
      def = Defn.creationType(Type.Integer);
      def.setGenre(Genre.PredefInteger);
      env.enrichir("integer", def);
      
      // ------------
      // Définitions rajoutées ci-dessous
      // ------------
      
      //Type boolean
      def = Defn.creationType(Type.Boolean);
      def.setGenre(Genre.PredefBoolean);
      env.enrichir("boolean", def);
      
      //false
      def = Defn.creationConstBoolean(false);
      def.setGenre(Genre.PredefFalse);
      env.enrichir("false", def);
      
      //true
      def = Defn.creationConstBoolean(true);
      def.setGenre(Genre.PredefTrue);
      env.enrichir("true", def);
      
      //max_int
      def = Defn.creationConstInteger(java.lang.Integer.MAX_VALUE);
      def.setGenre(Genre.PredefMaxInt);
      env.enrichir("max_int", def);
      
      //real
      def = Defn.creationType(Type.Real);
      def.setGenre(Genre.PredefReal);
      env.enrichir("real", def);
      
      //string
      def = Defn.creationType(Type.String);
      env.enrichir("string", def);
   }

   /**************************************************************************
    * PROGRAMME
    **************************************************************************/
   private void verifier_PROGRAMME(Arbre a) throws ErreurVerif {
      initialiserEnv();
      verifier_LISTE_DECL(a.getFils1());
      verifier_LISTE_INST(a.getFils2());
   }

   /**************************************************************************
    * LISTE_DECL
    **************************************************************************/
   private void verifier_LISTE_DECL(Arbre a) throws ErreurVerif {
      switch(a.getNoeud()) {
      case Vide:
    	  break;
      case ListeDecl:
    	  verifier_LISTE_DECL(a.getFils1());
    	  verifier_DECL(a.getFils2());
    	  break;
      default:
    	  throw new ErreurInterneVerif("Arbre incorrect dans verifier_LISTE_DECL");
      }
   }

/**************************************************************************
    * LISTE_INST
    **************************************************************************/
   private void verifier_LISTE_INST(Arbre a) throws ErreurVerif {
	   switch(a.getNoeud()) {
	   case Vide:
		   break;
	   case ListeInst:
	   	  verifier_LISTE_INST(a.getFils1());
	   	  verifier_INST(a.getFils2());
	   	  break;
	   default:
	   	  throw new ErreurInterneVerif("Arbre incorrect dans verifier_LISTE_INST");
	   }
   }

   // ------------------------------------------------------------------------
   // Operations de vérifications et de décoration pour toutes 
   // les constructions d'arbres rajoutées ci-dessous
   // ------------------------------------------------------------------------
   
/**************************************************************************
    * DECL
    **************************************************************************/
   private void verifier_DECL(Arbre a) throws ErreurVerif {
		Type type = verifier_TYPE(a.getFils2());
		verifier_LIST_IDENT(a.getFils1(), type);
   }
	
   
/**************************************************************************
    * TYPE
    **************************************************************************/
   private Type verifier_TYPE(Arbre a) throws ErreurVerif {
		switch(a.getNoeud()) {
		case Ident:
			return verifier_IDENT_Util(a, NatureDefn.Type);
		case Intervalle:
			return verifier_INTERVALLE(a);
		case Tableau:
			return verifier_TABLEAU(a);
		default:
			throw new ErreurInterneVerif("Arbre incorrect dans verifier_TYPE");
		}
	}
   

/**************************************************************************
    * Utilisation de IDENT
    **************************************************************************/
private Type verifier_IDENT_Util(Arbre a, NatureDefn... natures) throws ErreurVerif {
	Defn identDefn = env.chercher(a.getChaine().toLowerCase());
	if(identDefn == null) //Si l'identificateur n'a pas été déclaré
		ErreurContext.ErreurIDManquant.leverErreurContext(": " + a.getChaine(), a.getNumLigne());
	
	boolean natureFound = false;
	for(int i = 0; i < natures.length; i++) {
		if(identDefn.getNature() == natures[i])
			natureFound = true;
	}
	
	if(!natureFound) //Si l'identificateur n'est pas de la nature attendue
		ErreurContext.ErreurNature.leverErreurContext(": " + a.getChaine(), a.getNumLigne());
	
	Decor decor = new Decor(identDefn, identDefn.getType());
	a.setDecor(decor);
	
	return identDefn.getType();
}

/**************************************************************************
 * Déclaration de IDENT
 **************************************************************************/
private Type verifier_IDENT_Decl(Arbre a, Type type) throws ErreurVerif {
	Defn identDefn = env.chercher(a.getChaine().toLowerCase());
	if(identDefn != null) //Si l'identificateur existe déjà
	{
		ErreurContext.ErreurIDExistant.leverErreurContext(": " + a.getChaine(), a.getNumLigne());
		return null;
	}
	else
	{
		env.enrichir(a.getChaine().toLowerCase(), Defn.creationVar(type));
		Decor decor = new Decor(Defn.creationVar(type), type);
		a.setDecor(decor);
		return type;
	}	
}

/**************************************************************************
 	* INTERVALLE
 	**************************************************************************/
private Type verifier_INTERVALLE(Arbre a) throws ErreurVerif {
	Integer borneInf = verifier_EXP_CONST(a.getFils1());
	Integer borneSup = verifier_EXP_CONST(a.getFils2());
	
	Type intervalle = Type.creationInterval(borneInf, borneSup);
	Defn defn = Defn.creationType(intervalle);
	Decor decor = new Decor(defn, defn.getType());
	a.setDecor(decor);
	return intervalle;
}

/**************************************************************************
	* EXP_CONST
	**************************************************************************/
private Integer verifier_EXP_CONST(Arbre a) throws ErreurVerif {
	switch(a.getNoeud()) {
	case Ident:
		verifier_IDENT_Util(a, NatureDefn.ConstInteger);
		return a.getDecor().getDefn().getValeurInteger();
	case Entier:
		Decor decor = new Decor(Type.Integer);
		a.setDecor(decor);
		return a.getEntier();
	case PlusUnaire:
		return verifier_PlusUnaire(a);
	case MoinsUnaire:
		return verifier_MoinsUnaire(a);
	default:
		throw new ErreurInterneVerif("Arbre incorrect dans verifier_EXP_CONST");
	}
}

/**************************************************************************
 	* PlusUnaire
 	**************************************************************************/
private Integer verifier_PlusUnaire(Arbre a) throws ErreurVerif {
	Integer valeur = verifier_EXP_CONST(a.getFils1());
	Decor decor = new Decor(a.getFils1().getDecor().getType());
	a.setDecor(decor);
	return valeur;
}

/**************************************************************************
	* MoinsUnaire
	**************************************************************************/
private Integer verifier_MoinsUnaire(Arbre a) throws ErreurVerif {
	Integer valeur = verifier_EXP_CONST(a.getFils1());
	Decor decor = new Decor(a.getFils1().getDecor().getType());
	a.setDecor(decor);
	return -valeur;
}

/**************************************************************************
 	* TABLEAU
 	**************************************************************************/
private Type verifier_TABLEAU(Arbre a) throws ErreurVerif {
	Type typeIndice = verifier_INTERVALLE(a.getFils1());
	Type typeElement = verifier_TYPE(a.getFils2());
	Type tableau = Type.creationArray(typeIndice, typeElement);
	
	Defn defn = Defn.creationType(tableau);
	
	Decor decor = new Decor(defn, defn.getType());
	a.setDecor(decor);
	return tableau;
}

/**************************************************************************
    * LISTE_IDENT
    **************************************************************************/
   private void verifier_LIST_IDENT(Arbre a, Type type) throws ErreurVerif {
	   switch(a.getNoeud()) {
	   case ListeIdent:
		   verifier_LIST_IDENT(a.getFils1(), type);
		   verifier_IDENT_Decl(a.getFils2(), type);
		   break;
	   case Vide:
		   break;
	   default:
		   throw new ErreurInterneVerif("Arbre incorrect dans verifier_LISTE_IDENT");
	   }
	}
   
/**************************************************************************
    * INST
    **************************************************************************/
   private void verifier_INST(Arbre a) throws ErreurVerif {
		switch(a.getNoeud()) {
		case Nop:
			break;
		case Affect:
			verifier_Affect(a);
			break;
		case Pour:
			verifier_Pour(a);
			break;
		case TantQue:
			verifier_TantQue(a);
			break;
		case Si:
			verifier_Si(a);
			break;
		case Lecture:
			verifier_Lecture(a);
			break;
		case Ecriture:
			verifier_Ecriture(a);
			break;
		case Ligne:
			break;
		default:
			throw new ErreurInterneVerif("Arbre incorrect dans verifier_INST");
		}	
	}

/**************************************************************************
    * Affect
    **************************************************************************/
	private void verifier_Affect(Arbre a) throws ErreurVerif {
		verifier_PLACE(a.getFils1());
		verifier_EXP(a.getFils2());
		
		Type typePlace = a.getFils1().getDecor().getType();
		Type typeExp = a.getFils2().getDecor().getType();
		
		ResultatAffectCompatible compatible = ReglesTypage.affectCompatible(typePlace, typeExp);
		if(compatible.getOk()) {
			if(compatible.getConv2()) {
				//Si ResultatAffectCompatible indique qu'il faut effectuer une conversion
				//On crée un noeud conversion que l'on insère au bon endroit
				a.setFils2(Arbre.creation1(Noeud.Conversion, a.getFils2(), a.getFils2().getNumLigne()));
				Decor decor = new Decor(Type.Real);
				a.getFils2().setDecor(decor);
			}
		}
		else
			ErreurContext.ErreurType.leverErreurContext("", a.getNumLigne());
	} 
	
/**************************************************************************
    * Binaire
    **************************************************************************/
	private void verifier_Binaire(Arbre a) throws ErreurVerif {
		verifier_EXP(a.getFils1());
		verifier_EXP(a.getFils2());
		Type typeExp1 = a.getFils1().getDecor().getType();
		Type typeExp2 = a.getFils2().getDecor().getType();
		
		ResultatBinaireCompatible compatible = ReglesTypage.binaireCompatible(a.getNoeud(), typeExp1, typeExp2);
		if(compatible.getOk()) {
			//On rajoute un noeud conversion si ResultatBinaireCompatible le demande
			if(compatible.getConv1()) {
				a.setFils1(Arbre.creation1(Noeud.Conversion, a.getFils1(), a.getFils1().getNumLigne()));
				Decor decor = new Decor(Type.Real);
				a.getFils1().setDecor(decor);
				
			}
			else if(compatible.getConv2()) {
				a.setFils2(Arbre.creation1(Noeud.Conversion, a.getFils2(), a.getFils2().getNumLigne()));
				Decor decor = new Decor(Type.Real);
				a.getFils2().setDecor(decor);
			}
			Decor decor = new Decor(compatible.getTypeRes());
			a.setDecor(decor);
		}
		else
			ErreurContext.ErreurType.leverErreurContext("", a.getNumLigne());
	} 

/**************************************************************************
    * Unaire
    **************************************************************************/
	private void verifier_Unaire(Arbre a) throws ErreurVerif {
		verifier_EXP(a.getFils1());
		
		Type typeExp = a.getFils1().getDecor().getType();
		
		ResultatUnaireCompatible compatible = ReglesTypage.unaireCompatible(a.getNoeud(), typeExp);
		if(compatible.getOk()) {
			Decor decor = new Decor(compatible.getTypeRes());
			a.setDecor(decor);
		}
		else
			ErreurContext.ErreurType.leverErreurContext("", a.getNumLigne());
	} 
	
/**************************************************************************
    * Pour
    **************************************************************************/
	private void verifier_Pour(Arbre a) throws ErreurVerif {
		verifier_PAS(a.getFils1());
		verifier_LISTE_INST(a.getFils2());
	}
	
/**************************************************************************
	* TantQue
	**************************************************************************/
	private void verifier_TantQue(Arbre a) throws ErreurVerif {
		verifier_EXP(a.getFils1());
		if(a.getFils1().getDecor().getType() != Type.Boolean)
			ErreurContext.ErreurType.leverErreurContext(" : type Boolean attendu", a.getNumLigne());
		verifier_LISTE_INST(a.getFils2());
	}
	
/**************************************************************************
	* Si
	**************************************************************************/
	private void verifier_Si(Arbre a) throws ErreurVerif {
		verifier_EXP(a.getFils1());
		if(a.getFils1().getDecor().getType() != Type.Boolean)
			ErreurContext.ErreurType.leverErreurContext(" : type Boolean attendu", a.getNumLigne());
		verifier_LISTE_INST(a.getFils2());
		verifier_LISTE_INST(a.getFils3());
	}
	
/**************************************************************************
	* Lecture
	**************************************************************************/
	private void verifier_Lecture(Arbre a) throws ErreurVerif {
		verifier_PLACE(a.getFils1());
		Type typePlace = a.getFils1().getDecor().getType();
		if(typePlace != Type.Real && typePlace.getNature() != NatureType.Interval)
			ErreurContext.ErreurType.leverErreurContext(": " + a.getFils1().getChaine() + " : type Real ou Interval attendu", a.getNumLigne());
	}
	
/**************************************************************************
	* Ecriture
	**************************************************************************/
	private void verifier_Ecriture(Arbre a) throws ErreurVerif {
		verifier_LISTE_EXP(a.getFils1());
		//a = a.getFils1();
		do {
			a = a.getFils1();
			NatureType natureExpression = a.getFils2().getDecor().getType().getNature();
			if(natureExpression != NatureType.Real && natureExpression != NatureType.String && natureExpression != NatureType.Interval)
				ErreurContext.ErreurType.leverErreurContext(": " + a.getFils2().getChaine() + " : type Real, String ou Interval attendu", a.getNumLigne());
			
		}while(a.getFils1().getNoeud() != Noeud.Vide);
	}

/**************************************************************************
	* PLACE
	**************************************************************************/
	private void verifier_PLACE(Arbre a) throws ErreurVerif {
		switch(a.getNoeud())
		{
		case Ident:
			verifier_IDENT_Util(a, NatureDefn.Var);
			break;
		case Index:
			verifier_Index(a);
			break;
		default:
			throw new ErreurInterneVerif("Arbre incorrect dans verifier_PLACE");
		}
	} 
	
/**************************************************************************
	* Index
	**************************************************************************/
	private void verifier_Index(Arbre a) throws ErreurVerif {
		verifier_PLACE(a.getFils1());
		verifier_EXP(a.getFils2());
		
		NatureType natureArray = a.getFils1().getDecor().getType().getNature();
		if(natureArray != NatureType.Array) {
			if(a.getFils1().getNoeud().equals(Noeud.Ident))
				ErreurContext.ErreurType.leverErreurContext(": " + a.getFils1().getChaine() + " : type Array attendu", a.getNumLigne());
			else
				ErreurContext.ErreurType.leverErreurContext(": " + " indexation trouvée" + " : type Array attendu", a.getNumLigne());
		}
		
		NatureType natureInterval = a.getFils2().getDecor().getType().getNature();
		if(natureInterval != NatureType.Interval) {
			if(natureInterval == NatureType.Real)
				ErreurContext.ErreurType.leverErreurContext(": " + a.getFils2().getReel() + " : type Interval attendu", a.getNumLigne());
			else
				ErreurContext.ErreurType.leverErreurContext(": " + a.getFils2().getChaine() + " : type Interval attendu", a.getNumLigne());
		}
	
		Decor decor = new Decor(a.getFils1().getDecor().getType().getElement());
		a.setDecor(decor);
	}
	
/**************************************************************************
	* PAS
	**************************************************************************/
	private void verifier_PAS(Arbre a) throws ErreurVerif {
		switch(a.getNoeud()) {
		case Increment:
		case Decrement:
			verifier_IDENT_Util(a.getFils1(), NatureDefn.Var);
			verifier_EXP(a.getFils2());
			verifier_EXP(a.getFils3());
			
			NatureType natureInterval = a.getFils1().getDecor().getType().getNature();
			if(natureInterval != NatureType.Interval)
				ErreurContext.ErreurType.leverErreurContext(" : type Interval attendu pour l'identificateur", a.getNumLigne());
			natureInterval = a.getFils2().getDecor().getType().getNature();
			if(natureInterval != NatureType.Interval)
				ErreurContext.ErreurType.leverErreurContext(" : type Interval attendu à gauche", a.getNumLigne());
			natureInterval = a.getFils3().getDecor().getType().getNature();
			if(natureInterval != NatureType.Interval)
				ErreurContext.ErreurType.leverErreurContext(" : type Interval attendu à droite", a.getNumLigne());
			break;
		default:
			throw new ErreurInterneVerif("Arbre incorrect dans verifier_PAS");
		}
	}
	
/**************************************************************************
	* EXP
	**************************************************************************/
	private void verifier_EXP(Arbre a) throws ErreurVerif {
		switch(a.getNoeud()) {
		case Et:
		case Ou:
		case Egal:
		case InfEgal:
		case SupEgal:
		case NonEgal:
		case Inf:
		case Sup:
		case Plus:
		case Moins:
		case Mult:
		case DivReel:
		case Reste:
		case Quotient:
			verifier_EXP(a.getFils1());
			verifier_EXP(a.getFils2());
			verifier_Binaire(a);
			break;
		case Index:
			verifier_Index(a);
			break;
		case PlusUnaire:
		case MoinsUnaire:
		case Non:
			verifier_EXP(a.getFils1());
			verifier_Unaire(a);
			break;
		case Entier:
			Defn defInteger = Defn.creationConstInteger(a.getEntier());
			a.setDecor(new Decor(defInteger, defInteger.getType()));
			break;
		case Reel:
			a.setDecor(new Decor(Type.Real));
			break;
		case Chaine:
			a.setDecor(new Decor(Type.String));
			break;
		case Ident:
			verifier_IDENT_Util(a, NatureDefn.Var, NatureDefn.ConstInteger, NatureDefn.ConstBoolean);
			break;
		case Conversion:
			a.setDecor(new Decor(Type.Real));
			break;
		default:
			throw new ErreurInterneVerif("Arbre incorrect dans verifier_EXP");
		}
	}
	
/**************************************************************************
	* LISTE_EXP
	**************************************************************************/
	private void verifier_LISTE_EXP(Arbre a) throws ErreurVerif {
		switch(a.getNoeud()) {
		case Vide:
			break;
		case ListeExp:
			verifier_LISTE_EXP(a.getFils1());
			verifier_EXP(a.getFils2());
			break;
		default:
			throw new ErreurInterneVerif("Arbre incorrect dans verifier_LISTE_EXP");
		}
	}
	
}
