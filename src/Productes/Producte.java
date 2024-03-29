package Productes;
/**
 * Superclasse producte amb els seus atributs
 * @author GRUP 10
 * 
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
		return this.getNom() +" - "+ preu + "�";
	}

}
