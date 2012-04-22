package karta;

import helper.alert_ok;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

public class Karta_GUI extends JFrame {
	private JPanel contentPane;
	private JTable wizytyTable;
	private KartaPacjenta karta;
	
	/**
	 * Create the frame.
	 */
	public Karta_GUI(KartaPacjenta kartap) {
		this.karta = kartap;
		
		final SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		
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
		GridBagLayout gbl_dane = new GridBagLayout();
		gbl_dane.columnWidths = new int[]{201, 200, 0};
		gbl_dane.rowHeights = new int[]{16, 16, 16, 0};
		gbl_dane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_dane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		dane.setLayout(gbl_dane);
		
		JLabel lblNewLabel = new JLabel("Imie:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		dane.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblImiePacjenta = new JLabel(karta.pacjent.getName());
		GridBagConstraints gbc_lblImiePacjenta = new GridBagConstraints();
		gbc_lblImiePacjenta.fill = GridBagConstraints.BOTH;
		gbc_lblImiePacjenta.insets = new Insets(0, 0, 5, 0);
		gbc_lblImiePacjenta.gridx = 1;
		gbc_lblImiePacjenta.gridy = 0;
		dane.add(lblImiePacjenta, gbc_lblImiePacjenta);
		
		JLabel lblNewLabel_1 = new JLabel("Nazwisko:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		dane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(karta.pacjent.getSurname());
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		dane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Data urodzenia:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 2;
		dane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(date.format(karta.pacjent.getBirdthDate()));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 2;
		dane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JPanel wizyty = new JPanel();
		tabbedPane.addTab("Wizyty", null, wizyty, null);
		wizyty.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollWizyty = new JScrollPane();
		wizyty.add(scrollWizyty, BorderLayout.CENTER);
		
		AbstractTableModel tableModel = new AbstractTableModel() {
			private Object[][] data = {};
			private Object[] columnNames = {"Data wizyty"};
			
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
			
			{
				Object[][] asd = new Object[karta.listaWizyt.size()][this.getColumnCount()];
				int i =0;
				for(Wizyta it : karta.listaWizyt)
				{
					//asd[i++][0] = date.format(it.getDate());
					asd[i++][0] = it;
				}
				data = asd;
			}
			
		};
		//tableModel.getData();
		
		wizytyTable = new JTable();
		wizytyTable.setFillsViewportHeight(true);
		wizytyTable.setModel(tableModel);
		scrollWizyty.setViewportView(wizytyTable);
		
		JPanel wizyty_przyciski = new JPanel();
		wizyty.add(wizyty_przyciski, BorderLayout.SOUTH);
		
		JButton btnSzczegy = new JButton("Szczegóły");
		btnSzczegy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					Visit_details_GUI dialog = null;
					if(wizytyTable.getSelectedRow() == -1 || wizytyTable.getSelectedColumn() == -1)
					{
						throw new VisitNotSelectedException();
					}
					dialog = new Visit_details_GUI(wizytyTable.getValueAt(wizytyTable.getSelectedRow(), wizytyTable.getSelectedColumn()));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch(VisitNotSelectedException ex)
				{
					try {
						alert_ok dialog = new alert_ok("Wybierz wizytę w tabeli");
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				catch(Exception exc)
				{
					exc.printStackTrace();
				}
			}
		});
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

