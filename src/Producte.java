
public abstract class Producte 
{
	protected String nom;
	protected int codiReferencia;
	protected double preu;
	protected double descompte;
	protected static int codiProductes=1;
	
	
	public Producte (String nom, double preu)
	{
		this.nom=nom;
		this.codiReferencia=codiProductes;
		codiProductes++;
		this.preu=preu;
		
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCodiReferencia(int codiReferencia) {
		this.codiReferencia = codiReferencia;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}

	public void setDescompte(double descompte) {
		this.descompte = descompte;
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
