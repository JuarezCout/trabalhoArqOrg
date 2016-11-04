package trabalhoArqOrg;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class EntradaSaida {
	
	private ArrayList<String> lista = new ArrayList<>();
	private String linha;//linha para transformar em vetor
	private int lin;
	private List<Integer[]> listaCod = new ArrayList<Integer[]>();
	public Integer[] codigo = new Integer[4]; //vetor com o codigo transformado em int
	int verificaEnderecoEA;
	boolean verificaBarramentoCont = false,verificaBarramentoDad = false, verificaTemEndereco = true;
			
	//inicia o fluxo entre E/S e RAM
	
	public void rodaEntradaSaida() throws IOException{
		//Irá rodar a classe e o metodo RODArAM e seus metodos enquanto a memoria tiver espaço e os barramentos puderem ser usados
		lerArquivo();
		int cont = 0;
		while (cont < codigo.length){
			/*while (verificaBarramentoCont){
				System.out.println("Não ta livre");                                              //implementar a thread em cima disso
				if(verificaBarramentoCont != Gerenciador.barr.isBarrContLiberado()){break;}  
			}*/
				System.out.println("rodei" + cont + "      " + Gerenciador.barr.isBarrContLiberado());
				Gerenciador.barr.barramentoControle("RAM", "E/A");
				cont++;
		}
		
		ArrayList<Integer[]> filaEndereco = new ArrayList<Integer[]>();
		
		while (filaEndereco != null){		
			System.out.println("Recebi os Endereços");
			Gerenciador.barr.setBarrEndLiberado (true);
			/*while(!Gerenciador.barr.isBarrDadLiberado()){
				if(verificaBarramentoDad != Gerenciador.barr.isBarrDadLiberado()){ break; }  //implementar a thread em cima disso
			}*/
				Gerenciador.barr.barramentoDados("RAM", listaCod);
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
		
		lin = lista.size() - 1;
		for(int cont = 0; cont < lin; cont++){
			
			linha = lista.get(cont);
			linha = linha.toUpperCase();
			
			//Patterns
			Pattern add = Pattern.compile("^ADD\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern mov = Pattern.compile("^MOV\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern imul = Pattern.compile("^IMUL\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern inc = Pattern.compile("^INC\\s+(\\w+)\\s*");
			
			//Matchers
			Matcher madd  = add.matcher(linha);
			
			Matcher mmov  = mov.matcher(linha);
			
			Matcher mimul = imul.matcher(linha);
			
			Matcher minc  = inc.matcher(linha);
			
			
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
						System.out.println("\nErro no endereço escrito da linha: " + linha);
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
						System.out.println("\nErro no endereço escrito da linha: " + linha);
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
			
				listaCod.add(cont, codigo);
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
						System.out.println("\nErro no endereço escrito da linha: " + linha);
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
						System.out.println("\nErro no endereço escrito da linha: " + linha);
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
				
				listaCod.add(cont, codigo);
				
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
						System.out.println("\nErro no endereço escrito da linha: " + linha);
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
						System.out.println("\nErro no endereço escrito da linha: " + linha);
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
				
				listaCod.add(cont, codigo);
				
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
						System.out.println("\nErro no endereço escrito da linha: " + linha);
						for (int i = 0; i < codigo.length; i++) {
							codigo[i] = 0;
					}	
				    }
					end = (end + 16 + 5)*(-1);
					codigo[1] = end;
				}
				
				codigo[2] = -1;
				
				codigo[3] = -1;
				
				listaCod.add(cont, codigo);
				
			} else {
		    	System.out.println("\n\nErro na sintaxe do código da linha: " + (cont + 1) + " (" + linha + ").");
		    	
		    	for (int i = 0; i < codigo.length; i++) {
					codigo[i] = 0;
			}	
		    	
		    	break;
		    }
			for (int i = 0; i < codigo.length; i++) {
				System.out.print(listaCod.get(cont)[i] + " ");
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
		    lista.add(linha);
		    if (linha == null){
		    	break;
		    }
		}
		lerArq.close();  
		analisaSintaxe(lista);
		
	}
}	
	
	
