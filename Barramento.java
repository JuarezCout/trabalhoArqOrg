package trabalhoArqOrg;

public class Barramento {
	
	public double tamMemoria, clock, largBarr;
	public static boolean barrEnd = true, barrDad = true, barrCont = true;
	
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

	//metodos que levem Pacote de dados entre uma classe e outra
	//barramento(REMETENTE, DESTINATARIO, PACOTE)
	
	//De dados: transporte do que irá ser salvo ou executado
	public void barramentoDados(String dest, int[] pacote){
		
		if(dest == "RAM" ){
			barrDad = false;				
			Gerenciador.DadNaRam = pacote;
			
		} else if (dest == "CPU"){
			barrDad = false;
			Gerenciador.DadNaCpu = pacote;
		}
	}
	
	//De endereço: Envio de endereços para salvar ou pegar dados
	public void barramentoEndereco(String dest, int pacote){
		if(dest == "E/A"){
			barrEnd = false;
		} else if (dest == "CPU"){
			barrEnd = false;
		} else if (dest == "RAM"){
			barrEnd = false;
		} 
	}
	
	//De controle: Pergunta se tem espaço ou algo para executar
	public void barramentoControle(String dest, String remet){
		if(dest == "RAM" && remet == "CPU"){
			barrCont = false;
		} else if (dest == "RAM" && remet == "E/A"){
			barrCont = false;
		}
	}
	
	
}
