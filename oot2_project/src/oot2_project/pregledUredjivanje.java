package oot2_project;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class pregledUredjivanje {

	JFrame frame;
	private JTable tableSve;
	private JButton obrisiTransakciju;
	private JButton prikaziPodatke;
	private JButton uredi;
	private login user;
	

	/**
	 * Create the application.
	 */
	public pregledUredjivanje(login user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sve Transakcije");
		lblNewLabel.setBounds(141, 24, 93, 14);
		frame.getContentPane().add(lblNewLabel);
		
		DefaultTableModel modelSve= new DefaultTableModel();
		modelSve.addColumn("ID");
		modelSve.addColumn("Naziv");
		modelSve.addColumn("Iznos");
		modelSve.addColumn("Kategorija");
		modelSve.addColumn("Datum");
		
		tableSve = new JTable(modelSve);
		JScrollPane scrollPane=new JScrollPane(tableSve);
		scrollPane.setBounds(10, 68, 414, 126);
		frame.getContentPane().add(scrollPane);
		
		obrisiTransakciju = new JButton("Obrisi");
		obrisiTransakciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ids;
				int odabirRed=tableSve.getSelectedRow();
				ids = tableSve.getValueAt(odabirRed, 0).toString();
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");
					
					String upit="DELETE FROM OOT_Transakcije WHERE id_transakcije=?";
					PreparedStatement ps=connection.prepareStatement(upit);
					ps.setString(1, ids);
					int izbrisanoRedaka=ps.executeUpdate();
					if(izbrisanoRedaka==1) {
						DefaultTableModel model1=(DefaultTableModel)tableSve.getModel();
						model1.removeRow(odabirRed);
						}
					else {
						JOptionPane.showMessageDialog(null, "Nije uspješno izbrisati redak.");
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}

				
				
			}
		});
		obrisiTransakciju.setBounds(298, 205, 89, 23);
		frame.getContentPane().add(obrisiTransakciju);
		
		prikaziPodatke = new JButton("Prikazi");
		prikaziPodatke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id_korisnika = user.getKorisnicki_id();
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");
					String upit="SELECT * FROM OOT_Transakcije WHERE id_korisnik_transakcija=?";
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setInt(1, id_korisnika);
					ResultSet rs=ps.executeQuery();
					modelSve.setRowCount(0);
					while(rs.next())
					{
						int id=rs.getInt(1);
						String naziv=rs.getString(2);
						int iznos=rs.getInt(3);
						String kategorija=rs.getString(4);
						int korisnik= rs.getInt(5);
						String datum=rs.getString(6);
						modelSve.addRow(new Object[]{id, naziv, iznos, kategorija, datum});
					}

				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		prikaziPodatke.setBounds(38, 205, 89, 23);
		frame.getContentPane().add(prikaziPodatke);
		
		uredi = new JButton("Uredi");
		uredi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ids, transakcijaNazivs, transakcijaIznoss, datums, listKategorijas;	
				int odabirRed=tableSve.getSelectedRow();
				ids = tableSve.getValueAt(odabirRed, 0).toString();
				transakcijaNazivs = tableSve.getValueAt(odabirRed, 1).toString();
				transakcijaIznoss = tableSve.getValueAt(odabirRed, 2).toString();
				listKategorijas = tableSve.getValueAt(odabirRed, 3).toString();
				datums = tableSve.getValueAt(odabirRed, 4).toString();
				
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");

					String upit1="SELECT * FROM OOT_Transakcije WHERE id_transakcije=?";
					PreparedStatement ps1=con.prepareStatement(upit1);
					ps1.setString(1, ids);
					
					ResultSet rs1=ps1.executeQuery();
					if(rs1.next()) {
						
						
						
						 String upit = "UPDATE OOT_Transakcije SET naziv_transakcije = ?, iznos_transakcije = ?, kategorija_transakcije = ?, datum_transakcije = ? WHERE id_transakcije=?";
	                      Statement stmt=con.createStatement();
					PreparedStatement ps=con.prepareStatement(upit);
						ps.setString(1, transakcijaNazivs);
						ps.setString(2, transakcijaIznoss);
						ps.setString(3, listKategorijas);
						ps.setString(4, datums);
						ps.setString(5, ids);
						
						int rowsUpdated = ps.executeUpdate();
					    if (rowsUpdated > 0) {
					    	JOptionPane.showMessageDialog(null, "Podaci su uspiješno ažurirani.");
					    } else {
					        JOptionPane.showMessageDialog(null, "Ažuriranje nije uspijelo.");
					    }
					}
					
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
			}
		});
		uredi.setBounds(171, 205, 89, 23);
		frame.getContentPane().add(uredi);
	}
}
