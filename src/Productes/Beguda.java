package Productes;

/**
 * Subclasse beguda que hereda de la classe producte
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
		super(nom,preu, codiReferencia);
		this.alcohol= alcohol;
		this.volum=volum;
	}
	
	//*******************************GETTER'S & SETTER'S*****************************************************
	
	public void setVolum(int volum) 
	{
		this.volum = volum;
	}

	public int getVolum() 
	{
		return volum;
	}

	public boolean getAlcohol() 
	{
		return alcohol;
	}

	/**
	 * Metode per visualitzar per pantalla els atributs de la beguda
	 */
	public String toString() {
		return super.toString().concat("-Volum: "+volum+ "ml\n-Beguda alcohòlica: " +alcohol+"\n**************");
	}
	
	

}
