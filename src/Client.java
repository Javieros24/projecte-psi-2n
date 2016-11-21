public class Client {
	
	private String nom, adreca, nomUsuari, contrasenya;
	private int identificador, numTelefon, numComandes;
	private Comanda[] taulaComandes;
	private boolean preferent;
	private RestriccionsAlimentaries[] restriccions;
	private static int codiClient=1;
	
	public Client(String nom, String adreca, String nomUsuari, String contrasenya, int numTelefon, RestriccionsAlimentaries[] restriccions){
		this.nom=nom;
		this.adreca=adreca;
		this.nomUsuari=nomUsuari;
		this.contrasenya=contrasenya;
		this.numTelefon=numTelefon;
		identificador=codiClient;
		codiClient++;
		numComandes=0;
		preferent=false;
		
		this.restriccions = new RestriccionsAlimentaries[restriccions.length];
		for(int i=0; i<restriccions.length; i++){
			this.restriccions[i]=restriccions[i];
		}
		
		taulaComandes = new Comanda[100];
	}
	
	/** Afegeix una comanda a l'historial de comandes del client.
	 * @param comanda Comanda que es vol afegir a l'historial. **/
	public void afegirComanda(Comanda comanda){
		taulaComandes[numComandes]=comanda;
		numComandes++;
		
		if (numComandes==6) preferent=true;
	}
	
	public Comanda buscaComanda(int codiComanda){
		
		for (int i=0; i < numComandes; i++){
			if (codiComanda == taulaComandes[i].getNumProd())
				return taulaComandes[i];
		}
		
		//EXCEPCIO!
		return null;
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
	
	public void eliminaComanda(int numComanda){
		for(int i=numComanda; i<numComandes-1; i++){
			taulaComandes[i]=taulaComandes[i+1];
		}
		numComandes--;
	}

	/*-------------------GETTERS I SETTERS----------------------------------*/
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getadreca() {
		return adreca;
	}

	public void setadreca(String adreca) {
		this.adreca = adreca;
	}

	public String getNomUsuari() {
		return nomUsuari;
	}

	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public int getNumTelefon() {
		return numTelefon;
	}

	public void setNumTelefon(int numTelefon) {
		this.numTelefon = numTelefon;
	}

	public int getNumComandes() {
		return numComandes;
	}

	public void setNumComandes(int numComandes) {
		this.numComandes = numComandes;
	}

	public Comanda[] getTaulaComandes() {
		return taulaComandes;
	}

	public void setTaulaComandes(Comanda[] taulaComandes) {
		this.taulaComandes = taulaComandes;
	}

	public boolean isPreferent() {
		return preferent;
	}

	public void setPreferent(boolean preferent) {
		this.preferent = preferent;
	}

	public RestriccionsAlimentaries[] getRestriccions() {
		return restriccions;
	}

	public void setRestriccions(RestriccionsAlimentaries[] restriccions) {
		this.restriccions = restriccions;
	}

	public String getAdreca() {
		return adreca;
	}

	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}

	public static int getCodiClient() {
		return codiClient;
	}

	public static void setCodiClient(int codiClient) {
		Client.codiClient = codiClient;
	}

	public String toString() {
		return "Nom: "+nom+"\nAdreca: "+adreca+"\nNom d'usuari: "+nomUsuari+"\nContrasenya: "+contrasenya+"\nNumero de telefon: "+numTelefon+
				"\nIdentificador: "+identificador+"\nNumero de Comandes: "+numComandes+"\nRestriccions alimentaries: "+restriccions;
	}
	
	
	
}
