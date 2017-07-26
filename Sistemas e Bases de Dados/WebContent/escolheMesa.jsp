<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	String restaurante = (String)session.getAttribute("restaurante");
	//lista mesas de um restaurante
	ArrayList<Object> mesas = db.listTables(restaurante);
	db.disconnectSQL();
%>
<br>
<div class="row">

	<div class="col-md-3">

		<div class="content-panel">
			<h4>
				<i class="fa fa-angle-right"></i>Escolha a sua mesa
			</h4>
			<select class="form-control" name="escolhaMesas" onclick="">
				
				<%
				out.println("<option value='empty'> </option>");
				for(int i=0; i<mesas.size();i++){
					out.println("<option value='"+mesas.get(i)+"'>"+mesas.get(i)+"</option>");
				}
				%>
			</select>
			<button class='btn btn-primary' onclick="atribuiMesa()">Submit</button>
		</div>

	</div>
	<!-- /col-md-12 -->
</div>