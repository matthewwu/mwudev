package cmpe281.lab.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmpe281.lab.DAL.EC2InstanceDAO;
import cmpe281.lab.EC2.EC2Client;
import cmpe281.lab.Model.EC2Instance;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.RunInstancesResult;

/**
 * Servlet implementation class InstanceServlet
 */
@WebServlet("/instance")
public class InstanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("new")) {
				// Get AMIs from Amazon
				List<Image> images = new EC2Client().getFreeAMIs();
				request.setAttribute("images", images);
				// Display the form
				request.getRequestDispatcher("WEB-INF/jsp/instanceForm.jsp").forward(request, response);
			}
			else if (action.equalsIgnoreCase("start")) {
				try {
					new EC2Client().startInstance(request.getParameter("id"));
				} catch (AmazonServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AmazonClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("monitor");
			}
			else if (action.equalsIgnoreCase("stop")) {
				new EC2Client().stopInstances(request.getParameter("id"));
				response.sendRedirect("monitor");
			}
			else if (action.equalsIgnoreCase("terminate")) {
				new EC2Client().terminateInstance(request.getParameter("id"));
				//Remove instance from database
				new EC2InstanceDAO().delete(request.getParameter("id"));
				response.sendRedirect("monitor");
			}
			else if (action.equalsIgnoreCase("updateAll")) {
//				PrintWriter out = response.getWriter();
				
				// Get instances from Amazon
				Set<Instance> amazonInstances = new EC2Client().GetAllInstance();
				// Get instances from local DB
				List<EC2Instance> localInstances = new EC2InstanceDAO().getAll();
				EC2InstanceDAO IDAO = new EC2InstanceDAO();
				
				//delete all instance from db before insert
				for(EC2Instance item:localInstances)
				{
					IDAO.delete(item.getInstanceId());
				}
				
				//insert all instance (except the one running our project)
				for (Instance amazonInstance : amazonInstances) {
					if (!amazonInstance.getInstanceId().equals("i-0731d161")) {
						// check if the instance is NOT with status terminated
						if (!amazonInstance.getState().getName().equals("terminated")) {
							EC2Instance ec2instance = new EC2Instance();
							ec2instance.setInstanceId(amazonInstance.getInstanceId());
							ec2instance.setName(amazonInstance.getImageId());
							ec2instance.setStatus(amazonInstance.getState().getName());
							ec2instance.setDNS(amazonInstance.getPublicDnsName());
							ec2instance.setType(amazonInstance.getInstanceType());
							new EC2InstanceDAO().create(ec2instance);
						}
					}
				}
				response.sendRedirect("monitor");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("create")) {
				try {
					EC2Client Client = new EC2Client();
					EC2InstanceDAO InstanceDAO = new EC2InstanceDAO();
					//Mike
					RunInstancesResult Result = Client.createAMInstances(request.getParameter("imageId"), 1, 1, "mbp", "t1.micro", "us-east-1a","sg-bb4c99d3");
					//Matt
					//RunInstancesResult Result = Client.createAMInstances(request.getParameter("imageId"), 1, 1, "MattKey", "t1.micro", "us-east-1a","sg-cb9f4aa3");
					//Add the instance to our table for management
					List<Instance> ResultInstances = Result.getReservation().getInstances();
					for(Instance item:ResultInstances)
					{
						EC2Instance temp = new EC2Instance();
						temp.setInstanceId(item.getInstanceId());
						temp.setName(item.getKeyName());						
						temp.setStatus(item.getState().getName());
						temp.setType(item.getInstanceType());
						InstanceDAO.create(temp);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("monitor");
			}
		}
	}

}
