package paquete;

public class EquipoVoleibol extends Equipo{

	private static int nVoleibol = 0;
	
	public EquipoVoleibol(String nombreEquipo, Deporte deporte, double presupuesto) {
		super(nombreEquipo, deporte, presupuesto);
		nVoleibol = nVoleibol++;
	}

	public static int getnVoleibol() {
		return nVoleibol;
	}

	public static void setnVoleibol(int nVoleibol) {
		EquipoVoleibol.nVoleibol = nVoleibol;
	}
	
}
