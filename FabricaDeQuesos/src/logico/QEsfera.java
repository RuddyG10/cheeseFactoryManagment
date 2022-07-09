package logico;

public class QEsfera extends Queso {

	public QEsfera(String codigo,float radio, float precioBase,float precioUnitario) {
		super(codigo, radio, precioBase, precioUnitario);
		
	}
	public float volumen() {
		return (float) ((4/3)*(Math.PI)*Math.pow(radio, 3));
	}
	public void precioUnitario() {
		precioUnitario = 25;
	}

}
