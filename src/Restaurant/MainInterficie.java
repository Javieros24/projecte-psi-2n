package Restaurant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import Excepcions.NotFoundException;
import InterficieGrafica.*;
import Llistes.LlistaClients;
import Llistes.LlistaProductes;
import Productes.Beguda;
import Productes.Plat;
import Productes.Producte;
import Productes.RestriccionsAlimentaries;

public class MainInterficie {
	
	private static LlistaProductes llistaProductes;
	private static LlistaClients llistaClients;

	public static void main(String[] args) {
		carregarProductes();
		carregarClients();
		carregarComandes();
		Client[] client = llistaClients.getLlistaClients();
		new Historial("Historial de comandes", client[0]);
		//new Login("Login", llistaClients, llistaProductes);
	}
	
	//FITXERS
	
	private static void carregarProductes(){
		
		llistaProductes = new LlistaProductes(100);
		try {
			BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Productes.txt"));
			String linia;
			StringTokenizer token;
			String tipus, nom;
			double preu;
			int ref, volum;
			RestriccionsAlimentaries[] r;
			boolean alcohol;

			linia = lectura.readLine();
			while (linia != null) {
				token = new StringTokenizer(linia, ",");
				tipus = token.nextToken();
				nom = token.nextToken();
				preu = Double.parseDouble(token.nextToken());
				ref = Integer.parseInt(token.nextToken());
				if (tipus.equalsIgnoreCase("PLAT")) {
					r = new RestriccionsAlimentaries[Integer.parseInt(token.nextToken())];
					for (int i = 0; i < r.length; i++) {
						r[i] = RestriccionsAlimentaries.valueOf(token.nextToken());
					}
					llistaProductes.afegirElement(nom, preu, r, ref);
				} else if (tipus.equalsIgnoreCase("BEGUDA")) {
					volum = Integer.parseInt(token.nextToken());
					alcohol = token.nextToken().equalsIgnoreCase("SI");
					llistaProductes.afegirElement(nom, preu, volum, alcohol, ref);
				}
				linia = lectura.readLine();
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! La llista de productes és plena.");
		}
	}
	
	private static void carregarClients(){
		
		llistaClients = new LlistaClients(100);
		try {
			BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Clients.txt"));
			String linia;
			StringTokenizer token;
			String nom, adreca, nomUsuari, contrasenya;
			int codiClient, numTelefon, numRestriccions;
			RestriccionsAlimentaries[] r;
			
			linia = lectura.readLine();
			while(linia != null){
				token = new StringTokenizer(linia, ",");
				nom = token.nextToken();
				codiClient = Integer.parseInt(token.nextToken());
				adreca = token.nextToken();
				nomUsuari = token.nextToken();
				contrasenya = token.nextToken();
				numTelefon = Integer.parseInt(token.nextToken());
				numRestriccions = Integer.parseInt(token.nextToken());
				r = new RestriccionsAlimentaries[numRestriccions];
				for (int i=0; i < r.length; i++){
					r[i] = RestriccionsAlimentaries.valueOf(token.nextToken());
				}
				llistaClients.afegirElement(nom, adreca, nomUsuari, contrasenya, numTelefon, r, codiClient);
				linia = lectura.readLine();
			}
			lectura.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR! La llista de clients és plena.");
		}
		}                        

	private static void carregarComandes(){
	
	try {
		BufferedReader lectura = new BufferedReader(new FileReader("src/Fitxers/Comandes.txt"));
		String linia, horaComanda;
		StringTokenizer token;
		Client client=null;
		Comanda c;
		Producte p;
		int refClient, refComanda, refProducte,numProd;
		linia = lectura.readLine();
		while(linia != null){
			try {
				token = new StringTokenizer(linia, ",");
				refClient = Integer.parseInt(token.nextToken());
				client=llistaClients.consultarElement(refClient);
				refComanda = Integer.parseInt(token.nextToken());
				horaComanda = token.nextToken();
				numProd = Integer.parseInt(token.nextToken());
				c=new Comanda(numProd,refComanda, horaComanda);
				
				for(int i=0;i<numProd;i++)
				{
					refProducte = Integer.parseInt(token.nextToken());
					p=llistaProductes.consultarElement(refProducte);
					c.afegirProducte(p, 1);
				}
				client.afegirComanda(c);
				
				linia = lectura.readLine();
			} catch (NotFoundException e) {
				linia = lectura.readLine();
				//Si un producte de la llista ha estat eliminat, aquella comanda no s'afegeix
			}
		}
		lectura.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	} catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("ERROR! La llista de productes és plena.");
	}
}
	
