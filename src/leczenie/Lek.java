package leczenie;

import java.util.ArrayList;
import java.util.List;

public class Lek {
	protected String nazwa;
	protected String cena;
	protected String opis;
	
	static List<Lek> dostepneleki = new ArrayList<Lek>();
	
	static Lek find(String lek) throws LekNotFoundException
	{
		
		return null;
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Lek && ((Lek) o).nazwa == this.nazwa) return true;
		else return false;
	}
}
