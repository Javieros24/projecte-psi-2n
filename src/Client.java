public class Client {
	
	String nom, adre�a, nomUsuari, contrasenya;
	int identificador, numTelefon, numComandes;
	Comanda[] taulaComandes;
	
	public Client(String nom, String adre�a, String nomUsuari, String contrasenya, int numClients, int numTelefon){
		this.nom=nom;
		this.adre�a=adre�a;
		this.nomUsuari=nomUsuari;
		this.contrasenya=contrasenya;
		this.numTelefon=numTelefon;
		identificador=numClients;
		numComandes=0;
				
		taulaComandes = new Comanda[20];
	}
	
	/** Afegeix una comanda a l'historial de comandes del client.
	 * @param comanda Comanda que es vol afegir a l'historial. **/
	public void afegirComanda(Comanda comanda){
		taulaComandes[numComandes]=comanda;
		numComandes++;
	}
	
}
