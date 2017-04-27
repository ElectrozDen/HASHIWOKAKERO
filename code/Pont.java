package fr.hashi.main;

public class Pont extends Predicat{
	private Ile ile1, ile2;
	
	public Pont(Ile i1, Ile i2){
		setIle1(i1);
		setIle2(i2);
	}

	public Ile getIle1() {
		return ile1;
	}

	public void setIle1(Ile ile1) {
		this.ile1 = ile1;
	}

	public Ile getIle2() {
		return ile2;
	}

	public void setIle2(Ile ile2) {
		this.ile2 = ile2;
	}


}
