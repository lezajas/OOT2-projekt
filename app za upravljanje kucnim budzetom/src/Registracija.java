import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Registracija {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public Registracija() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 414, 252);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Korisničko ime : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 147, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLozinka = new JLabel("Lozinka : ");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLozinka.setBounds(10, 53, 147, 31);
		frame.getContentPane().add(lblLozinka);
		
		textField = new JTextField();
		textField.setBounds(122, 18, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(122, 60, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Prijavi se");
		btnNewButton.setBounds(265, 59, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRegistrirajSe = new JButton("Registriraj se");
		btnRegistrirajSe.setBounds(246, 156, 120, 23);
		frame.getContentPane().add(btnRegistrirajSe);
		
		JLabel lblNewLabel_1 = new JLabel("Ako nemate korisnički račun registrirajte se:");
		lblNewLabel_1.setBounds(10, 160, 212, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
