package fr.hashi.main;

import java.util.ArrayList;
import java.util.List;

public class Tools {
	
	public static Ile copyIle(Ile ile){
		Ile ilec = new Ile(ile.getPosition(),ile.getN());
		if(ile.isCopy())
			ilec.setCopy();
		if(ile.isNegatif())
			ilec.setNegatif();
			
		return ilec;
	}
	
	public static boolean isSameIle(Ile ile1, Ile ile2){
		return ile1.getPosition() == ile2.getPosition() && ile1.isCopy() == ile2.isCopy() && ile1.isNegatif() == ile2.isNegatif();
	}
	
	public static boolean isSameIleWithoutCopy(Ile ile1, Ile ile2){
		return ile1.getPosition() == ile2.getPosition() && ile1.isNegatif() == ile2.isNegatif();
	}
	
	public static void printClause(Modele modele){
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
	
	
	
	public static boolean isEqualListIles(List<Ile> list1, List<Ile> list2){
		if(list1.size() != list2.size()) return false;
		int n = 0;
		for(Ile ile : list1){
			if(list2.contains(ile)) n++;
		}
		
		return n == list1.size();
	}
	
	
	
	
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
	
	public static boolean isOppos(Pont pont1, Pont pont2){
		return (isSameIle(pont1.getIle1(), pont2.getIle1()) && isSameIle(pont1.getIle2(), pont2.getIle2()) || isSameIle(pont1.getIle1(), pont2.getIle2()) && isSameIle(pont1.getIle2(), pont2.getIle1()))
				&& ( pont1.isNegatif() && !pont2.isNegatif()||!pont1.isNegatif() && pont2.isNegatif());
	}
	
	public static boolean isSamePont(Pont pont1, Pont pont2){
		return (isSameIle(pont1.getIle1(), pont2.getIle1()) && isSameIle(pont1.getIle2(), pont2.getIle2()) || isSameIle(pont1.getIle1(), pont2.getIle2()) && isSameIle(pont1.getIle2(), pont2.getIle1()))
				&& pont1.isNegatif() == pont2.isNegatif();
	}

	
	public static boolean isClauseValide(Clause clause){
		for(Pont pont1 : clause.getListPonts()){
			for(Pont pont2 : clause.getListPonts()){
				if(isOppos(pont1, pont2)) return true;
			}
		}
		return false;
	}
	
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

	
	public static Modele setSimplificationFromModele(Modele modele){
		
		Modele simplified_modele = new Modele();
		boolean canbeAdd;
		for(Clause clause1 : modele.getListclauses()){
			 canbeAdd = true;
			if(isClauseValide(clause1)) canbeAdd = false;
			for(Clause clause2 : modele.getListclauses()){
				if(isInClause(clause2, clause1) && clause1 != clause2){
					canbeAdd = false;
				}
			}
			if(canbeAdd)
				simplified_modele.addClause(clause1);
		}
		
		return simplified_modele;
	}
		
}
