package paquete;

import java.io.IOException;
import java.util.Scanner;

public class Director extends Persona {
	static String barra = "-------------------------------------";
	private static int contador = 1;
	
	public Director(String nombre, String apellido, String profesion, int equipo) {
		super(nombre, apellido, profesion, equipo);
		super.setId(contador);
		contador++;
	}
	public static void MenuModificarJugador(Scanner scanner) throws IOException {
		//El usuario ingresa a un menu exclusivo para la bolsa de jugadores disponibles
		System.out.println("Bienvenido a la bolsa de jugadores de NavesLiga!\n");
		int opcionUsuario = 0;
		
		do {
			System.out.println("Ingresa que opcion deseas realizar:");
			System.out.println("1. Comprar un jugador");
			System.out.println("2. Vender un jugador");
			System.out.println("3. Intercambiar un jugador");
			System.out.println("4. Salir de la Bolsa");
	
			//Se comprueba si el ususario ha ingresado un numero o un caracter invalido
			if (scanner.hasNextInt()) {
				opcionUsuario = scanner.nextInt();
				if(scanner.hasNextLine()){
					scanner.nextLine();
					}
				//Se comprueba a que clase debe dirigirse segun la opcion del usuario
				opcionesBolsa(opcionUsuario, scanner);
			} else {
				System.err.println("No has ingresado una opcion correcta dentro del sistema, intentalo nuevamente");
				scanner.nextLine();
			}
		} while (opcionUsuario != 4);
		
		if (opcionUsuario == 4) {
			System.out.println("Vuelve a la bolsa de NavesLiga cuando quieras!");
		}
	}
	
	public static void opcionesBolsa(int opcionUsuario,Scanner scanner) throws IOException {
		switch (opcionUsuario) {
		case 1: {
			System.out.println("Comprar un jugador");
			FicharJugadorBolsa();
			System.out.println(barra);
			break;
		}
		case 2:{
			System.out.println("Vender un jugador");
			VenderJugador();
			System.out.println(barra);
			break;
		}
		case 3:{
			System.out.println("Intercambiar un jugador");
			System.out.println(barra);
			intercambio(scanner);
			break;
		}
		case 4:{
			System.out.println("Salir de la bolsa de NavesLiga");
			System.out.println(barra);
			break;
		}
		default:
			System.out.println("No hay una opcion disponible para el numero ingresado");
			System.out.println("Intentalo nuevamente y escoge una opcion valida.\n");
		}
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

	
	public static void intercambio(Scanner scanner) throws IOException {

		String nombreEquipo1;
		String nombreEquipo2;
		String nombreDeporte;
		Deporte deporteFichaje = null;
		Equipo equipo1 = null;
		Equipo equipo2 = null;
		Persona jugador1 = null;
		Persona jugador2 = null;

		for (Deporte deporteExistente : Deporte.Deportes)
			System.out.println(deporteExistente.getNombre());

		while (deporteFichaje == null) {
			System.out.println("\nSeleccione un deporte");
			nombreDeporte = scanner.nextLine().toLowerCase();
			for (Deporte deporte : Deporte.Deportes) {
				if (deporte.getNombre().toLowerCase().equals(nombreDeporte)) {
					deporteFichaje = deporte;
					break;
				}
			}
			if (deporteFichaje == null)
				System.out.println("Ese deporte no existe");
		}

		for (Equipo equipoDeporte : Equipo.ListaEquipos) {
			if (equipoDeporte.getDeporte() == deporteFichaje)
				System.out.println(equipoDeporte.getNombreEquipo());
		}

		while (equipo1 == null) {
			System.out.println("\nQue equipo va a realizar el fichaje");
			nombreEquipo1 = scanner.nextLine().toLowerCase();
			for (Equipo Equipo : Equipo.ListaEquipos) {
				if (Equipo.getNombreEquipo().toLowerCase().equals(nombreEquipo1)
						&& Equipo.getDeporte() == deporteFichaje) {
					equipo1 = Equipo;
					break;
				}
			}
			if (equipo1 == null)
				System.out.println("Ese equipo no existe");
		}

		for (Equipo equipoDeporte : Equipo.ListaEquipos) {
			if (equipoDeporte.getDeporte() == deporteFichaje && equipoDeporte != equipo1)
				System.out.println(equipoDeporte.getNombreEquipo());
		}

		while (equipo2 == null) {
			System.out.println("\nCon que equipo desea realizar el intercambio");
			nombreEquipo2 = scanner.nextLine().toLowerCase();
			for (Equipo Equipo : Equipo.ListaEquipos) {
				if (Equipo.getNombreEquipo().toLowerCase().equals(nombreEquipo2)
						&& equipo1.getDeporte() == Equipo.getDeporte() && equipo1 != equipo2) {
					equipo2 = Equipo;
					break;
				}
			}
			if (equipo2 == null)
				System.out.println("No existe un equipo con ese nombre con el que puedas intercambiar jugadores");
		}

		// Aqui pides jugadores
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
		equipo1.setUltimoIntercambio("Ofrecieron a " + jugador2.getNombre() + " " + jugador2.getApellido() + " por "
				+ jugador1.getNombre() + " " + jugador1.getApellido());
		equipo2.setUltimoIntercambio("Ofrecieron a " + jugador1.getNombre() + " " + jugador1.getApellido() + " por "
				+ jugador2.getNombre() + " " + jugador2.getApellido());
		Equipo.actualizarEquipos();
	}
}
