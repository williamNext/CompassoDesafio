package br.com.compasso.application;

import java.io.IOException;
import java.util.Scanner;

import br.com.compasso.model.Pessoa;
import br.com.compasso.servicos.CadastraPessoa;
import br.com.compasso.servicos.ChecadorValidadeCodigo;
import br.com.compasso.servicos.ErrorMessages;
import br.com.compasso.servicos.ListaPessoas;

public class Menu {

	private boolean flag;
	private Scanner scan;
	private int key;
	private CadastraPessoa cadastraPes;
	private ListaPessoas listpes;
	private String filename;
	private ChecadorValidadeCodigo checador;
	private ErrorMessages erro;

	public Menu() {
		this.scan = new Scanner(System.in);
		this.flag = true;
		this.cadastraPes = new CadastraPessoa();
		this.listpes = new ListaPessoas();
		this.filename = "database.txt";
		this.checador = new ChecadorValidadeCodigo();
		this.erro = new ErrorMessages();
	}

	public void menuDeOpcoes() throws IOException {

		printaOpcoesMenu();

		while (flag) {

			try {
				this.key = Integer.parseInt(this.scan.nextLine());

			} catch (Exception e) {
				this.key = -1;
			}

			switch (key) {
			case 1:
				coletaFormulario();
				break;
			case 2:
				this.listpes.leArquivo(this.filename);
				break;
			case 0:
				System.out.println("fim do programa");
				System.exit(0);
			default:
				this.erro.errorMessage("op��o inv�lida!");
				break;

			}
			printaOpcoesMenu();
		}
	}

	public void printaOpcoesMenu() {
		System.out.println("\n## Escolha uma das op��es abaixo ## \n" + "op��o 1 - Cadastra pessoa\n"
				+ "op��o 2 - Imprime pessoas cadastradas\n" + "op��o 0 - sair do programa");

		System.out.println("---------------------------------\n");
		System.out.print("Digite aqui sua op��o: ");
	}

	public void coletaFormulario() {
		int cod, age;
		String name, address;

		System.out.print("digite o c�digo: ");
		try {
			cod = Integer.parseInt(this.scan.nextLine());

			System.out.print("\nDigite Nome: ");
			name = this.scan.nextLine();

			System.out.print("\nDigite endereco: ");
			address = this.scan.nextLine();

			System.out.print("\nDigite idade: ");
			age = Integer.parseInt(this.scan.nextLine());

			if (this.checador.checaCodigo(filename, String.valueOf(cod)) && !name.isEmpty() && !address.isEmpty()) {
				Pessoa p = new Pessoa(cod, name, address, age);
				this.cadastraPes.registra(p, this.filename);
			} else if (name.isEmpty()) {
				this.erro.errorMessage("Campo nome � obrigat�rio!!");
			} else if (address.isEmpty()) {
				this.erro.errorMessage("Campo endere�o � obrigat�rio!!!");
			}

		} catch (Exception e) {
			this.erro.errorMessage("Campo C�digo e Idade devem ser n�meros Inteiros!!!");
		}

	}

}
