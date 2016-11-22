import java.util.Scanner;

public class Main {

    static Scanner teclat = new Scanner(System.in);
	private static LlistaProductes llistaProductes;
	private static LlistaClients llistaClients;
	private static boolean llegit;
    
	public static void main(String[] args) {
		int opcioMenu;
		
		inicialitzaDades();
		mostraMenu();
		opcioMenu = teclat.nextInt();

		while (opcioMenu != 10){
			switch(opcioMenu) {
			case 1: afegirProducte();					break;
			case 2: eliminarProducte();					break;
			case 3: consultarProducte();				break;
			case 4: crearClient();						break;
			case 5: historialComandes(escullClient());	break;
			case 6: eliminarClient(escullClient());		break;
			case 7: afegirComanda(escullClient());		break;
			case 8: eliminarComanda(escullClient());	break;
			case 9: consultarComanda(escullClient());	break;
			default: System.out.println("Aquesta opció no està disponible, comproba que ha introduit correctament el valor desitjat.");
			}
			
			mostraMenu();
			opcioMenu = teclat.nextInt();
		}
	}
	
	/**Mètode que mostra per pantalla el menú del programa
	 * 
	 */
	public static void mostraMenu(){
		System.out.print("\nPulsi enter per mostrar el menú\n\n");
		teclat.nextLine(); 			//Buidem el buffer del teclat
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

	}
	
	/**Mètode que permet afegir un producte a la llista (plat o beguda).
	 * 
	 */
	public static void afegirProducte(){
		int opcio;
		String nom;
		double preu;
		
			System.out.println("Indica que vol afegir: ");
			System.out.println("1. Plat\n2. Beguda");
			opcio = teclat.nextInt();
			while (opcio != 1 && opcio != 2){
				System.out.println("ERROR!");
				System.out.println("1. Plat\n2. Beguda");
				opcio = teclat.nextInt();
			}
			
			System.out.println("-Indica el nom:");
			nom = teclat.next();
			System.out.println("-Indica el preu:");
			preu = teclat.nextDouble();		
			
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
		RestriccionsAlimentaries[] restriccions;
		int nRestriccions, cont=0;
		
		System.out.println("Per quants tipus de persones es apte el plat? 0,1,2,3 (Celíacs, al·lèrgics lactosa, al·lèrgics fruits secs)");
		nRestriccions = teclat.nextInt();
		restriccions = new RestriccionsAlimentaries[nRestriccions];
		
		if (nRestriccions == 0){
			llistaProductes.afegirElement(nom, preu, restriccions);
		}
		else{						
			if (cont<nRestriccions){
				System.out.println("\nÉs aquest plat apte per celíacs? (SI/NO): ");
				if (teclat.next().equalsIgnoreCase("SI")) {
					restriccions[cont] = RestriccionsAlimentaries.CELIACS;
					cont++;
				}
			}
			
			if (cont<nRestriccions){
				System.out.println("\nÉs aquest plat apte per al·lèrgics a la lactosa? (SI/NO): ");
				if (teclat.next().equalsIgnoreCase("SI")) {
					restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
					cont++;
				}
			}
			
			if (teclat.nextLine().equalsIgnoreCase("SI") && cont<nRestriccions){
				System.out.println("\nÉs aquest plat apte per al·lèrgics als fruits secs? (SI/NO): ");
				if (teclat.next().equalsIgnoreCase("SI")) {
					restriccions[cont] = RestriccionsAlimentaries.ALERGISCFRUITSSECS;
					cont++;
				}
			}	
			
			llistaProductes.afegirElement(nom, preu, restriccions);
		}
	}
	
	/**Mètode privat que afegiex una beguda a la llista de productes
	 * 
	 * @param nom string amb el nom de la beguda
	 * @param preu valor flotant amb el preu de la beguda
	 */
	private static void afegirBeguda(String nom, double preu){
		boolean alcohol;
		int volum;
		
		System.out.print("\n-Volum: ");
		volum = teclat.nextInt();
		System.out.print("\n- Alcohol SI/NO: ");
		alcohol = teclat.next().equalsIgnoreCase("SI");		
		llistaProductes.afegirElement(nom, preu, volum, alcohol);
		}
	
