package twc.Automation.SmokeTestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Title;
import twc.Automation.Driver.Drivers;
import twc.Automation.General.DeviceStatus;
import twc.Automation.General.loginModule;
import twc.Automation.HandleMapLocal.MapLocalFunctions;
import twc.Automation.HandleWithApp.AppFunctions;
import twc.Automation.HandleWithAppium.AppiumFunctions;
import twc.Automation.HandleWithCharles.CharlesFunctions;
import twc.Automation.ReadDataFromFile.read_excel_data;
import twc.Automation.ReadDataFromFile.read_xml_data_into_buffer;
import twc.Automation.RetryAnalyzer.RetryAnalyzer;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Title;
import twc.Automation.Driver.Drivers;
import twc.Automation.General.loginModule;
import twc.Automation.HandleMapLocal.MapLocalFunctions;
import twc.Automation.HandleWithApp.AppFunctions;
import twc.Automation.HandleWithAppium.AppiumFunctions;
import twc.Automation.HandleWithCharles.CharlesFunctions;
import twc.Automation.RetryAnalyzer.RetryAnalyzer;
import twc.Automation.General.Functions;


public class smokeTestCases extends Drivers {
	
	
	/*@Test
	public static void Scroll() throws Exception{
		for(int scroll=1;scroll<=250;scroll++){
			System.out.println("times ="  +scroll);
			for(int i=1;i<=10 ;i++){
				//System.out.println("times" +i);
				Thread.sleep(2000);
				AppFunctions.Swipe();
				Thread.sleep(2000);
			}
			
			AppiumFunctions.Kill_launch();
		}
		CharlesFunctions.ExportSession();
		
	}*/
	
	/*@Test
	public static void Scroll() throws Exception{
		
			for(int i=1;i<=20 ;i++){
			System.out.println("times::::"  +i);
				AppiumFunctions.LaunchAppWithFullReset();
				read_xml_data_into_buffer.read_bb_native();
				
			}		
		CharlesFunctions.ExportSession();
		
	}*/
	
	
	//Validate BB on Severe1
	/*@Test(priority=1,enabled = true,retryAnalyzer=RetryAnalyzer.class)
		@Title("Content Mode Severe1")
		public void Smoke_Test_Case_Content_Mode_Severe1() throws Exception{
			System.out.println("================= Content Mode Severe1 Test Case Started =========================");
			AppiumFunctions.Kill_launch();
			MapLocalFunctions.contentModeModule("severe1");
			CharlesFunctions.charlesOpen();
			CharlesFunctions.browserForMapLocal();
			CharlesFunctions.ClearSessions();
			AppiumFunctions.Kill_launch();
			CharlesFunctions.ExportSession();
			Functions.maplocal_bbcall_validation("MapLocal","severe1");
			System.out.println("================= Content Mode Severe1 Test Case End =========================");
		}*/

		//Valodate BB on Severe2
		/*@Test(priority=2,enabled = true,retryAnalyzer=RetryAnalyzer.class)
		@Title("Content Mode Severe2")
		public void Smoke_Test_Case_Content_Mode_Severe2() throws Exception{
			System.out.println("================= Content Mode Severe2 Test Case Started =========================");

			MapLocalFunctions.contentModeModule("severe2");
			//AppiumFunctions.UnInstallApp(); //added this
			AppiumFunctions.Kill_launch();
//			AppiumFunctions.Kill_launch();
			CharlesFunctions.ClearSessions();
			//AppiumFunctions.Kill_launch();
			AppiumFunctions.Kill_launch();; //added this
			CharlesFunctions.ExportSession();
			Functions.maplocal_bbcall_validation("MapLocal","severe2");
			System.out.println("================= Content Mode Severe2 Test Case End =========================");
		}*/
		
