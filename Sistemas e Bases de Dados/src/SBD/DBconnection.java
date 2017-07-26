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

public class DBconnection {

	static final String url = "jdbc:mysql://127.0.0.1:3306/sbd";

	static private Connection conn;
	static String user, password;
	private final static int maxStock = 2000;

	private int mesCorrente = 0, anoCorrente = 0, horaCorrente = 0, minutoCorrente = 0, segundoCorrente = 0,
			diaCorrente = 0;
	private int mes = 0, ano = 0, horas = 0, minutos = 0, segundos = 0, dia = 0;

	public DBconnection() {
		// TODO Auto-generated constructor stub
		user = null;
		password = null;
		connectSQL();

	}

	public void connectSQL() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			user = "SBD2017";
			password = "cenas";
			conn = DriverManager.getConnection(url, user, password);
			if (conn == null)
				System.out.println("entrei");
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void printSQLException(SQLException ex) {
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

	public void disconnectSQL() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				printSQLException(e);
			}
		}
	}

	public boolean loginAuthentication(String nome, String password) {

		String query;
		query = "SELECT funcionario.password, funcionario.nome FROM funcionario WHERE password = '" + password
				+ "' and nome='" + nome + "' ";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					return false;
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}

		return true;
	}

	public boolean registerClient(String nome, String data) {
		String query;
		query = "INSERT INTO cliente VALUES('0','" + nome + "','" + data + "','0', CURRENT_TIMESTAMP)";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			printSQLException(e);
		}

		return false;
	}
	
	public int getMesaTakeaway(String restaurante){
		String query;
		query = "SELECT mesa.id FROM mesa WHERE mesa.lugares ='0' and mesa.restaurante_designacao='"+restaurante+"'";
		
		int mesaID = (int)queryResult(query).get(0);
		
		return mesaID;
	}
	
	public ArrayList<Object> listPedidosDaMesa(String mesaID){
		String query;
		query="SELECT mesa_pedido.pedido_id FROM mesa_pedido WHERE mesa_pedido.mesa_id="+mesaID;
		
		return queryResult(query);
		
	}
	
	public ArrayList<Object> listaIngredientes(){
		
		String query;
		query ="SELECT ingrediente.designacao FROM ingrediente";
		
		return queryResult(query);
			
	}
	
	public ArrayList<Object> listaFornecedoresIng(String ingrediente){
		String query;
		
		query="SELECT fornecedor_ingrediente.fornecedor_designacao FROM fornecedor_ingrediente "
				+ "INNER JOIN ingrediente "
				+ "ON ingrediente.designacao = '"+ingrediente+"' and (ingrediente.id = fornecedor_ingrediente.ingrediente_id)";
		
		return queryResult(query);
	}
	
	public int precoIngFornecedor(String ingrediente, String fornecedor){
		String query;
		query="SELECT fornecedor_ingrediente.preco FROM fornecedor_ingrediente "
				+ "INNER JOIN ingrediente "
				+ "ON ingrediente.designacao ='"+ingrediente+"' and (ingrediente.id = fornecedor_ingrediente.ingrediente_id) "
						+ "WHERE fornecedor_ingrediente.fornecedor_designacao ='"+fornecedor+"'";
		
		return (int)queryResult(query).get(0);
	}
	
	public int getIDingrediente(String ingrediente){
		String query;
		query = "SELECT ingrediente.id FROM ingrediente WHERE ingrediente.designacao ='"+ingrediente+"'";
		
		
		return (int)queryResult(query).get(0);
	}
	
	public boolean registaPedidoFornecedor(int ingredienteID,String fornecedor,String quantidade,String restaurante){
		String query;
		query="INSERT INTO regista_pedido_fornecedor VALUES('0','"+ingredienteID+"','"+fornecedor+"','"+quantidade+"','"+restaurante+"')";
		
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			printSQLException(e);
			return false;
		}
		
		return true;
	}
	
	public ArrayList<Object> listItemsPedido(String pedidoID){
		String query;
		query="SELECT item.descricao, item_pedido.estado FROM item_pedido "
				+ "INNER JOIN item ON item.id = item_pedido.item_id "
				+ "WHERE item_pedido.pedido_id="+pedidoID;
		return queryResult(query);
	}
	
	public boolean modidificaEstadoItemPedido(int pedidoID,int itemID,String estado,String uniqueID){
		
		String query;
		query="UPDATE item_pedido SET item_pedido.estado = '"+estado+"' WHERE item_pedido.pedido_id='"+pedidoID+"' and item_pedido.item_id='"+itemID+"' and item_pedido.id = '"+uniqueID+"'";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			printSQLException(e);
			return false;
		}
		
		return true;
	}
	
	public String getFuncionarioRest(String nome, String password){
		String query;
		query = "SELECT restaurante_funcionario.restaurante_designacao "
				+ "FROM restaurante_funcionario INNER JOIN funcionario "
				+ "ON funcionario.id = restaurante_funcionario.funcionario_id"
				+ " WHERE(funcionario.nome='"+nome+"' and funcionario.password='"+password+"')";

		return (String)queryResult(query).get(0);
	}
	
	public ArrayList<Object> listaMesas(String restaurante){
		String query;
		query="SELECT mesa.id FROM mesa WHERE mesa.lugares !=0 and mesa.restaurante_designacao='"+restaurante+"'";
		
		return queryResult(query);
	}

	public ArrayList<Object> currentMenuDataHora(String data, String horaa, String restaurante) {
		String query = "";
		Calendar cal = Calendar.getInstance();
		String modo, tipo;
		modo = null;
		tipo = null;
		// parse dos valores
		ano = Integer.parseInt(data.substring(0, 4));
		mes = Integer.parseInt(data.substring(6));
		dia = Integer.parseInt(data.substring(4, 6));
		horas = Integer.parseInt(horaa.substring(0, 2));

		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.MONTH, mes - 1);
		cal.set(Calendar.DAY_OF_MONTH, dia);
		cal.set(Calendar.HOUR_OF_DAY, horas);
		cal.set(Calendar.MINUTE, minutos);
		if (segundos != 0) {
			cal.set(Calendar.SECOND, segundos);
		}
		this.mes = cal.get(Calendar.MONTH);
		this.dia = cal.get(Calendar.DAY_OF_WEEK);
		this.ano = cal.get(Calendar.YEAR);

		this.horas = cal.get(Calendar.HOUR_OF_DAY);
		this.minutos = cal.get(Calendar.MINUTE);
		this.segundos = cal.get(Calendar.SECOND);
		// almoço 12:00 - 15:00
		// jantar 19:00 - 22:00
		// almoco - dia semana
		if ((horas >= 12 && horas < 15) && (dia > 1 && dia < 7)) {
			modo = "dia-semana";
			tipo = "almoco";
		}

		else if ((horas >= 19 && horas < 22) && (dia > 1 && dia < 7)) {
			modo = "dia-semana";
			tipo = "jantar";
		}

		else if ((horas >= 12 && horas < 15) && !(dia > 1 && dia < 7)) {
			modo = "fds/feriado";
			tipo = "almoco";
		}

		else if ((horas >= 19 && horas < 22) && !(dia > 1 && dia < 7)) {
			modo = "fds/feriado";
			tipo = "jantar";
		}

		query = "SELECT item.descricao,item_menu.preco FROM item_menu INNER JOIN item ON item_menu.item_id = item.id "
				+ "INNER JOIN menu ON item_menu.menu_id = menu.id and menu.restaurante_designacao = '" + restaurante
				+ "'" + " and (menu.modo = '" + modo + "' and menu.tipo = '" + tipo + "')";

		return queryResult(query);
	}

	public ArrayList<Object> currentMenu(String restaurante) {
		String query = "";
		Calendar cal = Calendar.getInstance();
		String modo, tipo;
		modo = null;
		tipo = null;

		diaCorrente = cal.get(Calendar.DAY_OF_WEEK);
		mesCorrente = cal.get(Calendar.MONTH) + 1;
		anoCorrente = cal.get(Calendar.YEAR);

		horaCorrente = cal.get(Calendar.HOUR_OF_DAY);
		minutoCorrente = cal.get(Calendar.MINUTE);
		segundoCorrente = cal.get(Calendar.SECOND);

		// almoço 12:00 - 15:00
		// jantar 19:00 - 22:00
		// almoco - dia semana
		if ((horaCorrente >= 12 && horaCorrente < 15) && (diaCorrente > 1 && diaCorrente < 7)) {
			modo = "dia-semana";
			tipo = "almoco";
		}
		// jantar - dia semana
		else if ((horaCorrente >= 19 && horaCorrente < 22) && (diaCorrente > 1 && diaCorrente < 7)) {
			modo = "dia-semana";
			tipo = "jantar";
		}
		// almoco - fds
		else if ((horaCorrente >= 12 && horaCorrente < 15) && !(diaCorrente > 1 && diaCorrente < 7)) {
			modo = "fds/feriado";
			tipo = "almoco";
		}
		// jantar - fsd
		else if ((horaCorrente >= 19 && horaCorrente < 22) && !(diaCorrente > 1 && diaCorrente < 7)) {
			modo = "fds/feriado";
			tipo = "jantar";
		}

		// query = "SELECT item.descricao, item_menu.preco, item_menu.menu_id FROM item_menu INNER
		// JOIN item ON item_menu.item_id = item.id "
		// + "INNER JOIN menu ON item_menu.menu_id = menu.id and
		// menu.restaurante_designacao = '"+restaurante+"'"
		// + " and (menu.modo = '"+modo+"' and menu.tipo = '"+tipo+"')";
		//
		query = "SELECT item.descricao, item_menu.preco, item_menu.menu_id FROM item_menu "
				+ "INNER JOIN item ON item_menu.item_id = item.id "
				+ "INNER JOIN menu ON item_menu.menu_id = menu.id and menu.restaurante_designacao = '" + restaurante+ "'" 
				+ " and (menu.modo = 'dia-semana' and menu.tipo = 'jantar')";

		return queryResult(query);
	}

	public int getClientId(String nomeCliente) {

		String query;

		query = "SELECT cliente.id FROM cliente WHERE cliente.nome = '" + nomeCliente + "'";

		return (int) queryResult(query).get(0);
	}

	public boolean clienteExists(String nome) {
		String query;

		query = "SELECT cliente.nome FROM cliente WHERE cliente.nome = '" + nome + "'";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					return false;
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		}

		return true;
	}

	public int getClientPedidoID(int idCliente) {
		String query;

		query = "SELECT pedido.id FROM pedido WHERE pedido.cliente_id = '" + idCliente + "'";

		return (int) queryResult(query).get(0);
	}
	
	public void atribuiDividaCliente(int clientID,String itemID,String menuID){
		String query;
		query="UPDATE cliente "
				+ "INNER JOIN item_menu ON item_menu.item_id = '"+itemID+"' and item_menu.menu_id ='"+menuID+"' "
						+ "SET cliente.divida = cliente.divida + item_menu.preco "
						+ "WHERE cliente.id = '"+clientID+"'";
		
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public int clienteDivida(int clienteID){
		String query;
		query="SELECT cliente.divida FROM cliente WHERE cliente.id ='"+clienteID+"'";
		
		int divida = (int)queryResult(query).get(0);
		
		return divida;
	}
	
	public void registaQuantidadeIngrediente(int ingredienteID,int quantidade, String restaurante){
		String query;
		int quantidadeAtual =0;
		query="UPDATE stock_ingrediente "
				+ "INNER JOIN stock "
				+ "ON stock.id = stock_ingrediente.stock_id and stock.restaurante_designacao='"+restaurante+"' "
						+ "SET stock_ingrediente.stock_quantidade = stock_ingrediente.stock_quantidade - '"+quantidade+"' "
								+ "WHERE stock_ingrediente.ingrediente_id ='"+ingredienteID+"'";
		
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		//check rotura
		
		query="SELECT stock_ingrediente.stock_quantidade FROM stock_ingrediente "
				+ "INNER JOIN stock ON stock.restaurante_designacao = '"+restaurante+"' "
						+ "WHERE stock_ingrediente.stock_id = stock.id and stock_ingrediente.ingrediente_id='"+ingredienteID+"'";
		
		quantidadeAtual = (int)queryResult(query).get(0);
		
		if(quantidadeAtual < maxStock/20){
			query = "UPDATE stock_ingrediente "
				+ "INNER JOIN stock "
				+ "ON stock.id = stock_ingrediente.stock_id and stock.restaurante_designacao='"+restaurante+"' "
						+ "SET stock_ingrediente.rotura = 'rotura' "
								+ "WHERE stock_ingrediente.ingrediente_id ='"+ingredienteID+"'";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				printSQLException(e);
			}
		}
		
	}
	
	public ArrayList<Object> getClienteAnos(String restaurante){
		
		String query;
		String mesCompare,dayCompare,dataCompare;
		String current,currentAux;
		ArrayList<Object> result,cliente;
		cliente = new ArrayList<Object>();
		mesCompare="0";
		dayCompare="0";
		Calendar cal = Calendar.getInstance();

		diaCorrente = cal.get(Calendar.DAY_OF_MONTH);
		mesCorrente = cal.get(Calendar.MONTH) + 1;
		anoCorrente = cal.get(Calendar.YEAR);
		
		horaCorrente = cal.get(Calendar.HOUR_OF_DAY);
		minutoCorrente = cal.get(Calendar.MINUTE);
		segundoCorrente = cal.get(Calendar.SECOND);
		if(mesCorrente > 10){
			mesCompare = ""+mesCorrente;
		}else{
			mesCompare += mesCorrente;
		}
		
		if(diaCorrente > 10){
			dayCompare = ""+diaCorrente;
		}else{
			dayCompare += diaCorrente;
		}
		
		dataCompare = dayCompare + "-" + mesCompare;
		
		query = "SELECT cliente.dataNascimento,cliente.nome, mesa.id FROM cliente "
				+ "INNER JOIN pedido ON pedido.cliente_id = cliente.id "
				+ "INNER JOIN mesa_pedido ON mesa_pedido.pedido_id = pedido.id "
				+ "INNER JOIN mesa ON mesa.id = mesa_pedido.mesa_id AND mesa.restaurante_designacao = '"+restaurante+"'";
		
		
		result = queryResult(query);
		
		for(int i=0; i<result.size();i+=3){
			current = (String)result.get(i);
			currentAux = current.substring(0,5);
			if(currentAux.compareTo(dataCompare)==0){
				//nome
				cliente.add(result.get(i+1));
				//mesa
				cliente.add(result.get(i+2));
			}
		}
		
		return cliente;
	}
	
	public ArrayList<Object> listaStocksRestaurante(String restaurante){
		String query;
		query="SELECT ingrediente.designacao,stock_ingrediente.stock_quantidade FROM stock_ingrediente"
				+ " INNER JOIN ingrediente ON ingrediente.id = stock_ingrediente.ingrediente_id"
				+ " INNER JOIN stock ON stock.id = stock_ingrediente.stock_id and stock.restaurante_designacao='"+restaurante+"'";
		
		return queryResult(query);
	}
	
	public ArrayList<Object> listaStockRoturaRestaurante(String restaurante){
		String query;
		query="SELECT ingrediente.designacao,stock_ingrediente.stock_quantidade FROM stock_ingrediente"
				+ " INNER JOIN ingrediente ON ingrediente.id = stock_ingrediente.ingrediente_id"
				+ " INNER JOIN stock ON stock.id = stock_ingrediente.stock_id and stock.restaurante_designacao='"+restaurante+"'"
						+ " WHERE stock_ingrediente.rotura='rotura'";
		
		return queryResult(query);
	}
	
	public ArrayList<Object> itemsMaisLucro(){
		String query;
		query="SELECT item.descricao, SUM(registo_venda_item.preco) AS NumberSold FROM registo_venda_item "
				+ "INNER JOIN (SELECT DISTINCT(registo_venda_item.item_id) FROM registo_venda_item) as f "
				+ "ON registo_venda_item.item_id = f.item_id "
				+ "INNER JOIN item ON item.id = registo_venda_item.item_id "
				+ "GROUP BY descricao "
				+ "ORDER BY NumberSold DESC";
		
		
		return queryResult(query);
	}
	
	public ArrayList<Object> itemsMaisVendidos(){
		String query;
		query="SELECT item.descricao, COUNT(registo_venda_item.item_id) AS NumberSold FROM registo_venda_item "
				+ "INNER JOIN (SELECT DISTINCT(registo_venda_item.item_id) FROM registo_venda_item) as f "
				+ "ON registo_venda_item.item_id = f.item_id "
				+ "INNER JOIN item ON item.id = registo_venda_item.item_id "
				+ "GROUP BY descricao "
				+ "ORDER BY NumberSold DESC";
		
		
		return queryResult(query);
		
	}

	public void registarVenda(String menuID, String itemID){
		String query;
		query="SELECT item_menu.preco FROM item_menu WHERE item_menu.item_id ='"+itemID+"' and item_menu.menu_id='"+menuID+"'";
		
		int preco = (int)queryResult(query).get(0);
		
		query="INSERT INTO registo_venda_item VALUES('"+itemID+"','"+preco+"',CURRENT_TIMESTAMP)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("DEBUG ADD REGISTAR VENDA");
			printSQLException(e);
		}
		
	}
	
	public boolean adicionarPedido(int clientID) {
		boolean hasPedido;
		hasPedido = clientHasPedido(clientID);
		String estado, query;
		//estado default
		if (!hasPedido) {
			query = "INSERT INTO pedido VALUES ('0','" + clientID + "')";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				System.out.println("DEBUG ADD PEDIDO");
				printSQLException(e);
			}
		}

		return hasPedido;
	}

	private boolean clientHasPedido(int clientID) {
		String query;

		query = "SELECT pedido.cliente_id FROM pedido WHERE pedido.cliente_id = '" + clientID + "'";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			try (ResultSet rs = stmt.executeQuery()) {
				if (!rs.next()) {
					return false;
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
			System.out.println("DEBUG CLIENT HAS PEDIDO");
		}

		return true;
	}
	
	public int getItemID(String descricao){
		String query;
		query = "SELECT item.id FROM item WHERE item.descricao='"+descricao+"'";
		
		return (int) queryResult(query).get(0);
	}
	
	public boolean adicionarItemPedido(int itemID, int pedidoID){
		
		String query,estado;
		estado = "preparando";
		query = "INSERT INTO item_pedido VALUES ('"+itemID+"','"+pedidoID+"','"+estado+"')";
		
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			printSQLException(e);
			return false;
		}
		
		return true;
	}
	
	private boolean checkMesa(int pedidoID){
		String query;
		query="SELECT mesa_pedido.mesa_id FROM mesa_pedido WHERE mesa_pedido.pedido_id = '"+pedidoID+"'";
		
		int size = queryResult(query).size();
		if(size!=0){
			return true;
		}else{
			return false;
		}
	}
	
	public int adicionarPedidoMesa(int pedidoID, int mesaID){
		String query;
		query = "";
		
		boolean mesaCheck = checkMesa(pedidoID);
		if(!mesaCheck){
			query="INSERT INTO mesa_pedido VALUES('"+mesaID+"','"+pedidoID+"')";
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				printSQLException(e);
			}
			//caso não tenha mesa retorn -1 que indica SUCESSO
			return -1;
		}else{
			//se já existe mesa associada retorna o valor do ID DA MESA
			query = "SELECT mesa_pedido.mesa_id FROM mesa_pedido WHERE mesa_pedido.pedido_id = "+pedidoID;
			
			return (int)queryResult(query).get(0);	
		}
		
	}
	
	public ArrayList<Object> consultaPedido(int pedidoID){
		String query;
		
		query = "SELECT item.descricao,item_pedido.estado "
				+ "FROM item_pedido INNER JOIN item ON (item.id = item_pedido.item_id) "
				+ "and item_pedido.pedido_id = '"+pedidoID+"'";
		
		
		return queryResult(query);
	}
	
	public ArrayList<Object> listTables(String restaurante){
		String query;
		query = "SELECT mesa.id FROM mesa WHERE mesa.restaurante_designacao = 'Casa do Pasto' and mesa.lugares != 0";
		
		return queryResult(query);
	}

	public ArrayList<Object> listPlaces() {
		String query;
		query = "SELECT restaurante.designacao FROM restaurante";

		return queryResult(query);
	}

	
	public int getUniqueID(String pedidoID, int itemID){
		String query;
		query="SELECT item_pedido.id FROM item_pedido WHERE item_pedido.pedido_id = '"+pedidoID+"' and item_pedido.item_id='"+itemID+"'";
		
		return (int)queryResult(query).get(0);
		
	}
}
