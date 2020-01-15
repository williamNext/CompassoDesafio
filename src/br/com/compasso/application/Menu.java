package br.com.compasso.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.com.compasso.model.Pessoa;
import br.com.compasso.servicos.CadastraPessoa;
import br.com.compasso.servicos.ListaPessoas;

public class Menu {

	private boolean flag;
	private Scanner scan;
	private int key;
	private CadastraPessoa cadastraPes;
	private ListaPessoas listpes;
	private String filename;

	public Menu() {
		this.scan = new Scanner(System.in);
		this.flag = true;
		this.cadastraPes = new CadastraPessoa();
		this.listpes = new ListaPessoas();
		this.filename = "database.txt";
	}

	public void menuDeOpcoes() throws IOException {

		printaOpcoesMenu();

		while (flag) {

			try {
				// Parse de String para int para evita os problemas gerados com o uso de nextInt
				// e nextLine juntos;
				this.key = Integer.parseInt(this.scan.nextLine());

			} catch (Exception e) {
				this.key = -1;//pra garantir que n vai ter problema no switch pq o default do int =0
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
				errorMessage("opção inválida!");
				break;

			}
			printaOpcoesMenu();
		}
	}

	public void printaOpcoesMenu() {
		System.out.println("\n## Escolha uma das opções abaixo ## \n" + "opção 1 - Cadastra pessoa\n"
				+ "opção 2 - Imprime pessoas cadastradas\n" + "opção 0 - sair do programa");

		System.out.println("---------------------------------\n");
		System.out.print("Digite aqui sua opção: ");
	}

	
	public void coletaFormulario() {
		int cod, age;
		String name, address;

		System.out.print("digite o código: ");
		try {
			cod = Integer.parseInt(this.scan.nextLine());

			System.out.print("\nDigite Nome: ");
			name = this.scan.nextLine();

			System.out.print("\nDigite endereco: ");
			address = this.scan.nextLine();

			System.out.print("\nDigite idade: ");
			age = Integer.parseInt(this.scan.nextLine());

			// registra apenas com códigos únicos, checa endereço e nome vazios
			if (checaValidadeCodigo(String.valueOf(cod)) && !name.isEmpty() && !address.isEmpty() ) {
				Pessoa p = new Pessoa(cod, name, address, age);
				this.cadastraPes.registra(p,this.filename);
			}
			else if(name.isEmpty()){
				errorMessage("Campo nome é obrigatório!!");
			}
			else if(address.isEmpty()){
				errorMessage("Campo endereço é obrigatório!!!");
			}

		} catch (Exception e) {
			errorMessage("Campo Código e Idade devem ser números Inteiros!!!");
		}

	}

//	private void printaPessoasCadastradas() {
//
//		try (BufferedReader bw = new BufferedReader(new FileReader("database.txt"))) {
//
//			String [] dados;
//			String line,formatado;
//			
//			line = bw.readLine();
//			while (line != null) {
//				dados = line.split(";");
////				
//				formatado = String.format("Cód:%s \nNome: %s \nEndereço:%s \nIdade: %s",dados[0],dados[1],dados[2],dados[3]);
//				System.out.println("\n++++++++++++++++++\n"+formatado+"");
//				line = bw.readLine();
//			}
//			bw.close();
//		} catch (Exception e) {
//			errorMessage(e.getMessage());
//		}
//	}


	// para cadastro com códigos únicos
	private boolean checaValidadeCodigo(String codPessoa) {

		try (BufferedReader bw = new BufferedReader(new FileReader("database.txt"))) {

			String line;
			String[] campos;

			line = bw.readLine();
			while (line != null) {
				campos = line.split(";");
				if (campos[0].equals(codPessoa)) {
					errorMessage("Código de pessoa já existe!!!");
					bw.close();
					return false;
				}
				line = bw.readLine();
			}
			bw.close();

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

		return true;
	}
	
	
	public void errorMessage(String message) {
		System.out.println("\n--------------------------------------------------");
		System.out.println("Erro : "+message);
		System.out.println("--------------------------------------------------");
	}

}
