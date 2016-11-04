package trabalhoArqOrg;

import java.util.ArrayList;

public class Barramento {
	
	public double tamMemoria, clock, largBarr;
	public boolean barrEndLiberado = true, barrDadLiberado = true, barrContLiberado = true;
	//public boolean perguntaMemoria = false, recebeMemoria = false, perguntaEntradaSaida = false, perguntaDados =  false, perguntaCpu = false;
	public int[] dados;
	public int enderecoEA;
	public ArrayList<Integer> filaCont = new ArrayList<Integer>();
	public ArrayList<Integer> filaEnd  = new ArrayList<Integer>();
	public ArrayList<Integer> filaDad  = new ArrayList<Integer>();
	 
	//metodos que levem Pacote de dados entre uma classe e outra
	//barramento(REMETENTE, DESTINATARIO, PACOTE)
	
	//De dados: transporte do que irá ser salvo ou executado
	public void barramentoDados(String dest, int[] pacote){
		
		if(dest == "RAM" ){
			barrDadLiberado = false;
			//perguntaDados = true;
			dados = pacote;
			
		} else if (dest == "CPU"){
			barrDadLiberado = false;
			//perguntaDados = true;
		}
	}
	
	//De endereço: Envio de endereços para salvar ou pegar dados
	public void barramentoEndereco(String dest, int pacote){
		if(dest == "E/A"){
			barrEndLiberado = false;
			setFilaEnd(pacote);
			//perguntaEntradaSaida = true;
			//perguntaMemoria = false;
			enderecoEA = pacote;
		} else if (dest == "CPU"){
			barrEndLiberado = false;
		//	perguntaCpu = true;
			
		}
	}
	
	//De controle: Pergunta se tem espaço ou algo para executar
	public void barramentoControle(String dest, String remet){
		if(dest == "RAM" && remet == "CPU"){
			barrContLiberado = false;
			//perguntaMemoria = true;
		} else if (dest == "RAM" && remet == "E/A"){
			barrContLiberado = false;
			setFilaCont(1);
			//perguntaMemoria = true;
		}
	}
	
	/**
	 * @return the filaCont
	 */
	public Integer getFilaCont(int i) {
		return filaCont.get(i);
	}

	/**
	 * @param filaCont the filaCont to set
	 */
	public void setFilaCont(int filaCont) {
		this.filaCont.add(filaCont);
	}

	/**
	 * @return the filaEnd
	 */
	public Integer getFilaEnd(int i) {
		return filaEnd.get(i);
	}

	/**
	 * @param filaEnd the filaEnd to set
	 */
	public void setFilaEnd(int filaEnd) {
		this.filaEnd.add(filaEnd);
	}

	/**
	 * @return the filaDad
	 */
	public Integer getFilaDad(int i) {
		return filaDad.get(i);
	}

	/**
	 * @param filaDad the filaDad to set
	 */
	public void setFilaDad(int filaDad) {
		this.filaDad.add(filaDad);
	}

	/**
	 * @return the enderecoEA
	 */
	public int getEnderecoEA() {
		return enderecoEA;
	}

	/**
	 * @param enderecoEA the enderecoEA to set
	 */
	public void setEnderecoEA(int enderecoEA) {
		this.enderecoEA = enderecoEA;
	}

	/**
	 * @return se o barramento de endereço esta liberado
	 */
	public boolean isBarrEndLiberado() {
		return barrEndLiberado;
	}

	/**
	 * @param define quando o barramento ta ocupado
	 */
	public void setBarrEndLiberado(boolean barrEndLiberado) {
		this.barrEndLiberado = barrEndLiberado;
	}

	/**
	 * @return se o barramento de dados esta liberado
	 */
	public boolean isBarrDadLiberado() {
		return barrDadLiberado;
	}

	/**
	 * @param define quando o barramento ta ocupado
	 */
	public void setBarrDadLiberado(boolean barrDadLiberado) {
		this.barrDadLiberado = barrDadLiberado;
	}

	/**
	 * @return se o barramento de controle esta liberado
	 *
	 */
	public boolean isBarrContLiberado() {
		return barrContLiberado;
	}

	/**
	 * @param define quando o barramento ta ocupado
	 */
	public void setBarrContLiberado(boolean barrContLiberado) {
		this.barrContLiberado = barrContLiberado;
	}

	/**
	 * @return the tamMemoria
	 */
	public double getTamMemoria() {
		return tamMemoria;
	}

	/**
	 * @param tamMemoria the tamMemoria to set
	 */
	public void setTamMemoria(double tamMemoria) {
		this.tamMemoria = tamMemoria;
	}

	/**
	 * @return the clock
	 */
	public double getClock() {
		return clock;
	}

	/**
	 * @param clock the clock to set
	 */
	public void setClock(double clock) {
		this.clock = clock;
	}

	/**
	 * @return the largBarr
	 */
	public double getLargBarr() {
		return largBarr;
	}

	/**
	 * @param largBarr the largBarr to set
	 */
	public void setLargBarr(double largBarr) {
		this.largBarr = largBarr;
		}

	/**
	 * @return the dados
	 */
	public int[] getDados() {
		return dados;
	}

	/**
	 * @param dados the dados to set
	 */
	public void setDados(int[] dados) {
		this.dados = dados;
	}
	
	/**
	 * @return the perguntaMemoria
	 *//*
	public boolean isPerguntaMemoria() {
		return perguntaMemoria;
	}

	*//**
	 * @param perguntaMemoria the perguntaMemoria to set
	 *//*
	public void setPerguntaMemoria(boolean perguntaMemoria) {
		this.perguntaMemoria = perguntaMemoria;
	}

	*//**
	 * @return the recebeMemoria
	 *//*
	public boolean isRecebeMemoria() {
		return recebeMemoria;
	}

	*//**
	 * @param recebeMemoria the recebeMemoria to set
	 *//*
	public void setRecebeMemoria(boolean recebeMemoria) {
		this.recebeMemoria = recebeMemoria;
	}

	*//**
	 * @return the perguntaEntradaSaida
	 *//*
	public boolean isPerguntaEntradaSaida() {
		return perguntaEntradaSaida;
	}

	*//**
	 * @param perguntaEntradaSaida the perguntaEntradaSaida to set
	 *//*
	public void setPerguntaEntradaSaida(boolean perguntaEntradaSaida) {
		this.perguntaEntradaSaida = perguntaEntradaSaida;
	}

	*//**
	 * @return the perguntaDados
	 *//*
	public boolean isPerguntaDados() {
		return perguntaDados;
	}

	*//**
	 * @param perguntaDados the perguntaDados to set
	 *//*
	public void setPerguntaDados(boolean perguntaDados) {
		this.perguntaDados = perguntaDados;
	}

	*//**
	 * @return the perguntaCpu
	 *//*
	public boolean isPerguntaCpu() {
		return perguntaCpu;
	}

	*//**
	 * @param perguntaCpu the perguntaCpu to set
	 *//*
	public void setPerguntaCpu(boolean perguntaCpu) {
		this.perguntaCpu = perguntaCpu;
	}*/
	
	
}
