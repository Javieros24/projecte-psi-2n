package InterficieGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javax.swing.*;

import InterficieGrafica.Menu;
import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Productes.Plat;
import Productes.Producte;
import Restaurant.Client;
import Restaurant.Comanda;

public class CrearComanda extends JFrame{
	

	private static final long serialVersionUID = 1L;
	private JLabel eProducte = new JLabel("Aquí pot escollir diferents productesdel nostre Menú per crear una nova comanda");
	private JLabel eMenu = new JLabel("MENÚ");
	private JLabel eComanda = new JLabel("COMANDA");
	private JList<Producte> lProductes;
	private JList<Producte> lComanda;
	private JButton bAfegir = new JButton("Afegir producte");
	private JLabel eQuantitat = new JLabel("Quantitat ");
	private JTextField tfQuantitat = new JTextField(2);
	private JLabel ePreu;
	private int numProductes, productesRestants;
	
	public CrearComanda(String nom, Client client, LlistaProductes llistaProductes, LlistaClients llistaClients){
		super(nom);
     
        boolean correcte=false;
        while(!correcte){
		try {
			String numProd = JOptionPane.showInputDialog("Quants productes voldrà afegir?");
			numProductes = Integer.parseInt(numProd);
			if (numProductes<=0) throw new NumberFormatException();
			correcte = true;
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Valor introduït no correcte!", "ERROR!",JOptionPane.WARNING_MESSAGE);
		}
        }
		
		
		productesRestants = numProductes;
		setSize(500,400);
		setResizable(false);
		JPanel esquerra = new JPanel();
		JPanel dreta = new JPanel();

		Container container = getContentPane();
		container.setLayout(new GridLayout(1,2));
		
		container.add(esquerra);
		container.add(dreta);
		
		lProductes = new JList<Producte>(llistaProductes.getLlistaProductes());
		lProductes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lProductes.setLayoutOrientation(JList.VERTICAL);

		JScrollPane scrollProductes = new JScrollPane();
		scrollProductes.setViewportView(lProductes);
		
		eMenu.setFont(new java.awt.Font("Erias Light ITC", 1, 25));
		eComanda.setFont(new java.awt.Font("Erias Light ITC", 1, 25));
		lProductes.setFont(new java.awt.Font("Calibri", 0, 20));
		
		esquerra.setLayout(new BoxLayout(esquerra, BoxLayout.PAGE_AXIS));
		esquerra.add(eMenu, BorderLayout.NORTH);
		esquerra.add(new JSeparator(SwingConstants.HORIZONTAL));
		esquerra.add(scrollProductes,BorderLayout.CENTER);
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
		DefaultListModel<Producte> list = new DefaultListModel<Producte>();
		lComanda = new JList<Producte>(list);
		lComanda.setFont(new java.awt.Font("Calibri", 0, 20));

		JScrollPane scrollComanda = new JScrollPane();
		scrollComanda.setViewportView(lComanda);
		ePreu = new JLabel("TOTAL   "+comanda.calcularPreu(client.isPreferent())+"€");
		eComanda.setAlignmentX(Component.CENTER_ALIGNMENT);
		lComanda.setAlignmentX(Component.CENTER_ALIGNMENT);
		eQuantitat.setAlignmentX(Component.CENTER_ALIGNMENT);
		ePreu.setAlignmentX(Component.CENTER_ALIGNMENT);
		dreta.add(eComanda, BorderLayout.NORTH);
		dreta.add(new JSeparator(SwingConstants.HORIZONTAL));
		dreta.add(scrollComanda, BorderLayout.NORTH);
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
						if (p instanceof Plat){
							if(!((Plat)p).esApte(client.getRestriccions())){
								 int resposta = JOptionPane.showConfirmDialog(null, "Atencio el plat que vol afegir és perillós per la seva salut.\nVol continuar?", "ATENCIO!", JOptionPane.YES_NO_OPTION);
								 if(resposta == JOptionPane.NO_OPTION){
									 JOptionPane.showMessageDialog(null, "No s'ha afegit el producte");
								 }
								 else{
									 productesRestants = productesRestants-quant;
									 comanda.afegirProducte(p, quant);
									 for (int cont=0; cont<quant;cont++){
										 list.addElement(p);
									 }
								 }
							}
							else{
								 productesRestants = productesRestants-quant;
								 comanda.afegirProducte(p, quant);
								 for (int cont=0; cont<quant;cont++){
									 list.addElement(p);
								 }
							}
						}
						else{
							productesRestants = productesRestants-quant;
							comanda.afegirProducte(p, quant);
							for (int cont=0; cont<quant;cont++){
								list.addElement(p);
							}
						}
						NumberFormat nf = NumberFormat.getInstance();
						nf.setMaximumFractionDigits(2);
						nf.setRoundingMode( RoundingMode.DOWN);
						ePreu.setText("TOTAL   "+nf.format(comanda.calcularPreu(client.isPreferent()))+"€");
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

	public JLabel geteProducte() {
		return eProducte;
	}

	
}
