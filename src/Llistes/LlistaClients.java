package Llistes;
import Excepcions.DuplicatedNameException;
import Excepcions.IllegalCharException;
import Excepcions.NotFoundException;
import Productes.RestriccionsAlimentaries;
import Restaurant.Client;
import Restaurant.Comanda;

public class LlistaClients {
	private Client[] llistaClients;
	private int nElements;
	private int codiClient;
	private int codiComandes;
	
	public LlistaClients(int num){
		llistaClients = new Client[num];
		codiClient=1;
		codiComandes=1;
	}
	
	/**M�tode que permet afegir un client a la llista
	 * 
	 * @param nom String amb el nom del client
	 * @param adreca String amb l'adre�a del client
	 * @param nomUsuari String amb el nom d'usuari
	 * @param contrasenya String amb la contrasenya
	 * @param numTelefon Enter amb el numero de telefon de l'usuari
	 * @param restriccions Taula de RestriccionsAlimentaries amb les restrccions del client
	 * @return	retorna el client que s'acava d'afegir a la llista
	 * @throws DuplicatedNameException excepci� que �s llan�ada en cas d'haver intentat afegir un client amb el mateix nom
	 * @throws IllegalCharException excepci� que �s llan�ada en cas d'haver afeigt un element amb alguna ","
	 */
	public Client afegirElement(String nom, String adreca, String nomUsuari, String contrasenya, int numTelefon, RestriccionsAlimentaries[] restriccions) throws DuplicatedNameException, IllegalCharException{
		if (!existeixNom(nomUsuari)){
			caracterIlegal(nom, adreca, nomUsuari, contrasenya);
			referencia();
			llistaClients[nElements] = new Client(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions, codiClient);
			nElements++;
			return llistaClients[nElements-1];
		}
		throw new DuplicatedNameException();
	}
	
	/**M�tode que permet afegir un client a la llista. 
	 * Aquest m�tode es difer�ncia del anterior pel codi de refer�ncia, ja que aquest m�tode �s utilitzat quan s'afegeix un client
	 * desde un fitxer.
	 * 
	 * @param nom String amb el nom del client
	 * @param adreca String amb l'adre�a del client
	 * @param nomUsuari String amb el nom d'usuari
	 * @param contrasenya String amb la contrasenya
	 * @param numTelefon Enter amb el numero de telefon de l'usuari
	 * @param restriccions Taula de RestriccionsAlimentaries amb les restrccions del client
	 * @param codiClient Enter que conte el codi de refer�ncia del client que s'ha d'afegir
	 * @return	retorna el client que s'acava d'afegir a la llista
	 * @throws DuplicatedNameException excepci� que �s llan�ada en cas d'haver intentat afegir un client amb el mateix nom
	 */
	public void afegirElement(String nom, String adreca, String nomUsuari, String contrasenya, int numTelefon, RestriccionsAlimentaries[] restriccions, int codiClient){
		llistaClients[nElements] = new Client(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions, codiClient);
		nElements++;
	}
	
	/**M�tode que permet eliminar un client de la llista. Utilitza el codi de refer�ncia d'aquest per eliminar-lo.
	 * En cas de no trobar l'element llen�a una excepci� de no trobat.
	 * 
	 * @param ref enter amb el codi de refer�ncia del element a eliminar
	 * @throws NotFoundException excepci� llen�ada si no hi ha cap client amb aquell codi de refer�ncia
	 */
	public void eliminarElement(int  ref) throws NotFoundException{
		int  i;
		
		i=buscarElement(ref);
		
		for(; i<nElements-1; i++){
			llistaClients[i]=llistaClients[i+1];
		}
		nElements--;
	}
	
	/**M�tode que busca un client a partir d'un codi de refer�ncia. En cas de no trobar cap element amb aquell codi
	 * llen�a una excepci� de no trobat. Retorna la posici� del element que t� el codi de refer�ncia indicat.
	 * 
	 * @param codi identificador del client a buscar
	 * @return posici� de la taula on es troba el client
	 * @throws NotFoundException excepci� llen�ada si no hi ha cap client amb aquell codi de refer�ncia
	 */
	public int buscarElement(int ref) throws NotFoundException{
	
		for (int i=0; i < nElements; i++){
			if (llistaClients[i].getIdentificador() == ref)
				return i;
		}
		
		throw new NotFoundException();
	}
	
