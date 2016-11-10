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
			case 1: afegirProducte();					break;
			case 2: eliminarProducte();					break;
			case 3: consultarProducte();				break;
			case 4: crearClient();						break;
			case 5: historialComandes(escullClient());	break;
			case 6: eliminarClient(escullClient());		break;
			case 7: afegirComanda(escullClient());		break;
			case 8: eliminarComanda(escullClient());	break;
			case 9: consultarComanda(escullClient());	break;
			default: System.out.println("Aquesta opci� no est� disponible, comproba que ha introduit correctament el valor desitjat.");
			}
			
			mostraMenu();
			opcioMenu = teclat.nextInt();
		}
	}
	
	/**M�tode que mostra per pantalla el men� del programa
	 * 
	 */
	public static void mostraMenu(){
		System.out.print("\nPulsi enter per mostrar el men�\n\n");
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
	
	/**M�tode que inicialitza les dades del programa
	 * 
	 */
	public static void inicialitzaDades(){
		llistaProductes = new Producte[100];
		llistaClients = new Client[100];
		nClients = 0;
		nProductes = 0;
		
		Beguda p = new Beguda("Un Macarro", 12, 120, true);
		llistaProductes[0] = p;
		nProductes++;
		RestriccionsAlimentaries[] res;
		
		res = new RestriccionsAlimentaries[1];
		res[0] = RestriccionsAlimentaries.CELIACS;
		Client c = new Client("Roger", "HOla", "buskk", "1234", 977, res);
		llistaClients[0] = c;
		nClients++;
	}
	
	/**M�tode que permet afegir un producte a la llista (plat o beguda).
	 * 
	 */
	public static void afegirProducte(){
		int opcio;
		String nom;
		double preu;
		
		
		if (nProductes == llistaProductes.length)
			System.out.println("No es pot afegir cap producte m�s!");
		else{
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
	}
	
	/**M�tode que afegeix un plat a la llista de productes
	 * 
	 * @param nom string amb el nom del plat
	 * @param preu valor flotant amb el preu del plat
	 */
	private static void afegirPlat(String nom, double preu){
		RestriccionsAlimentaries[] restriccions;
		int nRestriccions, cont=0;
		
		System.out.println("Per quants tipus de persones es apte el plat? 0,1,2,3 (Cel�acs, al�l�rgics lactosa, al�l�rgics fruits secs)");
		nRestriccions = teclat.nextInt();
		restriccions = new RestriccionsAlimentaries[nRestriccions];
		
		if (nRestriccions == 0){
			llistaProductes[nProductes] = new Plat(nom, preu, restriccions);
			nProductes++;
		}
		else{			
			teclat.nextLine(); 
			

			if (cont<nRestriccions){
				System.out.println("\n�s aquest plat apte per cel�acs? (SI/NO): ");
				if (teclat.nextLine().equalsIgnoreCase("SI")) {
					restriccions[cont] = RestriccionsAlimentaries.CELIACS;
					cont++;
				}
			}
			

			if (cont<nRestriccions){
				System.out.println("\n�s aquest plat apte per al�l�rgics a la lactosa? (SI/NO): ");
				if (teclat.nextLine().equalsIgnoreCase("SI")) {
					restriccions[cont] = RestriccionsAlimentaries.ALERGICSLACTOSA;
					cont++;
				}
			}
			
			
			if (teclat.nextLine().equalsIgnoreCase("SI") && cont<nRestriccions){
				System.out.println("\n�s aquest plat apte per al�l�rgics als fruits secs? (SI/NO): ");
				if (teclat.nextLine().equalsIgnoreCase("SI")) {
					restriccions[cont] = RestriccionsAlimentaries.ALERGISCFRUITSSECS;
					cont++;
				}
			}	
			
			llistaProductes[nProductes] = new Plat(nom, preu, restriccions);
			nProductes++;
		}
	}
	
	/**M�tode privat que afegiex una beguda a la llista de productes
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
		llistaProductes[nProductes]= new Beguda(nom, preu, volum, alcohol);
		nProductes++;
	}
	
	/**M�tode que permet eliminar un producte. L'usuari introduir� el codi del producte
	 * 
	 */
	public static void eliminarProducte(){
		int codi, posicio;
		
		mostraMenu();
		System.out.println("Indica el codi del producte que vol eliminar: ");
		codi = teclat.nextInt();
		posicio = buscaProducte(codi);
		while(posicio == -1){
			System.out.println("No s'ha trobat el producte, torna a intentar-ho: ");
			codi = teclat.nextInt();
			posicio = buscaProducte(codi);
		}
		
		for (int i=posicio; i<nProductes-1; i++){
			llistaProductes[i] = llistaProductes[i+1];
		}
		nProductes--;
		
		System.out.println("S'ha eliminat correctament el producte");
	}
	
	/**M�tode que permet consultar un producte. L'usuari introduir� el codi del producte
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
	
	/**M�tode que busca un producte a partir d'un codi, -1 en cas de no trobar-lo
	 * 
	 * @param codi codi del producte a buscar
	 * @return posici� de la taula on es troba el producte, -1 en cas de no trobar-lo
	 */
	private static int buscaProducte(int codi){
		
		for (int i=0; i < nProductes; i++){
			if (llistaProductes[i].getCodiReferencia() == codi)
				return i;
		}
		return -1;
	}
	
	/**M�tode que busca un client a partir d'un codi, -1 en cas de no trobar-lo
	 * 
	 * @param codi identificador del client a buscar
	 * @return posici� de la taula on es troba el client, -1 en cas de no trobar-lo
	 */
	private static int buscaClient(int codi){
		
		for (int i=0; i < nClients; i++){
			if (llistaClients[i].getIdentificador() == codi)
				return i;
		}
		return -1;
	}

	/**M�tode que mostra per pantalla el men� de restaurant
	 * 
	 */
	private static void mostraProductes(){
		int i;
		
		System.out.println("********************		[MEN�]		  ********************");
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
	
	/**public static int buscaComanda(int codiClient,int codiComanda)
	{
		Comanda c;
		Comanda[] tc;
		
		tc=llistaClients[buscaClient(codiClient)].getTaulaComandes() ;
		for (int i=0; i < llistaClients[buscaClient(codiClient)].getNumComandes(); i++)
		{
			c=tc[i] ;
			if (codiComanda == c.getCodiComanda()) return i;
		}
		return -1;
	}
	
	/** Afegeix una comanda a un client
	 * @param codiClient
	 */
	public static void afegirComanda(int codiClient){
		int numMax, posicio, quantitat, codiProducte;
		boolean continuar;
		double preu = 0;
		Producte[] p;
		Comanda c;
		
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
			mostraProductes() ;
			System.out.println("Elegeix un producte de la llista:") ;
			codiProducte = teclat.nextInt();
			posicio = buscaProducte(codiProducte);
			while(posicio == -1){
				System.out.println("No s'ha trobat el producte, torna a intentar-ho: ");
				codiProducte = teclat.nextInt();
				posicio = buscaProducte(codiProducte);
			}
			
			//comprovar restriccions alimentaries del productes
			RestriccionsAlimentaries[] r=llistaClients[buscaClient(codiClient)].getRestriccions();	
			continuar=true ;
			if (llistaProductes[posicio] instanceof Plat) {
				if (((Plat)llistaProductes[posicio]).esApte(r)==false)
				{
				
					System.out.println("Atencio! El plat que ha escollit pot ser perillos per la seva salut, esta segur que vol continuar (si/no)");
					String s = teclat.next() ;
					while ((!s.equalsIgnoreCase("SI") && (!s.equalsIgnoreCase("NO"))))
					{
						System.out.println("Error, voleu continuar amb el producte? (si/no)");
						s = teclat.next() ;
					}
					if (s.equals("NO")) continuar = false ;
				}
			}
			if (continuar)
			{
				System.out.println("Escull una quantitat de ("+llistaProductes[posicio].getNom()+") entre [1-"+(numMax - c.getNumProd())+"]");
				quantitat = teclat.nextInt();
				
				while (((quantitat + c.getNumProd()) > numMax ) || (quantitat <= 0))
				{
					System.out.println("Error en la quantitat, elegeixi una quantitat entre [1-"+(numMax - c.getNumProd())+"]");
					quantitat = teclat.nextInt();
				}
				c.afegirProducte(llistaProductes[posicio], quantitat);		
				i = i + quantitat-1;
				
				//calcular preu
				
				if (llistaClients[buscaClient(codiClient)].isPreferent()==true)
				{
					preu = preu + quantitat*(llistaProductes[posicio].preu - llistaProductes[posicio].descompte) ;
				}
				else 
				{
					preu = preu + quantitat*(llistaProductes[posicio].preu) ;
				}
			}
			
		}

		//mostrar i guardar comanda
		p = c.getLlista() ;
		for (int i=0; i<numMax; i++)
		{
			System.out.println((i+1)+". "+p[i].getNom());
		}
		System.out.println("El preu final es "+preu+"�, confirmar la comanda? (si/no) ");
		String s = teclat.next() ;
		while ((!s.equalsIgnoreCase("SI") && (!s.equalsIgnoreCase("NO"))))
		{
			System.out.println("Error, confirmar la comanda? (si/no)");
			s = teclat.next() ;
		}
		if (s.equalsIgnoreCase("si"))
		{
			llistaClients[buscaClient(codiClient)].afegirComanda(c);
			System.out.println("La comanda s'ha realitzat amb exit");
		}
		else System.out.println("Comanda cancelada");
		
	}
	
	public static void eliminarComanda(int codiClient)
	{
		int i = buscaClient(codiClient) ;
		int num ;
		System.out.println("Escull una comanda per eliminar entre [1-"+ llistaClients[i].getNumComandes()+"]");
		historialComandes(codiClient) ;
		num = teclat.nextInt() ;
		while ((num > llistaClients[i].getNumComandes()) || (num <= 0))
		{
			System.out.println("Error, escull una comanda per consultar entre [1-"+ llistaClients[i].getNumComandes()+"]");
			num = teclat.nextInt() ;
		}
		llistaClients[i].eliminaComanda(num-1);
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
		int numTelefon, numRestriccions;
		RestriccionsAlimentaries[] restriccions;
		
		if (nClients == llistaClients.length)
			System.out.println("No s'ha pogut crear un nou client, llista plena!");
		else{
			teclat.nextLine(); 			//Buidem el buffer del teclat
			System.out.println("Introdueix el nom del nou client: ");
			nom = teclat.nextLine();
			System.out.println("Introdueix un nom d'usuari: ");
			nomUsuari = teclat.nextLine();
			System.out.println("Introdueix una contrasenya: ");
			contrasenya = teclat.nextLine();
			System.out.println("Introdueix la teva adreca: ");
			adreca = teclat.nextLine();
			System.out.println("Introdueix el teu numero de telefon: ");
			numTelefon = teclat.nextInt();
			System.out.println("Introdueix la quantitat de restriccions alimentaries [0,3]: ");
			numRestriccions = teclat.nextInt();
			restriccions = new RestriccionsAlimentaries[numRestriccions];
			for (int i = 0; i < numRestriccions; i++) {
				System.out.println("Escriu la restriccio numero " + numRestriccions + 1 + ": ");
				restriccions[i] = RestriccionsAlimentaries.valueOf(teclat.next());
			}
			llistaClients[nClients] = new Client(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions);
			System.out.println("S'ha creat correctament el client");
			nClients++;
		}
	}
	
	public static void eliminarClient(int codiClient){
		int  i;
		
		i=buscaClient(codiClient);
		for(; i<nClients-1; i++){
			llistaClients[i]=llistaClients[i+1];
		}
		
		System.out.println("S'ha eliminat el client correctament");
		nClients--;
	}

	
	public static void consultarClient(int codiClient){
		int i;
		
		i = buscaClient(codiClient);
		System.out.println(llistaClients[i].toString());
	}
	
	public static void historialComandes(int codiClient){	
		int i;
		
		i = buscaClient(codiClient);
		for(int j=0; j<llistaClients[i].getNumComandes(); j++){
			System.out.println(llistaClients[i].getTaulaComandes()[j]);		//FALTA TOSTRING DE COMANDES
		}
	}
	
	private static int escullClient(){
		int i;
		for(i=0; i<nClients; i++){
			System.out.println("Nom: "+llistaClients[i].getNomUsuari()+" (Ref: "+llistaClients[i].getIdentificador()+")");
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
