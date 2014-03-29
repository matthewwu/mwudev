package cmpe281.lab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cmpe281.lab.DAL.*;
import cmpe281.lab.Model.*;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		int uid = 1; 
		if(session.getAttribute("uid")!= null)
		{
			uid = Integer.parseInt(session.getAttribute("uid").toString());
		}
		
		List<TestProject> listProjects = new TestProjectDAO().getProjectByUser(uid);
		
		request.setAttribute("listProjects", listProjects);
		// fix when the user just connect to the project
		if (session.getAttribute("login") != null) {
			request.setAttribute("login", session.getAttribute("login").toString());
		}
		request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
