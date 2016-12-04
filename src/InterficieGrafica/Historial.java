package InterficieGrafica;
import javax.swing.*;

import InterficieGrafica.Menu;
import Llistes.*;
import Productes.Producte;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;

import Restaurant.*;

public class Historial extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private int numComanda=0;
	
	public Historial(String nom, Client client, LlistaProductes llistaProductes, LlistaClients llistaClients){
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
		JList<Comanda> llista = new JList<Comanda>(llistaComandes);
		JScrollPane scroll1 = new JScrollPane();
		scroll1.setViewportView(llista);
		JButton consultar = new JButton();
		consultar.setText("Consultar informacio de la comanda");
		esquerra.add(consultar, BorderLayout.SOUTH);
		esquerra.add(titol1, BorderLayout.NORTH);
		esquerra.add(scroll1, BorderLayout.CENTER);
		
		//configurem la banda dreta (informacio detallada de la comanda)
		JButton tornar = new JButton("Tornar al menu principal");
		JLabel titol2 = new JLabel("Detalls de la comanda:");
		JButton copiar = new JButton("Realitzar aquesta mateixa comanda");
		JList<Producte> info = new JList<Producte>();
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setViewportView(info);
		JLabel resum = new JLabel();
		JPanel adalt = new JPanel(new BorderLayout());
		JPanel abaix = new JPanel(new BorderLayout());
		
		dreta.add(adalt, BorderLayout.NORTH);
		dreta.add(abaix, BorderLayout.SOUTH);
		
		adalt.add(tornar, BorderLayout.NORTH);
		adalt.add(titol2, BorderLayout.CENTER);		
		adalt.add(scroll2, BorderLayout.AFTER_LAST_LINE);
		abaix.add(resum, BorderLayout.NORTH);
		abaix.add(copiar,BorderLayout.SOUTH);
		
		
		info.setSize(400, 400);
		copiar.setSize(600, 600);
		
		setSize(500, 300);
		setVisible(true);
		
		consultar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				numComanda = llista.getSelectedIndex();
				Producte[] productes = llistaComandes[numComanda].getLlista();
				info.setListData(productes);
				NumberFormat nf = NumberFormat.getInstance();
				nf.setMaximumFractionDigits(2);
				nf.setRoundingMode( RoundingMode.DOWN);
				String preu = nf.format(llistaComandes[numComanda].calcularPreu(client.isPreferent()));
				resum.setText("El preu total de la comanda es de "+preu+"€");
			}
		});
		
		copiar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Comanda c = client.copiar(llistaComandes[numComanda],llistaComandes[numComanda].getCodiComanda());
				client.afegirComanda(c);
				guardarComanda(client.getIdentificador(), c);
				JOptionPane.showMessageDialog(null,"S'ha realitzat correctament la comanda!");
			}
		});
		
		tornar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				setVisible(false);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				new Menu("Menu", client, llistaProductes, llistaClients);
			}
		});
		
	}
	
	private void guardarComanda(int RefClient, Comanda c){
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Comandes.txt", true));
			Producte[] llista = c.getLlista();
			escriptura.write(RefClient+",");
			escriptura.write(c.getCodiComanda()+",");
			escriptura.write(c.getHoraComanda()+",");
			escriptura.write(String.valueOf(c.getNumProd()));
			for(int i=0;i<c.getNumProd();i++){
				escriptura.write("," + llista[i].getCodiReferencia());
			}
			escriptura.write("\n");
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
