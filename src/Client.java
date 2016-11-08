import java.util.Arrays;

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

	/*-------------------GETTERS I SETTERS----------------------------------*/
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdreça() {
		return adreça;
	}

	public void setAdreça(String adreça) {
		this.adreça = adreça;
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

	public RestriccionsAlimentaries getRestriccions() {
		return restriccions;
	}

	public void setRestriccions(RestriccionsAlimentaries restriccions) {
		this.restriccions = restriccions;
	}

	public String toString() {
		return "Client [nom=" + nom + ", adreça=" + adreça + ", nomUsuari=" + nomUsuari + ", contrasenya=" + contrasenya
				+ ", identificador=" + identificador + ", numTelefon=" + numTelefon + ", numComandes=" + numComandes
				+ ", taulaComandes=" + Arrays.toString(taulaComandes) + ", preferent=" + preferent + ", restriccions="
				+ restriccions + "]";
	}
	
	
	
}
