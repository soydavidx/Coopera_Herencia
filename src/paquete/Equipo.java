package paquete;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Equipo {
	public static ArrayList<Equipo> ListaEquipos = new ArrayList<>();
	
	public static ArrayList<Persona>Bolsa = new ArrayList<>();
	
	private static int contador = 1;	
	private int presupuesto;

	private int id;

	private String nombreEquipo;
	private int nJugadores;

	private ArrayList<Persona> GrupoPersonales = new ArrayList<>();
	private Deporte deporte;
	private int puntos;
	private String ultimoIntercambio;
	// datos de la carpeta temporal

	public Equipo(String nombreEquipo, Deporte deporte) {
		this.id = contador++;
		this.nombreEquipo = nombreEquipo;
		this.deporte = deporte;
		this.ultimoIntercambio = "No hay intercambios recientes";
	}


	public static void sumarPuntos(Equipo equipo1, int puntosEquipo1, Equipo equipo2, int puntosEquipo2) {
		if (puntosEquipo1 == puntosEquipo2) {
			equipo1.setPuntos(equipo1.getPuntos() + 1);
			equipo2.setPuntos(equipo2.getPuntos() + 1);
		} else if (puntosEquipo1 > puntosEquipo2)
			equipo1.setPuntos(equipo1.getPuntos() + 3);
		else
			equipo2.setPuntos(equipo2.getPuntos() + 3);
	}
	
	public static void actualizarEquipos() throws IOException {
		for (Equipo equipo : ListaEquipos) {
			equipo.getGrupoPersonales().clear();
			for (Persona persona : Persona.ListaPersona) {
				if (persona.getIdEquipo()==equipo.getId())
					equipo.getGrupoPersonales().add(persona);
			}
		}
		Persona.ActualizarFicheroEquipos();
		Entrenador.EstablecerTitular();
	}
	
	public static void AnadirPorcentajeEquipo() {
		//ordenar de mayor a menor a los equipos segun sus puntos
		List<Equipo>orden = Equipo.ListaEquipos.stream()
				.sorted(Comparator.comparing(Equipo::getPuntos).reversed())
				.collect(Collectors.toList());
		//encontrar el equipo en ranking 1 y setearle su presupuesto a 1% mas
		int posEquipo = Equipo.ListaEquipos.indexOf(orden.get(0));
		Equipo equipoRefe = Equipo.getListaEquipos().get(posEquipo);
		//el problema ocurre porque para sumar el 1 porciento hay que cambiar a double , puntos
		//cambiar a double el atributo puntos
		equipoRefe.setPuntos(equipoRefe.getPuntos() + (equipoRefe.getPuntos() * 0.01));
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getnJugadores() {
		return nJugadores;
	}

	public void setnJugadores(int nJugadores) {
		this.nJugadores = nJugadores;
	}

	public ArrayList<Persona> getGrupoPersonales() {
		return GrupoPersonales;
	}

	public void setGrupoPersonales(ArrayList<Persona> grupoPersonales) {
		GrupoPersonales = grupoPersonales;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public static ArrayList<Equipo> getListaEquipos() {
		return ListaEquipos;
	}

	public static void setListaEquipos(ArrayList<Equipo> listaEquipos) {
		ListaEquipos = listaEquipos;
	}

	public String getUltimoIntercambio() {
		return ultimoIntercambio;
	}

	public void setUltimoIntercambio(String ultimoIntercambio) {
		this.ultimoIntercambio = ultimoIntercambio;
	}


	public static ArrayList<Persona> getBolsa() {
		return Bolsa;
	}


	public static void setBolsa(ArrayList<Persona> bolsa) {
		Bolsa = bolsa;
	}


	public int getPresupuesto() {
		return presupuesto;
	}


	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}
	
}
