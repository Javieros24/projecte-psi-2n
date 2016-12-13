package Restaurant;
import java.util.Arrays;

import Excepcions.NotFoundException;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

/**Classe que gestiona les dades dels clients.
 * 
 *  
 *  @author GRUP 10
 *  
 *  
 *  **/

public class Client {
	
	private String nom, adreca, nomUsuari, contrasenya;
	private int identificador, numTelefon, numComandes;
	private Comanda[] taulaComandes;
	private boolean preferent;
	private RestriccionsAlimentaries[] restriccions;
	
	/**Constructor de client.
	 * @param nom Nom del client
	 * @param adreca Adreça del client
	 * @param nomUsuari Nom d'usuari 
	 * @param contrasenya Contrasenya per a accedir a l'usuari del client
	 * @param numTelefon Numero de telefon
	 * @param restriccions Taula de les restriccions del client
	 * @param codiClient Codi identificador unic per a cada client **/
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
	
	/**Reb la referencia d'una comanda i el busca a la taula de comandes del client.
	 *@param codiComanda Referencia de la comanda
	 *@return Comanda amb el mateix codi**/
	public Comanda buscaComanda(int codiComanda) throws NotFoundException{
		
		for (int i=0; i < numComandes; i++){
			if (codiComanda == taulaComandes[i].getCodiComanda())
				return taulaComandes[i];
		}
		throw new NotFoundException();
	}
	
	
	/** Reb per parametre la referencia de la comanda que es vol copiar de l'historial
	 * i es crea una nova comanda amb els mateixos productes.
	 * @param numComanda Referencia de la comanda 
	 * @return Comanda **/

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
	
	/**Reb el codi d'una comanda i la elimina de la llista de comandes del client.
	 * @param codi Referencia de al comanda**/
	public void eliminaComanda(int codi) throws NotFoundException{
		
		//Comprova que existeix la comanda.
		buscaComanda(codi);
		
		int pos = buscarElement(codi);
		
		for(int i=pos; i<numComandes-1; i++){
			taulaComandes[i]=taulaComandes[i+1];
		}
		numComandes--;
	}
	
	/**Comprova si el client ha fet alguna comanda
	 * @return true si el client ha realitzat alguna comanda o
	 *false si el client no ha realitzat cap comanda.**/
	public boolean hiHaComandes(){
		if (numComandes==0) return false;
		else return true;
	}
	 /**Busca la posicio d'una comanda a la taula de comandes del client.
	  * @param ref Referencia de la comanda.
	  * @return Index de la posicio de la comanda a la taula. **/
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

	public String getAdreca() {
		return adreca;
	}

	public String getNomUsuari() {
		return nomUsuari;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public int getIdentificador() {
		return identificador;
	}

	public int getNumTelefon() {
		return numTelefon;
	}

	public int getNumComandes() {
		return numComandes;
	}

	public Comanda[] getTaulaComandes() {
		return taulaComandes;
	}

	public boolean isPreferent() {
		return preferent;
	}

	public RestriccionsAlimentaries[] getRestriccions() {
		return restriccions;
	}

	public String toString() {
		return "Nom:\t "+nom+"\nAdreca:\t "+adreca+"\nNom d'usuari:\t "+nomUsuari+"\nContrasenya:\t "+contrasenya+"\nNumero de telefon:\t "+numTelefon+
				"\n[Ref: "+identificador+"]\nNumero de Comandes:\t "+numComandes+"\nRestriccions alimentaries:\t "+Arrays.toString(restriccions);
	}	
}
