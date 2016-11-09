
public class Plat extends Producte 
{
	
	private RestriccionsAlimentaries[] restriccions;
	private int nRestriccions;
	
	public Plat(String nom, int codiReferencia, double preu, int nRestriccions)
	{
		super(nom, codiReferencia, preu);
		restriccions = new RestriccionsAlimentaries[nRestriccions];
		this.nRestriccions = 0;
	}
	
	public void afegirRestriccions(RestriccionsAlimentaries r)
	{
		restriccions[nRestriccions] = r;
		nRestriccions++;
	}
	
	public boolean esApte(RestriccionsAlimentaries[] r)
	{
		return true;
	}
	

}
