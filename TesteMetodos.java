package trabalhoArqOrg;

import java.io.IOException;
import java.util.Scanner;

public class TesteMetodos {

	public static void main(String[] args) throws IOException {
		
		int tamanhoRam, clock, larg;
		Scanner c = new Scanner (System.in);
		//receber os dados do Tamanho da Ram, Clock e Largura do Barramento
		System.out.println("Tamanho da ram:");
		tamanhoRam = c.nextInt();
		System.out.println("Frequencia do Clock:");
		clock = c.nextInt();
		System.out.println("Largura do barramento:");
		larg = c.nextInt();
		c.close();	
		
		
		try {
			Gerenciador.rodaGerenciador(tamanhoRam, clock, larg);
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
