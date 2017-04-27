package fr.hashi.main;

public class Ile extends Predicat {
	private int position;
	private boolean copy = false;
	private short n;
	
	public Ile(int pos, short nn){
		this.position = pos;
		this.n = nn;
	}
	
	public boolean isCopy(){
		return this.copy;
	}
	
	public void setCopy(){
		this.copy = true;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public short getN() {
		return n;
	}

	public void setN(short n) {
		this.n = n;
	}
	
}
