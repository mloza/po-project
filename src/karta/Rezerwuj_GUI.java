package karta;

import helper.alert_ok;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

public class Rezerwuj_GUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Rezerwuj_GUI(final KartaPacjenta karta) {
		setBounds(100, 100, 173, 99);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblData = new JLabel("Data:");
			GridBagConstraints gbc_lblData = new GridBagConstraints();
			gbc_lblData.insets = new Insets(0, 0, 0, 5);
			gbc_lblData.anchor = GridBagConstraints.EAST;
			gbc_lblData.gridx = 0;
			gbc_lblData.gridy = 0;
			contentPanel.add(lblData, gbc_lblData);
		}
		
			DateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy");
		    DateFormatter displayFormatter = new DateFormatter(displayFormat);
		    final DateFormat editFormat = new SimpleDateFormat("dd.MM.yyyy");
		    DateFormatter editFormatter = new DateFormatter(editFormat);
		    DefaultFormatterFactory factory = new DefaultFormatterFactory(displayFormatter,
		        displayFormatter, editFormatter);
			final JFormattedTextField formattedTextField = new JFormattedTextField(factory, new Date());
			GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
			gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_formattedTextField.gridx = 1;
			gbc_formattedTextField.gridy = 0;
			contentPanel.add(formattedTextField, gbc_formattedTextField);
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Calendar c = Calendar.getInstance();
							Calendar c2 = Calendar.getInstance();
							c.setTime((Date) formattedTextField.getValue());
							c2.setTime(new Date());
							if(c.before(c2))
							{
								try {
									alert_ok dialog = new alert_ok("Najwczesniej można zarezerwować wizytę jutro.");
									dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									dialog.setVisible(true);
								} catch (Exception e21) {
									e21.printStackTrace();
								}
							} else {
								Wizyta w = new Wizyta();
								w.setDateOfVisit((GregorianCalendar) c);
								karta.listaWizyt.add(w);
								Rezerwuj_GUI.this.dispose();
							}
						} catch (Exception e1) {
							try {
								alert_ok dialog = new alert_ok("Zły format daty");
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} catch (Exception e21) {
								e21.printStackTrace();
							}
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Rezerwuj_GUI.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		
	}

}
