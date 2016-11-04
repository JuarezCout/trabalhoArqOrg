package trabalhoArqOrg;

import java.util.Scanner;

public class MemoriaRam {
	
	private static final Object Integer = 1;
	public static int[] memoria = { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
									-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
	public int posMemoria = 0;
	//public boolean verificaTemPedido = true, verificaTemDados = true, verificaBarramentoEndereco = false;
	
	public void rodaRam (){
		while(Gerenciador.barr.filaCont.contains(Integer)){ //Loop para verificar se tem alguem pedindo endereço
			if(Gerenciador.barr.isBarrContLiberado() == false){ // Se não tiver pedido de memoria, vai rodar até apareceu um pedido
				while (!Gerenciador.barr.barrContLiberado == false){
					if(Gerenciador.barr.barrContLiberado == true){
						break;
					}
					System.out.println("Não tem");
			}
		} //Executa quando tem algum pedido de endereço
			System.out.println("Recebi a pergunta");
			//Gerenciador.barr.setPerguntaMemoria(false); // Seta false na variavel apos verificar que tem pedido
			Gerenciador.barr.setBarrContLiberado(true); //Libera o barramento de controle
			posEspMemoria();
			if(posEspMemoria()){ // Se tiver memoria, pergunta se pode mandar para a EntradaSaida o endereço
					if(Gerenciador.barr.isBarrEndLiberado() == false){
						while (Gerenciador.barr.isBarrEndLiberado() == false){
							if (Gerenciador.barr.isBarrEndLiberado() == true){ break;}
						}	
						Gerenciador.barr.barramentoEndereco("E/A", posMemoria);	
					} 
			} else { //Senão tiver, manda um aviso que encheu e se deseja continuar a processar apagando os comandos amis antigos
				Scanner c = new Scanner(System.in);
				System.out.println("Acabou a Memoria para salvar as instruçoes!\nDeseja continuar, apagando os comandos mais antigos? [SIM||NAO]");
			    String resp = c.nextLine();
			    if (resp == "SIM"){
			    	esvaziaMemoria();
			    	while (!Gerenciador.barr.isBarrEndLiberado()){
						if(Gerenciador.barr.isBarrEndLiberado() != Gerenciador.barr.isBarrEndLiberado()){
							while (!Gerenciador.barr.isBarrEndLiberado()){
								if (Gerenciador.barr.isBarrEndLiberado()){ break;	}
						}
					} 
						Gerenciador.barr.barramentoEndereco("E/A", posMemoria);						
					}
			    } else {
			    	System.out.println("Memoria vai continuar cheia, logo encerraremos o Emulador!");
			    	c.close();
			    	return;
			    }
			    c.close();
			}
			
		}
		
		/*while (verificaTemDados){ 
			if (verificaTemDados != Gerenciador.barr.isPerguntaDados()){ // Se não tiver com o barramento liberado, vai ficar rodando até liberar
				while (!verificaTemDados){
					if(verificaTemDados == Gerenciador.barr.isPerguntaDados()){ break; }
			}
		}   //Pergunta se tem dados para salvar
			System.out.println("Recebi os dados");
			Gerenciador.barr.setPerguntaDados(false);
			Gerenciador.barr.setBarrDadLiberado(true);
			addMemoria(Gerenciador.barr.dados, Gerenciador.barr.getEnderecoEA());
			Gerenciador.barr.setPerguntaDados(false);
			
			System.out.print("Memoria: {");
			for (int cont = 0; cont < 32; cont++) {
				System.out.print(MemoriaRam.memoria[cont] + " ");
			}
			System.out.println("}");
		}*/
	}
	
	/**
	* Metodos para tratar sobre o recebimento e verificação da memoria
	*/
	
	public boolean posEspMemoria(){
		for(int cont = 0; cont <= 12; cont+=4){
			if (memoria[cont] == -1){
				posMemoria = cont;
				return true;
			}	
		}
		return false;
	}
	
	public void avanPosMemoria(){
		posMemoria += 4;
		
		if (posMemoria == 16){
			posMemoria = 0;
		}
	}
	
	public void esvaziaMemoria(){
		avanPosMemoria();
		
		memoria[posMemoria]     = -1;
		memoria[posMemoria + 1] = -1;
		memoria[posMemoria + 2] = -1;
		memoria[posMemoria + 3] = -1;
		
	}
	
	public void addMemoria(int [] comando, int posMemoria){
		
		memoria[posMemoria]     = comando[0];
		memoria[posMemoria + 1] = comando[1];
		memoria[posMemoria + 2] = comando[2];
		memoria[posMemoria + 3] = comando[3];
		
		avanPosMemoria();
	}
}
