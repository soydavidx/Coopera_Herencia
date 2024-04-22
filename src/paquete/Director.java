package paquete;

import java.io.IOException;
import java.util.Scanner;

public class Director extends Persona {
	private static int contador = 1;

	public Director(String nombre, String apellido, String profesion, int equipo) {
		super(nombre, apellido, profesion, equipo);
		super.setId(contador);
		contador++;
	}
	public static void MenuModificarJugador() {
		//cambiar en el menu principal donde ponga intercambio a MenuModificarJugador
		//preguntara al usuario que opcion elegir entre vender , fichar o intercambio
		//invocar el metodo vender , fichar o intercambio con un switch
	}
	
	public static void VenderJugador() {
		//1.mostrar jugadores en el equipo
		//2.elegir un jugador del equipo
		//3.modificar el idEquipo del jugador a 0
		//4.anadir el jugador a la bolsa
		//5.eliminar del equipo
	}
	public static void FicharJugadorBolsa() {
		//1.mostrar jugadores disponibles en la bolsa
		//2.elegir un jugador de la bolsa
		//3.modificar el idEquipo del jugador de 0 al de equipo fichado
		//4.anadir el jugador al equipo
		//5 quitar el presupuesto del equipo segun el valor del jugador
		//6.eliminar de la bolsa
	}

	public static void intercambio() throws IOException {
		Scanner scanner = new Scanner(System.in);
		String nombreEquipo1;
		String nombreEquipo2;
		Equipo equipo1 = null;
		Equipo equipo2 = null;
		Persona jugador1 = null;
		Persona jugador2 = null;
		
		boolean equipo1Existe = false;
		boolean equipo2Existe = false;
		
		
		while (!equipo1Existe) {
			System.out.println("Que equipo va a realizar el fichaje");
			nombreEquipo1 = scanner.nextLine().toLowerCase();
			for (Equipo Equipo : Equipo.ListaEquipos) {
				if (Equipo.getNombreEquipo().toLowerCase().equals(nombreEquipo1)) {
					equipo1Existe = true;
					equipo1 = Equipo;
					break;
				}
			}
			if (!equipo1Existe)
				System.out.println("Ese equipo no existe");
		}
		
		while (!equipo2Existe) {
			System.out.println("Con que equipo desea realizar el intercambio");
			nombreEquipo2 = scanner.nextLine().toLowerCase();
			for (Equipo Equipo : Equipo.ListaEquipos) {
				if (Equipo.getNombreEquipo().toLowerCase().equals(nombreEquipo2) && equipo1.getDeporte() == Equipo.getDeporte() && equipo1 != equipo2) {
					equipo2Existe = true;
					equipo2 = Equipo;
					break;
				}
			}
			if (!equipo2Existe)
				System.out.println("No existe un equipo con ese nombre con el que puedas intercambiar jugadores");
		}
		
		//Aqui pides jugadores
		int cont = 0;
		System.out.println("Selecciona el jugador que quieres");
		for (Persona persona : equipo2.getGrupoPersonales()) {
			if (persona.getProfesion().equals("jugador")) {
				cont++;
				System.out.println(cont + ". " + persona.getNombre() + " " + persona.getApellido());
			}
		}

		int num = scanner.nextInt();

		cont = 0;
		for (Persona persona : equipo2.getGrupoPersonales()) {
			if (persona.getProfesion().equals("jugador")) {
				cont++;
				if (cont == num) {
					System.out.println("has fichado a " + persona.getNombre());
					jugador1 = persona;
				}
			}
		}
		
		if (jugador1 == null) {
			System.out.println("Ese valor no es valido");
			return;
		}
			
		cont = 0;
		System.out.println("Selecciona el jugador que quieres dar a cambio");
		for (Persona persona : equipo1.getGrupoPersonales()) {
			if (persona.getProfesion().equals("jugador")) {
				cont++;
				System.out.println(cont + ". " + persona.getNombre() + " " + persona.getApellido());
			}
		}
		
		num = scanner.nextInt();
		
		cont = 0;
		for (Persona persona : equipo1.getGrupoPersonales()) {
			if (persona.getProfesion().equals("jugador")) {
				cont++;
				if (cont == num) {
					System.out.println("has dado a " + persona.getNombre());
					int idEquipo = jugador1.getIdEquipo();
					jugador2 = persona;
					jugador1.setIdEquipo(jugador2.getIdEquipo());
					jugador2.setIdEquipo(idEquipo);
				}
			}
		}
		
		if (jugador2 == null) {
			System.out.println("Ese valor no es valido");
			return;
		}
		equipo1.setUltimoIntercambio("Ofrecieron a " + jugador2.getNombre() + " " + jugador2.getApellido() + " por " + jugador1.getNombre() + " " + jugador1.getApellido());
		equipo2.setUltimoIntercambio("Ofrecieron a " + jugador1.getNombre() + " " + jugador1.getApellido() + " por " + jugador2.getNombre() + " " + jugador2.getApellido());
		Equipo.actualizarEquipos();
	}
}
