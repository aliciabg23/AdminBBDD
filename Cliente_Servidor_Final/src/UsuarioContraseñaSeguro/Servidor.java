package UsuarioContraseñaSeguro;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

	public static void main(String args[]) {
		ServerSocket servidor;
		try {
			servidor=new ServerSocket(5000);
		
		System.out.println("Servidor iniciado");
		PrintWriter fsalida;
		while(true) {
			Socket cliente=new Socket();
			cliente=servidor.accept();
			fsalida = new PrintWriter(cliente.getOutputStream(), true);
			Hilo hilo=new Hilo(servidor,cliente);
			hilo.start();
	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
