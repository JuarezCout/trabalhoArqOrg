package trabalhoArqOrg;

public class MemoriaRam {
	
	public int[] memoria = { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
							 -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1 };
	public boolean gravouMemoria = false;
	public int posMemoria = 0;
	
	public boolean posEspMemoria(){
		for(int cont = 0; cont <= 12; cont+=4){
			if (memoria[cont] == -1){
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
	
	public void addMemoria(int [] comando){
		
		memoria[posMemoria]     = comando[0];
		memoria[posMemoria + 1] = comando[1];
		memoria[posMemoria + 2] = comando[2];
		memoria[posMemoria + 3] = comando[3];
		
		avanPosMemoria();
		gravouMemoria = true;
	}
	
	public void recebeComando(int [] comando){
		
		if(!posEspMemoria()){
			esvaziaMemoria();
		}
		
		addMemoria(comando);
	}
}
