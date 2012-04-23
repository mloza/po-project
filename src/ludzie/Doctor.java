package ludzie;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.SpringLayout;



public class Doctor extends Person {

	private JFrame frame;

		private String login;
		Occupation type = Occupation.DOCTOR;
		
		public Doctor(String login, String name, String surname,
				Date birthDate) {
			super(name, surname, birthDate);
			
			this.setLogin(login);
			initialize();
		}

		protected String getLogin() {
			return login;
		}

		private void setLogin(String login) {
			this.login = login;
		}
		

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
