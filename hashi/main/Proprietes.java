package fr.hashi.main;

import java.util.ArrayList;
import java.util.List;

public class Proprietes {
	
	/********************Il n’existe pas de pont reliant une île à elle-même ou à sa copie********************/
	public static Modele getNoSameIleInPont(){
		Modele modele = new Modele();
		Clause clause = new Clause();
		for(Ile ile : Map.getMap().getListIles()){
			if(!ile.isCopy()){
				Pont pont = new Pont(ile, ile);
				pont.setNegatif();
				Ile ile_copy = Tools.copyIle(ile);
				ile_copy.setCopy();
				Pont pont_2 = new Pont(ile_copy,ile_copy);
				pont_2.setNegatif();
				clause.addPont(pont);
				modele.addClause(clause);
				clause = new Clause();
				clause.addPont(pont_2);
				modele.addClause(clause);
				clause = new Clause();
			}
		}
		return modele;
	}
	
	/********************Pas de ponts entre 2 îles collées********************/
	public static Modele getNear(){
		Modele modele = new Modele();
		Clause clause = new Clause();
		int size = Map.getMap().getListIles().size();
		
		for(int i = 0 ; i < size-1; i++){
			for(int j = i+1 ; j < size; j++){
				Ile ile1 = Map.getMap().getListIles().get(i);
				Ile ile2 = Map.getMap().getListIles().get(j);
				if(Definition.isNear(ile1, ile2) && ile1.isCopy()==ile2.isCopy()){
					Pont pont = new Pont(ile1,ile2);
					pont.setNegatif();
					clause.addPont(pont);
					modele.addClause(clause);
					clause = new Clause();
				}
			}
		}
		if(modele.getListclauses().size() != 0)
			return modele;
		else
			return null;
	}
	
	/********************Les ponts sont horizontaux ou verticaux********************/
	public static Modele getHoriVert(){
		
		Modele modele = new Modele();
		Clause clause = new Clause();
		int size = Map.getMap().getListIles().size();
		
		for(int i = 0 ; i < size-1; i++){
			for(int j = i+1 ; j < size; j++){
				Ile ile1 = Map.getMap().getListIles().get(i);
				Ile ile2 = Map.getMap().getListIles().get(j);
				if(!ile1.isCopy() && !ile2.isCopy() ){
				
					Pont pont = new Pont(ile1, ile2);
					pont.setNegatif();
					boolean propL = Definition.isLigne(ile1, ile2);
					boolean propC = Definition.isColonne(ile1, ile2);
					
					if(!propL  && !propC){
						clause.addPont(pont);
						modele.addClause(clause);
					}
					clause = new Clause();
					if(propL  && propC){
						clause.addPont(pont);
						modele.addClause(clause);
					}
					
				}
			}
		}
		
		if(modele.getListclauses().size() != 0)
			return modele;
		else
			return null;
	}
	
	/********************Les ponts ne se croisent pas********************/
	public static Modele getCroisement(){

		Modele modele = new Modele();
		Clause clause = new Clause();
		
		int size = Map.getMap().getListIles().size();
		
		if(size <4*2) return null;
		
		for(int i = 0 ; i < size; i++){
			for(int j = 0 ; j < size; j++){
				for(int k = 0 ; k < size; k++){
					for(int l = 0 ; l < size; l++){
						
						Ile ile1 = Map.getMap().getListIles().get(i);
						Ile ile2 = Map.getMap().getListIles().get(j);
						Ile ile3 = Map.getMap().getListIles().get(k);
						Ile ile4 = Map.getMap().getListIles().get(l);
						
						boolean crois = Definition.isCroiser(ile1, ile2, ile3, ile4);
						if(crois){
							Pont pont = new Pont(ile1, ile2);
							Pont pont2 = new Pont(ile3, ile4);
							pont.setNegatif();
							pont2.setNegatif();
							clause.addPont(pont);
							clause.addPont(pont2);
							modele.addClause(clause);
							clause = new Clause();
						}
					}
				}
			}
		}
		if(modele.getListclauses().size() != 0)
			return modele;
		else
			return null;
	}
	
	
	/********************Il y a deux ponts ou moins entre 2 îles********************/
	public static Modele get2Pont(){

		Modele modele = new Modele();
		Clause clause = new Clause();
		
		int size = Map.getMap().getListIles().size();
		
		for(int i = 0 ; i < size-1; i++){
			for(int j = i+1 ; j < size; j++){
				Ile ile1 = Map.getMap().getListIles().get(i);
				Ile ile2 = Map.getMap().getListIles().get(j);
				Ile ile1c = null;
				Ile ile2c = null;
				
				if(!ile1.isCopy() && ! ile2.isCopy()){
					for(Ile ile : Map.getMap().getListIles()){
						if( ile.isCopy() && Tools.isSameIleWithoutCopy(ile1, ile)){
							ile1c = ile;
						}else if(ile.isCopy() && Tools.isSameIleWithoutCopy(ile2, ile)){
							ile2c = ile;
						}
					}

		
					Pont pont = new Pont(ile1, ile2);
					Pont pont2 = new Pont(ile1c, ile2c);
					pont2.setNegatif();
					
					clause.addPont(pont2);
					clause.addPont(pont);
					modele.addClause(clause);
					clause = new Clause();
				}
				
				
			}
		}
		
		if(modele.getListclauses().size() != 0)
			return modele;
		else
			return null;
	}
	
	/********************Le nombre de ponts partant de chaque île correspond au plus au nombre indiqué sur l’île********************/
	public static Modele getAuPlus(){
		
		Modele modele = new Modele();
		Clause clause = new Clause();
		List<List<Ile>> listCombin = new ArrayList<>();
		for(Ile ile : Map.getMap().getListIles()){
			if(!ile.isCopy()){
				Tools.getAllCombinaisonsWithoutIle(ile, listCombin, new ArrayList<>(), ile.getN()+1);
				for(List<Ile> listile : listCombin){
					for(Ile ile2 : listile){
						Ile copy = null;
						if(ile2.isCopy()){
							for(Ile check : Map.getMap().getListIles()){
								if(Tools.isSameIleWithoutCopy(ile, check)&& check.isCopy())copy = check;
							}
						}else copy = ile;
						
						Pont pont = new Pont(copy, ile2);
						pont.setNegatif();
						clause.addPont(pont);
					}
					modele.addClause(clause);
					clause = new Clause();
				}
			
				listCombin.clear();
			}
		}
		listCombin.clear();
		return modele;
	}
	
	/********************Le nombre de ponts partant de chaque île correspond au moins au nombre indiqué sur l’île********************/
	public static Modele getAuMoins(){

		Modele modele = new Modele();
		Clause clause = new Clause();
		List<List<Ile>> listCombin = new ArrayList<>();
		for(Ile ile : Map.getMap().getListIles()){
			if(!ile.isCopy()){
				Tools.getAllCombinaisonsWithoutIle(ile, listCombin, new ArrayList<>(), Map.getMap().getListIles().size()-ile.getN()+1);
				for(List<Ile> listile : listCombin){
					for(Ile ile2 : listile){
						Ile copy = null;
						if(ile2.isCopy()){
							for(Ile check : Map.getMap().getListIles()){
								if(Tools.isSameIleWithoutCopy(ile, check)&& check.isCopy())copy = check;
							}
						}else copy = ile;
		
						Pont pont = new Pont(copy, ile2);
						clause.addPont(pont);
					}
					modele.addClause(clause);
					clause = new Clause();
				}
			
				listCombin.clear();
			}
		}
		listCombin.clear();
		return modele;
	}
	
	
}
