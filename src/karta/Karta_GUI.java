package karta;

import helper.alert_ok;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;

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
		dane.setLayout(new MigLayout("", "[207px][207px]", "[16.00][16.00px][16.00px][16.00px][52px]"));
		
		JLabel lblNewLabel = new JLabel("Imie:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dane.add(lblNewLabel, "cell 0 0,grow");
		
		JLabel lblImiePacjenta = new JLabel(karta.pacjent.getName());
		dane.add(lblImiePacjenta, "cell 1 0,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Nazwisko:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		dane.add(lblNewLabel_1, "cell 0 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel(karta.pacjent.getSurname());
		dane.add(lblNewLabel_2, "cell 1 1");
		
		JLabel lblNewLabel_3 = new JLabel("Data urodzenia:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		dane.add(lblNewLabel_3, "cell 0 2,grow");
		
		JLabel lblNewLabel_4 = new JLabel(date.format(karta.pacjent.getBirdthDate()));
		dane.add(lblNewLabel_4, "cell 1 2,grow");
		
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

