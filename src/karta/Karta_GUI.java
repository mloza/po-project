package karta;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Karta_GUI extends JFrame {

	private JPanel contentPane;
	private JTable wizytyTable;
	private KartaPacjenta karta;
	
	/**
	 * Create the frame.
	 */
	public Karta_GUI(KartaPacjenta karta) {
		this.karta = karta;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "name_27256002870603");
		
		JPanel dane = new JPanel();
		tabbedPane.addTab("Dane pacjenta", null, dane, null);
		dane.setLayout(new GridLayout(4, 2, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Imie:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dane.add(lblNewLabel);
		
		JLabel lblImiePacjenta = new JLabel(karta.pacjent.getName());
		dane.add(lblImiePacjenta);
		
		JLabel lblNewLabel_1 = new JLabel("Nazwisko:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		dane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(karta.pacjent.getSurname());
		dane.add(lblNewLabel_2);
		
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		JLabel lblNewLabel_3 = new JLabel("Data urodzenia:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		dane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(date.format(karta.pacjent.getBirdthDate()));
		dane.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		dane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		dane.add(panel_2);
		
		JPanel wizyty = new JPanel();
		tabbedPane.addTab("Wizyty", null, wizyty, null);
		wizyty.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollWizyty = new JScrollPane();
		wizyty.add(scrollWizyty, BorderLayout.CENTER);
		
		wizytyTable = new JTable();
		wizytyTable.setFillsViewportHeight(true);
		wizytyTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"2012-20-02"},
			},
			new String[] {
				"Data Wizyty"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollWizyty.setViewportView(wizytyTable);
		
		JPanel wizyty_przyciski = new JPanel();
		wizyty.add(wizyty_przyciski, BorderLayout.SOUTH);
		
		JButton btnSzczegy = new JButton("Szczegóły");
		wizyty_przyciski.add(btnSzczegy);
		
		JButton btnDodaj = new JButton("Dodaj");
		wizyty_przyciski.add(btnDodaj);
		
		JButton btnEdytuj = new JButton("Edytuj");
		wizyty_przyciski.add(btnEdytuj);
		
		JButton btnUsu = new JButton("Usuń");
		wizyty_przyciski.add(btnUsu);
		
		JPanel choroby = new JPanel();
		tabbedPane.addTab("Przebyte choroby", null, choroby, null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "name_27186532136654");
		panel.setLayout(new BorderLayout(0, 0));
	}

}
