package cmpe281.lab.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import cmpe281.lab.EC2.*;

/**
 * Servlet implementation class MonitorServlet
 */
@WebServlet("/monitor")
public class MonitorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AmazonEC2Client ec2;
	private EC2Client ec2API;	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MonitorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (ec2 == null) {
			AWSCredentials credentials = new PropertiesCredentials(
					getClass().getClassLoader().getResourceAsStream("AwsCredentials.properties"));
			ec2 = new AmazonEC2Client(credentials);
		}
		try		
		{
			ec2API = new EC2Client();
		}
		catch(Exception ex)
		{		
		}
				
		request.setAttribute("ec2", ec2);
		request.setAttribute("ec2API",ec2API);
		request.getRequestDispatcher("WEB-INF/jsp/monitor.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
