package fr.hashi.main;

public class HashiMain {
	
	/********************Démarrage du programme********************/
	public static void main(String args[]){
		Thread t = new Thread(new CommandsManager());
		t.start();
		
	}
}
