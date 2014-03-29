package cmpe281.lab.Model;

import java.util.Date;

public class Request {

	private int RequestId;	
	private int Status; //0 or 1
	private int ProjectId;
	private String EC2InstanceId;
	private String ProjectName;	
	private int ProjectPriority;
	private Date RequestTime;
	
	public Date getRequestTime() {
		return RequestTime;
	}
	public void setRequestTime(Date requestTime) {
		RequestTime = requestTime;
	}
	public int getProjectPriority() {
		return ProjectPriority;
	}
	public void setProjectPriority(int projectPriority) {
		ProjectPriority = projectPriority;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	
	public int getRequestId() {
		return RequestId;
	}
	public void setRequestId(int requestId) {
		RequestId = requestId;
	}
	
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getProjectId() {
		return ProjectId;
	}
	public void setProjectId(int projectId) {
		ProjectId = projectId;
	}
	public String getEC2InstanceId() {
		return EC2InstanceId;
	}
	public void setEC2InstanceId(String eC2InstanceId) {
		EC2InstanceId = eC2InstanceId;
	}
	
	
}
