package cmpe281.lab.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmpe281.lab.DAL.*;
import cmpe281.lab.Model.*;

/**
 * Servlet implementation class AllocationServlet
 */
@WebServlet("/Allocation")
public class AllocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		List<EC2Instance> listInstances = new EC2InstanceDAO().getAvailableInstance();		
		
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("grantrequest")) {
				int Rid = Integer.parseInt(request.getParameter("rid"));
				//String InstanceId = request.getParameter("instanceid");
				
				RequestDAO DAL = new RequestDAO();
				DAL.UpdateRequest(Rid, 1, listInstances.get(0).getInstanceId());
				
				Request tmpRequest = DAL.GetRequest(Rid);
				
				TestProjectDAO PDAO = new TestProjectDAO();
				PDAO.UpdateProject(tmpRequest.getProjectId(), listInstances.get(0).getInstanceId());
				
			}			
		}
				
		List<TestProject> listProjects = new TestProjectDAO().getAll();
		List<Request> listRequests = new RequestDAO().getAll();
		request.setAttribute("listProjects", listProjects);
		request.setAttribute("listRequests", listRequests);
		request.setAttribute("listInstances", listInstances);
		request.getRequestDispatcher("WEB-INF/jsp/allocation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
