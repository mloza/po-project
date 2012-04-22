package karta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Visit_details_GUI extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			Visit_details_GUI dialog = new Visit_details_GUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 * @param object 
	 */
	public Visit_details_GUI(Object object) {
		Wizyta wizyta = (Wizyta) object;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{96, 310, 0};
		gbl_contentPanel.rowHeights = new int[]{14, 14, 74, 14, 73, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Data wizyty:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel label = new JLabel(wizyta.toString());
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHWEST;
			gbc_label.insets = new Insets(0, 0, 5, 0);
			gbc_label.gridx = 1;
			gbc_label.gridy = 0;
			contentPanel.add(label, gbc_label);
		}
		{
			JLabel lblWykonaneBadania = new JLabel("Wykonane badania:");
			GridBagConstraints gbc_lblWykonaneBadania = new GridBagConstraints();
			gbc_lblWykonaneBadania.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblWykonaneBadania.insets = new Insets(0, 0, 5, 5);
			gbc_lblWykonaneBadania.gridx = 0;
			gbc_lblWykonaneBadania.gridy = 1;
			contentPanel.add(lblWykonaneBadania, gbc_lblWykonaneBadania);
		}
		{
			JLabel label = new JLabel("<dynamic>");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHWEST;
			gbc_label.insets = new Insets(0, 0, 5, 0);
			gbc_label.gridx = 1;
			gbc_label.gridy = 1;
			contentPanel.add(label, gbc_label);
		}
		{
			JLabel lblOpis = new JLabel("Diagnoza:");
			GridBagConstraints gbc_lblOpis = new GridBagConstraints();
			gbc_lblOpis.anchor = GridBagConstraints.WEST;
			gbc_lblOpis.insets = new Insets(0, 0, 5, 5);
			gbc_lblOpis.gridx = 0;
			gbc_lblOpis.gridy = 2;
			contentPanel.add(lblOpis, gbc_lblOpis);
		}
		{
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText("<dynamic>");
			GridBagConstraints gbc_textPane = new GridBagConstraints();
			gbc_textPane.fill = GridBagConstraints.BOTH;
			gbc_textPane.insets = new Insets(0, 0, 5, 0);
			gbc_textPane.gridx = 1;
			gbc_textPane.gridy = 2;
			contentPanel.add(textPane, gbc_textPane);
		}
		{
			JLabel lblPrzepisaneLeki = new JLabel("Przepisane leki:");
			GridBagConstraints gbc_lblPrzepisaneLeki = new GridBagConstraints();
			gbc_lblPrzepisaneLeki.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblPrzepisaneLeki.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrzepisaneLeki.gridx = 0;
			gbc_lblPrzepisaneLeki.gridy = 3;
			contentPanel.add(lblPrzepisaneLeki, gbc_lblPrzepisaneLeki);
		}
		{
			JLabel label = new JLabel("<dynamic drugs>");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTHWEST;
			gbc_label.insets = new Insets(0, 0, 5, 0);
			gbc_label.gridx = 1;
			gbc_label.gridy = 3;
			contentPanel.add(label, gbc_label);
		}
		{
			JLabel lblLeki = new JLabel("Leczenie:");
			GridBagConstraints gbc_lblLeki = new GridBagConstraints();
			gbc_lblLeki.anchor = GridBagConstraints.WEST;
			gbc_lblLeki.insets = new Insets(0, 0, 0, 5);
			gbc_lblLeki.gridx = 0;
			gbc_lblLeki.gridy = 4;
			contentPanel.add(lblLeki, gbc_lblLeki);
		}
		{
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText("<dynamic leczenie>");
			GridBagConstraints gbc_textPane = new GridBagConstraints();
			gbc_textPane.fill = GridBagConstraints.BOTH;
			gbc_textPane.gridx = 1;
			gbc_textPane.gridy = 4;
			contentPanel.add(textPane, gbc_textPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Visit_details_GUI.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
