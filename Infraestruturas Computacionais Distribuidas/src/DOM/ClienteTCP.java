package DOM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {

	String aux = null;
	String ID = null;
	StringBuilder stb = null;

	public ClienteTCP(String id) {

		ID = id;

		String host = "localhost"; // M�quina onde reside a aplica��o servidora
		int port = 6000; // Porto da aplica��o servidora

		Socket socket = null;
		BufferedReader is = null;
		PrintWriter os = null;
		stb = new StringBuilder(1000);

		try {
			socket = new Socket(host, port);

			// Mostrar os parametros da liga��o
//			System.out.println("Liga��o: " + socket);
//			System.out.println("Endere�o do Servidor: " + socket.getInetAddress() + " Porto: " + socket.getPort());
//			System.out.println("Endere�o Local: " + socket.getLocalAddress() + " Porto: " + socket.getLocalPort());
//			System.out.println();

			// Stream para escrita no socket
			os = new PrintWriter(socket.getOutputStream(), true);

			// Stream para leitura do socket
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Escreve no socket
			// os.println("Ol� mundo!!!");
			char novoID = ID.charAt(0);
//			String novaString = ID.substring(1);
//			switch(novoID){
//				case '1':
//					os.println("1");
//				case '2':
//					os.println("2");
//				case '3':
//					os.println("3");
//				case '4':
//					os.println("4");
//				case '5':
//					os.println("5");
//			}
			os.println(ID);
			// COM O ID DO SERVICO A MSG SERA DIFERENTE , iremos pedir ao
			// servidor o que queremos

			// Mostrar o que se recebe do socket
			while ((aux = is.readLine()) != null) {
				stb.append(aux+"\n");
			}
			aux = stb.toString();
			// System.out.println("Recebi -> " + aux);

		}
		// ---------------------
		// Socket Exceptions
		// ---------------------
		// A maioria dos m�todos da class Socket reportam anomalias atrav�s das
		// excep��es IOException ou a sub classe java.net.SocketException.
		// No entanto, existem outras excep��es, que extendem destas classes,
		// que permitem determinar com maior precis�o a causa dos problemas.
		// O conjunto de classes de excep��es originadas pelos m�todos socks
		// s�o:
		//
		// public class SocketException extends IOException
		//
		// public class BindException extends SocketException
		//
		// public class ConnectException extends SocketException
		//
		// public class NoRouteToHostException extends SocketException
		//
		// public class UnknownHostException extends IOException
		//
		// public class ProtocolException extends IOException
		//
		catch (UnknownHostException e) {
			System.err.println("M�quina " + host + " desconhecida: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro na liga��o: " + e.getMessage());
		} finally {
			// No fim de tudo, fechar os streams e o socket
			try {
				if (os != null)
					os.close();
				if (is != null)
					is.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				// if an I/O error occurs when closing this socket
			}
		} // end finally

	}
	
	public String respostaServidor(){
		return this.aux;
	}

}
