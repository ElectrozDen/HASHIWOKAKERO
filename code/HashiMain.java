package fr.hashi.main;

import java.util.Scanner;

public class HashiMain {
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Veuillez entre la taille du terrain :");
		int taille = scan.nextInt();
		Map map = new Map(taille, taille);
		System.out.println("Combien d'iles ? :");
		int nb = scan.nextInt();
		for(int i = 1; i<= nb ; i++){
			System.out.println("x pour l'ile n°"+nb+": ");
			int x = scan.nextInt();
			System.out.println("y pour l'ile n°"+nb+": ");
			int y = scan.nextInt();
			System.out.println("n pour l'ile n°"+nb+": ");
			short n = scan.nextShort();
			Ile ile = new Ile(map.getPosition(x, y),n);
			map.addIle(ile);
			Ile ilec = Tools.copyIle(ile);
			ilec.setCopy();
			map.addIle(ilec);
		}
		Modele modele = new Modele();
		
		System.out.println("------------Formalisation des propriétes------------");
		System.out.println();
		System.out.println("Il n’existe pas de pont reliant une île à elle-même ou à sa copie:");
		System.out.println();
		Modele propPR = Proprietes.getNoSameIleInPont();
		modele.addAllClauses(propPR);
		Tools.printClause(propPR);
		System.out.println();
		System.out.println("------------Les ponts sont horizontaux ou verticaux------------");
		System.out.println();
		Modele propHV = Proprietes.getHoriVert();
		if(propHV != null){
			modele.addAllClauses(propHV);
			Tools.printClause(propHV);
		}else{
			System.out.println("Tous les ponts sont horizontaux ou verticaux entre eux");
		}
		System.out.println();
		System.out.println("------------Les ponts ne se croisent pas------------");
		System.out.println();
		Modele propCR = Proprietes.getCroisement();
		if(propCR != null){
			modele.addAllClauses(propCR);
			Tools.printClause(propCR);
		}else{
			System.out.println("Aucun croisement entre iles");
		}
		System.out.println();
		System.out.println("------------Il y a deux ponts ou moins entre 2 îles------------");
		System.out.println();
		Modele prop2P = Proprietes.get2Pont();
		if(prop2P != null){
			modele.addAllClauses(prop2P);
			Tools.printClause(prop2P);
		}else{
			System.out.println("je sais pas quoi dire");
		}
		System.out.println();
		System.out.println("Le nombre de ponts partant de chaque île correspond au plus au nombre indiqué sur l’île:");
		System.out.println();
		Modele propAUP = Proprietes.getAuPlus();
		modele.addAllClauses(propAUP);
		Tools.printClause(propAUP);
		System.out.println();
		System.out.println("Le nombre de ponts partant de chaque île correspond au moins au nombre indiqué sur l’île:");
		System.out.println();
		Modele propAUM = Proprietes.getAuMoins();
		modele.addAllClauses(propAUM);
		Tools.printClause(propAUM);
		System.out.println();
		System.out.println("------------FNC FINALE------------");
		System.out.println();
		modele = Tools.setSimplificationFromModele(modele);
		Tools.printClause(modele);
		
		
		
	}
}
