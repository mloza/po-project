package run;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import people.Administrator;
import people.Occupation;

import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class Main {

	private JFrame frmLogowanie;
	private JTextField txtWpiszLogin;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	class Check {
		public void c(String txt, char[] pass) {
			try {
				CsvReader search = new CsvReader("users.csv");
				search.readHeaders();
				String ask = new String(pass);
				while (search.readRecord()) {
					if (txt.equals(search.get("LOGIN"))) {
						if (ask.equals(search.get("PASSWORD"))) {
							if (search.get("TYPE").equals("Administrator")) {
								frmLogowanie.dispose();
								Administrator login = new Administrator(
										search.get(0), search.get(2),
										search.get(3), Occupation.ADMINISTRATOR);
								login.run();
								System.out.println("admin");
							}

							else if (search.get("TYPE").equals("Lekarz")) {
								System.out.println("lekarz");
							}

							else if (search.get("TYPE").equals("Pielęgniarka")) {
								System.out.println("pielegniarka");
							}

							else if (search.get("TYPE").equals("Supercieć")) {
								System.out.println("superciec");
							}

						}
					}
				}
				search.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
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
		frmLogowanie.setBounds(600, 300, 400, 149);
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
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 39,
				SpringLayout.WEST, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 0,
				SpringLayout.SOUTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -39,
				SpringLayout.EAST, frmLogowanie.getContentPane());
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Check a = new Check();
				a.c(txtWpiszLogin.getText(), passwordField.getPassword());
			}
		});

		frmLogowanie.getContentPane().add(btnNewButton);

		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.SOUTH, txtWpiszLogin, -5,
				SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 41,
				SpringLayout.NORTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, -32,
				SpringLayout.SOUTH, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -39,
				SpringLayout.EAST, frmLogowanie.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 6,
				SpringLayout.SOUTH, passwordField);
		passwordField.setToolTipText("");
		frmLogowanie.getContentPane().add(passwordField);

		JLabel label = new JLabel("");
		springLayout.putConstraint(SpringLayout.NORTH, label, 10,
				SpringLayout.NORTH, frmLogowanie.getContentPane());
		frmLogowanie.getContentPane().add(label);

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
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 1,
				SpringLayout.EAST, lblHaslo);
		springLayout.putConstraint(SpringLayout.NORTH, lblHaslo, 5,
				SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, lblHaslo, 0,
				SpringLayout.WEST, lblLogin);
		springLayout.putConstraint(SpringLayout.SOUTH, lblHaslo, -12,
				SpringLayout.NORTH, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, lblHaslo, 0,
				SpringLayout.EAST, lblLogin);
		frmLogowanie.getContentPane().add(lblHaslo);
		frmLogowanie.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { frmLogowanie.getContentPane(), txtWpiszLogin,
						btnNewButton }));
	}
}
