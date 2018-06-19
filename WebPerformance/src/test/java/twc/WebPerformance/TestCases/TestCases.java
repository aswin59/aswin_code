package twc.WebPerformance.TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import twc.WebPerformance.General.*;


public class TestCases {
	
	@Test(priority=1)
	public void WebPerformance_TestCase() throws Exception{
		System.out.println("================= Start WebPerformance Test Case Started =========================");
		
		for(int i=1;i<=3;i++){
		Functions.openChromeBrowser();
		Functions.chromeDriverClose();
		Functions.ReadXMLfile();
		System.out.println("================= End WebPerformance Test Case End =========================");
		}
		
	}
	@BeforeTest
	public void Before_Test() throws Exception{
		System.out.println("================= Before Test Started =========================");
		Functions.charlesOpen();
		Functions.openCharlesBrowser();
		Functions.startSessionBrowserData();
		System.out.println("================= Before Test End =========================");
	}
	@AfterTest
	public void After_Test() throws Exception{
		System.out.println("================= Before Test Started =========================");
		Functions.charlesClose();
		Functions.firefoxDriversClose();
		System.out.println("================= Before Test End =========================");
	}
}
