<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="SBD.DBconnection"%>
<%@ page import="java.util.ArrayList"%>

<%
	DBconnection db = new DBconnection();
	ArrayList<Object> restaurantes = db.listPlaces();
	db.disconnectSQL();
%>
<h3>
	<i class="fa fa-angle-right"></i> Consulta Ementa por data e hora
</h3>
<div class="row">
	<div class="col-md-3">
		<div class="content-panel">
			Data:<input class="form-control" type="date" name="inputDate" /> 
			Hora:<input class="form-control" type="time" name="inputTime" /> 
			
			Restaurantes:<select class="form-control" name="restaurantes">
				<%
					out.println("<option value='empty'> </option>");
					for (int i = 0; i < restaurantes.size(); i++) {
						out.println("<option value='" + restaurantes.get(i) + "'>" + restaurantes.get(i) + "</option>");
					}
				%>
			</select>
			<button class='btn btn-primary' onclick="loadDocDH()">Procurar</button>
		</div>
		<! --/content-panel -->
	</div>
	<!-- /col-md-12 -->
</div>

<div id="results"></div>
<!-- row -->