	/**Mètode que permet eliminar un producte. L'usuari introduirà el codi del producte
	 * 
	 */
	public static void eliminarProducte(){
		int codi;
		
		mostraMenu();
		System.out.println("Indica el codi del producte que vol eliminar: ");
		codi = teclat.nextInt();
		llistaProductes.eliminarElement(codi);
		
		System.out.println("S'ha eliminat correctament el producte");
	}
	
	/**Mètode que permet consultar un producte. L'usuari introduirà el codi del producte
	 * 
	 */
	public static void consultarProducte(){
		int codi;
		
		mostraProductes();
		System.out.println("Indica el codi del producte que vol consultar: ");
		codi = teclat.nextInt();
		System.out.println(llistaProductes.consultarElement(codi).toString());
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
	public static void afegirComanda(int codiClient){
		int numMax, posicio = -1, quantitat = -1, codiProducte;
		boolean continuar;
		double preu = 0;
		Producte[] p;
		Comanda c;
		Producte[] llista = llistaProductes.getLlistaProductes();
		
		//preguntem el numero de productes que tindra la comanda
		System.out.println("Cuants productes voldras afegir a la comanda?") ;
		numMax=teclat.nextInt() ;
		while(numMax<=0){
			System.out.println("Error, elegeix un numero mes gran que 0:") ;
			numMax=teclat.nextInt() ;
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
					codiProducte = teclat.nextInt();
					posicio = llistaProductes.buscarElement(codiProducte);
					llegit = true ;
				} 
				catch (NotFoundException e) {
					e.printStackTrace();
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
			//comprovar restriccions alimentaries del productes
			RestriccionsAlimentaries[] r= llistaClients.consultarElement(codiClient).getRestriccions();
			continuar=true ;
			
			if (llista[posicio] instanceof Plat) {
				if (((Plat)llista[posicio]).esApte(r)==false)
				{
					System.out.println("Atencio! El plat que ha escollit pot ser perillos per la seva salut, esta segur que vol continuar (si/no)");
					String s = teclat.next() ;
					while ((!s.equalsIgnoreCase("SI")) && (!s.equalsIgnoreCase("NO")))
					{
						System.out.println("Error, voleu continuar amb el producte? (si/no)");
						s = teclat.next() ;
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
						quantitat = teclat.nextInt() ;
						while (((quantitat + c.getNumProd()) > numMax) || (quantitat <= 0)) {
							System.out.println("Error en la quantitat, elegeixi una quantitat entre [1-"+(numMax - c.getNumProd()) + "]");
							quantitat = teclat.nextInt();
						}
						llegit = true ;
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} 
				}
				c.afegirProducte(llista[posicio], quantitat);		
				i = i + quantitat-1;
				
				//calcular preu
				
				if (llistaClients.consultarElement(codiClient).isPreferent()==true)
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
		String s = teclat.next() ;
		while ((!s.equalsIgnoreCase("SI") && (!s.equalsIgnoreCase("NO"))))
		{
			System.out.println("Error, confirmar la comanda? (si/no)");
			s = teclat.next() ;
		}
		if (s.equalsIgnoreCase("si"))
		{
			llistaClients.consultarElement(codiClient).afegirComanda(c);
			System.out.println("La comanda s'ha realitzat amb exit");
		}
		else System.out.println("Comanda cancelada");
		
	}
	
	public static void eliminarComanda(int codiClient)
	{
		int num ;
		System.out.println("Escull una comanda per eliminar entre [1-"+llistaClients.consultarElement(codiClient).getNumComandes() +"]");
		historialComandes(codiClient) ;
		num = teclat.nextInt() ;
		while ((num > llistaClients.consultarElement(codiClient).getNumComandes()) || (num <= 0))
		{
			System.out.println("Error, escull una comanda per consultar entre [1-"+ llistaClients.consultarElement(codiClient).getNumComandes()+"]");
			num = teclat.nextInt() ;
		}
		llistaClients.consultarElement(codiClient).eliminaComanda(num-1);
	}
	
	public static void consultarComanda(int codiClient)
	{
		int num ;
		int i = buscaClient(codiClient) ;

		System.out.println("Escull una comanda per consultar entre [1-"+ llistaClients[i].getNumComandes()+"]");
		historialComandes(codiClient) ;
		num = teclat.nextInt() ;
		while ((num > llistaClients[i].getNumComandes()) || (num <= 0))
		{
			System.out.println("Error, elegeix una comanda per consultar entre [1-"+ llistaClients[i].getNumComandes()+"]");
			num = teclat.nextInt() ;
		}
		System.out.println("La comanda es la seguent");
		Comanda c=llistaClients[i].getTaulaComandes()[num-1] ;
		Producte[] p = c.getLlista() ;
		for (int j=0; j<c.getNumProd(); j++)
		{
			System.out.println((j+1)+". "+p[j].getNom());
		}
	}
	
	/** Demana totes les dades necessaries per a crear un client i crida al constructor de clients.
	 *  Afegeix el nou client a la llista de clients i incrementa el comptador de clients. **/
	public static void crearClient(){
		String nom, adreca, nomUsuari, contrasenya;
		int numTelefon, numRestriccions, cont=0;
		RestriccionsAlimentaries[] restriccions;
		
		if (nClients == llistaClients.length)
			System.out.println("No s'ha pogut crear un nou client, llista plena!");
		else{
			System.out.println("Introdueix el nom del nou client: ");
			nom = teclat.next();
			System.out.println("Introdueix un nom d'usuari: ");
			nomUsuari = teclat.next();
			System.out.println("Introdueix una contrasenya: ");
			contrasenya = teclat.next();
			System.out.println("Introdueix la teva adreca: ");
			adreca = teclat.next();
			System.out.println("Introdueix el teu numero de telefon: ");
			numTelefon = teclat.nextInt();
			System.out.println("Introdueix la quantitat de restriccions alimentaries [0,3]: ");
			numRestriccions = teclat.nextInt();
			restriccions = new RestriccionsAlimentaries[numRestriccions];
			if (numRestriccions == 0){
				llistaClients.afegirElement(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions);
				System.out.println("S'ha creat correctament el client");
			}
			else{						
				if (cont<numRestriccions){
					System.out.println("\nÉs vostè celíac? (SI/NO): ");
					if (teclat.next().equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.CELIACS;
						cont++;
					}
				}
				
				if (cont<numRestriccions){
					System.out.println("\nÉs voste al·lèrgic a la lactosa? (SI/NO): ");
					if (teclat.next().equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
						cont++;
					}
				}
				
				if (teclat.nextLine().equalsIgnoreCase("SI") && cont<numRestriccions){
					System.out.println("\nÉs vostè al·lèrgic als fruits secs? (SI/NO): ");
					if (teclat.next().equalsIgnoreCase("SI")) {
						restriccions[cont] = RestriccionsAlimentaries.ALERGISCFRUITSSECS;
						cont++;
					}
				}
				llistaClients.afegirElement(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions);
				System.out.println("S'ha creat correctament el client");
			}
		}	
	}
	
	
	public static void consultarClient(){
		
		int codiClient = escullClient();
		
		System.out.println(llistaClients.consultarElement(codiClient).toString());
	}
	
	public static void historialComandes(){	
		
		int codiClient = escullClient();
		Client c = llistaClients.consultarElement(codiClient);

		for(int j=0; j<c.getNumComandes(); j++){
			System.out.println(c.getTaulaComandes()[j]);		//FALTA TOSTRING DE COMANDES
		}
	}
	
	private static int escullClient(){
		int i;
		Client[] llista = llistaClients.getLlistaClients();
		
		for(i=0; i<llistaClients.getnElements(); i++){
			System.out.println("Nom: "+llista[i].getNomUsuari()+" (Ref: "+llista[i].getIdentificador()+")");
		}
		
		System.out.println("Escriu l'identificador del client que vulguis consultar: ");
		i=teclat.nextInt();
		while (buscaClient(i) == -1){
			System.out.println("ERROR! No s'ha trobat el client, torna a intentar-ho: ");
			i = teclat.nextInt();
		}
		
		return i;
	}
	
}
