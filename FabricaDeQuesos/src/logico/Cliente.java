package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{
	
	private String cedula;
	private String nombre;
	private String direccion;
	private String telefono;
	private ArrayList<Factura> misFacturas;
	
	public Cliente(String codigo, String nombre, String direccion, String telefono) {
		super();
		this.cedula = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.misFacturas = new ArrayList<Factura>();
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String codigo) {
		this.cedula = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}
	public void addFactura(Factura factura) {
		if(!misFacturas.contains(factura)) {
			misFacturas.add(factura);
		}
	}
	
}
