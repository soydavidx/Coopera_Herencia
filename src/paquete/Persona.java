package paquete;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Persona {
	protected static ArrayList<Persona> ListaPersona = new ArrayList<>();

	private int id;
	private String nombre;
	private String apellido;
	private String profesion;
	private int idEquipo;

	public Persona(String nombre, String apellido, String profesion, int idEquipo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.profesion = profesion;
		this.idEquipo = idEquipo;
	}

	public static void crearEquiposYJugadores() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("src/MiembrosEquipo.txt"));
		try {
			// AQUI GUARDAREMOS LAS LINEAS DEL FICHERO
			String line;
			// CON ESTE WHILE PODREMOS LEER LOS DATOS DEL FICHERO
			while ((line = br.readLine()) != null) {
				String[] datos = line.split(", ");

				switch (datos[2]) {
				case "jugador" -> ListaPersona.add(new Jugador(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]),
						datos[4], Integer.parseInt(datos[5]), Integer.parseInt(datos[6])));
				case "entrenador" ->
					ListaPersona.add(new Entrenador(datos[0], datos[1], datos[2], Integer.parseInt(datos[3])));
				case "director" ->
					ListaPersona.add(new Director(datos[0], datos[1], datos[2], Integer.parseInt(datos[3])));
				case "Sepak Takraw" -> Equipo.ListaEquipos.add(new EquipoSepak(datos[0], Deporte.Deportes.get(0)));
				case "voleibol" -> Equipo.ListaEquipos.add(new EquipoRugby(datos[0], Deporte.Deportes.get(1)));
				case "Rugby Subacuatico" ->
					Equipo.ListaEquipos.add(new EquipoVoleibol(datos[0], Deporte.Deportes.get(2)));
				}
			}
			for (Equipo equipo : Equipo.ListaEquipos) {
				for (Persona persona : ListaPersona) {
					if (persona.getIdEquipo() == equipo.getId())
						equipo.getGrupoPersonales().add(persona);
					if (persona.getIdEquipo() == 0)
						Equipo.getBolsa().add(persona);
				}
			}
		} catch (Exception e) {
			System.out.println("Error.");
			e.printStackTrace();
		} finally {
			br.close();
		}

	}

	public static void ActualizarFicheroEquipos() throws IOException {
		BufferedWriter brw = new BufferedWriter(new FileWriter("src/MiembrosEquipo.txt"));
		try {
			for (Equipo equipo : Equipo.ListaEquipos) {
				brw.write(equipo.getNombreEquipo() + ", " + 0 + ", " + equipo.getDeporte().getNombre() + "\n");
				for (Persona persona : equipo.getGrupoPersonales()) {
					switch (persona.getProfesion()) {
					case "jugador" -> brw.write(persona.getNombre() + ", " + persona.getApellido() + ", "
							+ persona.getProfesion() + ", " + persona.getIdEquipo() + ", "
							+ ((Jugador) persona).getPosicion() + ", " + ((Jugador) persona).getTotalSanciones() + ", "
							+ ((Jugador) persona).getTotalMarcados() + "\n");
					case "entrenador" -> brw.write(persona.getNombre() + ", " + persona.getApellido() + ", "
							+ persona.getProfesion() + ", " + persona.getIdEquipo() + "\n");
					case "director" -> brw.write(persona.getNombre() + ", " + persona.getApellido() + ", "
							+ persona.getProfesion() + ", " + persona.getIdEquipo() + "\n");
					}
				}
			}
			Entrenador.EstablecerTitular();
		} catch (Exception e) {
			System.out.println("Error.");
		    e.printStackTrace();
		} finally {
			brw.close();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public static ArrayList<Persona> getListaPersona() {
		return ListaPersona;
	}

	public static void setListaPersona(ArrayList<Persona> listaPersona) {
		ListaPersona = listaPersona;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

}
