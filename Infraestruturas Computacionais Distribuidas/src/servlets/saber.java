package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class saber
 */
@WebServlet("/saber")
public class saber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public saber() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// nascido=2000-01-01 formato ISO
		LocalDate nascido=LocalDate.now();
		if(request.getParameter("nascido")!=null)
		  nascido=LocalDate.parse(request.getParameter("nascido"));
		response.setContentType("text/html");
        // Actual logic goes here.
	    PrintWriter out = response.getWriter();
	    out.println("<h1>Nascido em: " +nascido+ "</h1>");
	    out.println("<h1>" +data.saber(nascido) + "</h1>");
	    // TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
