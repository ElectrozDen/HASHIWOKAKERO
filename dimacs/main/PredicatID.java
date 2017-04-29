package fr.dimacs.main;

public class PredicatID {
	private int id;
	private boolean negatif;
	
	public PredicatID(int id, boolean neg){
		if(id<0) id=-id;
		setId(id);
		setNegatif(neg);
	}
	
	public PredicatID(int id){
		if(id<0){
			this.setNegatif(true);
			id = -id;
		}else{
			this.setNegatif(false);
		}
		setId(id);
	}

	public boolean isNegatif() {
		return negatif;
	}

	public void setNegatif(boolean negatif) {
		this.negatif = negatif;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
