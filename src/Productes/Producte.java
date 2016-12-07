package Productes;
/**
 * @author Sergi Quevedo Garreta
 * Superclasse producte amb els seus atributs
 * @version 1.0
 */
public abstract class Producte 
{
	//Atributs protected ja que la classe es abstracte
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
	
	/**
	 * Setter
	 * @param nom es el nom del proudcte
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Setter
	 * @param preu es el preu del producte
	 */
	public void setPreu(double preu) {
		this.preu = preu;
	}
	
	/**
	 * Setter
	 * @param descompte es el descompte que s'aplica en els clients preferents
	 */
	public void setDescompte(double descompte) {
		this.descompte = descompte;
	}
	
	/**
	 * Getter
	 * @return el nom del producte
	 */
	public String getNom() 
	{
		return nom;
	}
	
	/**
	 * Getter
	 * @return el codi de referencia del producte
	 */
	public int getCodiReferencia() 
	{
		return codiReferencia;
	}

	/**
	 * Getter
	 * @return el preu del producte
	 */
	public double getPreu() 
	{
		return preu;
	}
	
	/**
	 * Getter
	 * @return el descompte del producte en els clients preferents
	 */
	public double getDescompte() 
	{
		return descompte;
	}
	
	/**
	 * Metode per mostrar per pantalla els atributs del producte
	 */
	public String toString() 
	{
		return this.getNom() +" - "+ preu + "€";
	}

}
