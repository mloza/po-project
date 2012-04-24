package gabinet;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class Gabinet_GUI implements Serializable {

	public JFrame frame;
	private JTable table;
	private List<Gabinet> gabs = new ArrayList<Gabinet>();
	/**
	 * Launch the application.
	 */
	
	class MyTModel extends AbstractTableModel implements TableModelListener {
		private Object[][] data = {{"Standardowy", "Stół, Krzesło, Biurko, Stetoskop, Ciśnieniomierz" },
				{"Zabiegowy","Skalpel, Aparat EKG, Tomograf, Ciśnieniomierz" },
				{"Operacyjny", "Skalpel, Stół, Tomograf, Młoteczek Lekarski" }};
		private Object[] columnNames = {"Typ Gabinetu", "Typ oświetlenia", "Sprzet w Gabinecie"};
		
		
		@Override
		public int getRowCount() {
			return data.length;
		}
		
		public String getColumnName(int col) {
	        return (String) columnNames[col];
	    }
		
		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}
		
		public void Initiate() {

		}
		
		public void tableChanged(TableModelEvent e)
		{
			this.Initiate();
			table.repaint();
		}

		public void readData() {
			Object[][] asd = new Object[Gabinet.gabinety.size()][this.getColumnCount()];
			int i =0;
			for(Gabinet it : Gabinet.gabinety)
			{
				//asd[i++][0] = date.format(it.getDate());
				asd[i][0] = it.getTypGabinetu();
				asd[i][1] = it.getTypOswietlenia();
				asd[i++][2] = it.getSprzetString();

			}
			data = asd;
		}
	}
	
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		MyTModel m = new MyTModel();
		m.readData();
		table = new JTable(m);
		//table.setModel(new DefaultTableModel(namesOfColumns,5));
		scrollPane.setViewportView(table);
		
		final JPanel panel_przyciski = new JPanel();
		FlowLayout fl_panel_przyciski = (FlowLayout) panel_przyciski.getLayout();
		fl_panel_przyciski.setAlignment(FlowLayout.RIGHT);
		przegladanie.add(panel_przyciski, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		final JMenu mnGabinet = new JMenu("Zarządzanie Gabinetem");
		menuBar.add(mnGabinet);
		
		
		JMenuItem mntmWyjdz = new JMenuItem("Wyjdź");
		mntmWyjdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gabinet_GUI.this.frame.dispose();
			}
		});
		
		JMenuItem mntmNewGabinet = new JMenuItem("Dodaj nowy Gabinet");
		mntmNewGabinet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		mnGabinet.add(mntmNewGabinet);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Usuń Gabinet");
		mnGabinet.add(mntmNewMenuItem_1);
		mnGabinet.add(mntmWyjdz);
	}

	}