	/**M�tode que busca un client a partir d'un codi de refer�ncia. En cas de no trobar cap element amb aquell codi
	 * llen�a una excepci� de no trobat. Retorna l'element que t� el codi de refer�ncia indicat.
	 * 
	 * @param codi identificador del client a buscar
	 * @return retorna el client que t� el codi de refer�ncia indicat
	 * @throws NotFoundException excepci� llen�ada si no hi ha cap client amb aquell codi de refer�ncia
	 */
	public Client consultarElement(int ref) throws NotFoundException
	{
		
		for (int i=0; i < nElements; i++){
			if (llistaClients[i].getIdentificador() == ref)
				return llistaClients[i];
			
		}
		throw new NotFoundException();
	}
	
	/**M�tode privat que s'encarrega de deixar un codi de refer�ncia no utilitat abans de afegir un client
	 * a la llista. Aquest m�tode �s cridat just abans d'afegir un client a la llista.
	 * 
	 */
	private void referencia(){
		
		for (int i=0; i<nElements; i++){
			if (codiClient == llistaClients[i].getIdentificador()){
				i=0;
				codiClient++;
			}
		}
	}
	
	/**M�tode que busca el  car�cter ',' entre els strings ja que �s l'utilitzat com a token al llegir de fitxers
	 * 
	 * @param nom String a analitzar
	 * @param adreca String a analitzar
	 * @param nomUsuari String a analitzar
	 * @throws IllegalCharException
	 */
	private void caracterIlegal(String nom,String adreca,String nomUsuari, String contrasenya) throws IllegalCharException{
		int i;
		char valor = ',';
		for(i=0; i<nom.length(); i++){
			if (valor == nom.charAt(i)) throw new IllegalCharException();
		}
		for(i=0; i<adreca.length(); i++){
			if (valor == adreca.charAt(i)) throw new IllegalCharException();
		}
		for(i=0; i<nomUsuari.length(); i++){
			if (valor == nomUsuari.charAt(i)) throw new IllegalCharException();
		}
		for(i=0; i<contrasenya.length(); i++){
			if (valor == contrasenya.charAt(i)) throw new IllegalCharException();
		}
	}

	
	/**M�tode privat que s'encarrega de deixar un codi de refer�ncia no utilitat abans de afegir una comanda
	 * a la taula d'un client. Aquest m�tode �s cridat just abans d'afegir una comanda a la taula.
	 * 
	 */
	public int referenciaComanda(){
		Client c;
		Comanda[] com;
		for (int j=0; j<nElements; j++){
			c = llistaClients[j];
			com = llistaClients[j].getTaulaComandes();
			for (int i=0; i<c.getNumComandes() ; i++){
				if (codiComandes == com[i].getCodiComanda()){
					i=-1;
					j=-1;
					codiComandes++;
				}
			}
		}
		return codiComandes;
	}
	
	/**M�tode privat que busca si hi ha algun client amb el mateix nom afegit a la llista
	 * 
	 * @param nom String amb nom del client a buscar
	 * @return retorna cert en cas d'existir un client amb aquell nom, fals en cas contrari
	 */
	private boolean existeixNom(String nom){
		for (int i=0; i<nElements; i++){
			if (nom.equalsIgnoreCase(llistaClients[i].getNomUsuari()))
				return true;
		}
		return false;
	}

	/**Getter de la llista de clients
	 * 
	 * @return retorna la llista de clients
	 */
	public Client[] getLlistaClients() {
		return llistaClients;
	}

	/**Getter del n�mero de clients que t� la llista
	 * 
	 * @return retorna el n�mero de clients que t� la llista
	 */
	public int getnElements() {
		return nElements;
	}
}
