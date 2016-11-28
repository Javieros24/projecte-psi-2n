package Restaurant;
import Productes.Producte;

public class Comanda {

	private Producte[] llista ;
	private int numProd;
	private int codiComanda;
	
	/**Constructor de Comanda 
	 * @param num
	 */
	public Comanda(int num, int codiRef)
	{
		llista = new Producte[num];
		numProd = 0;
		codiComanda = codiRef;
		
	}
	
	/** Afegeix un producte a la llista
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
	
	public String toString() 
	{
		return "Comanda #" +codiComanda+", amb "+numProd+" productes.";
	}
	
	/*-------------------GETTERS I SETTERS----------------------------------*/
	
	public Producte[] getLlista() {
		return llista;
	}
	
	public int getNumProd() {
		return numProd;
	}
	
	public void setNumProd(int numProd) {
		this.numProd = numProd ;
	}
	
	public int getCodiComanda ()
	{
		return codiComanda;
	}
}
