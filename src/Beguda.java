
public class Beguda extends Producte  
{
	private int volum;
	private boolean alcohol;
	
	public Beguda(String nom, double preu, int volum, boolean alcohol)
	{
		super(nom,preu);
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
