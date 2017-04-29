package fr.dimacs.main;

import java.io.IOException;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

public class Solveur {
	
	
	/********************Resoud à partir d'un modele********************/
	public static void solve(ModeleDimacs modele){
		Dimacs dimacs = new Dimacs("current_modele");
		dimacs.writeDimacs(modele);
		solve(dimacs);
	}

	/********************Resoud à partir d'un dimacs********************/
	public static void solve(Dimacs dimacs) {
		ISolver solver = SolverFactory.newDefault();
		solver . setTimeout (3600); // 1 hour timeout
		Reader reader = new DimacsReader ( solver );
		try {
			
			IProblem problem = reader.parseInstance(dimacs.getName());
			if(problem.isSatisfiable()){
				System.out.println("La fnc est satisfaisable");
				String models = reader.decode(problem.model());
				String model[]=models.split(" ");
				System.out.println("Voici un domaine de validité : ");
				for(int i = 0; i < model.length-1; i++){
					int mod = Integer.parseInt(model[i]);
					
					System.out.println(""+mod+ " vaut vrai");
				}
			}else{
				System.out.println("La fnc est insatisfaisable");
			}
		} catch (ParseFormatException | IOException | ContradictionException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
