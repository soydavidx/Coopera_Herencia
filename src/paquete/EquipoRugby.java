package paquete;

public class EquipoRugby extends Equipo {

	private static int nRugby = 0;

	public EquipoRugby(String nombreEquipo, Deporte deporte) {
		super(nombreEquipo, deporte);
		nRugby = nRugby++;
	}

	public static int getnRugby() {
		return nRugby;
	}

	public static void setnRugby(int nRugby) {
		EquipoRugby.nRugby = nRugby;
	}

}
