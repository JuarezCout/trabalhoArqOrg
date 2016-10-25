package trabalhoArqOrg;

import java.io.IOException;
import java.util.Scanner;

public class TesteMetodos {

	public static void main(String[] args) throws IOException {
		
		/*double tamMemoria, clock, largBarr;
		Scanner valMem   = new Scanner(System.in);
		Scanner valClock = new Scanner(System.in);
		Scanner valLarg  = new Scanner(System.in);
	
		System.out.println("Tamanho da Memoria: ");
		tamMemoria = valMem.nextDouble();
		
		System.out.println("Clock: ");
		clock = valClock.nextDouble();
		
		System.out.println("Tamanho da Memoria: ");
		largBarr = valLarg.nextDouble();*/
		
		Gerenciador g = new Gerenciador();
		g.rodaGerenciador();
		
	}

}
