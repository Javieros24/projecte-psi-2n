package Productes;

/**
 * Subclasse plat que hereda de la superclasse producte
 * @author Sergi Quevedo Garreta
 * 
 */
public class Plat extends Producte 
{
	
	private RestriccionsAlimentaries[] restriccions;
	
	/**
	 * Constructor de la subclasse plat que inicialitza els parametres de la superclasse i els seus
	 * @param nom es el nom del plat
	 * @param preu es el preu del plat
	 * @param restriccions son les restriccions alimentaries d'aquell plat
	 * @param codiReferencia es el codi de referencia del plat
	 */
	public Plat(String nom, double preu, RestriccionsAlimentaries[] restriccions, int codiReferencia)
	{
		super(nom,preu, codiReferencia);											//Inicialitzem els atributs de la superclasse
		this.restriccions = new RestriccionsAlimentaries[restriccions.length];
		for (int i=0; i < restriccions.length; i++){
			this.restriccions[i] = restriccions[i];
		}
	}
	
	/**
	 * Metode per mirar si un plat es apte per un client
	 * @param r son les restriccions alimentaries
	 * @return true si el plat es apte o false si no es apte 
	 */
	public boolean esApte(RestriccionsAlimentaries[] r)
	{
		int i,j;
		i=0;
		j=0;
		
		while(i<r.length)
		{
			while(j < restriccions.length)
			{
				if (r[i] == restriccions[j])
				{
					return false;
				}
				
				j++;
			}
			i++;
			
		}
		return true;
		
	}
	
	/**
	 * Getter
	 * @return les restriccions alimentaries d'aquell plat
	 */
	public RestriccionsAlimentaries[] getRestriccions() {
		return restriccions;
	}
	
	/**
	 * Setter
	 * @param restriccions son les restriccions alimentaries 
	 */
	public void setRestriccions(RestriccionsAlimentaries[] restriccions) {
		this.restriccions = restriccions;
	}
	
	public String toString(){
		return super.toString()+"    [Plat]";
	}
}
