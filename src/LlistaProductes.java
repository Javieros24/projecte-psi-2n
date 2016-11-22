
public class LlistaProductes {
	private Producte[] llistaProductes;
	private int nElements;
	
	public LlistaProductes(int num){
		llistaProductes= new Producte[num];
		
	}
	
	public void afegirElement(String nom, double preu, RestriccionsAlimentaries[] r){
		llistaProductes[nElements] = new Plat(nom, preu, r);
		nElements++;
	}
	
	public void afegirElement(String nom, double preu, int volum, boolean alcohol){
		llistaProductes[nElements] = new Beguda(nom, preu, volum, alcohol);
		nElements++;
	}
	
	public void eliminarElement(int  ref) throws NotFoundException{
		int  i;
		
		i=buscarElement(ref);
		
		for(; i<nElements-1; i++){
			llistaProductes[i]=llistaProductes[i+1];
		}
		
		System.out.println("S'ha eliminat el Producte correctament");
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
			if (llistaProductes[i].codiReferencia == ref)
				return i;
		}
		
		throw new NotFoundException();
	}
	
	public Producte consultarElement(int ref) throws NotFoundException{
		
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].codiReferencia == ref)
				return llistaProductes[i];
		}
		
		throw new NotFoundException();
	}

	public Producte[] getLlistaProductes() {
		return llistaProductes;
	}

	public int getnElements() {
		return nElements;
	}
}
