package karta;
import java.io.Serializable;
import java.util.GregorianCalendar;
import leczenie.*;
import java.util.ArrayList;

public class Wizyta implements Serializable {
	GregorianCalendar dateOfVisit;
	Diagnoza jakaChoroba;
	ArrayList<Badanie> jakieBadania = new ArrayList<Badanie>();
}
