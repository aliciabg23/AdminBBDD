package UsuarioContraseñaSeguro;

public class ContadorTiempo extends Thread{
	int horas=0;
	int minutos=0;
	int segundos=0;
	boolean sesion=false;
	
	public void run() {
	while(true) {
	while(sesion) {
		try {
			Thread.sleep(1000);
			segundos+=1;
			if(segundos==60) {
				minutos+=1;
				segundos=0;
				if(minutos==60) {
					horas+=1;
					minutos=0;
				}
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		}
	}
	public String getTiempo() {
		return horas+":"+minutos+":"+segundos;
		
	}
	public void iniciar(boolean in) {
		sesion=in;
	}

}
