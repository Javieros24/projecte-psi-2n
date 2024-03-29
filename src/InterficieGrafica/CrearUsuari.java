package InterficieGrafica;

import javax.swing.*;

import Excepcions.DuplicatedNameException;
import Excepcions.IllegalCharException;
import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Productes.RestriccionsAlimentaries;
import Restaurant.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**Classe que genera la finestra per a poder crear un nou client
 *
 * @author GRUP 10
 *
 */
public class CrearUsuari extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel buit = new JLabel("");
	private JLabel eMissatge = new JLabel ("Es necessari insertar la informacio seguent");
	private JLabel eMissatge1 = new JLabel ("per completar el registre:");
	private JLabel eDades  = new JLabel("Dades");
	private JLabel eNom  = new JLabel("Nom i cognoms");
	private JLabel eAdreca  = new JLabel("Adre�a");
	private JLabel eTelefon  = new JLabel("Num. Telefon");
	private JTextField tfNom = new JTextField();
	private JTextField tfAdreca = new JTextField();
	private JTextField tfTelefon = new JTextField();
	private JLabel eRestriccions  = new JLabel("Restriccions Alimentaries");
	private JCheckBox cbLactosa = new JCheckBox("Lactosa");
	private JCheckBox cbFruitsSecs = new JCheckBox("Fruits secs");
	private JCheckBox cbCeliacs = new JCheckBox("Celiacs");
	private JLabel eUsuari  = new JLabel("Usuari");
	private JLabel eNomUsuari  = new JLabel("Nom d'usuari");
	private JLabel eContrasenya  = new JLabel("Contrasenya");
	private JTextField tfNomUsuari = new JTextField();
	private JTextField tfContrasenya = new JTextField();
	private JButton bRegistrar = new JButton ("Registra't");
	private JButton bTornar = new JButton ("Tornar");
	
	/**Constructor de la finestra
	 * @param nom Nom de la finestra
	 * @param llistaProductes La llista de productes per poder passarla al Menu
	 * @param llistaClients La llista de clients per a poder passarla al Menu
	 */
	public CrearUsuari(String nom, LlistaProductes llistaProductes, LlistaClients llistaClients){
		
		super(nom);
		setSize(750,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);	
		eMissatge.setFont(new java.awt.Font("Calibri", 0, 18));
		eMissatge1.setFont(new java.awt.Font("Calibri", 0, 18));
		eDades.setFont(new java.awt.Font("Calibri", 1, 20));
		eRestriccions.setFont(new java.awt.Font("Calibri", 1, 20));
		eUsuari.setFont(new java.awt.Font("Calibri", 1, 20));
		eNom.setFont(new java.awt.Font("Calibri", 0, 18));
		eAdreca.setFont(new java.awt.Font("Calibri", 0, 18));
		eTelefon.setFont(new java.awt.Font("Calibri", 0, 18));
		eNomUsuari.setFont(new java.awt.Font("Calibri", 0, 18));
		eContrasenya.setFont(new java.awt.Font("Calibri", 0, 18));
		
		//dividim el frame principal en 2
		JPanel pDades = new JPanel();
		JPanel pUsuari = new JPanel();	
		setLayout(new GridLayout(1,3));
		add(pDades);		
		add(pUsuari);	
		//dividim el panell dades en 3
		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		pDades.setLayout(new GridLayout(3,1));
		pDades.add(p0);		
		pDades.add(p1);
		pDades.add(p2);
		p0.setLayout(new GridLayout(3,1));	
		p0.add(eMissatge);
		p0.add(eMissatge1);
		p0.add(eDades);
		p1.setLayout(new GridLayout(5,2));
		p1.add(eNom);
		p1.add(tfNom);
		p1.add(eAdreca);
		p1.add(tfAdreca);
		p1.add(eTelefon);
		p1.add(tfTelefon);		
		p2.setLayout(new GridLayout(4,1));
		p2.add(eRestriccions);
		p2.add(cbLactosa);
		p2.add(cbFruitsSecs);
		p2.add(cbCeliacs);
		//dividim el panell usuari en 2
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		pUsuari.setLayout(new GridLayout(2,1));
		pUsuari.add(p3);
		pUsuari.add(p4);		
		p3.setLayout(new GridLayout(3,2));
		p3.add(eUsuari);
		p3.add(buit);
		p3.add(eNomUsuari);
		p3.add(tfNomUsuari);
		p3.add(eContrasenya);
		p3.add(tfContrasenya);
		p4.add(bRegistrar, BorderLayout.SOUTH);
		p4.add(bTornar, BorderLayout.SOUTH);	
		bRegistrar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				RestriccionsAlimentaries[] restriccions;
				int i=0;
				if(cbLactosa.isSelected()) i++;
				if(cbCeliacs.isSelected()) i++;
				if(cbFruitsSecs.isSelected()) i++;
				restriccions = new RestriccionsAlimentaries[i];
				i=0;
				if(cbLactosa.isSelected()) {
					restriccions[i] = RestriccionsAlimentaries.ALERGICSLACTOSA;
					i++;
				}
				if(cbCeliacs.isSelected()) {
					restriccions[i] = RestriccionsAlimentaries.CELIACS;
					i++;
				}
				if(cbFruitsSecs.isSelected()) {
					restriccions[i] = RestriccionsAlimentaries.ALERGICSFRUITSSECS;
					i++;
				}
				try {
					if((tfNom.getText().isEmpty())||(tfAdreca.getText().isEmpty())|| (tfNomUsuari.getText().isEmpty())|| (tfContrasenya.getText().isEmpty())|| (tfTelefon.getText().isEmpty())){
						JOptionPane.showMessageDialog(null, "Camps buits", "ERROR!",JOptionPane.WARNING_MESSAGE);
					}
					else{
						Client c = llistaClients.afegirElement(tfNom.getText(), tfAdreca.getText(), tfNomUsuari.getText(), tfContrasenya.getText(), Integer.parseInt(tfTelefon.getText()), restriccions);
						guardarClient(c);
						setVisible(false);
						new Menu("Menu", c, llistaProductes, llistaClients);
					}
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Numero de telefon erroni", "ERROR!",JOptionPane.WARNING_MESSAGE);
				} catch (DuplicatedNameException e) {
					JOptionPane.showMessageDialog(null, "Aquest nom d'usuari ja existeix.", "ERROR!",JOptionPane.WARNING_MESSAGE);
				} catch (IllegalCharException e) {
					JOptionPane.showMessageDialog(null, "ERROR! Cap camp pot contenir el valor ','", "ERROR!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		bTornar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				setVisible(false);
				new Login("Login", llistaProductes, llistaClients);
			}
		});
	}
	
	/** Classe que permet guardar al final d'un fitxer el client passat per referencia
	 * @param c Client que es vol guardar
	 */
	private static void guardarClient(Client c){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Clients.txt", true));
			escriptura.write(c.getNom()+",");
			escriptura.write(c.getIdentificador()+",");
			escriptura.write(c.getAdreca()+",");
			escriptura.write(c.getNomUsuari()+",");
			escriptura.write(c.getContrasenya()+",");
			escriptura.write(c.getNumTelefon()+",");
			escriptura.write(String.valueOf(c.getRestriccions().length));
			for (int i=0; i < c.getRestriccions().length; i++){
				escriptura.write(","+c.getRestriccions()[i]);
			}
			escriptura.write("\n");
			escriptura.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
