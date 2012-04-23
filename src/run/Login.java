package run;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import ludzie.Administrator;
import ludzie.Doctor;
import ludzie.Nurse;
import ludzie.Person;
import ludzie.Superciec;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import ciphers.Cipher;

import com.csvreader.CsvReader;

public class Login {

	private JFrame frmLogowanie;
	private JTextField txtWpiszLogin;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnPracownik;
	private JRadioButton rdbtnPacjent;
	public static Person logIn;
	
	private Cipher ciph = Cipher.ADFGVC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogowanie.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	class Check {
		public void c(String txt, char[] pass, String searchfile) {
			try {
				DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				Date data;
				CsvReader search = new CsvReader(searchfile);
				search.readHeaders();
				String ask = new String(pass);
				while (search.readRecord()) {
					if (txt.equals(search.get("LOGIN"))) {
						System.out.println(ciph.getCipher(ask));
						if (ciph.getCipher(ask).equals(search.get("PASSWORD"))) {
							if (search.get("TYPE").equals("Administrator")) {
								frmLogowanie.dispose();
								data = (Date)f.parse(search.get(5));
								Administrator login = new Administrator(
										search.get(0), search.get(3),
										search.get(4), data);
								logIn = login;
								System.out.println("admin");
								login.run();
							}
							else if(search.get("TYPE").equals("Lekarz")){
								frmLogowanie.dispose();
								data = (Date)f.parse(search.get(5));
								Doctor login = new Doctor(
										search.get(0), search.get(3),
										search.get(4), data);
								logIn = login;
								System.out.println("lekarz");
								login.run();
							}
							else if(search.get("TYPE").equals("Pielegniarka")){
								frmLogowanie.dispose();
								data = (Date)f.parse(search.get(5));
								Nurse login = new Nurse(
										search.get(0), search.get(3),
										search.get(4), data);
								logIn = login;
								System.out.println("pigula");
								login.run();
							}
							else if(search.get("TYPE").equals("Superciec")){
								frmLogowanie.dispose();
								data = (Date)f.parse(search.get(5));
								Superciec login = new Superciec(
										search.get(0), search.get(3),
										search.get(4), data);
								logIn = login;
								System.out.println("superciec");
								login.run();
							}
							else if(search.get("TYPE").equals("Lekarz")){
								frmLogowanie.dispose();
								data = (Date)f.parse(search.get(5));
								Doctor login = new Doctor(
										search.get(0), search.get(3),
										search.get(4), data);
								logIn = login;
								System.out.println("pacjent");
								login.run();
							}
						}
					}
				}
				search.close();
			} catch (FileNotFoundException e) {
				System.err.println(e);
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println(e);
				e.printStackTrace();
			} catch (ParseException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmLogowanie = new JFrame();
		frmLogowanie.setTitle("Logowanie");
		frmLogowanie.setBounds(600, 300, 400, 185);
		frmLogowanie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmLogowanie.getContentPane().setLayout(springLayout);

		txtWpiszLogin = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtWpiszLogin, 10,
				SpringLayout.NORTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtWpiszLogin, -39,
				SpringLayout.EAST, frmLogowanie.getContentPane());

		txtWpiszLogin.setHorizontalAlignment(SwingConstants.LEFT);
		frmLogowanie.getContentPane().add(txtWpiszLogin);
		txtWpiszLogin.setColumns(10);

		JButton btnNewButton = new JButton("Potwierdz");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 99,
				SpringLayout.NORTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 39,
				SpringLayout.WEST, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -10,
				SpringLayout.SOUTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 0,
				SpringLayout.EAST, txtWpiszLogin);

		frmLogowanie.getContentPane().add(btnNewButton);

		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.SOUTH, txtWpiszLogin, -5,
				SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 41,
				SpringLayout.NORTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 129,
				SpringLayout.WEST, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, -32,
				SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -39,
				SpringLayout.EAST, frmLogowanie.getContentPane());
		passwordField.setToolTipText("");
		frmLogowanie.getContentPane().add(passwordField);

		JLabel lblLogin = new JLabel("Login");
		springLayout.putConstraint(SpringLayout.WEST, txtWpiszLogin, 1,
				SpringLayout.EAST, lblLogin);
		springLayout.putConstraint(SpringLayout.NORTH, lblLogin, 10,
				SpringLayout.NORTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblLogin, 67,
				SpringLayout.WEST, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblLogin, 36,
				SpringLayout.NORTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblLogin, -252,
				SpringLayout.EAST, frmLogowanie.getContentPane());
		frmLogowanie.getContentPane().add(lblLogin);

		JLabel lblHaslo = new JLabel("Haslo");
		springLayout.putConstraint(SpringLayout.NORTH, lblHaslo, 10,
				SpringLayout.SOUTH, lblLogin);
		springLayout.putConstraint(SpringLayout.WEST, lblHaslo, 67,
				SpringLayout.WEST, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblHaslo, -35,
				SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, lblHaslo, -1,
				SpringLayout.WEST, passwordField);
		frmLogowanie.getContentPane().add(lblHaslo);

		final JRadioButton rdbtnPracownik = new JRadioButton("Pracownik");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnPracownik, 3,
				SpringLayout.SOUTH, passwordField);
		buttonGroup.add(rdbtnPracownik);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnPracownik, 0,
				SpringLayout.WEST, txtWpiszLogin);
		frmLogowanie.getContentPane().add(rdbtnPracownik);

		final JRadioButton rdbtnPacjent = new JRadioButton("Pacjent");
		buttonGroup.add(rdbtnPacjent);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnPacjent, 6,
				SpringLayout.EAST, rdbtnPracownik);
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnPacjent, -6,
				SpringLayout.NORTH, btnNewButton);
		frmLogowanie.getContentPane().add(rdbtnPacjent);
		frmLogowanie.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { frmLogowanie.getContentPane(), txtWpiszLogin,
						btnNewButton }));

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Check a = new Check();
				if (rdbtnPracownik.isSelected()) {
					a.c(txtWpiszLogin.getText(), passwordField.getPassword(),
							"data/workers.csv");
				} else if (rdbtnPacjent.isSelected()) {
					a.c(txtWpiszLogin.getText(), passwordField.getPassword(),
							"data/patients.csv");
				}

			}
		});
	}
}