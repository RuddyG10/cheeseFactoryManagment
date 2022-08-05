package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Fabrica implements Serializable{
	
	private ArrayList<Queso> misQuesos;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Factura> facturas;
	public static int genCodQueso= 1;
	public static int genCodFact = 1;
	private static Fabrica fabri = null;
	private static final long serialVersionUID = 1;
	public Fabrica() {
		super();
		this.facturas = new ArrayList<Factura>();
		this.misClientes = new ArrayList<Cliente>();
		this.misQuesos = new ArrayList<Queso>();
	}
	public static Fabrica getInstance() {
		if(fabri == null) {
			fabri = new Fabrica();
		}
		return fabri;
	}
	public static void setFabrica(Fabrica fab) {
		Fabrica.fabri = fab;
	}
	public ArrayList<Queso> getMisQuesos() {
		return misQuesos;
	}
	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	
	public void insertarCliente(Cliente client) {
		misClientes.add(client);
	}
	public void insertarQueso(Queso queso) {
		misQuesos.add(queso);
		genCodQueso++;
	}
	public float volumenPorQueso(Queso queso) {
		float volumen = -1;
		if(misQuesos.contains(queso)) {
			volumen = queso.volumen();
		}
		return volumen;
	}
	public Factura crearFactura(Cliente client, ArrayList<Queso> quesosCliente) {
		Factura factura = new Factura("F-"+genCodFact,client, quesosCliente,precioTotalFactura(quesosCliente));
		facturas.add(factura);
		client.addFactura(factura);
		genCodFact++;
		return factura;
	}

	private float precioTotalFactura(ArrayList<Queso> quesosCliente) {
		float precioTotal = 0;
		for (Queso queso : quesosCliente) {
			precioTotal += queso.getPrecioBase() + queso.getPrecioUnitario()*queso.volumen();
		}
		return precioTotal;
	}
	public Queso buscarQuesoByCod(String cod) {
		Queso auxQ = null;
		boolean encont = false;
		int i = 0;
		while(i<misQuesos.size()&& !encont) {
			if(misQuesos.get(i).getCodigo().equalsIgnoreCase(cod)) {
				auxQ = misQuesos.get(i);
				encont = true;
			}
			i++;
		}
		return auxQ;
	}
	public void reemplazarQueso(Queso queso, Queso aux) {
		int index = misQuesos.indexOf(queso);
		if(index>=0) {
			misQuesos.set(index, aux);
		}
		
	}
	public void eliminarQueso(Queso selected) {
		misQuesos.remove(selected);
		
	}
	public Cliente buscarClientePorCedula(String cedula) {
		Cliente auxCliente = null;
		int i = 0;
		boolean find = false;
		while(i<misClientes.size() && !find) {
			if(misClientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				auxCliente = misClientes.get(i);
				find = true;
			}
			i++;
		}
		return auxCliente;
	}
	public void eliminarCliente(Cliente selected) {
		if(misClientes.contains(selected)) {
			misClientes.remove(selected);
		}
	}
	public void reemplazarCliente(Cliente client, Cliente clientMod) {
		if(misClientes.contains(client)) {
			int index = misClientes.indexOf(client);
			misClientes.set(index, clientMod);
		}
		
	}
	public Factura buscarFacturaPorCod(String cod) {
		Factura auxF = null;
		int i = 0;
		boolean find = false;
		while(i<facturas.size() && !find) {
			if(facturas.get(i).getCodigo().equalsIgnoreCase(cod)) {
				auxF = facturas.get(i);
				find = true;
			}
			i++;
		}
		return auxF;
	}
	
}
