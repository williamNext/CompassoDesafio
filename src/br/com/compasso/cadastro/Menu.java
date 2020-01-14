package br.com.compasso.cadastro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

	private boolean flag;
	private Scanner scan;
	private int key;

	
	public Menu() {
		this.scan = new Scanner(System.in);
		this.flag = true;
		
	}

	
	public void menuDeOpcoes() throws IOException {

		printaOpcoesMenu();

		while (flag) {
			
			
			try {
			//Parse de String para int para evita os problemas gerados com o uso de next int e nextLine juntos;
			   this.key = Integer.parseInt(this.scan.nextLine());
			   
			} catch (Exception e) {
				this.key = -1;
				
			}
			
		
			switch (key) {
			case 1:
				realizaCadastro();
				
				break;
			case 2:
				printaPessoasCadastradas();
				break;
			case 0:
				System.out.println("fim do programa");
				System.exit(0);
			default:
				System.out.println("opção inválida!");
				break;

			}
			printaOpcoesMenu();
		}
	}
	
	
	
	
	private void printaPessoasCadastradas(){
					
		try(BufferedReader bw = new BufferedReader(new FileReader("database.txt"))){
			
			int pes =1;
			String line;
			
			line = bw.readLine();
			while (line!= null) {
				System.out.println("-----------------------------\n Pessoa "+ pes);
				System.out.println("\n"+(String.join("\n", line.split(";"))+""));
				pes++;
				line = bw.readLine();
			}
			bw.close();
		}catch (Exception e) {
			System.out.println("Erro: "+ e.getMessage());
		}
	}


	public void printaOpcoesMenu() {
		System.out.println("\n## Escolha uma das opções abaixo ## \n" 
				+ "opção 1 - Cadastra pessoa\n"
				+ "opção 2 - Imprime pessoas cadastradas\n" 
				+ "opção 0 - sair do programa");

		System.out.println("---------------------------------\n");
		System.out.print("Digite aqui sua opção: ");
	}
	
	
	public void realizaCadastro() {
		int cod, age;
		String name, address;
		
			System.out.print("digite o código: ");
		try {
			cod =Integer.parseInt( this.scan.nextLine());
			
			
			System.out.print("\nDigite Nome: ");
			name = this.scan.nextLine();
			
			System.out.print("\nDigite endereco: ");	
			address = this.scan.nextLine();
			
			System.out.print("\nDigite idade: ");
			age = Integer.parseInt( this.scan.nextLine());
			
			
			Pessoa p = new Pessoa(cod,name,address,age);
			registraPessoa(p);
			
		} catch (Exception e) {
			System.out.println("Algum dado não corresponde com seu campo!");
		}
		
	}


	private void registraPessoa(Pessoa p) throws IOException {
		File file = new File("database.txt");
		
		BufferedWriter bw  = new BufferedWriter(new FileWriter(file, true));
		
		bw.write(p.toString());
		bw.newLine();
		bw.close();
			
	}
	
	
}
