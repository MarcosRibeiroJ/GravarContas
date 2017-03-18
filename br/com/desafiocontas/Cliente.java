package br.com.desafiocontas;

/**
 * Classe que cria clientes, a principio soh possui informacao do nome do cliente
 * @author Marcos Ribeiro
 */

public class Cliente {
private String nome;
	
	Cliente(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
