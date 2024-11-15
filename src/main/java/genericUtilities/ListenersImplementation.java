package genericUtilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation to ITestListener interface of TestNG
 * @author Chaitra M
 *
 */
public class ListenersImplementation implements ITestListener{
	
	//Capture the current system date and time for Screenshot name and Report Name
	Date d = new Date();
	SimpleDateFormat f = new SimpleDateFormat(" dd-MM-yyyy hh-mm-ss");
	String date = f.format(d);
	
	//For Extent Reports
	ExtentReports report;
	ExtentTest test;
	

	public void onTestStart(ITestResult result) {
		
		//Capture the method name of @Test
		String methodName = result.getMethod().getMethodName();
		
		/*@Test execution is started*/
		System.out.println(methodName+" -> Test script execution started");
		
		/*Intimate extent Reports for @Test execution*/
		test = report.createTest(methodName);
		
	}

	public void onTestSuccess(ITestResult result) {
		
		//Capture the method Name of @Test
		String methodName = result.getMethod().getMethodName();
		
		/*@Test execution is PASS*/
		System.out.println(methodName+" -> Test script is PASS");
		
		/*Log the status of test as PASS in Extent Report*/
		test.log(Status.PASS, methodName+" -> Test script is PASS");
		
	}

	public void onTestFailure(ITestResult result) {
		
		//Capture the method Name of @Test
		String methodName = result.getMethod().getMethodName();
		
		/*@Test execution is FAIL*/
		System.out.println(methodName+" -> Test script is FAIL");
		
		//Capture the exception
		System.out.println(result.getThrowable());
		
		/*Log the status of test as FAIL in extent report*/
		test.log(Status.FAIL, methodName+" -> Test script is FAIL");
		
		/*Log the exception in extent report*/
		test.log(Status.WARNING, result.getThrowable());
		
		/*Capture the Screenshot*/
		SeleniumUtility s = new SeleniumUtility();
				
		//Screenshot name configured
		String screenshotName = methodName+date;
				
		try {
			String path = s.captureScreenShot(BaseClass.sDriver, screenshotName);
			
			/*Attach the screenshot in extent report*/
			test.addScreenCaptureFromPath(path);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		
		//Capture the method Name of @Test
		String methodName = result.getMethod().getMethodName();
		
		/*@Test execution is SKIP*/
		System.out.println(methodName+" -> Test script is SKIP");
		
		//Capture the Exception
		System.out.println(result.getThrowable());
		
		/*Log the status of test as SKIP in extent report*/
		test.log(Status.SKIP, methodName+" -> Test script is SKIP" );
		
		/*log the exception in extent report*/
		test.log(Status.WARNING,result.getThrowable() );
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
		/*Suite level execution - <suite>*/
		System.out.println("Suite Execution started");
		
		/*Basic Configuration of Extent Report*/
		ExtentSparkReporter esr = new ExtentSparkReporter(".\\ExtentReports\\Report -"+date+".html");
		esr.config().setDocumentTitle("SWAG LABS Execution Report");
		esr.config().setReportName("Execution Build version 1.12");
		esr.config().setTheme(Theme.DARK);
		
		/*Feed the configuration to Extent reports class*/
		report = new ExtentReports();
		report.attachReporter(esr);
		report.setSystemInfo("Base Env", "Test Env");
		report.setSystemInfo("Base Browser", "Microsoft Edge");
		report.setSystemInfo("Base Platform", "Windows Family");
		report.setSystemInfo("Base URL", "TestEnv.com");
		report.setSystemInfo("Reporter Name", "Chaitra");
		
	}

	public void onFinish(ITestContext context) {
		
		/*Suite level execution*/
		System.out.println("Suite Execution finished");
		
		/*Generate the extent Report*/
		report.flush();
		
	}

	
}
