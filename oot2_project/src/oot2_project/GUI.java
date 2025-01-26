package oot2_project;
import oot2_project.Registracija;
import oot2_project.glavniIzbornik;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import oot2_project.login;


public class GUI {

	private JFrame frame;
	private JTextField login_ime;
	private JPasswordField login_lozinka;
	private login user;

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
		 user = new login();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 447, 232);
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
		login_ime.setBounds(122, 18, 136, 20);
		frame.getContentPane().add(login_ime);
		login_ime.setColumns(10);
		
		JButton btnNewButton = new JButton("Prijavi se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String imes, lozinkas;
				imes = login_ime.getText(); 
				lozinkas = login_lozinka.getText()
;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");

					String upit="SELECT * FROM OOT_Korisnik WHERE korisnicko_ime=? AND lozinka=?";
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setString(1, imes);
					ps.setString(2, lozinkas);
					
					ResultSet rs=ps.executeQuery();
					if (rs.next())
					{
						int db_id=rs.getInt(1);
						login user = new login();
						user.setKorisnicki_id(db_id);
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									glavniIzbornik window = new glavniIzbornik(user);
									window.frame.setVisible(true);
									frame.dispose();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Provjerite podatke ili se registrirajte.");
					}
				
					
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnNewButton.setBounds(333, 59, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRegistrirajSe = new JButton("Registriraj se");
		btnRegistrirajSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registracija window = new Registracija();
				window.frame.setVisible(true);
			}
			
		});
		btnRegistrirajSe.setBounds(302, 156, 120, 23);
		frame.getContentPane().add(btnRegistrirajSe);
		
		JLabel lblNewLabel_1 = new JLabel("Ako nemate korisnički račun registrirajte se:");
		lblNewLabel_1.setBounds(10, 160, 282, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		login_lozinka = new JPasswordField();
		login_lozinka.setBounds(122, 60, 136, 20);
		frame.getContentPane().add(login_lozinka);
	}
}