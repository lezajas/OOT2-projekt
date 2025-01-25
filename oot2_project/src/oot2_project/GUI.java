package oot2_project;
import oot2_project.Registracija;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class GUI {

	private JFrame frame;
	private JTextField login_ime;
	private JPasswordField login_lozinka;

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
	public GUI() {
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
		
		login_ime = new JTextField();
		login_ime.setBounds(122, 18, 86, 20);
		frame.getContentPane().add(login_ime);
		login_ime.setColumns(10);
		
		JButton btnNewButton = new JButton("Prijavi se");
		btnNewButton.setBounds(265, 59, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRegistrirajSe = new JButton("Registriraj se");
		btnRegistrirajSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registracija window = new Registracija();
				window.frame.setVisible(true);
			}
			
		});
		btnRegistrirajSe.setBounds(246, 156, 120, 23);
		frame.getContentPane().add(btnRegistrirajSe);
		
		JLabel lblNewLabel_1 = new JLabel("Ako nemate korisnički račun registrirajte se:");
		lblNewLabel_1.setBounds(10, 160, 212, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		login_lozinka = new JPasswordField();
		login_lozinka.setBounds(122, 60, 86, 20);
		frame.getContentPane().add(login_lozinka);
	}
}