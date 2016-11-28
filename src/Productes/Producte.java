package Productes;
/**
 * Superclasse producte amb els seus atributs
 */
public abstract class Producte 
{

	protected String nom;
	protected int codiReferencia;
	protected double preu;
	protected double descompte;
	
	/**
	 * Contructor que rep per parametre els atributs i els inicialitza
	 * @param nom es el nom del producte
	 * @param preu es el preu del producte
	 */
	public Producte (String nom, double preu, int codiRef)
	{
		this.nom=nom;
		this.codiReferencia=codiRef;
		this.preu=preu;
		descompte=preu*0.05;
	}
	
	//******************* GETTER'S & SETTER'S********************************************************
	
	public void setNom(String nom) {
		this.nom = nom;
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
		return "\n***PRODUCTE***\t[ref: " + codiReferencia + "]\n-Nom: "+this.getNom() +"\n-Preu " + preu + "€";
	}

}
