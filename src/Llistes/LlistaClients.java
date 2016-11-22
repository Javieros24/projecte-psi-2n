package Llistes;
import Excepcions.NotFoundException;
import Productes.RestriccionsAlimentaries;
import Restaurant.Client;

public class LlistaClients {
	private Client[] llistaClients;
	private int nElements;
	
	public LlistaClients(int num){
		llistaClients = new Client[num];
	}
	
	public void afegirElement(String nom, String adreca, String nomUsuari, String contrasenya, int numTelefon, RestriccionsAlimentaries[] restriccions){
		llistaClients[nElements] = new Client(nom, adreca, nomUsuari, contrasenya, numTelefon, restriccions);
		nElements++;
	}
	
	public void eliminarElement(int  ref) throws NotFoundException{
		int  i;
		
		i=buscarElement(ref);
		
		for(; i<nElements-1; i++){
			llistaClients[i]=llistaClients[i+1];
		}
		nElements--;
	}
	
	/**Mètode que busca un client a partir d'un codi, -1 en cas de no trobar-lo
	 * 
	 * @param codi identificador del client a buscar
	 * @return posició de la taula on es troba el client, -1 en cas de no trobar-lo
	 */
	public int buscarElement(int ref) throws NotFoundException{
	
		for (int i=0; i < nElements; i++){
			if (llistaClients[i].getIdentificador() == ref)
				return i;
		}
		
		throw new NotFoundException();
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
