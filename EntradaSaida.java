package trabalhoArqOrg;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class EntradaSaida {
	
	public ArrayList<String> lista = new ArrayList<>();
	public String linha;//linha para transformar em vetor
	public static int[][] codigo = new int [100][4];//matriz com o codigo transformaod em vetores de int
	
	//inicia o fluxo entre E/S e RAM
	
	public void rodaEntradaSaida() throws IOException{
		//Irá rodar a classe e seus metodos enquanto a memoria tiver espaço e os barramentos puderem ser usados
		lerArquivo();
		for (int i = 0; i < codigo.length; i++) {
			if(Barramento.barrCont == false){
				
			}
		}
	}
	
	public void analisaSintaxe (ArrayList<String> lista)
	{	
		//int[][] codTemporario = new int [lista.size()][4];
		
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
		
		int tamanho = lista.size() - 1;
		for(int cont = 0; cont < tamanho; cont++){
			
			linha = lista.get(cont);
			linha = linha.toUpperCase();
			
			//Patterns
			Pattern add = Pattern.compile("^ADD\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern mov = Pattern.compile("^MOV\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern imul = Pattern.compile("^IMUL\\s+(\\w+)\\s*,\\s*(\\w+)\\s*");
			
			Pattern inc = Pattern.compile("^INC\\s+(\\w+)\\s*");
			
			Pattern label = Pattern.compile("^LABEL\\s+(\\d)");
			
			Pattern loop = Pattern.compile("(\\w)\\s*<\\s*(\\w)\\s*?\\s*JUMP\\s*(\\d)\\s*:\\s*(\\d)")
			
			//Matchers
			Matcher madd  = add.matcher(linha);
			
			Matcher mmov  = mov.matcher(linha);
			
			Matcher mimul = imul.matcher(linha);
			
			Matcher minc  = inc.matcher(linha);
			
			Matcher mlabel = label.matcher(linha);
			
			Matcher mloop = loop.matcher(linha);
			
			//Convertendo os comandos para um vetor de int
			if(madd.find()){
				//ADD
				codigo[cont][0] = 1; //Define o 1 de acordo com o dicionário
				
				//Valida se o primeiro valor do comando é um Registrador ou um Endereço
				if ((madd.group(1)).matches("([A, B, C, D])")){
					switch (madd.group(1)){
					case "A":
						codigo[cont][1] = -2;
						break;
					case "B":
						codigo[cont][1] = -3;
						break;
					case "C":
						codigo[cont][1] = -4;
						break;
					case "D":
						codigo[cont][1] = -5;
						break;
						
					}
				}
				
				if ((madd.group(1)).matches("0X0+(\\w)")){
					String endHexa = (madd.group(1)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linha);
						for (int i = 0; i < codigo.length; i++) {
				    		for (int j = 0; j < codigo[i].length; j++) {
								codigo[i][j] = 0;
							}
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[cont][1] = end;
				}
				
				//Valida se o Segundo valor do comando é um Registrador, Endereço ou uma Constante
				if ((madd.group(2)).matches("([A, B, C, D])")){
					switch (madd.group(1)){
					case "A":
						codigo[cont][2] = -2;
						break;
					case "B":
						codigo[cont][2] = -3;
						break;
					case "C":
						codigo[cont][2] = -4;
						break;
					case "D":
						codigo[cont][2] = -5;
						break;
						
					}
				}
				
				if ((madd.group(2)).matches("0X0+(\\w)")){
					String endHexa = (madd.group(2)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linha);
						for (int i = 0; i < codigo.length; i++) {
				    		for (int j = 0; j < codigo[i].length; j++) {
								codigo[i][j] = 0;
							}
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[cont][2] = end;
				}
				
				if ((madd.group(2)).matches("(\\d)")){
					codigo[cont][2] = Integer.parseInt(madd.group(2));
				}
				
				codigo[cont][3] = -1;
				
			} else if (mmov.find()){
				//MOV
				codigo[cont][0] = 2;
				
				//Valida se o primeiro valor do comando é um Registrador ou um Endereço
				if ((mmov.group(1)).matches("([A, B, C, D])")){
					switch (mmov.group(1)){
					case "A":
						codigo[cont][1] = -2;
						break;
					case "B":
						codigo[cont][1] = -3;
						break;
					case "C":
						codigo[cont][1] = -4;
						break;
					case "D":
						codigo[cont][1] = -5;
						break;
						
					}
				}
				
				if ((mmov.group(1)).matches("0X0+(\\w)")){
					String endHexa = (mmov.group(1)).replaceAll("0X0+", "");
					int end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linha);
						for (int i = 0; i < codigo.length; i++) {
				    		for (int j = 0; j < codigo[i].length; j++) {
								codigo[i][j] = 0;
							}
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[cont][1] = end;
				}
				
				//Valida se o Segundo valor do comando é um Registrador, Endereço ou uma Constante
				if ((mmov.group(2)).matches("([A, B, C, D])")){
					switch (mmov.group(1)){
					case "A":
						codigo[cont][2] = -2;
						break;
					case "B":
						codigo[cont][2] = -3;
						break;
					case "C":
						codigo[cont][2] = -4;
						break;
					case "D":
						codigo[cont][2] = -5;
						break;
						
					}
				}
				
				if ((mmov.group(2)).matches("0X0+(\\w)")){
					String endHexa = (mmov.group(2)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linha);
						for (int i = 0; i < codigo.length; i++) {
				    		for (int j = 0; j < codigo[i].length; j++) {
								codigo[i][j] = 0;
							}
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[cont][2] = end;
				}
				if ((mmov.group(2)).matches("(\\d)")){
					codigo[cont][2] = Integer.parseInt(mmov.group(2));
				}
				
				codigo[cont][3] = -1;           
				
			} else if (mimul.find()){
				//IMUL
				codigo[cont][0] = 3;
				
				//Valida se o primeiro valor do comando é um Registrador ou um Endereço
				if ((mimul.group(1)).matches("([A, B, C, D])")){
					switch (mimul.group(1)){
					case "A":
						codigo[cont][1] = -2;
						break;
					case "B":
						codigo[cont][1] = -3;
						break;
					case "C":
						codigo[cont][1] = -4;
						break;
					case "D":
						codigo[cont][1] = -5;
						break;
						
					}
				}
				
				if ((mimul.group(1)).matches("0X0+(\\w)")){
					String endHexa = (mimul.group(1)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linha);
						for (int i = 0; i < codigo.length; i++) {
				    		for (int j = 0; j < codigo[i].length; j++) {
								codigo[i][j] = 0;
							}
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[cont][1] = end;
				}
				
				//Valida se o Segundo valor do comando é um Registrador, Endereço ou uma Constante
				if ((mimul.group(2)).matches("([A, B, C, D])")){
					switch (mimul.group(1)){
					case "A":
						codigo[cont][2] = -2;
						break;
					case "B":
						codigo[cont][2] = -3;
						break;
					case "C":
						codigo[cont][2] = -4;
						break;
					case "D":
						codigo[cont][2] = -5;
						break;
						
					}
				}
				
				if ((mimul.group(2)).matches("0X0+(\\w)")){
					String endHexa = (mimul.group(2)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linha);
						for (int i = 0; i < codigo.length; i++) {
				    		for (int j = 0; j < codigo[i].length; j++) {
								codigo[i][j] = 0;
							}
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[cont][2] = end;
				}
				
				if ((mimul.group(2)).matches("(\\d)")){
					codigo[cont][2] = Integer.parseInt(mimul.group(2));
				}
				
				codigo[cont][3] = -1;
				
			} else if (minc.find()){
				//INC
				codigo[cont][0] = 4;
				
				//Valida se o primeiro valor do comando é um Registrador ou um Endereço
				if ((minc.group(1)).matches("([A, B, C, D])")){
					switch (madd.group(1)){
					case "A":
						codigo[cont][1] = -2;
						break;
					case "B":
						codigo[cont][1] = -3;
						break;
					case "C":
						codigo[cont][1] = -4;
						break;
					case "D":
						codigo[cont][1] = -5;
						break;
						
					}
				}
				
				if ((minc.group(1)).matches("0X0+(\\w)")){
					String endHexa = (minc.group(1)).replaceAll("0X0+", "");
					Integer end = Integer.parseInt(endHexa, 16);
					if(end > 16){
						System.out.println("\nErro no endereço escrito da linha: " + linha);
						for (int i = 0; i < codigo.length; i++) {
				    		for (int j = 0; j < codigo[i].length; j++) {
								codigo[i][j] = 0;
							}
						}
				    }
					end = (end + 16 + 5)*(-1);
					codigo[cont][1] = end;
				}
				
				codigo[cont][2] = -1;
				
				codigo[cont][3] = -1;
				
			} else if(mlabel.find){
				
				
			} else if(mloop.find){
				
				
			} else {
		    	System.out.println("\n\nErro na sintaxe do código da linha: " + (cont + 1) + " (" + linha + ").");
		    	
		    	for (int i = 0; i < codigo.length; i++) {
		    		for (int j = 0; j < codigo[i].length; j++) {
						codigo[i][j] = 0;
					}
				}
		    	
		    	break;
		    }
			
			for (int i = 0; i < 4; i++) {
				System.out.print(codigo[cont][i] + " ");
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
	
	public void buffer(int linha)
	{
		if (){
			;
		}
	}
	
}	
	
	
