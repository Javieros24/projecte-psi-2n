package Restaurant;
import Excepcions.DuplicatedNameException;
import Excepcions.NotFoundException;
import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Productes.Beguda;
import Productes.Plat;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

import java.io.*;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.StringTokenizer;

/**Classe principal que controla tota la excecució del programa
 * 
 * @author GRUP 10
 *
 */
public class Main {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader teclat = new BufferedReader(isr);
    
    /**Guarda tota la informació necessària del productes del restaurant
     */
	private static LlistaProductes llistaProductes;
	private static LlistaClients llistaClients;
	private static boolean llegit;
    
	/**Mètode principal que crida als mètodes pertinents per carregar la informació desde els fitxers, així com fer de menú
	 * interactiu del programa
	 */
	public static void main(String[] args) {
		int opcioMenu=-1;
		
		//inicialitza les dades
		carregarProductes();
		carregarClients();
		carregarComandes();
		mostraMenu();
		
		try {
			opcioMenu = Integer.parseInt(teclat.readLine());
		} catch (NumberFormatException e) {
			System.out.println("ERROR! Ha d'introduïr un número.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (opcioMenu != 10){
			switch(opcioMenu) {
			case 1: afegirProducte();					break;
			case 2: eliminarProducte();					break;
			case 3: consultarProducte();				break;
			case 4: crearClient();						break;
			case 5: consultarComanda();					break;
			case 6: consultarClient();					break;
			case 7: eliminarClient();					break;
			case 8: novaComanda();						break;
			case 9: eliminarComanda();					break;
			default: System.out.println("ERROR! Aquesta opció no està disponible, comprovi que ha introduït correctament el valor desitjat.");
			}
			mostraMenu();
			
			try {
				opcioMenu = Integer.parseInt(teclat.readLine());
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Gràcies per la seva visita! =P");
		try {
			teclat.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	/**Mètode que mostra per pantalla el menú del programa
	 * 
	 */
	private static void mostraMenu(){
		
		System.out.println("Premi enter per continuar.");
		try {
			teclat.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n\n\t****************    [TAKE A BYTE]    ****************");
		System.out.println("\n\t1. Afegir nous productes (plat o beguda)");
		System.out.println("\t2. Eliminar producte");
		System.out.println("\t3. Consultar producte");
		System.out.println("\t4. Crear un nou client");
		System.out.println("\t5. Veure comandes d'un client");
		System.out.println("\t6. Consultar un client");
		System.out.println("\t7. Eliminar client");
		System.out.println("\t8. Afegir nova comanda");
		System.out.println("\t9. Eliminar comanda");
		System.out.println("\t10. Sortir");
		System.out.println("\n\t********************************************************");
		System.out.printf("\n\t\t\tIndica opcio:");
	}
	
	/**Mètode que permet afegir un producte a la llista (plat o beguda).
	 * 
	 */
	private static void afegirProducte(){
		int opcio = -1;
		String nom = null;
		double preu = 0.0;
		
		llegit=false;
		while(!llegit){
			try {
				System.out.println("Que vol afegir? ");
				System.out.println("1. Plat\n2. Beguda");
				opcio = Integer.parseInt(teclat.readLine());
				while (opcio != 1 && opcio != 2){
					System.out.println("ERROR!");
					System.out.println("1. Plat\n2. Beguda");
					opcio = Integer.parseInt(teclat.readLine());
				}
				
				System.out.println("-Nom:");
				nom = teclat.readLine();
				System.out.println("-Preu:");
				preu = Double.parseDouble(teclat.readLine());
				llegit = true;
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
			if (opcio == 1) afegirPlat(nom, preu);
			else			afegirBeguda(nom, preu);
	}
	
	/**Mètode que afegeix un plat a la llista de productes preguntant al usuari tota la informació necessària
	 * 
	 * @param nom string amb el nom del plat
	 * @param preu valor flotant amb el preu del plat
	 */
	private static void afegirPlat(String nom, double preu){
		RestriccionsAlimentaries[] restriccions = null;
		int nRestriccions=0, cont=0;
		String cadena;
		
		llegit = false;
		while(!llegit){
			try {
				System.out.println("Per quants tipus de persones és apte el plat? 0, 1, 2 o 3 (Celíacs, al·lèrgics lactosa, al·lèrgics fruits secs).");
				nRestriccions = Integer.parseInt(teclat.readLine());
				while (nRestriccions > 3) {
					System.out.println("El valor ha de ser entre 0 i 3.");
					nRestriccions = Integer.parseInt(teclat.readLine());
				}
				restriccions = new RestriccionsAlimentaries[nRestriccions];
				llegit = true;
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NegativeArraySizeException e) {
				System.out.println("ERROR! El nombre ha de ser positiu.");
			}
		}
		
		if (nRestriccions != 0){		
			try {
				while (cont < nRestriccions) {
					if (cont < nRestriccions) {
						System.out.println("És aquest plat apte per celíacs? SI o NO: ");
						cadena = teclat.readLine();
						while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")) {
							System.out.println("ERROR! Ha d'introduïr SI o NO:");
							cadena = teclat.readLine();
						}
						if (cadena.equalsIgnoreCase("SI")) {
							restriccions[cont] = RestriccionsAlimentaries.CELIACS;
							cont++;
						}
					}
					if (cont < nRestriccions) {
						System.out.println("És aquest plat apte per al·lèrgics a la lactosa? SI o NO: ");
						cadena = teclat.readLine();
						while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")) {
							System.out.println("ERROR! Ha d'introduïr SI o NO:");
							cadena = teclat.readLine();
						}
						if (cadena.equalsIgnoreCase("SI")) {
							restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
							cont++;
						}
					}
					if (cont < nRestriccions) {
						System.out.println("És aquest plat apte per al·lèrgics als fruits secs? SI o NO: ");
						cadena = teclat.readLine();
						while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")) {
							System.out.println("ERROR! Ha d'introduïr SI o NO:");
							cadena = teclat.readLine();
						}
						if (cadena.equalsIgnoreCase("SI")) {
							restriccions[cont] = RestriccionsAlimentaries.ALERGICSFRUITSSECS;
							cont++;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
			
		try {
			guardarProducte(llistaProductes.afegirElement(nom, preu, restriccions));		//Guardem el producte al fitxer i a la llista
			System.out.println("S'ha afegit el producte correctament.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! No es pot afegir cap més producte.");
		} catch (DuplicatedNameException e) {
			System.out.println("ERROR! El producte que està intentant afegir ja existeix");
		}
	}
	
	
	/**Mètode que afegeix una beguda a la llista de productes preguntant al usuari tota la informació necessària
	 * 
	 * @param nom string amb el nom de la beguda
	 * @param preu valor flotant amb el preu de la beguda
	 */
	private static void afegirBeguda(String nom, double preu){
		boolean alcohol;
		int volum = 0;
		String a = null;
		
		try {
			System.out.println("-Volum: ");
			volum = Integer.parseInt(teclat.readLine());
			while (volum >= 0){
				System.out.println("ERROR! El volum ha de ser major de 0 ml");
				volum = Integer.parseInt(teclat.readLine());
			}
			System.out.println("-Alcohol SI/NO: ");
			a = teclat.readLine();
			while (!a.equalsIgnoreCase("SI") && !a.equalsIgnoreCase("NO")){
				System.out.println("ERROR! Ha d'introduïr SI o NO:");
				a = teclat.readLine();
			}
		} catch (NumberFormatException e) {
			System.out.println("ERROR! Ha d'introduïr un número.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		alcohol = a.equalsIgnoreCase("SI");
		
		try {
			guardarProducte(llistaProductes.afegirElement(nom, preu, volum, alcohol));		//Guardem el producte al fitxer i a la llista
			System.out.println("S'ha afegit el producte correctament.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! No es pot afegir cap més producte.");
		} catch (DuplicatedNameException e) {
			System.out.println("ERROR! El producte que està intentant afegir ja existeix");
		}
	}
	
	/**Mètode que permet eliminar un producte. L'usuari introduirà el codi del producte
	 * 
	 */
	private static void eliminarProducte(){
		int codi;
		
		mostraProductes();
		
		llegit = false;
		while (!llegit) {
			try {
				System.out.println("Codi del producte que vol eliminar: ");
				codi = Integer.parseInt(teclat.readLine());
				llistaProductes.eliminarElement(codi);
				sobreescriureProductes();	//Una vegada eliminat el producte guardem la informació al fitxer
				System.out.println("S'ha eliminat correctament el producte.");
				llegit = true;
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (NotFoundException e){
				System.out.println("ERROR! No s'ha trobat l'element.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**Mètode que permet consultar un producte. L'usuari introduirà el codi del producte
	 * 
	 */
	private static void consultarProducte(){
		int codi;
		
		mostraProductes();
		
		llegit = false;
		while(!llegit){
		try {
			System.out.println("Codi del producte que vol consultar: ");
			codi = Integer.parseInt(teclat.readLine());
			System.out.println(llistaProductes.consultarElement(codi).toString());
			llegit = true;
		} catch (NumberFormatException e) {
			System.out.println("ERROR! Ha d'introduïr un número.");
		} catch (NotFoundException e) {
			System.out.println("ERROR! No s'ha trobat l'element.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}	

	/**Mètode que mostra per pantalla el menú de restaurant
	 * 
	 */
	private static void mostraProductes(){
		int i;
		Producte[] p;
		int nProductes;
		
		p = llistaProductes.getLlistaProductes();
		nProductes = llistaProductes.getnElements();
		
		System.out.println("\n\n***********************[MENÚ]***********************\n");
		System.out.println("PLATS:\t\t\t\t\tImport:\n");
		for (i=0; i < nProductes; i++){
			if (p[i] instanceof Plat)
				System.out.println("[ref: "+ p[i].getCodiReferencia()+"] -"+ p[i].getNom()+ " \t\t\t"+p[i].getPreu()+"€");
		}
		
		System.out.println("\nBEGUDES:\n");
		for (i=0; i < nProductes; i++){
			if (p[i] instanceof Beguda)
				System.out.println("[ref: "+ p[i].getCodiReferencia()+"] -"+ p[i].getNom()+ " \t\t\t"+p[i].getPreu()+"€");
		}
		System.out.println("\n****************************************************");
	}
	
	/**Mètode que et dona a elegir entre copiar una comanda o fer una nova
	 */
	public static void novaComanda()
	{
		Client client = escullClient();
		String s = null;
		if (client.hiHaComandes()) 
		{	
			try {
				System.out.println("Vol tornar a demanar una comanda feta anteriorment? SI o NO");
				s = teclat.readLine();
				while ((!s.equalsIgnoreCase("SI")) && (!s.equalsIgnoreCase("NO")))
				{
					System.out.println("ERROR! Contesti SI o NO");
					s = teclat.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (s.equalsIgnoreCase("SI")) copiarComanda(client);
			else afegirComanda(client);
		}
		else afegirComanda(client);	
	}

	/**Metode que et eprmet copiar una comanda de la llista de comandes del client
	 * @param client Client que vol copiar la comanda
	 */
	public static void copiarComanda(Client client)
	{
		int ref=-1;
		boolean llegit=false;
		Comanda c = null;	
		Comanda copia;
		while(!llegit)
		{
			try {
				System.out.println("Esculli una de les comandes per a copiar: ");
				historialComandes(client);
				ref = Integer.parseInt(teclat.readLine());
				c = client.buscaComanda(ref);
				llegit=true;
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NotFoundException e) {
				System.out.println("ERROR! No s'ha trobat l'element.");
			}	
		}
		copia=client.copiar(c, llistaClients.referenciaComanda());
		finalitzarComanda(client,copia);
		
	}
	
	/**Metode que et pregunta totes les dades necessaries per a poder crear una nova comanda
	 * @param client Client que vol fer una nova comanda
	 */
	public static void afegirComanda(Client client){
		int numMax = 0, posicio = -1, quantitat = -1, codiProducte;
		boolean continuar;
		Comanda c;
		Producte[] llista = llistaProductes.getLlistaProductes();
				
		
		//preguntem el numero de productes que tindra la comanda
		llegit = false;
		while (!llegit)
		{
			try {
				System.out.println("Quants productes voldrà afegir a la comanda?");
				numMax=Integer.parseInt(teclat.readLine());
				while(numMax<=0){
					System.out.println("ERROR! Ha d'escollir un valor més gran de 0:");
					numMax=Integer.parseInt(teclat.readLine());
				}
				llegit=true;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			}
		}
			
		//creem la comanda
		int codiRef = llistaClients.referenciaComanda();
		c = new Comanda(numMax, codiRef);
		for (int i=0; i<numMax; i++)
		{   
			llegit = false;
			while (!llegit)
			{
				mostraProductes();
				try {
					System.out.println("Esculli un producte de la llista:");
					codiProducte = Integer.parseInt(teclat.readLine());
					posicio = llistaProductes.buscarElement(codiProducte);
					llegit = true;
				} 
				catch (NotFoundException e) {
					System.out.println("ERROR! No s'ha trobat l'element.");
				}
				catch (NumberFormatException e) {
					System.out.println("ERROR! Ha d'introduïr un número.");
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
			
			//comprovar restriccions alimentaries del productes
			
			RestriccionsAlimentaries[] r;	
			r = client.getRestriccions();
			
			continuar=true;
			
			if (llista[posicio] instanceof Plat) {
				if (((Plat)llista[posicio]).esApte(r)==false)
				{
					String s = null;
					try {
						System.out.println("Atenció! El plat que ha escollit pot ser perillós per la seva salut, està segur que vol continuar? SI o NO");
						s = teclat.readLine();
						while ((!s.equalsIgnoreCase("SI")) && (!s.equalsIgnoreCase("NO")))
						{
							System.out.println("ERROR! Vol continuar amb el producte? SI o NO");
							s = teclat.readLine();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (s.equals("NO")) continuar = false;
				}
			}
			if (continuar)
			{
				//demanem quantitat
				llegit = false;
				while (!llegit) {
					try {
						System.out.println("Esculli una quantitat de " + llista[posicio].getNom() + " entre 1 i "+ (numMax - c.getNumProd()) + ".");
						quantitat = Integer.parseInt(teclat.readLine());
						while (((quantitat + c.getNumProd()) > numMax) || (quantitat <= 0)) {
							System.out.println("ERROR! Ha d'escollir una quantitat entre 1 i "+(numMax - c.getNumProd()) + ".");
							quantitat = Integer.parseInt(teclat.readLine());
						}
						llegit = true;
					} catch (NumberFormatException e) {
						System.out.println("ERROR! Ha d'introduïr un número.");
					} catch (IOException e){
						e.printStackTrace();
					}	
				}
				c.afegirProducte(llista[posicio], quantitat);		
				i = i + quantitat-1;
			}
			//tornem al bucle per elegir un altre producte
		}
		
		finalitzarComanda(client,c);	
	}
	
	/**Metode que mostra la comanda realitzada i demana confirmació per part de l'usuari
	 * @param client Client que vol fer la comanda
	 * @param c Comanda que es vol guardar
	 */
	public static void finalitzarComanda(Client client, Comanda c)
	{
		Producte[] p;
		String s = null;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setRoundingMode( RoundingMode.DOWN);
		//mostrar comanda
		p = c.getLlista();
		
		System.out.println("\n***********[COMANDA]***********");
		System.out.println("Producte:\t\tPreu:");
		System.out.println("-------------------------------");
		for (int i=0; i<c.getNumProd(); i++)
		{
			System.out.println((i+1)+". "+p[i].getNom()+"\t\t"+p[i].getPreu());
		}
		System.out.println("-------------------------------");
		if (client.isPreferent()) 
		{
			System.out.println("TOTAL SENSE DESCOMPTE "+nf.format(c.calcularPreu(false))+"€ (IVA inclòs)\nTOTAL AMB DESCOMPTE "+nf.format(c.calcularPreu(client.isPreferent()))+"€ (IVA inclòs)");
		}
		else System.out.println("TOTAL "+nf.format(c.calcularPreu(client.isPreferent()))+"€ (IVA inclòs)");
		System.out.println("********************************");
		System.out.println("Vol confirmar la comanda? SI o NO"); 
		try {
			s = teclat.readLine();
			while ((!s.equalsIgnoreCase("SI") && (!s.equalsIgnoreCase("NO"))))
			{
				System.out.println("ERROR! Vol confirmar la comanda? SI o NO");
				s = teclat.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (s.equalsIgnoreCase("si"))
		{
			//guardar comanda
			guardarComanda(client.getIdentificador(), client.afegirComanda(c));
			System.out.println("La comanda s'ha realitzat amb èxit! :D");
		}
		else System.out.println("Comanda cancelada... :(");
		
	}
	
	/**Mètode que et permet eliminar una comanda de la llista de comandes d'un client
	 */
	private static void eliminarComanda()
	{
		int ref=-1;;
		boolean llegit=false;
		Client client = escullClient();
		while(!llegit)
		{
			try {
				System.out.println("Esculli una de les comandes per a eliminar: ");
				historialComandes(client);
				ref = Integer.parseInt(teclat.readLine());
				client.buscaComanda(ref);
				llegit=true;
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NotFoundException e) {
				System.out.println("ERROR! No s'ha trobat l'element.");
			}	
		}
		try {
			client.eliminaComanda(ref);
			sobreescriureComandes();
			System.out.println("\nS'ha eliminat la comanda correctament.");
		} catch (NotFoundException e) {
			System.out.println("ERROR! No s'ha trobat l'element.");
		}	
	}
	
	/**Mètode que et permet veure la llista de productes d'una determinada comanda d'un client
	 */
	private static void consultarComanda()
	{
		int ref=-1;	
		Client client = escullClient();
		Comanda c=null;
		
		llegit=false;
		while(!llegit)
		{
			try {
				System.out.println("Esculli una de les comandes: ");
				historialComandes(client);
				ref = Integer.parseInt(teclat.readLine());
				c = client.buscaComanda(ref);
				llegit=true;
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NotFoundException e) {
				System.out.println("ERROR! No s'ha trobat l'element.");
			}	
		}
		System.out.println("Informació de la comanda:");
		Producte[] p = c.getLlista();
		for (int j=0; j<c.getNumProd(); j++)
		{
			System.out.println((j+1)+". "+p[j].getNom());
		}
	}
	
	/** Demana totes les dades necessaries per a crear un client i crida al constructor de clients.
	 *  Afegeix el nou client a la llista de clients i incrementa el comptador de clients. 
	 */
	private static void crearClient(){
		String nom=null, adreca=null, nomUsuari=null, contrasenya=null;
		int numTelefon=0, numRestriccions=0, cont=0;
		RestriccionsAlimentaries[] restriccions=null;
		boolean correcte=false;
		String cadena = null;
		
		System.out.println("Introdueixi les següents dades: ");
		while(!correcte){
			try { 
				System.out.println("-Nom: ");
				nom = teclat.readLine();
				correcte=true;
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		correcte=false;
		while(!correcte){
			try {
				System.out.println("-Nom d'usuari: ");
				nomUsuari = teclat.readLine();
				correcte=true;
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		correcte=false;
		while(!correcte){
			try {
				System.out.println("-Contrasenya: ");
				contrasenya = teclat.readLine();
				correcte=true;
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		correcte=false;
		while(!correcte){
			try {
				System.out.println("-Adreça: ");
				adreca = teclat.readLine();
				correcte=true;
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		correcte=false;
		while(!correcte){
			
				try {
					System.out.println("-Número de telèfon: ");
					numTelefon = Integer.parseInt(teclat.readLine());
					correcte=true;
				} catch (IOException e) {
					e.printStackTrace();
					
				} catch (NumberFormatException e1) {
					System.out.println("ERROR! Ha d'introduïr un número.");
				}
			
		}
		correcte=false;
		while(!correcte){
			try {
				System.out.println("Introdueixi la quantitat de restriccions alimentaries entre 0 i 3: ");
				numRestriccions = Integer.parseInt(teclat.readLine());
				restriccions = new RestriccionsAlimentaries[numRestriccions];
				while (numRestriccions > 3) {
					System.out.println("El valor ha d'estar entre 0 i 3.");
					numRestriccions = Integer.parseInt(teclat.readLine());
				}
				correcte=true;
			} catch (NumberFormatException e1) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e){
				e.printStackTrace();
			} catch (NegativeArraySizeException e){
				System.out.println("ERROR! El nombre ha de ser positiu.");
			}
		}
			
		if (numRestriccions != 0){
			try {
				while (cont < numRestriccions) {
					System.out.println("És vostè celíac? SI o NO: ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")) {
						System.out.println("ERROR! Ha d'introduïr SI o NO.");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.CELIACS;
						cont++;
					}
					if (cont < numRestriccions) {
						System.out.println("És vostè al·lèrgic a la lactosa? SI o NO: ");
						cadena = teclat.readLine();
						while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")) {
							System.out.println("ERROR! Ha d'introduïr SI o NO.");
							cadena = teclat.readLine();
						}
						if (cadena.equalsIgnoreCase("SI")) {
							restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
							cont++;
						}
					}
					if (cont < numRestriccions) {
						System.out.println("És vostè al·lèrgic als fruits secs? SI o NO: ");
						cadena = teclat.readLine();
						while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")) {
							System.out.println("ERROR! Ha d'introduïr SI o NO.");
							cadena = teclat.readLine();
						}
						if (cadena.equalsIgnoreCase("SI")) {
							restriccions[cont] = RestriccionsAlimentaries.ALERGICSFRUITSSECS;
							cont++;
						}
					} 
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			
			try {
				guardarClient(llistaClients.afegirElement(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions));
				System.out.println("S'ha creat correctament el client!");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("ERROR! No s'ha pogut crear el client. Llista plena.");
			} catch (DuplicatedNameException e) {
				System.out.println("ERROR! El producte que està intentant afegir ja existeix");
			}
	}
	
	/**Mostra les dades d'un determinat client
	 */
	private static void consultarClient(){
		Client c = escullClient();
		System.out.println(c.toString());
	}
	
	/**Mostra totes les comandes realitzades per el client passat epr parametre
	 * @param c Client del qual es mostran totes les comandes realitzades per ell
	 */
	private static void historialComandes(Client c){	

		for(int j=0; j<c.getNumComandes(); j++){
			System.out.println(c.getTaulaComandes()[j]);
		}
	}
	
	/**Mètode que demana a l'usuari a escollir un client a la llista de clients del programa
	 * @return client Retorna el client escollit
	 */
	private static Client escullClient(){
		int ref;
		boolean correcte=false;
		Client[] llista = llistaClients.getLlistaClients();
		Client c = null;
		
		System.out.println("\n******************************************");
		System.out.println("Referència\t\tNom d'usuari");
		System.out.println("------------------------------------------");
		for(int i=0; i<llistaClients.getnElements(); i++){
			System.out.println("[ "+llista[i].getIdentificador()+" ]\t\t\t"+llista[i].getNomUsuari());
		}
		System.out.println("******************************************");
		
		while(!correcte){
			try{
			System.out.println("Escrigui l'identificador del client: ");
			ref=Integer.parseInt(teclat.readLine());
			c = llistaClients.consultarElement(ref);
			correcte = true;
			} catch(NumberFormatException e){
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (NotFoundException e) {
				System.out.println("ERROR! No s'ha trobat l'element.");
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		return c;
	}
	
	/**
	 * Mètode que elimina un client de la llista de clients
	 */
	private static void eliminarClient(){
		Client c=escullClient();
		
		try {
			llistaClients.eliminarElement(c.getIdentificador());
			sobreescriureClients();
			System.out.println("S'ha eliminat el client correctament.");
		} catch (NotFoundException e) {
			System.out.println("ERROR! No s'ha trobat l'element.");
		}
	}	
	
//***************************	MÈTODES PER TREBALLAR DESDE FITXERS	  ***************************
	/**
	 * Mètode que carrega els productes (plats i begudes) desde un fitxer 
	 */
	private static void carregarProductes(){
		
		llistaProductes = new LlistaProductes(100);		//Creem la llista de productes
		try {
			BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Productes.txt"));
			String linia;
			
			//Variables necessàries per crear un plat o una beguda
			StringTokenizer token;
			String tipus, nom;
			double preu;
			int ref, volum;
			RestriccionsAlimentaries[] r;
			boolean alcohol;

			linia = lectura.readLine();
			while (linia != null) {
				try {
					token = new StringTokenizer(linia, ",");
					tipus = token.nextToken();
					nom = token.nextToken();
					preu = Double.parseDouble(token.nextToken());
					ref = Integer.parseInt(token.nextToken());
					if (tipus.equalsIgnoreCase("PLAT")) {
						r = new RestriccionsAlimentaries[Integer.parseInt(token.nextToken())];
						for (int i = 0; i < r.length; i++) {
							r[i] = RestriccionsAlimentaries.valueOf(token.nextToken());
						}
						llistaProductes.afegirElement(nom, preu, r, ref);
					} else if (tipus.equalsIgnoreCase("BEGUDA")) {
						volum = Integer.parseInt(token.nextToken());
						alcohol = token.nextToken().equalsIgnoreCase("SI");
						llistaProductes.afegirElement(nom, preu, volum, alcohol, ref);
					}
					linia = lectura.readLine();
				} catch (IllegalArgumentException e) {
					/*No mostrem cap missatge per no molestar al usuari i no afegim aquell producte a la llista
					 * la següent vegada que carreguem els productes ja no existirà aquesta línia que causa error.
					 */
					linia=lectura.readLine();		//Llegim la seguent línia
				} catch (DuplicatedNameException e) {
					//En cas d'existir un producte amb aquell nom, no s'afegeix a la llista
					linia = lectura.readLine();//Llegim la seguent línia
				}
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR! No s'ha trobat el fitxer per carregar els productes");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! La llista de productes és plena.");
		} 
	}
	
	/**
	 * Mètode que carrega els clients desde un fitxer 
	 */
	private static void carregarClients(){
		
		llistaClients = new LlistaClients(100);
		try {
			BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Clients.txt"));
			String linia;
			StringTokenizer token;
			String nom, adreca, nomUsuari, contrasenya;
			int codiClient, numTelefon, numRestriccions;
			RestriccionsAlimentaries[] r;
			
			linia = lectura.readLine();
			while(linia != null){
				token = new StringTokenizer(linia, ",");
				nom = token.nextToken();
				codiClient = Integer.parseInt(token.nextToken());
				adreca = token.nextToken();
				nomUsuari = token.nextToken();
				contrasenya = token.nextToken();
				numTelefon = Integer.parseInt(token.nextToken());
				numRestriccions = Integer.parseInt(token.nextToken());
				r = new RestriccionsAlimentaries[numRestriccions];
				for (int i=0; i < r.length; i++){
					r[i] = RestriccionsAlimentaries.valueOf(token.nextToken());
				}
				llistaClients.afegirElement(nom, adreca, nomUsuari, contrasenya, numTelefon, r, codiClient);
				linia = lectura.readLine();
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! La llista de clients és plena.");
		}
		}                        

	/**
	 * Mètode que carrega les comandes realitzades per un client desde un fitxer 
	 */
	private static void carregarComandes(){
	
	try {
		BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Comandes.txt"));
		String linia, horaComanda;
		StringTokenizer token;
		Client client=null;
		Comanda c;
		Producte p;
		int refClient, refComanda, refProducte,numProd;
		linia = lectura.readLine();
		while(linia != null){
			try {
				token = new StringTokenizer(linia, ",");
				refClient = Integer.parseInt(token.nextToken());
				client=llistaClients.consultarElement(refClient);
				refComanda = Integer.parseInt(token.nextToken());
				horaComanda = token.nextToken();
				numProd = Integer.parseInt(token.nextToken());
				c=new Comanda(numProd,refComanda, horaComanda);
				
				for(int i=0;i<numProd;i++)
				{
					refProducte = Integer.parseInt(token.nextToken());
					p=llistaProductes.consultarElement(refProducte);
					c.afegirProducte(p, 1);
				}
				client.afegirComanda(c);
				
				linia = lectura.readLine();
			} catch (NotFoundException e) {
				linia = lectura.readLine();
				//Si un producte de la llista ha estat eliminat, aquella comanda no s'afegeix
			}
		}
		lectura.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	} catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("ERROR! La llista de productes és plena.");
	}
	}
	
	/**Mètode que guarda al final del fitxer el producte passat per paràmetre. Aquesta funció
	 * és cridada cada vegada que s'afegeix un producte a la llista
	 * 
	 * @param p producte que serà afegit al fitxer
	 */
	private static void guardarProducte(Producte p){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Productes.txt", true));	//true per escriure al final del fitxer
			
			//Diferenciem entre Plat i Beguda
			if (p instanceof Plat){
				escriptura.write("PLAT,");
				escriptura.write(p.getNom()+",");
				escriptura.write(p.getPreu()+",");
				escriptura.write(p.getCodiReferencia()+",");
				escriptura.write(String.valueOf(((Plat) p).getRestriccions().length));
				RestriccionsAlimentaries r[] = ((Plat) p).getRestriccions();
				for (int i=0; i < r.length; i++)
					escriptura.write(","+r[i]);
				escriptura.write("\n");
			}
			else if (p instanceof Beguda){
				escriptura.write("BEGUDA,");
				escriptura.write(p.getNom()+",");
				escriptura.write(p.getPreu()+",");
				escriptura.write(p.getCodiReferencia()+",");
				escriptura.write(((Beguda) p).getVolum()+",");
				if (((Beguda) p).getAlcohol())
					escriptura.write("SI\n");
				else
					escriptura.write("NO\n");
			}
			escriptura.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**Mètode que guarda al final del fitxer el client passat per paràmetre. Aquesta funció
	 * és cridada cada vegada que es crea un client
	 * @param c Client que es vol afegir al fitxer
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
	/**Mètode que guarda al final del fitxer la comanda passada per paràmetre. Aquesta funció
	 * és cridada cada vegada que es crea una comanda
	 * @param RefClient Referencia del client per saber qui ha fet la comanda
	 * @param c Comanda creada per el client
	 */
	private static void guardarComanda(int RefClient, Comanda c){
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Comandes.txt", true));
			Producte[] llista = c.getLlista();
			escriptura.write(RefClient+",");
			escriptura.write(c.getCodiComanda()+",");
			escriptura.write(String.valueOf(c.getNumProd()));
			for(int i=0;i<c.getNumProd();i++){
				escriptura.write("," + llista[i].getCodiReferencia());
			}
			escriptura.write(c.getHoraComanda()+"\n");
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		} //nullPointerException
	}
	
	/**Mètode que sobreescriu el fitxer de productes i guarda la llista de productes actual del programa.
	 * Aquest mètode és cridat cada vegada que s'elimina un producte.
	 */
	private static void sobreescriureProductes(){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Productes.txt"));	//Creem un nou fitxer buit
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Producte[] llista = llistaProductes.getLlistaProductes();
		for(int i=0; i<llistaProductes.getnElements();i++){
			guardarProducte(llista[i]);			//Cridem a la funció que guarda producte a producte dintre del fitxer,aquesta vegada amb un fitxer buit
		}
	}
	
	/**Mètode que sobreescriu el fitxer de comandes i guarda les comandes actuals del programa.
	 * Aquest mètode és cridat cada vegada que s'elimina una comanda.
	 */
	private static void sobreescriureComandes(){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Comandes.txt"));
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Client[] client=llistaClients.getLlistaClients();
		Comanda[] llistaComandes;
		for(int j=0; j<llistaClients.getnElements(); j++){
			llistaComandes = client[j].getTaulaComandes();
			for(int i=0; i<client[j].getNumComandes();i++){
				guardarComanda(client[j].getIdentificador(), llistaComandes[i]);
			}
		}
	}
	
	/**Mètode que sobreescriu el fitxer de clients i guarda els clients actuals del programa.
	 * Aquest mètode és cridat cada vegada que s'elimina un client.
	 */
	private static void sobreescriureClients(){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Clients.txt"));
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Client[] llista = llistaClients.getLlistaClients();
		for(int i=0; i<llistaClients.getnElements();i++){
			guardarClient(llista[i]);
		}
	}
}
