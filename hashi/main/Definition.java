package fr.hashi.main;

public class Definition {
	
	
	
	/********************Définition L(a,b)********************/
	public static boolean isLigne(Ile a, Ile b){
		int x1 = Map.getMap().getX(a.getPosition());
		int x2 = Map.getMap().getX(b.getPosition());
		
		if(x1==x2){
			return true;
		}else{
			return false;
		}
	}
	/********************Définition C(a,b)********************/
	
	public static boolean isColonne(Ile a, Ile b){
		int y1 = Map.getMap().getY(a.getPosition());
		int y2 = Map.getMap().getY(b.getPosition());
		
		if(y1==y2){
			return true;
		}else{
			return false;
		}
	}
	/********************Définition Cr(a,b,c,d)********************/
	public static boolean isCroiser(Ile a, Ile b, Ile c, Ile d){
		boolean propL = isLigne(a, b);
		boolean propC = isColonne(c, d);
		
		if(!propL || !propC){
			return false;
		}else{
			int ya = Map.getMap().getY(a.getPosition());
			int yc = Map.getMap().getY(c.getPosition());
			int yb = Map.getMap().getY(b.getPosition());
			
			int xc = Map.getMap().getX(c.getPosition());
			int xa = Map.getMap().getX(a.getPosition());
			int xd = Map.getMap().getX(d.getPosition());
			
			if(ya<yc && yc<yb && xc<xa && xa<xd){
				return true;
			}else{
				return false;
			}
			
		}
	}
	/********************Si 2 îles sont collées********************/
	public static boolean isNear(Ile a, Ile b){
		int xa = Map.getMap().getX(a.getPosition());
		int xb = Map.getMap().getX(b.getPosition());
		
		int ya = Map.getMap().getY(a.getPosition());
		int yb = Map.getMap().getY(b.getPosition());
		
		if(xa == xb + 1 || xa == xb-1 || ya == yb + 1 || ya == yb -1 )
			return true;
		else return false;
	}
}
