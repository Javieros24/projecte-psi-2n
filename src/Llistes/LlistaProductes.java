package Llistes;
import Excepcions.DuplicatedNameException;
import Excepcions.NotFoundException;
import Productes.Beguda;
import Productes.Plat;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

public class LlistaProductes {
	private Producte[] llistaProductes;
	private int nElements;
	private int codiReferencia;
	
	public LlistaProductes(int num){
		llistaProductes= new Producte[num];
		codiReferencia=1;
		
	}
	
	public Plat afegirElement(String nom, double preu, RestriccionsAlimentaries[] r) throws DuplicatedNameException{
		if (existeixNom(nom)) throw new DuplicatedNameException();
		else{
			referencia();
			llistaProductes[nElements] = new Plat(nom, preu, r, codiReferencia);
			nElements++;
		}
		
		return ((Plat)llistaProductes[nElements-1]);
	}
	
	public Beguda afegirElement(String nom, double preu, int volum, boolean alcohol) throws DuplicatedNameException{
		if (!existeixNom(nom)){
			referencia();
			llistaProductes[nElements] = new Beguda(nom, preu, volum, alcohol, codiReferencia);
			nElements++;
			return ((Beguda)llistaProductes[nElements-1]);
		}
		throw new DuplicatedNameException();
	}
	
	public void afegirElement(String nom, double preu, RestriccionsAlimentaries[] r, int codiRef){
			llistaProductes[nElements] = new Plat(nom, preu, r, codiRef);
			nElements++;
	}
	
	public void afegirElement(String nom, double preu, int volum, boolean alcohol, int codiRef){
		llistaProductes[nElements] = new Beguda(nom, preu, volum, alcohol, codiRef);
		nElements++;
	}
	
	public void eliminarElement(int  ref) throws NotFoundException{
		int  i;
		
		i=buscarElement(ref);
		
		for(; i<nElements-1; i++){
			llistaProductes[i]=llistaProductes[i+1];
		}
		nElements--;
	}
	
	/**Mètode que busca un Producte a partir d'un codi, -1 en cas de no trobar-lo
	 * 
	 * @param codi identificador del Producte a buscar
	 * @return posició de la taula on es troba el Producte, -1 en cas de no trobar-lo
	 * @throws NotFoundException 
	 */
	public int buscarElement(int ref) throws NotFoundException{
	
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].getCodiReferencia() == ref)
				return i;
		}
		
		throw new NotFoundException();
	}
	
	public Producte consultarElement(int ref) throws NotFoundException{
		
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].getCodiReferencia() == ref)
				return llistaProductes[i];
		}
		
		throw new NotFoundException();
	}
	
	private void referencia(){

		for (int i=0; i<nElements; i++){
			if (codiReferencia == llistaProductes[i].getCodiReferencia()){
				i=0;
				codiReferencia++;
			}
		}
	}
	
	private boolean existeixNom(String nom){
		for (int i=0; i<nElements; i++){
			if (nom.equalsIgnoreCase(llistaProductes[i].getNom()))
				return true;
		}
		return false;
	}

	public Producte[] getLlistaProductes() {
		return llistaProductes;
	}

	public int getnElements() {
		return nElements;
	}
}
