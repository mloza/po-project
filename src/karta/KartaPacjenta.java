package karta;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import java.awt.EventQueue;

import ludzie.Pacjent;

public class KartaPacjenta implements Serializable {
	//ArrayList<Wpis> listaWpisow = new ArrayList<Wpis>();
	//ArrayList<Wizyta> listaWizyt = new ArrayList<Wizyta>();
	Pacjent pacjent;

	public KartaPacjenta() {

	}

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

}
