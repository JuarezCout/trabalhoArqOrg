package trabalhoArqOrg;

public class Cpu {
	int regA = -1, regB = -1, regC = -1, regD = -1, regCI = -1;
	boolean procComando = false;
	
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
	
	public void procMov(int [] comando){}
	
	public void procAdd(int [] comando){}
	
	public void procImul(int [] comando){}
	
	public void procInc(int [] comando){}
	
	public void procComando(int [] comando){
		switch (comando[0]){
			case 1:
				procMov(comando);
				break;
			case 2:	
				procAdd(comando);
				break;
			case 3:
				procImul(comando);
				break;
			case 4:
				procInc(comando);
				break;
		}
	}
	
	public void 
	
}
