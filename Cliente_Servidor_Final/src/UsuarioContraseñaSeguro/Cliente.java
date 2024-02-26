package UsuarioContraseñaSeguro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente extends Thread implements ActionListener{
	
	BufferedReader entrada;
    static PrintWriter salida;
    String host = "localhost";
    int puerto = 5000;
    
    Socket cliente;
    static String op="";
	static boolean cerrado=false;
	
	JFrame inicio;
	JLabel usuarioLabel;
	JLabel contLabel;
	JTextField usuario;
	JTextField contrasena;
	JButton iniciar;
	JPanel panelPrincipal;
	JPanel panelNorte;
	JPanel panelCentro;
	JPanel panelSur;
	
	JFrame aplicacion;
	private JPanel panel;
	private JTextArea textarea;
	private JScrollPane scrollpane;
	private JButton botonListar;
	private JButton botonAgregar;
	private JButton botonActualizar;
	private JButton botonEliminar;
	JButton botonSubir;
	JButton botonCerrar;
	JFrame datos;
	JLabel idLabel;
	JTextField id;
	JLabel nombreLabel;
	JTextField nombre;
	JLabel latitudLabel;
	JTextField latitud;
	JLabel longitudLabel;
	JTextField longitud;
	JLabel descripcionLabel;
	JTextField descripcion;
	JButton confirmar;
	JPanel panelDatos;
	JPanel panelLatitud;
	JPanel panelLongitud;
	JPanel panelDescripcion;
	JPanel panelBoton;
	JPanel panelNombre;
	JPanel panelId;
	static boolean sesionIniciada=false;
	
	//PANEL CONFRIMACION
	JFrame confirmacion;
	JPanel panelConfirmacion;
	JPanel panelTextoConfirmacion;
	JPanel panelBotonesConfirmacion;
	JLabel textoConfirmacion;
	JButton si;
	JButton no;
	
	
	//PANEL CAMBIO TABLA
	JFrame cambioTabla;
	JPanel cambioPanel;
	JPanel tablaPanel;
	JPanel botonTablaPanel;
	JLabel tablaLabel;
	JTextField tabla;
	JButton tablaBotonConf;
	
	JButton abrirCambio;
	
	//TIEMPO SESION
	JLabel tiempo;
	
		
	public Cliente() {
		this.inicio=new JFrame();
		this.usuarioLabel=new JLabel("Usuario:");
		this.contLabel=new JLabel("Contraseña:");
		this.usuario=new JTextField(15);
		this.contrasena=new JTextField(15);
		this.iniciar=new JButton("Iniciar");
		this.panelPrincipal=new JPanel();
		this.panelNorte=new JPanel();
		this.panelCentro=new JPanel();
		this.panelSur=new JPanel();
		this.botonSubir=new JButton("Subir archivo");
		
		this.aplicacion=new JFrame();
		this.panel = new JPanel();
		this.textarea = new JTextArea();
		this.scrollpane = new JScrollPane(this.textarea);
		this.botonListar = new JButton("LISTAR");
		this.botonAgregar = new JButton("AGREGAR");
		this.botonActualizar = new JButton("ACTUALIZAR");
		this.botonEliminar = new JButton("ELIMINAR");
		this.botonCerrar=new JButton("CERRAR");
		this.abrirCambio=new JButton("CAMBIAR TABLA");
		
		this.tiempo=new JLabel("00:00:00");

		this.panel.setLayout(null);

		this.botonListar.setBounds(270, 50, 130, 30);
		this.botonAgregar.setBounds(80, 50, 130, 30);
		this.botonActualizar.setBounds(270, 120, 130, 30);
		this.botonEliminar.setBounds(80, 120, 130, 30);
		this.scrollpane.setBounds(40, 170, 400, 200);
		this.botonCerrar.setBounds(50, 380, 100, 30);
		this.botonSubir.setBounds(160, 380, 120, 30);
		this.abrirCambio.setBounds(300, 380, 150, 30);
		this.tiempo.setBounds(20,20,100,20);


		this.botonListar.addActionListener(this);
		this.botonAgregar.addActionListener(this);
		this.botonActualizar.addActionListener(this);
		this.botonEliminar.addActionListener(this);

		this.panel.add(this.botonListar);
		this.panel.add(this.botonAgregar);
		this.panel.add(this.botonActualizar);
		this.panel.add(this.botonEliminar);
		this.panel.add(this.scrollpane);
		this.panel.add(this.botonCerrar);
		this.panel.add(this.botonSubir);
		this.panel.add(this.abrirCambio);
		this.panel.add(tiempo);
		aplicacion.add(panel);
		aplicacion.setSize(500, 500);

		 this.panelId=new JPanel();
		 this.id=new JTextField(15);
		 this.idLabel=new JLabel("ID");
		 this.datos=new JFrame();
		 this.latitudLabel=new JLabel("Latitud");
		 this.latitud=new JTextField(15);
		 this.longitudLabel=new JLabel("Longitud");
		 this.longitud=new JTextField(15);
		 this.descripcionLabel=new JLabel("Descripcion");
		 this.descripcion=new JTextField(15);
		 this.confirmar=new JButton("Confirmar datos");
		 this.panelDatos=new JPanel();
		 this.panelLatitud=new JPanel();
		 this.panelLongitud=new JPanel();
		 this.panelDescripcion=new JPanel();
		 this.panelBoton=new JPanel();
		 this.panelNombre=new JPanel();
		 this.nombreLabel=new JLabel("Nombre");
		 this.nombre=new JTextField(15);
		 

		this.cambioTabla=new JFrame();
		this.cambioPanel=new JPanel();
		this.tablaLabel=new JLabel("Nombre de tabla");
		this.tabla=new JTextField(15);
		this.tablaBotonConf=new JButton("Cambiar");
		this.tablaPanel=new JPanel();
		this.botonTablaPanel=new JPanel();
		
		this.cambioPanel.setLayout(new GridLayout(2,1));
		this.tablaPanel.setLayout(new FlowLayout());
		this.botonTablaPanel.setLayout(new FlowLayout());
		
		this.tablaPanel.add(tablaLabel);
		this.tablaPanel.add(tabla);
		this.cambioPanel.add(tablaPanel);
		this.botonTablaPanel.add(tablaBotonConf);
		this.cambioPanel.add(botonTablaPanel);
		this.cambioTabla.add(cambioPanel);
		this.cambioTabla.setSize(300,300);
		this.cambioTabla.setVisible(false);
		 
		 panelDatos.setLayout(new GridLayout(6,1));
		 panelId.setLayout(new FlowLayout());
		 panelNombre.setLayout(new FlowLayout());
		 panelLatitud.setLayout(new FlowLayout());
		 panelLongitud.setLayout(new FlowLayout());
		 panelDescripcion.setLayout(new FlowLayout());
		 panelBoton.setLayout(new FlowLayout());
		 
		 panelId.add(idLabel);
		 panelId.add(id);
		 panelNombre.add(nombreLabel);
		 panelNombre.add(nombre);
		 panelLatitud.add(latitudLabel);
		 panelLatitud.add(latitud);
		 panelLongitud.add(longitudLabel);
		 panelLongitud.add(longitud);
		 panelDescripcion.add(descripcionLabel);
		 panelDescripcion.add(descripcion);
		 panelBoton.add(confirmar);
		 panelDatos.add(panelId);
		 panelDatos.add(panelNombre);
		 panelDatos.add(panelLatitud);
		 panelDatos.add(panelLongitud);
		 panelDatos.add(panelDescripcion);
		 panelDatos.add(panelBoton);
		 datos.add(panelDatos);
		 datos.setSize(300,300);
		 
		 
		 
		 panelPrincipal.setLayout(new GridLayout(3,1));
			panelNorte.setLayout(new FlowLayout());
			panelCentro.setLayout(new FlowLayout());
			panelSur.setLayout(new FlowLayout());
			
			panelNorte.add(usuarioLabel);
			panelNorte.add(usuario);
			panelPrincipal.add(panelNorte);
			
			panelCentro.add(contLabel);
			panelCentro.add(contrasena);
			panelPrincipal.add(panelCentro);
			
			panelSur.add(iniciar);
			panelPrincipal.add(panelSur);
			
			inicio.add(panelPrincipal);
			inicio.setSize(300, 400);
			
			this.confirmacion=new JFrame();
			this.panelConfirmacion=new JPanel();
			this.panelTextoConfirmacion=new JPanel();
			this.panelBotonesConfirmacion=new JPanel();
			this.textoConfirmacion=new JLabel();
			this.si=new JButton("Si");
			this.no=new JButton("No");
			
			this.panelConfirmacion.setLayout(new GridLayout(2,1));
			this.panelTextoConfirmacion.setLayout(new FlowLayout());
			this.panelBotonesConfirmacion.setLayout(new FlowLayout());
			
			this.panelTextoConfirmacion.add(textoConfirmacion);
			this.panelBotonesConfirmacion.add(si);
			this.panelBotonesConfirmacion.add(no);
			this.panelConfirmacion.add(panelTextoConfirmacion);
			this.panelConfirmacion.add(panelBotonesConfirmacion);
			this.confirmacion.add(panelConfirmacion);
			this.confirmacion.setSize(700,100);
			this.confirmacion.setVisible(false);
			
		 
		 try {
			cliente = new Socket(host, puerto);
		
		salida = new PrintWriter(cliente.getOutputStream(), true);
		entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		 } catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		addActionEvent();
		inicio.setVisible(true);
		
	}
	

	
	public void addActionEvent() {
		this.iniciar.addActionListener(this);
		botonEliminar.addActionListener(this);
		botonActualizar.addActionListener(this);
		botonAgregar.addActionListener(this);
		botonListar.addActionListener(this);
		this.confirmar.addActionListener(this);
		this.botonCerrar.addActionListener(this);
		this.si.addActionListener(this);
		this.no.addActionListener(this);
		this.botonSubir.addActionListener(this);
		this.tablaBotonConf.addActionListener(this);
		this.abrirCambio.addActionListener(this);
	}
	public void enviar(String linea) {
		salida.println(linea);
	}
	public String recibir() throws IOException {
		String linea=entrada.readLine();
		return linea;
		
	}
	
	String parametros;
	String tablaBBDD="mascotas";

	public void actionPerformed(ActionEvent e) {
		// Recogemos el tipo de boton que se ha pulsado y su texto
		Object source = e. getSource ();
		
		try {
		if(source==iniciar) {
			
		    enviar("iniciar");
	        String usuario="";
	        String cont="";
	        String eco;
	        
	        
	        usuario=this.usuario.getText();
	        cont=this.contrasena.getText();
	        
	        MessageDigest md;
				md=MessageDigest.getInstance("MD5");
				byte dataBytes[]=cont.getBytes();
				md.update(dataBytes);
				byte resumen[]=md.digest();
				cont=Hexadecimal(resumen);

		        String mensaje=usuario+"/"+cont;
		    
		    enviar(mensaje);
		    
		   
		    }else if(source==botonCerrar){
	        	this.cliente.close();
	        	sesionIniciada=false;
	        	this.aplicacion.setVisible(false);
	        	cerrado=true;
	        }else if(source==botonListar) {
			    op="listar";
			    enviar(op);
			    enviar(tablaBBDD);
			    System.out.println("Envia opcion");
			   
			    
		}else if(source==botonAgregar) {
			op="agregar";
			datos.setVisible(true);
			
		}else if(source==botonEliminar) {
			op="eliminar";
			datos.setVisible(true);
			
		}else if(source==abrirCambio) {
			cambioTabla.setVisible(true);
		}else if(source==tablaBotonConf) {
			String nombreTabla=this.tabla.getText();
			tablaBBDD=nombreTabla;
			cambioTabla.setVisible(false);
		}else if(source==si) {
			this.confirmacion.setVisible(false);
			this.datos.setVisible(false);
			enviar(op);
			enviar(parametros);
			
		}else if(source==no) {
			this.confirmacion.setVisible(false);
			
		}else if(source==botonActualizar) {
			op="actualizar";
			this.datos.setVisible(true);
		}else if(source==botonSubir) {
			op="subir";
			enviar(op);
		}else if(source==confirmar) {
	        String mensaje;
	        String eco;
	        
	        if(op.equals("agregar")) {
		    //String id=this.id.getText().toString();
	        String nombre=this.nombre.getText().toString();
	        String  latitud=this.latitud.getText().toString();
	        String longitud=this.longitud.getText().toString();
	        String descripcion=this.descripcion.getText().toString();
	        
			parametros = "tabla="+tablaBBDD+"&nombre="+nombre+"&latitud=" + latitud + "&longitud=" + longitud+ "&descripcion=" + descripcion;
			this.textoConfirmacion.setText("Vas a añadir a '"+nombre+"' con latitud '"+latitud+"', longitud '"+longitud+"' y descripcion '"+descripcion+"'. ¿Estás seguro?");
			this.confirmacion.setVisible(true);
			System.out.println(parametros);
			
	        }else if(op.equals("eliminar")) {
		        String id=this.id.getText().toString();
				parametros = "tabla="+tablaBBDD+"&id=" + id;
				this.textoConfirmacion.setText("Vas a eliminar al elemento con ID '"+id+"'. ¿Estás seguro?");
				this.confirmacion.setVisible(true);
				
	        }else if(op.equals("actualizar")) {
	        	String id=this.id.getText();
	        	String nombre=this.nombre.getText();
	        	String latitud=this.latitud.getText();
	        	String longitud=this.longitud.getText();
	        	String descripcion=this.descripcion.getText();
	        	
				parametros = "tabla="+tablaBBDD+"&id="+id+"&nombre="+nombre+"&latitud=" + latitud + "&longitud=" + longitud+ "&descripcion=" + descripcion;
	        	
	        	this.textoConfirmacion.setText("Vas a modificarle al elemento con ID '"+id+"' el nombre a '"+nombre+"', la latitud a '"+latitud+"', la longitud a '"+longitud+"' y la descripcion a '"+descripcion+"'. ¿Estas seguro?");
	        	this.confirmacion.setVisible(true);
	        	
	        }
		}
	       
	        System.out.println("FIN DEL ENVIO . . .");
	        
			} catch (NoSuchAlgorithmException | IOException l) {
				// TODO Auto-generated catch block
			}
			
		
		
		

	}
	
	 static String Hexadecimal(byte[] resumen) {
			// TODO Auto-generated method stub
			String hex="";
			for(int i=0;i<resumen.length;i++) {
				String h=Integer.toHexString(resumen[i] & 0xFF);
				if(h.length()==1) {
					hex+="0";
				}
				hex+=h;
			}
			return hex.toUpperCase();
		}
		static ContadorTiempo ct=new ContadorTiempo();

	 
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
		 Cliente c=new Cliente();
		 cerrado=false;
	    c.start();

	    ct.start();
		ct.iniciar(true);

			
			
		}
	
	@Override
	public void run() {
		String eco;
		// TODO Auto-generated method stub
		System.out.println("Arranca");
		

		while(true) {
		if(sesionIniciada) {
			this.tiempo.setText(ct.getTiempo());
		 try {
			if((eco=recibir())!=null) {
				this.textarea.append(eco);
				this.textarea.append("\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			 try {
				eco = recibir();
				//ecoDesencriptado=desencriptar(eco);

				if(eco.equals("1")) {
			    	aplicacion.setVisible(true);
			    	inicio.setVisible(false);
			    	sesionIniciada=true;
			    	
			    }else {
					JOptionPane.showMessageDialog(null, "Usuario y contraseña incorrecto");

			    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			    
		}
		if(cerrado==true) {
			break;
		}
		}
	}
	
	
	

}
