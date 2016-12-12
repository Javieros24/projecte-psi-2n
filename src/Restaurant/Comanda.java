package Restaurant;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Productes.Producte;

/** Classe que gestiona una comanda d'un client
 * 
 * @author GRUP 10
 *
 */
public class Comanda {

	private Producte[] llista ;
	private int numProd;
	private int codiComanda;
	private String horaComanda;
	
	/**Constructor de Comanda 
	 * @param num Numero de productes que tindrà la comanda
	 * @param codiRef Codi de referencia de la comanda
	 */
	public Comanda(int num, int codiRef)
	{
		llista = new Producte[num];
		numProd = 0;
		codiComanda = codiRef;
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		horaComanda = format.format(cal.getTime());
		
		
	}
	/**Constructor de comanda on es pasa l'hora, utilitzar per a carregar dels fitxers
	 * @param num Numero de productes que tindrà la comanda
	 * @param codiRef Codi de referencia de la comanda
	 * @param horaComanda Data en la que s'ha realitzat la comanda
	 */
	public Comanda(int num, int codiRef, String horaComanda)
	{
		llista = new Producte[num];
		numProd = 0;
		codiComanda = codiRef;
		
		this.horaComanda = horaComanda;
		
	}
	
	/** Afegeix un producte a la llista
	 * @param p Producte que es vol afegir
	 * @param quantitat Quantitat del producte que es vol
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
	 * @param p Producte que es vol eliminar
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
	/**Calcula i retorna el preu de la comanda, tenint en compte si el client es preferent
	 * @param preferent True si el client es preferent (s'aplica descompte) i False si no ho es
	 * @return preu Preu final de la comanda
	 */
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
	/**toString de comanda
	 */
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
