package Restaurant;
import Excepcions.NotFoundException;
import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Productes.Beguda;
import Productes.Plat;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

import java.io.*;

public class Main {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader teclat = new BufferedReader(isr);
	private static LlistaProductes llistaProductes;
	private static LlistaClients llistaClients;
	private static boolean llegit;
    
	public static void main(String[] args) {
		int opcioMenu=-1;
		
		inicialitzaDades();
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
			case 5: historialComandes(escullClient());	break;
			case 6: eliminarClient();					break;
			case 7: afegirComanda();					break;
			case 8: eliminarComanda();					break;
			case 9: consultarComanda();					break;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**Mètode que mostra per pantalla el menú del programa
	 * 
	 */
	public static void mostraMenu(){
		
		System.out.println("Premi enter per continuar.");
		try {
			teclat.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n\n\t****************    [TAKE A BYTE]    ****************");			//TAKE A bitE
		System.out.println("\n\t1. Afegir nous productes (plat o beguda)");
		System.out.println("\t2. Eliminar producte");
		System.out.println("\t3. Consultar producte");
		System.out.println("\t4. Crear un nou client");
		System.out.println("\t5. Veure comandes d'un client");
		System.out.println("\t6. Eliminar client");
		System.out.println("\t7. Afegir comanda");
		System.out.println("\t8. Eliminar comanda");
		System.out.println("\t9. Consultar comanda");
		System.out.println("\t10. Sortir");
		System.out.println("\n\t********************************************************");
		System.out.printf("\n\t\t\tIndica opcio:");
	}
	
	/**Mètode que inicialitza les dades del programa
	 * 
	 */
	public static void inicialitzaDades(){
		llistaClients = new LlistaClients(100);
		llistaProductes = new LlistaProductes(100);
		
		RestriccionsAlimentaries[] r= new RestriccionsAlimentaries[1];
		r[0] = RestriccionsAlimentaries.CELIACS;
		
		llistaProductes.afegirElement("iMac Flurry", 12, r);
		llistaProductes.afegirElement("SandBITx", 5, r);
		llistaProductes.afegirElement("Birra", 1, 123, true);
		llistaClients.afegirElement("Ruye", "pl pou", "buskk", "sergi1997", 97737804, r);
		
		RestriccionsAlimentaries[] r3= new RestriccionsAlimentaries[1];
		r3[0] = RestriccionsAlimentaries.ALERGISCFRUITSSECS;
		RestriccionsAlimentaries[] r5= new RestriccionsAlimentaries[2];
		r5[1] = RestriccionsAlimentaries.ALERGICSLACTOSA;
		r5[0] = RestriccionsAlimentaries.ALERGISCFRUITSSECS;
		llistaProductes.afegirElement("3.14zza", 3.14, r5);
		llistaProductes.afegirElement("Hidromiel", 2.99, 250, true);
		llistaClients.afegirElement("Javier Ortega Sánchez", "C/ de Salou", "javieros24", "abc123", 672695760, r3);
	
		RestriccionsAlimentaries[] r2 = new RestriccionsAlimentaries[1];
		r2[0]=RestriccionsAlimentaries.CELIACS;
		RestriccionsAlimentaries[] r4 = new RestriccionsAlimentaries[1];
		r4[0]=RestriccionsAlimentaries.ALERGISCFRUITSSECS;
		llistaProductes.afegirElement("Coliflor", 5.99, r4);
		llistaProductes.afegirElement("Ginlemon", 15.99, 100, true);
		llistaClients.afegirElement("Sergi Quevedo Garreta", "Calle falsa 123", "spiderman123", "Sergi1997", 622354987, r2);
	
		RestriccionsAlimentaries[] r1 = new RestriccionsAlimentaries[1] ;
		r1[0] = RestriccionsAlimentaries.ALERGICSLACTOSA ;
		llistaClients.afegirElement("Jeroni Molina Mellado", "Carrer de la piruleta", "molme", "123abc", 969696969, r1);
		llistaProductes.afegirElement("Cafè de Java", 3.99, 50, false);
		llistaProductes.afegirElement("Pa Int-egral", 6.99, r2);
	
		llistaProductes.afegirElement("JavaDog", 7, r2);
		llistaProductes.afegirElement("MACarró", 1.99, r2);
		llistaClients.afegirElement("Roger Bosch Mateo", "Ptda. Serres, Castellvell del Camp", "buskk", "swiWaitForVBlank", 977340793, r4);
	}
	
	/**Mètode que permet afegir un producte a la llista (plat o beguda).
	 * 
	 */
	public static void afegirProducte(){
		int opcio = -1;
		String nom = null;
		double preu = 0.0;
		
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
			
			if (opcio == 1)
				afegirPlat(nom, preu);
			else
				afegirBeguda(nom, preu);
			
			System.out.println("S'ha afegit el producte correctament.");
			
	}
	
	/**Mètode que afegeix un plat a la llista de productes
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
				if (cont<nRestriccions){
					System.out.println("És aquest plat apte per celíacs? SI o NO: ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("ERROR! Ha d'introduïr SI o NO:");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.CELIACS;
						cont++;
					}
				}
				
				if (cont<nRestriccions){
					System.out.println("És aquest plat apte per al·lèrgics a la lactosa? SI o NO: ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("ERROR! Ha d'introduïr SI o NO:");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
						cont++;
					}
				}
				
				if (cont<nRestriccions){
					System.out.println("És aquest plat apte per al·lèrgics als fruits secs? SI o NO: ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("ERROR! Ha d'introduïr SI o NO:");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.ALERGISCFRUITSSECS;
						cont++;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
			
		try {
			llistaProductes.afegirElement(nom, preu, restriccions);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! No es pot afegir cap més producte.");
		}
	}
	
	
	/**Mètode privat que afegiex una beguda a la llista de productes
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
			llistaProductes.afegirElement(nom, preu, volum, alcohol);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! No es pot afegir cap més producte.");
		}
	}
	
	/**Mètode que permet eliminar un producte. L'usuari introduirà el codi del producte
	 * 
	 */
	public static void eliminarProducte(){
		int codi;
		
		mostraProductes();
		
		llegit = false;
		while (!llegit) {
			try {
				System.out.println("Codi del producte que vol eliminar: ");
				codi = Integer.parseInt(teclat.readLine());
				llistaProductes.eliminarElement(codi);
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
	public static void consultarProducte(){
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
		System.out.println("PLATS:\t\t\t\tImport:\n");
		for (i=0; i < nProductes; i++){
			if (p[i] instanceof Plat)
				System.out.println("[ref: "+ p[i].getCodiReferencia()+"] -"+ p[i].getNom()+ " \t\t"+p[i].getPreu()+"€");
		}
		
		System.out.println("\nBEGUDES:\n");
		for (i=0; i < nProductes; i++){
			if (p[i] instanceof Beguda)
				System.out.println("[ref: "+ p[i].getCodiReferencia()+"] -"+ p[i].getNom()+ " \t\t"+p[i].getPreu()+"€");
		}
		System.out.println("\n****************************************************");
	}
	

	/** Afegeix una comanda a un client
	 * @param codiClient
	 */
	public static void afegirComanda(){
		int numMax = 0, posicio = -1, quantitat = -1, codiProducte;
		boolean continuar;
		double preu = 0;
		Producte[] p;
		Comanda c;
		Producte[] llista = llistaProductes.getLlistaProductes();
		Client client = escullClient();
				
		
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
		c = new Comanda(numMax);
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
				
				//calcular preu
				
				if (client.isPreferent()) preu = preu + quantitat*(llista[posicio].getPreu() - llista[posicio].getDescompte());
				else preu = preu + quantitat*(llista[posicio].getPreu());
			}
			//tornem al bucle per elegir un altre producte
		}	

		//mostrar i guardar comanda
		p = c.getLlista();
		for (int i=0; i<numMax; i++)
		{
			System.out.println((i+1)+". "+p[i].getNom());
		}
		System.out.println("TOTAL: "+preu+"€ (IVA inclòs).\nVol confirmar la comanda? SI o NO ");
		String s = null;
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
			client.afegirComanda(c);
			System.out.println("La comanda s'ha realitzat amb èxit! :D");
		}
		else System.out.println("Comanda cancelada... :(");
		
	}
	
	public static void eliminarComanda()
	{
		int num=-1;
		boolean llegit=false, llegit1=false;
		Client client = escullClient();
		while(!llegit1)
		{

			try {
				System.out.println("Esculli una comanda per eliminar entre 1 i "+client.getNumComandes() +".");
				historialComandes(client);
				num = Integer.parseInt(teclat.readLine());
				llegit1=true;
			} catch (NumberFormatException e) {					
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		while ((num > client.getNumComandes()) || (num <= 0))
		{
			try {
				System.out.println("ERROR! Ha d'escollir una comanda entre 1 i "+ client.getNumComandes()+".");
				num = Integer.parseInt(teclat.readLine());
			} catch (NumberFormatException e) {
				System.out.println("ERROR! Ha d'introduïr un número.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		while(!llegit)
		{
			try
			{
			client.eliminaComanda(num-1);
			llegit=true;
			}
			catch(NotFoundException e)
			{
				e.getMessage();
			}
		}
	}
	
	public static void consultarComanda()
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
	 *  Afegeix el nou client a la llista de clients i incrementa el comptador de clients. **/
	public static void crearClient(){
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
				System.out.println("És vostè celíac? SI o NO: ");
				cadena = teclat.readLine();
				while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
					System.out.println("ERROR! Ha d'introduïr SI o NO.");
					cadena = teclat.readLine();
				}
				if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.CELIACS;
						cont++;
				}
				
				if (cont<numRestriccions){
					System.out.println("És vostè al·lèrgic a la lactosa? SI o NO: ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("ERROR! Ha d'introduïr SI o NO.");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
						cont++;
					}
				}
				if (cont < numRestriccions){
					System.out.println("És vostè al·lèrgic als fruits secs? SI o NO: ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("ERROR! Ha d'introduïr SI o NO.");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.ALERGISCFRUITSSECS;
						cont++;
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			
			try {
				llistaClients.afegirElement(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions);
				System.out.println("S'ha creat correctament el client!");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("ERROR! No s'ha pogut crear el client. Llista plena.");
			}
	}
		
	
	
	
	public static void consultarClient(){
		
		Client c = escullClient();
		
		System.out.println(c.toString());
	}
	
	public static void historialComandes(Client c){	

		for(int j=0; j<c.getNumComandes(); j++){
			System.out.println(c.getTaulaComandes()[j]);
		}
	}
	
	private static Client escullClient(){
		int ref;
		boolean correcte=false;
		Client[] llista = llistaClients.getLlistaClients();
		Client c = null;
		
		for(int i=0; i<llistaClients.getnElements(); i++){
			System.out.println("Nom: "+llista[i].getNomUsuari()+" (Ref: "+llista[i].getIdentificador()+")");
		}
		
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
	
	private static void eliminarClient(){
		Client c=escullClient();
		
		try {
			llistaClients.eliminarElement(c.getIdentificador());
		} catch (NotFoundException e) {
			System.out.println("ERROR! No s'ha trobat l'element.");
		}
		
		System.out.println("S'ha eliminat el client correctament.");
	}	
}
