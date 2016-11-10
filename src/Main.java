import java.util.Scanner;

public class Main {

    static Scanner teclat = new Scanner(System.in);
	private static Producte[] llistaProductes;
	private static Client[] llistaClients;
	private static int nClients, nProductes;
    
	public static void main(String[] args) {
		int opcioMenu;
		
		inicialitzaDades();
		mostraMenu();
		opcioMenu = teclat.nextInt();

		while (opcioMenu != 10){
			switch(opcioMenu) {
			case 1: afegirProducte();		break;
			case 2: eliminarProducte();		break;
			case 3: consultarProducte();	break;
			case 4: crearClient();			break;
			case 5: historialComandes();	break;
			case 6: eliminarClient();		break;
			case 7: afegirComanda();		break;
			case 8: eliminarComanda();		break;
			case 9: consultarComandes();	break;
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
		System.out.println("\n\t1. Afeigr nous productes (plat o beguda)");
		System.out.println("\t2. Eliminar producte");
		System.out.println("\t3. Consultar producte");
		System.out.println("\t4. Crear un nou client");
		System.out.println("\t5. Veure comandes d'un client");
		System.out.println("\t6. Crear nova comanda");
		System.out.println("\t10. Sortir");
		System.out.println("\n\t********************************************************");
		System.out.printf("\n\t\t\tIndica opcio:");
	}
	
	/**Mètode que inicialitza les dades del programa
	 * 
	 */
	public static void inicialitzaDades(){
		llistaProductes = new Producte[100];
		llistaClients = new Client[100];
		nClients = 0;
		nProductes = 0;
	}
	
	/**Mètode que permet afegir un producte a la llista (plat o beguda).
	 * 
	 */
	public static void afegirProducte(){
		int opcio;
		String nom;
		double preu;
		
		if (nProductes == llistaProductes.length)
			System.out.println("No es pot afegir cap producte més!");
		else{
			System.out.println("Indica que vol afegir: ");
			System.out.println("1. Plat\n2. Beguda");
			opcio = teclat.nextInt();
			while (opcio != 1 && opcio != 2){
				System.out.println("ERROR!");
				System.out.println("1. Plat\n2. Beguda");
				opcio = teclat.nextInt();
			}
			
			System.out.print("-Indica el nom: ");
			nom = teclat.nextLine();
			System.out.print("\n-Indica el preu: ");
			preu = teclat.nextDouble();		
			
			if (opcio == 1)
				afegirPlat(nom, preu);
			else
				afegirBeguda(nom, preu);
		}
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
		
		System.out.print("\nÉs aquest plat apte per celíacs? (SI/NO): ");
		if (teclat.nextLine().equalsIgnoreCase("SI") && cont<nRestriccions){
			restriccions[cont] = RestriccionsAlimentaries.CELIACS;
			cont++;
		}
		
		System.out.print("\nÉs aquest plat apte per al·lèrgics a la lactosa? (SI/NO): ");
		if (teclat.nextLine().equalsIgnoreCase("SI") && cont<nRestriccions){
			restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
			cont++;
		}
		
		System.out.print("\nÉs aquest plat apte per al·lèrgics als fruits secs? (SI/NO): ");
		if (teclat.nextLine().equalsIgnoreCase("SI") && cont<nRestriccions){
			restriccions[cont] = RestriccionsAlimentaries.ALERGISCFRUITSSECS;
			cont++;
		}	
		
		llistaProductes[nProductes] = new Plat(nom, nProductes+1, preu, restriccions);		
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
		alcohol = teclat.nextLine().equalsIgnoreCase("SI");		
		llistaProductes[nProductes]= new Beguda(nom, nProductes+1, preu, volum, alcohol);
		nProductes++;
	}
	
	/**Mètode que permet eliminar un producte. L'usuari introduirà el codi del producte
	 * 
	 */
	public static void eliminarProducte(){
		int codi, posicio;
		
		System.out.println("Indica el codi del producte que vol eliminar: ");
		codi = teclat.nextInt();
		posicio = buscaProducte(codi);
		while(posicio == -1){
			System.out.println("No s'ha trobat el producte, torna a intentar-ho: ");
			codi = teclat.nextInt();
			posicio = buscaProducte(codi);
		}
		
		for (int i=posicio; i<nProductes-1; i++){
			llistaProductes[i] = llistaProductes[i-1];
		}
		
		System.out.println("S'ha eliminat correctament el producte");
	}
	
	/**Mètode que permet consultar un producte. L'usuari introduirà el codi del producte
	 * 
	 */
	public static void consultarProducte(){
		int codi, posicio;
		
		mostraProductes();
		System.out.println("Indica el codi del producte que vol consultar: ");
		codi = teclat.nextInt();
		posicio = buscaProducte(codi);
		while(posicio == -1){
			System.out.println("No s'ha trobat el producte, torna a intentar-ho: ");
			codi = teclat.nextInt();
			posicio = buscaProducte(codi);
		}
		
		System.out.println(llistaProductes[posicio].toString());
	}
	
	/**Mètode que busca un producte a partir d'un codi, -1 en cas de no trobar-lo
	 * 
	 * @param codi codi del producte a buscar
	 * @return posició de la taula on es troba el producte, -1 en cas de no trobar-lo
	 */
	private static int buscaProducte(int codi){
		
		for (int i=0; i < nProductes; i++){
			if (llistaProductes[i].getCodiReferencia() == codi)
				return i;
		}
		return -1;
	}
	
	/**Mètode que busca un client a partir d'un codi, -1 en cas de no trobar-lo
	 * 
	 * @param codi identificador del client a buscar
	 * @return posició de la taula on es troba el client, -1 en cas de no trobar-lo
	 */
	private static int buscaClient(int codi){
		
		for (int i=0; i < nClients; i++){
			if (llistaClients[i].getIdentificador() == codi)
				return i;
		}
		return -1;
	}

	/**Mètode que mostra per pantalla el menú de restaurant
	 * 
	 */
	private static void mostraProductes(){
		int i;
		
		System.out.println("********************		[MENÚ]		  ********************");
		System.out.println("PLATS:");
		for (i=0; i < nProductes; i++){
			if (llistaProductes[i] instanceof Plat)
				System.out.println("-"+ llistaProductes[i].getNom()+ " (ref: "+ llistaProductes[i].codiReferencia+")");
		}
		
		System.out.println("BEGUDES:");
		for (i=0; i < nProductes; i++){
			if (llistaProductes[i] instanceof Beguda)
				System.out.println("-"+ llistaProductes[i].getNom()+ " (ref: "+ llistaProductes[i].codiReferencia+")");
		}
		System.out.println("**************************************************************");
	}
	
	public static void afegirComanda(int codi){
		int num, posicio, quantitat;
		Comanda c;
		
		System.out.println("Cuants productes voldras afegir a la comanda?") ;
		num=teclat.nextInt() ;
		c = new Comanda(num) ;
		
		for (int i=0; i<num; i++)
		{   
			System.out.println("Elegeix un producte de la llista") ;
			mostraProductes() ;
			codi = teclat.nextInt();
			posicio = buscaProducte(codi);
			while(posicio == -1){
				System.out.println("No s'ha trobat el producte, torna a intentar-ho: ");
				codi = teclat.nextInt();
				posicio = buscaProducte(codi);
			}
			System.out.println("Quants productes ("+llistaProductes[posicio].getNom()+") voldras afegir");
			quantitat = teclat.nextInt();
			c.afegirProducte(llistaProductes[posicio], quantitat);
		}
		System.out.println("La comanda s'ha realitzat amb exit");
	}
	
	public static void eliminarComanda()
	{
		
	}
	
	public static void consultarComanda()
	{
		
	}
	
	/** Demana totes les dades necessaries per a crear un client i crida al constructor de clients.
	 *  Afegeix el nou client a la llista de clients i incrementa el comptador de clients. **/
	public static void crearClient(){
		String nom, adreca, nomUsuari, contrasenya;
		int numTelefon, numRestriccions;
		RestriccionsAlimentaries[] restriccions;
		
		System.out.println("Introdueix el nom del nou client: ");
		nom=teclat.next();
		System.out.println("Introdueix un nom d'usuari: ");
		nomUsuari=teclat.next();
		System.out.println("Introdueix una contrasenya: ");
		contrasenya=teclat.next();
		System.out.println("Introdueix la teva adreca: ");
		adreca=teclat.next();
		System.out.println("Introdueix el teu numero de telefon: ");
		numTelefon=teclat.nextInt();
		System.out.println("Introdueix la quantitat de restriccions alimentaries [0,3]: ");
		numRestriccions=teclat.nextInt();
		
		restriccions=new RestriccionsAlimentaries[numRestriccions];
		for(int i=0; i<numRestriccions; i++){
			System.out.println("Escriu la restriccio numero "+ numRestriccions+1 +": ");
			restriccions[i]=RestriccionsAlimentaries.valueOf(teclat.next());
		}
		
		llistaClients[nClients]= new Client(nom, adreca, nomUsuari, contrasenya, nClients, numTelefon, restriccions);
		nClients++;
	}
	
	public static void eliminarClient(){
		int identificador, i;
		for(i=0; i<nClients-1; i++){
			System.out.println(llistaClients[i].getNomUsuari()+llistaClients[i].getIdentificador());
		}
		
		System.out.println("Escriu l'identificador del client que vulguis eliminar: ");
		identificador=teclat.nextInt();
		
		i=buscaClient(identificador);
		if (i==-1) System.out.println("No s'ha trobat el client.");
		else{
			for(; i<nClients-1; i++){
				llistaClients[i]=llistaClients[i+1];
			}
			nClients--;
		}
		
	}
	
	public static void consultarClient(){
		int i;
		for(i=0; i<nClients-1; i++){
			System.out.println(llistaClients[i].getNomUsuari()+llistaClients[i].getIdentificador());
		}
		
		System.out.println("Escriu l'identificador del client que vulguis consultar: ");
		i=teclat.nextInt();
		
		if(i==-1) System.out.println("No s'ha trobat el client.");
		else System.out.println(llistaClients[i]);
	}
	
	public static void historialComandes(){
		int i;
		for(i=0; i<nClients-1; i++){
			System.out.println(llistaClients[i].getNomUsuari()+llistaClients[i].getIdentificador());
		}
		
		System.out.println("Escriu l'identificador del client que vulguis consultar: ");
		i=teclat.nextInt();
		
		if(i==-1) System.out.println("No s'ha trobat el client.");
		else{
			for(int j=0; j<llistaClients[i].getNumComandes(); j++){
				System.out.println(llistaClients[i].getTaulaComandes()[j]);		//FALTA TOSTRING DE COMANDES
			}
		}
	}
	
}
