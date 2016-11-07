
public class Plats extends Productes 
{
	public enum RestriccionsAlimentaries
	{
		CELIACS, ALERGICSLACTOSA, ALERGISCFRUITSSECS;
	}
	
	public Plats(String nom, int codiReferencia, double preu, double descompte)
	{
		super(nom, codiReferencia, preu, descompte);
	}

	private boolean apte;
	
	private RestriccionsAlimentaries(boolean apte)
	{
		this.apte=apte;
	}
	
	public boolean getApte()
	{
		return(this.apte);
	}
	

}
