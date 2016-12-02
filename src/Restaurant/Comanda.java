package Restaurant;
import java.util.GregorianCalendar;

import Productes.Producte;

public class Comanda {

	private Producte[] llista ;
	private int numProd;
	private int codiComanda;
	private String horaComanda;
	
	/**Constructor de Comanda 
	 * @param num
	 */
	public Comanda(int num, int codiRef)
	{
		llista = new Producte[num];
		numProd = 0;
		codiComanda = codiRef;
		
		horaComanda = "["+GregorianCalendar.HOUR_OF_DAY+":"+GregorianCalendar.MINUTE+"] "+GregorianCalendar.DAY_OF_MONTH+"/"+GregorianCalendar.MONTH+"/"+GregorianCalendar.YEAR;
		
	}
	
	public Comanda(int num, int codiRef, String horaComanda)
	{
		llista = new Producte[num];
		numProd = 0;
		codiComanda = codiRef;
		
		this.horaComanda = horaComanda;
		
	}
	
	/** Afegeix un producte a la llista
	 * @param p
	 * @param quantitat
	 */
	public void afegirProducte(Producte p, int quantitat)
	{
		for (int i=0; i<quantitat ;i++)
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
	
	public double calcularPreu(boolean preferent)
	{
		double preu=0;
		for (int i=0; i<numProd;i++)
		{
			if (preferent) preu = preu + (llista[i].getPreu() - llista[i].getDescompte());
			else preu = preu + (llista[i].getPreu());
		}
		return preu;
	}
	
	public String toString() 
	{
		//return "[Ref: " +codiComanda+"] amb "+numProd+" productes.\t"+horaComanda;
		return horaComanda+" | "+numProd+" productes.";
	}
	
	/*-------------------GETTERS I SETTERS----------------------------------*/
	
	public Producte[] getLlista() {
		return llista;
	}
	
	public String getHoraComanda() {
		return horaComanda;
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

	public void setCodiComanda(int codiComanda) {
		this.codiComanda = codiComanda;
	}
}
