package br.com.desafiocontas;

/**
 * Classe criada para testar a criacao de contas e as operacoes de saque, deposito e transferencias
 * Todas essas operacoes estao automatizadas na classe OperacaoSistema, sendo assim, basta criar um objeto dessa classe e chamar seus metodos
 * @author Marcos Ribeiro
 */

public class GeralContas {

	public static void main(String[] args) {
		
		OperacaoSistema op = new OperacaoSistema();
		op.cadastrar();
		op.sacarSistema();
		op.depositarSistema();
		op.transferirSistema();
		op.exibirConteudo();

	}

}
