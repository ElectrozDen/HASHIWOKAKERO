package fr.hashi.main;

public class HashiMain {
	
	/********************D�marrage du programme********************/
	public static void main(String args[]){
		Thread t = new Thread(new CommandsManager());
		t.start();
		
	}
}
