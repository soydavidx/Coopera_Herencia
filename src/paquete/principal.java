package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MulticastSocket;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class principal {
	public static String signo = "#";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// actualizar los datos de los jugadores al la aplicacion
		System.out.println("Practica nº10: Herencia Cooperativa");
		System.out.println("Un video mas mi gente para perder el tiempo");
		// Hecho por David, Diego y Peter
		Deporte.crearDeportes();
		Persona.crearEquiposYJugadores();
		Entrenador.EstablecerTitular();
		InicioPrograma();
		// Jugador.ActualizarEquipo();

	}

	public static void InicioPrograma() throws IOException {
		Scanner scanner = new Scanner(System.in);

		int opcionUsuario = 0;
		System.out.println("Bienvenid@ a la NavesLiga. En la NavesLiga todos ganamos!!!");
		System.out.println(Director.barra);
		do {
			System.out.println("MENU PRINCIPAL");
			System.out.println("Que deseas hacer hoy? (Inserta el numero de la accion por consola)\n");
			System.out.println("1. Mostrar datos de un equipo.");
			System.out.println("2. Mercado de fichajes de jugadores");
			System.out.println("3. Ver resultados de las ligas");
			System.out.println("4. Salir del programa");

			if (scanner.hasNextInt()) {
				opcionUsuario = scanner.nextInt();
				scanner.nextLine();
				CambiarOpciones(opcionUsuario,scanner);
			} else {
				System.err.println("No has ingresado una opcion correcta dentro del sistema, intentalo nuevamente");
				scanner.nextLine();
			}
		} while (opcionUsuario != 4);

		if (opcionUsuario == 4) {
			System.out.println("Fin del programa");
			System.out.println("--------------------");
			scanner.close();
		}

	}

	public static void CambiarOpciones(int opcionUsuario,Scanner scanner) throws IOException {
		switch (opcionUsuario) {
		case 1: {
			System.out.println("Opcion 1. Mostrar datos de un equipo");
			System.out.println("--------------------");
			MostrarDatos(scanner);
			break;
		}
		case 2: {
			System.out.println("Opcion 2. Intercambiar un jugador");
			System.out.println("--------------------");
			//Director.intercambio(scanner);
			Director.MenuModificarJugador(scanner);
			break;
		}
		case 3: {
			System.out.println("Opcion 3. Ver resultados de las liga");
			System.out.println("--------------------");
			LeerArchivoPartida();
			break;
		}
		case 4: {
			System.out.println("Opcion 4. Salir del programa");
			break;
		}
		default:
			System.out.println("No hay una opcion disponible para el numero ingresado");
			System.out.println("Intentalo nuevamente y escoge una opcion valida.\n");
		}
		System.out.println("--------------------");
	}

	public static void MostrarDatos(Scanner scanner) {
		boolean existe = false;
		System.out.println("Disponemos de esos deportes :");
		System.out.println("--------------------");
		Deporte.Deportes.stream()
	    .map(deporte -> deporte.getNombre())
	    .forEach(deporte -> System.out.println(deporte));
		System.out.println("--------------------");
		
		System.out.println("\nIntroduce un Deporte");
		String ReferenciaDeporte = scanner.nextLine().toLowerCase();
		
		for (Deporte deporte : Deporte.Deportes) {
			if (deporte.getNombre().equals(ReferenciaDeporte)) {
				existe = true;
				break;
			}
		}
		
		if (existe == false) {
			System.out.println("Error debes introducir un deporte mencionado repitelo otra vez");
			MostrarDatos(scanner);
		}
		
		System.out.println("Tenemos esos equipos en el deporte " + ReferenciaDeporte + ":") ;
		Equipo.ListaEquipos.stream()
		.filter(equipo -> equipo.getDeporte().getNombre().equals(ReferenciaDeporte))
		.forEach(equipo -> System.out.println(equipo.getNombreEquipo()));
		//optimizar bucle for
		System.out.println("\nIntroduzca el nombre de un equipo para mostrar sus datos");
		String NombreEquipo = scanner.nextLine().toLowerCase();
		// verificar si el equipo tecleado existe
		for (Equipo equipo : Equipo.ListaEquipos) {
			if (equipo.getNombreEquipo().toLowerCase().equals(NombreEquipo)) {
				System.out.println("\nDatos de " + equipo.getNombreEquipo());
				System.out.println("Deporte: " + equipo.getDeporte().getNombre());
				System.out.println("\nTitulares");
				System.out.println("--------------------");
				for (Persona persona : equipo.getGrupoPersonales()) {
					if (persona.getProfesion().equals("jugador") && ((Jugador) persona).getTitular()) {
						System.out.println(persona.getNombre() + " " + persona.getApellido() + " posicion: " + ((Jugador) persona).getPosicion());
					}
				}
				System.out.println("--------------------");
				System.out.println("\nSuplentes");
				System.out.println("--------------------");
				for (Persona persona : equipo.getGrupoPersonales()) {
					if (persona.getProfesion().equals("jugador") && !((Jugador) persona).getTitular()) {
						System.out.println(persona.getNombre() + " " + persona.getApellido() + " posicion: " + ((Jugador) persona).getPosicion());
					}
				}
				System.out.println("--------------------");
				System.out.println("\nDirector");
				System.out.println("--------------------");
				for (Persona persona : equipo.getGrupoPersonales()) {
					if (persona.getProfesion().equals("director")) {
						System.out.println(persona.getNombre() + " " + persona.getApellido());
					}
				}
				System.out.println("--------------------");
				System.out.println("\nEntrenador");
				System.out.println("--------------------");
				for (Persona persona : equipo.getGrupoPersonales()) {
					if (persona.getProfesion().equals("entrenador")) {
						System.out.println(persona.getNombre() + " " + persona.getApellido());
					}
				}
				System.out.println("--------------------");
				System.out.println("\nUltimo fichaje");
				System.out.println("--------------------");
				System.out.println(equipo.getUltimoIntercambio());
				
				
				List<Jugador> jugadoresMasSanciones = equipo.getGrupoPersonales().stream()
				        .filter(persona -> persona instanceof Jugador)
				        .map(persona -> (Jugador) persona)
				        .sorted(Comparator.comparing(Jugador::getTotalSanciones).reversed())
				        .limit(3)
				        .collect(Collectors.toList());
				
				System.out.println("--------------------");
				System.out.println("\nLos tres jugadores con mas sanciones son");
				System.out.println("--------------------");
				for (Jugador jugador : jugadoresMasSanciones)
				    System.out.println(jugador.getNombre() + " " + jugador.getTotalSanciones());
				
				
				List<Jugador> jugadorMenosValor = equipo.getGrupoPersonales().stream()
				        .filter(persona -> persona instanceof Jugador)
				        .map(persona -> (Jugador) persona)
				        .sorted(Comparator.comparing(Jugador::getTotalSanciones).reversed())
				        .limit(1)
				        .collect(Collectors.toList());
				
				System.out.println("--------------------");
				System.out.println("\nEl jugador con menos valor es");
				System.out.println("--------------------");
				System.out.println(jugadorMenosValor.get(0).getNombre() + " " + jugadorMenosValor.get(0).getApellido());
			}	
		}
	}
	
	
	public static void LeerArchivoPartida() throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader("src/Partidos.txt"));

        String line;

        while ((line = bfr.readLine()) != null) {

            String[] datos = line.split(", ");

            if (datos[2].toLowerCase().equals("sancion")) {
            	Persona jugador = ReferenciaJugador(datos[0], datos[1]);
            	Jugador.sumarSancionTanto(jugador, datos[2]);
            }
            else if (datos[2].toLowerCase().equals("tanto")) {
            	Persona jugador = ReferenciaJugador(datos[0], datos[1]);
            	Jugador.sumarSancionTanto(jugador, datos[2]);
            }
            else {
            	Equipo equipo1 = verificarNombreEquipo(datos[0]);
            	Equipo equipo2 = verificarNombreEquipo(datos[2]);
            	Equipo.sumarPuntos(equipo1, Integer.parseInt(datos[1]), equipo2, Integer.parseInt(datos[3]));
            }
        }
        Persona.ActualizarFicheroEquipos();
        Entrenador.EstablecerTitular();
        MostrarRanking();
        bfr.close();
    }
    public static Persona ReferenciaJugador(String nombre,String apellido) {
        for (Persona persona : Persona.getListaPersona()) {
            if (persona.getNombre().equals(nombre) && persona.getApellido().equals(apellido)) {
                return persona;
            }
        }
        return null;
    }
    
    public static void MostrarRanking () {
    	
    	for (Deporte deporte : Deporte.Deportes) {
    		int cont = 0;
        	List<Equipo> Equipos = (List<Equipo>) Equipo.ListaEquipos.stream()
        			.filter(equipo -> equipo.getDeporte().equals(deporte))
        			.sorted(Comparator.comparing(Equipo::getPuntos).reversed())
        			.collect(Collectors.toList());
    		System.out.println("\nRanking de " + deporte.getNombre());
    		for (Equipo equipo : Equipos) {
    			if (equipo.getDeporte()==deporte) {
    				cont++;
    				System.out.println(cont + ". " + equipo.getNombreEquipo() + " " + equipo.getPuntos());
    			}
    		}
    	}
    }
    
	public static Equipo verificarNombreEquipo(String Nombre) {
		// verificar si el equipo tecleado existe
		for (Equipo equipo : Equipo.ListaEquipos) {
			if (equipo.getNombreEquipo().toLowerCase().equals(Nombre.toLowerCase())) {
				return equipo;
			}
		}
		return null;
	}
}
