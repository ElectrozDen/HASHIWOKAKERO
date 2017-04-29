package fr.dimacs.main;

import java.util.ArrayList;
import java.util.List;

public class Sat3 {
	/********************Transforme un modele en un modele sous forme 3SAT********************/
	public static ModeleDimacs get3SAT(ModeleDimacs md){
		ModeleDimacs modele = new ModeleDimacs();
		for(ClauseDimacs clause : md.getListclauses()){
			int size = clause.getListID().size();
			if(size == 1){
				int y1 = md.getAId();
				int y2 = md.getAId();
				ClauseDimacs clauseD = new ClauseDimacs();
				clauseD.addPredicatID(new PredicatID(clause.getListID().get(0).getId(),clause.getListID().get(0).isNegatif()));
				clauseD.addPredicatID(new PredicatID(y1)); clauseD.addPredicatID(new PredicatID(y2));
				ClauseDimacs clauseD2 = new ClauseDimacs();
				clauseD2.addPredicatID(new PredicatID(clause.getListID().get(0).getId(),clause.getListID().get(0).isNegatif()));
				clauseD2.addPredicatID(new PredicatID(y1)); clauseD2.addPredicatID(new PredicatID(-y2));
				ClauseDimacs clauseD3 = new ClauseDimacs();
				clauseD3.addPredicatID(new PredicatID(clause.getListID().get(0).getId(),clause.getListID().get(0).isNegatif()));
				clauseD3.addPredicatID(new PredicatID(-y1)); clauseD3.addPredicatID(new PredicatID(y2));
				ClauseDimacs clauseD4 = new ClauseDimacs();
				clauseD4.addPredicatID(new PredicatID(clause.getListID().get(0).getId(),clause.getListID().get(0).isNegatif()));
				clauseD4.addPredicatID(new PredicatID(-y1)); clauseD4.addPredicatID(new PredicatID(-y2));
				modele.addClause(clauseD);modele.addClause(clauseD2);modele.addClause(clauseD3);modele.addClause(clauseD4);
			}else if(size==2){
				int y1 = md.getAId();
				ClauseDimacs clauseD = new ClauseDimacs();
				clauseD.addPredicatID(new PredicatID(clause.getListID().get(0).getId(),clause.getListID().get(0).isNegatif()));
				clauseD.addPredicatID(new PredicatID(clause.getListID().get(1).getId(),clause.getListID().get(1).isNegatif()));
				clauseD.addPredicatID(new PredicatID(y1));
				ClauseDimacs clauseD2 = new ClauseDimacs();
				clauseD2.addPredicatID(new PredicatID(clause.getListID().get(0).getId(),clause.getListID().get(0).isNegatif()));
				clauseD2.addPredicatID(new PredicatID(clause.getListID().get(1).getId(),clause.getListID().get(1).isNegatif()));
				clauseD2.addPredicatID(new PredicatID(-y1));
				modele.addClause(clauseD);modele.addClause(clauseD2);
				
			}else if(size==3){
				modele.addClause(clause);
			}else{
				List<Integer> y = new ArrayList<>();
				for(int i = 1; i<=size-3;i++){
					y.add(md.getAId());
				}
				ClauseDimacs clauseD = new ClauseDimacs();
				clauseD.addPredicatID(new PredicatID(clause.getListID().get(0).getId(),clause.getListID().get(0).isNegatif()));
				clauseD.addPredicatID(new PredicatID(clause.getListID().get(1).getId(),clause.getListID().get(1).isNegatif()));
				clauseD.addPredicatID(new PredicatID(y.get(0)));
				modele.addClause(clauseD);
				for(int i = 2; i<=size-3; i++){
					ClauseDimacs clauseT = new ClauseDimacs();
					clauseT.addPredicatID(new PredicatID(-y.get(i-2)));
					clauseT.addPredicatID(new PredicatID(clause.getListID().get(i).getId(),clause.getListID().get(i).isNegatif()));
					clauseT.addPredicatID(new PredicatID(y.get(i-1)));
					modele.addClause(clauseT);
				}
				ClauseDimacs clauseD2 = new ClauseDimacs();
				clauseD2.addPredicatID(new PredicatID(clause.getListID().get(size-2).getId(),clause.getListID().get(size-2).isNegatif()));
				clauseD2.addPredicatID(new PredicatID(clause.getListID().get(size-1).getId(),clause.getListID().get(size-1).isNegatif()));
				clauseD2.addPredicatID(new PredicatID(-y.get(size-4)));
				modele.addClause(clauseD2);
			}
		}
		modele.removeAllAID();
		return modele;
		
	}
}
