package karta;

import java.awt.EventQueue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ludzie.Pacjent;

public class KartaPacjenta implements Serializable {
	//ArrayList<Wpis> listaWpisow = new ArrayList<Wpis>();
	ArrayList<Wizyta> listaWizyt = new ArrayList<Wizyta>();
	Pacjent pacjent;

	public KartaPacjenta(Pacjent pacjent) {
		this.pacjent = pacjent;
	}

	public void printWpis(int i) {
	}

	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Karta_GUI frame = new Karta_GUI(KartaPacjenta.this);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void CreateVisits() {
		Wizyta w1 = new Wizyta();
		GregorianCalendar asd = (GregorianCalendar) Calendar.getInstance();
		asd.set(2012, 4, 15);
		w1.setDateOfVisit(asd);
		w1.setOdbyta(true);
		w1.setPotwierdzona(true);
		listaWizyt.add(w1);
		
		w1 = new Wizyta();
		asd = (GregorianCalendar) Calendar.getInstance();
		asd.set(2012, 4, 20);
		w1.setDateOfVisit(asd);
		w1.setOdbyta(true);
		w1.setPotwierdzona(true);
		w1.Init();
		listaWizyt.add(w1);
		
		w1 = new Wizyta();
		asd = (GregorianCalendar) Calendar.getInstance();
		asd.set(2012, 5, 7);
		w1.setDateOfVisit(asd);
		//w1.setOdbyta(true);
		//w1.setPotwierdzona(true);
		w1.Init();
		listaWizyt.add(w1);
	}

}
