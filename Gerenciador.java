package trabalhoArqOrg;

import java.io.IOException;
import java.util.Scanner;

public class Gerenciador {
	
	public static Barramento barr;
	public static EntradaSaida entradaSaida;
	public static MemoriaRam memoriaRam;
	public static Cpu cpu;
	  
	public static void rodaGerenciador (int tamanhoRam, int clock, int larguraBarramento) throws IOException{	
		//instanciando as classes
		barr = new Barramento(clock, larguraBarramento);
		entradaSaida = new EntradaSaida();
        memoriaRam = new MemoriaRam(tamanhoRam);
       // cpu = new Cpu();
        
        //threads
        Thread tES = new Thread(entradaSaida);
        tES.start();
        Thread tRam = new Thread(memoriaRam);
        tRam.start();	
        //Thread tCpu = new Thread(cpu);
       // tCpu.start();
	}
	
	public void usouBarramento(){
		
	}
	
}


























