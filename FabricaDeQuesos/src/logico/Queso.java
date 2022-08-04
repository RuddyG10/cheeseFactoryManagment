package logico;

import java.io.Serializable;

public class Queso implements Serializable{
	
	protected String codigo;
	protected float radio;
	protected float precioBase;
	protected float precioUnitario;
	
	public Queso(String codigo,float radio, float precioBase,float precioUnitario) {
		super();
		this.codigo = codigo;
		this.radio = radio;
		this.precioBase = precioBase;
		this.precioUnitario = precioUnitario;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getRadio() {
		return radio;
	}
	public void setRadio(float radio) {
		this.radio = radio;
	}
	public float getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}
	
	public float volumen() {
		
		return radio;
	}

}
