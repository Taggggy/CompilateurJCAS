/**
 * <i>fr.esisar.compilation.verif</i> permet de vérifier la sémantique d'un arbre abstrait et de décorer les noeuds de l'arbre avec des types et des natures.
 * </br></br>
 * 
 * <i>Verif.java</i> prend l'arbre abstrait généré par l'analyse syntaxique comme argument.</br>
 * Il commence par initialiser l'environnement avec tous les identificateurs de types ou de constantes.</br>
 * Il vérifie ensuite chaque noeud de l'arbre pour voir si les règles sémantiques sont respectées.</br>
 * 
 * En particulier, sont vérifiées :
 * <ul>
 * <li> L'existence ou non des identificateurs déclarés et utilisés </li>
 * <li> La concordance des types dans les expressions </li>
 * </ul>
 * 
 * Les types sont vérifiés dans <i>ReglesTypage.java</i> et renvoient un objet des classes ResultatAffectCompatible, ResultatBinaireCompatible ou ResultatUnaireCompatible selon l'expression.</br>
 * Ces objets indiquent si le type est correct ou non.</br>
 * 
 * Ils indiquent également si une conversion est nécessaire, si c'est le cas, lors de la vérification un noeud Conversion est inséré au bon endroit dans l'arbre.</br>
 * Si une erreur sémantique est repérée, une exception définie dans <i>ErreurContext.java</i> est levée.</br>
 * 
 * De plus, pour chaque identificateur, la vérification associe un type et une nature.
 */
package fr.esisar.compilation.verif;