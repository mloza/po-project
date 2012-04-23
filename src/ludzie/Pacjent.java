package ludzie;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import karta.KartaPacjenta;
import tester.Test;

public class Pacjent extends Person implements Serializable {

	public KartaPacjenta karta;
	private String login;
	private String pass;

	public Pacjent(String login, String pass, String name, String surname,
			Date birdthDate) {
		super(name, surname, birdthDate);
		this.setLogin(login);
		this.setPass(pass);
	}

	public Pacjent(String name, String surname, Date birdthDate) {
		super(name, surname, birdthDate);
	}

	public void showCard() throws PatientCardNotExistsException {
		if (this.karta == null) {
			Object[] options = { "Tak", "Nie" };
			final JOptionPane optionPane = new JOptionPane(
					"Karta pacjenta nie istnieje, czy chcesz ją utworzyć?",
					JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,
					null, options, options[0]);

			final JDialog dialog = new JDialog(Test.frame, "Karta pacjenta",
					true);
			dialog.setContentPane(optionPane);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			optionPane.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent e) {
					String prop = e.getPropertyName();
					if (dialog.isVisible() && (e.getSource() == optionPane)
							&& (prop.equals(JOptionPane.VALUE_PROPERTY))) {
						dialog.setVisible(false);
					}
				}
			});
			dialog.pack();
			dialog.setVisible(true);

			String value = (String) optionPane.getValue();
			if (value.contentEquals("Tak")) {
				this.karta = new KartaPacjenta(this);
			} else if (value.contentEquals("Nie")) {
				throw new PatientCardNotExistsException(
						"Odmówiono utworzenia karty pacjenta");
			}
		}
		karta.show();
	}

	public void createCardWithVisit() {
		this.karta = new KartaPacjenta(Pacjent.this);
		this.karta.CreateVisits();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
