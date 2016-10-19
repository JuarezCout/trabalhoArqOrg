package trabalhoArqOrg;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			EntradaSaida ea = new EntradaSaida();
			Cpu cpu         = new Cpu();
			MemoriaRam mRam = new MemoriaRam();
			Gerenciador ger = new Gerenciador();
			
			
			
			ea.rodaEntradaSaida();
	}

}
