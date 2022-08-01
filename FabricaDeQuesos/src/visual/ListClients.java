package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Fabrica;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListClients extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private Object[] row;
	private boolean search;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnVerFacturas;
	private Cliente selected;
	private JButton btnSeleccionar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListClients dialog = new ListClients(false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListClients(boolean aux) {
		search = aux;
		setTitle("Lista de Clientes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 506, 300);
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
						if(index>=0) {
							selected = Fabrica.getInstance().buscarClientePorCedula(table.getValueAt(index, 0).toString());
						}
						if(!search) {
							btnModificar.setEnabled(true);
							btnVerFacturas.setEnabled(true);
						}
						else {
							btnSeleccionar.setEnabled(true);
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				model = new DefaultTableModel();
				String[] headers = {"Cedula","Nombre","Direccion","Telefono"};
				model.setColumnIdentifiers(headers);
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerFacturas = new JButton("Facturas");
				btnVerFacturas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListFac lista = new ListFac(selected);
						lista.setVisible(true);
						btnModificar.setEnabled(false);
						btnVerFacturas.setEnabled(false);
					}
				});
				{
					btnSeleccionar = new JButton("Seleccionar");
					btnSeleccionar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dameCliente();
							dispose();
						}
					});
					btnSeleccionar.setVisible(false);
					btnSeleccionar.setEnabled(false);
					buttonPane.add(btnSeleccionar);
				}
				btnVerFacturas.setEnabled(false);
				buttonPane.add(btnVerFacturas);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ModClient modificar = new ModClient(selected,false);
						modificar.setVisible(true);
						btnModificar.setEnabled(false);
						btnVerFacturas.setEnabled(false);
						loadClient();
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selected = null;
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadClient();
		revision();
		
	}
	public Cliente dameCliente() {
		return selected;
		
	}
	public void revision() {
		if(search) {
			setTitle("Seleccione cliente");
			btnModificar.setVisible(false);
			btnVerFacturas.setVisible(false);
			btnSeleccionar.setVisible(true);
		}
		
	}

	public void loadClient() {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		ArrayList<Cliente> clientes = Fabrica.getInstance().getMisClientes();
		for (Cliente cliente : clientes) {
			row[0] = cliente.getCedula();
			row[1] = cliente.getNombre();
			row[2] = cliente.getDireccion();
			row[3] = cliente.getTelefono();
			model.addRow(row);
		}
		
	}

}
