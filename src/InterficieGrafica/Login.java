package InterficieGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Llistes.LlistaClients;
import Restaurant.Client;

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JLabel eNom  = new JLabel("Usuari      ");
	private JLabel eContrasenya  = new JLabel("Contrasenya      ");
	private JLabel eRegistrar  = new JLabel("Encara no estas registrat? Registra't AQUI! ");
	private JButton bLogin = new JButton ("Entrar");
	private JTextField tfNom = new JTextField();
	private JPasswordField pfContrasenya = new JPasswordField();
	
	
	public Login(String nom, LlistaClients llistaClients){
		super(nom);
		
		//Dividim el Frame en 2, amb proporcio 0.3
		JPanel logo = new JPanel();
		JPanel info = new JPanel();
		JPanel dades = new JPanel();
		JPanel registre = new JPanel();
		
		logo.setBackground(Color.DARK_GRAY);
		//BufferedImage logo = ImageIO.read(new File("src/Fitxers/logo.png"));
		info.setBackground(Color.WHITE);
		JSplitPane panell = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panell.setResizeWeight(0.5);
		panell.add(logo);
		panell.add(info);
		panell.setEnabled(true);
		panell.setDividerSize(0);
		add(panell, BorderLayout.CENTER);
		setSize(450,250);
		setVisible(true);

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
		
		boolean trobat =usuariCorrecte(llistaClients);
		if (trobat){
			new Menu();
			System.out.println("SI!");
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuari o contrasenya incorrecta.", "ERROR!",JOptionPane.WARNING_MESSAGE);
		}
		
		bLogin.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				boolean trobat =usuariCorrecte(llistaClients);
				if (trobat){
					new Menu();
					System.out.println("SI!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Usuari o contrasenya incorrecta.", "ERROR!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
		
		
	}

	private boolean usuariCorrecte(LlistaClients llista){
		Client[] clients= llista.getLlistaClients();
		
		for(int i=0; i<llista.getnElements(); i++) {
			if (clients[i].getNomUsuari().equals(tfNom.getText()) && clients[i].getContrasenya().equals(String.valueOf(pfContrasenya.getPassword())))
				return true;
		}
		return false;
		
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
