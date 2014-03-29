package cmpe281.lab.EC2;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.model.Datapoint;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;

public class EC2Client {

	private AWSCredentials credentials;
	private static AmazonEC2 ec2;
	private static AmazonCloudWatchClient CWClient;
	@SuppressWarnings("unused")
	private KeyPair keyPair;

	public EC2Client() {
		try {
			credentials = new PropertiesCredentials(getClass().getClassLoader()
					.getResourceAsStream("AwsCredentials.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ec2 = new AmazonEC2Client(credentials);
		CWClient = new AmazonCloudWatchClient(credentials);
	}

	/*
	 * Get All Instances
	 */
	public Set<Instance> GetAllInstance() {
		DescribeInstancesResult describeInstancesRequest = ec2
				.describeInstances();
		List<Reservation> reservations = describeInstancesRequest
				.getReservations();
		Set<Instance> instances = new HashSet<Instance>();

		for (Reservation reservation : reservations) {
			instances.addAll(reservation.getInstances());
		}

		return instances;
	}

	/*
	 * Get Instance by its Id
	 */
	public Instance GetInstance(String InstanceId) {
		Instance temp = null;
		Set<Instance> MyInstances = GetAllInstance();
		for (Instance item : MyInstances) {
			if (item.getInstanceId() == InstanceId) {
				temp = item;
			}
		}

		return temp;
	}

	/*
	 * Start instance by its Id
	 */
	public StartInstancesResult startInstance(final String instanceId)
			throws AmazonServiceException, AmazonClientException,
			InterruptedException {
		StartInstancesRequest startRequest = new StartInstancesRequest()
				.withInstanceIds(instanceId);
		StartInstancesResult startResult = ec2.startInstances(startRequest);

		return startResult;
	}

	/*
	 * Stop instance by its Id
	 */
	public StopInstancesResult stopInstances(String instanceId)
			throws AmazonServiceException, AmazonClientException {
		StopInstancesRequest stopRequest = new StopInstancesRequest()
				.withInstanceIds(instanceId);
		StopInstancesResult stopResult = ec2.stopInstances(stopRequest);

		return stopResult;
	}

	/*
	 * Terminate instance by its Id
	 */
	public TerminateInstancesResult terminateInstance(String instanceId) {
		TerminateInstancesRequest terminateRequest = new TerminateInstancesRequest()
				.withInstanceIds(instanceId);
		return ec2.terminateInstances(terminateRequest);
	}

	/*
	 * Get all AIMs
	 */
	public List<Image> GetAIMs() {

		DescribeImagesResult ImageResult = ec2.describeImages();

		return ImageResult.getImages();

	}

	/*
	 * Get AMIs eligible with free tier
	 */
	public List<Image> getFreeAMIs() {
		// Create the filter to only get AMIs owner = Amazon and EBS based
		// Filter f1 = new Filter("root-device-type");
		// f1.withValues("ebs");
		// Filter f2 = new Filter("owner-alias");
		// f2.withValues("amazon");
		Filter f1 = new Filter("image-id");
		f1.withValues("ami-e565ba8c").withValues("ami-a29943cb")
				.withValues("ami-8f8c54e6");
		// Create the request to only get the AMIs eligible with the free tier
		DescribeImagesRequest describeImagesRequest = new DescribeImagesRequest();
		describeImagesRequest.withFilters(f1);
		// Object to return from EC2
		DescribeImagesResult imageResult = ec2
				.describeImages(describeImagesRequest);
		return imageResult.getImages();
	}

	/*
	 * createAMInstances(amiId, 1, 1, keyPairName, "m1.small", "us-east-1a");
	 */
	public RunInstancesResult createAMInstances(String AMId, int min, int max,
			String keyPairName, String insType, String availabilityZone,String SecGroup)
			throws Exception {
		ec2.setEndpoint("ec2.amazonaws.com");

		// CREATE EC2 INSTANCES
//		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
//				.withInstanceType("t1.micro").withImageId(AMId).withMinCount(min)
//				.withMaxCount(max).withSecurityGroupIds(SecGroup)
//				.withKeyName(keyPairName);
		
		RunInstancesRequest hardcodeRequest = new RunInstancesRequest()
				.withInstanceType("t1.micro").withImageId("ami-0ef55167").withMinCount(min)
				.withMaxCount(max).withSecurityGroupIds("sg-bd00b5d5")
				.withKeyName(keyPairName);

		return ec2.runInstances(hardcodeRequest);

		// RunInstancesRequest request = new RunInstancesRequest();
		// request.setImageId(AMId);
		// request.setInstanceType(insType);
		// request.setMinCount(min);
		// request.setMaxCount(max);
		// // set to zone
		// Placement p = new Placement();
		// p.setAvailabilityZone(availabilityZone);
		// request.setPlacement(p);
		// request.setImageId(AMId);
		// // Create key pair for user – for the moment I prefer using the same
		// key [Mike]
		// // CreateKeyPairRequest kpReq = new CreateKeyPairRequest();
		// // kpReq.setKeyName(keyPairName);
		// // CreateKeyPairResult kpres = ec2.createKeyPair(kpReq);
		// // keyPair = kpres.getKeyPair();
		//
		// request.setKeyName(keyPairName);
		// RunInstancesResult runInstancesRes = ec2.runInstances(request);
		//
		// return runInstancesRes;

	}

	public Double GetLastHourCPUAvg(String InstanceId) {
		Double Usage = 0.0;
		long offsetInMilliseconds = 1000 * 60 * 60 * 1;
		GetMetricStatisticsRequest request = new GetMetricStatisticsRequest()
				.withStartTime(
						new Date(new Date().getTime() - offsetInMilliseconds))
				.withNamespace("AWS/EC2")
				.withPeriod(60 * 60)
				// .withDimensions(new
				// Dimension().withName("InstanceType").withValue("t1.micro"))
				.withDimensions(
						new Dimension().withName("InstanceId").withValue(
								InstanceId)).withMetricName("CPUUtilization")
				.withStatistics("Average", "Maximum").withEndTime(new Date());
		GetMetricStatisticsResult result = CWClient
				.getMetricStatistics(request);
		// System.out.println("metrics label = "+result.getLabel());
		// System.out.println("Count:" + result.getDatapoints().size());

		for (Datapoint dp : result.getDatapoints()) {
			Usage = dp.getAverage();
		}
		return Usage;

	}

	public Double GetLastHourCPUMax(String InstanceId) {
		Double Usage = 0.0;
		long offsetInMilliseconds = 1000 * 60 * 60 * 1;
		GetMetricStatisticsRequest request = new GetMetricStatisticsRequest()
				.withStartTime(
						new Date(new Date().getTime() - offsetInMilliseconds))
				.withNamespace("AWS/EC2")
				.withPeriod(60 * 60)
				// .withDimensions(new
				// Dimension().withName("InstanceType").withValue("t1.micro"))
				.withDimensions(
						new Dimension().withName("InstanceId").withValue(
								InstanceId)).withMetricName("CPUUtilization")
				.withStatistics("Average", "Maximum").withEndTime(new Date());
		GetMetricStatisticsResult result = CWClient
				.getMetricStatistics(request);
		// System.out.println("metrics label = "+result.getLabel());
		// System.out.println("Count:" + result.getDatapoints().size());

		for (Datapoint dp : result.getDatapoints()) {
			Usage = dp.getMaximum();
		}
		return Usage;

	}
}
