package karta;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.awt.EventQueue;

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
		asd.set(2012, 4, 29);
		w1.setDateOfVisit(asd);
		listaWizyt.add(w1);
		w1 = new Wizyta();
		asd = (GregorianCalendar) Calendar.getInstance();
		asd.set(2012, 5, 7);
		w1.setDateOfVisit(asd);
		listaWizyt.add(w1);
	}

}
