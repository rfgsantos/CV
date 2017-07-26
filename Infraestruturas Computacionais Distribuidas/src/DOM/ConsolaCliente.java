package DOM;

import java.util.Random;
import java.util.Scanner;

public class ConsolaCliente implements Runnable {

	// identifier para efeitos de identificacao
	// final String ID = getLocalIP();

	// identifier de quem é o utilizador
	final String idPessoa = "0"; // 0 funcionario, 1 cliente rest
	
	// DECLARACAO DE VARIAVEIS
	ClienteTCP cl;
	Scanner sc;
	String nomeCliente, dataNascimento, ipLocal, ID_Servico;
	int aux;
	Ementa ementa = new Ementa();
	String divida;
	Tempo tempo;
	Random rnd;
	int idCliente;

	public static void main(String[] args) {
		ConsolaCliente icl = new ConsolaCliente();
		icl.run();

	}

	ConsolaCliente() {

		// INICIALIZACAO DE VARIAVEIS
		sc = new Scanner(System.in);
		ipLocal = null;
		nomeCliente = null;
		dataNascimento = null;
		divida = "";
		aux = 0;
		ID_Servico = "0";
		tempo = new Tempo();
		rnd = new Random();
		
		this.idCliente = 100000 + rnd.nextInt(900000);

	}

	// loop principal
	public void run() {

		// caso seja um FUNCIONARIO
		if (idPessoa.equals("0")) {

			while (true) {

				// menu funcionario
				System.out.println("Restaurante Casa do Pasto - F");
				System.out.println();

				// imrprimir menu
				printMenuFuncionario();

				// switch case para opcoes do funcionario
				switchFuncionario();
//				printAux();
			}

		}

		// caso seja um CLIENTE
		else {

			// menu cliente
			System.out.println("Restaurante Casa do Pasto - C");
			System.out.println();

			// pedir nome e data de nascimento e imprimir menu
			// pedirInfoCliente();

			while (true) {
				
				refreshDivida();
				
				printMenuCliente();
				// switch case para os opcoes do cliente
				switchCliente();
				//printAux();

			}
		}

	}

	// ultima informaçao recebi do servidor
	private String returnAux() {
		return cl.aux;
	}
	
	private void ementaOpcoes(){
		cl = new ClienteTCP("1");
		String[] ementaPrecos = returnAux().split(":");
		//ultima posicao do array é NULL por causa do comando string ex: prato:vazio
		
		String[] pratos = new String[ementaPrecos.length];
		String[] precos = new String[ementaPrecos.length];

		for(int i=0; i<ementaPrecos.length;i++){
			String current = ementaPrecos[i];
			for(int j=0; j<current.length();j++){
				if(ementaPrecos[i].charAt(j) == '_'){
					pratos[i] = ementaPrecos[i].substring(0,j);	
					precos[i] = ementaPrecos[i].substring(j+1);
				}
			}
		}
		
		System.out.println("-------------------------Ementa Actual-------------------------");
		for (int i = 0; i < ementaPrecos.length-1; i++) {
			System.out.println("" + (i+1) +". "+ pratos[i] + "........preco: " + precos[i] + "$");
		}
		System.out.println();
		System.out.println();
		sc = new Scanner(System.in);
		
		System.out.println("Ecolha uma opcao: ");
		System.out.println("1.Pedir produto");
		System.out.println("2.Voltar para menu");
		int opcaoEscolhida = sc.nextInt();
		
		if(opcaoEscolhida == 1){
			escolheNaEmenta();
		}
		
		System.out.println();
		System.out.println();
		
		
	
	}
	
