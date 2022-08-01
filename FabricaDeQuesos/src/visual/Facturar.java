package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.sun.glass.ui.Pixels.Format;

import logico.Cliente;
import logico.Fabrica;
import logico.Factura;
import logico.Queso;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class Facturar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JButton btnBuscar;
	private JTextField txtTotal;
	private JButton btnFacturar;
	private JList listCompras;
	private DefaultListModel listModelQuesos;
	private JList listQuesos;
	private ArrayList<Queso> selected;
	private JButton btnRight;
	private JButton btnLeft;
	private DefaultListModel listModelCarrito;
	private JTextField txtFecha;
	private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Facturar dialog = new Facturar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Facturar() {
		setTitle("Facturar compra del cliente");
		setModal(true);
		setBounds(100, 100, 531, 549);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelDatosCliente = new JPanel();
		panelDatosCliente.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosCliente.setBounds(10, 11, 496, 128);
		contentPanel.add(panelDatosCliente);
		panelDatosCliente.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Datos del cliente:");
		lblNewLabel.setBounds(10, 11, 149, 14);
		panelDatosCliente.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cedula:");
		lblNewLabel_1.setBounds(10, 36, 54, 14);
		panelDatosCliente.add(lblNewLabel_1);
		
		txtCedula = new JTextField();
		txtCedula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char letra = arg0.getKeyChar();
				if(!Character.isDigit(letra)) {
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		txtCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activarFactura();
			}
		});
		txtCedula.setBounds(74, 33, 172, 20);
		panelDatosCliente.add(txtCedula);
		txtCedula.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListClients listaCliente = new ListClients(true);
				listaCliente.setVisible(true);
				if(listaCliente.dameCliente() != null) {
					Cliente auxCliente = listaCliente.dameCliente();
					txtCedula.setText(auxCliente.getCedula());
					txtDireccion.setText(auxCliente.getDireccion());
					txtTelefono.setText(auxCliente.getTelefono());
					txtNombre.setText(auxCliente.getNombre());
				}
			}
		});
		btnBuscar.setBounds(256, 32, 89, 23);
		panelDatosCliente.add(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(10, 67, 54, 14);
		panelDatosCliente.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isLetter(letra)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activarFactura();
			}

			
		});
		txtNombre.setBounds(74, 64, 172, 20);
		panelDatosCliente.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Telefono:");
		lblNewLabel_3.setBounds(256, 66, 64, 14);
		panelDatosCliente.add(lblNewLabel_3);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letra = e.getKeyChar();
				if(!Character.isDigit(letra)) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activarFactura();
			}
		});
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(313, 66, 172, 20);
		panelDatosCliente.add(txtTelefono);
		
		JLabel lblNewLabel_4 = new JLabel("Direccion:");
		lblNewLabel_4.setBounds(10, 103, 64, 14);
		panelDatosCliente.add(lblNewLabel_4);
		
		txtDireccion = new JTextField();
		txtDireccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activarFactura();
			}
		});
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(74, 100, 172, 20);
		panelDatosCliente.add(txtDireccion);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha:");
		lblNewLabel_8.setBounds(299, 11, 54, 14);
		panelDatosCliente.add(lblNewLabel_8);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(363, 8, 122, 20);
		panelDatosCliente.add(txtFecha);
		txtFecha.setColumns(10);
		txtFecha.setText(format.format(new Date()));
		
		JPanel PanelCompra = new JPanel();
		PanelCompra.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelCompra.setBounds(10, 150, 496, 256);
		contentPanel.add(PanelCompra);
		PanelCompra.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Lista de Quesos:");
		lblNewLabel_5.setBounds(10, 11, 183, 14);
		PanelCompra.add(lblNewLabel_5);
		
		listQuesos = new JList();
		listQuesos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(listQuesos.getSelectedIndex() != -1) {
					btnRight.setEnabled(true);
				}else {
					btnLeft.setEnabled(false);
				}
			}
		});
		listQuesos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listModelQuesos = new DefaultListModel();
		listQuesos.setBounds(10, 36, 183, 142);
		PanelCompra.add(listQuesos);
		
		btnLeft = new JButton("<");
		btnLeft.setEnabled(false);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listCompras.getSelectedIndex() != -1) {
					List quesos = listCompras.getSelectedValuesList();
					Object quesosLista[] = quesos.toArray();
					for (int i = 0; i < quesos.size(); i++) {
						listModelQuesos.addElement(quesosLista[i]);
					}
					listQuesos.setModel(listModelQuesos);
					
					//eliminar elementos
					if(listModelCarrito.getSize() != 0) {
						for (int j = 0; j < quesosLista.length; j++) {
							listModelCarrito.removeElement(quesosLista[j]);
						}
					}
					listCompras.setModel(listModelCarrito);
					btnLeft.setEnabled(false);
					activarFactura();
					subTotal();
				}
			}
		});
		btnLeft.setBounds(203, 117, 89, 23);
		PanelCompra.add(btnLeft);
		
		btnRight = new JButton(">");
		btnRight.setEnabled(false);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listQuesos.getSelectedIndex() != -1) {
					List quesos = listQuesos.getSelectedValuesList();
					Object quesosLista[] = quesos.toArray();
					for (int i = 0; i < quesos.size(); i++) {
						listModelCarrito.addElement(quesosLista[i]);
					}
					listCompras.setModel(listModelCarrito);
					
					//eliminar elementos
					if(listModelQuesos.getSize() != 0) {
						for (int j = 0; j < quesosLista.length; j++) {
							listModelQuesos.removeElement(quesosLista[j]);
						}
					}
					listQuesos.setModel(listModelQuesos);
					btnRight.setEnabled(false);
					activarFactura();
					subTotal();
					
				}
				
				
			}

			
		});
		btnRight.setBounds(204, 47, 89, 23);
		PanelCompra.add(btnRight);
		
		JLabel lblNewLabel_7 = new JLabel("Carrito de compras:");
		lblNewLabel_7.setBounds(314, 11, 155, 14);
		PanelCompra.add(lblNewLabel_7);
		
		listCompras = new JList();
		listCompras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(listCompras.getSelectedIndex() != -1) {
					btnLeft.setEnabled(true);
				}
				else {
					btnLeft.setEnabled(false);
				}
			}
		});
		listCompras.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listModelCarrito = new DefaultListModel();
		listCompras.setBounds(303, 36, 183, 142);
		PanelCompra.add(listCompras);
		
		JTextPane txtpnMantengaCtrlPara = new JTextPane();
		txtpnMantengaCtrlPara.setText("Mantenga ctrl para seleccion multiple en ambas listas.\r\n \">\"-agregar al carrito.\r\n \"<\" devolver a la lista.\r\n");
		txtpnMantengaCtrlPara.setEditable(false);
		txtpnMantengaCtrlPara.setBackground(UIManager.getColor("Button.background"));
		txtpnMantengaCtrlPara.setBounds(10, 189, 476, 56);
		PanelCompra.add(txtpnMantengaCtrlPara);
		
		JPanel panelTotal = new JPanel();
		panelTotal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTotal.setBounds(10, 417, 496, 44);
		contentPanel.add(panelTotal);
		panelTotal.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Sub Total:");
		lblNewLabel_6.setBounds(10, 11, 67, 14);
		panelTotal.add(lblNewLabel_6);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(87, 8, 151, 20);
		panelTotal.add(txtTotal);
		txtTotal.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnFacturar = new JButton("Facturar");
				btnFacturar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cliente auxCliente = Fabrica.getInstance().buscarClientePorCedula(txtCedula.getText());
						int option1 = JOptionPane.showConfirmDialog(null, "Realizar Factura?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if(option1 == 0) {
							if(auxCliente == null) {
								auxCliente = new Cliente(txtCedula.getText(), txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText());
								Fabrica.getInstance().insertarCliente(auxCliente);
							}
							Factura factura = Fabrica.getInstance().crearFactura(auxCliente, selected);
							
							for (Queso queso : selected) {
								Fabrica.getInstance().eliminarQueso(queso);
							}
							int option2 = JOptionPane.showConfirmDialog(null, "Facturacion exitosa. Desea ver su Factura?", "Informacion.", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							if(option2 == 0) {
								VerFactura ver = new VerFactura(factura);
								ver.setVisible(true);
							}
							clear();
							btnFacturar.setEnabled(false);
							btnRight.setEnabled(false);
							btnLeft.setEnabled(false);
						}
						
					}

					

					
				});
				btnFacturar.setEnabled(false);
				btnFacturar.setActionCommand("OK");
				buttonPane.add(btnFacturar);
				getRootPane().setDefaultButton(btnFacturar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		llenarQuesos();
	}

	public void llenarQuesos() {
		ArrayList<Queso> quesos = Fabrica.getInstance().getMisQuesos();
		for (Queso queso : quesos) {
			listModelQuesos.addElement(queso.getCodigo()+" - Precio: $"+queso.getPrecioUnitario());
		}
		listQuesos.setModel(listModelQuesos);
		
	}
	public void activarFactura() {
		if(!txtCedula.getText().isEmpty() && !txtDireccion.getText().isEmpty() &&
			!txtNombre.getText().isEmpty() && !txtTelefono.getText().isEmpty()) {
			if(listCompras.getModel().getSize() > 0) {
				btnFacturar.setEnabled(true);
			}
			else {
				btnFacturar.setEnabled(false);
			}
		}
		else {
			btnFacturar.setEnabled(false);
		}
		
	}
	public void subTotal() {
		float subTotal = 0;
		String[] codes;
		selected = new ArrayList<Queso>();
		for (int i = 0; i < listModelCarrito.size(); i++) {
			codes = listModelCarrito.get(i).toString().split(" ");
			System.out.println(codes[0]);
			selected.add(Fabrica.getInstance().buscarQuesoByCod(codes[0]));
		}
		for (Queso queso : selected) {
			subTotal += queso.getPrecioUnitario();
		}
		
		txtTotal.setText(String.valueOf(subTotal));
		
	}
	public void clear() {
		txtCedula.setText("");
		txtDireccion.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		txtTotal.setText("");
		listModelCarrito.clear();
	}
}
