package Excepcions;

public class IllegalCharException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public IllegalCharException(){
		super("ERROR! El valor ',' no és permès.");
	}
}
