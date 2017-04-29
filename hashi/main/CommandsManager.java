package fr.hashi.main;

import java.util.HashMap;
import java.util.Scanner;

import fr.dimacs.main.ClauseDimacs;
import fr.dimacs.main.Dimacs;
import fr.dimacs.main.ModeleDimacs;
import fr.dimacs.main.PredicatID;

public class CommandsManager implements Runnable {
	private boolean isRunning = true;
	
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(isRunning){
			String command = scan.nextLine();
			manageCommand(command.split(" "));
		}
	}

	/********************Gérer les commandes********************/
	private void manageCommand(String[] args) {
		if(args[0].equalsIgnoreCase("map")){
			new Map(Integer.parseInt(args[1]));
		}else if(args[0].equalsIgnoreCase("ile")){
			if(Map.getMap()!= null){
				Map.getMap().addIle(new Ile(Map.getMap().getPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2])),(short)Integer.parseInt(args[3])));
			}else{
				System.out.println("Pas de map");
			}
		}else if(args[0].equalsIgnoreCase("fnc")){
			if(Map.getMap() != null){
				if(args[1].equalsIgnoreCase("nosame")){
					Tools.printModele(Proprietes.getNoSameIleInPont());
				}else if(args[1].equalsIgnoreCase("nonear")){
					Tools.printModele(Proprietes.getNear());
				}else if(args[1].equalsIgnoreCase("crois")){
					Tools.printModele(Proprietes.getCroisement());
				}else if(args[1].equalsIgnoreCase("2p")){
					Tools.printModele(Proprietes.get2Pont());
				}else if(args[1].equalsIgnoreCase("auplus")){
					Tools.printModele(Proprietes.getAuPlus());
				}else if(args[1].equalsIgnoreCase("aumoins")){
					Tools.printModele(Proprietes.getAuMoins());
				}else if(args[1].equalsIgnoreCase("horivert")){
					Tools.printModele(Proprietes.getHoriVert());
				}else if(args[1].equalsIgnoreCase("all")){
					Tools.printModele(getModele());
				}else{
					System.out.println("Propriete non existante");
				}
			}else{
				System.out.println("pas de map");
			}
		}else if(args[0].equalsIgnoreCase("dimacs")){
			if(Map.getMap()!=null){
				createDimacs(getModele());
			}else{
				System.out.println("Pas de map");
			}
		}else if(args[0].equalsIgnoreCase("exit")){
			this.isRunning = false;
		}else{
			System.out.println("Commande inconnue");
		}
		
	}
	
	/********************Obtenir le fnc finale********************/
	
	public Modele getModele(){
		Modele modele = new Modele();
		Modele propPR = Proprietes.getNoSameIleInPont();
		modele.addAllClauses(propPR);
		Modele propHV = Proprietes.getHoriVert();
		if(propHV != null){
			modele.addAllClauses(propHV);
		}else{
			System.out.println("Tous les ponts sont horizontaux ou verticaux entre eux");
		}
		Modele propNE = Proprietes.getNear();
		if(propNE != null){
			modele.addAllClauses(propNE);
		}else{
			System.out.println("Aucun pont est à coté d'un autre pont");
		}
		Modele propCR = Proprietes.getCroisement();
		if(propCR != null){
			modele.addAllClauses(propCR);
		}else{
			System.out.println("Aucun croisement entre iles");
		}
		Modele prop2P = Proprietes.get2Pont();
		if(prop2P != null){
			modele.addAllClauses(prop2P);
		}
		Modele propAUP = Proprietes.getAuPlus();
		modele.addAllClauses(propAUP);
		
		Modele propAUM = Proprietes.getAuMoins();
		modele.addAllClauses(propAUM);
		System.out.println("------------FNC FINALE------------");
		System.out.println();
		modele = Tools.setSimplificationFromModele(modele);
		return modele;
	}
	
	/********************Créer le fichier dimacs********************/
	
	public void createDimacs(Modele modele){
		HashMap<Pont, Integer> listID = new HashMap<>();
		ModeleDimacs modeleD = new ModeleDimacs();
		ClauseDimacs clauseD = new ClauseDimacs();
		for(Clause clause : modele.getListclauses()){
			for(Pont pont : clause.getListPonts()){
				int id = 0;
				for(Pont pontA : listID.keySet()){
					if(Tools.isSamePontWithoutNegatif(pont, pontA)){
						id = listID.get(pontA);
						if(pont.isNegatif()) id = -id;
					}
				}
				if(id==0){
					id = pont.getIle1().getPosition()*10 + pont.getIle2().getPosition();
					if (pont.getIle1().isCopy()) id=id*10;
					if(pont.isNegatif()) id = -id;
					int pos = id;
					if(pos < 0) pos = -pos;
					listID.put(pont, pos);
				}
				clauseD.addPredicatID(new PredicatID(id));
			}
			modeleD.addClause(clauseD);
			clauseD = new ClauseDimacs();
		}
		Dimacs dimacs = new Dimacs("hashiwokakero_fnc.dim");
		dimacs.writeDimacs(modeleD);
	}
	
}
