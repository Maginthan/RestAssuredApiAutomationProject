package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	//Instance of report name to be added in the Extent report directory
	String reportName;
	
	public void onStart(ITestContext testContext) {
		//Instance to create a time stamp for adding the report name with time stamp
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report-"+ timeStamp + ".html";
		
		sparkReporter = new ExtentSparkReporter("/Users/maginthangr/eclipse-workspace/RestApiProject.com/reports/"+reportName);
		
		//Configuring the spark reporter with theme, title, report name
		sparkReporter.config().setDocumentTitle("RestApiAutomationProject");
		sparkReporter.config().setReportName("Users API");
		sparkReporter.config().setTheme(Theme.DARK);
		
		//Setting system info to the extent reports
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Users API");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Maginthan");
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		//This is called when all the methods are executed of extent reports in order to generate the reports.
		extent.flush();
	}
	
	
	
	
}
