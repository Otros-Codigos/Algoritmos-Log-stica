package main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.data_structures.Graph;
import model.logic.GoogleMap;
import model.logic.Modelo;

public class Main {
	
	public final static String ARCOS = "./data/arcos.txt";
	public final static String VERTICES= "./data/vertices.txt";
	
	private static Modelo modelo = new Modelo();
	// private static GoogleMap goog = new GoogleMap();
	
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		System.out.println("---------------------------------------");
		System.out.println("--- VAMOS A CARGAR EL GRAFO ---");
		System.out.println("---------------------------------------");
		
		modelo.leerTxtVertix(VERTICES);
		modelo.leerTxtArc(ARCOS);
		
		System.out.println("---------------------------------------");
		System.out.println("--- CAMINO MÁS CORTO --- ");
		System.out.println("---------------------------------------");
		
		 Graph mapita = modelo.SPDosUbicaciones(1, 16);
		// ArrayList<Integer> vertices = modelo.darVerticesDelGrafo();
		
		// System.out.println("---------------------------------------");
		// System.out.println("--- PINTANDO GRAFO --- ");
		// System.out.println("---------------------------------------");
		
		// goog.imprmirElMejorGrafoDeLaHistoria(mapita, vertices);
		
	}
}
