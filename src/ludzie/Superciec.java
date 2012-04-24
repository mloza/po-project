package ludzie;

import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import gabinet.*;
public class Superciec extends Person implements worker{

	private JFrame frame;

		private String login;
		Occupation type = Occupation.DOCTOR;
		
		public Superciec(String login, String name, String surname,
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
		/*
		frame = new JFrame("Superciec");
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
		*/
		Gabinet_GUI window = new Gabinet_GUI();
		window.frame.setVisible(true);
		
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gabinet_GUI window = new Gabinet_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
