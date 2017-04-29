package fr.hashi.main;

import java.util.ArrayList;
import java.util.List;

public class Modele{
	private List<Clause> listclauses = new ArrayList<>();

	public void addClause(Clause list){
		this.listclauses.add(list);
	}
	
	public void addAllClauses(Modele modele){
		this.listclauses.addAll(modele.getListclauses());
	}
	
	public List<Clause> getListclauses() {
		return listclauses;
	}

	public void setListclauses(List<Clause> listclause) {
		this.listclauses = listclause;
	}
	
	public void removeClause(Clause clause){
		this.listclauses.remove(clause);
	}
	
}
