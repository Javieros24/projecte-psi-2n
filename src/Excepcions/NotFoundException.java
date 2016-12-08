package Excepcions;

/**Classe que extend Exception. Aquesta excepcio serveix per quan no es troba un objecte a una taula d'objectes
 *
 *@author GRUP 10
 */
public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	public NotFoundException(){
		super("Error, no s'ha trobat l'element.") ;
	}
}