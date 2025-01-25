package oot2_project;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registracija {

	public JFrame frame;
	private JTextField email;
	private JTextField ime;
	private JPasswordField lozinka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registracija window = new Registracija();
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
		frame.setBounds(100, 100, 416, 203);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Upišite vaš email:");
		lblNewLabel.setBounds(31, 30, 104, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUpiiteVaeIme = new JLabel("Upišite vaše ime:");
		lblUpiiteVaeIme.setBounds(31, 69, 104, 14);
		frame.getContentPane().add(lblUpiiteVaeIme);
		
		JLabel lblUpiiteSiLozinku = new JLabel("Upišite si lozinku:");
		lblUpiiteSiLozinku.setBounds(31, 106, 104, 14);
		frame.getContentPane().add(lblUpiiteSiLozinku);
		
		email = new JTextField();
		email.setBounds(145, 27, 86, 20);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		ime = new JTextField();
		ime.setColumns(10);
		ime.setBounds(145, 66, 86, 20);
		frame.getContentPane().add(ime);
		
		lozinka = new JPasswordField();
		lozinka.setBounds(145, 103, 86, 20);
		frame.getContentPane().add(lozinka);
		
		JButton btnNewButton = new JButton("Registriraj se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String imes, emails, lozinkas;
				imes=ime.getText();
				emails=email.getText();
				lozinkas=lozinka.getText();
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");
					String upit="INSERT INTO OOT_Korisnik(korisnicko_ime, email, lozinka) VALUES (?, ?, ?)";
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setString(1, imes);
					ps.setString(2, emails);
					ps.setString(3, lozinkas);
					
					int ubacenoRedaka=ps.executeUpdate();
					if (ubacenoRedaka>0) {
						JOptionPane.showMessageDialog(null, "Uspješno upisani podaci.");
					}
					else {
						JOptionPane.showMessageDialog(null, "Podaci nisu upisani!");
					}
						}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
				
				
			}
		});
		btnNewButton.setBounds(266, 102, 110, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
