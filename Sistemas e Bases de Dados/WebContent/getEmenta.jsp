<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	ArrayList<Object> restaurantes = db.listPlaces();
	db.disconnectSQL();
%>
<h3>
	<i class="fa fa-angle-right"></i> Consulta Ementa
</h3>
<div class="row">

	<div class="col-md-3">

		<div class="content-panel">
			<h4>
				<i class="fa fa-angle-right"></i>Escolha o Restaurante
			</h4>

			<select class="form-control" name="restaurantes"
				onchange="onchangeEmenta(this.value)">
				
				<%
				out.println("<option value='empty'> </option>");
				for(int i=0; i<restaurantes.size();i++){
					out.println("<option value='"+restaurantes.get(i)+"'>"+restaurantes.get(i)+"</option>");
				}
				%>
			</select>

		</div>

	</div>
	<!-- /col-md-12 -->
</div>

<section id="results">







</section>
<!-- row -->

