
public class Plat extends Producte 
{
	
	public Plat(String nom, int codiReferencia, double preu, double descompte)
	{
		super(nom, codiReferencia, preu, descompte);
	}

	private boolean apte;
	
	private void RestriccionsAlimentaries(boolean apte)
	{
		this.apte=apte;
	}
	
	public boolean getApte()
	{
		return(this.apte);
	}
	

}
