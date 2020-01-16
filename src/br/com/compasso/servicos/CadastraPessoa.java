package br.com.compasso.servicos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import br.com.compasso.model.Pessoa;

public class CadastraPessoa  implements EscreveArquivo{
	ErrorMessages erro = new ErrorMessages();
	@Override
	public void registra(Pessoa p, String filename) {
		try {
			BufferedWriter bw =new BufferedWriter(new FileWriter(filename, true));
			salvaPessoa(p, bw);
		} catch (Exception e) {
			this.erro.errorMessage("Algum erro ocorreu no cadastramento, tente novamente");
		}
	}
	
	private void salvaPessoa(Pessoa p, BufferedWriter bw) throws IOException {
		bw.write(p.toString());
		bw.newLine();
		bw.close();
	}

}
