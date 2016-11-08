package trabalhoArqOrg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemoriaRam implements Runnable {
	
	private static final Object Integer = 1;
	public int[] memoria;
	public int posMemoria = 0;
	
	public MemoriaRam(int tam){
		this.memoria = new int[tam];
		for (int i = 0; i < this.memoria.length; i++) {
			this.memoria[i] = -1;
		}
	}
	
	/**
	* Metodos para tratar sobre o recebimento e verificação da memoria
	*/
	
	public boolean posEspMemoria(){
		for(int cont = 0; cont <= (memoria.length / 2); cont+=4){
			if (memoria[cont] == -1){
				this.posMemoria = cont;
				return true;
			}
		}
		return false;
	}
	
	public void avanPosMemoria(){
		this.posMemoria += 4;
		
		if (this.posMemoria > (memoria.length / 2)){
			this.posMemoria = 0;
		}
	}
	
	public void esvaziaMemoria(){
		
		for (int i = 0; i < (Gerenciador.barr.getLarguraBanda())/4; i++) {
			avanPosMemoria();
			memoria[this.posMemoria]     = -1;
			memoria[this.posMemoria + 1] = -1;
			memoria[this.posMemoria + 2] = -1;
			memoria[this.posMemoria + 3] = -1;
		}
		
		
	}
	
	public void addMemoria(Integer [] comando, int posMemoria){
			memoria[this.posMemoria]     = comando[0];
			memoria[this.posMemoria + 1] = comando[1];
			memoria[this.posMemoria + 2] = comando[2];
			memoria[this.posMemoria + 3] = comando[3];
			
			avanPosMemoria();
	}

	@Override
	public void run() {
		System.out.println("Entrei RAM");
		while (true){
			try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EntradaSaida.class.getName()).log(Level.SEVERE, null, ex);
            }
			
			int[] filaControle = Gerenciador.barr.getFilaCont();
			/*if (Gerenciador.barr.getFilaCont() != null){		
                filaControle = Gerenciador.barr.getFilaCont();
                Gerenciador.barr.setFilaCont(null);
            } else {
            	filaControle = null;
            }*/
			
			if(filaControle != null){
				posEspMemoria();
				if (filaControle[0] == 1){
					if(posEspMemoria() == true){
						Gerenciador.barr.barramentoEndereco("E/A", posMemoria);	
					} else {
						Scanner c = new Scanner(System.in);
						System.out.println("Acabou a Memoria para salvar as instruçoes!\nDeseja continuar, apagando os comandos mais antigos? [SIM||NAO]");
					    String resp = c.nextLine();
					    if (resp == "SIM"){
					    	esvaziaMemoria();
					    	Gerenciador.barr.barramentoEndereco("E/A", posMemoria);						
					    } else {
					    	System.out.println("Memoria vai continuar cheia, logo encerraremos o Emulador!");
					    	c.close();
					    	return;
					    }
					    c.close();
					}
				}
			
			}
			
			List<Integer[]> filaDados;
			filaDados = Gerenciador.barr.getFilaDad();
			
			if (filaDados != null) {
				for (int i = 0; i < filaDados.size(); i++) {
					addMemoria(filaDados.get(i), Gerenciador.barr.getEndereco()[0]);
				}
			
			
			Gerenciador.barr.setFilaDad(null);
		
		}
			
		}
	}
}
