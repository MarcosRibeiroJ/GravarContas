package br.com.desafiocontas;

/**
 * Classe que cria contas, um objeto dessa classe eh composto de um valor em double para o saldo
 * e de um objeto cliente, que utilizaremos o atributo nome
 * @author Marcos Ribeiro
 */

public class Conta2 {
	private double saldo;
	private Cliente cliente;
	private String codConta;
	
	Conta2(String codConta, Cliente pessoa, double saldo) {
		this.saldo = saldo;
		this.cliente = pessoa;
		this.codConta = codConta;
	}
	
	public String getCodConta() {
		return codConta;
	}
	
	public void setCodConta(String codConta) {
		this.codConta = codConta;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

}//fim classe
