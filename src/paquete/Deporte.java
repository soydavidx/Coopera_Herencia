package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Deporte {

	public static ArrayList<Deporte> Deportes = new ArrayList<Deporte>();
	private String nombre;
	//numero de participantes
	private int Nparticipantes;
	
	public Deporte(String nombre, int nparticipantes) {
		this.nombre = nombre;
		Nparticipantes = nparticipantes;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNparticipantes() {
		return Nparticipantes;
	}
	public void setNparticipantes(int nparticipantes) {
		Nparticipantes = nparticipantes;
	}
	
	public static void crearDeportes() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/Deportes.txt"));
		// AQUI GUARDAREMOS LAS LINEAS DEL FICHERO
		String line;
		// CON ESTE WHILE PODREMOS LEER LOS DATOS DEL FICHERO
		while ((line = br.readLine()) != null) {
			String[] datos = line.split(", ");
			Deportes.add(new Deporte(datos[0], Integer.parseInt(datos[1])));
		}
	}
}
