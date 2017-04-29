package fr.dimacs.main;

public class MainDimacs {

	public static void main(String args[]){
		Thread t = new Thread(new CommandsManager());
		t.start();
	}
}
