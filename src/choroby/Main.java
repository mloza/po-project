package choroby;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;

public class Main {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
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
				"Nazwa", "Objawy"
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
		
		final JPanel panel_przyciski = new JPanel();
		FlowLayout fl_panel_przyciski = (FlowLayout) panel_przyciski.getLayout();
		fl_panel_przyciski.setAlignment(FlowLayout.RIGHT);
		przegladanie.add(panel_przyciski, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Edytuj");
		panel_przyciski.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Usu\u0144");
		panel_przyciski.add(btnNewButton_1);
		btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		final JMenu mnChoroby = new JMenu("Choroby");
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
	}

}
