
public class LlistaComandes {
	private Comanda[] llistaComandes;
	private int nElements;
	
	public LlistaComandes(){
		
	}
	
	public void afegirElement(Comanda c){
		llistaComandes[nElements] = c;
		nElements++;
	}
	
	public void eliminarComanda(){
		
	}
}
