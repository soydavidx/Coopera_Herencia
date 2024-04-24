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
		// El usuario ingresa a un menu exclusivo para la bolsa de jugadores disponibles
		System.out.println("Bienvenido a la bolsa de jugadores de NavesLiga!\n");
		int opcionUsuario = 0;

		do {
			System.out.println("Ingresa que opcion deseas realizar:");
			System.out.println("1. Comprar un jugador");
			System.out.println("2. Vender un jugador");
			System.out.println("3. Intercambiar un jugador");
			System.out.println("4. Salir de la Bolsa");

			// Se comprueba si el ususario ha ingresado un numero o un caracter invalido
			if (scanner.hasNextInt()) {
				opcionUsuario = scanner.nextInt();
				if (scanner.hasNextLine()) {
					scanner.nextLine();
				}
				// Se comprueba a que clase debe dirigirse segun la opcion del usuario
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

	public static void opcionesBolsa(int opcionUsuario, Scanner scanner) throws IOException {
		switch (opcionUsuario) {
		case 1: {
			System.out.println("Comprar un jugador");
			FicharJugadorBolsa(scanner);
			System.out.println(barra);
			break;
		}
		case 2: {
			System.out.println("Vender un jugador");
			VenderJugador(scanner);
			System.out.println(barra);
			break;
		}
		case 3: {
			System.out.println("Intercambiar un jugador");
			System.out.println(barra);
			intercambio(scanner);
			break;
		}
		case 4: {
			System.out.println("Salir de la bolsa de NavesLiga");
			System.out.println(barra);
			break;
		}
		default:
			System.out.println("No hay una opcion disponible para el numero ingresado");
			System.out.println("Intentalo nuevamente y escoge una opcion valida.\n");
		}
	}

	public static void VenderJugador(Scanner scanner) throws IOException {

		Deporte deporteEquipo = null;
		Equipo equipoVenta = null;
		Persona jugadorVenta = null;

		for (Deporte deporteExistente : Deporte.Deportes)
			System.out.println(deporteExistente.getNombre());

		do {
			System.out.println("\nSeleccione un deporte");
			String nombreDeporte = scanner.nextLine().toLowerCase();

			for (Deporte deporte : Deporte.Deportes) {
				if (deporte.getNombre().toLowerCase().equals(nombreDeporte))
					deporteEquipo = deporte;
			}
			if (deporteEquipo == null)
				System.out.println("Ese deporte no existe");

		} while (deporteEquipo == null);

		for (Equipo equipoDeporte : Equipo.ListaEquipos) {
			if (equipoDeporte.getDeporte() == deporteEquipo)
				System.out.println(equipoDeporte.getNombreEquipo());
		}

		do {
			System.out.println("\nQue equipo va a realizar la venta");
			String nombreEquipo = scanner.nextLine().toLowerCase();

			for (Equipo equipo : Equipo.ListaEquipos) {
				if (equipo.getNombreEquipo().toLowerCase().equals(nombreEquipo) && equipo.getDeporte() == deporteEquipo)
					equipoVenta = equipo;
			}
			if (equipoVenta == null)
				System.out.println("Ese equipo no esta en la lista");

		} while (equipoVenta == null);

		int cont = 0;
		for (Persona persona : equipoVenta.getGrupoPersonales()) {
			if (persona.getProfesion().toLowerCase().equals("jugador"))
				cont++;
		}

		if (equipoVenta.getDeporte().getNparticipantes() < cont) {
			
			cont = 0;
			for (Persona persona : equipoVenta.getGrupoPersonales()) {
				if (persona.getProfesion().toLowerCase().equals("jugador")) {
					cont++;
					System.out.println(cont + ". " + persona.getNombre() + " " + persona.getApellido() + " " + String.format("%.2f", ((Jugador) persona).getValor()) + "€");
				}
			}
			
			System.out.println("\nQue jugador desea vender");
			int num = 0;

			while (true) {
				System.out.println("Introduce un numero");
				
				if (scanner.hasNextInt()) {
					num = scanner.nextInt();
					if (num <= cont && num > 0)
						break;
					else {
						System.out.println("Debes introducir un numero valido.");
						scanner.nextLine();
					}
				} else {
					System.out.println("Debes introducir un numero valido.");
					scanner.nextLine();
				}
			}

			cont = 0;
			for (Persona jugador : equipoVenta.getGrupoPersonales()) {
				cont++;
				if (num == cont && jugador.getProfesion().toLowerCase().equals("jugador"))
					jugadorVenta = jugador;
			}

			System.out.println("El jugador " + jugadorVenta.getNombre() + " " + jugadorVenta.getApellido()
					+ " ha sido vendido a la bolsa");
			jugadorVenta.setIdEquipo(0);
			equipoVenta.setPresupuesto(equipoVenta.getPresupuesto() + ((Jugador) jugadorVenta).getValor());
			Equipo.actualizarEquipos();
		} else
			System.out.println("este equipo tiene el numero minimo de integrantes para formar una plantilla");
	}

	public static void FicharJugadorBolsa(Scanner scanner) throws IOException {

		Deporte deporteEquipo = null;
		Equipo equipoFichaje = null;
		Persona jugadorFichaje = null;

		if (Equipo.Bolsa.size() > 0) {

			for (Deporte deporteExistente : Deporte.Deportes)
				System.out.println(deporteExistente.getNombre());

			do {
				System.out.println("\nSeleccione un deporte");
				String nombreDeporte = scanner.nextLine().toLowerCase();

				for (Deporte deporte : Deporte.Deportes) {
					if (deporte.getNombre().toLowerCase().equals(nombreDeporte))
						deporteEquipo = deporte;
				}
				if (deporteEquipo == null)
					System.out.println("Ese deporte no existe");

			} while (deporteEquipo == null);

			for (Equipo equipoDeporte : Equipo.ListaEquipos) {
				if (equipoDeporte.getDeporte() == deporteEquipo)
					System.out.println(equipoDeporte.getNombreEquipo());
			}

			do {
				System.out.println("\nQue equipo va a realizar la venta");
				String nombreEquipo = scanner.nextLine().toLowerCase();

				for (Equipo equipo : Equipo.ListaEquipos) {
					if (equipo.getNombreEquipo().toLowerCase().equals(nombreEquipo)
							&& equipo.getDeporte() == deporteEquipo)
						equipoFichaje = equipo;
				}
				if (equipoFichaje == null)
					System.out.println("Ese equipo no esta en la lista");

			} while (equipoFichaje == null);

			int cont = 0;
			for (Persona persona : equipoFichaje.getGrupoPersonales()) {
				if (persona.getProfesion().toLowerCase().equals("jugador"))
					cont++;
			}

			System.out.println("\nQue jugador desea fichar (presupuesto: " + String.format("%.2f",equipoFichaje.getPresupuesto()) + "€)");
			cont = 0;
			for (Persona persona : Equipo.Bolsa) {
				cont++;
				System.out.println(cont + ". " + persona.getNombre() + " " + persona.getApellido() + " " + String.format("%.2f", ((Jugador) persona).getValor()) + "€");
			}

			int num = 0;

			while (true) {
				System.out.println("Introduce un numero");
				
				if (scanner.hasNextInt()) {
					num = scanner.nextInt();
					if (num <= Equipo.Bolsa.size() && num > 0)
						break;
					else {
						System.out.println("Debes introducir un numero valido.");
						scanner.nextLine();
					}
				} else {
					System.out.println("Debes introducir un numero valido.");
					scanner.nextLine();
				}
			}

			cont = 0;
			for (Persona jugador : Equipo.Bolsa) {
				cont++;
				if (num == cont)
					jugadorFichaje = jugador;
			}

			if (equipoFichaje.getPresupuesto() >= ((Jugador) jugadorFichaje).getValor()) {
				System.out.println("El jugador " + jugadorFichaje.getNombre() + " " + jugadorFichaje.getApellido()
						+ " ha sido fichado de la bolsa");
				jugadorFichaje.setIdEquipo(equipoFichaje.getId());
				equipoFichaje.setPresupuesto(equipoFichaje.getPresupuesto() - ((Jugador) jugadorFichaje).getValor());
				Equipo.actualizarEquipos();
			} else
				System.out.println("El equipo no tiene dinero para fichar a " + jugadorFichaje.getNombre() + " " + jugadorFichaje.getApellido());
		} else
			System.out.println("La bolsa no tiene jugadores");
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

		int num = 0;

		while (true) {
			System.out.println("Introduce un numero");
			
			if (scanner.hasNextInt()) {
				num = scanner.nextInt();
				if (num <= cont && num > 0) {
					System.out.println();
					break;
				}
				else {
					System.out.println("Debes introducir un numero valido.");
					scanner.nextLine();
				}
			} else {
				System.out.println("Debes introducir un numero valido.");
				scanner.nextLine();
			}
		}

		cont = 0;
		for (Persona jugador : equipo2.getGrupoPersonales()) {
			cont++;
			if (num == cont && jugador.getProfesion().toLowerCase().equals("jugador"))
				jugador1 = jugador;
		}

		
		cont = 0;
		System.out.println("Selecciona el jugador que ofreces");
		for (Persona persona : equipo1.getGrupoPersonales()) {
			if (persona.getProfesion().equals("jugador")) {
				cont++;
				System.out.println(cont + ". " + persona.getNombre() + " " + persona.getApellido());
			}
		}
		
		num = 0;
		while (true) {
			System.out.println("Introduce un numero");
			
			if (scanner.hasNextInt()) {
				num = scanner.nextInt();
				if (num <= cont && num > 0)
					break;
				else {
					System.out.println("Debes introducir un numero valido.");
					scanner.nextLine();
				}
			} else {
				System.out.println("Debes introducir un numero valido.");
				scanner.nextLine();
			}
		}

		cont = 0;
		for (Persona jugador : equipo1.getGrupoPersonales()) {
			cont++;
			if (num == cont && jugador.getProfesion().toLowerCase().equals("jugador"))
				jugador2 = jugador;
		}


		System.out.println("has dado a " + jugador2.getNombre());
		int idEquipo = jugador1.getIdEquipo();
		jugador1.setIdEquipo(jugador2.getIdEquipo());
		jugador2.setIdEquipo(idEquipo);

		equipo1.setUltimoIntercambio("Ofrecieron a " + jugador2.getNombre() + " " + jugador2.getApellido() + " por "
				+ jugador1.getNombre() + " " + jugador1.getApellido());
		equipo2.setUltimoIntercambio("Ofrecieron a " + jugador1.getNombre() + " " + jugador1.getApellido() + " por "
				+ jugador2.getNombre() + " " + jugador2.getApellido());
		Equipo.actualizarEquipos();
	}
}
