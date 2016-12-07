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
	
	private JLabel titol1;
	private Comanda[] llistaComandes;
	private JList<Comanda> llista;
	private JScrollPane scroll1;
	private JButton consultar;
	private JButton tornar;
	private JLabel titol2 ;
	private JButton copiar;
	private JList<Producte> info;
	private JScrollPane scroll2;
	private JLabel resum;
	private JPanel adalt;
	private JPanel abaix;
	
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
		llistaComandes = client.getTaulaComandes();
		titol1 = new JLabel("Les teves comandes:");
		llista = new JList<Comanda>(llistaComandes);
		scroll1 = new JScrollPane(llista);
		consultar = new JButton("Consultar informacio de la comanda");
		esquerra.add(consultar, BorderLayout.SOUTH);
		esquerra.add(titol1, BorderLayout.NORTH);
		esquerra.add(scroll1, BorderLayout.CENTER);
		
		//configurem la banda dreta (informacio detallada de la comanda)
		tornar = new JButton("Tornar al menu principal");
		titol2 = new JLabel("Detalls de la comanda:");
		copiar = new JButton("Realitzar aquesta mateixa comanda");
		copiar.setEnabled(false);
		info = new JList<Producte>();
		scroll2 = new JScrollPane(info);
		resum = new JLabel();
		adalt = new JPanel(new BorderLayout());
		abaix = new JPanel(new BorderLayout());
		
		dreta.add(adalt, BorderLayout.NORTH);
		dreta.add(abaix, BorderLayout.SOUTH);
		
		adalt.add(tornar, BorderLayout.NORTH);
		adalt.add(titol2, BorderLayout.CENTER);		
		adalt.add(scroll2, BorderLayout.AFTER_LAST_LINE);
		abaix.add(resum, BorderLayout.NORTH);
		abaix.add(copiar,BorderLayout.SOUTH);
		
		
		info.setSize(400, 400);
		copiar.setSize(600, 600);
		
		setSize(1000, 600);
		setVisible(true);
		setResizable(false);
		
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
				if(!copiar.isEnabled()) copiar.setEnabled(true);
			}
		});
		
		copiar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				Comanda c = client.copiar(llistaComandes[numComanda],llistaComandes[numComanda].getCodiComanda());
				client.afegirComanda(c);
				guardarComanda(client.getIdentificador(), c);
				JOptionPane.showMessageDialog(null,"S'ha realitzat correctament la comanda!");
				llista.updateUI();
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
