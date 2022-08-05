package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import logico.Fabrica;
import logico.Factura;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Menu extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	static Socket sdf = null;
	static DataInputStream EntradaSocket = null;
	static DataOutputStream SalidaSocket = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream queseria;
				FileOutputStream queseria2;
				ObjectInputStream reader;
				ObjectOutputStream queseriaWrite;
				try {
					queseria = new FileInputStream("queseria.dat");
					reader = new ObjectInputStream(queseria);
					int genCodFact = reader.readInt();
					int genCodQueso = reader.readInt();
					
					Fabrica temp = (Fabrica)reader.readObject();
					Fabrica.setFabrica(temp);
					temp.genCodFact = genCodFact;
					temp.genCodQueso = genCodQueso;
					
					queseria.close();
					reader.close();
					
				} catch (FileNotFoundException e) {
					try {
						queseria2 = new FileOutputStream("queseria.dat");
						queseriaWrite = new ObjectOutputStream(queseria2);
			
						queseriaWrite.writeInt(Fabrica.getInstance().genCodFact);
						queseriaWrite.writeInt(Fabrica.getInstance().genCodQueso);
						queseriaWrite.writeObject(Fabrica.getInstance());
						
						queseria2.close();
						queseriaWrite.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					sdf = new Socket("127.0.0.1", 7000);
					EntradaSocket = new DataInputStream(new BufferedInputStream(sdf.getInputStream()));
					SalidaSocket = new DataOutputStream(new BufferedOutputStream(sdf.getOutputStream()));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {

				FileOutputStream queseria;
				ObjectOutputStream queseriaWrite;
				try {
					queseria = new FileOutputStream("queseria.dat");
					queseriaWrite = new ObjectOutputStream(queseria);
					queseriaWrite.writeInt(Fabrica.getInstance().genCodFact);
					queseriaWrite.writeInt(Fabrica.getInstance().genCodQueso);
					queseriaWrite.writeObject(Fabrica.getInstance());
				} catch (FileNotFoundException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width,dim.height-30);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Fabrica");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Crear Queso");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegQueso regQ = new RegQueso(null);
				regQ.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar Quesos");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListQueso lista = new ListQueso();
				lista.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Tienda");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Facturar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Facturar fac = new Facturar();
				fac.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Lista de Facturas");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListFac listaFac = new ListFac(null);
				listaFac.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Clientes");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Lista de Clientes");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListClients listaClient = new ListClients(false);
				listaClient.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public static void respaldo(Factura factura) {
		try {
			if(factura!=null) {
				SalidaSocket.writeUTF(factura.getCodigo());
				SalidaSocket.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
