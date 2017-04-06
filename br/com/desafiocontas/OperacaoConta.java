package br.com.desafiocontas;

/**
 * Classe criada para executar operacoes com as contas criadas na classe Conta2
 * Possui os metodos: sacar, depositar e transferir
 * @author Marcos Ribeiro
 */

public class OperacaoConta {
	public boolean verificaSaldo(Conta2 conta, double valor) {
		if(valor > conta.getSaldo()) {
			return false;
		} else {
			return true;
		}
	}
	
	public void sacar(Conta2 contaSaque, double valorSaque) {
		if(verificaSaldo(contaSaque, valorSaque)) {
			contaSaque.setSaldo(contaSaque.getSaldo() - valorSaque);
		}
	}
	
	public void depositar(Conta2 conta, double valorDeposito) {
		conta.setSaldo((conta.getSaldo() + valorDeposito));
	}
	
	public void transferir(Conta2 contaOrigem, double valorTransferido, Conta2 contaDestino) {
		if(verificaSaldo(contaOrigem, valorTransferido)) {
			sacar(contaOrigem, valorTransferido);
			depositar(contaDestino, valorTransferido);
		}
	}
	
}//fim classe

