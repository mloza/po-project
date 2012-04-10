package people;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Admin {

	public static void main(String[] args) {
		JTextField login;
		final JFrame frame = new JFrame("Administrator");

		String[] actionList = { "", "Utworz nowe konto", "Usuń konto",
				"Wyświetl" };

		final JComboBox choose = new JComboBox(actionList);

		choose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (choose.getSelectedIndex()) {
				case 1:

					System.out.println("1");
					cont.setVisible(false);
					break;
				}
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		Container cont = frame.getContentPane();
		cont.setLayout(new FlowLayout());
		cont.add(choose);

		frame.setVisible(true);
	}
}