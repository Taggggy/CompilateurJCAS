package fr.esisar.compilation.verif;

import fr.esisar.compilation.global.src.*;

/**
 * La classe ReglesTypage permet de définir les différentes règles 
 * de typage du langage JCas.
 */

public class ReglesTypage {

   /**
    * Teste si le type t1 et le type t2 sont compatibles pour l'affectation, 
    * c'est à dire si on peut affecter un objet de t2 à un objet de type t1.
    */

   public static ResultatAffectCompatible affectCompatible(Type t1, Type t2) {
	  ResultatAffectCompatible res = new ResultatAffectCompatible();
	  
	  if((t1.getNature() == NatureType.Real && t2.getNature() == NatureType.Real) ||
	     (t1.getNature() == NatureType.Interval && t2.getNature() == NatureType.Interval) ||
	     (t1.getNature() == NatureType.Boolean && t2.getNature() == NatureType.Boolean))
	  {
		  res.setConv2(false);
		  res.setOk(true);
	  }
	  else if (t1.getNature() == NatureType.Real && t2.getNature() == NatureType.Interval)
	  {
		  res.setConv2(true);  
		  res.setOk(true);
	  }
	  
	  else if(t1.getNature() == NatureType.Array && t2.getNature() == NatureType.Array) {
		  if(t1.getIndice().getBorneInf() != t2.getIndice().getBorneInf())
			  res.setOk(false);
		  else if(t1.getIndice().getBorneSup() != t2.getIndice().getBorneSup())
			  res.setOk(false);
		  else
			  res = affectCompatible(t1.getElement(), t2.getElement());
	  }
	  else
		  	res.setOk(false);
	  
      return res;
   }

   /**
    * Teste si le type t1 et le type t2 sont compatibles pour l'opération 
    * binaire représentée dans noeud.
    */

   public static ResultatBinaireCompatible binaireCompatible
      (Noeud noeud, Type t1, Type t2) {
	  ResultatBinaireCompatible res = new ResultatBinaireCompatible();
	  if (t1.getNature().equals(NatureType.Interval) && t2.getNature().equals(NatureType.Real)) {
		  res.setConv1(true);
		  res.setConv2(false);
	  }
	  else if(t1.getNature().equals(NatureType.Real) && t2.getNature().equals(NatureType.Interval)) {
		  res.setConv2(true);
		  res.setConv1(false);
	  }
	  else {
		  res.setConv2(false);
		  res.setConv1(false);
	  }
	  res.setOk(true);
	  if((noeud.equals(Noeud.Et) || noeud.equals(Noeud.Ou))//test and, or
		   && t1.getNature().equals(NatureType.Boolean) && t2.getNature().equals(NatureType.Boolean)) {
			  res.setTypeRes(Type.Boolean);
	  }
	  else if(noeud.equals(Noeud.Egal) || noeud.equals(Noeud.Inf) || noeud.equals(Noeud.InfEgal) //Test =,<,<=,>,>=,/=
			   || noeud.equals(Noeud.Sup) || noeud.equals(Noeud.SupEgal) || noeud.equals(Noeud.NonEgal)) {
		  if((t1.getNature().equals(NatureType.Interval) || t1.getNature().equals(NatureType.Real))
			  				&& (t2.getNature().equals(NatureType.Interval) || t2.getNature().equals(NatureType.Real))) {
			  res.setTypeRes(Type.Boolean);
		  }
		  else
			  res.setOk(false);
	  }
	  else if(noeud.equals(Noeud.Plus) || noeud.equals(Noeud.Moins) || noeud.equals(Noeud.Mult)) { //Test +,-,*
		  if((t1.getNature().equals(NatureType.Interval) && t2.getNature().equals(NatureType.Real))
				  || (t1.getNature().equals(NatureType.Real) && t2.getNature().equals(NatureType.Interval))
				  || (t1.getNature().equals(NatureType.Real) && t2.getNature().equals(NatureType.Real))) {
			  res.setTypeRes(Type.Real);
		  }
		  else if(t1.getNature().equals(NatureType.Interval) && t2.getNature().equals(NatureType.Interval)) {
			  res.setTypeRes(Type.Integer);
		  }
		  else {//Pourrait etre généralisé comme les autres mais cela rendrait le code bien plus lourd
			  res.setOk(false);
		  }
	  }
	  else if((noeud.equals(Noeud.Reste) || noeud.equals(Noeud.Quotient)) //Test div, mod
			  && t1.getNature().equals(NatureType.Interval) && t2.getNature().equals(NatureType.Interval)) {
		  res.setTypeRes(Type.Integer);
	  }
	  else if(noeud.equals(Noeud.DivReel)//Test "/"
			  		&& (t1.getNature().equals(NatureType.Interval) || t1.getNature().equals(NatureType.Real))
			  		&& (t2.getNature().equals(NatureType.Interval) || t2.getNature().equals(NatureType.Real))){
		  res.setTypeRes(Type.Real);
	  }
	  else 
		  res.setOk(false);
	  return res;
   }

   /**
    * Teste si le type t est compatible pour l'opération binaire représentée 
    * dans noeud.
    */
   public static ResultatUnaireCompatible unaireCompatible
         (Noeud noeud, Type t) {
	  ResultatUnaireCompatible res = new ResultatUnaireCompatible();
	  res.setOk(true);
	  if(noeud.equals(Noeud.Non) 
			  && t.getNature().equals(NatureType.Boolean)){
		  res.setTypeRes(Type.Boolean);
	  }
	  else if((noeud.equals(Noeud.PlusUnaire) || noeud.equals(Noeud.MoinsUnaire)) //Test +,- for interval
			  && t.getNature().equals(NatureType.Interval)) {
		  res.setTypeRes(Type.Integer);
	  }
	  else if((noeud.equals(Noeud.PlusUnaire) || noeud.equals(Noeud.MoinsUnaire)) //Test +,- for interval
			  && t.getNature().equals(NatureType.Real)) {
		  res.setTypeRes(Type.Real);
	  }
	  else {
		  res.setOk(false);
	  }
      return res;
   }
         
}

