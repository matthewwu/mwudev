package cmpe281.lab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmpe281.lab.DAL.TaaSUserDAO;
import cmpe281.lab.DAL.TestProjectDAO;
import cmpe281.lab.Model.TaaSUser;
import cmpe281.lab.Model.TestProject;

/**
 * Servlet implementation class UserMappingServlet
 */
@WebServlet("/userMapping")
public class UserMappingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMappingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("removeUser")) {
				Integer pid = Integer.parseInt(request.getParameter("pid"));
				Integer uid = Integer.parseInt(request.getParameter("uid"));
				new TestProjectDAO().removeUserToProject(uid, pid);
				response.sendRedirect("userMapping?pid=" + pid);
			}
		}
		else {
			// get the project from request
			TestProject testProject = new TestProjectDAO().GetProject(Integer.parseInt(request.getParameter("pid")));
			request.setAttribute("testProject", testProject);
			// retrieve users associated with this project
			List<TaaSUser> userList = new TestProjectDAO().getUsersByProject(Integer.parseInt(request.getParameter("pid")));
			request.setAttribute("userList", userList);
			// retrieve all users
			List<TaaSUser> allUsers = new TaaSUserDAO().getAll();
			request.setAttribute("allUsers", allUsers);
			// forward the request to the JSP
			request.getRequestDispatcher("WEB-INF/jsp/userMapping.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("addUser")) {
				Integer pid = Integer.parseInt(request.getParameter("pid"));
				Integer uid = Integer.parseInt(request.getParameter("uid"));
				new TestProjectDAO().addUserToProject(uid, pid);
				response.sendRedirect("userMapping?pid=" + pid);
			}
		}
	}

}
