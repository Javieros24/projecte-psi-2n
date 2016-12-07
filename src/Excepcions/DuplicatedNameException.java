package Excepcions;

/**Classe que extend Exception. Aquesta excepcio serveix per quan un client o un producte que
 * s'intenta afegir ja existeix, �s a dir, t� el mateix nom, saltar� aquesta excepci�
 *
 *@author GRUP 10
 */
public class DuplicatedNameException extends Exception{

	private static final long serialVersionUID = 1L;

	public DuplicatedNameException(){
		super("ERROR! l'element que s'intenta afegir ja existeix");
	}
}
