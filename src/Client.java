public class Client {
	
	private String nom, adreça, nomUsuari, contrasenya;
	private int identificador, numTelefon, numComandes;
	private Comanda[] taulaComandes;
	private boolean preferent;
	private RestriccionsAlimentaries restriccions;
	
	public Client(String nom, String adreça, String nomUsuari, String contrasenya, int numClients, int numTelefon, RestriccionsAlimentaries restriccions){
		this.nom=nom;
		this.adreça=adreça;
		this.nomUsuari=nomUsuari;
		this.contrasenya=contrasenya;
		this.numTelefon=numTelefon;
		identificador=numClients;
		numComandes=0;
		this.restriccions=restriccions;
		preferent=false;
		
		taulaComandes = new Comanda[100];
	}
	
	/** Afegeix una comanda a l'historial de comandes del client.
	 * @param comanda Comanda que es vol afegir a l'historial. **/
	public void afegirComanda(Comanda comanda){
		taulaComandes[numComandes]=comanda;
		numComandes++;
		
		if (numComandes==6) preferent=true;
	}
	
	/** Retorna l'historial de comandes del client.
	 * @return Taula de comandes. **/
	public Comanda[] consultar(){
		return taulaComandes;
	}
	
	/** Reb per parametre l'index de la comanda que es vol copiar de l'historial
	 * i es crea una nova comanda amb les mateixes.
	 * @param numComanda Index de la comanda a la taula.
	 * @return Comanda **/
	public Comanda copiar(int numComanda){
		int i=0;
		Producte[] p=taulaComandes[numComanda].getLlista();
		
		Comanda copia=new Comanda(taulaComandes[numComanda].getNumProd());
		while(i<taulaComandes[numComanda].getNumProd())
			copia.afegirProducte(p[i], 1);
			i++;
		return copia;
	}
	
}
