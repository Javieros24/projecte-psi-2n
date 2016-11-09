
public class Beguda extends Producte  
{
	private int volum;
	private boolean alcohol;
	
	public Beguda(String nom, int codiReferencia, double preu, int volum, boolean alcohol)
	{
		super(nom, codiReferencia, preu);
		this.alcohol= alcohol;
		this.volum=volum;
	}

	public int getVolum() 
	{
		return volum;
	}

	public boolean getAlcohol() 
	{
		return alcohol;
	}

}
