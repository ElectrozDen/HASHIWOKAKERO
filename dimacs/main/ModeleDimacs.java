package fr.dimacs.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModeleDimacs {
	private List<ClauseDimacs> listclauses = new ArrayList<>();
	private List<Integer> listID = new ArrayList<>();
	private List<Integer> listAID = new ArrayList<>();
	
	
	
	
	private void addId(PredicatID id){
		if(!this.listID.contains(id.getId()))
			this.listID.add(id.getId());
	}
	

	public void addClause(ClauseDimacs list){
		for(PredicatID id : list.getListID()){
			addId(id);
		}
		this.listclauses.add(list);
	}
	
	public List<Integer> getListID() {
		return listID;
	}


	public void addAllClauses(ModeleDimacs modele){
		for(ClauseDimacs clause : modele.getListclauses())
			addClause(clause);
	}
	
	public List<ClauseDimacs> getListclauses() {
		return listclauses;
	}

	public void setListclauses(List<ClauseDimacs> listclause) {
		this.listclauses = listclause;
	}

	public int getMaxId(){
		int max = 0;
		for(int id : this.listID){
			if(max < id)max = id;
		}
		return max;
	}
	
	public int getAId(){
		Random random = new Random();
		int id = random.nextInt(300);
		while(this.listID.contains(id) || this.listAID.contains(id) || id == 0)
			id = random.nextInt(300);
		this.listAID.add(id);
		return id;
	}
	
	public void removeId(int id){
		this.listAID.remove(id);
	}
	
	public void removeAllAID(){
		this.listAID.clear();
	}
	
	public void print(){
		for(ClauseDimacs clause : this.getListclauses()){
			System.out.print("(");
			for(PredicatID pred : clause.getListID()){
				if(pred.isNegatif())System.out.print("-");
				System.out.print(""+pred.getId());
				if(clause.getListID().lastIndexOf(pred)!=clause.getListID().size()-1)
					System.out.print(" V " );
			}
			if(this.getListclauses().lastIndexOf(clause) != this.listclauses.size()-1)
				System.out.println(")^");
			else
				System.out.println(")");
		}
	}

	
}
