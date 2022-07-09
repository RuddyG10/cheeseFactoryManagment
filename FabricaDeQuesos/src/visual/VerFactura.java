package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Factura;
import logico.Queso;

import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class VerFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTable table;
	private String[] headers = {"Codigo del queso","Precio Unitario","Volumen"};
	private Factura factura;
	private DefaultTableModel model;
	private Object[] row;
	private JTextField txtSubTotal;
	private JTextField txtVolumen;
	private JTextField txtTotal;
	private JTextField txtCodigo;
	private JTextField txtFecha;
	private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerFactura dialog = new VerFactura(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VerFactura(Factura aux) {
		factura = aux;
		setTitle("Factura");
		setModal(true);
		setBounds(100, 100, 492, 542);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panelInfo = new JPanel();
			panelInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelInfo.setBounds(10, 11, 456, 207);
			contentPanel.add(panelInfo);
			panelInfo.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Factura.");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(10, 12, 124, 32);
			panelInfo.add(lblNewLabel);
			
			JTextPane txtpnFabricaDeQuesos = new JTextPane();
			txtpnFabricaDeQuesos.setEditable(false);
			txtpnFabricaDeQuesos.setBackground(UIManager.getColor("Button.background"));
			txtpnFabricaDeQuesos.setText("Fabrica de Quesos .Inc\r\nSantiago, Rep. Dom.");
			txtpnFabricaDeQuesos.setBounds(10, 43, 158, 47);
			panelInfo.add(txtpnFabricaDeQuesos);
			{
				JLabel lblPropietario = new JLabel("Propietario:");
				lblPropietario.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblPropietario.setBounds(213, 13, 124, 33);
				panelInfo.add(lblPropietario);
			}
			
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(213, 43, 233, 153);
			panelInfo.add(panel);
			panel.setLayout(null);
			
			JLabel label = new JLabel("Nombre:");
			label.setBounds(7, 19, 60, 14);
			panel.add(label);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(74, 14, 151, 20);
			panel.add(txtNombre);
			
			JLabel label_1 = new JLabel("Cedula:");
			label_1.setBounds(7, 52, 60, 14);
			panel.add(label_1);
			
			txtCedula = new JTextField();
			txtCedula.setEditable(false);
			txtCedula.setColumns(10);
			txtCedula.setBounds(74, 48, 151, 20);
			panel.add(txtCedula);
			
			JLabel label_2 = new JLabel("Telefono:");
			label_2.setBounds(7, 85, 60, 14);
			panel.add(label_2);
			
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(74, 82, 151, 20);
			panel.add(txtTelefono);
			
			JLabel label_3 = new JLabel("Direccion:");
			label_3.setBounds(7, 118, 60, 14);
			panel.add(label_3);
			
			txtDireccion = new JTextField();
			txtDireccion.setEditable(false);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(74, 116, 151, 20);
			panel.add(txtDireccion);
			
			JLabel lblNewLabel_4 = new JLabel("Codigo:");
			lblNewLabel_4.setBounds(10, 94, 55, 14);
			panelInfo.add(lblNewLabel_4);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(75, 91, 93, 20);
			panelInfo.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNewLabel_5 = new JLabel("Fecha:");
			lblNewLabel_5.setBounds(10, 119, 55, 14);
			panelInfo.add(lblNewLabel_5);
			
			txtFecha = new JTextField();
			txtFecha.setEditable(false);
			txtFecha.setColumns(10);
			txtFecha.setBounds(75, 116, 93, 20);
			panelInfo.add(txtFecha);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 229, 456, 132);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JPanel panelTotal = new JPanel();
		panelTotal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTotal.setBounds(10, 372, 266, 82);
		contentPanel.add(panelTotal);
		panelTotal.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Sub Total:");
		lblNewLabel_1.setBounds(21, 18, 73, 14);
		panelTotal.add(lblNewLabel_1);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setBounds(115, 14, 143, 20);
		panelTotal.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sum. Por volumen:");
		lblNewLabel_2.setBounds(9, 50, 108, 14);
		panelTotal.add(lblNewLabel_2);
		
		txtVolumen = new JTextField();
		txtVolumen.setEditable(false);
		txtVolumen.setBounds(115, 47, 143, 20);
		panelTotal.add(txtVolumen);
		txtVolumen.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(286, 372, 180, 82);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Total:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(10, 31, 53, 20);
		panel_1.add(lblNewLabel_3);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(73, 33, 97, 20);
		panel_1.add(txtTotal);
		txtTotal.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		load();
	}

	public void load() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		ArrayList<Queso> quesos =factura.getQuesosCliente();
		float subTotal = 0;
		float sumVolumen = 0;
		float total = 0;
		for (Queso queso : quesos) {
			subTotal += queso.getPrecioUnitario();
			sumVolumen += queso.volumen();
			row[0] = queso.getCodigo();
			row[1] = queso.getPrecioUnitario();
			row[2] = queso.volumen();
			model.addRow(row);
		}
		total = factura.getPrecioTotal();
		txtNombre.setText(factura.getMiCliente().getNombre());
		txtCedula.setText(factura.getMiCliente().getCedula());
		txtTelefono.setText(factura.getMiCliente().getTelefono());
		txtDireccion.setText(factura.getMiCliente().getDireccion());
		
		txtCodigo.setText(factura.getCodigo());
		txtFecha.setText(format.format(factura.getFecha()));
		
		txtSubTotal.setText(String.valueOf(subTotal));
		txtVolumen.setText(String.valueOf(sumVolumen));
		txtTotal.setText(String.valueOf(total));
		
	}
}
