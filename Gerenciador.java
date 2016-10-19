package trabalhoArqOrg;

import java.util.Scanner;

public class Gerenciador {
	
	public static Barramento barr = new Barramento();
	
	public void rodaGerenciador (){
		double ram, clock, larg;
		Scanner c = new Scanner (System.in);
		//receber os dados do Tamanho da Ram, Clock e Largura do Barramento
		System.out.println("Tamanho da ram:");
		ram = c.nextDouble();
		System.out.println("Frequencia do Clock:");
		clock = c.nextDouble();
		System.out.println("Largura do barramento:");
		larg = c.nextDouble();
		c.close();
		
		//salvar no programa os parametros para execução
		barr.setTamMemoria(ram);
		barr.setClock(clock);
		barr.setLargBarr(larg);
		
		
		
		
	}
	
}
