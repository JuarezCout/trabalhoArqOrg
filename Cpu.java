package trabalhoArqOrg;

import java.io.IOException;
import java.util.Scanner;

public class Cpu extends MemoriaRam {
	int[] memoria;
	int regA = -1, regB = -1, regC = -1, regD = -1, regCI = -1;
	boolean procComando = false;
	
	public void rodaCpu (){
		
	}
	
	/**
	 * @return the regA
	 */
	public int getRegA() {
		return regA;
	}
	/**
	 * @param regA the regA to set
	 */
	public void setRegA(int regA) {
		this.regA = regA;
	}
	/**
	 * @return the regB
	 */
	public int getRegB() {
		return regB;
	}
	/**
	 * @param regB the regB to set
	 */
	public void setRegB(int regB) {
		this.regB = regB;
	}
	/**
	 * @return the regC
	 */
	public int getRegC() {
		return regC;
	}
	/**
	 * @param regC the regC to set
	 */
	public void setRegC(int regC) {
		this.regC = regC;
	}
	/**
	 * @return the regD
	 */
	public int getRegD() {
		return regD;
	}
	/**
	 * @param regD the regD to set
	 */
	public void setRegD(int regD) {
		this.regD = regD;
	}
	/**
	 * @return the procComando
	 */
	public boolean isProcComando() {
		return procComando;
	}
	/**
	 * @param procComando the procComando to set
	 */
	public void setProcComando(boolean procComando) {
		this.procComando = procComando;
	}
	
	public void defineCI(int cont){
		regCI = cont;
	}
	
	public int getMemoria(int posicao)
	{
		return memoria[posicao];
	}
	/**
	 * Define o valor de determinada posição da memória.
	 * 
	 * @param int $posicao Posição que será inserido o valor na memória.
	 * @param int $valor Valor que será colocado na posição da memória.
	 */
	public void setMemoria(int posicao, int valor)
	{
		memoria[posicao] = valor;
	}
	
	public void procMov(int [] comando){
		int val1 = comando [1];
		int val2 = comando [2];
		
		if(val2 < -1){
			switch (val2){
			case -2:
				val2= getRegA();
				break;
			case -3:
				val2 = getRegB();
				break;
			case -4:
				val2 = getRegC();
				break;
			case -5:
				val2 = getRegD();
				break;
			default:
				val2 = memoria[(val2 * -1) - 5];
				
		}	
		
		}
		
		if(val1 < -1){
			switch (val1){
			case -2:
				setRegA(val2);
				break;
			case -3:
				setRegB(val2);
				break;
			case -4:
				setRegC(val2);
				break;
			case -5:
				setRegD(val2);
				break;
			default:
				int pos = (val1 * -1) - 5;
				setMemoria(pos, val2);				
			}
		}	
		
	}
	
	public void procAdd(int [] comando){}
	
	public void procImul(int [] comando){}
	
	public void procInc(int [] comando){}
	
	public void procComando(int [][] comando, int linha){
		switch (comando[linha][0]){
			case 1:
				procMov(comando[linha]);
				break;
			case 2:	
				procAdd(comando[linha]);
				break;
			case 3:
				procImul(comando[linha]);
				break;
			case 4:
				procInc(comando[linha]);
				break;
		}
	}
	
	// Rodando o Emulador
	public static void main(String[] args) throws IOException {
		
		EntradaSaida i = new EntradaSaida();
		i.lerArquivo();
		
		
		
	}		
	
}
