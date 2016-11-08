
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
		for (int i=quantitat; i>0;i--)
		{
			llista[numProd] = p ;
			numProd++;
		}
	}
	
	/** Elimina un producte de la llista
	 * 
	 * @param p
	 */
	public void eliminarProducte(Producte p)
	{
		for (int i=0; i<numProd;i++)
		{
			if (llista[i]==p)
			{
				numProd--;
				for (int j=i;j<numProd;i++)
				{
					llista[j]=llista[j+1] ;
				}
				i--;
			}
		}
	}
	
	/** Get llista de productes
	 * 
	 * @return llista
	 */
	public Producte[] getLlista() {
		return llista;
	}
	
	/** Get numProd
	 * 
	 * @return numProd
	 */
	public int getNumProd() {
		return numProd;
	}
	
	
	
	
}
