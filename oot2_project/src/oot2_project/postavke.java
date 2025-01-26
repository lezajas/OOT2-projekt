package oot2_project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class postavke {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					postavke window = new postavke();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public postavke() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 499, 374);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Promjena lozinke:");
		lblNewLabel.setBounds(38, 35, 116, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Promjena korisničkog imena:");
		lblNewLabel_1.setBounds(38, 89, 148, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