	//CXTG Values Test Case 
	@Title("Verify WeatherFx API Call Response When User Selects Contextual Locations")
	@Test(priority=3,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	public void
	Smoke_Test_Case_C415129_Verify_Weatherfx_API_Call_Response_When_User_Selects_Contextual_Locations()
			throws Exception{
		System.out.println("================= CXTG Values Test Case Started =========================");
		MapLocalFunctions.contentModeModule("normal");
		CharlesFunctions.startSessionBrowserData();
		//AppiumFunctions.LaunchAppWithFullReset("false");
		AppFunctions.compareBuildVersion();//commeneted dec 9
		AppiumFunctions.Kill_launch();
		AppiumFunctions.Swipe_Up();
		loginModule.login();
		AppFunctions.Kill_Launch_App();
		Functions.verifySavedAddressList(1);
		CharlesFunctions.StopExportSessionXMLFile();
		Functions.validate_CXTG_values("WFXTrigger");
		System.out.println("================= CXTG Values Test Case End =========================");
	}

	// Wfxtg Values Test Case 
	@Test(priority=4,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify WeatherFX API Request For Wfxtg Value")
	public void
	Smoke_Test_Case_C333173_Verify_WeatherFX_API_Request_For_Wfxtg_Value()
			throws Exception{
		System.out.println("================= Wfxtg Values Test Case Started =========================");

		if(RetryAnalyzer.count >= 1){
			Smoke_Test_Case_C415129_Verify_Weatherfx_API_Call_Response_When_User_Selects_Contextual_Locations();
			Functions.validate_Wfxtg_Value_With_Pubads_Call("WFXTrigger");
		}
		else{
			Functions.validate_Wfxtg_Value_With_Pubads_Call("WFXTrigger");
		}

		System.out.println("================= Wfxtg Values Test Case End =========================");
	}


	// Factual Call Test Case 
	@Test(priority=5,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Factual Call")
	public void Smoke_Test_Case_C333174_Verify_Factual_Call() throws
	Exception{
		System.out.println("================= Factual Call Test Case Started =========================");

		if(RetryAnalyzer.count >= 1){
			Smoke_Test_Case_C415129_Verify_Weatherfx_API_Call_Response_When_User_Selects_Contextual_Locations();
			Functions.validate_API_Call_With_PubAds_Call("LocationWFX");
		}
		else{
			Functions.validate_API_Call_With_PubAds_Call("LocationWFX");
		}

		System.out.println("================= Factual Call Test Case End =========================");
	}


	//Lotame Call Test Case 
	@Test(priority=6,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Lotame Call")
	public void Smoke_Test_Case_C333182_Verify_Lotame_Call() throws
	Exception{
		System.out.println("================= Lotame Call Test Case Started =========================");

		if(RetryAnalyzer.count >= 1){
			Smoke_Test_Case_C415129_Verify_Weatherfx_API_Call_Response_When_User_Selects_Contextual_Locations();
			Functions.validate_API_Call_With_PubAds_Call("Lotame");
		}
		else{
			Functions.validate_API_Call_With_PubAds_Call("Lotame");
		}

		System.out.println("================= Lotame Call Test Case End =========================");
	}

	// Pull To Refresh Test Case 
	@Test(priority=7,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Ad Call Is Made On Pull To Refresh")
	public void
	Smoke_Test_Case_C333179_Verify_Ad_Call_Is_Made_On_Pull_To_Refresh()
			throws Exception{
		System.out.println("================= Pull To Refresh Test Case Started =========================");
		CharlesFunctions.startSessionBrowserData();
		AppiumFunctions.Kill_launch();
		AppFunctions.Pull_To_Refresh("Pulltorefresh");
		CharlesFunctions.StopExportSessionXMLFile();
		Functions.bb_call_validation("Pulltorefresh");
		
		System.out.println("================= Pull To Refresh Test Case  End =========================");
	}


	//ApiumFunctions.LaunchAppWithFullReset(); Launch Test Case 
	@Test(priority=8,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Ad Calls On Clean Launch")
	public void Smoke_Test_Case_C333172_Verify_Ad_Calls_On_Clean_Launch()
			throws Exception{
		System.out.println("================= Clean App Launch Test Case Started =========================");
		CharlesFunctions.startSessionBrowserData();
		//AppiumFunctions.Kill_launch();
		AppiumFunctions.Kill_launch();
		AppFunctions.CleanLaunch_launch("General");
		CharlesFunctions.ExportSession();
		Functions.clean_App_Launch("CleanLaunch");
		System.out.println("================= Clean App Launch Test Case End =========================");
	}
//
//
	//Hourly Module Extended Page Ad Test Case 
	@Test(priority=9,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Ad Is Present On Extended Hourly Page")
	public void
	Smoke_Test_Case_C333175_Verify_Ad_Is_Present_On_Extended_Hourly_Page()
			throws Exception {
		System.out.println("================= Hourly Module Test Case Started =========================");
		//AppiumFunctions.Kill_launch();
		AppiumFunctions.Kill_launch();
		AppFunctions.verify_adpresent_onextended_page("Hourly");
		System.out.println("================= Hourly Module Test Case End =========================");
	}
//
//
	//Daily Module Extended Page Ad Test Case 
	@Test(priority=10,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Ad Is Present On Extended Daily Page")
	public void
	Smoke_Test_Case_C333180_Verify_Ad_Is_Present_On_Extended_Daily_Page()
			throws Exception {
		System.out.println("================= Daily Module Test Case Started =========================");

		if(RetryAnalyzer.count >= 1){
			AppiumFunctions.Kill_launch();
			AppFunctions.verify_adpresent_onextended_page("Daily");
		}
		else{
			AppFunctions.verify_adpresent_onextended_page("Daily");
		}

		System.out.println("================= Daily Module Test Case End =========================");
	}


	// RADAR And MAPS Module Extended Page Ad Test Case 
	@Test(priority=11,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Ad Is Present On Extended Map Page")
	public void
	Smoke_Test_Case_C333176_Verify_Ad_Is_Present_On_Extended_Map_Page()
			throws Exception {
		System.out.println("================= MAP Module Test Case Started =========================");

		if(RetryAnalyzer.count >= 1){
			AppiumFunctions.Kill_launch();
			AppFunctions.verify_adpresent_onextended_page("Map");
		}
		else{

			AppFunctions.verify_adpresent_onextended_page("Map");
		}

		System.out.println("================= MAP Module Test Case End =========================");
	}



	// News Module Extended Page Ad Test Case 
	@Test(priority=12,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Ad Is Present On Extended News Page")
	public void Smoke_Test_Case_C333175_Verify_Ad_Is_Present_On_Extended_News_Page()throws Exception {
		System.out.println("================= NEWS Module Test Case Started =========================");

		if(RetryAnalyzer.count >= 1){
			AppiumFunctions.Kill_launch();
			AppFunctions.verify_adpresent_onextended_page("News");
		}
		else{
			//AppiumFunctions.Kill_launch();
			AppFunctions.verify_adpresent_onextended_page("News");
		}

		System.out.println("================= NEWS Module Test Case End =========================");
	}
//	 
//	
//	
//
	//Test Mode BB Call Test Case 
	/*@Test(priority=13,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify BB Ad Call In Test Mode")
	public void Smoke_Test_Case_C658710_Verify_BB_Ad_Call_In_Test_Mode()
			throws Exception{
		System.out.println("================= Verify BB Call In Test Mode Test Case Started =========================");

		AppiumFunctions.Kill_launch();
		loginModule.login();
		AppFunctions.Change_to_Test_Mode("TestMode");
		//CharlesFunctions.startSessionBrowserData();
		CharlesFunctions.ClearSessions();
		//AppFunctions.verifyBBCallLocationFromListInTestMode("AddressPage","Bridgeton");
		CharlesFunctions.ExportSession();
		Functions.bb_call_validation("TestMode");
		System.out.println("================= Verify BB Call In Test Mode Test Case End =========================");
	}*/

	/*@Test(priority=14,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify ThirdParty Beacons Call")
	public void Verify_ThirdParty_Beacons_Call() throws Exception{
		System.out.println("================= ThirdpartyBecon Test Case Started =========================");

		if(RetryAnalyzer.count >= 1){
			Smoke_Test_Case_C658710_Verify_BB_Ad_Call_In_Test_Mode();
			Functions.beacons_validation("ThirdpartyBecon");
		}
		else{
			Functions.beacons_validation("ThirdpartyBecon");
		}

		System.out.println("================= ThirdpartyBecon Test Case End =========================");
	}*/
	/*@Test(priority=15,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Video Playlist PubAd Call")
	public void Smoke_Test_Case_C344253_Verify_Playlist_Pubads_Call() throws
	Exception{
		System.out.println("================= PreRoll Video Call Test Case Started =========================");
		//CharlesFunctions.startSessionBrowserData();
		AppiumFunctions.Kill_launch();
		AppFunctions.verify_Vedio_Module_Click_On_Forecast_Video("PreRollVideo");
		CharlesFunctions.ExportSession();
		Functions.bb_call_validation("PreRollVideo");
		//CharlesFunctions.BrowserClosed();
		//CharlesFunctions.charlesClose();
		System.out.println("================= PreRoll Video Call Test Case End =========================");
	}*/


	/*@Test(priority=16,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Fourth Party Beacon Call In Test Mode")
	public void Verify_Fourth_Party_Beacon() throws Exception{
		System.out.println("================= Verify BB Call In Test Mode Test Case for forthparty beacon Started =========================");

		AppiumFunctions.Kill_launch();

		CharlesFunctions.startSessionBrowserData();//changed
		AppFunctions.verifyBBCallLocationFromListInTestMode("AddressPage","Chinook");
		CharlesFunctions.StopExportSessionXMLFile();
		Functions.beacons_validation("FourthpartyBeacon");
		System.out.println("================= Verify BB Call In Test Mode Test Casefor forthparty beacon End =========================");
	}*/


	// Verify WatsonAd call
	/*@Test(priority=17,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify WatsonAds Call")
	public void Smoke_Test_Case_Verify_Watson_Ads() throws Exception{
		System.out.println("===========================Verify WatsonAds Call started====================");
		//Navigate to testMode
		CharlesFunctions.startSessionBrowserData();
		CharlesFunctions.charlesOpen();
		 AppiumFunctions.UnInstallApp();
		CharlesFunctions.openCharlesBrowser();
		AppiumFunctions.Kill_launch();
		Thread.sleep(15000);
		//AppFunctions.Change_to_Test_Mode("TestMode");
		Thread.sleep(15000);//needed here
		loginModule.login();
		//AppFunctions.Kill_Launch_App();
		AppFunctions.verifyBBCallLocationFromListInTestMode("AddressPage","Hoquiam"); //west
		AppFunctions.Swipe_Conter(5);
		AppFunctions.search_with_watson_ad("Hot");
		Thread.sleep(2000);
//		Ad.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[1]/android.view.View[1]").click();		
	 System.out.println("============================Verify WatsonAds Call ended====================");
	}*/
//

	// GoRun & Ski Module Extended Page Ad Test Case 
	/*@Test(priority=18,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Ad Is Present On Extended Health Page")
	public void
	Smoke_Test_Case_XXXXX_Verify_calls_Present_On_Extended_GoRun_Page()
			throws Exception {
		System.out.println("================= GoRun Module Test Case Started =========================");
		String[] module=new String[]{"GoRun"};
		int count=0;
		for(int i=0; i<module.length; i++){
			AppFunctions.verify_subModule(module[i], i);
		}
		System.out.println("================= GoRun Module Test Case End =========================");
	}*/

    /*@Test(priority=19,enabled = false,retryAnalyzer=RetryAnalyzer.class)
	@Title("With Alert Mode")
	public void Smoke_Test_Case_With_Alert_Mode() throws Exception{
		System.out.println("================= With Alert Mode Test Case Started =========================");
		MapLocalFunctions.alertModeChanges("withalert");
		CharlesFunctions.charlesOpen();
		 CharlesFunctions.browserForMapLocal();
		CharlesFunctions.ClearSessions();
		AppiumFunctions.Kill_launch();
		CharlesFunctions.ExportSession();
		Functions.maplocal_bbcall_validation("MapLocal","withalert");
		System.out.println("================= With Alert Mode Test Case End =========================");
	}



	@Test(priority=20,enabled = false,retryAnalyzer=RetryAnalyzer.class)
	@Title("With Out Alert Mode")
	public void Smoke_Test_Case_With_Out_Alert_Mode() throws Exception{
		System.out.println("================= With Out Alert Mode Test Case Started =========================");
		MapLocalFunctions.alertModeChanges("withoutalert");
		CharlesFunctions.ClearSessions();
		AppiumFunctions.Kill_launch();
		CharlesFunctions.ExportSession();
		Functions.maplocal_bbcall_validation("MapLocal","withoutalert");
		System.out.println("================= With Out Alert Mode Test Case End =========================");
	}


	//Addzone Test Script
	@Test(priority=25,enabled = true,retryAnalyzer=RetryAnalyzer.class)
	@Title("Verify Ad on Add Zones")
	public void Smoke_Test_Case_Verify_Ad_Is_Present_On_ADDZONE_Page() throws Exception {
		AppFunctions.Adzone_Validations();
	}
	
	
	//push alerts Test Script
		@Test(priority=26,enabled = true,retryAnalyzer=RetryAnalyzer.class)
		@Title("Verify push notifications")
		public void Smoke_Test_Case_Verify_Push_notifications() throws Exception {
			System.out.println("push notifications testcase started");
			//AppFunctions.pushalerts_Validations();
			System.out.println("push notifications testcase end");
		}*/
		
		// Verify the home screen ads  and ad calls for  Native BB and Native Animated BB  
		/*@Test(priority=13,enabled = true,retryAnalyzer=RetryAnalyzer.class)
		@Title("Verify home screen ads  and ad calls")
		public void Smoke_Test_Case_homescreenadsandadcalls()
				throws Exception{
			System.out.println("================= Verify home screen ads  and ad calls for  Native BB  Test Case Started =========================");

			AppiumFunctions.LaunchApp();
			AppFunctions.Change_to_airlock_testMode("TestMode");
			//CharlesFunctions.startSessionBrowserData();
			CharlesFunctions.ClearSessions();
			//AppFunctions.verifyBBCallLocationFromListInTestMode("AddressPage","Bridgeton");
			//Functions.bb_call_validation("TestMode");
			System.out.println("================= Verify home screen ads  and ad calls for  Native BB  Test Case End =========================");
		}*/

		
	@BeforeTest
	public void Before_Test() throws Exception {
		System.out
		.println("================= Before Test Started =========================");
		CharlesFunctions.charlesOpen();
		// AppiumFunctions.UnInstallApp();
		CharlesFunctions.openCharlesBrowser();
		MapLocalFunctions.alertModeChanges("withoutalert");
		AppiumFunctions.killADB();
		//AppiumFunctions.UnInstallApp();
		AppiumFunctions.AppiumServerStop();
		AppiumFunctions.AppiumServerStart();
		AppiumFunctions.LaunchAppWithFullReset();
	  //Ad.quit();
		//AppiumFunctions.LaunchAppWithFullReset();
		
		System.out.println("================= Before Test End =========================");
	}
	
	
	
	//
		
	/*@AfterTest
	public void After_Test() throws Exception{
		System.out.println("After Test started");

		//TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
		Ad.quit();
		
	}*/

}
