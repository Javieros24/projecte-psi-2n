
public abstract class Producte 
{
	protected String nom;
	protected int codiReferencia;
	protected double preu;
	protected double descompte;
	
	
	public Producte (String nom, int codiReferencia, double preu)
	{
		this.nom=nom;
		this.codiReferencia=codiReferencia;
		this.preu=preu;
		
	}

	public String getNom() 
	{
		return nom;
	}

	public int getCodiReferencia() 
	{
		return codiReferencia;
	}


	public double getPreu() 
	{
		return preu;
	}

	public double getDescompte() 
	{
		return descompte;
	}

}
