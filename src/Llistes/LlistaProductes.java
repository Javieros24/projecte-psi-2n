package Llistes;
import Excepcions.DuplicatedNameException;
import Excepcions.NotFoundException;
import Productes.Beguda;
import Productes.Plat;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

/**Classe que gestiona els productes, els guarda en una llista i dona el codi de refer�ncia
 * que tindr�n
 * 
 * @author GRUP 10
 *
 */
public class LlistaProductes {
	
	/**Guarda tota la informaci� dels productes en una llista. Tant plats com begudes
	 */
	private Producte[] llistaProductes;
	
	/**Guarda la quantitat de productes que cont� la llista 
	 */
	private int nElements;
	
	/**Proporciona el codi de refer�ncia que tindr�n els productes
	 */
	private int codiReferencia;
	
	/**Constructor que crea una llista amb la grand�ria passada per par�metre i inicialitza
	 * el codi de refer�ncia a 1.
	 * 
	 * @param num enter que definir� la grand�ria de la llista de productes
	 */
	public LlistaProductes(int num){
		llistaProductes= new Producte[num];
		codiReferencia=1;
		
	}
	
	/**M�tode que permet afegir un element, en aquest cas un plat, a la llista
	 * 
	 * @param nom String amb el nom del plat
	 * @param preu double amb el preu del plat
	 * @param r taula de restriccions aliment�ries del plat
	 * @return	retorna el plat que s'acava d'afegir a la llista
	 * @throws DuplicatedNameException excepci� que �s llan�ada en cas d'haver intentat afegir un plat amb el mateix nom
	 */
	public Plat afegirElement(String nom, double preu, RestriccionsAlimentaries[] r) throws DuplicatedNameException{
		if (existeixNom(nom)) throw new DuplicatedNameException();
		else{
			referencia();
			llistaProductes[nElements] = new Plat(nom, preu, r, codiReferencia);
			nElements++;
		}
		
		return ((Plat)llistaProductes[nElements-1]);
	}
	
	/**M�tode que permet afegir un element, en aquest cas una beguda, a la llista
	 * 
	 * @param nom String amb el nom de la beguda
	 * @param preu double amb el preu de la beguda
	 * @param volum enter que cont� el volum de la beguda
	 * @param alcohol boole� que indica si la beguda cont� o no alcohol
	 * @return	retorna la beguda que s'acava d'afegir a la llista
	 * @throws DuplicatedNameException excepci� que �s llan�ada en cas d'haver intentat afegir una beguda amb el mateix nom
	 */
	public Beguda afegirElement(String nom, double preu, int volum, boolean alcohol) throws DuplicatedNameException{
		if (!existeixNom(nom)){
			referencia();
			llistaProductes[nElements] = new Beguda(nom, preu, volum, alcohol, codiReferencia);
			nElements++;
			return ((Beguda)llistaProductes[nElements-1]);
		}
		throw new DuplicatedNameException();
	}
	
	/**M�tode que permet afegir un element, en aquest cas un plat, a la llista. 
	 * Aquest m�tode es difer�ncia del anterior pel codi de refer�ncia, ja que aquest m�tode �s utilitzat quan s'afegeix un plat
	 * desde un fitxer.
	 * 
	 * @param nom String amb el nom del plat
	 * @param preu double amb el preu del plat
	 * @param r taula de restriccions aliment�ries del plat
	 * @param codiRef enter que cont� el codi de refer�ncia del plat que s'ha d'afegir a la llista
	 * @throws DuplicatedNameException excepci� que �s llan�ada en cas d'haver intentat afegir un plat amb el mateix nom
	 */
	public void afegirElement(String nom, double preu, RestriccionsAlimentaries[] r, int codiRef) throws DuplicatedNameException{
		if (!existeixNom(nom)){	
			llistaProductes[nElements] = new Plat(nom, preu, r, codiRef);
			nElements++;
		}
		throw new DuplicatedNameException();
	}
	
