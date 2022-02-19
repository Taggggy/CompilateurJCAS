
package fr.esisar.compilation.verif;

/**
 * Exception levée en cas d'erreur interne lors des vérifications contextuelles.
 */

public class ErreurInterneVerif extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param s informations sur l'erreur interne soulevée
	 */
	public ErreurInterneVerif(String s) {
		super("===========================================================\n" + 
            "                ERREUR INTERNE VERIF                       \n" + 
            "===========================================================\n" + 
            s + "\n" + 
            "===========================================================\n");
   }

}


