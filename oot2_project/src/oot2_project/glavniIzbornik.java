package oot2_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import oot2_project.login;
import javax.swing.JPanel;
import java.awt.Panel;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.ChartTheme;
import javax.swing.JTextField;


public class glavniIzbornik {

	public JFrame frame;
	private JTable tablePrihodi;
	private JTable tableRashodi;
	private login user; //Sprema varijablu login
	private JTextField uk_iznos;
	private JTextField textField;



	/**
	 * Create the application.
	 */
	public glavniIzbornik(login user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 617, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel PrihodiLabel = new JLabel("PRIHODI");
		PrihodiLabel.setBounds(47, 43, 75, 14);
		PrihodiLabel.setForeground(Color.GREEN);
		frame.getContentPane().add(PrihodiLabel);
		
		JLabel rashodiLabel = new JLabel("RASHODI");
		rashodiLabel.setBounds(493, 43, 79, 14);
		rashodiLabel.setForeground(Color.RED);
		frame.getContentPane().add(rashodiLabel);
		
		JLabel lblNewLabel = new JLabel("PREGLED BUĐETA");
		lblNewLabel.setBounds(246, 11, 114, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton pregledUredjivanje = new JButton("Pregledaj i Uredi");
		pregledUredjivanje.setBounds(23, 374, 127, 23);
		pregledUredjivanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pregledUredjivanje window = new pregledUredjivanje(user);
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(pregledUredjivanje);
		
		
		JButton dodajTransakciju = new JButton("Dodaj Transakciju");
		dodajTransakciju.setBounds(214, 374, 158, 23);
		dodajTransakciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajTransakciju window = new dodajTransakciju(user);
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(dodajTransakciju);
		
		JButton postavke = new JButton("Postavke");
		postavke.setBounds(458, 374, 114, 23);
		postavke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postavke window = new postavke(user);
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(postavke);
		
		DefaultTableModel modelPrihodi= new DefaultTableModel();
		modelPrihodi.addColumn("Naziv");
		modelPrihodi.addColumn("Datum");
		modelPrihodi.addColumn("Iznos");
		
		tablePrihodi = new JTable(modelPrihodi);
		JScrollPane scrollPane=new JScrollPane(tablePrihodi);
		scrollPane.setBounds(23, 68, 210, 272);
		frame.getContentPane().add(scrollPane);
		tablePrihodi.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Naziv", "Datum", "Iznos"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		DefaultTableModel modelRashodi= new DefaultTableModel();
		modelRashodi.addColumn("Naziv");
		modelRashodi.addColumn("Datum");
		modelRashodi.addColumn("Iznos");
		
		tableRashodi = new JTable(modelRashodi);
		JScrollPane scrollPaneRashodi=new JScrollPane(tableRashodi);
		scrollPaneRashodi.setBounds(362, 68, 210, 272);
		frame.getContentPane().add(scrollPaneRashodi);
		tableRashodi.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Naziv", "Datum", "Iznos"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		
		JButton btnNewButton = new JButton("Osviježi");
		btnNewButton.setBounds(246, 340, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int id_korisnika=user.getKorisnicki_id();
					float prihodi_uk_iznos = 0;
					float rashodi_uk_iznos = 0;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://student.veleri.hr/tmatejcic", "tmatejcic", "31032003tomi");
					String upit="SELECT naziv_transakcije, datum_transakcije, iznos_transakcije FROM OOT_Transakcije WHERE kategorija_transakcije='PRIHOD' AND id_korisnik_transakcija=?";
					PreparedStatement ps=con.prepareStatement(upit);
					ps.setInt(1, id_korisnika);
					ResultSet rs=ps.executeQuery();
					
					DefaultTableModel model=(DefaultTableModel)tablePrihodi.getModel();
					model.setRowCount(0);
					while(rs.next()) {
						String nazivs=rs.getString(1);
						String datums=rs.getString(2);
						String iznos=rs.getString(3);
						float iznosFloat = Float.parseFloat(iznos); 
						prihodi_uk_iznos=prihodi_uk_iznos+iznosFloat;
						model.addRow(new Object[] {nazivs,datums, iznos});
					}
					tablePrihodi.repaint();
					
					String upit2="SELECT naziv_transakcije, datum_transakcije, iznos_transakcije FROM OOT_Transakcije WHERE kategorija_transakcije='RASHOD' AND id_korisnik_transakcija=?";
					PreparedStatement ps2=con.prepareStatement(upit2);
					ps2.setInt(1, id_korisnika);
					ResultSet rs2=ps2.executeQuery();
					
					DefaultTableModel model2=(DefaultTableModel)tableRashodi.getModel();
					model2.setRowCount(0);
					
					while(rs2.next()) {
						String nazivs=rs2.getString(1);
						String datums=rs2.getString(2);
						String iznos=rs2.getString(3);
						float iznosFloat = Float.parseFloat(iznos); 
						rashodi_uk_iznos=rashodi_uk_iznos+iznosFloat;
						model2.addRow(new Object[] {nazivs,datums, iznos});
					}
					
					if(rashodi_uk_iznos<prihodi_uk_iznos) {
						float fuk_iznos =prihodi_uk_iznos - rashodi_uk_iznos;
						String tekst= String.valueOf(fuk_iznos);
						JLabel ukupni_rashodiLabel = new JLabel("Izračunan iznos: +"+tekst);
						ukupni_rashodiLabel.setBounds(246, 40, 150, 20);
						ukupni_rashodiLabel.setForeground(Color.GREEN);
						frame.getContentPane().add(ukupni_rashodiLabel);
						frame.repaint();
					}
					else {
						float fuk_iznos =rashodi_uk_iznos - prihodi_uk_iznos;
						String tekst= String.valueOf(fuk_iznos);
						JLabel ukupni_prihodiiLabel = new JLabel("Izračunan iznos: -"+tekst);
						 ukupni_prihodiiLabel.setBounds(246, 40, 150, 20);
						 ukupni_prihodiiLabel.setForeground(Color.RED);
						frame.getContentPane().add( ukupni_prihodiiLabel);
						frame.repaint();
					}
					
					
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		
		
		


	

		
	}
}
