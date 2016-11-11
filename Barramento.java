package trabalhoArqOrg;

import java.util.ArrayList;
import java.util.List;

public class Barramento {
	public int[] dados, endereco = new int[2];
	public int largura, clock, larguraBanda;
	public ArrayList<int[]> filaCont;
	public List<int[]> filaEnd ;
	public List<Integer[]> filaDad;
	 
	public Barramento (int clock, int largura){
		this.clock = clock;
		this.largura = largura;
		this.larguraBanda = (clock*largura)/8;
		filaCont = new ArrayList<int[]>();
		filaEnd   = new ArrayList<int[]>();
		filaDad   = new ArrayList<Integer[]>();
		filaDad   = new ArrayList<Integer[]>();
		
	}
	 
	//metodos que levem Pacote de dados entre uma classe e outra
	//barramento(REMETENTE, DESTINATARIO, PACOTE)
	
	
	//De dados: transporte do que irá ser salvo ou executado
	public void barramentoDados(String dest, List<Integer[]> pacote, Integer[] end){
		if(dest == "RAM" ){
			this.filaDad.add(pacote.get(0));
		} else if (dest == "CPU"){
			this.filaDad = pacote;
			this.endereco[0] = end[0];
		}
	}
	
	//De endereço: Envio de endereços para salvar ou pegar dados
	public void barramentoEndereco(String dest, int pacote){
		if(dest == "E/A"){
			this.endereco[0] = pacote;
			this.endereco[1] = 1; // 1 para dizer que o endereco é para e/a
			this.filaEnd.add(endereco);
			Gerenciador.entradaSaida.setBarramentoDadLivre(true);
		} else if (dest == "CPU"){
			this.endereco[0] = pacote;
			this.endereco[1] = 2; // 2 para dizer que o endereco é para cpu	
			this.filaEnd.add(endereco);
		}
	}
	
	//De controle: Pergunta se tem espaço ou algo para executar
	public void barramentoControle(String dest, String remet, int sinal){
		if(dest == "RAM" && remet == "CPU"  ){
			int[] cont = {4};
            for (int i = 0; i < this.larguraBanda/4; i++) { 
            	this.filaCont.add(i, cont); // 4 para dizer que cpu quer ler em endereço na RAM
			}
		}else if(dest == "RAM" && remet == "CPU"  ){
			int[] cont = {3};
            for (int i = 0; i < this.larguraBanda/4; i++) { 
            	this.filaCont.add(i, cont); // 2 para dizer que a cpu quer ler comando na RAM
			}
		}else if(dest == "RAM" && remet == "CPU"  ){
			int[] cont = {2};
            for (int i = 0; i < this.larguraBanda/4; i++) { 
            	this.filaCont.add(i, cont); // 2 para dizer que a cpu quer gravar na RAM
			}
		}else if (dest == "RAM" && remet == "E/A"){
			int[] cont = {1};				
			this.filaCont.add(0, cont); // 1 para dizer que a e/s quer gravar informações na RAM
			Gerenciador.memoriaRam.setBarramentoEndLivre(true);
		}
	}
	
	/**
	 * @return the larguraBanda
	 */
	public int getLarguraBanda() {
		return larguraBanda;
	}

	/**
	 * @return the filaCont
	 */
	public int getFilaCont(int i) {
		return filaCont.get(0)[i];
	}
	
	public void setNullFilaCont(){
		this.filaCont.clear();
	}

	/**
	 * @param filaCont the filaCont to set
	 */
	public void setFilaCont(int[] filaCont) {
		this.filaCont.add(filaCont);
	}

	/**
	 * @return the filaEnd
	 */
	public int[] getFilaEnd() {
		return filaEnd.get(0);
	}
	
	public void setNullFilaEnd(){
		this.filaEnd.clear();
	}

	/**
	 * @param filaEnd the filaEnd to set
	 */
	public void setFilaEnd(int[] filaEnd) {
		this.filaEnd.add(filaEnd);
	}

	/**
	 * @return the filaDad
	 */
	public List<Integer[]> getFilaDad() {
		return filaDad;
	}
	
	public void setNullFilaDad(){
		this.filaDad.clear();
	}
	
	/**
	 * @param filaDad the filaDad to set
	 */
	public void setFilaDad(Integer[] filaDad) {
		this.filaDad.add(filaDad);
	}
	
	/**
	 * @return the endereco
	 */
	public int[] getEndereco() {
		return endereco;
	}

	
}
