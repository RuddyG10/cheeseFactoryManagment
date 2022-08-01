package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Fabrica;
import logico.Factura;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListFac extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnVerCliente;
	private JButton btnCancelar;
	private static DefaultTableModel model;
	private String[] headers = {"Codigo","Cedula del Cliente","Total","fecha"};
	private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private Cliente cliente;
	private Object[] row;
	private Factura selected;
	private JButton btnVerFactura;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListFac dialog = new ListFac(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListFac(Cliente client) {
		setTitle("Lista de facturas");
		cliente=client;
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int index = table.getSelectedRow();
						selected = Fabrica.getInstance().buscarFacturaPorCod(table.getValueAt(index, 0).toString());
						
						if(client == null) {
						btnVerCliente.setEnabled(true);
						}
						btnVerFactura.setEnabled(true);
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				model = new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerCliente = new JButton("Ver Cliente");
				if(cliente != null) {
					btnVerCliente.setVisible(false);
				}
				btnVerCliente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ModClient ver = new ModClient(selected.getMiCliente(),true);
						ver.setVisible(true);
						btnVerCliente.setEnabled(false);
						btnVerFactura.setEnabled(false);
					}
				});
				{
					btnVerFactura = new JButton("Ver Factura");
					btnVerFactura.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							VerFactura verFactura = new VerFactura(selected);
							verFactura.setVisible(true);
							btnVerFactura.setEnabled(false);
							btnVerCliente.setEnabled(false);
						}
					});
					btnVerFactura.setEnabled(false);
					buttonPane.add(btnVerFactura);
				}
				btnVerCliente.setEnabled(false);
				btnVerCliente.setActionCommand("OK");
				buttonPane.add(btnVerCliente);
				getRootPane().setDefaultButton(btnVerCliente);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadFacturas();
	}

	public void loadFacturas() {
		ArrayList<Factura> facturas;
		if(cliente == null) {
			facturas = Fabrica.getInstance().getFacturas();
		}
		else {
			facturas = cliente.getMisFacturas();
		}
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Factura factura : facturas) {
			row[0] = factura.getCodigo();
			row[1] = factura.getMiCliente().getCedula();
			row[2] = factura.getPrecioTotal();
			row[3] = format.format(factura.getFecha());
			model.addRow(row);
		}
	}

}
