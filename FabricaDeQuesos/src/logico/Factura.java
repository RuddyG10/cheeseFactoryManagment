package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
	private Cliente miCliente;
	private ArrayList<Queso> quesosCliente;
	private float precioTotal;
	private Date fecha;
	private String codigo;
	public Factura(String codigo,Cliente miCliente, ArrayList<Queso> quesosCliente,float precioTotal) {
		super();
		this.codigo = codigo;
		this.miCliente = miCliente;
		this.quesosCliente = quesosCliente;
		this.precioTotal = precioTotal;
		this.fecha = new Date();
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public Cliente getMiCliente() {
		return miCliente;
	}
	public ArrayList<Queso> getQuesosCliente() {
		return quesosCliente;
	}
	
	
	
	
	
	
}
