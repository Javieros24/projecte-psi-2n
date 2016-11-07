
public class Beguda extends Productes  
{
	int volum;
	boolean alcohol;
	
	
	public Beguda(String nom, int codiReferencia, double preu, double descompte, int volum)
	{
		super(nom, codiReferencia, preu, descompte);
		alcohol=false;
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
