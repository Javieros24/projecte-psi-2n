
public class Productes 
{
	String nom;
	int codiReferencia;
	double preu;
	double descompte;
	
	
	public Productes (String nom, int codiReferencia, double preu, double descompte)
	{
		this.nom=nom;
		this.codiReferencia=codiReferencia;
		this.preu=preu;
		this.descompte=descompte;
		
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
