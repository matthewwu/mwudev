package cmpe281.lab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestDataServlet
 */
@WebServlet("/TestDataServlet")
public class TestDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String welcomeMessage = "Test "+name;
		request.setAttribute("welcomeMessage", welcomeMessage);
		/*
		* Set the content type(MIME Type) of the response.
		*/
		/*
		response.setContentType("text/html");
		 
		PrintWriter out = response.getWriter();
		
		* Write the HTML to the response
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> A very simple servlet example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>"+welcomeMessage+"</h1>");		
		out.println("</body>");
		out.println("</html>");
		//out.close();
		
		// Now use our Coffee Model above
		// EDIT: modified to use DAO class
	    //TaaSUser ce = new TaaSUser();

	    List<TaaSUser> result = new TaaSUserDAO().getAll();

	    // Use the below code to debug the program if you get problems 
	    response.setContentType("text/html");
	    
	    out.println("Coffee Selection Advise<br>");

	    Iterator<TaaSUser> it = result.iterator();
	    while(it.hasNext()) {
	      out.print("<br>try: " + it.next());
	    }
	    */
	    request.getRequestDispatcher("WEB-INF/jsp/Info.jsp").forward(request, response);
	}

}
