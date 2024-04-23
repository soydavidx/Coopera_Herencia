package paquete;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Equipo {
	public static ArrayList<Equipo> ListaEquipos = new ArrayList<>();
	
	public static ArrayList<Persona>Bolsa = new ArrayList<>();
	
	private static int contador = 1;	
	private double presupuesto;
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
        Equipo.Bolsa.clear();
        for (Equipo equipo : ListaEquipos) {
            equipo.getGrupoPersonales().clear();
            for (Persona persona : Persona.ListaPersona) {
                if (persona.getIdEquipo()==equipo.getId())
                    equipo.getGrupoPersonales().add(persona);
                if (persona.getIdEquipo() == 0)
                    Equipo.getBolsa().add(persona);
            }
        }
        Persona.ActualizarFicheroEquipos();
        Entrenador.EstablecerTitular();
    }
	
	public static void AnadirPorcentajeEquipo(Equipo equipo1, Equipo equipo2, int puntuacion1, int puntuacion2) {
		//si gana el equipo1
		if (puntuacion1 > puntuacion2) {
		equipo1.setPresupuesto(equipo1.getPresupuesto() + (equipo1.getPresupuesto() * 0.01));
		//si gana el equipo2
		}else if (puntuacion1 < puntuacion2) {
		equipo2.setPresupuesto(equipo2.getPresupuesto() + (equipo2.getPresupuesto() * 0.01));
		//si empatan
		}else {
		equipo1.setPresupuesto(equipo1.getPresupuesto() + (equipo1.getPresupuesto() * 0.005));
		equipo2.setPresupuesto(equipo2.getPresupuesto() + (equipo2.getPresupuesto() * 0.005));
		}
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


	public double getPresupuesto() {
		return presupuesto;
	}


	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}
	
}
