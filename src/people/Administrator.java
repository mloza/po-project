package people;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class Administrator extends Worker {

	private String login;
	Occupation type;

	private static JTextField txtLogin;
	private static JPasswordField txtPassword;
	private static JTextField txtSurname;
	private static JTextField txtName;
	private static JTextField txtDel;
	private static JPasswordField passConf;
	private JTextField txtLoginS;
	private JTextField txtSurnameS;
	private JTextField txtNameS;

	public Administrator(String login, String surname, String name,
			Occupation type) {
		super(surname, name, type);
		this.setLogin(login);
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
	public void run() {
		final JFrame frame = new JFrame("Administrator");
		final JPanel create = new JPanel();
		final JPanel delete = new JPanel();
		final JPanel search = new JPanel();
		String[] actionList = { "", "Utwórz nowe konto", "Usuń konto",
				"Wyświetl konto" };
		String[] typeList = { "", "Administrator", "Lekarz", "Pielęgniarka",
				"Supercieć" };

		final JComboBox comboBox = new JComboBox(actionList);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (comboBox.getSelectedIndex()) {
				case 0:
					create.setVisible(false);
					delete.setVisible(false);
					search.setVisible(false);
					break;

				case 1:
					create.setVisible(true);
					delete.setVisible(false);
					search.setVisible(false);
					break;

				case 2:
					create.setVisible(false);
					delete.setVisible(true);
					search.setVisible(false);
					break;

				case 3:
					create.setVisible(false);
					delete.setVisible(false);
					search.setVisible(true);
				}
			}
		});

		class ActionS {
			protected String run(String log, String sur, String nam, String typ) {
				String ans = "";
				String nowy = "";
				try {
					CsvReader search = new CsvReader("users.csv");
					search.readHeaders();
					while (search.readRecord()) {
						if (log.equals(search.get("LOGIN"))) {
							if(ans.contains(log))
								nowy = search.get("TYPE")+ " " +search.get("LOGIN")+ " " +search.get("NAME")+" "+search.get("SURNAME")+"\n";
							if(!ans.contains(nowy)){
							ans =  ans + nowy;
							}
						}
						if (typ.equals(search.get("TYPE"))) {
							nowy = search.get("TYPE")+ " " +search.get("LOGIN")+ " " +search.get("NAME")+" "+search.get("SURNAME")+"\n";
							if(!ans.contains(nowy)){
							ans =  ans + nowy;
							}
						}
						if (sur.equals(search.get("SURNAME"))) {
							nowy = search.get("TYPE")+ " " +search.get("LOGIN")+ " " +search.get("NAME")+" "+search.get("SURNAME")+"\n";
							if(!ans.contains(nowy)){
							ans =  ans + nowy;
							}
						}
						if (nam.equals(search.get("NAME"))) {
							nowy = search.get("TYPE")+ " " +search.get("LOGIN")+ " " +search.get("NAME")+" "+search.get("SURNAME")+"\n";
							if(!ans.contains(nowy)){
							ans =  ans + nowy;
							}
						}
						
					}

				} catch (IOException e) {
					e.getStackTrace();
				}
				return ans;
			}
		}

		class ActionD {
			protected void run(String logincheck, char[] pass) {
				try {
					CsvReader search = new CsvReader("users.csv");
					search.readHeaders();
					String haslo = new String(pass);
					String txt = login; // to jest login administratora
					while (search.readRecord()) {
						if (txt.equals(search.get("LOGIN"))) {
							if (haslo.equals(search.get("PASSWORD"))) {
								File in = new File("users.csv");
								File tmp = new File("tmp.csv");

								BufferedReader reader = new BufferedReader(
										new FileReader(in));
								BufferedWriter writer = new BufferedWriter(
										new FileWriter(tmp));

								String curr;
								String[] temp;
								while ((curr = reader.readLine()) != null) {
									temp = curr.split(",");
									if (!temp[0].equals(logincheck)) {
										writer.write(curr + "\n");
										writer.flush();
									}
								}
								reader.close();
								writer.close();
								in.delete();
								tmp.renameTo(in);
							}
						}
					}
				} catch (IOException e) {
					e.getStackTrace();
				}
			}
		}

		class ActionC {
			protected void run(String login, char[] pass, String typ,
					String nazwisko, String imie) {
				String of = "users.csv";
				if (!typ.equals("")) {
					try {
						CsvReader search = new CsvReader("users.csv");
						search.readHeaders();
						String haslo = new String(pass);
						boolean can = true;
						while (search.readRecord()) {
							if (login.equals(search.get("LOGIN"))) {
								can = false;
							}
						}
						if (can) {
							CsvWriter saveNew = new CsvWriter(new FileWriter(
									of, true), ',');
							saveNew.write(login);
							saveNew.write(haslo);
							saveNew.write(typ);
							saveNew.write(nazwisko);
							saveNew.write(imie);
							saveNew.endRecord();
							saveNew.close();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();

					}

				}
			}
		}

		/*
		 * ************************************************************************
		 */

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(600, 300, 300, 300);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 10,
				SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 10,
				SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -216,
				SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -10,
				SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		frame.getContentPane();
		frame.getContentPane().add(comboBox);

		JLayeredPane layeredPane = new JLayeredPane();
		springLayout.putConstraint(SpringLayout.NORTH, layeredPane, 6,
				SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, layeredPane, 0,
				SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, layeredPane, -16,
				SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, layeredPane, 0,
				SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(layeredPane);

		search.setBounds(0, 0, 280, 194);
		layeredPane.add(search);
		SpringLayout sl_search = new SpringLayout();
		search.setLayout(sl_search);

		JLabel lblLoginS = new JLabel("Login");
		lblLoginS.setFont(new Font("Dialog", Font.BOLD, 14));
		search.add(lblLoginS);

		txtLoginS = new JTextField();
		sl_search.putConstraint(SpringLayout.NORTH, txtLoginS, 0,
				SpringLayout.NORTH, search);
		sl_search.putConstraint(SpringLayout.WEST, txtLoginS, 76,
				SpringLayout.WEST, search);
		sl_search.putConstraint(SpringLayout.EAST, txtLoginS, 0,
				SpringLayout.EAST, search);
		sl_search.putConstraint(SpringLayout.NORTH, lblLoginS, 0,
				SpringLayout.NORTH, txtLoginS);
		sl_search.putConstraint(SpringLayout.EAST, lblLoginS, -6,
				SpringLayout.WEST, txtLoginS);
		search.add(txtLoginS);
		txtLoginS.setColumns(10);

		JLabel lblNazwiskoS = new JLabel("Nazwisko");
		sl_search.putConstraint(SpringLayout.NORTH, lblNazwiskoS, 6,
				SpringLayout.SOUTH, lblLoginS);
		lblNazwiskoS.setFont(new Font("Dialog", Font.BOLD, 14));
		sl_search.putConstraint(SpringLayout.WEST, lblNazwiskoS, 0,
				SpringLayout.WEST, search);
		search.add(lblNazwiskoS);

		txtSurnameS = new JTextField();
		sl_search.putConstraint(SpringLayout.NORTH, txtSurnameS, 4,
				SpringLayout.SOUTH, txtLoginS);
		sl_search.putConstraint(SpringLayout.WEST, txtSurnameS, 0,
				SpringLayout.WEST, txtLoginS);
		sl_search.putConstraint(SpringLayout.EAST, txtSurnameS, 0,
				SpringLayout.EAST, txtLoginS);
		search.add(txtSurnameS);
		txtSurnameS.setColumns(10);

		JLabel lblImieS = new JLabel("Imię");
		lblImieS.setFont(new Font("Dialog", Font.BOLD, 14));
		sl_search.putConstraint(SpringLayout.NORTH, lblImieS, 6,
				SpringLayout.SOUTH, lblNazwiskoS);
		sl_search.putConstraint(SpringLayout.EAST, lblImieS, 0,
				SpringLayout.EAST, lblLoginS);
		search.add(lblImieS);

		txtNameS = new JTextField();
		sl_search.putConstraint(SpringLayout.NORTH, txtNameS, 6,
				SpringLayout.SOUTH, txtSurnameS);
		sl_search.putConstraint(SpringLayout.WEST, txtNameS, 0,
				SpringLayout.WEST, txtLoginS);
		sl_search.putConstraint(SpringLayout.EAST, txtNameS, 0,
				SpringLayout.EAST, txtLoginS);
		search.add(txtNameS);
		txtNameS.setColumns(10);

		JButton btnWyszukaj = new JButton("Wyszukaj");
		sl_search.putConstraint(SpringLayout.NORTH, btnWyszukaj, 6,
				SpringLayout.SOUTH, txtNameS);
		sl_search.putConstraint(SpringLayout.EAST, btnWyszukaj, 0,
				SpringLayout.EAST, txtLoginS);
		search.add(btnWyszukaj);

		final JTextPane textPane = new JTextPane();
		sl_search.putConstraint(SpringLayout.NORTH, textPane, 6,
				SpringLayout.SOUTH, btnWyszukaj);
		sl_search.putConstraint(SpringLayout.WEST, textPane, 10,
				SpringLayout.WEST, search);
		sl_search.putConstraint(SpringLayout.SOUTH, textPane, -10,
				SpringLayout.SOUTH, search);
		sl_search.putConstraint(SpringLayout.EAST, textPane, -10,
				SpringLayout.EAST, txtLoginS);
		search.add(textPane);
		
		final JComboBox cbTypeS = new JComboBox(typeList);
		sl_search.putConstraint(SpringLayout.WEST, cbTypeS, -177, SpringLayout.WEST, btnWyszukaj);
		sl_search.putConstraint(SpringLayout.SOUTH, cbTypeS, -6, SpringLayout.NORTH, textPane);
		sl_search.putConstraint(SpringLayout.EAST, cbTypeS, -6, SpringLayout.WEST, btnWyszukaj);
		search.add(cbTypeS);

		delete.setBounds(0, 0, 280, 194);
		layeredPane.add(delete);
		SpringLayout sl_delete = new SpringLayout();
		delete.setLayout(sl_delete);

		JLabel lblLoginToDel = new JLabel("Login konta do usunięcia:");
		lblLoginToDel.setFont(new Font("Dialog", Font.BOLD, 14));
		sl_delete.putConstraint(SpringLayout.NORTH, lblLoginToDel, 10,
				SpringLayout.NORTH, delete);
		sl_delete.putConstraint(SpringLayout.WEST, lblLoginToDel, 10,
				SpringLayout.WEST, delete);
		delete.add(lblLoginToDel);

		txtDel = new JTextField();
		sl_delete.putConstraint(SpringLayout.NORTH, txtDel, 6,
				SpringLayout.SOUTH, lblLoginToDel);
		sl_delete.putConstraint(SpringLayout.WEST, txtDel, 10,
				SpringLayout.WEST, delete);
		sl_delete.putConstraint(SpringLayout.SOUTH, txtDel, 31,
				SpringLayout.SOUTH, lblLoginToDel);
		sl_delete.putConstraint(SpringLayout.EAST, txtDel, 270,
				SpringLayout.WEST, delete);
		delete.add(txtDel);
		txtDel.setColumns(10);

		JLabel lblHasoAdministratora = new JLabel("Hasło administratora:");
		sl_delete.putConstraint(SpringLayout.NORTH, lblHasoAdministratora, 6,
				SpringLayout.SOUTH, txtDel);
		sl_delete.putConstraint(SpringLayout.WEST, lblHasoAdministratora, 0,
				SpringLayout.WEST, lblLoginToDel);
		lblHasoAdministratora.setFont(new Font("Dialog", Font.BOLD, 14));
		delete.add(lblHasoAdministratora);

		passConf = new JPasswordField();
		sl_delete.putConstraint(SpringLayout.NORTH, passConf, 6,
				SpringLayout.SOUTH, lblHasoAdministratora);
		sl_delete.putConstraint(SpringLayout.WEST, passConf, 10,
				SpringLayout.WEST, delete);
		sl_delete.putConstraint(SpringLayout.SOUTH, passConf, 31,
				SpringLayout.SOUTH, lblHasoAdministratora);
		sl_delete.putConstraint(SpringLayout.EAST, passConf, 270,
				SpringLayout.WEST, delete);
		delete.add(passConf);

		JButton btnUsu = new JButton("Usuń");
		sl_delete.putConstraint(SpringLayout.SOUTH, btnUsu, -10,
				SpringLayout.SOUTH, delete);
		sl_delete.putConstraint(SpringLayout.EAST, btnUsu, 0,
				SpringLayout.EAST, lblHasoAdministratora);
		delete.add(btnUsu);

		final JCheckBox chckbxUsuWszystkieDane = new JCheckBox(
				"Usuń wszystkie dane użytkownika");
		sl_delete.putConstraint(SpringLayout.NORTH, chckbxUsuWszystkieDane, 6,
				SpringLayout.SOUTH, passConf);
		sl_delete.putConstraint(SpringLayout.WEST, chckbxUsuWszystkieDane, 0,
				SpringLayout.WEST, lblLoginToDel);
		delete.add(chckbxUsuWszystkieDane);
		delete.setVisible(false);

		create.setBounds(0, 0, 280, 194);
		layeredPane.add(create);
		SpringLayout sl_create = new SpringLayout();
		create.setLayout(sl_create);

		JButton btnPotwierd = new JButton("Stwórz");

		sl_create.putConstraint(SpringLayout.WEST, btnPotwierd, 100,
				SpringLayout.WEST, create);
		sl_create.putConstraint(SpringLayout.SOUTH, btnPotwierd, 0,
				SpringLayout.SOUTH, create);
		btnPotwierd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		create.add(btnPotwierd);

		JLabel lblLogin = new JLabel("Login");
		sl_create.putConstraint(SpringLayout.NORTH, lblLogin, 13,
				SpringLayout.NORTH, create);
		sl_create.putConstraint(SpringLayout.WEST, lblLogin, 10,
				SpringLayout.WEST, create);
		lblLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		create.add(lblLogin);

		txtLogin = new JTextField();
		sl_create.putConstraint(SpringLayout.NORTH, txtLogin, 10,
				SpringLayout.NORTH, create);
		sl_create.putConstraint(SpringLayout.WEST, txtLogin, 16,
				SpringLayout.EAST, lblLogin);
		sl_create.putConstraint(SpringLayout.SOUTH, txtLogin, -159,
				SpringLayout.SOUTH, create);
		sl_create.putConstraint(SpringLayout.EAST, txtLogin, -10,
				SpringLayout.EAST, create);
		create.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblHaso = new JLabel("Hasło");
		sl_create.putConstraint(SpringLayout.NORTH, lblHaso, 14,
				SpringLayout.SOUTH, lblLogin);
		sl_create.putConstraint(SpringLayout.WEST, lblHaso, 0,
				SpringLayout.WEST, lblLogin);
		lblHaso.setFont(new Font("Dialog", Font.BOLD, 14));
		create.add(lblHaso);

		txtPassword = new JPasswordField();
		sl_create.putConstraint(SpringLayout.NORTH, txtPassword, 6,
				SpringLayout.SOUTH, txtLogin);
		sl_create.putConstraint(SpringLayout.WEST, txtPassword, 0,
				SpringLayout.WEST, txtLogin);
		sl_create.putConstraint(SpringLayout.SOUTH, txtPassword, -128,
				SpringLayout.SOUTH, create);
		sl_create.putConstraint(SpringLayout.EAST, txtPassword, 0,
				SpringLayout.EAST, txtLogin);
		create.add(txtPassword);

		JLabel lblTyp = new JLabel("Typ");
		sl_create.putConstraint(SpringLayout.NORTH, lblTyp, 14,
				SpringLayout.SOUTH, lblHaso);
		sl_create.putConstraint(SpringLayout.EAST, lblTyp, 0,
				SpringLayout.EAST, lblLogin);
		lblTyp.setFont(new Font("Dialog", Font.BOLD, 14));
		create.add(lblTyp);

		final JComboBox cbType = new JComboBox(typeList);
		sl_create.putConstraint(SpringLayout.NORTH, cbType, 6,
				SpringLayout.SOUTH, txtPassword);
		sl_create.putConstraint(SpringLayout.WEST, cbType, 16,
				SpringLayout.EAST, lblTyp);
		sl_create.putConstraint(SpringLayout.EAST, cbType, 0,
				SpringLayout.EAST, txtLogin);
		create.add(cbType);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		sl_create.putConstraint(SpringLayout.NORTH, lblNazwisko, 13,
				SpringLayout.SOUTH, lblTyp);
		sl_create.putConstraint(SpringLayout.WEST, lblNazwisko, 0,
				SpringLayout.WEST, create);
		lblNazwisko.setFont(new Font("Dialog", Font.BOLD, 12));
		create.add(lblNazwisko);

		txtSurname = new JTextField();
		sl_create.putConstraint(SpringLayout.NORTH, txtSurname, 6,
				SpringLayout.SOUTH, cbType);
		sl_create.putConstraint(SpringLayout.WEST, txtSurname, 0,
				SpringLayout.WEST, txtLogin);
		sl_create.putConstraint(SpringLayout.EAST, txtSurname, 0,
				SpringLayout.EAST, txtLogin);
		create.add(txtSurname);
		txtSurname.setColumns(10);

		JLabel lblImi = new JLabel("Imię");
		sl_create.putConstraint(SpringLayout.NORTH, lblImi, 16,
				SpringLayout.SOUTH, lblNazwisko);
		sl_create.putConstraint(SpringLayout.EAST, lblImi, 0,
				SpringLayout.EAST, lblLogin);
		lblImi.setFont(new Font("Dialog", Font.BOLD, 14));
		create.add(lblImi);

		txtName = new JTextField();
		sl_create.putConstraint(SpringLayout.NORTH, txtName, 133,
				SpringLayout.NORTH, create);
		sl_create.putConstraint(SpringLayout.SOUTH, txtSurname, -6,
				SpringLayout.NORTH, txtName);
		sl_create.putConstraint(SpringLayout.SOUTH, txtName, -36,
				SpringLayout.SOUTH, create);
		sl_create.putConstraint(SpringLayout.WEST, txtName, 0,
				SpringLayout.WEST, txtLogin);
		sl_create.putConstraint(SpringLayout.EAST, txtName, 0,
				SpringLayout.EAST, txtLogin);
		create.add(txtName);
		txtName.setColumns(10);

		layeredPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { search, delete, lblLoginToDel, txtDel,
						lblHasoAdministratora, passConf, btnUsu,
						chckbxUsuWszystkieDane, lblLoginS, txtLoginS,
						lblNazwiskoS, txtSurnameS, lblImieS, txtNameS,
						btnWyszukaj, create, btnPotwierd, lblLogin, txtLogin,
						lblHaso, txtPassword, lblTyp, cbType, lblNazwisko,
						txtSurname, lblImi, txtName }));
		search.setVisible(false);

		/* TUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU */
		btnPotwierd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ActionC create = new ActionC();
				create.run(txtLogin.getText(), txtPassword.getPassword(),
						(String) cbType.getSelectedItem(),
						txtSurname.getText(), txtName.getText());
			}
		});
		btnUsu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (chckbxUsuWszystkieDane.isSelected()
						&& (!txtDel.getText().equals(login))) {
					ActionD del = new ActionD();
					del.run(txtDel.getText(), passConf.getPassword());
				}
			}
		});
		btnWyszukaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ActionS se = new ActionS();
				textPane.setText(se.run(txtLoginS.getText(),txtSurnameS.getText(), txtNameS.getText(),
						(String) cbTypeS.getSelectedItem()));
			}
		});
		create.setVisible(false);
		frame.setVisible(true);
	}
}