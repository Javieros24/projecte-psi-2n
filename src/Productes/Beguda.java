package Productes;

/**
 * @author Sergi Quevedo Garreta
 * Subclasse beguda que hereda de la classe producte
 * @version 1.0
 */
public class Beguda extends Producte  
{
	private int volum;
	private boolean alcohol;
	
	/**
	 * Constructor que rep per parametre els atributs de classe i els inicialitza
	 * @param nom es el nom de la beguda
	 * @param preu es el preu de la beguda
	 * @param volum es el volum d'alcohol que porta
	 * @param alcohol es un boolea: SI, si porta alcohol, NO si no porta alcohol
	 */
	public Beguda(String nom, double preu, int volum, boolean alcohol, int codiReferencia)
	{
		super(nom,preu, codiReferencia);										//Inicialitza els atribut de la classe pare
		this.alcohol= alcohol;
		this.volum=volum;
	}
	
	/**
	 * Setter
	 * @param volum es el volum de la beguda
	 */
	public void setVolum(int volum) 
	{
		this.volum = volum;
	}
	
	/**
	 * Getter
	 * @return el volum de la beguda
	 */
	public int getVolum() 
	{
		return volum;
	}
	
	/**
	 * Getter
	 * @return true si la beguda porta alcohol i false si no no porta alcohol
	 */
	public boolean getAlcohol() 
	{
		return alcohol;
	}

	public String toString(){
		return super.toString()+"     [Beguda]";
	}
	
	

}
