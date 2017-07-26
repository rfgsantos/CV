package servidorTP2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCPConcorrente {

    public final static int DEFAULT_PORT = 6000; 
    
    
    public static void main(String[] args) 
    {
        int port = DEFAULT_PORT; 

        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException e) {
                System.err.println("Erro no porto indicado");
            }
        }
        
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

            Socket newSock    = null;

            for( ; ; ) {
                System.out.println("Servidor TCP concorrente aguarda ligacao no porto " + port + "..." );

                // Espera connect do cliente
                newSock = serverSocket.accept(); 

                Thread th = new HandleConnectionThread(newSock);
                th.start();
            }
        } 
        catch (IOException e) {
            System.err.println("Excepção no servidor: " + e);
        }
    } // end main

} // end ServidorTCP



class HandleConnectionThread extends Thread {

    private Socket s;


    public HandleConnectionThread(Socket connection) {
        this.s = connection;
    }


    public void run() {
System.out.println("Servidor dedicado a iniciar execucao...");
		
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		String linha = null;
		
		try {
			try {
				out = new ObjectOutputStream( s.getOutputStream() );
				in = new ObjectInputStream( s.getInputStream() );
			}
			catch (IOException ioEx) {
				System.out.println("Erro ao instanciar as streams: " + ioEx.getMessage() );
				ioEx.printStackTrace();
				return;
			}
			
			System.out.println("Servidor dedicado obteve as streams...");
			
			for(;;) {
				linha = (String)in.readObject();
				if ( linha==null ) {
					break;
				}
				System.out.println("Recebi: "+linha);
				//System.out.println( "["+Thread.currentThread().getName()+"]" + linha );
				out.writeObject( linha.toUpperCase() );
			}
		}
		catch (IOException ioEx) {
			System.out.println("O cliente terminou.");
		}
		catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			System.out.println("Servidor dedicado a terminar...");
			try {
				if ( in!=null ) {
					in.close();
				}
				if ( out!=null ) {
					out.flush();
					out.close();
				}
				if ( s!=null ) {
					s.close();
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("Servidor dedicado terminou.");
	}
} // end HandleConnectionThread
