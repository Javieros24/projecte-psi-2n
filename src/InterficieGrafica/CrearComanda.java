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

/**Classe que exten JFrame i que permet crear una nova comanda d'un usuari en concret
 * 
 * @author GRUP 10
 *
 */
public class CrearComanda extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	private JLabel eMenu = new JLabel("MENÚ");
	private JLabel eComanda = new JLabel("COMANDA");
	private JLabel eProductesRestants = new JLabel();
	private JList<Producte> lProductes;
	private JList<Producte> lComanda;
	private JButton bAfegir = new JButton("Afegir producte");
	private JButton bCancelar = new JButton("Cancelar comanda");
	private JLabel eQuantitat = new JLabel("Quantitat ");
	private JTextField tfQuantitat = new JTextField(2);
	private JLabel ePreu;
	private int numProductes, productesRestants;
	
	/**Constructor que crea una finestra que permet crear una nova comanda
	 * 
	 * @param nom String amb el nom de la finestra
	 * @param client variable de tipus Client que és el que vol afegir la comanda
	 * @param llistaProductes llista dels productes disponibles
	 * @param llistaClients llista dels clients
	 */
	public CrearComanda(String nom, Client client, LlistaProductes llistaProductes, LlistaClients llistaClients){
		super(nom);
     
        boolean correcte=false;
        while(!correcte){
			try {
				String numProd = JOptionPane.showInputDialog("Quants productes voldrà afegir?");
				numProductes = Integer.parseInt(numProd);
				if (numProductes<=0) throw new NumberFormatException();
				productesRestants = numProductes;
				eProductesRestants.setText(productesRestants+" productes restants en la comanda");
				correcte = true;
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Valor introduït no correcte!", "ERROR!",JOptionPane.WARNING_MESSAGE);
			}
        }
		
        //La finestra es divideix entre esquerra i dreta (esquerra menú i dreta comanda)
		JPanel esquerra = new JPanel();
		JPanel dreta = new JPanel();
		
		Container container = getContentPane();
		//Afegim al contenidor principal els panells esquerra i dreta
		container.setLayout(new GridLayout(1,2));
		container.add(esquerra);
		container.add(dreta);
		
		//Creem llista de tipus producte amb scroll que contindrà el menú
		lProductes = new JList<Producte>(llistaProductes.getLlistaProductes());
		lProductes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lProductes.setLayoutOrientation(JList.VERTICAL);
		JScrollPane scrollProductes = new JScrollPane();
		scrollProductes.setViewportView(lProductes);
		
		//Creem una llista de tipus producte en la que se li podràn afegir els elements un a un
		DefaultListModel<Producte> list = new DefaultListModel<Producte>();
		lComanda = new JList<Producte>(list);
		JScrollPane scrollComanda = new JScrollPane();
		scrollComanda.setViewportView(lComanda);
		
		//Configuracio esquerra
		esquerra.setLayout(new BoxLayout(esquerra, BoxLayout.PAGE_AXIS));
		
		esquerra.add(eMenu, BorderLayout.NORTH);
		esquerra.add(new JSeparator(SwingConstants.HORIZONTAL));
		esquerra.add(scrollProductes,BorderLayout.CENTER);
		JPanel pQuantitat = new JPanel();
		pQuantitat.setLayout(new FlowLayout());
		pQuantitat.add(bAfegir);
		pQuantitat.add(eQuantitat);
		pQuantitat.add(tfQuantitat);
		pQuantitat.add(eProductesRestants);
		
		esquerra.add(pQuantitat);
		
		eMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		lProductes.setAlignmentX(Component.CENTER_ALIGNMENT);
		bAfegir.setAlignmentX(Component.CENTER_ALIGNMENT);
		eProductesRestants.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Configuració dreta
		dreta.setLayout(new BoxLayout(dreta,BoxLayout.PAGE_AXIS));
		Comanda comanda = new Comanda(numProductes, llistaClients.referenciaComanda());
		
		ePreu = new JLabel("TOTAL   "+comanda.calcularPreu(client.isPreferent())+"€");

		dreta.add(eComanda, BorderLayout.NORTH);
		dreta.add(new JSeparator(SwingConstants.HORIZONTAL));
		dreta.add(scrollComanda, BorderLayout.NORTH);
		dreta.add(ePreu);
		JLabel eUsuari = new JLabel("Has iniciat sessió com "+client.getNomUsuari());
		JPanel pUsuari = new JPanel();
		pUsuari.setLayout(new BoxLayout(pUsuari,BoxLayout.PAGE_AXIS ));
		pUsuari.add(bCancelar);
		pUsuari.add(eUsuari);
		dreta.add(pUsuari);
		
		eComanda.setAlignmentX(Component.CENTER_ALIGNMENT);
		lComanda.setAlignmentX(Component.CENTER_ALIGNMENT);
		eQuantitat.setAlignmentX(Component.CENTER_ALIGNMENT);
		ePreu.setAlignmentX(Component.CENTER_ALIGNMENT);
		bCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
		eUsuari.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Configuració de la lletra
		eMenu.setFont(new java.awt.Font("Erias Light ITC", 1, 25));
		eComanda.setFont(new java.awt.Font("Erias Light ITC", 1, 25));
		lProductes.setFont(new java.awt.Font("Calibri", 0, 20));
		lComanda.setFont(new java.awt.Font("Calibri", 0, 20));
		ePreu.setFont(new java.awt.Font("Calibri", 0, 20));
        //Configuració de la finestra
		setSize(600,400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Listeners que afegeix el producte de la llista seleccionat a la comanda
		bAfegir.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				int i= lProductes.getSelectedIndex();
				try {
					Producte p = llistaProductes.getLlistaProductes()[i];
					int quant = Integer.parseInt(tfQuantitat.getText());
					if (quant<=0 || quant>productesRestants)
						JOptionPane.showMessageDialog(null, "La quantitat no és correcte! Afegeix entre 1 i "+productesRestants+" productes", "ERROR!",JOptionPane.WARNING_MESSAGE);
					else{
						if (p instanceof Plat){			//Els plats poden tenir restriccions alimentaries
							if(!((Plat)p).esApte(client.getRestriccions())){	//Si no és apte
								 int resposta = JOptionPane.showConfirmDialog(null, "Atencio el plat que vol afegir és perillós per la seva salut.\nVol continuar?", "ATENCIO!", JOptionPane.YES_NO_OPTION);
								 
								 if(resposta == JOptionPane.NO_OPTION)	JOptionPane.showMessageDialog(null, "No s'ha afegit el producte");
								 else{
									 productesRestants = productesRestants-quant;
									 comanda.afegirProducte(p, quant);		//Afegim el producte a la comanda
									 for (int cont=0; cont<quant;cont++)	
										 list.addElement(p);				//Afegim el producte a la llista que veu l'usuari	
								 }
							}
							else{		//Si és apte al plat s'afegeix el producte sense preguntar res
								 productesRestants = productesRestants-quant;
								 comanda.afegirProducte(p, quant);
								 for (int cont=0; cont<quant;cont++)
									 list.addElement(p);
							}
						}
						else{		//Si es una beguda la afegim sense preguntar res més
							productesRestants = productesRestants-quant;
							comanda.afegirProducte(p, quant);
							for (int cont=0; cont<quant;cont++)
								list.addElement(p);
						}
						//Trunquem el valor mostrat del preu de la comanda 
						NumberFormat nf = NumberFormat.getInstance();
						nf.setMaximumFractionDigits(2);
						nf.setRoundingMode( RoundingMode.DOWN);
						ePreu.setText("TOTAL   "+nf.format(comanda.calcularPreu(client.isPreferent()))+"€");	//Actualitzem preu
						eProductesRestants.setText(productesRestants+" productes restants en la comanda");		//Actualitzem productes restants
						if (productesRestants == 0){		//En cas d'haver acabat la comanda preguntem al usuari si la desitja afegir o no
							setVisible(false);
							int continuar = JOptionPane.showConfirmDialog(null, "Vol confirmar la comanda?", "ATENCIO!", JOptionPane.YES_NO_OPTION);
							if(continuar == JOptionPane.NO_OPTION){
								JOptionPane.showMessageDialog(null, "No s'ha afegit la comanda");
								new Menu("Menu", client, llistaProductes, llistaClients);
							 }
							else{
								client.afegirComanda(comanda);
								guardarComanda(client.getIdentificador(), comanda);
								JOptionPane.showMessageDialog(null,"S'ha creat correctament la comanda!");
								new Menu("Menu", client, llistaProductes, llistaClients);
							}
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
		
		//Listener que cancela la comanda actual i torna al menú principal
		bCancelar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int cancelar = JOptionPane.showConfirmDialog(null, "Està segur que vol cancelar la comanda?","Cancelar comanda",JOptionPane.YES_NO_OPTION);
				
				if (cancelar == JOptionPane.YES_OPTION){
					setVisible(false);
					new Menu("Menu", client, llistaProductes, llistaClients);
				}
			}
		});
	}
	
	/**Mètode que guarda al final del fitxer la comanda passada per paràmetre.
	 * 
	 * @param c comanda que serà afegit al fitxer
	 * @param refClient referència del client que ha afegit la comanda
	 */
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
