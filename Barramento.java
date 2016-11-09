package trabalhoArqOrg;

import java.util.ArrayList;
import java.util.List;

public class Barramento {
	public Integer[] endereco;
	public int[] dados;
	public int largura, clock, larguraBanda;
	public ArrayList<int[]> filaCont = new ArrayList<int[]>();
	public List<Integer[]> filaEnd     = new ArrayList<Integer[]>();
	public List<Integer[]> filaDad   = new ArrayList<Integer[]>();
	 
	public Barramento (int clock, int largura){
		this.clock = clock;
		this.largura = largura;
		this.larguraBanda = (clock*largura)/8;
		
	}
	 
	//metodos que levem Pacote de dados entre uma classe e outra
	//barramento(REMETENTE, DESTINATARIO, PACOTE)
	
	
	//De dados: transporte do que irá ser salvo ou executado
	public void barramentoDados(String dest, List<Integer[]> pacote, Integer[] end){
		
		if(dest == "RAM" ){
			filaDad = pacote;
			endereco[0] = end[0];			
		} else if (dest == "CPU"){
			filaDad = pacote;
			endereco[0] = end[0];
		}
	}
	
	//De endereço: Envio de endereços para salvar ou pegar dados
	public void barramentoEndereco(String dest, Integer pacote){
		if(dest == "E/A"){
			endereco[0] = pacote;
			endereco[1] = 1; // 1 para dizer que o endereco é para e/a
			filaEnd.set(0, endereco);
		} else if (dest == "CPU"){
			endereco[0] = pacote;
			endereco[1] = 2; // 2 para dizer que o endereco é para cpu	
			filaEnd.set(0, endereco);
		}
	}
	
	//De controle: Pergunta se tem espaço ou algo para executar
	public void barramentoControle(String dest, String remet, int sinal){
		if(dest == "RAM" && remet == "CPU"  ){
			int[] cont = {4};
            for (int i = 0; i < this.larguraBanda/4; i++) { 
				filaCont.add(i, cont); // 4 para dizer que cpu quer ler em endereço na RAM
			}
		}else if(dest == "RAM" && remet == "CPU"  ){
			int[] cont = {3};
            for (int i = 0; i < this.larguraBanda/4; i++) { 
				filaCont.add(i, cont); // 2 para dizer que a cpu quer ler comando na RAM
			}
		}else if(dest == "RAM" && remet == "CPU"  ){
			int[] cont = {2};
            for (int i = 0; i < this.larguraBanda/4; i++) { 
				filaCont.add(i, cont); // 2 para dizer que a cpu quer gravar na RAM
			}
		}else if (dest == "RAM" && remet == "E/A"){
			int[] cont = {1};
			for (int i = 0; i < this.larguraBanda/4; i++) { 				
				filaCont.add(i, cont); // 1 para dizer que a e/s quer gravar informações na RAM
			}
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
	public int[] getFilaCont() {
		return filaCont.get(0);
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
	public Integer[] getFilaEnd() {
		return filaEnd.get(0);
	}

	/**
	 * @param filaEnd the filaEnd to set
	 */
	public void setFilaEnd(Integer[] filaEnd) {
		this.filaEnd.add(filaEnd);
	}

	/**
	 * @return the filaDad
	 */
	public List<Integer[]> getFilaDad() {
		return filaDad;
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
	public Integer[] getEndereco() {
		return endereco;
	}

	
}
