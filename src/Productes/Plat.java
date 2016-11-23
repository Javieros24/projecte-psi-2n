package Productes;

import java.util.Arrays;

/**
 * Subclasse plat que hereda de la superclasse producte
 */
public class Plat extends Producte 
{
	
	private RestriccionsAlimentaries[] restriccions;
	
	public Plat(String nom, double preu, RestriccionsAlimentaries[] restriccions)
	{
		super(nom,preu);
		this.restriccions = new RestriccionsAlimentaries[restriccions.length];
		for (int i=0; i < restriccions.length; i++){
			this.restriccions[i] = restriccions[i];
		}
	}
	
	/**
	 * Metode per mirar
	 * @param r
	 * @return
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

	public String toString() {
		return super.toString().concat("\nProducte apte per: " + Arrays.toString(restriccions)+ "\n**************");
	}
	

}
