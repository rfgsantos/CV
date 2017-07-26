package SBD;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class DBconnection2 {

	static final String url = "jdbc:mysql://127.0.0.1:3306/sbd";
	
	static private Connection conn;
	static String user, password;

	private static int mesCorrente,anoCorrente,horaCorrente,minutoCorrente,segundoCorrente,diaCorrente;

	
	public DBconnection2() {
		// TODO Auto-generated constructor stub
		user = null;
		password = null;
		connectSQL();
		
	}
	
	public static  void connectSQL(){
		
		try{
//			Class.forName("sun.jdbc.odbc.jdbcOdbcDriver");
			Class.forName("com.mysql.jdbc.Driver");
			user = "SBD2017";
			password = "cenas";
			conn = DriverManager.getConnection(url,user,password);
			if(conn == null)System.out.println("entrei");
		}catch(SQLException e){
			printSQLException(e);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
    public static void printSQLException(SQLException ex) {
        while (ex != null) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getSQLState());
            System.out.println(ex.getErrorCode());
            // get next exception in object
            ex = ex.getNextException();
        }
    }
    
    public static ArrayList<Object> queryResult(String query){
    	
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()) {
                return ArrayResultSet(rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    	
    	
    	return null;
    }
    
    public static ArrayList<Object> ArrayResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int columnNumber = rsMetaData.getColumnCount();
        String columnName[] = new String[columnNumber];
        ArrayList<Object> resultado = new ArrayList<Object>();
        
        for (int i = 0; i < columnNumber; i++) {
            columnName[i] = rsMetaData.getColumnName(i + 1);
        }

        while (rs.next()) {
            for (int i = 0; i < columnNumber; i++) {
                if (i > 0)
                    System.out.print(", ");
                System.out.print(columnName[i] + " " + rs.getObject(i + 1));
                resultado.add(rs.getObject(i+1));
            }
            System.out.println();
        }
        
        return resultado;
    }
    
    public void disconnectSQL (){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
    }
    
    public boolean loginAuthentication(String nome, String password){
    	
    	String query;
    	query = "SELECT funcionario.password, funcionario.nome FROM funcionario WHERE password = '"+password+"' and nome='" +nome+"' ";
   
    	try(PreparedStatement stmt = conn.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()) {
            	 if(!rs.next()){
            		 return false;
            	 }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    	
    	return true;
    }
    
    public boolean registerClient(String nome, String data){
    	String query;
    	query = "INSERT INTO cliente VALUES('0','"+nome+"','"+data+"')";
    	
    	try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            printSQLException(e);
        }
    	
    	return false;
    }
    

    
    public static ArrayList<Object> currentMenu(String restaurante){
    	String query = "";
		Calendar cal = Calendar.getInstance();

		diaCorrente = cal.get(Calendar.DAY_OF_WEEK);
		mesCorrente = cal.get(Calendar.MONTH)+1;
		anoCorrente = cal.get(Calendar.YEAR);

		horaCorrente = cal.get(Calendar.HOUR_OF_DAY);
		minutoCorrente = cal.get(Calendar.MINUTE);
		segundoCorrente = cal.get(Calendar.SECOND);
		
		// almoço 12:00 - 15:00
		// jantar 19:00 - 22:00
		//almoco - dia semana
//		if((horaCorrente >= 12 && horaCorrente < 15) && (diaCorrente > 1 && diaCorrente < 7)){
//			query = "SELECT (id) FROM menu WHERE tipo='almoço' and modo='dia-semana' and restaurante_designacao = '"+restaurante+"'";
//			try(PreparedStatement stmt = conn.prepareStatement(query)) {
//	            ResultSet rs = stmt.executeQuery(query);
//	            System.out.println(ArrayResultSet(rs).size());
//	        } catch (SQLException e) {
//	            printSQLException(e);
//	        }
//		}
		query = "SELECT item_menu.preco, item.descricao FROM item_menu INNER JOIN item ON item_menu.item_id = item.id INNER JOIN menu ON item_menu.menu_id = menu.id and menu.restaurante_designacao = '"+restaurante+"'";
		
//		query = "";
    	
    	return queryResult(query);
    }

    public static void main(String []args){
    	DBconnection2 db = new DBconnection2();
    	ArrayList<Object> cenas = db.currentMenu("Le good Casa do Pasto");
    	for(int i=0; i<cenas.size();i++){
    		System.out.println(cenas.get(i));
    	}
    }

}
