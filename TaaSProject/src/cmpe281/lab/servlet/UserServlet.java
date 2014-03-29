package cmpe281.lab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmpe281.lab.DAL.TaaSUserDAO;
import cmpe281.lab.DAL.UserTypeDAO;
import cmpe281.lab.Model.TaaSUser;
import cmpe281.lab.Model.UserType;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		// Step 1: is there an action
		if (action != null) {
			if (action.equalsIgnoreCase("delete")) {
				new TaaSUserDAO().delete(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("msg", "User #" + request.getParameter("id") + " deleted!");
				// Get all users from the DB
				List<TaaSUser> listUsers = new TaaSUserDAO().getAll();
				request.setAttribute("listUsers", listUsers);
				request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("new")) {
				// Create a blank user with ID = 0 so the form knows we want to create a new user
				TaaSUser user = new TaaSUser();
				user.setUserId(0);
				request.setAttribute("user", user);
				// Get userTypeList so we can display it in the form
				List<UserType> userTypeList = new UserTypeDAO().getAll();
				request.setAttribute("userTypeList", userTypeList);
				request.getRequestDispatcher("WEB-INF/jsp/userForm.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("update")) {
				// Get user info from ID so we can fill the form
				TaaSUser user = new TaaSUserDAO().get(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("user", user);
				// Get userTypeList so we can display it in the form
				List<UserType> userTypeList = new UserTypeDAO().getAll();
				request.setAttribute("userTypeList", userTypeList);
				request.getRequestDispatcher("WEB-INF/jsp/userForm.jsp").forward(request, response);
			}
		}
		else {
			// Get all users from the DB
			List<TaaSUser> listUsers = new TaaSUserDAO().getAll();
			request.setAttribute("listUsers", listUsers);
			request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("create")) {
				TaaSUser user = new TaaSUser();
				user.setUserName(request.getParameter("UserName"));
				user.setPassword(request.getParameter("Password"));
				user.setFullName(request.getParameter("FullName"));
				user.setUserTypeId(Integer.parseInt(request.getParameter("UserType")));
				// Insert new user in the database
				new TaaSUserDAO().create(user);
				// Add message to indicate creation of the new user
				String msg = "User '" + user.getFullName() + "' successfully added in the database!";
				request.setAttribute("msg", msg);
				// Get all users from the DB
				List<TaaSUser> listUsers = new TaaSUserDAO().getAll();
				request.setAttribute("listUsers", listUsers);
				request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("update")) {
				TaaSUser user = new TaaSUser();
				user.setUserId(Integer.parseInt(request.getParameter("id")));
				user.setUserName(request.getParameter("UserName"));
				user.setPassword(request.getParameter("Password"));
				user.setFullName(request.getParameter("FullName"));
				user.setUserTypeId(Integer.parseInt(request.getParameter("UserType")));
				// Update user in the database
				new TaaSUserDAO().update(user);
				// Add message to indicate update of the  user
				String msg = "User '" + user.getFullName() + "' successfully updated in the database!";
				request.setAttribute("msg", msg);
				// Get all users from the DB
				List<TaaSUser> listUsers = new TaaSUserDAO().getAll();
				request.setAttribute("listUsers", listUsers);
				request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
			}
		}
	}

}