	private void escolheNaEmenta(){
		cl = new ClienteTCP("1");
		String[] ementaPrecos = returnAux().split(":");
		//ultima posicao do array é NULL por causa do comando string ex: prato:vazio
		
		String[] pratos = new String[ementaPrecos.length];
		String[] precos = new String[ementaPrecos.length];

		for(int i=0; i<ementaPrecos.length;i++){
			String current = ementaPrecos[i];
			for(int j=0; j<current.length();j++){
				if(ementaPrecos[i].charAt(j) == '_'){
					pratos[i] = ementaPrecos[i].substring(0,j);	
					precos[i] = ementaPrecos[i].substring(j+1);
				}
			}
		}
		
		System.out.println("-------------------------Ementa Actual-------------------------");
		for (int i = 0; i < ementaPrecos.length-1; i++) {
			System.out.println("" + (i+1) +". "+ pratos[i] + "........preco: " + precos[i] + "$");
		}
		System.out.println(ementaPrecos.length + ". Voltar");
		
		System.out.println();
		System.out.println();
		sc = new Scanner(System.in);
		
		System.out.println("Ecolha um produto: ");
		int produtoEscolhido = sc.nextInt();
		
		if(produtoEscolhido != ementaPrecos.length){
			cl = new ClienteTCP("4"+idCliente + pratos[produtoEscolhido-1] + ":");
			System.out.println("---------------------------------------------------------------");
			System.out.println("---------------------------------------------------------------");
			System.out.println("---------------------PEDIDO EFECTUADO--------------------------");
			System.out.println("---------------------------------------------------------------");
			System.out.println("---------------------------------------------------------------");
			System.out.println();
			System.out.println();
			escolheNaEmenta();
		}
		
		System.out.println();
		System.out.println();
		

	}
	public void consultaEmentaData(){
		sc = new Scanner(System.in);
		
		System.out.println("Digita a data do dia que deseja consulta a ementa");
		System.out.println("No formato: yyyymmdd");
		String recebeData = sc.nextLine();
		
		cl = new ClienteTCP("3"+recebeData);
		
		String[] ementaPrecos = returnAux().split(":");
		//ultima posicao do array é NULL por causa do comando string ex: prato:vazio
		
		String[] pratos = new String[ementaPrecos.length];
		String[] precos = new String[ementaPrecos.length];

		for(int i=0; i<ementaPrecos.length;i++){
			String current = ementaPrecos[i];
			for(int j=0; j<current.length();j++){
				if(ementaPrecos[i].charAt(j) == '_'){
					pratos[i] = ementaPrecos[i].substring(0,j);
					precos[i] = ementaPrecos[i].substring(j+1);
				}
			}
		}
		
		System.out.println("-------------------------Ementa Almoco-------------------------");
		for (int i = 0; i < ementaPrecos.length-9; i++) {
			System.out.println("" + (i+1) +". "+ pratos[i] + "........preco: " + precos[i] + "$");
		}
		System.out.println();
		System.out.println();
		System.out.println("-------------------------Ementa Jantar-------------------------");
		for (int i=ementaPrecos.length-9; i < ementaPrecos.length-1; i++) {
			System.out.println("" + (i+1) +". "+ pratos[i] + "........preco: " + precos[i] + "$");
		}
		
		System.out.println();
		System.out.println();
		
	}
	
	public void consultaEmentaDataHora(){
		sc = new Scanner(System.in);
		System.out.println("Digite a data que deseja consultar a ementa");
		System.out.println("No formato: yyyymmdd");
		// TODO fazer validacao da entrada. evitar valores de merda. tambem podes fazer validacao do lado do servidor
		// TODO formato errado ou datas impossiveis
		String recebeData = sc.nextLine();
		
//		System.out.println("DEBUG: " + recebeData);
		System.out.println("Digite as horas a que deseja consultar a ementa");
		System.out.println("No formato: hhmm");
		String recebeHora = sc.nextLine();
//		System.out.println("DEBUG : " + recebeHora);
		String result = "2"+recebeData+recebeHora;
//		System.out.println("DEBUG result: " + result);
		cl = new ClienteTCP(result);
		
		
		String[] ementaPrecos = returnAux().split(":");
		String[] pratos = new String[ementaPrecos.length];
		String[] precos = new String[ementaPrecos.length];
		System.out.println("-------------------------Ementa Pedida-------------------------");
		for(int i=0; i<ementaPrecos.length;i++){
			String current = ementaPrecos[i];
			for(int j=0; j<current.length();j++){
				if(ementaPrecos[i].charAt(j) == '_'){
					pratos[i] = ementaPrecos[i].substring(0,j);
					precos[i] = ementaPrecos[i].substring(j+1);
				}
			}
		}
		
		for(int i=0; i<ementaPrecos.length-1;i++){
			System.out.println("" + (i+1) +". "+ pratos[i] + "........preco: " + precos[i] + "$");
		}
		System.out.println();
		System.out.println();
		
	}
	
	private void refreshDivida(){
		cl = new ClienteTCP("6"+idCliente);
		divida = returnAux();
	}
		


	// metodo privado para imprimir menu do cliente
	private void printMenuCliente() {
		System.out.println("#########################################");
		System.out.println("##-------------- MENU -----------------##");
		System.out.println("## 1. Consultar ementa atual           ##");
		System.out.println("## 2. Consultar ementa data/hora       ##");
		System.out.println("## 3. Consultar ementa dia da semana   ##");
		System.out.println("## 4. Registar pedido                  ##");
		System.out.println("## 5. Consultar pedido                 ##");
		System.out.println("##-------------------------------------##");
		System.out.println("##            Divida: " + divida.trim()+"               "
				+ "##");
		System.out.println("##-------------------------------------##");
		System.out.println("##           Que deseja ?              ##");
		System.out.println("#########################################");

	}


