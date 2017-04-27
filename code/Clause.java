package fr.hashi.main;

import java.util.ArrayList;
import java.util.List;

public class Clause {
	private List<Pont> listPonts = new ArrayList<>();

	public List<Pont> getListPonts() {
		return listPonts;
	}
	
	public void addPont(Pont pont){
		this.listPonts.add(pont);
	}
	
	public void addListPont(List<Pont> listP){
		this.listPonts.addAll(listP);
	}

	public void setListPonts(List<Pont> listPonts) {
		this.listPonts = listPonts;
	}
	
	
}
