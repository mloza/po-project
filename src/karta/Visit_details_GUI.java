package karta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;

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
		contentPanel.setLayout(new MigLayout("", "[46px][grow]", "[14px][][grow][][grow]"));
		{
			JLabel lblNewLabel = new JLabel("Data wizyty:");
			contentPanel.add(lblNewLabel, "cell 0 0,alignx left,aligny top");
		}
		{
			JLabel label = new JLabel(wizyta.toString());
			contentPanel.add(label, "cell 1 0");
		}
		{
			JLabel lblWykonaneBadania = new JLabel("Wykonane badania:");
			contentPanel.add(lblWykonaneBadania, "cell 0 1");
		}
		{
			JLabel label = new JLabel("<dynamic>");
			contentPanel.add(label, "cell 1 1");
		}
		{
			JLabel lblOpis = new JLabel("Diagnoza:");
			contentPanel.add(lblOpis, "cell 0 2");
		}
		{
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText("<dynamic>");
			contentPanel.add(textPane, "cell 1 2,grow");
		}
		{
			JLabel lblPrzepisaneLeki = new JLabel("Przepisane leki:");
			contentPanel.add(lblPrzepisaneLeki, "cell 0 3");
		}
		{
			JLabel label = new JLabel("<dynamic drugs>");
			contentPanel.add(label, "cell 1 3");
		}
		{
			JLabel lblLeki = new JLabel("Leczenie:");
			contentPanel.add(lblLeki, "cell 0 4");
		}
		{
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText("<dynamic leczenie>");
			contentPanel.add(textPane, "cell 1 4,grow");
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
