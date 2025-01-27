package oot2_project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import oot2_project.login;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
public class postavke {

	public JFrame frame;
	private login user;
	private JPasswordField lozinka;
	private JPasswordField nova_lozinka;
	private JTextField korisnicko_ime;

	/**
	 * Create the application.
	 */
	public postavke(login user) {
		initialize();
		this.user = user;
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
		lblNewLabel.setBounds(10, 35, 116, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Promjena korisničkog imena:");
		lblNewLabel_1.setBounds(10, 158, 176, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		lozinka = new JPasswordField();
		lozinka.setBounds(136, 32, 131, 20);
		frame.getContentPane().add(lozinka);
		
		nova_lozinka = new JPasswordField();
		nova_lozinka.setBounds(309, 32, 131, 20);
		frame.getContentPane().add(nova_lozinka);
		
		korisnicko_ime = new JTextField();
		korisnicko_ime.setBounds(196, 155, 244, 20);
		frame.getContentPane().add(korisnicko_ime);
		korisnicko_ime.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("Promjeni korisničko ime");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int korisnicki_id=user.getKorisnicki_id();
				String korisnicko_imes;
				korisnicko_imes=korisnicko_ime.getText();
				if(korisnicko_imes.length()==0){
					JOptionPane.showMessageDialog(null, "Upišite novu korisničko ime!");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");
						String upit = "UPDATE OOT_Korisnik SET korisnicko_ime=?  WHERE id_korisnik=?";
	                    Statement stmt=con.createStatement();
	                    PreparedStatement ps=con.prepareStatement(upit);
	                    ps.setString(1, korisnicko_imes);
	                    ps.setInt(2, korisnicki_id);
	                    int rowsUpdated = ps.executeUpdate();
						if (rowsUpdated>0)
						{
	                    JOptionPane.showMessageDialog(null, "Korisničko ime uspiješno promjenjeno!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Dogodila se greška");
						}
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
					
					
				}
			}
		});
		btnNewButton_1.setBounds(256, 202, 184, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		JButton btnNewButton = new JButton("Promjeni lozinku");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int korisnicki_id=user.getKorisnicki_id();
				String lozinkas, nova_lozinkas;
				lozinkas = lozinka.getText();
				nova_lozinkas = nova_lozinka.getText();
				if(nova_lozinkas.length()==0){
					JOptionPane.showMessageDialog(null, "Upišite novu lozinku!");
				}
				else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");

						String upit="SELECT * FROM OOT_Korisnik WHERE id_korisnik=? AND lozinka=?";
						PreparedStatement ps=con.prepareStatement(upit);
						ps.setInt(1, korisnicki_id);
						ps.setString(2, lozinkas);
						
						ResultSet rs=ps.executeQuery();
						if (rs.next())
						{
							String upit_promjena = "UPDATE OOT_Korisnik SET lozinka=?  WHERE id_korisnik=?";
		                      Statement stmt=con.createStatement();
		                      PreparedStatement ps1=con.prepareStatement(upit_promjena);
		                      ps1.setString(1, nova_lozinkas);
		                      ps1.setInt(2, korisnicki_id);
		                      int rowsUpdated = ps1.executeUpdate();
		  					if (rowsUpdated>0)
		  					{
		                      JOptionPane.showMessageDialog(null, "Lozinka uspiješno promjenjena!");
		  					}
		  					else {
		  						JOptionPane.showMessageDialog(null, "Dogodila se greška");
		  					}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Stara lozinka nije ispravna!");
						}
					
						
						
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				
			}
		});
		btnNewButton.setBounds(256, 88, 184, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		JLabel lblNewLabel_2 = new JLabel("Brisanje računa:");
		lblNewLabel_2.setBounds(38, 270, 148, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Obriši moj račun.");
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int korisnicki_id=user.getKorisnicki_id();
				 int choice = JOptionPane.showConfirmDialog(null, "Jeste li sigurni da želite obrisati svoj račun?",
                         "Brisanje računa.", JOptionPane.YES_NO_OPTION);

				 		if (choice == JOptionPane.YES_OPTION) {
				 			try
							{
								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection connection=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");
								String upit="DELETE FROM OOT_Korisnik WHERE id_korisnik=?";
								PreparedStatement ps=connection.prepareStatement(upit);
								ps.setInt(1, korisnicki_id);
								int izbrisanoRedaka=ps.executeUpdate();
								if(izbrisanoRedaka==1) {
									JOptionPane.showMessageDialog(null, "Uspiješno brisanje računa. :(");
									System.exit(JOptionPane.CLOSED_OPTION);
									}
								else {
									JOptionPane.showMessageDialog(null, "Nije uspješno izbrisati redak.");
								}
							}
							catch(Exception e1)
							{
								JOptionPane.showMessageDialog(null, e1);
							}
				 			
				 		} else if (choice == JOptionPane.NO_OPTION) {
				 			
				 		} else {

				 		}


				
				
			}
		});
		btnNewButton_2.setBounds(256, 266, 184, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Stara lozinka*");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3.setBounds(139, 60, 106, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Nova lozinka*");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_3_1.setBounds(314, 63, 106, 14);
		frame.getContentPane().add(lblNewLabel_3_1);
	}
}
