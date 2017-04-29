package fr.hashi.main;

import java.util.ArrayList;
import java.util.List;

public class Tools {
	
	
	/********************Copie une île********************/
	public static Ile copyIle(Ile ile){
		Ile ilec = new Ile(ile.getPosition(),ile.getN());
		if(ile.isCopy())
			ilec.setCopy();
		if(ile.isNegatif())
			ilec.setNegatif();
			
		return ilec;
	}
	
	/********************Si 2 îles sont les mêmes********************/
	public static boolean isSameIle(Ile ile1, Ile ile2){
		return ile1.getPosition() == ile2.getPosition() && ile1.isCopy() == ile2.isCopy() && ile1.isNegatif() == ile2.isNegatif();
	}
	/********************Si 2 îles sont les mêmes sans prendre en compte les 2 grilles********************/
	public static boolean isSameIleWithoutCopy(Ile ile1, Ile ile2){
		return ile1.getPosition() == ile2.getPosition() && ile1.isNegatif() == ile2.isNegatif();
	}
	
	/********************Afficher un modele********************/
	public static void printModele(Modele modele){
		for(Clause clause: modele.getListclauses()){
			System.out.print("(");
			for(Pont pont : clause.getListPonts()){
				String spont="";
				if(pont.isNegatif()) spont+= "-";
				spont+="P("+pont.getIle1().getPosition();
				if(pont.getIle1().isCopy())spont+="'";
				spont+=","+pont.getIle2().getPosition();
				if(pont.getIle2().isCopy())spont+="'";
				spont+=")";
				if(clause.getListPonts().get(clause.getListPonts().size()-1)!=pont) spont+=" V ";
				System.out.print(spont);
			}
			System.out.print(")");
			if(modele.getListclauses().get(modele.getListclauses().size()-1) != clause) System.out.print("^");
			System.out.println();
		}
	}
	
	
	/********************Si 2 liste d'îles sont les mêmes********************/
	public static boolean isEqualListIles(List<Ile> list1, List<Ile> list2){
		if(list1.size() != list2.size()) return false;
		int n = 0;
		for(Ile ile : list1){
			if(list2.contains(ile)) n++;
		}
		
		return n == list1.size();
	}
	
	
	
	/********************Obtenir toutes les combinaisons possibles:
	 *-sans les combinaisons qui contient l'ile base 
	 * -en simplifiant aussi les combinaisons identiques (avec P(a,b)<=>P(b,a))
	 * ********************/
	public static void getAllCombinaisonsWithoutIle(Ile base, List<List<Ile>> listcombine, List<Ile> list, int n){
		for(Ile ile : Map.getMap().getListIles()){
			if(ile != base && !list.contains(ile)){
				List<Ile>tmp = new ArrayList<>();
				tmp.addAll(list);
				tmp.add(ile);
				if(n==1){
					boolean isAlready = false;
					for(List<Ile> listile : listcombine){
						if(isEqualListIles(listile, tmp)){
							isAlready = true;
							break;
						}
					}
					if(!isAlready)
						listcombine.add(tmp);
				}
				else
					getAllCombinaisonsWithoutIle(base, listcombine, tmp, n-1);
			}
		}
	}
	/********************Si 2 pont sont opposés(l'un négatif, l'autre positif)*******************/
	public static boolean isOppos(Pont pont1, Pont pont2){
		return (isSameIle(pont1.getIle1(), pont2.getIle1()) && isSameIle(pont1.getIle2(), pont2.getIle2()) || isSameIle(pont1.getIle1(), pont2.getIle2()) && isSameIle(pont1.getIle2(), pont2.getIle1()))
				&& ( pont1.isNegatif() && !pont2.isNegatif()||!pont1.isNegatif() && pont2.isNegatif());
	}
	
	/********************Si 2 pont sont les mêmes avec l'equivalence********************/
	public static boolean isSamePont(Pont pont1, Pont pont2){
		return (isSameIle(pont1.getIle1(), pont2.getIle1()) && isSameIle(pont1.getIle2(), pont2.getIle2()) || isSameIle(pont1.getIle1(), pont2.getIle2()) && isSameIle(pont1.getIle2(), pont2.getIle1()))
				&& pont1.isNegatif() == pont2.isNegatif();
	}
	/********************Si 2 pont sont les mêmes sans prendre en compte la négation********************/
	public static boolean isSamePontWithoutNegatif(Pont pont1, Pont pont2){
		return (isSameIle(pont1.getIle1(), pont2.getIle1()) && isSameIle(pont1.getIle2(), pont2.getIle2()) || isSameIle(pont1.getIle1(), pont2.getIle2()) && isSameIle(pont1.getIle2(), pont2.getIle1()));
	}

	/********************Regarde si une clause est valide********************/
	public static boolean isClauseValide(Clause clause){
		for(Pont pont1 : clause.getListPonts()){
			for(Pont pont2 : clause.getListPonts()){
				if(isOppos(pont1, pont2)) return true;
			}
		}
		return false;
	}
	
	/********************Regarde si cl1 est contenue dans cl2********************/
	public static boolean isInClause(Clause cl1, Clause cl2){
		if(cl1.getListPonts().size() > cl2.getListPonts().size()) return false;
		boolean isIn;
		
		for(Pont pont : cl1.getListPonts()){
			isIn = false;
			for(Pont pont2 : cl2.getListPonts()){
				if(isSamePont(pont, pont2)) isIn = true;
			}
			if(!isIn)return false;
		}
		return true;
	}

	
	/********************Simplification d'un modéle********************/
	public static Modele setSimplificationFromModele(Modele modele){
		
		Modele simplified_modele = new Modele();
		List<Clause> supprimed = new ArrayList<>();
		boolean canbeAdd;
		for(Clause clause1 : modele.getListclauses()){
			 canbeAdd = true;
			if(isClauseValide(clause1)) canbeAdd = false;
			for(Clause clause2 : modele.getListclauses()){
				if(isInClause(clause2, clause1) && clause1 != clause2){
					canbeAdd = false;
					break;
				}
			}
			if(canbeAdd)
				simplified_modele.addClause(clause1);
			else{
				boolean canBeSup = true;
				for(Clause cl3  : supprimed){
					if(isInClause(cl3,clause1)){
						canBeSup = false;
						break;
					}
				}
				if(canBeSup)
					supprimed.add(clause1);
				else
					simplified_modele.addClause(clause1);
			}
		}
		
		return simplified_modele;
	}
		
}
