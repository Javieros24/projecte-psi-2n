package InterficieGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import Excepcions.NotFoundException;
import InterficieGrafica.Menu;
import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Productes.Producte;
import Restaurant.Client;
import Restaurant.Comanda;

public class CrearComanda extends JFrame{
	
	private JLabel eProducte = new JLabel("Aquí pot escollir diferents productesdel nostre Menú per crear una nova comanda");
	private JLabel eMenu = new JLabel("MENÚ");
	private JLabel eComanda = new JLabel("COMANDA");
	private JList lProductes;
	private JList lComanda;
	private JButton bAfegir = new JButton("Afegir producte");
	private JLabel eQuantitat = new JLabel("Quantitat ");
	private JTextField tfQuantitat = new JTextField(2);
	private JLabel ePreu;
	private int numProductes, productesRestants;
	
	public CrearComanda(String nom, Client client, LlistaProductes llistaProductes, LlistaClients llistaClients){
		super(nom);
		numProductes = 10;
		productesRestants = numProductes;
		setSize(500,650);
		JPanel esquerra = new JPanel();
		JPanel dreta = new JPanel();

		Container container = getContentPane();
		container.setLayout(new GridLayout(1,2));
		
		container.add(esquerra);
		container.add(dreta);
		
		lProductes = new JList(llistaProductes.getLlistaProductes());
		lProductes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lProductes.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane lScroller = new JScrollPane(lProductes);
		//lScroller.setPreferredSize(new Dimension(250,100));
		
		
		eMenu.setFont(new java.awt.Font("Erias Light ITC", 1, 25));
		eComanda.setFont(new java.awt.Font("Erias Light ITC", 1, 25));
		lProductes.setFont(new java.awt.Font("Calibri", 0, 20));
		
		esquerra.setLayout(new BoxLayout(esquerra, BoxLayout.PAGE_AXIS));
		esquerra.add(eMenu, BorderLayout.NORTH);
		esquerra.add(new JSeparator(SwingConstants.HORIZONTAL));
		esquerra.add(lProductes,BorderLayout.CENTER);
		JPanel pQuantitat = new JPanel();
		pQuantitat.setLayout(new FlowLayout());
		pQuantitat.add(bAfegir);
		pQuantitat.add(eQuantitat);
		pQuantitat.add(tfQuantitat);
		esquerra.add(pQuantitat);
		eMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		lProductes.setAlignmentX(Component.CENTER_ALIGNMENT);
		bAfegir.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		dreta.setLayout(new BoxLayout(dreta,BoxLayout.PAGE_AXIS));
		Comanda comanda = new Comanda(numProductes, llistaClients.referenciaComanda());
		DefaultListModel list = new DefaultListModel();
		lComanda = new JList(list);
		lComanda.setFont(new java.awt.Font("Calibri", 0, 20));

		ePreu = new JLabel("TOTAL   "+comanda.calcularPreu(client.isPreferent())+"€");
		eComanda.setAlignmentX(Component.CENTER_ALIGNMENT);
		lComanda.setAlignmentX(Component.CENTER_ALIGNMENT);
		eQuantitat.setAlignmentX(Component.CENTER_ALIGNMENT);
		ePreu.setAlignmentX(Component.CENTER_ALIGNMENT);
		dreta.add(eComanda, BorderLayout.NORTH);
		dreta.add(new JSeparator(SwingConstants.HORIZONTAL));
		JPanel pComanda = new JPanel();
		pComanda.add(lComanda, BorderLayout.NORTH);
		dreta.add(pComanda);
		dreta.add(ePreu);
		JLabel eUsuari = new JLabel("Has iniciat sessió com "+client.getNomUsuari());
		JPanel pUsuari = new JPanel();
		pUsuari.setLayout(new FlowLayout());
		pUsuari.add(eUsuari);
		dreta.add(pUsuari);
		setVisible(true);
		
		
		bAfegir.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				int i= lProductes.getSelectedIndex();
				try {
					Producte p = llistaProductes.getLlistaProductes()[i];
					int quant = Integer.parseInt(tfQuantitat.getText());
					if (quant<=0 || quant>productesRestants)
						JOptionPane.showMessageDialog(null, "La quantitat no és correcte!", "ERROR!",JOptionPane.WARNING_MESSAGE);
					else{
						productesRestants = productesRestants-quant;
						comanda.afegirProducte(p, quant);
						for (int cont=0; cont<quant;cont++){
							list.addElement(p.getNom()+"     "+p.getPreu()+"€");
						}
						if (productesRestants == 0){
							client.afegirComanda(comanda);
							guardarComanda(client.getIdentificador(), comanda);
							JOptionPane.showMessageDialog(null,"S'ha creat correctament la comanda!");
							setVisible(false);
							setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							new Menu("Menu", client, llistaProductes, llistaClients);
						}
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "No ha seleccionat cap producte!", "ERROR!",JOptionPane.WARNING_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "No ha seleccionat cap producte!", "ERROR!",JOptionPane.WARNING_MESSAGE);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La quantitat no és correcte!", "ERROR!",JOptionPane.WARNING_MESSAGE);
				}

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
		} //nullPointerException
	}
	
}
