package Excepcions;

/**Classe que extend Exception. Aquesta excepcio serveix per quan un client o un producte que
 * s'intenta afegir ja existeix, és a dir, té el mateix nom, saltarà aquesta excepció
 *
 *@author GRUP 10
 */
public class DuplicatedNameException extends Exception{

	private static final long serialVersionUID = 1L;

	public DuplicatedNameException(){
		super("ERROR! l'element que s'intenta afegir ja existeix");
	}
}
