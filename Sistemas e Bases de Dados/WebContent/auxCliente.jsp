<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	ArrayList<Object> restaurantes = db.listPlaces();
	db.disconnectSQL();
%>


<h3>
	<i class="fa fa-angle-right"></i> Pretende Registar-se?
</h3>
<div class="row">
	<div class="col-md-4">

		<div class="content-panel">

			<form action="auxRegister.jsp" method="POST">
				<input class="form-control" type="text" name='nome' />Nome</input> 
				<input class="form-control" type="text" name="data" />Data</input>
				<h3>
					<i class="fa fa-angle-right"></i> Escolha o Restaurante
				</h3>
				<select class="form-control" name="restaurantes">
					<%
						out.println("<option value='empty'> </option>");
						for (int i = 0; i < restaurantes.size(); i++) {
							out.println("<option value='" + restaurantes.get(i) + "'>" + restaurantes.get(i) + "</option>");
						}
					%>

				</select> <input class="btn btn-primary" type="submit" value="Submit" />
			</form>
		</div>
	</div>
</div>
<h3>
	<i class="fa fa-angle-right"></i> Continuar como anonimo
</h3>
<div class="row">
	<div class="col-md-6">

		<div class="content-panel">
			<button class='btn btn-primary' onclick="continuarAnonimo()">Continuar</button>
		</div>
	</div>
</div>

