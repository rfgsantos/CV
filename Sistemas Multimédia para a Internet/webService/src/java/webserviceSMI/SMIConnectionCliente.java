/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserviceSMI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.jws.WebService;

/**
 *
 * @author Denga
 */
@WebService(endpointInterface = "webserviceSMI.SMIConnection")
public class SMIConnectionCliente implements SMIConnection{
    static private Connection conn;
    static private String user, password;
    static private final String url = "jdbc:mysql://127.0.0.1:3306/smi";
    public SMIConnectionCliente(){
        connectSQL();
    }
    
    private void connectSQL() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			user = "root";
			password = "";
			conn = DriverManager.getConnection(url, user, password);
			if (conn == null)
                            System.out.println("erro conexão");				
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    private void printSQLException(SQLException ex) {
		while (ex != null) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getSQLState());
			System.out.println(ex.getErrorCode());
			// get next exception in object
			ex = ex.getNextException();
		}
	}
    
    public ArrayList<Object> queryResult(String query) {

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			try (ResultSet rs = stmt.executeQuery()) {
				return ArrayResultSet(rs);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}

		return null;
	}
    
    public ArrayList<Object> ArrayResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnNumber = rsMetaData.getColumnCount();
		String columnName[] = new String[columnNumber];
		ArrayList<Object> resultado = new ArrayList<Object>();

		for (int i = 0; i < columnNumber; i++) {
			columnName[i] = rsMetaData.getColumnName(i + 1);
		}

		while (rs.next()) {
			for (int i = 0; i < columnNumber; i++) {
				// if (i > 0)
				// System.out.print(", ");
				// System.out.print(columnName[i] + " " + rs.getObject(i + 1));
				resultado.add(rs.getObject(i + 1));
			}
			// System.out.println();
		}

		return resultado;
	}

    
    @Override
    public String[] getParametrosCliente(String username) {
        String [] componentes = new String[5];
        componentes[0] = getNome(username);
        componentes[1] = getUsername(username);
        componentes[2] = getFoto(username);
        componentes[3] = getEmail(username);
        componentes[4] = getTipo(username);
        
        return componentes;
    }

    @Override
    public String getNome(String username) {
        String query = "SELECT userclient.nome FROM userclient WHERE username = '"+username+"'";
        String nome = "";
        nome = (String) queryResult(query).get(0);
        
       return nome;
    }

    @Override
    public String getUsername(String username) {
        String query = "SELECT userclient.username FROM userclient WHERE username = '"+username+"'";
        String user = "";
        user = (String) queryResult(query).get(0);
        
        return user;
    }

    @Override
    public String getFoto(String username) {
        String query = "SELECT userclient.Fotografia_perfil FROM userclient WHERE username = '"+username+"'";
        String foto = "";
        foto = (String) queryResult(query).get(0);
        
        return foto;
    }

    @Override
    public String getTipo(String username) {
        String query = "SELECT userclient.tipo FROM userclient WHERE username = '"+username+"'";
        String tipo = "";
        tipo = (String) queryResult(query).get(0);
        
        return tipo;
    }

    @Override
    public String getEmail(String username) {
        String query = "SELECT userclient.email FROM userclient WHERE username = '"+username+"'";
        String email = "";
        email = (String) queryResult(query).get(0);
        
        return email;
    }
    
 
    
}
