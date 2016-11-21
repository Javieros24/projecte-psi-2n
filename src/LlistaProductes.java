
public class LlistaProductes {
	private Producte[] llistaProductes;
	private int nElements;
	
	public LlistaProductes(){
		
	}
	
	public void afegirElement(String nom, double preu, RestriccionsAlimentaries[] r){
		llistaProductes[nElements] = new Plat(nom, preu, r);
		nElements++;
	}
	
	public void afegirElement(String nom, double preu, int volum, boolean alcohol){
		llistaProductes[nElements] = new Beguda(nom, preu, volum, alcohol);
		nElements++;
	}
	
	public void eliminarElement(int  ref){
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
	 */
	public int buscarElement(int ref){
	
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].codiReferencia == ref)
				return i;
		}
		
		//EXCEPCIO!
		return -1;
	}
	
	public Producte consultarElement(int ref){
		
		for (int i=0; i < nElements; i++){
			if (llistaProductes[i].codiReferencia == ref)
				return llistaProductes[i];
		}
		
		//EXCEPCIO!
		return null;
	}

	public Producte[] getLlistaProductes() {
		return llistaProductes;
	}

	public int getnElements() {
		return nElements;
	}
}
