package tester;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import ludzie.Pacjent;
import ludzie.PatientCardNotExistsException;
import ludzie.Person;

public class Test {

	private List<Person> ludzie;
	public static JFrame frame;
	public static Test self;

	public void createPeoples() {
		ludzie = new ArrayList<Person>();
		Calendar cal = Calendar.getInstance();
		cal.set(1990, 1, 8);
		Pacjent pacjent = new Pacjent("p2","p2","Jan", "Kowalski", cal.getTime());
		ludzie.add(pacjent);
		cal.set(1995, 1, 20);
		
		// Jego strata że wszedł właśnie na gg
		pacjent = new Pacjent("puk","stuk","Piotr", "Betlej", cal.getTime());
		
		pacjent.createCardWithVisit();
		ludzie.add(pacjent);
		System.out.println("Tworzę");
	}

	public Test() {
		initialize();
	}

	public void run() {
		Person pacjent = ludzie.get(1);
		try {
			((Pacjent) pacjent).showCard();
		} catch (PatientCardNotExistsException e) {
			System.out.println(e.getMessage());
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Logowanie");
		frame.setBounds(600, 300, 400, 149);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Test.self.saveAll();
				System.exit(0);
			}
		});
	}

	public void saveAll() {
		ObjectOutputStream op;
		try {
			op = new ObjectOutputStream(new FileOutputStream("data/ludzie.txt"));
			op.writeObject(this.ludzie);
			System.out.println("Zapisuje All");
			Person pacjent = ludzie.get(1);
			System.out.println(((Pacjent) pacjent).karta);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				Test window = new Test();
				self = window;
				try {
					System.out.println("Wczytuję");
					ObjectInputStream op = new ObjectInputStream(
							new FileInputStream("data/ludzie.txt"));
					window.ludzie = (ArrayList<Person>) op.readObject();
				} catch (Exception e) {
					System.out.println("Nie wczytałem");
					e.printStackTrace();
					window.createPeoples();
				} finally {
					ObjectOutputStream op;
					try {
						op = new ObjectOutputStream(new FileOutputStream(
								"data/ludzie.txt"));
						op.writeObject(window.ludzie);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

				try {
					Test.frame.setVisible(true);
					window.run();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
				}
			}
		});
	}

}
