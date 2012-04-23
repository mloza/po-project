package gabinet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.table.DefaultTableModel;

public class GabinetGUI2 {

	private JFrame frame;
	private JMenu mnOpcje;
	private JMenuItem mntmZapisz;
	private JMenuItem mntmWyjd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GabinetGUI2 window = new GabinetGUI2();
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
	public GabinetGUI2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 730, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnOpcje = new JMenu("Opcje");
		menuBar.add(mnOpcje);
		
		mntmZapisz = new JMenuItem("Zapisz");
		mnOpcje.add(mntmZapisz);
		
		mntmWyjd = new JMenuItem("Wyjdź");
		mnOpcje.add(mntmWyjd);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

}
