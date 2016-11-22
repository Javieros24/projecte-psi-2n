
public class LlistaClients {
	private Client[] llistaClients;
	private int nElements;
	
	public LlistaClients(){
		
	}
	
	public void afegirElement(String nom, String adreca, String nomUsuari, String contrasenya, int numTelefon, RestriccionsAlimentaries[] restriccions){
		llistaClients[nElements] = new Client(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions);
		nElements++;
	}
	
	public void eliminarElement(int  ref){
		int  i;
		
		i=buscarElement(ref);
		
		for(; i<nElements-1; i++){
			llistaClients[i]=llistaClients[i+1];
		}
		
		System.out.println("S'ha eliminat el client correctament");
		nElements--;
	}
	
	/**M�tode que busca un client a partir d'un codi, -1 en cas de no trobar-lo
	 * 
	 * @param codi identificador del client a buscar
	 * @return posici� de la taula on es troba el client, -1 en cas de no trobar-lo
	 */
	public int buscarElement(int ref){
	
		for (int i=0; i < nElements; i++){
			if (llistaClients[i].getIdentificador() == ref)
				return i;
		}
		
		//EXCEPCIO!
		return -1;
	}
	
	public Client consultarElement(int ref) throws NotFoundException
	{
		
		for (int i=0; i < nElements; i++){
			if (llistaClients[i].getIdentificador() == ref)
				return llistaClients[i];
			
		}
		throw new NotFoundException();
	}

	public Client[] getLlistaClients() {
		return llistaClients;
	}

	public int getnElements() {
		return nElements;
	}
}
