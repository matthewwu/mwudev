package cmpe281.lab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cmpe281.lab.Model.*;
import cmpe281.lab.DAL.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("logout")) {
				HttpSession session = request.getSession();
				session.invalidate();
				response.sendRedirect("home");
			}
		}
		else {
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		TaaSUserDAO UserDAO = new TaaSUserDAO();
		TaaSUser user = UserDAO.getUserByName(login);
		HttpSession session = request.getSession();
		session.setAttribute("uid",user.getUserId());
		session.setAttribute("login", login);
		session.setAttribute("password", password);
		response.sendRedirect("home");
	}

}
