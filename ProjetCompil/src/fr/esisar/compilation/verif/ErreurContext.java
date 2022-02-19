// -------------------------------------------------------------------------
// A COMPLETER, avec les différents types d'erreur et les messages d'erreurs 
// correspondants
// -------------------------------------------------------------------------

package fr.esisar.compilation.verif;

/**
 * Type énuméré pour les erreurs contextuelles. Ce type énuméré définit toutes
 * les erreurs contextuelles possibles et permet l'affichage des messages
 * d'erreurs pour la passe 2.
 */

public enum ErreurContext {
	/**
	 * Le type utilisé par n'est pas le type attendu
	 */
	ErreurType, 
	
	/**
	 * L'identificateur utilisé n'est pas dans la table des symboles
	 */
	ErreurIDManquant, 
	
	/**
	 * L'identificateur utilisé pour une déclaration existe déjà dans la table des symboles
	 */
	ErreurIDExistant, 
	
	/**
	 * L'identificateur utilisé est de la mauvaise nature 
	 */
	ErreurNature;

   /**
	* 
	* @param s Chaine de caractères permettant de préciser quel élément comporte une erreur
    * @param numLigne Numéro de la ligne où est localisée l'erreur
	* @throws ErreurVerif
    */
   public void leverErreurContext(String s, int numLigne) throws ErreurVerif {
	  System.err.println("=====================================================================");
      System.err.println("/!\\ Erreur contextuelle : ");
      switch (this) {
      	case ErreurType:
      		System.err.print("Mauvais type " + s);
      		break;
      	case ErreurIDManquant:
      		System.err.print("Identificateur inconnu " + s);
      		break;
      	case ErreurIDExistant:
      		System.err.print("Identificateur déjà déclaré " + s); 
      		break;
      	case ErreurNature:
      		System.err.print("Identificateur de la mauvaise nature " + s);
      		break;
      	default:
            System.err.print("Erreur non repertoriée");
      }
      System.err.println(" ... ligne " + numLigne);
      System.err.println("=====================================================================");
      throw new ErreurVerif();
   }

}
