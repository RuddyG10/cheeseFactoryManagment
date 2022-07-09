package logico;

public class QHueco extends QCilindro {
	
	private float radioInterno;
	public QHueco(String codigo,float radio, float precioBase, float longitud, float radioInterno,float precioUnitario) {
		super(codigo, radio, precioBase, longitud, precioUnitario);
		this.radioInterno = radioInterno;
	}
	public float getRadioInterno() {
		return radioInterno;
	}
	public void setRadioInterno(float radioInterno) {
		this.radioInterno = radioInterno;
	}
	public float volumen() {
		float volumen = -1;
		if(radioInterno<=radio) {
			volumen = (float) (Math.PI * longitud*(Math.pow(radio, 2) - Math.pow(radioInterno, 2)));
		}
		return volumen;
	}

}
