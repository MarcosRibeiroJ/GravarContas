package br.com.desafiocontas;

/**
 * Classe criada para automatizar a criacao de contas e as operacoes de saque, deposito e transferencia
 * @author Marcos Ribeiro
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OperacaoSistema {
	
	//A lista de contas sera usada por outros metodos, portando deve ser da classe
	ArrayList<Conta2> contas = new ArrayList<Conta2>();
	String path = "C:/Users/Markinho/cadastro2.txt", escolha;
	File arquivo;
	int indice = 0;

	//Metodo que cadastra conta com nome e saldo de usuario
	public void cadastrar() {
		
		//string para nome do cliente, op para parar o laco e path com caminho do txt criado
		String codConta, nome, op;
		
		//array que ira armazenar as contas
		//this.contas = new ArrayList<Conta2>();
				
		Scanner leitor = new Scanner(System.in);//scanner para ler dados
		do {
			System.out.println("Digite o codigo da conta: ");
			codConta = leitor.nextLine();
			System.out.println("Digite o nome do cliente: ");
			nome = leitor.nextLine();
			System.out.println("Digite o saldo do cliente: ");
			//criacao de objeto do tipo conta que recebe os dados do saldo e ja cria um objeto do tipo pessoa para armazenar o nome
			Conta2 conta = new Conta2(codConta, new Cliente(nome), leitor.nextDouble());
			this.contas.add(conta);//adicionando objeto conta no arraylist
			gravar();//chamada do metodo que grava dados no arquivo
			//controle para sair do laco ou continuar cadastrando contas
			System.out.println("Deseja cadastrar outra conta? ");
			op = leitor.nextLine();
			indice++;
		} while(!"nao".equals(op = leitor.nextLine()));
	}//fim cadastrar
	
	//Metodo que saca um valor de uma conta escolhida pelo usuario
	public void sacarSistema() {
		
		//valor do saque
		double valorSaque;
		
		//codigo da conta que sera pesquisado
		String codConta;
		
		//criacao do leitor
		Scanner leitor = new Scanner(System.in);
		
		//verificando se ja existe conta cadastrada
		if(this.contas.size() == 0) {
			System.out.println("Voce nao possui contas cadastradas");
			System.out.println("Digite qualquer tecla para cadastrar contas ou nao para sair:");
			
			//caso o usuario queira cadastrar conta
			if(!"nao".equals(leitor.nextLine())) {
				cadastrar();//chamada do metodo que cadastra contas
				do {
					//depois de ter as contas criadas, digitar o codigo de alguma para sacar
					System.out.println("Digite o codigo da conta para saque: ");
					
					//armazena o valor digitado, usei o next para corrigir um conflito, se usar nextLine vai dar conflito nos casos em que nao localiza a conta cadastrada
					codConta = leitor.next();
					
					//chamo o metodo pesquisar passando o array de contas e o codigo
					//se nao encontrar ele retorna -1, se encontrar retorna a posicao da conta no array
					if(pesquisar(contas, codConta) < 0) {
						System.out.println("Conta nao cadastrada");
					} else { //caso encontre, ja sera solicitado ao usuario que digite o valor para sacar
						System.out.println("Digite o valor do saque: ");
						valorSaque = leitor.nextDouble();
						
						//indice recebe o inteiro com a posicao da conta no array
						indice = pesquisar(contas, codConta);
						
						//crio uma operacao de conta para sacar
						OperacaoConta s = new OperacaoConta();
						
						//passo como parametro para o metodo a conta e o valor a sacar
						s.sacar(contas.get(indice), valorSaque);
						System.out.println("Cliente: " + contas.get(indice).getCliente().getNome() + " saldo: R$ " + contas.get(indice).getSaldo());
						gravar();//chamada do metodo que grava o resultado do saque no txt
					}
					//controle para parar de sacar quando o usuario digitar sair
					System.out.println("Digite qualquer tecla para realizar outro saque ou sair para encerrar: ");
					leitor.nextLine();
					escolha = leitor.next();//usei next para corrigir conflito que aparece quando uso nextLine
				} while(!"sair".equals(escolha));
			}
		}
	}//fim sacarSistema
	
	//Metodo que deposita um valor em uma conta escolhida pelo usuario
	public void depositarSistema() {
		
		//valor do deposito
		double valorDeposito;
		
		//codigo da conta que sera pesquisado
		String codConta;
		
		//criacao do leitor
		Scanner leitor = new Scanner(System.in);
		
		//verificando se ja existe conta cadastrada
		if(this.contas.size() == 0) {
			System.out.println("Voce nao possui contas cadastradas");
			System.out.println("Digite qualquer tecla para cadastrar contas ou nao para sair:");
			
			//caso o usuario queira cadastrar conta
			if(!"nao".equals(leitor.nextLine())) {
				cadastrar();//chamada do metodo que cadastra contas
				do {
					//depois de ter as contas criadas, digitar o codigo de alguma para depositar
					System.out.println("Digite o codigo da conta para deposito: ");
					
					//armazena o valor digitado, usei o next para corrigir um conflito, se usar nextLine vai dar conflito nos casos em que nao localiza a conta cadastrada
					codConta = leitor.next();
					
					//chamo o metodo pesquisar passando o array de contas e o codigo
					//se nao encontrar ele retorna -1, se encontrar retorna a posicao da conta no array
					if(pesquisar(contas, codConta) < 0) {
						System.out.println("Conta nao cadastrada");
					} else { //caso encontre, ja sera solicitado ao usuario que digite o valor para sacar
						System.out.println("Digite o valor do deposito: ");
						valorDeposito = leitor.nextDouble();
						
						//indice recebe o inteiro com a posicao da conta no array
						indice = pesquisar(contas, codConta);
						
						//crio uma operacao de conta para depositar
						OperacaoConta s = new OperacaoConta();
						
						//passo como parametro para o metodo a conta e o valor a depositar
						s.depositar(contas.get(indice), valorDeposito);
						System.out.println("Cliente: " + contas.get(indice).getCliente().getNome() + " saldo: R$ " + contas.get(indice).getSaldo());
						gravar();//chamada do metodo que grava o resultado do saque no txt
					}
					//controle para parar de depositar quando o usuario digitar sair
					System.out.println("Digite qualquer tecla para realizar outro deposito ou sair para encerrar: ");
					leitor.nextLine();
					escolha = leitor.next();//usei next para corrigir conflito que aparece quando uso nextLine
				} while(!"sair".equals(escolha));
			}
		}
	}//fim depositarSistema
	
	public void transferirSistema() {
		
		//valor do deposito
		double valorTransferencia;
			
		//codigo da conta que sera pesquisado
		String codContaOrigem, codContaDestino, parar;
		
		//criacao do leitor
		Scanner leitor = new Scanner(System.in);
		
		//verificando se ja existe conta cadastrada
		if(this.contas == null || this.contas.size() <= 1) {
			System.out.println("Voce nao possui duas ou mais contas cadastradas");
			System.out.println("Digite qualquer tecla para cadastrar contas ou nao para sair:");
										
			//caso o usuario queira cadastrar conta
			if(!"nao".equals(leitor.nextLine())) {
				cadastrar();//chamada do metodo que cadastra contas
				//verifica se apos cadastrar ja possui 2 contas
				if(this.contas.size() <= 1) {
					System.out.println("Voce ainda nao possui o minimo de duas contas cadastradas");
					System.out.println("Se deseja realizar uma transferencia tera que cadastrar ao menos mais uma conta");
					System.out.println("Digite qualquer tecla para cadastrar ou nao para sair");
					//caso nao possua duas contas e usuario queira cadastrar mais uma para transferir
					if(!"nao".equals(leitor.nextLine()) || this.contas.size() >= 2) {
						cadastrar();//chamada do metodo que cadastra contas
					}
				}
				//verifica uma ultima vez se existem duas contas criadas para realizar a transferencia
				if(this.contas.size() >= 2) {
					System.out.println("Aqui começa a transferencia");
				}
			}
		}
		System.out.println("terminou");
	}//fim metodo transferirSistema
	
	//Metodo que pesquisa uma conta pelo codigo cadastrado e retorna a posicao desta no array
	public int pesquisar(ArrayList<Conta2> contas, String codConta) {
		
		//variavel criada para parar a busca pela conta correta
		String termina = "";
		
		//indice que percorre o array
		int indiceConta = 0;
		
		//laco que pesquisa enquanto o array nao acabar, nao encontrarmos o codigo correto
		while(indiceConta < contas.size() && !contas.get(indiceConta).getCodConta().equals(codConta) && !"terminar".equals(termina)) {
			
			//enquanto array nao acabar e nao encontrar o codigo pesquisa, continue buscando a proxima conta do array
			contas.get(indiceConta).getCodConta();
			
			//para nao estourar o array, se chegamos na ultima posicao, nao incremente, apenas escreva terminar
			if(indiceConta + 1 == contas.size()) {
				termina = "terminar";
			} else {
				
				//se nao chegamos no final do array e nem localizamos a conta, continue incrementando 1
				indiceConta++;
			}
		}
		
		//caso a variavel termina estiver com texto terminar, quer dizer que percorremos o array inteiro e nao localizamos a conta desejada
		//nesse caso, retorne -1
		if("terminar".equals(termina)) {
			return -1;
		} else {
			
			//caso contrario, retorne a posicao do array onde localizou a conta
			return indiceConta;
		}
	}//fim pesquisar
	
	//Metodo que grava dados em um txt
	public void gravar() {
		
		//criado objeto do tipo arquivo
		this.arquivo = new File(this.path);
		
		//criado objeto que tem a funcao de escrever informacoes no buffer de memoria
		//esse objeto recebe como parametro um outro objeto do tipo FileWriter para escrever em arquivo e true para continuar do ponto em q o texto parou
		//esse objeto eh criado dentro de uma estrutura try, catch para tratar possiveis erros de I/O 
		//no caso desses objetos eh obrigatoria a criacao de uma estrutura dessas pq um arquivo a gravacao em arquivo pode apresentar varios erros, desde permissao de gravacao, arquivo corrompido, etc
		try(BufferedWriter escrever = new BufferedWriter(new FileWriter(arquivo, true))) {
			if(arquivo.length()>0) {//se o arquivo ja tiver conteudo ...
				escrever.newLine();//pule linha
				//adicione os dados de nome e saldo no txt
				escrever.write("Conta: " + contas.get(indice).getCodConta() + " Cliente: " + contas.get(indice).getCliente().getNome() + " Saldo: R$ " + contas.get(indice).getSaldo());
				//gravar conteudo
				escrever.flush();
			} else {
				escrever.write("Conta: " + contas.get(indice).getCodConta() + " Cliente: " + contas.get(indice).getCliente().getNome() + " Saldo: R$ " + contas.get(indice).getSaldo());
				escrever.flush();
			}
		} catch(IOException e) {//capturando erro caso aconteca e imprimindo o caminho do erro
			e.printStackTrace();
		}
	}//fim metodo gravar
	
}//fim classe
