package br.com.compasso.cadastro;

public class Pessoa {
	private int codigo;
	private String nome;
	private String endereco;
	private int idade;

	public Pessoa(int cod, String name, String addres, int age) {
		this.codigo = cod;
		this.nome = name;
		this.endereco = addres;
		this.idade = age;

	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public int getIdade() {
		return idade;
	}

	@Override
	public String toString() {

		return String.join(";", String.valueOf(this.getCodigo()), this.getNome(), this.getEndereco(),
				String.valueOf(this.getIdade()));

	}

}
