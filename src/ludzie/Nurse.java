package ludzie;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class Nurse extends Person implements worker{

	private JFrame frame;
	private String[] columnNames = {"Imie", "Nazwisko"};
	List<Pacjent> ludzie2 = null;
	Object[][] data;
	//List<Person> ludzie;
		private String login;
		Occupation type = Occupation.DOCTOR;
		private JTextField textField;
		private JPasswordField passwordField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTable table;
		
		public Nurse(String login, String name, String surname,
				Date birthDate) {
			super(name, surname, birthDate);
			this.setLogin(login);
			
			try {
				System.out.println("Wczytuję");
				ObjectInputStream op = new ObjectInputStream(
						new FileInputStream("data/ludzie.txt"));
				ludzie2 = (ArrayList<Pacjent>) op.readObject();
			} catch (Exception e) {
				System.out.println("Nie wczytałem");
				e.printStackTrace();
			} finally {
				ObjectOutputStream op;
				try {
					op = new ObjectOutputStream(
							new FileOutputStream("data/ludzie.txt"));
					op.writeObject(ludzie2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			data = new Object [ludzie2.size()+1][2];
			
			for (int i=0; i<ludzie2.size();i++) {
				data[i][0]=ludzie2.get(i).getName();
				data[i][1]=ludzie2.get(i).getSurname();
			}
			
			initialize();
			
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
		frame = new JFrame("Pielegniarka");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 250, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 430, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblLogin = new JLabel("Login");
		sl_panel.putConstraint(SpringLayout.NORTH, lblLogin, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblLogin, 0, SpringLayout.WEST, panel);
		panel.add(lblLogin);
		
		JLabel lblHaslo = new JLabel("Haslo");
		sl_panel.putConstraint(SpringLayout.WEST, lblHaslo, 0, SpringLayout.WEST, lblLogin);
		panel.add(lblHaslo);
		
		JLabel lblImie = new JLabel("Imie");
		sl_panel.putConstraint(SpringLayout.NORTH, lblImie, 88, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblImie, 0, SpringLayout.WEST, lblLogin);
		panel.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		sl_panel.putConstraint(SpringLayout.WEST, lblNazwisko, 0, SpringLayout.WEST, lblLogin);
		sl_panel.putConstraint(SpringLayout.EAST, lblNazwisko, 61, SpringLayout.WEST, panel);
		panel.add(lblNazwisko);
		
		JLabel lblDataUrodzenia = new JLabel("Data urodzenia");
		sl_panel.putConstraint(SpringLayout.WEST, lblDataUrodzenia, 0, SpringLayout.WEST, lblLogin);
		panel.add(lblDataUrodzenia);
		
		textField = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, lblHaslo, 6, SpringLayout.SOUTH, textField);
		sl_panel.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblLogin);
		sl_panel.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, panel);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		sl_panel.putConstraint(SpringLayout.NORTH, passwordField, 6, SpringLayout.SOUTH, lblHaslo);
		sl_panel.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, lblLogin);
		sl_panel.putConstraint(SpringLayout.EAST, passwordField, 114, SpringLayout.WEST, panel);
		panel.add(passwordField);
		
		textField_1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, lblNazwisko, 6, SpringLayout.SOUTH, textField_1);
		sl_panel.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, lblImie);
		sl_panel.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, lblLogin);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, lblDataUrodzenia, 6, SpringLayout.SOUTH, textField_2);
		sl_panel.putConstraint(SpringLayout.NORTH, textField_2, 6, SpringLayout.SOUTH, lblNazwisko);
		sl_panel.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, lblLogin);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textField_3, 6, SpringLayout.SOUTH, lblDataUrodzenia);
		sl_panel.putConstraint(SpringLayout.WEST, textField_3, 0, SpringLayout.WEST, lblLogin);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnStworz = new JButton("Stworz");
		sl_panel.putConstraint(SpringLayout.SOUTH, btnStworz, 0, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnStworz, 0, SpringLayout.EAST, lblDataUrodzenia);
		panel.add(btnStworz);
		
		final JButton btnWczytaj = new JButton("Wczytaj");
		sl_panel.putConstraint(SpringLayout.NORTH, btnWczytaj, 0, SpringLayout.NORTH, btnStworz);
		sl_panel.putConstraint(SpringLayout.EAST, btnWczytaj, -119, SpringLayout.EAST, panel);
		panel.add(btnWczytaj);
		
		table = new JTable(data,columnNames);
		sl_panel.putConstraint(SpringLayout.NORTH, table, 0, SpringLayout.NORTH, textField);
		sl_panel.putConstraint(SpringLayout.WEST, table, 6, SpringLayout.EAST, textField);
		sl_panel.putConstraint(SpringLayout.SOUTH, table, 0, SpringLayout.SOUTH, textField_3);
		sl_panel.putConstraint(SpringLayout.EAST, table, 306, SpringLayout.EAST, textField);
		panel.add(table);
		
		btnStworz.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("klik");
				ArrayList<Pacjent> ludzie=null;
				try {
					System.out.println("Wczytuję");
					ObjectInputStream op = new ObjectInputStream(
							new FileInputStream("data/ludzie.txt"));
					ludzie = (ArrayList<Pacjent>) op.readObject();
				} catch (Exception e1) {
					System.out.println("Nie wczytałem");
					e1.printStackTrace();
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
				
				String pass = new String(passwordField.getPassword());
				DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				Date dat = null;
				try {
					dat = (Date) f.parse(textField_3.getText());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				String[] s = {textField.getText(), textField_1.getText(), textField_2.getText()};
				if(s[1].length() != 0 && s[2].length() != 0 && s[3].length() != 0 ){
				Pacjent pacjent = new Pacjent(textField.getText(),pass,textField_1.getText(), textField_2.getText(), dat);
				ludzie.add(pacjent);
				System.out.println("Tworzę");
				ObjectOutputStream op;
				try {
					op = new ObjectOutputStream(new FileOutputStream("data/ludzie.txt"));
					op.writeObject(ludzie);
					System.out.println("Zapisuje All");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				}
				else {
					System.out.println("Nie mozna zapisywac!");
				}
			}
		});
		
		
		btnWczytaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				try {
					ludzie2.get(index).showCard();
				} catch (PatientCardNotExistsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		frame.setVisible(true);
	}
}
