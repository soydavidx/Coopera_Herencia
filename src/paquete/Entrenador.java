package paquete;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Entrenador extends Persona {
	private static int contador = 1;

	public Entrenador(String nombre, String apellido, String profesion, int equipo) {
		super(nombre, apellido, profesion, equipo);
		super.setId(contador);
		contador++;
	}

	public static void EstablecerTitular() {
		int x;
		for (Equipo equipo : Equipo.ListaEquipos) {
			
			x = equipo.getDeporte().getNparticipantes();
			
			List<Jugador> jugadores = equipo.getGrupoPersonales().stream()
			        .filter(persona -> persona instanceof Jugador)
			        .map(persona -> (Jugador) persona)
			        .sorted(Comparator.comparing(Jugador::getValor).reversed())
			        .limit(x)
			        .collect(Collectors.toList());

			jugadores.forEach(jugador -> jugador.setTitular(true));
		}
	}
}
