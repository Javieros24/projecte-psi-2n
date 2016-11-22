import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Main {

    static Scanner teclat2 = new Scanner(System.in);
    static InputStreamReader isr = new InputStreamReader(System.in) ;
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
			System.out.println("Valor no valid");
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
			default: System.out.println("Aquesta opció no està disponible, comproba que ha introduit correctament el valor desitjat.");
			}
			
			mostraMenu();
			
			try {
				opcioMenu = Integer.parseInt(teclat.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Valor no valid");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**Mètode que mostra per pantalla el menú del programa
	 * 
	 */
	public static void mostraMenu(){
		
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
				System.out.println("Indica que vol afegir: ");
				System.out.println("1. Plat\n2. Beguda");
				opcio = Integer.parseInt(teclat.readLine());
				while (opcio != 1 && opcio != 2){
					System.out.println("ERROR!");
					System.out.println("1. Plat\n2. Beguda");
					opcio = Integer.parseInt(teclat.readLine());
				}
				
				System.out.println("-Indica el nom:");
				nom = teclat.readLine();
				System.out.println("-Indica el preu:");
				preu = Double.parseDouble(teclat.readLine());
				llegit = true;
			} catch (NumberFormatException e) {
				System.out.println("Error, has d'introduïr un número!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
			if (opcio == 1)
				afegirPlat(nom, preu);
			else
				afegirBeguda(nom, preu);
		
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
			System.out.println("Per quants tipus de persones és apte el plat? 0,1,2,3 (Celíacs, al·lèrgics lactosa, al·lèrgics fruits secs)");
			nRestriccions = Integer.parseInt(teclat.readLine());
			while (nRestriccions > 3) {
				System.out.println("El valor ha de ser [0-3]");
				nRestriccions = Integer.parseInt(teclat.readLine());
			}
			restriccions = new RestriccionsAlimentaries[nRestriccions];
			llegit = true;
		} catch (NumberFormatException e) {
			System.out.println("Error, has d'introduïr un número!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NegativeArraySizeException e) {
			System.out.println("El número ha de serpositiu");
		}
		}
		
		if (nRestriccions != 0){		
			try {
				if (cont<nRestriccions){
					System.out.println("\nÉs aquest plat apte per celíacs? (SI/NO): ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("Error, has de introduïr SI/NO");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.CELIACS;
						cont++;
					}
				}
				
				if (cont<nRestriccions){
					System.out.println("\nÉs aquest plat apte per al·lèrgics a la lactosa? (SI/NO): ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("Error, has de introduïr SI/NO");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
						cont++;
					}
				}
				
				if (teclat.readLine().equalsIgnoreCase("SI") && cont<nRestriccions){
					System.out.println("\nÉs aquest plat apte per al·lèrgics als fruits secs? (SI/NO): ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("Error, has de introduïr SI/NO");
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
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error, no es ot afegir cap més producte");
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
			System.out.print("\n-Volum: ");
			volum = Integer.parseInt(teclat.readLine());
			System.out.print("\n- Alcohol SI/NO: ");
			a = teclat.readLine();
			while (!a.equalsIgnoreCase("SI") && !a.equalsIgnoreCase("NO")){
				System.out.println("Error, has de introduïr SI/NO");
				a = teclat.readLine();
			}
		} catch (NumberFormatException e) {
			System.out.println("Error, has d'introduïr un número!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		alcohol = a.equalsIgnoreCase("SI");
		
		try {
			llistaProductes.afegirElement(nom, preu, volum, alcohol);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Error, no es pot afegir cap més producte");
		}
	}
	
	/**Mètode que permet eliminar un producte. L'usuari introduirà el codi del producte
	 * 
	 */
	public static void eliminarProducte(){
		int codi;
		
		mostraMenu();
		
		llegit = false;
		while (!llegit) {
			try {
				System.out.println("Indica el codi del producte que vol eliminar: ");
				codi = Integer.parseInt(teclat.readLine());
				llistaProductes.eliminarElement(codi);
				System.out.println("S'ha eliminat correctament el producte");
				llegit = true;
			} catch (NumberFormatException e) {
				System.out.println("Error, has d'introduïr un número!");
			} catch (NotFoundException e){
				e.printStackTrace();
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
			System.out.println("Indica el codi del producte que vol consultar: ");
			codi = Integer.parseInt(teclat.readLine());
			System.out.println(llistaProductes.consultarElement(codi).toString());
			llegit = true;
		} catch (NumberFormatException e) {
			System.out.println("Error, has d'introduïr un número!");
		} catch (NotFoundException e) {
			e.printStackTrace();
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
		
		System.out.println("********************		[MENÚ]		  ********************");
		System.out.println("PLATS:");
		for (i=0; i < nProductes; i++){
			if (p[i] instanceof Plat)
				System.out.println("-"+ p[i].getNom()+ " (ref: "+ p[i].codiReferencia+") Preu: "+p[i].getPreu()+"€");
		}
		
		System.out.println("BEGUDES:");
		for (i=0; i < nProductes; i++){
			if (p[i] instanceof Beguda)
				System.out.println("-"+ p[i].getNom()+ " (ref: "+ p[i].codiReferencia+") Preu: "+p[i].getPreu()+"€");
		}
		System.out.println("**************************************************************");
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
				System.out.println("Cuants productes voldras afegir a la comanda?") ;
				numMax=teclat.read() ;
				while(numMax<=0){
					System.out.println("Error, elegeix un numero mes gran que 0:") ;
					numMax=Integer.parseInt(teclat.readLine()) ;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		//creem la comanda
		c = new Comanda(numMax) ;
		for (int i=0; i<numMax; i++)
		{   
			llegit = false;
			while (!llegit)
			{
				mostraProductes() ;
				try {
					System.out.println("Escull un producte de la llista:") ;
					codiProducte = Integer.parseInt(teclat.readLine());
					posicio = llistaProductes.buscarElement(codiProducte);
					llegit = true ;
				} 
				catch (NotFoundException e) {
					e.printStackTrace();
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
			
			//comprovar restriccions alimentaries del productes
			
			RestriccionsAlimentaries[] r ;	
			r = client.getRestriccions();
			
			continuar=true ;
			
			if (llista[posicio] instanceof Plat) {
				if (((Plat)llista[posicio]).esApte(r)==false)
				{
					String s = null;
					try {
						System.out.println("Atencio! El plat que ha escollit pot ser perillos per la seva salut, esta segur que vol continuar (si/no)");
						s = teclat.readLine();
						while ((!s.equalsIgnoreCase("SI")) && (!s.equalsIgnoreCase("NO")))
						{
							System.out.println("Error, voleu continuar amb el producte? (si/no)");
							s = teclat.readLine() ;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (s.equals("NO")) continuar = false ;
				}
			}
			if (continuar)
			{
				//demanem quantitat
				llegit = false;
				while (!llegit) {
					try {
						System.out.println("Escull una quantitat de (" + llista[posicio].getNom() + ") entre [1-"+ (numMax - c.getNumProd()) + "]");
						quantitat = Integer.parseInt(teclat.readLine()) ;
						while (((quantitat + c.getNumProd()) > numMax) || (quantitat <= 0)) {
							System.out.println("Error en la quantitat, elegeixi una quantitat entre [1-"+(numMax - c.getNumProd()) + "]");
							quantitat = Integer.parseInt(teclat.readLine());
						}
						llegit = true ;
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (IOException e){
						e.printStackTrace();
					}
					 
					
				}
				c.afegirProducte(llista[posicio], quantitat);		
				i = i + quantitat-1;
				
				//calcular preu
				
				if (client.isPreferent())
				{
					preu = preu + quantitat*(llista[posicio].preu - llista[posicio].descompte) ;
				}
				else 
				{
					preu = preu + quantitat*(llista[posicio].preu) ;
				}
			}
			//tornem al bucle per elegir un altre producte
			
		}

		//mostrar i guardar comanda
		p = c.getLlista() ;
		for (int i=0; i<numMax; i++)
		{
			System.out.println((i+1)+". "+p[i].getNom());
		}
		System.out.println("El preu final es "+preu+"€, confirmar la comanda? (si/no) ");
		String s = null;
		try {
			s = teclat.readLine();
			while ((!s.equalsIgnoreCase("SI") && (!s.equalsIgnoreCase("NO"))))
			{
				System.out.println("Error, confirmar la comanda? (si/no)");
				s = teclat.readLine() ;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (s.equalsIgnoreCase("si"))
		{
			client.afegirComanda(c);
			System.out.println("La comanda s'ha realitzat amb exit");
		}
		else System.out.println("Comanda cancelada");
		
	}
	
	public static void eliminarComanda()
	{
		int num=-1 ;
		boolean llegit=false, llegit1=false;
		Client client = escullClient();
		while(!llegit1)
		{

			try {
				System.out.println("Escull una comanda per eliminar entre [1-"+client.getNumComandes() +"]");
				historialComandes(client);
				num = Integer.parseInt(teclat.readLine());
				llegit1=true;
			} catch (NumberFormatException e) {					
				System.out.println("Error, numero no valid.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		while ((num > client.getNumComandes()) || (num <= 0))
		{
			try {
				System.out.println("Error, escull una comanda per consultar entre [1-"+ client.getNumComandes()+"]");
				num = Integer.parseInt(teclat.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Error, numero no valid.");
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
		int num=-1 ;
		
		Client client = escullClient();
		
		llegit=false;
		while(!llegit)
		{
				try {
					System.out.println("Escull una comanda per consultar entre [1-"+ client.getNumComandes()+"]");
					historialComandes(client) ;
					num = Integer.parseInt(teclat.readLine());
					llegit=true;
				} catch (NumberFormatException e) {
					System.out.println("Error, valor no valid.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		while ((num > client.getNumComandes()) || (num <= 0))
		{
			try {
				System.out.println("Error, elegeix una comanda per consultar entre [1-"+ client.getNumComandes()+"]");
				num = Integer.parseInt(teclat.readLine()) ;
			} catch (NumberFormatException e) {

				System.out.println("Error, valor no valid.");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		System.out.println("La comanda es la seguent");
		Comanda c=client.getTaulaComandes()[num-1] ;
		Producte[] p = c.getLlista() ;
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
		
		while(!correcte){
			try { 
				System.out.println("Introdueix el nom del nou client: ");
				nom = teclat.readLine();
				correcte=true;
			} catch (InputMismatchException e1) {
				System.out.println("ERROR: Nom incorrecte.");
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		correcte=false;
		while(!correcte){
			try {
				System.out.println("Introdueix un nom d'usuari: ");
				nomUsuari = teclat.readLine();
				correcte=true;
			} catch (InputMismatchException e1) {
				System.out.println("ERROR: Nom incorrecte.");
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		correcte=false;
		while(!correcte){
			try {
				System.out.println("Introdueix una contrasenya: ");
				contrasenya = teclat.readLine();
				correcte=true;
			} catch (InputMismatchException e1) {
				System.out.println("ERROR: Contrasenyan incorrecta.");
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		correcte=false;
		while(!correcte){
			try {
				System.out.println("Introdueix la teva adreca: ");
				adreca = teclat.readLine();
				correcte=true;
			} catch (InputMismatchException e1) {
				System.out.println("ERROR: Adreca incorrecta.");
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		correcte=false;
		while(!correcte){
			
				try {
					System.out.println("Introdueix el teu numero de telefon: ");
					numTelefon = Integer.parseInt(teclat.readLine());
					correcte=true;
				} catch (IOException e) {
					e.printStackTrace();
					
				} catch (NumberFormatException e1) {
					System.out.println("ERROR: Numero de telefon incorrecte.");
				}
			
		}
		correcte=false;
		while(!correcte){
			try {
				System.out.println("Introdueix la quantitat de restriccions alimentaries [0,3]: ");
				numRestriccions = Integer.parseInt(teclat.readLine());
				restriccions = new RestriccionsAlimentaries[numRestriccions];
				while (numRestriccions > 3) {
					System.out.println("El valor ha de ser [0-3]");
					numRestriccions = Integer.parseInt(teclat.readLine());
				}
				correcte=true;
			} catch (NumberFormatException e1) {
				System.out.println("ERROR: Nombre de restriccions incorrecte.");
			} catch (IOException e){
				e.printStackTrace();
			} catch (NegativeArraySizeException e){
				System.out.println("ERROR: Nombre de restriccions incorrecte.");
			}
		}
			
		if (numRestriccions != 0){
			try {
				System.out.println("\nÉs vostè celíac? (SI/NO): ");
				cadena = teclat.readLine();
				while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
					System.out.println("Error, has de introduïr SI/NO");
					cadena = teclat.readLine();
				}
				if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.CELIACS;
						cont++;
				}
				
				if (cont<numRestriccions){
					System.out.println("\nÉs vostè al·lergic a la lactosa? (SI/NO): ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("Error, has de introduïr SI/NO");
						cadena = teclat.readLine();
					}
					if (cadena.equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
						cont++;
					}
				}
				if (cont < numRestriccions){
					System.out.println("\nÉs vostè al·lergic als fruits secs? (SI/NO): ");
					cadena = teclat.readLine();
					while (!cadena.equalsIgnoreCase("SI") && !cadena.equalsIgnoreCase("NO")){
						System.out.println("Error, has de introduïr SI/NO");
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
				System.out.println("S'ha creat correctament el client");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("ERROR: No s'ha pogut crear el client. Llista plena.");
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
			System.out.println("Escriu l'identificador del client que vulguis consultar: ");
			ref=teclat.read();
			c = llistaClients.consultarElement(ref);
			correcte = true;
			} catch(InputMismatchException e){
				System.out.println("ERROR: Identificador incorrecte.");
			} catch (NotFoundException e) {
				e.printStackTrace();
			} catch (IOException e){
				
			}
		}
		return c;
	}
	
	private static void eliminarClient(){
		Client c=escullClient();
		
		try {
			llistaClients.eliminarElement(c.getIdentificador());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	
}
