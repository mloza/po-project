package ludzie;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;



public class Doctor extends Person {

	private JFrame frame;
	private String[] columnNames = {"Imie", "Nazwisko"};
	List<Pacjent> ludzie = null;
	Object[][] data;
		
			private String login;
		Occupation type = Occupation.DOCTOR;
		private JTable table;
		
		public Doctor(String login, String name, String surname,
				Date birthDate) {
			super(name, surname, birthDate);
			
			this.setLogin(login);
			
			
			try {
				System.out.println("Wczytuję");
				ObjectInputStream op = new ObjectInputStream(
						new FileInputStream("data/ludzie.txt"));
				ludzie = (ArrayList<Pacjent>) op.readObject();
			} catch (Exception e) {
				System.out.println("Nie wczytałem");
				e.printStackTrace();
			} finally {
				ObjectOutputStream op;
				try {
					op = new ObjectOutputStream(
							new FileOutputStream("data/ludzie.txt"));
					op.writeObject(ludzie);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			data = new Object [ludzie.size()+1][2];
			
			for (int i=0; i<ludzie.size();i++) {
				data[i][0]=ludzie.get(i).getName();
				data[i][1]=ludzie.get(i).getSurname();
			}
			
		}

		protected String getLogin() {
			return login;
		}

		private void setLogin(String login) {
			this.login = login;
		}
		
		/**
		 * @wbp.parser.entryPoint
		 */

		public void run(){
			initialize();
	
		}

	private void initialize() {
		frame = new JFrame("Lekarz");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		table = new JTable(data,columnNames);
		frame.getContentPane().add(table);
		frame.pack();
		frame.setVisible(true);
		
		final JButton btnPoka = new JButton("Wyswietl");
		 frame.add(btnPoka);
		
		 
		 btnPoka.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = table.getSelectedRow();
					try {
						ludzie.get(index).showCard();
					} catch (PatientCardNotExistsException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});

	}
}
