package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Fabrica;
import logico.QCilindro;
import logico.QEsfera;
import logico.QHueco;
import logico.Queso;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class RegQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JPanel panelEsfera;
	private JSpinner spnPrecioUnitario;
	private JSpinner spnRadio;
	private JSpinner spnCosto;
	private JRadioButton rdbtnEsfera;
	private JRadioButton rdbtnCilindro;
	private JRadioButton rdbtnCilinHueco;
	private JPanel panelCilindro;
	private JSpinner spnLongitud;
	private JPanel panelCilindroHueco;
	private JSpinner spnLongitudH;
	private JSpinner spnRadioInt;
	private Queso queso = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegQueso dialog = new RegQueso(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param b 
	 */
	public RegQueso(Queso aux) {
		queso = aux;
		setTitle("Registrar Queso");
		if(queso != null) {
			setTitle("Modificar Queso");
		}
		
		setModal(true);
		setBounds(100, 100, 479, 356);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelDatosQueso = new JPanel();
		panelDatosQueso.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatosQueso.setBounds(10, 11, 443, 98);
		contentPanel.add(panelDatosQueso);
		panelDatosQueso.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Datos Generales:");
		lblNewLabel.setBounds(10, 0, 149, 14);
		panelDatosQueso.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setBounds(10, 25, 55, 14);
		panelDatosQueso.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(75, 25, 127, 20);
		txtCodigo.setText("QE-"+Fabrica.genCodQueso);
		if(queso != null) {
			txtCodigo.setText(queso.getCodigo());
		}
		panelDatosQueso.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Precio Costo:");
		lblNewLabel_2.setBounds(212, 53, 84, 14);
		panelDatosQueso.add(lblNewLabel_2);
		
		spnCosto = new JSpinner();
		spnCosto.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				activarRegistrar();
			}
		});
		spnCosto.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnCosto.setBounds(306, 50, 127, 20);
		panelDatosQueso.add(spnCosto);
		
		JLabel lblNewLabel_3 = new JLabel("Precio Unidad:");
		lblNewLabel_3.setBounds(212, 28, 84, 14);
		panelDatosQueso.add(lblNewLabel_3);
		
		spnPrecioUnitario = new JSpinner();
		spnPrecioUnitario.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				activarRegistrar();
			}

		});
		spnPrecioUnitario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnPrecioUnitario.setBounds(306, 25, 127, 20);
		panelDatosQueso.add(spnPrecioUnitario);
		
		spnRadio = new JSpinner();
		spnRadio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				activarRegistrar();
			}
		});
		spnRadio.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadio.setBounds(75, 50, 128, 20);
		panelDatosQueso.add(spnRadio);
		
		JLabel lblNewLabel_4 = new JLabel("Radio:");
		lblNewLabel_4.setBounds(10, 50, 46, 14);
		panelDatosQueso.add(lblNewLabel_4);
		
		JPanel panelTipoQueso = new JPanel();
		panelTipoQueso.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTipoQueso.setBounds(10, 120, 443, 67);
		contentPanel.add(panelTipoQueso);
		panelTipoQueso.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Seleccione tipo de queso:");
		lblNewLabel_5.setBounds(10, 0, 198, 14);
		panelTipoQueso.add(lblNewLabel_5);
		
		rdbtnEsfera = new JRadioButton("Esferico");
		rdbtnEsfera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnEsfera.isSelected()) {
					rdbtnCilindro.setSelected(false);
					rdbtnCilinHueco.setSelected(false);
					panelEsfera.setVisible(true);
					panelCilindro.setVisible(false);
					panelCilindroHueco.setVisible(false);
					if(queso == null) {
						txtCodigo.setText("QE-"+Fabrica.genCodQueso);	
					}else {
						int codigo = obtenerNumero(queso.getCodigo());
						txtCodigo.setText("QE-"+codigo);
					}
					activarRegistrar();
				}
				activarRegistrar();
			}
		});
		if(queso==null) {

			rdbtnEsfera.setSelected(true);
		}
		rdbtnEsfera.setBounds(29, 21, 109, 23);
		panelTipoQueso.add(rdbtnEsfera);
		
		rdbtnCilindro = new JRadioButton("Cilindrico");
		rdbtnCilindro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCilindro.isSelected()) {
					rdbtnCilinHueco.setSelected(false);
					rdbtnEsfera.setSelected(false);
					panelCilindro.setVisible(true);
					panelEsfera.setVisible(false);
					panelCilindroHueco.setVisible(false);
					if(queso == null) {
						txtCodigo.setText("QC-"+Fabrica.genCodQueso);	
					}else {
						int codigo = obtenerNumero(queso.getCodigo());
						txtCodigo.setText("QC-"+codigo);
					}
					activarRegistrar();
				}
				activarRegistrar();
			}
		});
		rdbtnCilindro.setBounds(167, 21, 109, 23);
		panelTipoQueso.add(rdbtnCilindro);
		
		rdbtnCilinHueco = new JRadioButton("Cilindrico hueco");
		rdbtnCilinHueco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCilinHueco.isSelected()) {
					rdbtnCilindro.setSelected(false);
					rdbtnEsfera.setSelected(false);
					panelCilindroHueco.setVisible(true);
					panelCilindro.setVisible(false);
					panelEsfera.setVisible(false);
					if(queso == null) {
						txtCodigo.setText("QH-"+Fabrica.genCodQueso);	
					}
					else {
						int codigo = obtenerNumero(queso.getCodigo());
						txtCodigo.setText("QH-"+codigo);
					}
					activarRegistrar();
				}
				activarRegistrar();
			}

			
		});
		rdbtnCilinHueco.setBounds(305, 21, 109, 23);
		panelTipoQueso.add(rdbtnCilinHueco);
		
		panelEsfera = new JPanel();
		panelEsfera.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEsfera.setBounds(10, 198, 443, 72);
		contentPanel.add(panelEsfera);
		panelEsfera.setLayout(null);
		
		panelCilindro = new JPanel();
		panelCilindro.setVisible(false);
		panelCilindro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCilindro.setBounds(10, 198, 443, 72);
		contentPanel.add(panelCilindro);
		panelCilindro.setLayout(null);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(10, 28, 79, 14);
		panelCilindro.add(lblLongitud);
		
		spnLongitud = new JSpinner();
		spnLongitud.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				activarRegistrar();
			}
		});
		spnLongitud.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnLongitud.setBounds(69, 25, 135, 20);
		panelCilindro.add(spnLongitud);
		
		panelCilindroHueco = new JPanel();
		panelCilindroHueco.setVisible(false);
		panelCilindroHueco.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCilindroHueco.setBounds(10, 198, 443, 72);
		contentPanel.add(panelCilindroHueco);
		panelCilindroHueco.setLayout(null);
		
		JLabel lblLongitud2 = new JLabel("Longitud:");
		lblLongitud2.setBounds(10, 28, 79, 14);
		panelCilindroHueco.add(lblLongitud2);
		
		spnLongitudH = new JSpinner();
		spnLongitudH.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				activarRegistrar();
			}
		});
		spnLongitudH.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnLongitudH.setBounds(69, 25, 135, 20);
		panelCilindroHueco.add(spnLongitudH);
		
		JLabel lblRadioInt = new JLabel("Radio Interno:");
		lblRadioInt.setBounds(214, 28, 79, 14);
		panelCilindroHueco.add(lblRadioInt);
		
		spnRadioInt = new JSpinner();
		spnRadioInt.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				activarRegistrar();
			}
		});
		spnRadioInt.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioInt.setBounds(298, 25, 135, 20);
		panelCilindroHueco.add(spnRadioInt);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "Seguro que desea guardar los cambios?", "Advertencia.", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if(option == 0) {
							String codigo = txtCodigo.getText();
							float radio = Float.parseFloat(spnRadio.getValue().toString());
							float costo = Float.parseFloat(spnCosto.getValue().toString());
							float unitario = Float.parseFloat(spnPrecioUnitario.getValue().toString());
							if(queso == null) {
								
								Queso cheese = null;
								if(rdbtnEsfera.isSelected()) {
									cheese = new QEsfera(codigo, radio, costo, unitario);
									}
								if(rdbtnCilindro.isSelected()) {
									cheese = new QCilindro(codigo, radio, costo, Float.parseFloat(spnLongitud.getValue().toString()), unitario);	
								}
								if(rdbtnCilinHueco.isSelected()) {
									cheese = new QHueco(codigo, radio, costo, Float.parseFloat(spnLongitudH.getValue().toString()), Float.parseFloat(spnRadioInt.getValue().toString()), unitario);
								}
									Fabrica.getInstance().insertarQueso(cheese);
									JOptionPane.showMessageDialog(null,cheese.getCodigo()+" se ha registrado con exito!", "Informacion.", JOptionPane.INFORMATION_MESSAGE);
								
							}
							else {
								queso.setPrecioBase(costo);
								queso.setPrecioUnitario(unitario);
								JOptionPane.showMessageDialog(null,codigo+" se ha editado con exito!", "Informacion.", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							clear();
							
						}
					}

					
				});
				btnRegistrar.setEnabled(false);
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int option = JOptionPane.showConfirmDialog(null, "Seguro que desea cancelar registro?", "Advertencia.", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if(option == 0) {
							dispose();
						}
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			
		}
		revision();
		
	}
	public void revision() {
		if(queso != null) {
			btnRegistrar.setText("Editar");
			txtCodigo.setText(queso.getCodigo());
			spnCosto.setValue(queso.getPrecioBase());
			spnPrecioUnitario.setValue(queso.getPrecioUnitario());
			spnRadio.setValue(queso.getRadio());
			if(queso instanceof QHueco) {
				rdbtnCilinHueco.setSelected(true);
				
				activarPaneles();
				spnRadioInt.setValue(((QHueco) queso).getRadioInterno());
				spnLongitudH.setValue(((QHueco) queso).getLongitud());
			}
			else if(queso instanceof QCilindro && !(queso instanceof QHueco)) {
				rdbtnCilindro.setSelected(true);
				activarPaneles();
				spnLongitud.setValue(((QCilindro) queso).getLongitud());
			}
			else if(queso instanceof QEsfera) {
				System.out.println(2);
				rdbtnEsfera.setSelected(true);
				activarPaneles();
			}
			spnRadio.setEnabled(false);
			spnLongitud.setEnabled(false);
			spnLongitudH.setEnabled(false);
			spnRadioInt.setEnabled(false);
			rdbtnCilinHueco.setEnabled(false);
			rdbtnCilindro.setEnabled(false);
			rdbtnEsfera.setEnabled(false);
		}
		
	}

	public void activarPaneles() {
		if(rdbtnCilindro.isSelected()) {
			rdbtnCilinHueco.setSelected(false);
			rdbtnEsfera.setSelected(false);
			panelCilindro.setVisible(true);
			panelEsfera.setVisible(false);
			panelCilindroHueco.setVisible(false);
		}
		if(rdbtnEsfera.isSelected()) {
			rdbtnCilinHueco.setSelected(false);
			rdbtnCilindro.setSelected(false);
			panelCilindro.setVisible(false);
			panelEsfera.setVisible(true);
			panelCilindroHueco.setVisible(false);
		}
		if(rdbtnCilinHueco.isSelected()) {
			rdbtnCilindro.setSelected(false);
			rdbtnEsfera.setSelected(false);
			panelCilindro.setVisible(false);
			panelEsfera.setVisible(false);
			panelCilindroHueco.setVisible(true);
		}
		
	}

	public void activarRegistrar() {
		if(!txtCodigo.getText().isEmpty() && Float.parseFloat(spnRadio.getValue().toString())>0 &&
				rdbtnEsfera.isSelected() || rdbtnCilindro.isSelected() || rdbtnCilinHueco.isSelected()) {
			if(rdbtnEsfera.isSelected()) {
				btnRegistrar.setEnabled(true);
			}
			if(rdbtnCilindro.isSelected()) {
				if(Float.parseFloat(spnLongitud.getValue().toString())>0) {
					btnRegistrar.setEnabled(true);
				}
				else {
					btnRegistrar.setEnabled(false);
				}
			}
			if(rdbtnCilinHueco.isSelected()) {
				if(Float.parseFloat(spnLongitudH.getValue().toString())>0 &&
						Float.parseFloat(spnRadioInt.getValue().toString())>0 && 
						Float.parseFloat(spnRadio.getValue().toString()) > Float.parseFloat(spnRadioInt.getValue().toString())) {
					btnRegistrar.setEnabled(true);
				}
				else {
					btnRegistrar.setEnabled(false);
				}
			}
		}
		else {
			btnRegistrar.setEnabled(false);
		}
		
	}
	public void clear() {
		txtCodigo.setText("QE-"+Fabrica.genCodQueso);
		rdbtnEsfera.setSelected(true);
		rdbtnCilindro.setSelected(false);
		rdbtnCilinHueco.setSelected(false);
		panelEsfera.setVisible(true);
		panelCilindro.setVisible(false);
		panelCilindroHueco.setVisible(false);
		spnCosto.setValue(0);
		spnLongitud.setValue(0);
		spnLongitudH.setValue(0);
		spnRadio.setValue(0);
		spnPrecioUnitario.setValue(0);
		spnRadioInt.setValue(0);
		
	}
	public int obtenerNumero(String codigo) {
		int i = 0;
		String cod = "";
		for (int j = 0; j < codigo.length(); j++) {
			if(Character.isDigit(codigo.charAt(j))) {
				cod+=codigo.charAt(j);
			}
		}
		i = Integer.parseInt(cod);
		return i;
	}
}
