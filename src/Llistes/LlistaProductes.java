package Llistes;
import Excepcions.DuplicatedNameException;
import Excepcions.NotFoundException;
import Productes.Beguda;
import Productes.Plat;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

/**Classe que gestiona els productes, els guarda en una llista i dona el codi de referència
 * que tindràn
 * 
 * @author GRUP 10
 *
 */
public class LlistaProductes {
	
	/**Guarda tota la informació dels productes en una llista. Tant plats com begudes
	 */
	private Producte[] llistaProductes;
	
	/**Guarda la quantitat de productes que conté la llista 
	 */
	private int nElements;
	
	/**Proporciona el codi de referència que tindràn els productes
	 */
	private int codiReferencia;
	
	/**Constructor que crea una llista amb la grandària passada per paràmetre i inicialitza
	 * el codi de referència a 1.
	 * 
	 * @param num enter que definirà la grandària de la llista de productes
	 */
	public LlistaProductes(int num){
		llistaProductes= new Producte[num];
		codiReferencia=1;
		
	}
	
	/**Mètode que permet afegir un element, en aquest cas un plat, a la llista
	 * 
	 * @param nom String amb el nom del plat
	 * @param preu double amb el preu del plat
	 * @param r taula de restriccions alimentàries del plat
	 * @return	retorna el plat que s'acava d'afegir a la llista
	 * @throws DuplicatedNameException excepció que és llançada en cas d'haver intentat afegir un plat amb el mateix nom
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
	
	/**Mètode que permet afegir un element, en aquest cas una beguda, a la llista
	 * 
	 * @param nom String amb el nom de la beguda
	 * @param preu double amb el preu de la beguda
	 * @param volum enter que conté el volum de la beguda
	 * @param alcohol booleà que indica si la beguda conté o no alcohol
	 * @return	retorna la beguda que s'acava d'afegir a la llista
	 * @throws DuplicatedNameException excepció que és llançada en cas d'haver intentat afegir una beguda amb el mateix nom
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
	
	/**Mètode que permet afegir un element, en aquest cas un plat, a la llista. 
	 * Aquest mètode es diferència del anterior pel codi de referència, ja que aquest mètode és utilitzat quan s'afegeix un plat
	 * desde un fitxer.
	 * 
	 * @param nom String amb el nom del plat
	 * @param preu double amb el preu del plat
	 * @param r taula de restriccions alimentàries del plat
	 * @param codiRef enter que conté el codi de referència del plat que s'ha d'afegir a la llista
	 * @throws DuplicatedNameException excepció que és llançada en cas d'haver intentat afegir un plat amb el mateix nom
	 */
	public void afegirElement(String nom, double preu, RestriccionsAlimentaries[] r, int codiRef) throws DuplicatedNameException{
		if (!existeixNom(nom)){	
			llistaProductes[nElements] = new Plat(nom, preu, r, codiRef);
			nElements++;
		}
		throw new DuplicatedNameException();
	}
	
	/**Mètode que permet afegir un element, en aquest cas una beguda, a la llista.
	 * Aquest mètode es diferència del anterior pel codi de referència, ja que aquest mètode és utilitzat quan s'afegeix una beguda
	 * desde un fitxer.
	 * 
	 * @param nom String amb el nom de la beguda
	 * @param preu double amb el preu de la beguda
	 * @param volum enter que conté el volum de la beguda
	 * @param alcohol booleà que indica si la beguda conté o no alcohol
	 * @return	retorna la beguda que s'acava d'afegir a la llista
	 * @throws DuplicatedNameException excepció que és llançada en cas d'haver intentat afegir una beguda amb el mateix nom
	 */
	public void afegirElement(String nom, double preu, int volum, boolean alcohol, int codiRef) throws DuplicatedNameException{
		if (!existeixNom(nom)) {
			llistaProductes[nElements] = new Beguda(nom, preu, volum, alcohol, codiRef);
			nElements++;
		}
		throw new DuplicatedNameException();
	}
	
	/**Mètode que permet eliminar un plat o una beguda de la llista. Utilitza el codi de referència d'aquest per eliminar-lo.
	 * En cas de no trobar l'element llença una excepció de no trobat.
	 * 
	 * @param ref enter amb el codi de referència del element a eliminar
	 * @throws NotFoundException excepció llençada si no hi ha cap producte amb aquell codi de referència
	 */
	public void eliminarElement(int  ref) throws NotFoundException{
		int  i;
		i=buscarElement(ref);
		
		for(; i<nElements-1; i++){
			llistaProductes[i]=llistaProductes[i+1];
		}
		nElements--;
	}
	
	/**Mètode que busca un producte a partir d'un codi de referència. En cas de no trobar cap element amb aquell codi
	 * llença una excepció de no trobat. Retorna la posició del element que té el codi de referència indicat.
	 * 
	 * @param codi identificador del Producte a buscar
	 * @return posició de la taula on es troba el producte
	 * @throws NotFoundException excepció llençada si no hi ha cap producte amb aquell codi de referència
	 */
	public int buscarElement(int ref) throws NotFoundException{
	
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].getCodiReferencia() == ref)
				return i;
		}
		
		throw new NotFoundException();
	}
	
	/**Mètode que busca un producte a partir d'un codi de referència. En cas de no trobar cap element amb aquell codi
	 * llença una excepció de no trobat. Retorna l'element que té el codi de referència indicat.
	 * 
	 * @param codi identificador del Producte a buscar
	 * @return retorna el producte que té el codi de referència indicat
	 * @throws NotFoundException excepció llençada si no hi ha cap producte amb aquell codi de referència
	 */
	public Producte consultarElement(int ref) throws NotFoundException{
		
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].getCodiReferencia() == ref)
				return llistaProductes[i];
		}
		
		throw new NotFoundException();
	}
	
	/**Mètode privat que s'encarrega de deixar un codi de referència no utilitat abans de afegir un producte
	 * a la llista. Aquest mètode és cridat just abans d'afegir un producte a la llista.
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
	
	/**Mètode privat que busca si hi ha algun producte amb el mateix nom afegit a la llista
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

	/**Getter del número de productes que té la llista
	 * 
	 * @return retorna el número de productes que té la llista
	 */
	public int getnElements() {
		return nElements;
	}
}
