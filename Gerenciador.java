package trabalhoArqOrg;

import java.util.Scanner;

public class Gerenciador {
	
	public static Barramento barr = new Barramento();
	public static int EndNaEA,  //variavel que recebe o endereço para EntradaSaida atraves do barramento
		          EndNaCpu; //variavel que recebe o endereço para CPU atraves do barramento
				      
	public static boolean ContNaRam; //variavel que recebe o valor de controle avisando se tem ou não espaço ou codigo para processar
	
	public static int[] DadNaEA,  //
			    DadNaCpu, //variaveis que contem os dados para cada componente da Arquitetura
			    DadNaRam; //
	  
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
		
		//salvar na Arquitetura os parametros para execução
		barr.setTamMemoria(ram);
		barr.setClock(clock);
		barr.setLargBarr(larg);
		
				
	}
	
	public void usouBarramento(){
		
	}
	
}
