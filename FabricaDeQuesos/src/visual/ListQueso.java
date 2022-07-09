package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.Headers;

import logico.Fabrica;
import logico.QCilindro;
import logico.QEsfera;
import logico.QHueco;
import logico.Queso;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtCodigo;
	private static DefaultTableModel model;
	private JButton btnModificar;
	private JButton btnCancelar;
	private Object[] row;
	private JRadioButton rdbtnEsfera;
	private JRadioButton rdbtnCilindroH;
	private JRadioButton rdbtnCilindro;
	private String[] headers = {"Codigo","Radio","Costo","Precio Unitario","Volumen"};
	private Queso selected = null;
	private JButton btnEliminar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListQueso dialog = new ListQueso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListQueso() {
		setTitle("Lista de Quesos");
		setModal(true);
		setBounds(100, 100, 601, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelTipos = new JPanel();
		panelTipos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTipos.setBounds(10, 11, 282, 89);
		contentPanel.add(panelTipos);
		panelTipos.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione tipo de quesos:");
		lblNewLabel.setBounds(10, 11, 262, 14);
		panelTipos.add(lblNewLabel);
		
		rdbtnEsfera = new JRadioButton("Esferico");
		rdbtnEsfera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnEsfera.isSelected()) {
					rdbtnCilindro.setSelected(false);
					rdbtnCilindroH.setSelected(false);
					loadQuesos();
				}
				loadQuesos();
			}
		});
		rdbtnEsfera.setBounds(4, 43, 79, 23);
		panelTipos.add(rdbtnEsfera);
		
		rdbtnCilindro = new JRadioButton("Cilindrico");
		rdbtnCilindro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCilindro.isSelected()) {
					rdbtnCilindroH.setSelected(false);
					rdbtnEsfera.setSelected(false);
					loadQuesos();
				}
				loadQuesos();
			}
		});
		rdbtnCilindro.setBounds(87, 43, 79, 23);
		panelTipos.add(rdbtnCilindro);
		
		rdbtnCilindroH = new JRadioButton("Cilindrico Hueco");
		rdbtnCilindroH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCilindroH.isSelected()) {
					rdbtnCilindro.setSelected(false);
					rdbtnEsfera.setSelected(false);
					loadQuesos();
				}
				loadQuesos();
			}
		});
		rdbtnCilindroH.setBounds(170, 43, 108, 23);
		panelTipos.add(rdbtnCilindroH);
		
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBusqueda.setBounds(302, 11, 273, 89);
		contentPanel.add(panelBusqueda);
		panelBusqueda.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Busqueda por codigo:");
		lblNewLabel_1.setBounds(10, 11, 160, 14);
		panelBusqueda.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Codigo del Queso:");
		lblNewLabel_2.setBounds(10, 46, 104, 14);
		panelBusqueda.add(lblNewLabel_2);
		
		txtCodigo = new JTextField();
		
		
		
		txtCodigo.setBounds(124, 43, 139, 20);
		panelBusqueda.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTabla.setBounds(10, 111, 565, 163);
		contentPanel.add(panelTabla);
		panelTabla.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getSelectedRow();
				if(index>=0) {
					String cod = table.getValueAt(index, 0).toString();
					selected = Fabrica.getInstance().buscarQuesoByCod(cod);
					
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel();
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegQueso regQueso = new RegQueso(selected);
						regQueso.setVisible(true);
						loadQuesos();
						btnModificar.setEnabled(false);
						btnEliminar.setEnabled(false);
					}
				});
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int index = table.getSelectedRow();
						selected = Fabrica.getInstance().buscarQuesoByCod(table.getValueAt(index, 0).toString());
						int option = JOptionPane.showConfirmDialog(null, "Desea eliminar: "+selected.getCodigo()+"?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if(option == 0) {
							Fabrica.getInstance().eliminarQueso(selected);
							loadQuesos();
							btnEliminar.setEnabled(false);
							btnModificar.setEnabled(false);
						}
					}
				});
				buttonPane.add(btnEliminar);
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
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
		loadQuesos();
	}

	public void loadQuesos() {
		model.setColumnIdentifiers(headers);
		if(rdbtnCilindro.isSelected()) {
			model.addColumn("Longitud");;
		}
		if(rdbtnCilindroH.isSelected()) {
			model.addColumn("Longitud");
			model.addColumn("Radio Interno");
		}
		table.setModel(model);
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		ArrayList<Queso> quesos = Fabrica.getInstance().getMisQuesos();
		for (Queso queso : quesos) {
			if(!rdbtnEsfera.isSelected() && !rdbtnCilindro.isSelected() && !rdbtnCilindroH.isSelected()) {
				row[0] = queso.getCodigo();
				row[1] = queso.getRadio();
				row[2] = queso.getPrecioBase();
				row[3] = queso.getPrecioUnitario();
				row[4] = Fabrica.getInstance().volumenPorQueso(queso);
				model.addRow(row);
			}
			if(rdbtnEsfera.isSelected()) {
				if(queso instanceof QEsfera) {
					row[0] = queso.getCodigo();
					row[1] = queso.getRadio();
					row[2] = queso.getPrecioBase();
					row[3] = queso.getPrecioUnitario();
					row[4] = Fabrica.getInstance().volumenPorQueso(queso);
					model.addRow(row);
				}
				
			}
			if(rdbtnCilindro.isSelected()) {
				if(queso instanceof QCilindro && !(queso instanceof QHueco)) {
					row[0] = queso.getCodigo();
					row[1] = queso.getRadio();
					row[2] = queso.getPrecioBase();
					row[3] = queso.getPrecioUnitario();
					row[4] = Fabrica.getInstance().volumenPorQueso(queso);
					row[5] = ((QCilindro) queso).getLongitud();
					model.addRow(row);
				}
			}
			if(rdbtnCilindroH.isSelected()) {
				if(queso instanceof QHueco) {
					row[0] = queso.getCodigo();
					row[1] = queso.getRadio();
					row[2] = queso.getPrecioBase();
					row[3] = queso.getPrecioUnitario();
					row[4] = Fabrica.getInstance().volumenPorQueso(queso);
					row[5] = ((QHueco) queso).getLongitud();
					row[6] = ((QHueco) queso).getRadioInterno();
					model.addRow(row);
				}
			}
			
		}
	}
	
	
}
