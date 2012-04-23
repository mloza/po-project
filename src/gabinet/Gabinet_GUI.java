package gabinet;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gabinet_GUI {

	private JFrame frame;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gabinet_GUI window = new Gabinet_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gabinet_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 769, 568);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel zawartosc = new JPanel();
		frame.getContentPane().add(zawartosc, BorderLayout.CENTER);
		zawartosc.setLayout(new CardLayout(0, 0));
		
		final JPanel przegladanie = new JPanel();
		zawartosc.add(przegladanie, "name_113813651607297");
		przegladanie.setBorder(new EmptyBorder(5, 5, 5, 5));
		przegladanie.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		przegladanie.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Grypa", "Gor\u0105czka, Odwodnienie"},
				{"Kamica", "Odwodnienie, Gor\u0105czka"},
			},
			new String[] {
				"Typ Gabinetu", "Sprzet w Gabinecie"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		final JMenu mnChoroby = new JMenu("Zarządzaj Gabinetami");
		menuBar.add(mnChoroby);
		
		final JMenuItem mntmPrzegldaj = new JMenuItem("Przegl\u0105daj");
		mntmPrzegldaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		mnChoroby.add(mntmPrzegldaj);
		
		JMenuItem mntmDodajNow = new JMenuItem("Dodaj now\u0105");
		mntmDodajNow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		mnChoroby.add(mntmDodajNow);
		
		JMenuItem mntmEdytuj = new JMenuItem("Edytuj");
		mnChoroby.add(mntmEdytuj);
		
		JMenuItem mntmUsu = new JMenuItem("Usuń");
		mnChoroby.add(mntmUsu);
	}
	}


