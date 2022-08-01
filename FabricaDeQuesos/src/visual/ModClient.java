package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Fabrica;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModClient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Cliente client;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JButton btnEditar;
	private JButton btnCancelar;
	private boolean ver;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModClient dialog = new ModClient(null,false);
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
	public ModClient(Cliente selected, boolean b) {
		ver = b;
		setTitle("Modificar Cliente");
		setModal(true);
		client = selected;
		setBounds(100, 100, 496, 226);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Cedula:");
			lblNewLabel.setBounds(16, 38, 45, 14);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			txtCedula.setText(client.getCedula());
			txtCedula.setEditable(false);
			txtCedula.setBounds(88, 34, 133, 20);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(237, 38, 52, 14);
			panel.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char letra = arg0.getKeyChar();
					if(!Character.isLetter(letra)) {
						getToolkit().beep();
						arg0.consume();
					}
				}
			});
			txtNombre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarBoton();
				}

				
			});
			
			txtNombre.setText(client.getNombre());
			txtNombre.setBounds(316, 35, 133, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblNewLabel_2 = new JLabel("Telefono:");
			lblNewLabel_2.setBounds(16, 90, 62, 14);
			panel.add(lblNewLabel_2);
			
			txtTelefono = new JTextField();
			txtTelefono.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char num = e.getKeyChar();
					if(!Character.isDigit(num)) {
						getToolkit().beep();
						e.consume();
					}
				}
			});
			txtTelefono.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarBoton();
				}
			});
			txtTelefono.setText(client.getTelefono());
			txtTelefono.setBounds(88, 88, 133, 20);
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Direccion:");
			lblNewLabel_3.setBounds(237, 90, 69, 14);
			panel.add(lblNewLabel_3);
			
			txtDireccion = new JTextField();
			txtDireccion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarBoton();
				}
			});
			txtDireccion.setText(client.getDireccion());
			txtDireccion.setBounds(316, 87, 133, 20);
			panel.add(txtDireccion);
			txtDireccion.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEditar = new JButton("Editar");
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "Desea guardar los cambios?", "Advertencia.", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						if(option == 0) {
							client.setCedula(txtCedula.getText());
							client.setNombre(txtNombre.getText());
							client.setDireccion(txtDireccion.getText());
							client.setTelefono(txtTelefono.getText());
							JOptionPane.showMessageDialog(null, "Cliente modificado.", "Informacion.", JOptionPane.INFORMATION_MESSAGE);
							btnEditar.setEnabled(false);
							dispose();
						}
						
					}
				});
				btnEditar.setEnabled(false);
				btnEditar.setActionCommand("OK");
				buttonPane.add(btnEditar);
				getRootPane().setDefaultButton(btnEditar);
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
		revision();
	}
	public void revision() {
		if(ver) {
			setTitle("Informacion del cliente");
			txtDireccion.setEditable(false);
			txtTelefono.setEditable(false);
			txtNombre.setEditable(false);
			btnEditar.setVisible(false);
		}
	}

	public void activarBoton() {
		if(!txtDireccion.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtTelefono.getText().isEmpty()) {
			if(!txtDireccion.getText().equalsIgnoreCase(client.getDireccion()) || !txtNombre.getText().equalsIgnoreCase(client.getNombre())
					|| !txtTelefono.getText().equalsIgnoreCase(client.getTelefono())) {
				btnEditar.setEnabled(true);
			}
			else {
				btnEditar.setEnabled(false);
			}
		}
		else {
			btnEditar.setEnabled(false);
		}
	}
}
