package fr.hashi.main;

public class Predicat {
	private boolean negatif = false;
	
	public boolean isNegatif(){return this.negatif;}
	
	
	public void setNegatif(){
		this.negatif = true;
	}
	public void setPositif(){
		this.negatif = false;
	}
}
