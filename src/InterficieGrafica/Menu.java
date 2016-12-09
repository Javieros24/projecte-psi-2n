package InterficieGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Restaurant.Client;
import javax.swing.*;

/**
 * Finestra menu per tal de poder veure l'historial de comandes d'un client o fer una comanda
 * 
 * @author GRUP 10
 * 
 */
public class Menu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//Creem els botons 
	private JButton crearComanda = new JButton("Crear una comanda");
	private JButton veureHistorial = new JButton("Veure historial de comandes");
	private JButton logOut = new JButton("LOG OUT");
	private JLabel benvinguda2 = new JLabel("Esculli una de les opcions a realitzar:");
	
	/**
	 * Constructor que rep tots els parametres, els inicialitza i crea i afegeix tots els panells per l'interficie grafica
	 * @param nom es l'String que es mostra a dalt de tot a l'esquerra de la finestra
	 * @param client es un client
	 * @param llistaProductes es la llista de productes
	 * @param llistaClients es la llista de clients
	 */
	public Menu(String nom, Client client, LlistaProductes llistaProductes, LlistaClients llistaClients){
		super(nom);
		
		JLabel benvinguda1 = new JLabel("Benvingut a Take a Byte "+client.getNom()+".");
		
		//Creem els panels 
		JPanel info = new JPanel();
		JPanel benv = new JPanel();
		JPanel logo = new JPanel();
		JPanel dades = new JPanel();
		
		//Canviem el color de fons del panel de benvinguda
		benv.setBackground(Color.LIGHT_GRAY);
		
		ImageIcon image = new ImageIcon("src/Fitxers/TB-Menu.jpg");
		JLabel fondo = new JLabel(image);
		fondo.setSize(450, 125);
		fondo.setBounds(0, 0, 10, 10);
		
		//Dividim la pantalla en dos
		logo.setBackground(Color.WHITE);
		JSplitPane panell = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panell.setResizeWeight(0.3);
		panell.add(fondo);
		panell.add(info);
		info.setBackground(Color.LIGHT_GRAY);
		panell.setEnabled(true);
		panell.setDividerSize(0);
		add(panell, BorderLayout.CENTER);
		setSize(830, 500);
		setVisible(true);
		setResizable(false);
		
		//Canviar color text
		benvinguda1.setForeground(Color.BLACK);
		benvinguda2.setForeground(Color.BLACK);
		
		//Canviar el tipus de lletra
		crearComanda.setFont(new java.awt.Font("Calibri", 1, 16));
		veureHistorial.setFont(new java.awt.Font("Calibri", 1, 16));
		logOut.setFont(new java.awt.Font("Calibri", 1, 16));
		benvinguda2.setFont(new java.awt.Font("Calibri", 1, 18));
		benvinguda1.setFont(new java.awt.Font("Calibri", 1, 18));
		
		//Afegim els elements al panell 2 inferior
		dades.setLayout(new GridLayout(2, 2));
		benv.add(benvinguda1, BorderLayout.NORTH);
		benv.add(benvinguda2, BorderLayout.SOUTH);
		benv.add(logOut, BorderLayout.EAST);
		info.add(benv, BorderLayout.NORTH);
		dades.add(crearComanda, BorderLayout.NORTH);
		dades.add(veureHistorial, BorderLayout.SOUTH);
		info.add(dades, BorderLayout.CENTER);
		
		//Desenvolupem els esdeveniments en clicar el botó
		crearComanda.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new CrearComanda("Crear una nova comanda",client, llistaProductes, llistaClients);
				setVisible(false);
			}
			
		});
		
		veureHistorial.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if (!client.hiHaComandes()) JOptionPane.showMessageDialog(null,"Encara no has realitzat cap comanda!");
				else{
					new Historial("Historial de comandes de "+client.getNom(), client, llistaProductes, llistaClients);
					setVisible(false);
				}
			}
			
		});
		
		logOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new Login("Login", llistaProductes, llistaClients);
				setVisible(false);
			}
		});
		
		//Alliberem memoria
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
