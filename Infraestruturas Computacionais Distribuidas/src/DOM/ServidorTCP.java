package DOM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import Enumerators.ModoPrato;
import Enumerators.TipoEmenta;

public class ServidorTCP {

	public static ModoPrato ModoPratobyDate(int diasemana){
//		System.out.println("Recebi diasemana = "+diasemana);
//		System.out.println("Recebi diasemana = "+Calendar.MONDAY);
		if (diasemana == Calendar.SUNDAY || diasemana == Calendar.SATURDAY){
			return ModoPrato.FDSFERIADO;
		}
		else {
			return ModoPrato.DIASEMANA;
		}

	}
	public static TipoEmenta TipoEmentabyDate(int hora){
//		System.out.println("hora metodo" +hora);
		if (hora >=12 && hora < 15){
			return TipoEmenta.ALMOCO;
		}
		else if (hora >=19 && hora < 22) {
			return TipoEmenta.JANTAR;
		}
		else {
			return TipoEmenta.FECHADO;
		}
	}
	
	
	public static void main(String args[]) {

	
		Ementa ementa = new Ementa();
		
		String aux1 = null;
		
		int port = 6000;

		ServerSocket serverSocket = null;
		
		

		try {
			// o construtor ServerSocket gera uma excepção IOException (mais
			// especificamente BindException)
			// se o socket não poder ser associado ao porto indicado. Esta
			// excepção significa que ou
			// existe outro programa a utilizar o porto pretendido ou o socket
			// está a ser associado a um
			// porto entre 1 e 1023 sem privilégios de administrador (como, por
			// exemplo, no sistema UNIX)
			serverSocket = new ServerSocket(port);

			Socket newSock = null;
			BufferedReader is = null;
			PrintWriter os = null;

			for (;;) {
		
//				System.out.println("Servidor aguarda ligacao no porto " + port + "...");

				// Espera connect do cliente
				newSock = serverSocket.accept();

				try {
					// circuito virtual estabelecido: socket cliente na variavel
					// newSock
//					System.out.println("Servidor aceitou a ligacao: " + newSock.getInetAddress().getLocalHost().getCanonicalHostName());

					is = new BufferedReader(new InputStreamReader(newSock.getInputStream()));
					// Scanner socketInput = new Scanner(
					// newSock.getInputStream() );

					// PrinterWriter instanciado com 2º arg true o que indica
					// auto flush quando
					// utilizarmos os métodos println ou printf (ver
					// documentação)
					os = new PrintWriter(newSock.getOutputStream(), true);

					String inputLine = is.readLine();

					
					char choose  = inputLine.charAt(0);
					System.out.println("RECEBIDO DEBUG: " + choose);
					String resto = inputLine.substring(1);
					System.out.println("RECEBIDO DEBUG RESTO: " + resto);
					String idC;
					Pedido pedido;
					
					
					switch(choose){
						
						case '1':
							//consulta ementa hora atual
							Tempo t = new Tempo();
							int diasemana = t.getDiaCorrente();
							int hora = t.getHoraCorrente();
							//segunda feira ao meio dia
							aux1 = ementa.consultaEmenta(ModoPratobyDate(2), TipoEmentabyDate(12));
							break;
						case '2':
							//12 07 93 12 12
							//consulta ementa data e hora
							Tempo t2 = new Tempo();
							//spaghetti code start TODO refactor
							String[] auxTempo = new String[4];
							System.out.println("DEBUG HORA DATA : " + resto);
							int ano3 = Integer.parseInt(resto.substring(0,4));
							String outros = resto.substring(4);
							int idx = 0;
							for (int i = 2; i<= outros.length(); i+=2){
								String aux = outros.substring(i-2, i);
								auxTempo[idx] = aux;
								System.out.println(aux);
								idx++;
								
							}
							t2.modificaData(Integer.parseInt(auxTempo[1]), Integer.parseInt(auxTempo[0]), ano3);
							t2.modificaHora(Integer.parseInt(auxTempo[2]), Integer.parseInt(auxTempo[3]), 0);
							int ds = t2.getDia();
							int h = t2.getHora();
//							System.out.println("data "+t2.getDia());
//							System.out.println("hora case"+t2.getHora());
							System.out.println("teste "+ModoPratobyDate(ds));
							System.out.println("teste2 "+TipoEmentabyDate(h));
							
							
							aux1 = ementa.consultaEmenta(ModoPratobyDate(ds), TipoEmentabyDate(h));
							break;
							//Spaghetti code over
						case '3':
							//12 07 93
							//consulta ementa data
							String ementaTotal = "";
							
							Tempo t4 = new Tempo();
							//spaghetti code start TODO refactor
							int ano4 = Integer.parseInt(resto.substring(0,4));
							String mesEdia = resto.substring(4);
							String[] auxTempo4 = new String[2];
							int idx4 = 0;
							for (int i = 2; i<= mesEdia.length(); i+=2){
								String aux = mesEdia.substring(i-2, i);
								auxTempo4[idx4] = aux;
								System.out.println("estou aqui: " + aux);
								idx4++;
								
							}
							t4.modificaData(Integer.parseInt(auxTempo4[1]), Integer.parseInt(auxTempo4[0]), ano4);
							t4.modificaHora(12, 30, 0);
							int ds4 = t4.getDia();
							System.out.println("dia : " + ds4);
							int h4 = t4.getHora();
							ementaTotal = ementa.consultaEmenta(ModoPratobyDate(ds4), TipoEmentabyDate(h4));
							System.out.println("modo da refeição: " + ModoPratobyDate(ds4));
							System.out.println("tipo da refeição: " + TipoEmentabyDate(h4));
							t4.modificaHora(21, 30, 0);
							h4 = t4.getHora();
							ementaTotal+= ementa.consultaEmenta(ModoPratobyDate(ds4),TipoEmentabyDate(h4));
							System.out.println("modo da refeição: " + ModoPratobyDate(ds4));
							System.out.println("tipo da refeição: " + TipoEmentabyDate(h4));
							
							aux1 = ementaTotal;
							break;
						case '4':
							//regista pedido
//							System.out.println("entrou");
//							System.out.println("IP A SAIR: " + newIP);
							idC = resto.substring(0,6);
							pedido = new Pedido(idC);
//							System.out.println("DEBUG ID: "+idC);
							resto = resto.substring(6);
//							System.out.println("DEBUG RESTO: "+resto);
							String item = "";
							int aux =0;
							
							for(int i=0;i<resto.length();i++){
								if(resto.charAt(i) == ':'){
									item = resto.substring(aux, i);
									System.out.println("O ITEM A TRATAR: " + item);
									aux = i+1; 
									System.out.println("CENAS DEBUG ID: " + ementa.procuraIdItem(item));
									System.out.println(pedido.insereElementoPedido(ementa.procuraIdItem(item)));
									item = "";
								}
							}
							

							aux1 = "Pedido efetuado";
							break;
					
						case '5':
							//consulta pedido
							idC = resto.substring(0,6);
							pedido = new Pedido(idC);
							System.out.println("DEBUG ID: "+idC);
							resto = resto.substring(6);
							System.out.println("DEBUG RESTO: "+resto);
							aux1 = pedido.consultaPedido();
							break;
						case '6':
							//divida
							Tempo tp3 = new Tempo();
							idC = resto.substring(0,6);
							pedido = new Pedido(idC);
							System.out.println("DEBUG ID: "+idC);

							ArrayList<Integer> arrayaux = pedido.idElementosPedido();
							int preco = 0;
							for(int i=0; i<arrayaux.size(); i++){
								int current = arrayaux.get(i);
								//usar o objeto TEMPO TODO
								preco += Integer.parseInt(ementa.devolvePreco(ModoPratobyDate(2), TipoEmentabyDate(12), current));
							}
							aux1 = Integer.toString(preco) + "$";
							break;
							
						case '7':
							//atribuir estrela a um empregado
							
							String valor = String.valueOf(resto.charAt(0));
							String empregado = resto.substring(1);
							
							aux1 = String.valueOf(ementa.atribuiEstrela(valor, empregado));
							
							break;
						case '8':
							//devolve empregado
							
							int idempregado = Integer.parseInt(resto);
							
							aux1 = ementa.getEmpregado(idempregado);
							
							break;
							
						case '9':
							//verifica user
							String user = "";
							String pass = "";
							for(int i=0; i<resto.length();i++){
								if(resto.charAt(i) == '_'){
									user = resto.substring(0,i);
									pass = resto.substring(i+1);
								}
							}
							System.out.println("DEBUG USER: " + user);
							System.out.println("DEBUG PASSWORD: " + pass);
							
							System.out.println("DEBUG VERIFICA USER: " + String.valueOf(ementa.verificarUser(user, pass)));
//							aux1 = String.valueOf(ementa.verificarUser(user, pass));
							aux1 = "cenas";
							break;
							
						case 'm':
							//devolve todos os pedidos existentes
							if(resto.charAt(0) == '0'){
								Ementa emeAux = new Ementa();
								aux1 = emeAux.todosPedidos();
							}
							//muda estado pedido
							else if(resto.charAt(0) == '1'){
								String cliente = resto.substring(1,7);
								System.out.println("DEBUG CLIENTE MUDA ESTADO " + cliente);
								String estado = resto.substring(8);
								System.out.println("DEBUG ESTADO MUDA ESTADO " + estado);
								
								pedido = new Pedido(cliente);
								pedido.mudaEstadoPedido(cliente, estado);
								
								aux1="com sucesso";
							}

							break;
						case 'r':
							//remover um pedido
							
							pedido = new Pedido(resto);
							
							if(pedido.removePedido()){
								aux1="removeu";
							}else{
								aux1="nao removeu";
							}
							
							break;
							
					}
					//System.out.println(aux2)
					// String inputLine = socketInput.nextLine();
					System.out.println("Recebi -> " + inputLine);
					
					
					//os.println("Olá para ti também!!");
					os.println(aux1);
					
					System.out.println();
					
				} catch (IOException e) {
					// Excepção relacionada com a ligação actual - newSock, pode
					// ser
					// originada, por exemplo, pelo cliente ter terminado a
					// ligação.
					// Em princípio não se pretende realizar qualquer acção,
					// eventualmente,
					// registar o acontecimento num ficheiro de log
					System.err.println("erro na ligaçao " + newSock + ": " + e.getMessage());
				} finally {
					// garantir que o socket é fechado
					try {
						if (is != null)
							is.close();
						if (os != null)
							os.close();

						if (newSock != null)
							newSock.close();
					} catch (IOException e) {
					}
				}
				

			} // end for
		} catch (IOException e) {
			System.err.println("Excepção no servidor: " + e);
		}

	}
	

} // end ServidorTCP
