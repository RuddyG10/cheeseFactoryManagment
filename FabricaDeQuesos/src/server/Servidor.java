package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

//import jdk.internal.org.jline.utils.InputStreamReader;

public class Servidor extends Thread {

	private static DataInputStream facturaRespaldo = null;
	private static DataOutputStream facturaEscritor = null;

	public static void main(String[] args) throws IOException {
		ServerSocket sfd = null;
		try {
			sfd = new ServerSocket(7000);
		} catch (Exception e) {
			System.out.println("Error en comunicacion."+e);
			System.exit(1);
		}
		while (true) {
			try {
				Socket nsfd = sfd.accept();
				facturaRespaldo = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
				facturaEscritor = new DataOutputStream(new BufferedOutputStream(nsfd.getOutputStream()));
				
	 			String linea = facturaRespaldo.readUTF();
	 			if(!linea.equals("")) {
	 				try {
	 					System.out.println(linea);
						File original = new File(linea+".txt");
						FileInputStream input;
						input = new FileInputStream(original);
						BufferedReader br = new BufferedReader(new InputStreamReader(input));
						File respaldo = new File(linea+"_Respaldo.txt");
						
						FileOutputStream input2;
						input2 = new FileOutputStream(respaldo);
						BufferedWriter br2 = new BufferedWriter(new OutputStreamWriter(input2));
						try {
							String text;
							while((text = br.readLine())!=null) {
								try {
									br2.append(text);
									br2.newLine();
								} catch (Exception e) {
									System.out.println("Error-"+e.getMessage());
								}
							}
							br2.close();
							input.close();
							input2.close();
						} catch (Exception e) {
							System.out.println("Error-"+e.getMessage());
						}
						
						
					} catch (Exception e) {
						System.out.println("Error-"+e.getMessage());
					}
	 			}
				
				
			} catch (IOException e) {
				// TODO: handle exception
				System.out.println("Error- "+e.getMessage());
			}
			
			
		}
	}

}
