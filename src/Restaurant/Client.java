package Restaurant;
import Excepcions.NotFoundException;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

public class Client {
	
	private String nom, adreca, nomUsuari, contrasenya;
	private int identificador, numTelefon, numComandes;
	private Comanda[] taulaComandes;
	private boolean preferent;
	private RestriccionsAlimentaries[] restriccions;
	
	public Client(String nom, String adreca, String nomUsuari, String contrasenya, int numTelefon, RestriccionsAlimentaries[] restriccions, int codiClient){
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
	public Comanda afegirComanda(Comanda comanda){
		taulaComandes[numComandes]=comanda;
		numComandes++;
		
		if (numComandes==6) preferent=true;
		return taulaComandes[numComandes-1];
	}
	
	public Comanda buscaComanda(int codiComanda) throws NotFoundException{
		
		for (int i=0; i < numComandes; i++){
			if (codiComanda == taulaComandes[i].getCodiComanda())
				return taulaComandes[i];
		}
		throw new NotFoundException();
	}
	
	
	/** Reb per parametre l'index de la comanda que es vol copiar de l'historial
	 * i es crea una nova comanda amb les mateixes.
	 * @param numComanda Index de la comanda a la taula.
	 * @return Comanda **/
	public Comanda copiar(int numComanda){
		int i=0;
		Producte[] p=taulaComandes[numComanda].getLlista();
		
		Comanda copia=new Comanda(taulaComandes[numComanda].getNumProd(), taulaComandes[numComanda].getCodiComanda());
		while(i<taulaComandes[numComanda].getNumProd())
			copia.afegirProducte(p[i], 1);
			i++;
		return copia;
	}
	
	public Comanda copiar(Comanda c, int codi){
		int i=0;
		Producte[] p=c.getLlista();
		
		Comanda copia=new Comanda(c.getNumProd(), codi);
		while(i<c.getNumProd()){
			copia.afegirProducte(p[i], 1);
			i++;
		}
		return copia;
	}
	
	public void eliminaComanda(int codi) throws NotFoundException{
		
		//Comprova que existeix la comanda.
		buscaComanda(codi);
		
		int pos = buscarElement(codi);
		
		for(int i=pos; i<numComandes-1; i++){
			taulaComandes[i]=taulaComandes[i+1];
		}
		numComandes--;
	}
	
	public boolean hiHaComandes(){
		if (numComandes==0) return false;
		else return true;
	}
	
	public int buscarElement(int ref) throws NotFoundException{
		
		for (int i=0; i < numComandes; i++){
			if (taulaComandes[i].getCodiComanda() == ref)
				return i;
		}
		
		throw new NotFoundException();
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

	public String toString() {
		return "Nom:\t "+nom+"\nAdreca:\t "+adreca+"\nNom d'usuari:\t "+nomUsuari+"\nContrasenya:\t "+contrasenya+"\nNumero de telefon:\t "+numTelefon+
				"\n[Ref: "+identificador+"]\nNumero de Comandes:\t "+numComandes+"\nRestriccions alimentaries:\t "+restriccions;
	}
	
	
	
}
