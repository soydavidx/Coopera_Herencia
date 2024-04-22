package paquete;

public class Jugador extends Persona {
	private static int contador = 1;

	private String posicion;
	private int TotalSanciones;
	private int TotalMarcados;

	// estos atributo se modificara cada vez que se cargue la aplicacion leyendo
	// datos de la carpeta temporal
	private double valor;
	private int PuestoInteriorEquipo;
	private boolean titular;

	public Jugador(String nombre, String apellido, String profesion, int equipo, String posicion, int TotalSanciones, int TotalMarcados) {
		super(nombre, apellido, profesion, equipo);
		this.posicion = posicion;
		this.TotalSanciones = TotalSanciones;
		this.TotalMarcados = TotalMarcados;
		super.setId(contador);
		this.valor = CalcularValor();
		this.titular = false;
		contador++;
	}

	public static void sumarSancionTanto(Persona jugador, String valor) {
		if (valor.toLowerCase().equals("tanto"))
			((Jugador) jugador).setTotalMarcados(((Jugador) jugador).getTotalMarcados() + 1);
		else
			((Jugador) jugador).setTotalSanciones(((Jugador) jugador).getTotalSanciones() + 1);
	}
	
	// despues de haber jugado una partida
	private double CalcularValor() {
		return (TotalMarcados * 1000) - (TotalSanciones * 500);
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getTotalSanciones() {
		return TotalSanciones;
	}

	public void setTotalSanciones(int totalSanciones) {
		TotalSanciones = totalSanciones;
	}

	public int getTotalMarcados() {
		return TotalMarcados;
	}

	public void setTotalMarcados(int totalMarcados) {
		TotalMarcados = totalMarcados;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean getTitular() {
		return titular;
	}

	public void setTitular(boolean titular) {
		this.titular = titular;
	}

	public int getPuestoInteriorEquipo() {
		return PuestoInteriorEquipo;
	}

	public void setPuestoInteriorEquipo(int puestoInteriorEquipo) {
		PuestoInteriorEquipo = puestoInteriorEquipo;
	}

	// termina el getter y setter

}
