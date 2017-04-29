package fr.dimacs.main;

import java.io.File;
import java.util.Scanner;

public class CommandsManager implements Runnable{

	private boolean isRunning = true;
	private Dimacs dimacs = null;
	private ModeleDimacs modele = null;
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(isRunning){
			String command = scan.nextLine();
			manageCommand(command.split(" "));
		}
	}

	/********************Gére les commandes********************/
	private void manageCommand(String args[]){
		if(args[0].equalsIgnoreCase("new")){
			if(args[1].equalsIgnoreCase("dimacs")){
				if(args.length == 3){
					File file = new File(args[2]);
					if(file.exists())
						dimacs = new Dimacs(args[2]);
					else
						System.out.println("le fichier n'existe pas");
				}else{
					System.out.println("anque le nomb du fichier");
				}
			}else if(args[1].equalsIgnoreCase("modele")){
				modele = new ModeleDimacs();
			}else{
				System.out.println("Commande inconnue");
			}
		}else if(args[0].equalsIgnoreCase("add")){
			if(modele == null)
				System.out.println("Pas de modele creer");
			else{
				ClauseDimacs clause = new ClauseDimacs();
				for(int i = 1 ; i<args.length; i++){
					PredicatID id = new PredicatID(Integer.parseInt(args[i]));
					clause.addPredicatID(id);
				}
				modele.addClause(clause);
			}
		}else if(args[0].equalsIgnoreCase("print")){
			if(args[1].equalsIgnoreCase("dimacs")){
				if(dimacs != null){
					System.out.println(dimacs.readFile(true));
				}else{
					System.out.println("Pas de dimacs");
				}
			}else if(args[1].equalsIgnoreCase("modele")){
				if(modele != null){
					modele.print();
				}else{
					System.out.println("Pas de modele");
				}
			}else{
				System.out.println("Commande inconnue");
			}
		}else if(args[0].equalsIgnoreCase("to")){
			if(args[1].equalsIgnoreCase("dimacs")){
				if(args.length < 3){
					System.out.println("manque le nom de votre fichier");
				}else{
					if(modele == null){
						System.out.println("pas de modele");
					}else{
						dimacs = new Dimacs(args[2]);
						dimacs.writeDimacs(modele);
					}
				}
			}else if(args[1].equalsIgnoreCase("modele")){
				if(dimacs!=null){
					modele = dimacs.getModeleFromDimacs();
				}
			}else{
				System.out.println("Commande inconnue");
			}
		}else if(args[0].equalsIgnoreCase("dimacs")){
			if(args[1].equalsIgnoreCase("3sat")){
				if(dimacs!=null){
					dimacs.to3SAT();
				}else{
					System.out.println("pas de dimacs");
				}
			}else{
				System.out.println("Commande inconnue");
			}
		}else if(args[0].equalsIgnoreCase("modele")){
			if(args[1].equalsIgnoreCase("3sat")){
				if(modele!=null){
					modele = Sat3.get3SAT(modele);
				}else{
					System.out.println("pas de modele");
				}
			}else{
				System.out.println("Commande inconnue");
			}
		}else if(args[0].equalsIgnoreCase("exit")){
			this.isRunning = false;
		}else if(args[0].equalsIgnoreCase("solve")){
			if(args[1].equalsIgnoreCase("dimacs")){
				if(dimacs != null)
					Solveur.solve(dimacs);
				else
					System.out.println("Pas de dimacs");
			}else if(args[1].equalsIgnoreCase("modele")){
				if(modele != null)
					Solveur.solve(modele);
				else
					System.out.println("Pas de modele");
			}else{
				System.out.println("Commande inconnue");
			}
		}else{
			System.out.println("Commande inconnue");
		}
	}
}
