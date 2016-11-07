
public class Comanda {

	private Producte[] llista ;
	private int numProd = 0;
	
	public Comanda(int num)
	{
		llista = new Producte[num];
		
	}
	
	/** Afegeix un producte a la llista
	 * 
	 * @param p
	 * @param quantitat
	 */
	public void afegirProducte(Producte p, int quantitat)
	{
		llista[numProd] = p ;
		numProd++;
	}
	
	
	public void eliminarProducte()
	{
		
	}
	
	
}
