package Excepcions;

public class DuplicatedNameException extends Exception{

	public DuplicatedNameException(){
		super("ERROR! l'element que s'intenta afegir ja existeix");
	}
}
