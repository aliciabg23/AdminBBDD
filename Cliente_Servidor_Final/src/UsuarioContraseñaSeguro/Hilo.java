package UsuarioContraseñaSeguro;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Hilo extends Thread{

	ServerSocket servidor;
	Socket cliente;
	private PrintWriter fsalida;
    private BufferedReader fentrada;
	
	public Hilo(ServerSocket servidor, Socket cliente) throws IOException {
		// TODO Auto-generated constructor stub
		this.servidor=servidor;
		this.cliente=cliente;
	
		fsalida = new PrintWriter(cliente.getOutputStream(), true);
	    fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		
	}
	public void enviar(String linea) {
		fsalida.println(linea);
	}
	public String recibir() throws IOException {
		String linea=fentrada.readLine();
		return linea;
		
	}
	
	
	
	public void run() {
		
		while(true) {
			try {
		System.out.println("COMUNICO CON: " + cliente.toString());

        String cadena = "";
        System.out.println("Se conecta");
		String op= recibir();
        System.out.println("Lee la linea");
        System.out.println("opcion:"+op);
        
        while(!op.equals(""))
                if(op.equals("iniciar")) {
                	cadena = recibir();// OBTENER CADENA
                	//cadena=Enciptador.decrypt(cadena);
                	int indice=cadena.indexOf("/");
                	String usuario=cadena.substring(0, indice);
                	String cont=cadena.substring(indice+1,cadena.length());
        			URL url = new URL("http://localhost/programacion_de_procesos/usuarioContrasena.php");
        			URLConnection conexion=url.openConnection();
        			conexion.setDoOutput(true);
        			
        			String parametros = "user=" + usuario + "&password=" + cont;

					PrintWriter output = new PrintWriter(conexion.getOutputStream());
					output.write(parametros);
					output.close();

					BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
					String linea;
					while ((linea = br.readLine()) != null) {
						System.out.println(linea);
						if (linea.equals("1")) {
							enviar("1");
						} else {
							enviar("0");
						}
					}
					br.close();
					op="";
					enviar("BIENVENIDO AL SERVIDOR DE MASCOTAS");

                
                }else if(op.equals("listar")) {
                	op="";
                	URL url = new URL("http://localhost/programacion_de_procesos/listarAnimales.php");
        			URLConnection conexion=url.openConnection();
        			conexion.setDoOutput(true);
        			System.out.println("Entra en listar");
        			String tabla=recibir();
        			String parametros="tabla="+tabla;
        			PrintWriter output = new PrintWriter(conexion.getOutputStream());
					output.write(parametros);
					output.close();

        			BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
					String linea;
					while ((linea = br.readLine()) != null) {
						enviar(linea);
						System.out.println(linea);
					}
					
					System.out.println("Envia las lineas");
					br.close();
					op="";
                }else if(op.equals("agregar")) {
                	System.out.println("Entra en agregar");
                    
                    System.out.println("Lee los datos");
                    String parametros=recibir();
                    System.out.println(parametros);
            			URL url = new URL("http://localhost/programacion_de_procesos/insertarAnimales.php");
            			URLConnection conexion=url.openConnection();
            			conexion.setDoOutput(true);
            			

    					PrintWriter output = new PrintWriter(conexion.getOutputStream());
    					output.write(parametros);
    					output.close();
            			System.out.println("Envia los datos al php");


    					BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
    					String linea= br.readLine();
    					System.out.println("Lee la respuesta del php");
    					
    					enviar(linea);
    					System.out.println("Envia la respuesta del php");
    					br.close();
    					op="";
                }else if(op.equals("actualizar")) {
                    String parametros=recibir();

                	URL url = new URL("http://localhost/programacion_de_procesos/actualizarAnimales.php");
        			URLConnection conexion=url.openConnection();
        			conexion.setDoOutput(true);
        			

					PrintWriter output = new PrintWriter(conexion.getOutputStream());
					output.write(parametros);
					output.close();
        			System.out.println("Envia los datos al php");


					BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
					String linea= br.readLine();
					System.out.println("Lee la respuesta del php");
					
					enviar(linea);
					System.out.println("Envia la respuesta del php");
					br.close();
					op="";
                }else if(op.equals("subir")) {
                    String serv = "39259589.servicio-online.net";
                    String usuario = "alumnos_practicas";
                    String clave = "L1tter@tor";
                    String directorio = "alicia_procesos";

                    FTPClient cliente = new FTPClient();

                    String archivo_origen = "C:\\Users\\abail\\Documents\\DAM\\segundo\\programacion procesos\\fotos_justo\\foto1_mascotas.jpg"; // ARCHIVO DE MI PC
                    String archivo_destino = "foto1_mascotas.jpg"; // ARCHIVO QUE SE SUBE AL SERVIDOR

                    System.out.println("Nos conectamos a: " + serv);
                    try {

                        cliente.connect(serv);
                        cliente.enterLocalPassiveMode(); // MODO PASIVO

                        boolean login = cliente.login(usuario, clave);

                        if (login) {
                            enviar("Login correcto");
                        } else {
                            enviar("Login incorrecto");
                            cliente.disconnect();
                            System.exit(1);
                        }
                        FTPFile[] archivos = cliente.listFiles();

                        // Imprimir la lista de directorios
                        System.out.println("Directorios en el servidor FTP:");
                        for (FTPFile archivo : archivos) {
                         
                                System.out.println(archivo.getName());
                            
                        }

                        System.out.println("Directorio actual: " + cliente.printWorkingDirectory());
                   boolean creado=false;
                        // CAMBIAR DIRECTORIO
                   while(!creado) {
                        if (cliente.changeWorkingDirectory(directorio)) {
                            enviar("Directorio actual: " + cliente.printWorkingDirectory());
                            creado=true;
                        } else {
                           cliente.makeDirectory(directorio);
                           
                        }
                   }
                   FTPFile[] archivos2 = cliente.listFiles();

                   // Imprimir la lista de directorios
                   System.out.println("Directorios en el servidor FTP:");
                   for (FTPFile archivo : archivos2) {
                    
                           System.out.println(archivo.getName());
                       
                   }
                   

                        // SUBIR FICHERO
                        fsalida.println("Subiendo archivo: " + archivo_origen);
                        cliente.setFileType(FTP.BINARY_FILE_TYPE);
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo_origen));
                        if (cliente.storeFile(archivo_destino, in)) {
                            enviar("Subido correctamente . . . ");
                        } else {
                            enviar("No se ha podido subir . . .");
                        }

                        in.close();

                        boolean logout = cliente.logout();

                        if (logout) {
                            enviar("Logout del servidor FTP . . .");
                        } else {
                            enviar("Error al hacer Logout . . .");
                        }

                        cliente.disconnect();
                        System.out.println("Desconectado");
                        op="";
                    } catch (IOException e) {
                        System.out.println("Error");
                    }
                }else if(op.equals("eliminar")) {
                	System.out.println("Entra en eliminar");

                    String id=recibir();
                    System.out.println(id);

            			URL url = new URL("http://localhost/programacion_de_procesos/borrarAnimales.php");
            			URLConnection conexion=url.openConnection();
            			conexion.setDoOutput(true);
            			

    					PrintWriter output = new PrintWriter(conexion.getOutputStream());
    					output.write(id);
    					output.close();

    					BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
    					String linea= br.readLine();
    					System.out.println(linea);
    					enviar(linea);
    					op="";
                }
			}catch(Exception e) {
				
			}
		}
	}}