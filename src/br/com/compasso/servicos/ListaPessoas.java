package br.com.compasso.servicos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ListaPessoas implements LerArquivo {
	private ErrorMessages erro = new ErrorMessages();
	@Override
	public void leArquivo(String file) {
		
			try{
				if (new File(file).exists()) {
					BufferedReader bw = new BufferedReader(new FileReader(file));
					String line;
					line = bw.readLine();
					while (line != null) {
						printaPessoas(line);
						line = bw.readLine();
					}
					bw.close();
				}else {
					this.erro.errorMessage("não ha pessoas na lista");
				}
				
			} catch (Exception e) {
				this.erro.errorMessage("problemas na leitura da lista");
			}
		
		
	}
	private void printaPessoas(String line) {
		String[] dados;
		String  formatado;
		
		dados = line.split(";");	
		formatado = String.format("Cód:%s \nNome: %s \nEndereço:%s \nIdade: %s", dados[0], dados[1],
				dados[2], dados[3]);
		System.out.println("\n++++++++++++++++++\n" + formatado + "");
	}


	
}
