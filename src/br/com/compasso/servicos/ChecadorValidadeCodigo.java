package br.com.compasso.servicos;

import java.io.BufferedReader;
import java.io.FileReader;

public class ChecadorValidadeCodigo {
	private ErrorMessages erro = new ErrorMessages();
	public boolean checaCodigo(String file, String codPessoa) {
		try (BufferedReader bw = new BufferedReader(new FileReader("database.txt"))) {

			String line;
			String[] campos;

			line = bw.readLine();
			while (line != null) {
				campos = line.split(";");
				if (campos[0].equals(codPessoa)) {
					bw.close();
					this.erro.errorMessage("Código da pessoa já existe");
					return false;
				}
				line = bw.readLine();
			}
			bw.close();

		} catch (Exception e) {
			
		}

		return true;
	}
}
