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
	
	public static void inicialitzaDades(){
		llistaProductes = new Producte[100];
		llistaClients = new Client[100];
		nClients = 0;
		nProductes = 0;
	}
	
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
	
	public static void consultarProducte(){
		int codi, posicio;
		
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

}
