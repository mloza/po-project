package karta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import choroby.Objaw;

public class EditVisit_GUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

	class listModel extends AbstractListModel {
		Object[] values;
		List<Objaw> objawy = Objaw.getAll();
		Wizyta visit;
		JList list;
		
		public int getSize() {
			return values.length;
		}
		public Object getElementAt(int index) {
			return values[index];
		}
		public void initialize(Wizyta visit, JList list) {
			this.visit = visit;
			
			List<String> o = new ArrayList<String>();
			for(Objaw i: Objaw.getAll())
			{
				o.add(i.getNazwa());
			}
			values = o.toArray();
			this.list = list;			
		}
		
		public int[] gsv() {
			int[] l = new int[visit.objawy.size()];
			int i=0;
			int f = -1;
			for(Objaw in: visit.objawy)
			{
				//System.out.println(objawy.indexOf(i));
				
				if((f = objawy.indexOf(in)) != -1)
				{
					System.out.println(f);
					l[i++] = f;
				}
				
			}
			return l;
			
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public EditVisit_GUI(final Wizyta visit) {
		setBounds(100, 100, 450, 554);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			DateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy");
		    DateFormatter displayFormatter = new DateFormatter(displayFormat);
		    final DateFormat editFormat = new SimpleDateFormat("dd.MM.yyyy");
		    DateFormatter editFormatter = new DateFormatter(editFormat);
		    DefaultFormatterFactory factory = new DefaultFormatterFactory(displayFormatter,
		        displayFormatter, editFormatter);
			GridBagLayout gbl_contentPanel = new GridBagLayout();
			gbl_contentPanel.columnWidths = new int[]{92, 212, 0};
			gbl_contentPanel.rowHeights = new int[]{30, 130, 0};
			gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			contentPanel.setLayout(gbl_contentPanel);
			{
				JLabel lblDataWizyty = new JLabel("Data wizyty:");
				GridBagConstraints gbc_lblDataWizyty = new GridBagConstraints();
				gbc_lblDataWizyty.anchor = GridBagConstraints.EAST;
				gbc_lblDataWizyty.fill = GridBagConstraints.VERTICAL;
				gbc_lblDataWizyty.insets = new Insets(0, 0, 5, 5);
				gbc_lblDataWizyty.gridx = 0;
				gbc_lblDataWizyty.gridy = 0;
				contentPanel.add(lblDataWizyty, gbc_lblDataWizyty);
			}
			final JFormattedTextField formattedTextField = new JFormattedTextField(factory, visit.getDateOfVisit().getTime());
			GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
			gbc_formattedTextField.fill = GridBagConstraints.BOTH;
			gbc_formattedTextField.insets = new Insets(0, 0, 5, 0);
			gbc_formattedTextField.gridx = 1;
			gbc_formattedTextField.gridy = 0;
			contentPanel.add(formattedTextField, gbc_formattedTextField);
			{
				JLabel lblObjawy = new JLabel("Objawy");
				GridBagConstraints gbc_lblObjawy = new GridBagConstraints();
				gbc_lblObjawy.anchor = GridBagConstraints.EAST;
				gbc_lblObjawy.fill = GridBagConstraints.VERTICAL;
				gbc_lblObjawy.insets = new Insets(0, 0, 0, 5);
				gbc_lblObjawy.gridx = 0;
				gbc_lblObjawy.gridy = 1;
				contentPanel.add(lblObjawy, gbc_lblObjawy);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 1;
				
				listModel model = new listModel();				
				contentPanel.add(scrollPane, gbc_scrollPane);
				{
					JList list = new JList();
					model.initialize(visit, list);
					list.setModel(model);
					list.setSelectedIndices(model.gsv());
					scrollPane.setViewportView(list);
				}
			}

			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Calendar c = Calendar.getInstance();
						c.setTime((Date) formattedTextField.getValue());
						visit.setDateOfVisit((GregorianCalendar) c);
						
						EditVisit_GUI.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EditVisit_GUI.this.dispose();
					}
				});
				buttonPane.add(btnCancel);
			}
		}
	}

}