	/**M�tode que permet afegir un element, en aquest cas una beguda, a la llista.
	 * Aquest m�tode es difer�ncia del anterior pel codi de refer�ncia, ja que aquest m�tode �s utilitzat quan s'afegeix una beguda
	 * desde un fitxer.
	 * 
	 * @param nom String amb el nom de la beguda
	 * @param preu double amb el preu de la beguda
	 * @param volum enter que cont� el volum de la beguda
	 * @param alcohol boole� que indica si la beguda cont� o no alcohol
	 * @return	retorna la beguda que s'acava d'afegir a la llista
	 * @throws DuplicatedNameException excepci� que �s llan�ada en cas d'haver intentat afegir una beguda amb el mateix nom
	 */
	public void afegirElement(String nom, double preu, int volum, boolean alcohol, int codiRef) throws DuplicatedNameException{
		if (!existeixNom(nom)) {
			llistaProductes[nElements] = new Beguda(nom, preu, volum, alcohol, codiRef);
			nElements++;
		}
		throw new DuplicatedNameException();
	}
	
	/**M�tode que permet eliminar un plat o una beguda de la llista. Utilitza el codi de refer�ncia d'aquest per eliminar-lo.
	 * En cas de no trobar l'element llen�a una excepci� de no trobat.
	 * 
	 * @param ref enter amb el codi de refer�ncia del element a eliminar
	 * @throws NotFoundException excepci� llen�ada si no hi ha cap producte amb aquell codi de refer�ncia
	 */
	public void eliminarElement(int  ref) throws NotFoundException{
		int  i;
		i=buscarElement(ref);
		
		for(; i<nElements-1; i++){
			llistaProductes[i]=llistaProductes[i+1];
		}
		nElements--;
	}
	
	/**M�tode que busca un producte a partir d'un codi de refer�ncia. En cas de no trobar cap element amb aquell codi
	 * llen�a una excepci� de no trobat. Retorna la posici� del element que t� el codi de refer�ncia indicat.
	 * 
	 * @param codi identificador del Producte a buscar
	 * @return posici� de la taula on es troba el producte
	 * @throws NotFoundException excepci� llen�ada si no hi ha cap producte amb aquell codi de refer�ncia
	 */
	public int buscarElement(int ref) throws NotFoundException{
	
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].getCodiReferencia() == ref)
				return i;
		}
		
		throw new NotFoundException();
	}
	
	/**M�tode que busca un producte a partir d'un codi de refer�ncia. En cas de no trobar cap element amb aquell codi
	 * llen�a una excepci� de no trobat. Retorna l'element que t� el codi de refer�ncia indicat.
	 * 
	 * @param codi identificador del Producte a buscar
	 * @return retorna el producte que t� el codi de refer�ncia indicat
	 * @throws NotFoundException excepci� llen�ada si no hi ha cap producte amb aquell codi de refer�ncia
	 */
	public Producte consultarElement(int ref) throws NotFoundException{
		
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].getCodiReferencia() == ref)
				return llistaProductes[i];
		}
		
		throw new NotFoundException();
	}
	
	/**M�tode privat que s'encarrega de deixar un codi de refer�ncia no utilitat abans de afegir un producte
	 * a la llista. Aquest m�tode �s cridat just abans d'afegir un producte a la llista.
	 * 
	 */
	private void referencia(){

		for (int i=0; i<nElements; i++){
			if (codiReferencia == llistaProductes[i].getCodiReferencia()){
				i=0;
				codiReferencia++;
			}
		}
	}
	
	/**M�tode privat que busca si hi ha algun producte amb el mateix nom afegit a la llista
	 * 
	 * @param nom String amb nom del producte a buscar
	 * @return retorna cert en cas d'existir un producte amb aquell nom, fals en cas contrari
	 */
	private boolean existeixNom(String nom){
		for (int i=0; i<nElements; i++){
			if (nom.equalsIgnoreCase(llistaProductes[i].getNom()))
				return true;
		}
		return false;
	}

	/**Getter de la llista de productes
	 * 
	 * @return retorna la llista de productes
	 */
	public Producte[] getLlistaProductes() {
		return llistaProductes;
	}

	/**Getter del n�mero de productes que t� la llista
	 * 
	 * @return retorna el n�mero de productes que t� la llista
	 */
	public int getnElements() {
		return nElements;
	}
}
