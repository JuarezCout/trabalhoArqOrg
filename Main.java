package trabalhoArqOrg;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			EntradaSaida ea = new EntradaSaida();
			Cpu cpu         = new Cpu();
			MemoriaRam mRam = new MemoriaRam();
			Gerenciador ger = new Gerenciador();
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
			Gerenciador.barr.setTamMemoria(ram);
			Gerenciador.barr.setClock(clock);
			Gerenciador.barr.setLargBarr(larg);
			
			//ea.rodaEntradaSaida();
	}

}
