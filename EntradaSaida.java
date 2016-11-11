package trabalhoArqOrg;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntradaSaida implements Runnable {
	
	private ArrayList<String> listaArquivo = new ArrayList<>();
	private String linhaCodigoInt;//linha para transformar em vetor
	private int lin, contadorDeInstrucoes = 0;
	private ArrayList<Integer[]> listaComCodigoInt = new ArrayList<Integer[]>();
	private ArrayList<Integer[]> filaDados = new ArrayList<Integer[]>();
	private ArrayList<Integer[]> dadosPorLoop = new ArrayList<Integer[]>();
	private int[] filaEndereco = new int[2];
	public Integer[] codigo = new Integer[4]; //vetor com o codigo transformado em int
	public boolean barramentoContLivre = true, barramentoDadLivre = false;
			
	//inicia o fluxo entre E/S e RAM
        
    public EntradaSaida () throws IOException{
        lerArquivo();
    }
	
     @Override
    public void run() {
    	
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EntradaSaida.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(barramentoContLivre){
            	Gerenciador.barr.barramentoControle("RAM", "E/A", 0); 
            	System.out.println("ES: Mandei controle para RAM");
            	this.barramentoContLivre = false;
            	
            }
            // Olhar barramento, na fila de endereços
            if(barramentoDadLivre){
				if (Gerenciador.barr.getFilaEnd() != null) {
					System.out.println("ES: Recebi o endereço da RAM");
					 this.filaEndereco = Gerenciador.barr.getFilaEnd();
					 Gerenciador.barr.setNullFilaEnd();
				} else {
					this.filaEndereco = null;
				}
	          
	            
	            if (this.filaEndereco != null && this.filaEndereco[1] == 1){
	            	for (int i = contadorDeInstrucoes; i < (Gerenciador.barr.getLarguraBanda())/4; i++) {
						this.filaDados.add(i, buffer(contadorDeInstrucoes)); //pega os dados
					}
					Integer[] enderecoParaSalvarDado = {filaEndereco[0]};
					Gerenciador.barr.barramentoDados("RAM", filaDados, enderecoParaSalvarDado ); // manda para a Ram
					this.filaEndereco = null;
					this.filaDados = null;
					System.out.println("ES: Mandei dados para RAM");
					
					this.contadorDeInstrucoes = contadorDeInstrucoes + (Gerenciador.barr.getLarguraBanda())/4; // define de onde vai pegar na lista com o codigo Int
					this.barramentoContLivre = true; // libera o barramento de Controle
					this.barramentoDadLivre = false; // Ocupa o barramento de dados
	            	
	            }
        	}
            
            if (contadorDeInstrucoes > listaComCodigoInt.get(0).length) { 
            	System.out.println("Todas as instruções foram salvas! Thread ES finalizada!");
            	Thread.interrupted();
            	break;
			}
            
        }
    }
    
	public void analisaSintaxe (ArrayList<String> lista)
	{	
		
		/**
		 * DICIONARIO
		 * 1 = ADD
		 * 2 = MOV
		 * 3 = IMUL
		 * 4 = INC
		 */
		 
		 /**
		 * -1 = NULL
		 * -2 = A
		 * -3 = B
		 * -4 = C
		 * -5 = D
		*/
		
		lin = listaArquivo.size() - 1;
		for(int cont = 0; cont < lin; cont++){
			
			linhaCodigoInt = listaArquivo.get(cont);
			linhaCodigoInt = linhaCodigoInt.toUpperCase();
			
			//Patterns
			Pattern add = Pattern.compile("^ADD\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern mov = Pattern.compile("^MOV\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern imul = Pattern.compile("^IMUL\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern inc = Pattern.compile("^INC\\s+(\\w+)\\s*");
			
			//Matchers
			Matcher madd  = add.matcher(linhaCodigoInt);
			
			Matcher mmov  = mov.matcher(linhaCodigoInt);
			
			Matcher mimul = imul.matcher(linhaCodigoInt);
			
			Matcher minc  = inc.matcher(linhaCodigoInt);
			
			
			//Convertendo os comandos para um vetor de int
			if(madd.find()){
				//ADD
				codigo[0] = 1; //Define o 1 de acordo com o dicionário
				
				//Valida se o primeiro valor do comando é um Registrador ou um Endereço
				if ((madd.group(1)).matches("([A, B, C, D])")){
					switch (madd.group(1)){
					case "A":
						codigo[1] = -2;
						break;
					case "B":
						codigo[1] = -3;
						break;
					case "C":
						codigo[1] = -4;
						break;
					case "D":
						codigo[1] = -5;
						break;
						
					}
				}
				
				if ((madd.group(1)).matches("0X0+(\\w)")){
					String endHexa = (madd.group(1)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linhaCodigoInt);
						for (int i = 0; i < codigo.length; i++) {
								codigo[i] = 0;
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[1] = end;
				}
				
				//Valida se o Segundo valor do comando é um Registrador, Endereço ou uma Constante
				if ((madd.group(2)).matches("([A, B, C, D])")){
					switch (madd.group(1)){
					case "A":
						codigo[2] = -2;
						break;
					case "B":
						codigo[2] = -3;
						break;
					case "C":
						codigo[2] = -4;
						break;
					case "D":
						codigo[2] = -5;
						break;
						
					}
				}
				
				if ((madd.group(2)).matches("0X0+(\\w)")){
					String endHexa = (madd.group(2)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linhaCodigoInt);
						for (int i = 0; i < codigo.length; i++) {
							codigo[i] = 0;
					}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[2] = end;
				}
				
				if ((madd.group(2)).matches("(\\d)")){
					codigo[2] = Integer.parseInt(madd.group(2));
				}
				
				codigo[3] = -1;
			
				listaComCodigoInt.add(cont, codigo);
				
			} else if (mmov.find()){
				//MOV
				codigo[0] = 2;
				
				//Valida se o primeiro valor do comando é um Registrador ou um Endereço
				if ((mmov.group(1)).matches("([A, B, C, D])")){
					switch (mmov.group(1)){
					case "A":
						codigo[1] = -2;
						break;
					case "B":
						codigo[1] = -3;
						break;
					case "C":
						codigo[1] = -4;
						break;
					case "D":
						codigo[1] = -5;
						break;
						
					}
				}
				
				if ((mmov.group(1)).matches("0X0+(\\w)")){
					String endHexa = (mmov.group(1)).replaceAll("0X0+", "");
					int end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linhaCodigoInt);
						for (int i = 0; i < codigo.length; i++) {
							codigo[i] = 0;
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[1] = end;
				}
				
				//Valida se o Segundo valor do comando é um Registrador, Endereço ou uma Constante
				if ((mmov.group(2)).matches("([A, B, C, D])")){
					switch (mmov.group(1)){
					case "A":
						codigo[2] = -2;
						break;
					case "B":
						codigo[2] = -3;
						break;
					case "C":
						codigo[2] = -4;
						break;
					case "D":
						codigo[2] = -5;
						break;
						
					}
				}
				
				if ((mmov.group(2)).matches("0X0+(\\w)")){
					String endHexa = (mmov.group(2)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linhaCodigoInt);
						for (int i = 0; i < codigo.length; i++) {
							codigo[i] = 0;
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[2] = end;
				}
				if ((mmov.group(2)).matches("(\\d)")){
					codigo[2] = Integer.parseInt(mmov.group(2));
				}
				
				codigo[3] = -1; 
				
				listaComCodigoInt.add(cont, codigo);
				
			} else if (mimul.find()){
				//IMUL
				codigo[0] = 3;
				
				//Valida se o primeiro valor do comando é um Registrador ou um Endereço
				if ((mimul.group(1)).matches("([A, B, C, D])")){
					switch (mimul.group(1)){
					case "A":
						codigo[1] = -2;
						break;
					case "B":
						codigo[1] = -3;
						break;
					case "C":
						codigo[1] = -4;
						break;
					case "D":
						codigo[1] = -5;
						break;
						
					}
				}
				
				if ((mimul.group(1)).matches("0X0+(\\w)")){
					String endHexa = (mimul.group(1)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linhaCodigoInt);
						for (int i = 0; i < codigo.length; i++) {
							codigo[i] = 0;
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[1] = end;
				}
				
				//Valida se o Segundo valor do comando é um Registrador, Endereço ou uma Constante
				if ((mimul.group(2)).matches("([A, B, C, D])")){
					switch (mimul.group(1)){
					case "A":
						codigo[2] = -2;
						break;
					case "B":
						codigo[2] = -3;
						break;
					case "C":
						codigo[2] = -4;
						break;
					case "D":
						codigo[2] = -5;
						break;
						
					}
				}
				
				if ((mimul.group(2)).matches("0X0+(\\w)")){
					String endHexa = (mimul.group(2)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linhaCodigoInt);
						for (int i = 0; i < codigo.length; i++) {
							codigo[i] = 0;
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[2] = end;
				}
				
				if ((mimul.group(2)).matches("(\\d)")){
					codigo[2] = Integer.parseInt(mimul.group(2));
				}
				
				codigo[3] = -1;
				
				listaComCodigoInt.add(cont, codigo);
				
			} else if (minc.find()){
				//INC
				codigo[0] = 4;
				
				//Valida se o primeiro valor do comando é um Registrador ou um Endereço
				if ((minc.group(1)).matches("([A, B, C, D])")){
					switch (madd.group(1)){
					case "A":
						codigo[1] = -2;
						break;
					case "B":
						codigo[1] = -3;
						break;
					case "C":
						codigo[1] = -4;
						break;
					case "D":
						codigo[1] = -5;
						break;
						
					}
				}
				
				if ((minc.group(1)).matches("0X0+(\\w)")){
					String endHexa = (minc.group(1)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linhaCodigoInt);
						for (int i = 0; i < codigo.length; i++) {
							codigo[i] = 0;
					}	
				    }
					end = (end + 16 + 5)*(-1);
					codigo[1] = end;
				}
				
				codigo[2] = -1;
				
				codigo[3] = -1;
				
				listaComCodigoInt.add(cont, codigo);
				
			} else {
		    	System.out.println("\n\nErro na sintaxe do código da linha: " + (cont + 1) + " (" + linhaCodigoInt + ").");
		    	
		    	for (int i = 0; i < codigo.length; i++) {
					codigo[i] = 0;
			}	
		    	
		    	break;
		    }
			for (int i = 0; i < codigo.length; i++) {
				System.out.print(listaComCodigoInt.get(cont)[i] + " ");
			}
			System.out.println();
			
		}
	}
	
	public void lerArquivo() throws IOException
	{
		FileReader arq = new FileReader("C:\\Users\\Cliente\\Documents\\cod.txt");
	    //armazenando conteudo no arquivo no buffer
	    BufferedReader lerArq = new BufferedReader(arq);
	    //lendo a primeira linha
	    String linha = new String(); 
	    
		while (linha != null) {
			linha = lerArq.readLine();
		    listaArquivo.add(linha);
		    if (linha == null){
		    	break;
		    }
		}
		lerArq.close();  
		analisaSintaxe(listaArquivo);
		
	}
	
	public Integer[] buffer (int pos){
		if (pos > listaComCodigoInt.get(0).length){
			return null;
		}
		/*for (int i = pos; i < (Gerenciador.barr.getLarguraBanda())/4; i++) {
			if((Gerenciador.barr.getLarguraBanda())/4 > listaComCodigoInt.get(0).length - pos){break;}
			dadosPorLoop.add((i - pos), listaComCodigoInt.get(i));
		}*/
		
		return listaComCodigoInt.get(pos);
	}   

	/**
	 * @param barramentoDadLivre the barramentoDadLivre to set
	 */
	public void setBarramentoDadLivre(boolean barramentoDadLivre) {
		this.barramentoDadLivre = barramentoDadLivre;
	}
}	
	
	
