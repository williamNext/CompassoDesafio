package br.com.compasso.servicos;

import br.com.compasso.model.Pessoa;

public interface EscreveArquivo {
	
 void registra(Pessoa p, String filename);
}
