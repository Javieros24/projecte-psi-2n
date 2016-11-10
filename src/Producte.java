/**
 * Superclasse producte amb els seus atributs
 */
public abstract class Producte 
{

	protected String nom;
	protected int codiReferencia;
	protected double preu;
	protected double descompte;
	protected static int codiProductes=1;
	
	/**
	 * Contructor que rep per parametre els atributs i els inicialitza
	 * @param nom es el nom del producte
	 * @param preu es el preu del producte
	 */
	public Producte (String nom, double preu)
	{
		this.nom=nom;
		this.codiReferencia=codiProductes;
		codiProductes++;
		this.preu=preu;
		descompte=preu*0.05;
	}
	
	//******************* GETTER'S & SETTER'S********************************************************
	
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
	
	/**
	 * Metode per mostrar per pantalla els atributs del producte
	 */
	public String toString() 
	{
		return "Producte [nom=" + nom + ", codiReferencia=" + codiReferencia + ", preu=" + preu + ", descompte="
				+ descompte + "]";
	}

}
