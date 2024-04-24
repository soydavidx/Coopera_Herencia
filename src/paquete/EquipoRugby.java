package paquete;

public class EquipoRugby extends Equipo {

	private static int nRugby = 0;

	public EquipoRugby(String nombreEquipo, Deporte deporte, double presupuesto) {
		super(nombreEquipo, deporte, presupuesto);
		nRugby = nRugby++;
	}

	public static int getnRugby() {
		return nRugby;
	}

	public static void setnRugby(int nRugby) {
		EquipoRugby.nRugby = nRugby;
	}

}
