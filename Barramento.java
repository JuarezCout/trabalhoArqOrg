package trabalhoArqOrg;

import java.util.Scanner;

public class Barramento {
	
	double tamMemoria, clock, largBarr;
	
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

	//metodo que leve um Pacote de dados entre uma classe e outra
	//barramento(REMETENTE, DESTINATARIO, PACOTE)
	public void barramentoDados(String dest, String remet, int[] pacote){
		
		if(dest == "RAM"){
			
		} else if (dest == "CPU"){
			
		}
	}
	
	public void barramentoEndereco(String dest, String remet, int[] pacote){
		if(dest == "E/A"){
			
		} else if (dest == "CPU"){
			
		} else if (dest == "RAM"){
			
		} 
	}
	
	public void barramentoControle(String dest, String remet, int[] pacote){
		if(dest == "RAM"){
			
		} else if (dest == "CPU"){
			
		}
	}
	
	
}
