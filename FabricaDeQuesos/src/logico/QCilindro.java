package logico;

public class QCilindro extends Queso {
	protected float longitud;

	public QCilindro(String codigo,float radio, float precioBase,float longitud,float precioUnitario) {
		super(codigo, radio, precioBase, precioUnitario);
		this.longitud = longitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	public float volumen() {
		return (float) (Math.PI * Math.pow(radio, 2) *longitud);
	}
	
}
