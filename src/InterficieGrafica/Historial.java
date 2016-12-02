package InterficieGrafica;
import javax.swing.*;

import Excepcions.NotFoundException;
import InterficieGrafica.Menu;
import Llistes.LlistaProductes;
import Productes.Producte;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Restaurant.*;

public class Historial extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private int numComanda=0;
	
	public Historial(String nom, Client client){
		super(nom);
		
		//dividim la pantalla en 2 horitzontal
		Container container = getContentPane();
		container.setLayout(new GridLayout (1, 2));
		
		JPanel esquerra = new JPanel();
		JPanel dreta = new JPanel();
		esquerra.setLayout(new BorderLayout());
		dreta.setLayout(new BorderLayout());
		container.add(esquerra, BorderLayout.WEST);
		container.add(dreta, BorderLayout.EAST);
	
		//configurem la banda esquerra (llista de comandes)
		JLabel titol1 = new JLabel();
		titol1.setText("Les teves comandes:");
		Comanda[] llistaComandes = client.getTaulaComandes();
		JList llista = new JList(llistaComandes);
		JButton consultar = new JButton();
		consultar.setText("Consultar informacio de la comanda");
		esquerra.add(consultar, BorderLayout.SOUTH);
		esquerra.add(titol1, BorderLayout.NORTH);
		esquerra.add(llista, BorderLayout.CENTER);
		
		//configurem la banda dreta (informacio detallada de la comanda)
		JLabel titol2 = new JLabel();
		titol2.setText("Detalls de la comanda:");
		JButton boto = new JButton();
		boto.setText("Realitzar aquesta mateixa comanda");

		JList info = new JList();
		JPanel adalt = new JPanel(new BorderLayout());
		JPanel abaix = new JPanel(new BorderLayout());
		
		dreta.add(adalt, BorderLayout.NORTH);
		dreta.add(abaix, BorderLayout.SOUTH);
		
		adalt.add(titol2, BorderLayout.NORTH);		
		adalt.add(info, BorderLayout.CENTER);
		abaix.add(boto,BorderLayout.SOUTH);
		
		
		info.setSize(400, 400);
		boto.setSize(600, 600);
		
		setSize(500, 300);
		setVisible(true);
		
		consultar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				numComanda = llista.getSelectedIndex();
				Producte[] productes = llistaComandes[numComanda].getLlista();
				info.setListData(productes);
			}
		});
		
		boto.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Comanda c = client.copiar(llistaComandes[numComanda],llistaComandes[numComanda].getCodiComanda());
				client.afegirComanda(c);
			}
		});
		
	}

}
