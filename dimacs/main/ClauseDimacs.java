package fr.dimacs.main;

import java.util.ArrayList;
import java.util.List;

public class ClauseDimacs {
	private List<PredicatID> listID = new ArrayList<>();

	
	public void addPredicatID(PredicatID id){
		this.listID.add(id);
	}
	
	public List<PredicatID> getListID() {
		return listID;
	}

	public void setListID(List<PredicatID> listID) {
		this.listID = listID;
	}


	
	
	
	
}
