package fr.dimacs.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Dimacs {
	private File file;
	
	
	
	
	public Dimacs(String name){
		this.file = new File(name);
	}
	
	
	public String getName(){
		return this.file.getName();
	}
	
	
	/********************Renvoie le contenu d'un fichier dimacs********************/
	public String readFile(boolean comments){
		String in_file="";
		String line;
		FileReader f_reader;
		try {
			f_reader = new FileReader(this.file);
			BufferedReader reader = new BufferedReader(f_reader);
			
			while ( (line = reader.readLine()) != null) 
		    {
				if(line.charAt(0)!='c')
					in_file+=line+"\n";
		    }
			reader.close();
			f_reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in_file;
	}
	
	/********************Ecris un fichier dimacs à partir d'un modele********************/
	public void writeDimacs(ModeleDimacs modele){
		String dimacs ="";
		dimacs+="p cnf "+modele.getMaxId()+" "+modele.getListclauses().size()+"\n";
		for(ClauseDimacs clause : modele.getListclauses()){
			for(PredicatID id : clause.getListID()){
				if(id.isNegatif())dimacs+="-";
				dimacs+=id.getId();
				if(clause.getListID().lastIndexOf(id)!= clause.getListID().size()-1)dimacs+=" ";
				else dimacs+=" 0";
			}
			if(modele.getListclauses().lastIndexOf(clause)!=modele.getListclauses().size()-1)
				dimacs+="\n";
		}
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try{
		    Writer w = new FileWriter(this.file,true);

		    BufferedWriter output = new BufferedWriter(w);
		    String linesd[] = dimacs.split("\n");
		    int i = 0;
		    for(String myline : linesd){
		    	 output.write(myline);
		    	 if(i!=linesd.length-1)
		    		 output.newLine();
		    	 i++;
		    }
		    output.flush();
		    output.close(); 
		}
		catch(Exception e)
		    {
			System.out.println(e);
		    } 
	
	}
	
	/********************Obtenir un modele à partir d'un fichier********************/
	
	public ModeleDimacs getModeleFromDimacs(){
		ModeleDimacs modele = new ModeleDimacs();
		String dimacs = readFile(false);
		String lines[] = dimacs.split("\n");
		String params[] = lines[0].split(" ");
		//int nb_var = Integer.parseInt(params[2]);
		int nb_clauses = Integer.parseInt(params[3]);
		for(int i = 1; i<=nb_clauses;i++){
			String ids[] = lines[i].split(" ");
			ClauseDimacs clause = new ClauseDimacs();
			for(String id : ids){
				int id_i = Integer.parseInt(id);
				if(id_i != 0){
					PredicatID pred = new PredicatID(id_i);
					clause.addPredicatID(pred);
				}
			}
			modele.addClause(clause);
		}
		
		return modele;
	}
	
	/********************Transormer le dimacs en 3SAT********************/
	public void to3SAT(){
		ModeleDimacs modele = getModeleFromDimacs();
		ModeleDimacs modele3SAT = Sat3.get3SAT(modele);
		writeDimacs(modele3SAT);
	}
	
	
}
