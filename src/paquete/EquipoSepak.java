package paquete;

public class EquipoSepak extends Equipo {

	private static int nSepak = 0;
	
	public EquipoSepak(String nombreEquipo, Deporte deporte) {
		super(nombreEquipo, deporte);
		nSepak = nSepak++;
	}

	public static int getnSepak() {
		return nSepak;
	}

	public static void setnSepak(int nSepak) {
		EquipoSepak.nSepak = nSepak;
	}
	
	


}
