package InterficieGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Excepcions.NotFoundException;
import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Restaurant.Client;

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JLabel eNom  = new JLabel("Usuari      ");
	private JLabel eContrasenya  = new JLabel("Contrasenya      ");
	private JLabel eRegistrar  = new JLabel("Encara no estas registrat? Registra't AQUI! ");
	private JButton bLogin = new JButton ("Entrar");
	private JTextField tfNom = new JTextField();
	private JPasswordField pfContrasenya = new JPasswordField();
	
	
	public Login(String nom, LlistaProductes llistaProductes, LlistaClients llistaClients){
		super(nom);
		
		//Dividim el Frame en 2, amb proporcio 0.3
		JPanel logo = new JPanel();
		JPanel info = new JPanel();
		JPanel dades = new JPanel();
		JPanel registre = new JPanel();
		
		logo.setBackground(Color.WHITE);
		ImageIcon image = new ImageIcon("src/Fitxers/TB3.jpg");
		JLabel fondo = new JLabel(image);
		fondo.setSize(450, 125);
		fondo.setBounds(0, 0, 10, 10);
		info.setBackground(Color.WHITE);
		JSplitPane panell = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panell.setResizeWeight(0.5);
		panell.add(fondo);
		panell.add(info);
		panell.setEnabled(true);
		panell.setDividerSize(0);
		add(panell, BorderLayout.CENTER);
		setSize(450,250);
		setVisible(true);

		eNom.setFont(new java.awt.Font("Calibri", 1, 20));
		tfNom.setFont(new java.awt.Font("Calibri", 0, 14));
		eContrasenya.setFont(new java.awt.Font("Calibri", 1, 20));
		eRegistrar.setFont(new java.awt.Font("Calibri", 0, 20));
		bLogin.setFont(new java.awt.Font("Calibri", 1, 20));
		//Afegim els elements al panell 2 (inferior)
		dades.setLayout(new GridLayout(3,2));
		dades.add(eNom);
		dades.add(tfNom);
		dades.add(eContrasenya);
		dades.add(pfContrasenya);
		dades.add(bLogin);
		registre.add(eRegistrar);
		info.add(dades, BorderLayout.CENTER);
		info.add(registre, BorderLayout.SOUTH);
		
		bLogin.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try {
					Client client =usuariCorrecte(llistaClients);
					setVisible(false);
					new Menu("Menu", client, llistaProductes, llistaClients);
				} catch (NotFoundException e) {
					JOptionPane.showMessageDialog(null, "Usuari o contrasenya incorrecta.", "ERROR!",JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		
		eRegistrar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				setVisible(false);
				new CrearUsuari();
			}
		});
		
		
		
		
		
		
	}

	private Client usuariCorrecte(LlistaClients llista) throws NotFoundException{
		Client[] clients= llista.getLlistaClients();
		
		for(int i=0; i<llista.getnElements(); i++) {
			if (clients[i].getNomUsuari().equals(tfNom.getText()) && clients[i].getContrasenya().equals(String.valueOf(pfContrasenya.getPassword())))
				return clients[i];
		}
		throw new NotFoundException();
		
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public JLabel geteNom() {
		return eNom;
	}


	public JLabel geteContrasenya() {
		return eContrasenya;
	}


	public JLabel geteRegistrar() {
		return eRegistrar;
	}


	public JButton getbLogin() {
		return bLogin;
	}


	public JTextField getTfNom() {
		return tfNom;
	}


	public JPasswordField getPfContrasenya() {
		return pfContrasenya;
	}
	

}