	private static void guardarProducte(Producte p){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Productes.txt", true));
				
			if (p instanceof Plat){
				escriptura.write("PLAT,");
				escriptura.write(p.getNom()+",");
				escriptura.write(p.getPreu()+",");
				escriptura.write(p.getCodiReferencia()+",");
				escriptura.write(String.valueOf(((Plat) p).getRestriccions().length));
				RestriccionsAlimentaries r[] = ((Plat) p).getRestriccions();
				for (int i=0; i < r.length; i++)
					escriptura.write(","+r[i]);
				escriptura.write("\n");
			}
			else if (p instanceof Beguda){
				escriptura.write("BEGUDA,");
				escriptura.write(p.getNom()+",");
				escriptura.write(p.getPreu()+",");
				escriptura.write(p.getCodiReferencia()+",");
				escriptura.write(((Beguda) p).getVolum()+",");
				if (((Beguda) p).getAlcohol())
					escriptura.write("SI\n");
				else
					escriptura.write("NO\n");
			}
			escriptura.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void guardarClient(Client c){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Clients.txt", true));
			escriptura.write(c.getNom()+",");
			escriptura.write(c.getIdentificador()+",");
			escriptura.write(c.getAdreca()+",");
			escriptura.write(c.getNomUsuari()+",");
			escriptura.write(c.getContrasenya()+",");
			escriptura.write(c.getNumTelefon()+",");
			escriptura.write(String.valueOf(c.getRestriccions().length));
			for (int i=0; i < c.getRestriccions().length; i++){
				escriptura.write(","+c.getRestriccions()[i]);
			}
			escriptura.write("\n");
			escriptura.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void guardarComanda(int RefClient, Comanda c){
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Comandes.txt", true));
			Producte[] llista = c.getLlista();
			escriptura.write(RefClient+",");
			escriptura.write(c.getCodiComanda()+",");
			escriptura.write(String.valueOf(c.getNumProd()));
			for(int i=0;i<c.getNumProd();i++){
				escriptura.write("," + llista[i].getCodiReferencia());
			}
			escriptura.write(c.getHoraComanda()+"\n");
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		} //nullPointerException
	}
	
	private static void sobreescriureProductes(){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Productes.txt"));
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Producte[] llista = llistaProductes.getLlistaProductes();
		for(int i=0; i<llistaProductes.getnElements();i++){
			guardarProducte(llista[i]);
		}
	}
	
	private static void sobreescriureComandes(){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Comandes.txt"));
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Client[] client=llistaClients.getLlistaClients();
		Comanda[] llistaComandes;
		for(int j=0; j<llistaClients.getnElements(); j++){
			llistaComandes = client[j].getTaulaComandes();
			for(int i=0; i<client[j].getNumComandes();i++){
				guardarComanda(client[j].getIdentificador(), llistaComandes[i]);
			}
		}
	}
	
	private static void sobreescriureClients(){
		
		try {
			BufferedWriter escriptura = new BufferedWriter(new FileWriter("src/Fitxers/Clients.txt"));
			escriptura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Client[] llista = llistaClients.getLlistaClients();
		for(int i=0; i<llistaClients.getnElements();i++){
			guardarClient(llista[i]);
		}
	}

}
