package trabalhoArqOrg;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*; 

public class EntradaSaida {
	
	public ArrayList<String> lista = new ArrayList<>();
	public int[][] codigo = new int [lista.size()][4];
	public String linha;
	
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
		
		int tamanho = lista.size();
		for(int cont = 0; cont < tamanho; cont++){
			
			linha = lista.get(cont);
			System.out.println(linha);
			linha = linha.toUpperCase();
			
			//Patterns
			Pattern add = Pattern.compile("^ADD\\s+(\\w)\\s*,\\s*(\\w)\\s*");
			
			Pattern mov = Pattern.compile("^MOV\\s+(\\w)\\s*,\\s*(\\w)\\s*");
			
			Pattern imul = Pattern.compile("^IMUL\\s+(\\w)\\s*,\\s*(\\w)\\s*");
			
			Pattern inc = Pattern.compile("^INC\\s+(\\w)\\s*");
			
			//Matchers
			Matcher madd = add.matcher(linha);
			
			Matcher mmov = mov.matcher(linha);
			
			Matcher mimul = imul.matcher(linha);
			
			Matcher minc = inc.matcher(linha);
			
			//Convertendo os comandos para um vetor de int
			if(madd.find()){
				//ADD
				codigo[cont][0] = 1; //Define o 1 de acordo com o dicion�rio
				
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
				
				if ((madd.group(1)).matches("0X00+(\\w)")){
					
				}
				
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
				
				if ((madd.group(2)).matches("0X00+(\\d\\d)")){
					
				}
				
				if ((madd.group(2)).matches("(\\d)")){
					codigo[cont][2] = Integer.parseInt(madd.group(2));
				}
				
				codigo[cont][3] = -1;
				
			} else if (mmov.find()){
				
				codigo[cont][0] = 1;
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
				
				if ((mmov.group(1)).matches("0X00+(\\d\\d)")){
					
				}
				
				if ((mmov.group(1)).matches("([A, B, C, D])")){
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
				
				if ((mmov.group(1)).matches("0X00+(\\d\\d)")){
					
				}
				
				if ((mmov.group(1)).matches("(\\d\\d)")){
					codigo[cont][2] = Integer.parseInt(madd.group(2));
				}
				
				codigo[cont][3] = -1;
				
			} else if (mimul.find()){
				
				codigo[cont][0] = 1;
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
				
				if ((mimul.group(1)).matches("0X00+(\\d\\d)")){
					
				}
				
				if ((mimul.group(1)).matches("([A, B, C, D])")){
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
				
				if ((mimul.group(1)).matches("0X00+(\\d\\d)")){
					
				}
				
				if ((mimul.group(1)).matches("(\\d\\d)")){
					codigo[cont][2] = Integer.parseInt(madd.group(2));
				}
				
				codigo[cont][3] = -1;
				
			} else if (minc.find()){
				
				codigo[cont][0] = 1;
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
				
				if ((madd.group(1)).matches("0X00+(\\d\\d)")){
					
				}
				
				codigo[cont][2] = -1;
				
				codigo[cont][3] = -1;
			}
		    else {
		    	System.out.println("Erro na sintaxe do c�digo na linha: " + (cont + 1) + ".\n" + linha);
		    	return;
		    }
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
	
	/*public int buffer(int linhaAtual)
	{
		if (linhaAtual == (lista.size() - 1)){
			return -1;
		}
		
		return codigo[linhaAtual];
	}
	*/
	
}	
	
	
