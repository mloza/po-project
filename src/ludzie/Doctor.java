package ludzie;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;



public class Doctor extends Person {

	private JFrame frame;

		private String login;
		Occupation type = Occupation.DOCTOR;
		
		public Doctor(String login, String name, String surname,
				Date birthDate) {
			super(name, surname, birthDate);
			
			this.setLogin(login);
			
		}

		protected String getLogin() {
			return login;
		}

		private void setLogin(String login) {
			this.login = login;
		}
		
		/**
		 * @wbp.parser.entryPoint
		 */
		public void run(){
			initialize();
			
			
			
		}

	private void initialize() {
		frame = new JFrame("Lekarz");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 250, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 430, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
