
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
	
	public boolean esApte(RestriccionsAlimentaries[] r)
	{
		return true;
	}
	

}
