package trabalhoArqOrg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemoriaRam implements Runnable {
	
	private static final Object Integer = 1;
	public int[] memoria;
	private List<Integer[]> filaDados = new ArrayList<Integer[]>();
	private int filaControle;
	public int posMemoria = 0, cont = 0;
	public boolean barramentoEndLivre = false;
	
	public MemoriaRam(int tam){
		memoria = new int[tam];
		for (int i = 0; i < this.memoria.length; i++) {
			this.memoria[i] = -1;
		}
	}
	
	/**
	* Metodos para tratar sobre o recebimento e verificação da memoria
	*/
	
	public int posMemoriaDisponivel(){
		while (memoria[cont] != -1){
			if (cont > memoria.length/2 || memoria[cont + (Gerenciador.barr.getLarguraBanda())/4] != -1){  esvaziaMemoria();  }
			if (memoria[cont] == -1 && memoria[cont + (Gerenciador.barr.getLarguraBanda())/4] == -1){
				break;
			}
			cont++;
		}		
		
		/*for(cont = 0; cont <= (memoria.length / 2); cont+=4){
			if (cont > memoria.length - Gerenciador.barr.getLarguraBanda()){esvaziaMemoria();}
			if (memoria[cont] == -1){
				break;
			}
		}*/
		return cont;
	}
	
	public void avanPosMemoria(){
		this.posMemoria += 4;
		
		if (this.posMemoria > (memoria.length / 2)){
			this.posMemoria = 0;
		}
	}
	
	public void esvaziaMemoria(){
		
		for (int i = 0; i < (Gerenciador.barr.getLarguraBanda())/4; i++) {
			
			memoria[this.posMemoria]     = -1;
			memoria[this.posMemoria + 1] = -1;
			memoria[this.posMemoria + 2] = -1;
			memoria[this.posMemoria + 3] = -1;
			avanPosMemoria();
		}
		
		
	}
	
	public void addMemoria(Integer [] comando, int endereco){
			memoria[endereco]     = comando[0];
			memoria[endereco + 1] = comando[1];
			memoria[endereco + 2] = comando[2];
			memoria[endereco + 3] = comando[3];
			
			avanPosMemoria();
	}

	@Override
	public void run() {		
		while (true){
			//Mostrando as alterações na memoria
			/**System.out.println("Entrei RAM");
			for (int i = 0; i < memoria.length; i++) {
				if (i%32 == 0){System.out.println();}
				System.out.print(memoria[i] + " |");
			}
			System.out.println();*/
			
			try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EntradaSaida.class.getName()).log(Level.SEVERE, null, ex);
            }
			if(barramentoEndLivre){
				//verifica se tem sinal de controle no barramento 
				if (Gerenciador.barr.getFilaCont(0) == 1){
					System.out.println("RAM: recebi sinal da ES");
					filaControle = Gerenciador.barr.getFilaCont(0);
					Gerenciador.barr.setNullFilaCont();
				} else {
					this.filaControle = 0;
				}
				
				//manda endereço para quem mandou o sinal
				if (filaControle != 0 && filaControle == 1){ //se o sinal é da e/s
					this.posMemoria = posMemoriaDisponivel();
					Gerenciador.barr.barramentoEndereco("E/A", posMemoria);
					System.out.println("RAM: Mandei endereço para EntradaSaida");
					this.barramentoEndLivre = false;
				}
				if (filaControle != 0 && filaControle == 2){ //se o sinal é da cpu
					if(barramentoEndLivre){
						this.posMemoria = posMemoriaDisponivel();
						Gerenciador.barr.barramentoEndereco("CPU", posMemoria);
						this.barramentoEndLivre = false;
					}
				}
			}
			
			//verifica se tem dados no barramento e salva num variavel local
			if (!Gerenciador.barr.getFilaDad().isEmpty()) {
				filaDados = Gerenciador.barr.getFilaDad();
				Gerenciador.barr.setNullFilaDad();
			} else {
				filaDados = null;
			}

			// grava na memoria os dados recebidos
			if (filaDados != null) {
				for (int i = 0; i < filaDados.size(); i++) {
					addMemoria(filaDados.get(i), this.posMemoria);
					System.out.println("\n\n\n\n");
					this.barramentoEndLivre = true;
				}
			
			
			Gerenciador.barr.setFilaDad(null);
		
		}
			
		}
	}

	/**
	 * @param barramentoEndLivre the barramentoEndLivre to set
	 */
	public void setBarramentoEndLivre(boolean barramentoEndLivre) {
		this.barramentoEndLivre = barramentoEndLivre;
	}
}
