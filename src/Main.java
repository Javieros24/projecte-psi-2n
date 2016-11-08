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
			
			switch (opcio){
			case 1:
				//DEMANAR ENUMS
				llistaProductes[nProductes] = new Plat(nom, nProductes+1, preu);
				break;
			case 2:
				boolean alcohol= false;
				System.out.print("\n-Volum: ");
				int volum = teclat.nextInt();
				System.out.print("\n- Alcohol SI/NO: ");
				String portaAlcohol = teclat.nextLine();
				alcohol = portaAlcohol.equalsIgnoreCase("SI");
				llistaProductes[nProductes]= new Beguda(nom, nProductes+1, preu, volum, alcohol);
				nProductes++;
				break;
			}
		}
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
	
	public static void afegirComanda(){
		int num,codi,posicio,quantitat;
		Comanda c;
		
		System.out.println("Cuants productes voldras afegir a la comanda?") ;
		num=teclat.nextInt() ;
		c = new Comanda(num) ;
		
		for (int i=0; i<num; i++)
		{   
			System.out.println("Elegeix un producte de la llista") ;
			mostrarProductes() ;
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
		String nom, adreça, nomUsuari, contrasenya;
		int numTelefon;
		RestriccionsAlimentaries restriccions;
		
		System.out.println("Introdueix el nom del nou client: ");
		nom=teclat.next();
		System.out.println("Introdueix un nom d'usuari: ");
		nomUsuari=teclat.next();
		System.out.println("Introdueix una contrasenya: ");
		contrasenya=teclat.next();
		System.out.println("Introdueix la teva adreça: ");
		adreça=teclat.next();
		System.out.println("Introdueix el teu numero de telefon: ");
		numTelefon=teclat.nextInt();
		
		llistaClients[nClients]= new Client(nom, adreça, nomUsuari, contrasenya, nClients, numTelefon, restriccions);
		nClients++;
	}
	
	public static void eliminarClient(){
		
	}
	
	public static void consultarClient(){
		System.out.println("");
	}
	
	public static void historialComandes(){
		
	}
}
