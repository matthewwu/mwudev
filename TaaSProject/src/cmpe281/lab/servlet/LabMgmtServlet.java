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
 * Servlet implementation class LabMgmtServlet
 */
@WebServlet("/LabMgmt")
public class LabMgmtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LabMgmtServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<TestLab> listLabs = new TestLabDAO().getAll();
		List<VirtualTestLab> listVLabs = new VirtualTestLabDAO().getAll();
		List<TestProject> listProjects = new TestProjectDAO().getAll();
		request.setAttribute("listLabs", listLabs);
		request.setAttribute("listVLabs", listVLabs);
		request.setAttribute("listProjects", listProjects);
		
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			
			if (action.equalsIgnoreCase("newTestLab")) {
				// Get List of all users with admin status
				List<TaaSUser> adminList = new TaaSUserDAO().getAllAdmin();
				request.setAttribute("adminList", adminList);
				// display new Test Lab form
				request.getRequestDispatcher("WEB-INF/jsp/testLab/newTestLabForm.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("edit")) {
				// TODO
			} else if (action.equalsIgnoreCase("delete")) {
				new TestLabDAO().delete(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect("LabMgmt");
			} else if (action.equalsIgnoreCase("newVirtualTestLab")) {
				// get list of all test lab
				List<TestLab> testLabList = new TestLabDAO().getAll();
				request.setAttribute("testLabList", testLabList);
				// get list of all project manager + admin
				List<TaaSUser> pmList = new TaaSUserDAO().getAllPMs();
				pmList.addAll(new TaaSUserDAO().getAllAdmin());
				request.setAttribute("pmList", pmList);
				// display new virtual lab form
				request.getRequestDispatcher("WEB-INF/jsp/testLab/newVirtualTestLabForm.jsp").forward(request, response);
			} else if (action.equalsIgnoreCase("newTestProject")) {
				// get list of all virtual test lab
				List<VirtualTestLab> vTLabList = new VirtualTestLabDAO().getAll();
				request.setAttribute("vTLabList", vTLabList);
				// display new test project form
				request.getRequestDispatcher("WEB-INF/jsp/testLab/newTestProject.jsp").forward(request, response);
			}
			
			else if (action.equalsIgnoreCase("requestinstance")) {
				int Pid = Integer.parseInt(request.getParameter("pid"));
				TestProject project = new TestProjectDAO().GetProject(Pid);
				if(project.getProjectId() > 0)
				{
					Request ec2Request = new Request();
					ec2Request.setProjectId(project.getProjectId());
					RequestDAO DAL = new RequestDAO();
					DAL.create(ec2Request);
					TestProjectDAO PDAL = new TestProjectDAO();
					PDAL.UpdateProjectRequest(Pid, 1);
					
				}
				listProjects = new TestProjectDAO().getAll();
				request.setAttribute("listProjects", listProjects);
				request.getRequestDispatcher("WEB-INF/jsp/LabMgmt.jsp").forward(request, response);
			}

			else if (action.equalsIgnoreCase("removeinstance")) {
				int Pid = Integer.parseInt(request.getParameter("pid"));
				TestProject project = new TestProjectDAO().GetProject(Pid);
				if(project.getProjectId() > 0)
				{
					TestProjectDAO pDAO = new TestProjectDAO();
					pDAO.UpdateProject(Pid, "");
					TestProjectDAO PDAL = new TestProjectDAO();
					PDAL.UpdateProjectRequest(Pid, 0);
					
				}
				listProjects = new TestProjectDAO().getAll();
				request.setAttribute("listProjects", listProjects);
				request.getRequestDispatcher("WEB-INF/jsp/LabMgmt.jsp").forward(request, response);
			} else {
				listProjects = new TestProjectDAO().getAll();
				request.setAttribute("listProjects", listProjects);
				request.getRequestDispatcher("WEB-INF/jsp/LabMgmt.jsp").forward(request, response);
			}
			
		} else {
		
			
			request.getRequestDispatcher("WEB-INF/jsp/LabMgmt.jsp").forward(request, response);
		}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("createTestLab")) {
				TestLab testLab = new TestLab();
				testLab.setAdminUserId(Integer.parseInt(request.getParameter("AdminUserId")));
				testLab.setLabName(request.getParameter("LabName"));
				new TestLabDAO().create(testLab);
				response.sendRedirect("LabMgmt");
			}
			else if (action.equalsIgnoreCase("createVirtualTestLab")) {
				VirtualTestLab vtl = new VirtualTestLab();
				vtl.setParentTestLabId(Integer.parseInt(request.getParameter("ParentTestLab")));
				vtl.setPMId(Integer.parseInt(request.getParameter("ProjectManagerId")));
				vtl.setVLabName(request.getParameter("VLabName"));
				new VirtualTestLabDAO().create(vtl);
				response.sendRedirect("LabMgmt");
			}
			else if (action.equalsIgnoreCase("createTestProject")) {
				TestProject tp = new TestProject();
				tp.setName(request.getParameter("TPName"));
				tp.setParentVLabId(Integer.parseInt(request.getParameter("VLabId")));
				tp.setPriority(Integer.parseInt(request.getParameter("Priority")));
				new TestProjectDAO().create(tp);
				response.sendRedirect("LabMgmt");
			}
		}
	}

}
