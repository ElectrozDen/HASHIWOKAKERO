package fr.hashi.main;

import java.util.ArrayList;

public class Map {
	
	private ArrayList<Ile> listIles = new ArrayList<>();
	private int width;
	private int height;
	
	
	private static Map map;
	
	public Map(int w, int h){
		this.width = w;
		this.setHeight(h);
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
		return pos/this.width;
	}
	
	public int getY(int pos){
		int q = pos/this.width;
		return pos-(this.width*q);
	}
	
	public int getPosition(int x, int y){
		return x*this.width+y; 
	}

	public void addIle(Ile ile){
		this.listIles.add(ile);
	}
	
	public ArrayList<Ile> getListIles() {
		return listIles;
	}

	public void setListIles(ArrayList<Ile> listIles) {
		this.listIles = listIles;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}
	
	public static Map getMap(){
		return map;
	}
}
