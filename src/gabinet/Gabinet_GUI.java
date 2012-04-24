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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class Gabinet_GUI {

	public JFrame frame;
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
		String[] namesOfColumns = {"Typ Gabinetu", "Ilosc", "Sprzet w Gabinecie"};
		table = new JTable();
		//table.setModel(new DefaultTableModel(namesOfColumns,5));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Standardowy", 5, "Stół, Krzesło, Biurko, Stetoskop, Ciśnieniomierz" },
				{"Zabiegowy", 2, "Skalpel, Aparat EKG, Tomograf, Ciśnieniomierz" },
				{"Operacyjny", 2, "Skalpel, Stół, Tomograf, Młoteczek Lekarski" },
				{"", "" ,""},
			},
			new String[] {
				"Typ Gabinetu", "Ilość", "Sprzet w Gabinecie", 
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		final JTextField lblOczekiwanie = new JTextField("Stan OK");
		przegladanie.add(lblOczekiwanie, BorderLayout.SOUTH);
		
		final JPanel panel_przyciski = new JPanel();
		FlowLayout fl_panel_przyciski = (FlowLayout) panel_przyciski.getLayout();
		fl_panel_przyciski.setAlignment(FlowLayout.RIGHT);
		przegladanie.add(panel_przyciski, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		final JMenu mnGabinet = new JMenu("Zarządzanie Gabinetem");
		menuBar.add(mnGabinet);
		
		final JMenuItem mntmZapisz = new JMenuItem("Zapisz");
		mntmZapisz.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblOczekiwanie.setText("Zapisano!");
			}
		});
		mnGabinet.add(mntmZapisz);
		
		JMenuItem mntmWyjdz = new JMenuItem("Wyjdź");
		mntmWyjdz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JMenuItem mntmNewGabinet = new JMenuItem("Dodaj nowy Gabinet");
		mntmNewGabinet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblOczekiwanie.setText("Zapisano!");
			}
		});
		mnGabinet.add(mntmNewGabinet);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Usuń Gabinet");
		mnGabinet.add(mntmNewMenuItem_1);
		mnGabinet.add(mntmWyjdz);
		
		}
	}

	


