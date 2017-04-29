package fr.hashi.main;

import java.util.ArrayList;

public class Map {
	
	private ArrayList<Ile> listIles = new ArrayList<>();
	private int size;
	
	
	private static Map map;
	
	public Map(int w){
		this.size = w;
		map = this;
	}
	
	
	
	public boolean isIle(int pos){
		for(Ile ile : this.listIles){
			if(ile.getPosition() == pos)
				return true;
		}
		return false;
	}
	
	public int getX(int pos){
		return pos/this.size + 1;
	}
	
	public int getY(int pos){
		int q = pos/this.size;
		return pos-(this.size*q);
	}
	
	public int getPosition(int x, int y){
		return (x-1)*this.size+y; 
	}

	public void addIle(Ile ile){
		Ile ilec = new Ile(ile.getPosition(), ile.getN());
		ilec.setCopy();
		this.listIles.add(ile);
		this.listIles.add(ilec);
	}
	
	public ArrayList<Ile> getListIles() {
		return listIles;
	}

	public void setListIles(ArrayList<Ile> listIles) {
		this.listIles = listIles;
	}


	
	public static Map getMap(){
		return map;
	}
}