	// metodo privado para imprimir o menu do funcionario
	private void printMenuFuncionario() {
		System.out.println("##################################");
		System.out.println("##---------- MENU --------------##");
		System.out.println("## 1. Modificar estado pedido   ##");
		System.out.println("## 2. Remover Pedidos           ##");
		System.out.println("##------------------------------##");
		System.out.println("##- Que deseja ?                ##");
		System.out.println("##################################");

	}
	
	private void consultaEmentaActual(){
		cl = new ClienteTCP("1");
		// out.println(cenas);
		String[] ementaPrecos = returnAux().split(":");
		String[] pratos = new String[ementaPrecos.length];
		String[] precos = new String[ementaPrecos.length];
		
		for(int i=0; i<ementaPrecos.length;i++){
			String current = ementaPrecos[i];
			for(int j=0; j<current.length();j++){
				if(ementaPrecos[i].charAt(j) == '_'){
					pratos[i] = ementaPrecos[i].substring(0,j);
					precos[i] = ementaPrecos[i].substring(j+1);
				}
			}
		}
		System.out.println("-------------------------Ementa Actual-------------------------");
		for(int i=0; i<ementaPrecos.length-1;i++){
			System.out.println("" + (i+1) +". "+ pratos[i] + "........preco: " + precos[i] + "$");
		}
		
		System.out.println();
		System.out.println();
		
		
	}
	
	private void consultaPedido(){
		cl = new ClienteTCP("5"+idCliente);
		

		String[] pedido = returnAux().split(":");
		// out.println(rekt.length);
		String estadoPedido = pedido[pedido.length-1];
		
		System.out.println("------------------Consulta Pedido----------------------");
		System.out.println("---------------Pedido do Cliente "+ idCliente + "----------------");
		System.out.println();
		
		for(int i=0;i<pedido.length-1;i++){
			System.out.println("Pediu: " + pedido[i] + " --- Estado: " + estadoPedido);
		}
		
		System.out.println();
		System.out.println();
	}

	// metodo privado para pedir informacoes ao cliente
	// Nome e Data de Nascimento
	private void pedirInfoCliente() {
		// pedir nome ao cliente
		System.out.println("Nome: ");
		nomeCliente = sc.nextLine();

		// pedir data nascimento
		System.out.println("Data de Nascimento (ddmmaaaa): ");
		dataNascimento = sc.nextLine();

		System.out.println();
	}

	// metodo privado para selecionar escolha do cliente
	private void switchCliente() {

		aux = sc.nextInt();

		switch (aux) {

		// consultar ementa atual
		case 1:
			consultaEmentaActual();
			break;

		// consultar ementa data/hora
		case 2:
			consultaEmentaDataHora();
			break;

		// consultar ementa dia da semana
		case 3:
			consultaEmentaData();
			break;

		case 4:
			ementaOpcoes();
			break;

		// consultar estado do pedido
		case 5:
			consultaPedido();
			break;

		}

	}

