package trabalhoArqOrg;

import java.io.IOException;
import java.util.Scanner;

public class Gerenciador {
	
	public static Barramento barr = new Barramento();
	  
	public void rodaGerenciador () throws IOException{
		/*double ram, clock, larg;
		Scanner c = new Scanner (System.in);
		//receber os dados do Tamanho da Ram, Clock e Largura do Barramento
		System.out.println("Tamanho da ram:");
		ram = c.nextDouble();
		System.out.println("Frequencia do Clock:");
		clock = c.nextDouble();
		System.out.println("Largura do barramento:");
		larg = c.nextDouble();
		c.close();
		
		//salvar na Arquitetura os parametros para execução
		barr.setTamMemoria(ram);
		barr.setClock(clock);
		barr.setLargBarr(larg);*/
		
		EntradaSaida ea = new EntradaSaida();
                MemoriaRam ram = new MemoriaRam();
                Thread tES = new Thread(ea);
                tES.start();
                Thread tRam = new Thread(ram);
                tRam.start();	
	}
	
	public void usouBarramento(){
		
	}
	
}


