	// metodo privado para selecionar escolha do funcionario
	private void modificaEstadoPedido(){
		cl = new ClienteTCP("m0");
		String[] todosClientes = returnAux().split(":");
		String[] clientes = new String[todosClientes.length];
		String[] estados = new String[todosClientes.length];
		
		for(int i=0; i<todosClientes.length;i++){
			String current = todosClientes[i];
			for(int j=0; j<current.length();j++){
				if(todosClientes[i].charAt(j) == '_'){
					clientes[i] = todosClientes[i].substring(0,j);
					estados[i] = todosClientes[i].substring(j+1);
				}
			}
		}
		
		System.out.println("-------------------------Lista Pedidos-------------------------");
		for(int i=0;i<todosClientes.length-1;i++){
			System.out.println(""+(i+1)+". Cliente: " + clientes[i] + " --- Estado: " + estados[i]);
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Ecolha uma opcao: ");
		System.out.println("1.Mudar um estado");
		System.out.println("2.Voltar para menu");
		int opcaoEscolhida = sc.nextInt();
		
		if(opcaoEscolhida == 1){
			mudarEstado();
		}
		System.out.println();
		System.out.println();
		
	}
	
	private void mudarEstado(){
		cl = new ClienteTCP("m0");
		String[] todosClientes = returnAux().split(":");
		String[] clientes = new String[todosClientes.length];
		String[] estados = new String[todosClientes.length];
		
		for(int i=0; i<todosClientes.length;i++){
			String current = todosClientes[i];
			for(int j=0; j<current.length();j++){
				if(todosClientes[i].charAt(j) == '_'){
					clientes[i] = todosClientes[i].substring(0,j);
					estados[i] = todosClientes[i].substring(j+1);
				}
			}
		}
		
		System.out.println("-------------------------Lista Pedidos-------------------------");
		for(int i=0;i<todosClientes.length-1;i++){
			System.out.println(""+(i+1)+ ". Cliente: " + clientes[i] + " --- Estado: " + estados[i]);
		}
		System.out.println(todosClientes.length + ". Voltar");
		
		System.out.println();
		System.out.println();
		
		sc = new Scanner(System.in);
		System.out.println("Escolha o cliente: ");
		int clienteEscolhido = sc.nextInt();
		if(clienteEscolhido != todosClientes.length){
			auxMudaEstado(clientes[clienteEscolhido-1]);
		}
		
		System.out.println();
		System.out.println();
		
		
	}
	
	private void auxMudaEstado(String cliente){
		sc = new Scanner(System.in);
		System.out.println("----------Escolha o Estado------------");
		System.out.println("1. Aceitar");
		System.out.println("2. Preparacao");
		System.out.println("3. Pronto");
		System.out.println("4. Entregue");
		
		int escolhaEstado = sc.nextInt();
		
		if(escolhaEstado == 1){
			cl = new ClienteTCP("m1" + cliente + "_" + "Aceitar");
		}else if(escolhaEstado == 2){
			cl = new ClienteTCP("m1" + cliente + "_" + "Preparacao");
		}else if(escolhaEstado == 3){
			cl = new ClienteTCP("m1" + cliente + "_" + "Pronto");
		}else if(escolhaEstado == 4){
			cl = new ClienteTCP("m1" + cliente + "_" + "Entregue");
		}
		
		System.out.println();
		System.out.println();
	}
	
	private void removePedido(){
		cl = new ClienteTCP("m0");
		String[] todosClientes = returnAux().split(":");
		String[] clientes = new String[todosClientes.length];
		String[] estados = new String[todosClientes.length];
		
		for(int i=0; i<todosClientes.length;i++){
			String current = todosClientes[i];
			for(int j=0; j<current.length();j++){
				if(todosClientes[i].charAt(j) == '_'){
					clientes[i] = todosClientes[i].substring(0,j);
					estados[i] = todosClientes[i].substring(j+1);
				}
			}
		}
		
		System.out.println("-------------------------Lista Pedidos-------------------------");
		for(int i=0;i<todosClientes.length-1;i++){
			System.out.println(""+(i+1)+". Cliente: " + clientes[i] + " --- Estado: " + estados[i]);
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Ecolha uma opcao: ");
		System.out.println("1.Remover um Pedido");
		System.out.println("2.Voltar para menu");
		int opcaoEscolhida = sc.nextInt();
		
		if(opcaoEscolhida == 1){
			remove();
		}
		System.out.println();
		System.out.println();
		
		
	}
	
	private void remove(){
		cl = new ClienteTCP("m0");
		String[] todosClientes = returnAux().split(":");
		String[] clientes = new String[todosClientes.length];
		String[] estados = new String[todosClientes.length];
		
		for(int i=0; i<todosClientes.length;i++){
			String current = todosClientes[i];
			for(int j=0; j<current.length();j++){
				if(todosClientes[i].charAt(j) == '_'){
					clientes[i] = todosClientes[i].substring(0,j);
					estados[i] = todosClientes[i].substring(j+1);
				}
			}
		}
		
		System.out.println("-------------------------Lista Pedidos-------------------------");
		for(int i=0;i<todosClientes.length-1;i++){
			System.out.println(""+(i+1)+". Cliente: " + clientes[i] + " --- Estado: " + estados[i]);
		}
		System.out.println();
		System.out.println();
		
		sc = new Scanner(System.in);
		System.out.println("Escolha o cliente: ");
		int clienteEscolhido = sc.nextInt();
		if(clienteEscolhido != todosClientes.length){
			auxRemove(clientes[clienteEscolhido-1]);
		}
		
		System.out.println();
		System.out.println();
		
		
	}
	
	private void auxRemove(String cliente){
		cl = new ClienteTCP("r"+cliente);
		System.out.println("------------------------------------");
		System.out.println("------------------------------------");
		System.out.println("---------------Removeu--------------");
		System.out.println("------------------------------------");
		System.out.println("------------------------------------");
		
		System.out.println();
		System.out.println();
		
	}
	
	private void switchFuncionario() {

		aux = sc.nextInt();

		switch (aux) {

		// modificar estado pedido
		case 1:
			modificaEstadoPedido();


		// remover pedidos
		case 2:
			// cliente instanciado como funcionario
			removePedido();
		}

	}

}
